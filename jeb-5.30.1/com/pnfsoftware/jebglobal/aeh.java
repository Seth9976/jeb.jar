package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import java.lang.reflect.Field;

public class aeh {
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
   public static boolean qa;
   public static boolean Hk;
   public static boolean Me;
   public static boolean PV;

   public static void q(boolean var0) {
      RF();
      if (var0) {
         gP = true;
         lm = true;
         JY = true;
      }
   }

   public static void q() {
      RF(true);
   }

   static void RF() {
      for (Field var3 : aeh.class.getDeclaredFields()) {
         try {
            var3.set(null, false);
         } catch (Exception var4) {
         }
      }
   }

   static void RF(boolean var0) {
      q = true;
      RF = false;
      xK = var0;
      Dw = true;
      Uv = true;
      oW = false;
      gO = false;
      nf = false;
      gP = true;
      za = true;
      lm = true;
      zz = true;
      JY = true;
      HF = true;
      LK = true;
      io = true;
      qa = true;
      Hk = true;
      Me = true;
      PV = true;
   }

   static void q(int var0) {
      if (var0 == 0) {
         GlobalLog.setCutoffLevel(Integer.MAX_VALUE);
      } else if (var0 == -1) {
         GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.corei.parsers.asm.decompiler", Integer.MAX_VALUE);
         GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.core.units.code.asm.decompiler", Integer.MAX_VALUE);
      } else if (var0 == 1) {
         GlobalLog.removeGlobalFilter("com.pnfsoftware.jeb.corei.parsers.asm.decompiler");
         GlobalLog.removeGlobalFilter("com.pnfsoftware.jeb.core.units.code.asm.decompiler");
      }
   }

   public static void q(CFG var0, String var1) {
   }

   public static void q(CFG var0, String var1, String var2) {
   }

   public static void xK() {
   }
}
