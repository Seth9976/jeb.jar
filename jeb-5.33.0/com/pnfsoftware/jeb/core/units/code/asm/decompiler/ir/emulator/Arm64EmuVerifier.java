package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.unicorn.Arm64Const;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.unicorn.UnicornEngine;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.unicorn.UnicornException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.unicorn.UnicornLibrary;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Arm64EmuVerifier {
   private static final ILogger logger = GlobalLog.getLogger(Arm64EmuVerifier.class);
   private EEmulator emu;
   private IVirtualMemory vm;
   private int pagesize;
   private UnicornEngine mu;
   private List lastMemWrites = new ArrayList();
   private int iterationCount;
   private UnicornLibrary.CodeHook hook_code = new Arm64EmuVerifier$1(this);
   private UnicornLibrary.MemoryHook hook_mem = new Arm64EmuVerifier$2(this);

   Arm64EmuVerifier(EEmulator var1, boolean var2) throws Exception {
      Assert.a(var1 != null);
      this.emu = var1;
      this.vm = var1.getVirtualMemory();
      this.pagesize = this.vm.getPageSize();
      Assert.a(this.pagesize == 4096);
      Assert.a(var1.getGlobalContext().getNativeContext().getProcessor().getType() == ProcessorType.ARM64);
      this.mu = new UnicornEngine(2, 0);

      try {
         this.mu.setCpuModel(3);
         this.initRegisters();
         if (!var2) {
            this.initMemory();
         }

         this.mu.hookAdd(2160, this.hook_mem);
      } catch (Exception var4) {
         this.mu.close();
         this.mu = null;
         throw var4;
      }
   }

   private void initRegisters() {
      long var1 = this.mu.regRead64(261);
      var1 |= 3145728L;
      this.mu.regWrite64(261, var1);
      this.mu.regWrite64(262, this.emu.getRegisterValue("TPIDR_EL0").getValueAsLong());
      this.mu.regWrite64(260, this.emu.getPCAddress());
      this.writeReg64("SP", this.emu.getRegisterValue("XSP").getValueAsLong());

      for (int var3 = 0; var3 <= 30; var3++) {
         this.writeReg64("X" + var3, this.emu.getRegisterValue("X" + var3).getValueAsLong());
      }

      for (int var6 = 0; var6 <= 31; var6++) {
         this.writeReg128("V" + var6, this.emu.getRegisterValue("V" + var6));
      }

      long var7 = (this.emu.getRegisterValue("oVerflow").getValueAsLong() & 1L) << 28;
      var7 |= (this.emu.getRegisterValue("Carry").getValueAsLong() & 1L) << 29;
      var7 |= (this.emu.getRegisterValue("Zero").getValueAsLong() & 1L) << 30;
      var7 |= (this.emu.getRegisterValue("Negative").getValueAsLong() & 1L) << 31;
      this.mu.regWrite64(3, var7);
   }

   private void initMemory() throws Exception {
      ArrayList var1 = new ArrayList(this.vm.getAllocatedPageBases());
      int var2 = 0;

      while (var2 < var1.size()) {
         long var3 = (Long)var1.get(var2);
         var2++;

         long var5;
         for (var5 = var3 + this.pagesize; var1.contains(var5); var2++) {
            var5 += this.pagesize;
         }

         int var7 = (int)(var5 - var3);
         byte[] var8 = new byte[var7];
         this.vm.read(var3, var8.length, var8, 0, true);
         this.mu.memMap(var3, var7, 7);
         this.mu.memWrite(var3, var8);
      }
   }

   public void close() {
      if (this.mu != null) {
         try {
            this.mu.close();
            this.mu = null;
         } catch (Exception var2) {
            logger.catching(var2);
         }
      }
   }

   public int getIterationCount() {
      return this.iterationCount;
   }

   public void emulate(long var1, IInstruction var3) {
      if (var1 != this.mu.regRead64(260)) {
         throw new IllegalStateException("Unexpected PC!");
      } else if (!this.lastMemWrites.isEmpty()) {
         throw new IllegalStateException("The last emulation was not compared!");
      } else {
         if ((var1 & this.pagesize - 1) == 0L && !this.mu.memIsMapped(var1)) {
            this.loadMemoryPage(var1);
         }

         int var4 = this.emu.getState().getEvaluationCount();
         if (var4 % 100000 == 0) {
            Object[] var10000 = new Object[]{var4, var1, var3};
         }

         try {
            this.mu.emuStart(var1, var1 + var3.getSize(), 0L, 1L);
         } catch (UnicornException var8) {
            if (var8.getErrorCode() != 8) {
               throw var8;
            }

            long var6 = this.mu.regRead64(260);
            if (var6 == var1) {
               throw var8;
            }
         }

         this.iterationCount++;
      }
   }

   private boolean loadMemoryPage(long var1) {
      Assert.a((var1 & this.pagesize - 1) == 0L);
      byte[] var3 = new byte[this.pagesize];

      try {
         this.vm.read(var1, this.pagesize, var3, 0, true);
      } catch (MemoryException var4) {
         return false;
      }

      this.mu.memMap(var1, this.pagesize, 7);
      this.mu.memWrite(var1, var3);
      return true;
   }

   public void compareLastEmulation() throws IllegalStateException {
      this.compareRegisters();
      this.compareMemory();
   }

   private void compareRegisters() throws IllegalStateException {
      this.checkReg64("XSP", this.readReg64("SP"));

      for (int var1 = 0; var1 <= 30; var1++) {
         this.checkReg64("X" + var1, this.readReg64("X" + var1));
      }

      for (int var2 = 0; var2 <= 31; var2++) {
         this.checkReg128("V" + var2, this.readReg128("V" + var2));
      }
   }

   private void compareMemory() throws IllegalStateException {
      for (Couple var2 : this.lastMemWrites) {
         long var3 = (Long)var2.getFirst();
         int var5 = (Integer)var2.getSecond();
         byte[] var6 = new byte[var5];

         try {
            this.vm.read(var3, var5, var6, 0, true);
         } catch (MemoryException var8) {
            throw new IllegalStateException("Cannot read memory at requested address", var8);
         }

         byte[] var7 = new byte[var5];
         this.mu.memRead(var3, var7);
         if (Arrays.compare(var6, var7) != 0) {
            throw new IllegalStateException(
               Strings.ff(
                  "Mismatched memory: addr=0x%X size=%d expected(uni)=0x%s current(jeb)=0x%s",
                  var3,
                  var5,
                  Formatter.byteArrayToHex(var7),
                  Formatter.byteArrayToHex(var6)
               )
            );
         }
      }

      this.lastMemWrites.clear();
   }

   private void checkReg64(String var1, long var2) throws IllegalStateException {
      long var4 = this.emu.getRegisterValue(var1).getValueAsLong();
      if (var4 != var2) {
         throw new IllegalStateException(Strings.ff("Mismatched register value: reg=%s expected(uni)=0x%X current(jeb)=0x%X", var1, var2, var4));
      }
   }

   private void checkReg128(String var1, byte[] var2) throws IllegalStateException {
      IEImm var3 = this.emu.getRegisterValue(var1);
      byte[] var4 = this.gendecImmToLEBytes(var3);
      if (Arrays.compare(var4, var2) != 0) {
         throw new IllegalStateException(
            Strings.ff(
               "Mismatched register value: reg=%s expected(uni)=0x%s current(jeb)=0x%s", var1, Formatter.byteArrayToHex(var2), Formatter.byteArrayToHex(var4)
            )
         );
      }
   }

   private void writeReg64(String var1, long var2) {
      this.mu.regWrite64(this.getRegId(var1), var2);
   }

   private void writeReg128(String var1, IEImm var2) {
      Assert.a(var2.getBitsize() == 128);
      byte[] var3 = this.gendecImmToLEBytes(var2);
      this.mu.regWrite(this.getRegId(var1), var3);
   }

   private long readReg64(String var1) {
      return this.mu.regRead64(this.getRegId(var1));
   }

   private byte[] readReg128(String var1) {
      byte[] var2 = new byte[16];
      this.mu.regRead(this.getRegId(var1), var2);
      return var2;
   }

   private int getRegId(String var1) {
      try {
         return Arm64Const.class.getField("UC_ARM64_REG_" + var1).getInt(null);
      } catch (ReflectiveOperationException var3) {
         this.close();
         throw new RuntimeException(var3);
      }
   }

   private byte[] gendecImmToLEBytes(IEImm var1) {
      Assert.a(var1.getBitsize() % 8 == 0);
      byte[] var2 = new byte[var1.getBitsize() / 8];
      int var3 = 0;
      byte[] var4 = var1.getValue().toByteArray();

      for (int var5 = var4.length - 1; var5 >= 0; var5--) {
         var2[var3++] = var4[var5];
      }

      if (var4[0] < 0) {
         while (var3 < var2.length) {
            var2[var3++] = -1;
         }
      }

      return var2;
   }
}
