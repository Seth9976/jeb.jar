package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

// $VF: synthetic class
class OperationType$1 {
   static {
      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.MUL_U.ordinal()] = 1;
      } catch (NoSuchFieldError var40) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.MUL2_S.ordinal()] = 2;
      } catch (NoSuchFieldError var39) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.MUL2_U.ordinal()] = 3;
      } catch (NoSuchFieldError var38) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.DIV2_S.ordinal()] = 4;
      } catch (NoSuchFieldError var37) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.DIV2_U.ordinal()] = 5;
      } catch (NoSuchFieldError var36) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.CARRY.ordinal()] = 6;
      } catch (NoSuchFieldError var35) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.LOG_AND.ordinal()] = 7;
      } catch (NoSuchFieldError var34) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.LOG_OR.ordinal()] = 8;
      } catch (NoSuchFieldError var33) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.LOG_NOT.ordinal()] = 9;
      } catch (NoSuchFieldError var32) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.LOG_EQ.ordinal()] = 10;
      } catch (NoSuchFieldError var31) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.LOG_NEQ.ordinal()] = 11;
      } catch (NoSuchFieldError var30) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.LT_S.ordinal()] = 12;
      } catch (NoSuchFieldError var29) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.LE_S.ordinal()] = 13;
      } catch (NoSuchFieldError var28) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.GT_S.ordinal()] = 14;
      } catch (NoSuchFieldError var27) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.GE_S.ordinal()] = 15;
      } catch (NoSuchFieldError var26) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.LT_U.ordinal()] = 16;
      } catch (NoSuchFieldError var25) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.LE_U.ordinal()] = 17;
      } catch (NoSuchFieldError var24) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.GT_U.ordinal()] = 18;
      } catch (NoSuchFieldError var23) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.GE_U.ordinal()] = 19;
      } catch (NoSuchFieldError var22) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FEQ.ordinal()] = 20;
      } catch (NoSuchFieldError var21) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FNE.ordinal()] = 21;
      } catch (NoSuchFieldError var20) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FLT.ordinal()] = 22;
      } catch (NoSuchFieldError var19) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FGT.ordinal()] = 23;
      } catch (NoSuchFieldError var18) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FLE.ordinal()] = 24;
      } catch (NoSuchFieldError var17) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FGE.ordinal()] = 25;
      } catch (NoSuchFieldError var16) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.SHL.ordinal()] = 26;
      } catch (NoSuchFieldError var15) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.SHR.ordinal()] = 27;
      } catch (NoSuchFieldError var14) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.SAR.ordinal()] = 28;
      } catch (NoSuchFieldError var13) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.ROL.ordinal()] = 29;
      } catch (NoSuchFieldError var12) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.ROR.ordinal()] = 30;
      } catch (NoSuchFieldError var11) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.CAST.ordinal()] = 31;
      } catch (NoSuchFieldError var10) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.CAST_S.ordinal()] = 32;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FP2FP.ordinal()] = 33;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FP2INT.ordinal()] = 34;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.INT2FP.ordinal()] = 35;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FP2UINT.ordinal()] = 36;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.UINT2FP.ordinal()] = 37;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FADD.ordinal()] = 38;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FSUB.ordinal()] = 39;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FMUL.ordinal()] = 40;
      } catch (NoSuchFieldError var1) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.FDIV.ordinal()] = 41;
      } catch (NoSuchFieldError var0) {
      }
   }
}
