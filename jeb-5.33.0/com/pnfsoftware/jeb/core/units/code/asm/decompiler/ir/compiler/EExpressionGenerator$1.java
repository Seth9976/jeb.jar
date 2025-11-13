package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

// $VF: synthetic class
class EExpressionGenerator$1 {
   static {
      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE.ordinal()] = 1;
      } catch (NoSuchFieldError var37) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE_FIRSTBIT.ordinal()] = 2;
      } catch (NoSuchFieldError var36) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE_LASTBIT.ordinal()] = 3;
      } catch (NoSuchFieldError var35) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE_FIRST32.ordinal()] = 4;
      } catch (NoSuchFieldError var34) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE_HALF1.ordinal()] = 5;
      } catch (NoSuchFieldError var33) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE_HALF2.ordinal()] = 6;
      } catch (NoSuchFieldError var32) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.COMPOSE_2.ordinal()] = 7;
      } catch (NoSuchFieldError var31) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.COMPOSE_2EQ.ordinal()] = 8;
      } catch (NoSuchFieldError var30) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.COND.ordinal()] = 9;
      } catch (NoSuchFieldError var29) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN.ordinal()] = 10;
      } catch (NoSuchFieldError var28) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN8.ordinal()] = 11;
      } catch (NoSuchFieldError var27) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN16.ordinal()] = 12;
      } catch (NoSuchFieldError var26) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN32.ordinal()] = 13;
      } catch (NoSuchFieldError var25) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN64.ordinal()] = 14;
      } catch (NoSuchFieldError var24) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN128.ordinal()] = 15;
      } catch (NoSuchFieldError var23) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT.ordinal()] = 16;
      } catch (NoSuchFieldError var22) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT8.ordinal()] = 17;
      } catch (NoSuchFieldError var21) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT16.ordinal()] = 18;
      } catch (NoSuchFieldError var20) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT32.ordinal()] = 19;
      } catch (NoSuchFieldError var19) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT64.ordinal()] = 20;
      } catch (NoSuchFieldError var18) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT128.ordinal()] = 21;
      } catch (NoSuchFieldError var17) {
      }

      $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType = new int[OperationType.values().length];

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.ADD.ordinal()] = 1;
      } catch (NoSuchFieldError var16) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.SUB.ordinal()] = 2;
      } catch (NoSuchFieldError var15) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.MUL.ordinal()] = 3;
      } catch (NoSuchFieldError var14) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.DIV_S.ordinal()] = 4;
      } catch (NoSuchFieldError var13) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.DIV_U.ordinal()] = 5;
      } catch (NoSuchFieldError var12) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.REM_S.ordinal()] = 6;
      } catch (NoSuchFieldError var11) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.REM_U.ordinal()] = 7;
      } catch (NoSuchFieldError var10) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.AND.ordinal()] = 8;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.OR.ordinal()] = 9;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.XOR.ordinal()] = 10;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.ROL.ordinal()] = 11;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.ROR.ordinal()] = 12;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.SAR.ordinal()] = 13;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.SHL.ordinal()] = 14;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.SHR.ordinal()] = 15;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.NOT.ordinal()] = 16;
      } catch (NoSuchFieldError var1) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.POW.ordinal()] = 17;
      } catch (NoSuchFieldError var0) {
      }
   }
}
