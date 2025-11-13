package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;

public class Yv {
   static final int[] q;
   static final int[] RF = new int[AndroidPlatformABI.values().length];

   static {
      try {
         RF[AndroidPlatformABI.X86.ordinal()] = 1;
      } catch (NoSuchFieldError var10) {
      }

      try {
         RF[AndroidPlatformABI.X64.ordinal()] = 2;
      } catch (NoSuchFieldError var9) {
      }

      try {
         RF[AndroidPlatformABI.MIPS.ordinal()] = 3;
      } catch (NoSuchFieldError var8) {
      }

      try {
         RF[AndroidPlatformABI.MIPS64.ordinal()] = 4;
      } catch (NoSuchFieldError var7) {
      }

      try {
         RF[AndroidPlatformABI.ARM.ordinal()] = 5;
      } catch (NoSuchFieldError var6) {
      }

      try {
         RF[AndroidPlatformABI.ARM7.ordinal()] = 6;
      } catch (NoSuchFieldError var5) {
      }

      try {
         RF[AndroidPlatformABI.ARM64.ordinal()] = 7;
      } catch (NoSuchFieldError var4) {
      }

      q = new int[af.values().length];

      try {
         q[af.RF.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         q[af.q.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         q[af.xK.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
