package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

// $VF: synthetic class
class cba {
   static {
      try {
         RF[DOpcodeType.IR_ASSIGN.ordinal()] = 1;
      } catch (NoSuchFieldError var12) {
      }

      try {
         RF[DOpcodeType.IR_JCOND.ordinal()] = 2;
      } catch (NoSuchFieldError var11) {
      }

      q = new int[JavaOperatorType.values().length];

      try {
         q[JavaOperatorType.ADD.ordinal()] = 1;
      } catch (NoSuchFieldError var10) {
      }

      try {
         q[JavaOperatorType.SUB.ordinal()] = 2;
      } catch (NoSuchFieldError var9) {
      }

      try {
         q[JavaOperatorType.AND.ordinal()] = 3;
      } catch (NoSuchFieldError var8) {
      }

      try {
         q[JavaOperatorType.OR.ordinal()] = 4;
      } catch (NoSuchFieldError var7) {
      }

      try {
         q[JavaOperatorType.XOR.ordinal()] = 5;
      } catch (NoSuchFieldError var6) {
      }

      try {
         q[JavaOperatorType.NOT.ordinal()] = 6;
      } catch (NoSuchFieldError var5) {
      }

      try {
         q[JavaOperatorType.SHL.ordinal()] = 7;
      } catch (NoSuchFieldError var4) {
      }

      try {
         q[JavaOperatorType.NEG.ordinal()] = 8;
      } catch (NoSuchFieldError var3) {
      }

      try {
         q[JavaOperatorType.REM.ordinal()] = 9;
      } catch (NoSuchFieldError var2) {
      }

      try {
         q[JavaOperatorType.EQ.ordinal()] = 10;
      } catch (NoSuchFieldError var1) {
      }

      try {
         q[JavaOperatorType.NE.ordinal()] = 11;
      } catch (NoSuchFieldError var0) {
      }
   }
}
