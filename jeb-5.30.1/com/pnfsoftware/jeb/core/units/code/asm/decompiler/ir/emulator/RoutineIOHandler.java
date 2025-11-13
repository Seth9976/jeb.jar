package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStorageEntryGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLayoutInfo;

public class RoutineIOHandler {
   private EEmulator emu;
   private IEGlobalContext g;
   private boolean is64bit;
   private ICallingConvention cc;
   private IStorageEntryGenerator ingen;

   public RoutineIOHandler(EEmulator var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.emu = var1;
         this.g = var1.getGlobalContext();
         this.is64bit = var1.getGlobalContext().getConverter().getRegisterBitsize() == 64;
      }
   }

   public EEmulator getEmulator() {
      return this.emu;
   }

   public void reset(ICallingConvention var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.cc = var1;
         this.ingen = var1.getInputsGenerator();
      }
   }

   public long nextAsPtr() {
      StorageEntry var1 = this.ingen.next(TypeLayoutInfo.ptr);
      return this.emu.readStorage(var1).getValueAsAddress();
   }

   public int nextAsObj() {
      long var1 = this.nextAsPtr();
      if (var1 >= 0L && var1 <= 2147483647L) {
         return (int)var1;
      } else {
         throw new RuntimeException();
      }
   }

   public void skipJniEnvPtr() {
      if (!this.ingen.getCurrentEntries().isEmpty()) {
         throw new IllegalStateException();
      } else {
         this.nextAsPtr();
      }
   }

   public int nextAsInt() {
      StorageEntry var1 = this.ingen.next(TypeLayoutInfo.i1);
      IEImm var2 = this.emu.readStorage(var1).truncate(32);
      return (int)var2.getValueAsLong();
   }

   public long nextAsLong() {
      StorageEntry var1 = this.ingen.next(this.is64bit ? TypeLayoutInfo.i1 : TypeLayoutInfo.i2);
      IEImm var2 = this.emu.readStorage(var1).truncate(64);
      return var2.getValueAsLong();
   }

   public long nextAsSizet() {
      return this.nextAsLong();
   }

   public long nextAsOfft() {
      return this.nextAsLong();
   }

   public float nextAsFloat() {
      StorageEntry var1 = this.ingen.next(TypeLayoutInfo.f1);
      IEImm var2 = this.emu.readStorage(var1);
      if (var2.getBitsize() == 32) {
         return var2.getValueAsSingleFloat();
      } else if (var2.getBitsize() == 64) {
         return var2.truncate(32).getValueAsSingleFloat();
      } else if (var2.getBitsize() > 64) {
         return var2.truncate(32).getValueAsSingleFloat();
      } else {
         throw new RuntimeException();
      }
   }

   public double nextAsDouble() {
      StorageEntry var1 = this.ingen.next(this.is64bit ? TypeLayoutInfo.f1 : TypeLayoutInfo.f2);
      IEImm var2 = this.emu.readStorage(var1);
      if (var2.getBitsize() == 64) {
         return var2.getValueAsDoubleFloat();
      } else if (var2.getBitsize() > 64) {
         return var2.truncate(64).getValueAsDoubleFloat();
      } else {
         throw new RuntimeException();
      }
   }

   public void retInt(int var1) {
      IEImm var2 = this.g.createImm(var1, this.g.getRegisterBitsize());
      StorageEntry var3 = this.cc.getOutput(TypeLayoutInfo.i1, 0);
      this.emu.writeStorage(var3, var2);
   }

   public void retLong(long var1) {
      IEImm var3 = this.g.createImm(var1, this.g.getRegisterBitsize());
      StorageEntry var4 = this.cc.getOutput(this.is64bit ? TypeLayoutInfo.i1 : TypeLayoutInfo.i2, 0);
      this.emu.writeStorage(var4, var3);
   }

   public void retSizet(long var1) {
      this.retLong(var1);
   }

   public void retPtr(long var1) {
      IEImm var3 = this.g.createImm(var1, this.g.getAddressBitsize());
      StorageEntry var4 = this.cc.getOutput(TypeLayoutInfo.ptr, 0);
      this.emu.writeStorage(var4, var3);
   }

   public void retObj(int var1) {
      this.retInt(var1);
   }

   public void retFloat(float var1) {
      IEImm var2 = this.g.createImm(var1);
      StorageEntry var3 = this.cc.getOutput(TypeLayoutInfo.f1, 0);
      this.emu.writeStorage(var3, var2);
   }

   public void retDouble(double var1) {
      IEImm var3 = this.g.createImm(var1);
      StorageEntry var4 = this.cc.getOutput(this.is64bit ? TypeLayoutInfo.f1 : TypeLayoutInfo.f2, 0);
      this.emu.writeStorage(var4, var3);
   }
}
