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
import com.pnfsoftware.jebglobal.uq;

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
         var5.setValue(var16, var8);
         var16 = var6.getVariableByName("TPIDR_EL0");
         if (var16 == null) {
            IRegisterBank var11 = RegisterUtil.getBank(ProcessorType.ARM64);
            RegisterDescriptionEntry var12 = var11.getDescriptionEntryByName("TPIDR_EL0");
            if (var12 == null) {
               throw new RuntimeException("Cannot create TPIDR_EL0 register");
            }

            var16 = (IEVar)((uq)var6.getConverter()).q(var12.getId(), false);
         }

         var5.setValue(var16, var8);
         var16 = var6.getVariableByName("DCZID_EL0");
         if (var16 == null) {
            IRegisterBank var20 = RegisterUtil.getBank(ProcessorType.ARM64);
            RegisterDescriptionEntry var22 = var20.getDescriptionEntryByName("DCZID_EL0");
            if (var22 == null) {
               throw new RuntimeException("Cannot create DCZID_EL0 register");
            }

            var16 = (IEVar)((uq)var6.getConverter()).q(var22.getId(), false);
         }

         var5.setValue(var16, 16L);
         var16 = var6.getVariableByName("CTR_EL0");
         if (var16 == null) {
            IRegisterBank var21 = RegisterUtil.getBank(ProcessorType.ARM64);
            RegisterDescriptionEntry var23 = var21.getDescriptionEntryByName("CTR_EL0");
            if (var23 == null) {
               throw new RuntimeException("Cannot create CTR_EL0 register");
            }

            var16 = (IEVar)((uq)var6.getConverter()).q(var23.getId(), false);
         }

         var5.setValue(var16, 0L);
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
}
