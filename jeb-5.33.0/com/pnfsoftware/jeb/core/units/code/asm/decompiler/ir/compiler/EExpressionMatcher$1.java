package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

// $VF: synthetic class
class EExpressionMatcher$1 {
   static {
      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.ADD.ordinal()] = 1;
      } catch (NoSuchFieldError var40) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$OperationType[OperationType.MUL.ordinal()] = 2;
      } catch (NoSuchFieldError var39) {
      }

      $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O = new int[O.values().length];

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.ADD.ordinal()] = 1;
      } catch (NoSuchFieldError var38) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.MUL.ordinal()] = 2;
      } catch (NoSuchFieldError var37) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.AND.ordinal()] = 3;
      } catch (NoSuchFieldError var36) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.OR.ordinal()] = 4;
      } catch (NoSuchFieldError var35) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.XOR.ordinal()] = 5;
      } catch (NoSuchFieldError var34) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.GE_S.ordinal()] = 6;
      } catch (NoSuchFieldError var33) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.GT_S.ordinal()] = 7;
      } catch (NoSuchFieldError var32) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.LE_S.ordinal()] = 8;
      } catch (NoSuchFieldError var31) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.LT_S.ordinal()] = 9;
      } catch (NoSuchFieldError var30) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.GE_U.ordinal()] = 10;
      } catch (NoSuchFieldError var29) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.GT_U.ordinal()] = 11;
      } catch (NoSuchFieldError var28) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.LE_U.ordinal()] = 12;
      } catch (NoSuchFieldError var27) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.LT_U.ordinal()] = 13;
      } catch (NoSuchFieldError var26) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE.ordinal()] = 14;
      } catch (NoSuchFieldError var25) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE_FIRSTBIT.ordinal()] = 15;
      } catch (NoSuchFieldError var24) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE_LASTBIT.ordinal()] = 16;
      } catch (NoSuchFieldError var23) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE_HALFBIT.ordinal()] = 17;
      } catch (NoSuchFieldError var22) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE_FIRST32.ordinal()] = 18;
      } catch (NoSuchFieldError var21) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE_HALF1.ordinal()] = 19;
      } catch (NoSuchFieldError var20) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.SLICE_HALF2.ordinal()] = 20;
      } catch (NoSuchFieldError var19) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.COMPOSE_2.ordinal()] = 21;
      } catch (NoSuchFieldError var18) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.COMPOSE_2EQ.ordinal()] = 22;
      } catch (NoSuchFieldError var17) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.COND.ordinal()] = 23;
      } catch (NoSuchFieldError var16) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN.ordinal()] = 24;
      } catch (NoSuchFieldError var15) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN8.ordinal()] = 25;
      } catch (NoSuchFieldError var14) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN16.ordinal()] = 26;
      } catch (NoSuchFieldError var13) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN32.ordinal()] = 27;
      } catch (NoSuchFieldError var12) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN64.ordinal()] = 28;
      } catch (NoSuchFieldError var11) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.TRN128.ordinal()] = 29;
      } catch (NoSuchFieldError var10) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT.ordinal()] = 30;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT8.ordinal()] = 31;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT16.ordinal()] = 32;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT32.ordinal()] = 33;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT64.ordinal()] = 34;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.EXT128.ordinal()] = 35;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.DIV.ordinal()] = 36;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.REM.ordinal()] = 37;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.NCADD.ordinal()] = 38;
      } catch (NoSuchFieldError var1) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ir$compiler$O[O.NCSUB.ordinal()] = 39;
      } catch (NoSuchFieldError var0) {
      }
   }
}
