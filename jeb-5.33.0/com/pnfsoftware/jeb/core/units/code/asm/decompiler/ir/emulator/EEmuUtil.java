package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.corei.parsers.arm.converter.HE;
import com.pnfsoftware.jeb.util.base.Assert;

public class EEmuUtil {
   public static long setupGenericThreadLocalStorageInfo(EEmulator var0, long var1, boolean var3) {
      IVirtualMemory var4 = var0.getVirtualMemory();
      EState var5 = var0.getState();
      IEGlobalContext var6 = var0.getGlobalContext();
      ProcessorType var7 = var6.getNativeContext().getProcessor().getType();
      long var8 = var1;
      if (var3) {
         int var10 = var4.getPageSize();
         var8 = VirtualMemoryUtil.allocate(var4, var1, var10, 7);
      }

      if (var7.isIntel()) {
         IEVar var15 = var6.getVariableByName("fs");
         var5.setValue(var15, 512L);
         var5.setSegmentBase(512, var8);
         IEVar var13 = var6.getVariableByName("gs");
         var5.setValue(var13, 528L);
         var5.setSegmentBase(528, var8);
      } else if (var7 == ProcessorType.ARM64) {
         IEVar var16 = var6.getVariableByName("X18");
         Assert.a(var16 != null);
         var5.setValue(var16, var8);
         createArm64Var(var5, "TPIDR_EL0", var8);
         createArm64Var(var5, "DCZID_EL0", 4L);
         createArm64Var(var5, "CTR_EL0", 2219098116L);
         createArm64Var(var5, "ID_AA64PFR0_EL1", 17L);
         createArm64Var(var5, "ID_AA64PFR1_EL1", 0L);
         createArm64Var(var5, "ID_AA64ISAR0_EL1", 4368L);
         createArm64Var(var5, "ID_AA64ISAR1_EL1", 0L);
         createArm64Var(var5, "ID_AA64ISAR2_EL1", 0L);
         long var11 = 19200000L;
         createArm64Var(var5, "CNTFRQ_EL0", var11);
         createArm64Var(var5, "CNTVCT_EL0", var11 * 3600L * 2L);
         if (var3) {
            try {
               var4.writeLong(var8 + 0L, var8 + 4369L);
               var4.writeLong(var8 + 8L, var8 + 8738L);
               var4.writeLong(var8 + 16L, 0L);
               var4.writeLong(var8 + 24L, 0L);
               var4.writeLong(var8 + 32L, var8 + 13107L);
               var4.writeLong(var8 + 40L, -6148895925951734307L);
               var4.writeLong(var8 + 48L, var8 + 17476L);
               var4.writeLong(var8 + 56L, var8 + 21845L);
            } catch (MemoryException var14) {
            }
         }
      }

      return var8;
   }

   private static IEVar createArm64Var(EState var0, String var1, long var2) {
      IEGlobalContext var4 = var0.getGlobalContext();
      IEVar var5 = var4.getVariableByName(var1);
      if (var5 == null) {
         IRegisterBank var6 = RegisterUtil.getBank(ProcessorType.ARM64);
         RegisterDescriptionEntry var7 = var6.getDescriptionEntryByName(var1);
         if (var7 == null) {
            throw new RuntimeException("Failed to create register EVar: " + var1);
         }

         var5 = (IEVar)((HE)var4.getConverter()).pC(var7.getId(), false);
      }

      var0.setValue(var5, var2);
      return var5;
   }
}
