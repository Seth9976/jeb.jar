package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaUtil;
import com.pnfsoftware.jeb.util.io.EndianUtil;

public class PM {
   public static int pC(IMachineContext var0, Integer var1) throws ProcessorException {
      byte[] var2 = pC(var0, var1, 4);
      return EndianUtil.bytesToInt(var2, 0, var0.getInformation().getEndianness().toByteOrder());
   }

   public static byte A(IMachineContext var0, Integer var1) throws ProcessorException {
      try {
         return var0.getMemory().readByte(var1.intValue());
      } catch (MemoryException var3) {
         throw new ProcessorException(var3);
      }
   }

   public static short kS(IMachineContext var0, Integer var1) throws ProcessorException {
      byte[] var2 = pC(var0, var1, 2);
      return EndianUtil.bytesToShort(var2, 0, var0.getInformation().getEndianness().toByteOrder());
   }

   public static byte[] pC(IMachineContext var0, Integer var1, int var2) throws ProcessorException {
      byte[] var3 = new byte[var2];

      try {
         var0.getMemory().read(var1.intValue() & 4294967295L, var2, var3, 0);
         return var3;
      } catch (MemoryException var5) {
         throw new ProcessorException(var5);
      }
   }

   public static Long pC(IMachineContext var0, long var1, int var3) {
      return A(var0, var1, var3 == 64 ? 64 : 32);
   }

   private static Long A(IMachineContext var0, long var1, int var3) {
      if (var0 != null && var0.getRegisters() != null) {
         byte var5 = 4;
         int var6 = RegisterUtil.getRegisterGroup(var1);
         int var7 = RegisterUtil.getRegisterBitsize(var1);
         int var4;
         switch (var6) {
            case 0:
               var4 = RegisterUtil.getRegisterIndex(var1);
               if (var7 == 64) {
                  var5 = 8;
               }
               break;
            default:
               var4 = RegisterUtil.getRegisterIndex(var1);
         }

         byte[] var8 = var0.getRegisters().getValue(var4);
         if (var0.getInformation().getEndianness().isLittle()) {
            EndianUtil.swapByGroup(var8, var3 == 64 ? 8 : 4);
         }

         if (var8.length > var5) {
            byte[] var9 = new byte[var5];
            System.arraycopy(var8, var8.length - var5, var9, 0, var5);
            var8 = var9;
         }

         return EncodedMemoryAreaUtil.zeroExtend(var8, 0, var3);
      } else {
         return null;
      }
   }

   public static boolean pC(IMachineContext var0) {
      if (var0 != null && var0.getRegisters() != null) {
         byte[] var1 = var0.getRegisters().getValue(16);
         return var1 != null && var1.length >= 4;
      } else {
         return false;
      }
   }

   public static boolean A(IMachineContext var0) {
      if (!pC(var0)) {
         throw new IllegalArgumentException("Context does not contain CPSR register");
      } else {
         byte[] var1 = var0.getRegisters().getValue(16);
         if (var0.getInformation().getEndianness().isLittle()) {
            EndianUtil.swapByGroup(var1, 4);
         }

         return (var1[0] & 32) != 0;
      }
   }

   public static Integer kS(IMachineContext var0) {
      if (var0 != null && var0.getRegisters() != null) {
         byte[] var1 = var0.getRegisters().getValue(16);
         if (var0.getInformation().getEndianness().isLittle()) {
            EndianUtil.swapByGroup(var1, 4);
         }

         return var1[0] >>> 4;
      } else {
         return null;
      }
   }

   public static boolean wS(IMachineContext var0) {
      if (!pC(var0)) {
         throw new IllegalArgumentException("Context does not contain CPSR register");
      } else {
         byte[] var1 = var0.getRegisters().getValue(16);
         if (var0.getInformation().getEndianness().isLittle()) {
            EndianUtil.swapByGroup(var1, 4);
         }

         return (var1[3] & 32) != 0;
      }
   }

   public static boolean UT(IMachineContext var0) {
      if (!pC(var0)) {
         throw new IllegalArgumentException("Context does not contain CPSR register");
      } else {
         byte[] var1 = var0.getRegisters().getValue(16);
         if (var0.getInformation().getEndianness().isLittle()) {
            EndianUtil.swapByGroup(var1, 4);
         }

         return (var1[0] & 1) != 0;
      }
   }
}
