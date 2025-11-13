package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;

public class pr {
   static final int[] pC;
   static final int[] A = new int[AndroidPlatformABI.values().length];

   static {
      try {
         A[AndroidPlatformABI.X86.ordinal()] = 1;
      } catch (NoSuchFieldError var10) {
      }

      try {
         A[AndroidPlatformABI.X64.ordinal()] = 2;
      } catch (NoSuchFieldError var9) {
      }

      try {
         A[AndroidPlatformABI.MIPS.ordinal()] = 3;
      } catch (NoSuchFieldError var8) {
      }

      try {
         A[AndroidPlatformABI.MIPS64.ordinal()] = 4;
      } catch (NoSuchFieldError var7) {
      }

      try {
         A[AndroidPlatformABI.ARM.ordinal()] = 5;
      } catch (NoSuchFieldError var6) {
      }

      try {
         A[AndroidPlatformABI.ARM7.ordinal()] = 6;
      } catch (NoSuchFieldError var5) {
      }

      try {
         A[AndroidPlatformABI.ARM64.ordinal()] = 7;
      } catch (NoSuchFieldError var4) {
      }

      pC = new int[rH.values().length];

      try {
         pC[rH.A.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         pC[rH.pC.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         pC[rH.kS.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
