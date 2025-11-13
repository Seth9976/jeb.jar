package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;

// $VF: synthetic class
class AbstractConverter$1 {
   static {
      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.SET_IF_EQ.ordinal()] = 1;
      } catch (NoSuchFieldError var27) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.SET_IF_NE.ordinal()] = 2;
      } catch (NoSuchFieldError var26) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.SET_IF_LT_S.ordinal()] = 3;
      } catch (NoSuchFieldError var25) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.SET_IF_LT_U.ordinal()] = 4;
      } catch (NoSuchFieldError var24) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.SET_IF_LE_S.ordinal()] = 5;
      } catch (NoSuchFieldError var23) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.SET_IF_LE_U.ordinal()] = 6;
      } catch (NoSuchFieldError var22) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.SET_IF_GT_S.ordinal()] = 7;
      } catch (NoSuchFieldError var21) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.SET_IF_GT_U.ordinal()] = 8;
      } catch (NoSuchFieldError var20) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.SET_IF_GE_S.ordinal()] = 9;
      } catch (NoSuchFieldError var19) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.SET_IF_GE_U.ordinal()] = 10;
      } catch (NoSuchFieldError var18) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.JUMP_IF_EQ.ordinal()] = 11;
      } catch (NoSuchFieldError var17) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.JUMP_IF_NE.ordinal()] = 12;
      } catch (NoSuchFieldError var16) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.JUMP_IF_LT_S.ordinal()] = 13;
      } catch (NoSuchFieldError var15) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.JUMP_IF_LT_U.ordinal()] = 14;
      } catch (NoSuchFieldError var14) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.JUMP_IF_LE_S.ordinal()] = 15;
      } catch (NoSuchFieldError var13) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.JUMP_IF_LE_U.ordinal()] = 16;
      } catch (NoSuchFieldError var12) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.JUMP_IF_GT_S.ordinal()] = 17;
      } catch (NoSuchFieldError var11) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.JUMP_IF_GT_U.ordinal()] = 18;
      } catch (NoSuchFieldError var10) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.JUMP_IF_GE_S.ordinal()] = 19;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.JUMP_IF_GE_U.ordinal()] = 20;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.NOP.ordinal()] = 21;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.MOVE.ordinal()] = 22;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.STORE.ordinal()] = 23;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.LOAD.ordinal()] = 24;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$decompiler$ACS$OPS[ACS.OPS.JUMP.ordinal()] = 25;
      } catch (NoSuchFieldError var3) {
      }

      $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$type$StorageEntry$Type = new int[StorageEntry.Type.values().length];

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$type$StorageEntry$Type[StorageEntry.Type.REGISTER.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$type$StorageEntry$Type[StorageEntry.Type.STACK.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }

      try {
         $SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$type$StorageEntry$Type[StorageEntry.Type.REGISTER_PAIR.ordinal()] = 3;
      } catch (NoSuchFieldError var0) {
      }
   }
}
