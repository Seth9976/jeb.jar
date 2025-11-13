package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

// $VF: synthetic class
class gb {
   static {
      try {
         pC[OperationType.FP2INT.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         pC[OperationType.FP2UINT.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         pC[OperationType.INT2FP.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }

      try {
         pC[OperationType.UINT2FP.ordinal()] = 4;
      } catch (NoSuchFieldError var0) {
      }
   }
}
