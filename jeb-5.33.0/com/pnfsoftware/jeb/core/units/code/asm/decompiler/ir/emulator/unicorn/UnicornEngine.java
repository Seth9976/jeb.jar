package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.unicorn;

import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import java.io.File;

public class UnicornEngine implements AutoCloseable {
   private Pointer uc;
   private byte[] data1 = new byte[1];
   private byte[] data4 = new byte[4];
   private byte[] data8 = new byte[8];

   public UnicornEngine(int var1, int var2) {
      PointerByReference var3 = new PointerByReference();
      int var4 = UnicornLibrary.INSTANCE.uc_open(var1, var2, var3);
      if (var4 != 0) {
         throw new UnicornException("Failed to initialize Unicorn: " + var4);
      } else {
         this.uc = var3.getValue();
      }
   }

   @Override
   public void close() {
      this.verifyOpen();
      UnicornLibrary.INSTANCE.uc_close(this.uc);
      this.uc = null;
   }

   private void verifyOpen() {
      if (this.uc == null) {
         throw new IllegalStateException("The emulator instance is closed");
      }
   }

   public String getErrorMessage(int var1) {
      return UnicornLibrary.INSTANCE.uc_strerror(var1);
   }

   public int getLastError() {
      return UnicornLibrary.INSTANCE.uc_errno(this.uc);
   }

   public void setCpuModel(int var1) {
      int var2 = UnicornLibrary.INSTANCE.uc_ctl(this.uc, 7, var1);
      if (var2 != 0) {
         throw new UnicornException("Failed to set CPU model", var2);
      }
   }

   public boolean memIsMapped(long var1) {
      int var3 = UnicornLibrary.INSTANCE.uc_mem_read(this.uc, var1, this.data1, this.data1.length);
      return var3 == 0;
   }

   public void memMap(long var1, long var3, int var5) {
      int var6 = UnicornLibrary.INSTANCE.uc_mem_map(this.uc, var1, var3, var5);
      if (var6 != 0) {
         throw new UnicornException("Failed to map memory", var6);
      }
   }

   public void memWrite(long var1, byte[] var3) {
      int var4 = UnicornLibrary.INSTANCE.uc_mem_write(this.uc, var1, var3, var3.length);
      if (var4 != 0) {
         throw new UnicornException("Failed to write memory", var4);
      }
   }

   public void memRead(long var1, byte[] var3) {
      int var4 = UnicornLibrary.INSTANCE.uc_mem_read(this.uc, var1, var3, var3.length);
      if (var4 != 0) {
         throw new UnicornException("Failed to read memory", var4);
      }
   }

   public int memRead32(long var1) {
      byte[] var3 = this.data4;
      this.memRead(var1, var3);
      return EndianUtil.littleEndianBytesToInt(var3);
   }

   public long memRead64(long var1) {
      byte[] var3 = this.data8;
      this.memRead(var1, var3);
      return EndianUtil.littleEndianBytesToLong(var3);
   }

   public void regRead(int var1, byte[] var2) {
      int var3 = UnicornLibrary.INSTANCE.uc_reg_read(this.uc, var1, var2);
      if (var3 != 0) {
         throw new UnicornException("Failed to read register", var3);
      }
   }

   public void regWrite(int var1, byte[] var2) {
      int var3 = UnicornLibrary.INSTANCE.uc_reg_write(this.uc, var1, var2);
      if (var3 != 0) {
         throw new UnicornException("Failed to write register", var3);
      }
   }

   public int regRead32(int var1) {
      this.regRead(var1, this.data4);
      return EndianUtil.littleEndianBytesToInt(this.data4);
   }

   public void regWrite32(int var1, int var2) {
      this.regWrite(var1, EndianUtil.intToLEBytes(var2));
   }

   public long regRead64(int var1) {
      this.regRead(var1, this.data8);
      return EndianUtil.littleEndianBytesToLong(this.data8);
   }

   public void regWrite64(int var1, long var2) {
      this.regWrite(var1, EndianUtil.longToLEBytes(var2));
   }

   public Pointer hookAdd(int var1, Callback var2) {
      return this.hookAdd(var1, var2, 0L, -1L);
   }

   public Pointer hookAdd(int var1, Callback var2, long var3, long var5) {
      PointerByReference var7 = new PointerByReference();
      int var8 = UnicornLibrary.INSTANCE.uc_hook_add(this.uc, var7, var1, var2, null, var3, var5);
      if (var8 != 0) {
         throw new UnicornException("Failed to add hook", var8);
      } else {
         return var7.getValue();
      }
   }

   public void hookDel(Pointer var1) {
      int var2 = UnicornLibrary.INSTANCE.uc_hook_del(this.uc, var1);
      if (var2 != 0) {
         throw new UnicornException("Failed to delete hook", var2);
      }
   }

   public void emuStart(long var1, long var3) {
      this.emuStart(var1, var3, 0L, 0L);
   }

   public void emuStart(long var1, long var3, long var5, long var7) {
      int var9 = UnicornLibrary.INSTANCE.uc_emu_start(this.uc, var1, var3, var5, var7);
      if (var9 != 0) {
         throw new UnicornException("Failed to start emulation", var9);
      }
   }

   public void emuStop() {
      int var1 = UnicornLibrary.INSTANCE.uc_emu_stop(this.uc);
      if (var1 != 0) {
         throw new UnicornException("Failed to stop emulation", var1);
      }
   }

   static {
      String var0 = new File(System.getenv("JEB_DEVHOME"), "jeb3-core/core/testlibs").getPath();
      System.setProperty("jna.library.path", var0);
   }
}
