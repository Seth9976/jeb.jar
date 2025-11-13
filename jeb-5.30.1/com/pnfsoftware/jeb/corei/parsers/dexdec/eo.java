package com.pnfsoftware.jeb.corei.parsers.dexdec;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import java.lang.reflect.Field;

public class eo {
   public static boolean q;
   public static boolean RF;
   public static boolean xK;
   public static boolean Dw;
   public static boolean Uv;
   public static boolean oW;
   public static boolean gO;
   public static boolean nf;
   public static boolean gP;
   public static boolean za;
   public static boolean lm;
   public static boolean zz;
   public static boolean JY;
   public static boolean HF;
   public static boolean LK;
   public static boolean io;

   public static void q(boolean var0) {
      RF();
      if (var0) {
         Uv = true;
         gO = true;
         gP = true;
      }
   }

   public static void q() {
      xK();
   }

   static void RF() {
      for (Field var3 : eo.class.getDeclaredFields()) {
         try {
            var3.set(null, false);
         } catch (Exception var4) {
         }
      }
   }

   public static void xK() {
      q = true;
      RF = false;
      xK = true;
      Dw = true;
      Uv = true;
      oW = true;
      gO = true;
      nf = true;
      gP = true;
      za = true;
      lm = true;
      zz = true;
      JY = true;
      HF = true;
      LK = true;
      io = true;
   }

   static void q(int var0) {
      if (var0 == 0) {
         GlobalLog.setCutoffLevel(Integer.MAX_VALUE);
      } else if (var0 == -1) {
         GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.corei.parsers.dexdec", Integer.MAX_VALUE);
      } else if (var0 == 1) {
         GlobalLog.removeGlobalFilter("com.pnfsoftware.jeb.corei.parsers.dexdec");
      }
   }

   public static void Dw() {
   }
}
