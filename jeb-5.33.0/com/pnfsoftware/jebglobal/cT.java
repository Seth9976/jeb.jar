package com.pnfsoftware.jebglobal;

import java.util.HashMap;
import java.util.Map;

public class cT {
   @Deprecated
   public static final jp pC = new ue();
   public static final jp A = new xJ(0);
   public static final jp kS = new xJ(1);
   public static final jp wS = new xJ(0, 1);
   public static final jp UT = new xJ(0, 1, 2);
   public static final jp E = new xJ(1, 2);
   public static final jp sY = new xJ(2, 3);
   public static final jp ys = new xJ(-1);
   public static final jp ld = new aJ(0);
   public static final jp gp = new aJ(1);
   public static final jp oT = new kI(0, 1);
   public static final jp fI = new kI(0, 2);
   public static final jp WR = new Oz();
   public static final jp NS = new ET();
   public static final jp vP = new Fs();
   public static final jp xC = new ju();
   public static final jp ED = new Mk();
   public static final jp Sc = new ed();
   public static final jp ah = new Tp(0);
   public static final jp eP = new Tp(1);
   public static final jp UO = new JC(21, 0);
   private static Map Ab = new HashMap();
   private static Map rl = new HashMap();

   private static int kS(int var0, int var1) {
      return var0 | var1 << 8;
   }

   public static jp pC(int var0, int var1) {
      int var2 = kS(var0, var1);
      Object var3 = (jp)Ab.get(var2);
      if (var3 == null) {
         var3 = new Aa(var0, var1);
         Ab.put(var2, var3);
      }

      return (jp)var3;
   }

   public static jp A(int var0, int var1) {
      int var2 = kS(var0, var1);
      Object var3 = (jp)rl.get(var2);
      if (var3 == null) {
         var3 = new lU(var0, var1);
         rl.put(var2, var3);
      }

      return (jp)var3;
   }
}
