package com.pnfsoftware.jebglobal;

import com.microsoft.z3.Status;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

// $VF: synthetic class
class cil {
   static {
      try {
         RF[JavaOperatorType.CAST_TO_BOOLEAN.ordinal()] = 1;
      } catch (NoSuchFieldError var13) {
      }

      try {
         RF[JavaOperatorType.CAST_TO_BYTE.ordinal()] = 2;
      } catch (NoSuchFieldError var12) {
      }

      try {
         RF[JavaOperatorType.CAST_TO_CHAR.ordinal()] = 3;
      } catch (NoSuchFieldError var11) {
      }

      try {
         RF[JavaOperatorType.CAST_TO_SHORT.ordinal()] = 4;
      } catch (NoSuchFieldError var10) {
      }

      try {
         RF[JavaOperatorType.CAST_TO_INT.ordinal()] = 5;
      } catch (NoSuchFieldError var9) {
      }

      try {
         RF[JavaOperatorType.CAST_TO_LONG.ordinal()] = 6;
      } catch (NoSuchFieldError var8) {
      }

      try {
         RF[JavaOperatorType.CAST_TO_FLOAT.ordinal()] = 7;
      } catch (NoSuchFieldError var7) {
      }

      try {
         RF[JavaOperatorType.CAST_TO_DOUBLE.ordinal()] = 8;
      } catch (NoSuchFieldError var6) {
      }

      try {
         RF[JavaOperatorType.CAST_CONVERSION.ordinal()] = 9;
      } catch (NoSuchFieldError var5) {
      }

      try {
         RF[JavaOperatorType.CAST_TO_OBJECT.ordinal()] = 10;
      } catch (NoSuchFieldError var4) {
      }

      try {
         RF[JavaOperatorType.CONCAT.ordinal()] = 11;
      } catch (NoSuchFieldError var3) {
      }

      try {
         RF[JavaOperatorType.INSTANCEOF.ordinal()] = 12;
      } catch (NoSuchFieldError var2) {
      }

      q = new int[Status.values().length];

      try {
         q[Status.SATISFIABLE.ordinal()] = 1;
      } catch (NoSuchFieldError var1) {
      }

      try {
         q[Status.UNSATISFIABLE.ordinal()] = 2;
      } catch (NoSuchFieldError var0) {
      }
   }
}
