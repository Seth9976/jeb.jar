package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class uX {
   private static final ILogger pC = GlobalLog.getLogger(uX.class);

   public static int pC(String var0) {
      Assert.a(var0.startsWith("sm_"), "Expecting a sm_NNN string");
      return Integer.parseInt(var0.substring("sm_".length()));
   }

   public static String A(String var0) {
      return pC(pC(var0));
   }

   public static String pC(int var0) {
      if (var0 < 10) {
         return "Unknown Architecture: sm_" + var0;
      } else if (var0 < 20) {
         return "Tesla";
      } else if (var0 < 30) {
         return "Fermi";
      } else if (var0 < 40) {
         return "Kepler";
      } else if (var0 < 50) {
         return "Unknown Architecture: sm_" + var0;
      } else if (var0 < 60) {
         return "Maxwell";
      } else if (var0 < 70) {
         return "Pascal";
      } else if (var0 <= 72) {
         return "Volta";
      } else if (var0 < 80) {
         return "Turing";
      } else if (var0 <= 87) {
         return "Ampere";
      } else if (var0 < 90) {
         return "Ada";
      } else {
         return var0 < 130 ? "Blackwell" : "Unknown Architecture: sm_" + var0;
      }
   }
}
