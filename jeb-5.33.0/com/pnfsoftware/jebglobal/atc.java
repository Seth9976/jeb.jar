package com.pnfsoftware.jebglobal;

import com.microsoft.z3.Status;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

// $VF: synthetic class
class atc {
   static {
      try {
         A[OperationType.MUL2_S.ordinal()] = 1;
      } catch (NoSuchFieldError var31) {
      }

      try {
         A[OperationType.MUL2_U.ordinal()] = 2;
      } catch (NoSuchFieldError var30) {
      }

      try {
         A[OperationType.DIV2_S.ordinal()] = 3;
      } catch (NoSuchFieldError var29) {
      }

      try {
         A[OperationType.DIV2_U.ordinal()] = 4;
      } catch (NoSuchFieldError var28) {
      }

      try {
         A[OperationType.CAST.ordinal()] = 5;
      } catch (NoSuchFieldError var27) {
      }

      try {
         A[OperationType.CAST_S.ordinal()] = 6;
      } catch (NoSuchFieldError var26) {
      }

      try {
         A[OperationType.PAR.ordinal()] = 7;
      } catch (NoSuchFieldError var25) {
      }

      try {
         A[OperationType.CARRY.ordinal()] = 8;
      } catch (NoSuchFieldError var24) {
      }

      try {
         A[OperationType.POW.ordinal()] = 9;
      } catch (NoSuchFieldError var23) {
      }

      try {
         A[OperationType.FADD.ordinal()] = 10;
      } catch (NoSuchFieldError var22) {
      }

      try {
         A[OperationType.FSUB.ordinal()] = 11;
      } catch (NoSuchFieldError var21) {
      }

      try {
         A[OperationType.FMUL.ordinal()] = 12;
      } catch (NoSuchFieldError var20) {
      }

      try {
         A[OperationType.FDIV.ordinal()] = 13;
      } catch (NoSuchFieldError var19) {
      }

      try {
         A[OperationType.FEQ.ordinal()] = 14;
      } catch (NoSuchFieldError var18) {
      }

      try {
         A[OperationType.FNE.ordinal()] = 15;
      } catch (NoSuchFieldError var17) {
      }

      try {
         A[OperationType.FLT.ordinal()] = 16;
      } catch (NoSuchFieldError var16) {
      }

      try {
         A[OperationType.FGT.ordinal()] = 17;
      } catch (NoSuchFieldError var15) {
      }

      try {
         A[OperationType.FLE.ordinal()] = 18;
      } catch (NoSuchFieldError var14) {
      }

      try {
         A[OperationType.FGE.ordinal()] = 19;
      } catch (NoSuchFieldError var13) {
      }

      try {
         A[OperationType.FP2FP.ordinal()] = 20;
      } catch (NoSuchFieldError var12) {
      }

      try {
         A[OperationType.FP2INT.ordinal()] = 21;
      } catch (NoSuchFieldError var11) {
      }

      try {
         A[OperationType.INT2FP.ordinal()] = 22;
      } catch (NoSuchFieldError var10) {
      }

      try {
         A[OperationType.FP2UINT.ordinal()] = 23;
      } catch (NoSuchFieldError var9) {
      }

      try {
         A[OperationType.UINT2FP.ordinal()] = 24;
      } catch (NoSuchFieldError var8) {
      }

      try {
         A[OperationType.FUN.ordinal()] = 25;
      } catch (NoSuchFieldError var7) {
      }

      try {
         A[OperationType.ADD_SSAT.ordinal()] = 26;
      } catch (NoSuchFieldError var6) {
      }

      try {
         A[OperationType.ADD_USAT.ordinal()] = 27;
      } catch (NoSuchFieldError var5) {
      }

      try {
         A[OperationType.SUB_SSAT.ordinal()] = 28;
      } catch (NoSuchFieldError var4) {
      }

      try {
         A[OperationType.SUB_USAT.ordinal()] = 29;
      } catch (NoSuchFieldError var3) {
      }

      try {
         A[OperationType.FUNCTION.ordinal()] = 30;
      } catch (NoSuchFieldError var2) {
      }

      pC = new int[Status.values().length];

      try {
         pC[Status.SATISFIABLE.ordinal()] = 1;
      } catch (NoSuchFieldError var1) {
      }

      try {
         pC[Status.UNSATISFIABLE.ordinal()] = 2;
      } catch (NoSuchFieldError var0) {
      }
   }
}
