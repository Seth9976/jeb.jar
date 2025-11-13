package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

// $VF: synthetic class
class bwl {
   static {
      try {
         A[DOpcodeType.IR_ASSIGN.ordinal()] = 1;
      } catch (NoSuchFieldError var12) {
      }

      try {
         A[DOpcodeType.IR_JCOND.ordinal()] = 2;
      } catch (NoSuchFieldError var11) {
      }

      pC = new int[JavaOperatorType.values().length];

      try {
         pC[JavaOperatorType.ADD.ordinal()] = 1;
      } catch (NoSuchFieldError var10) {
      }

      try {
         pC[JavaOperatorType.SUB.ordinal()] = 2;
      } catch (NoSuchFieldError var9) {
      }

      try {
         pC[JavaOperatorType.AND.ordinal()] = 3;
      } catch (NoSuchFieldError var8) {
      }

      try {
         pC[JavaOperatorType.OR.ordinal()] = 4;
      } catch (NoSuchFieldError var7) {
      }

      try {
         pC[JavaOperatorType.XOR.ordinal()] = 5;
      } catch (NoSuchFieldError var6) {
      }

      try {
         pC[JavaOperatorType.NOT.ordinal()] = 6;
      } catch (NoSuchFieldError var5) {
      }

      try {
         pC[JavaOperatorType.SHL.ordinal()] = 7;
      } catch (NoSuchFieldError var4) {
      }

      try {
         pC[JavaOperatorType.NEG.ordinal()] = 8;
      } catch (NoSuchFieldError var3) {
      }

      try {
         pC[JavaOperatorType.REM.ordinal()] = 9;
      } catch (NoSuchFieldError var2) {
      }

      try {
         pC[JavaOperatorType.EQ.ordinal()] = 10;
      } catch (NoSuchFieldError var1) {
      }

      try {
         pC[JavaOperatorType.NE.ordinal()] = 11;
      } catch (NoSuchFieldError var0) {
      }
   }
}
