package com.pnfsoftware.jebglobal;

import java.util.HashMap;
import java.util.Map;

public class QI {
   @Deprecated
   public static final YB q = new Eq();
   public static final YB RF = new FY(0);
   public static final YB xK = new FY(1);
   public static final YB Dw = new FY(0, 1);
   public static final YB Uv = new FY(0, 1, 2);
   public static final YB oW = new FY(1, 2);
   public static final YB gO = new FY(2, 3);
   public static final YB nf = new FY(-1);
   public static final YB gP = new cp(0);
   public static final YB za = new cp(1);
   public static final YB lm = new Xw(0, 1);
   public static final YB zz = new Xw(0, 2);
   public static final YB JY = new Dw();
   public static final YB HF = new DP();
   public static final YB LK = new xs();
   public static final YB io = new SM();
   public static final YB qa = new Rj();
   public static final YB Hk = new Db();
   public static final YB Me = new mB(0);
   public static final YB PV = new mB(1);
   public static final YB oQ = new w(21, 0);
   private static Map xW = new HashMap();
   private static Map KT = new HashMap();

   private static int xK(int var0, int var1) {
      return var0 | var1 << 8;
   }

   public static YB q(int var0, int var1) {
      int var2 = xK(var0, var1);
      Object var3 = (YB)xW.get(var2);
      if (var3 == null) {
         var3 = new Ba(var0, var1);
         xW.put(var2, var3);
      }

      return (YB)var3;
   }

   public static YB RF(int var0, int var1) {
      int var2 = xK(var0, var1);
      Object var3 = (YB)KT.get(var2);
      if (var3 == null) {
         var3 = new Gs(var0, var1);
         KT.put(var2, var3);
      }

      return (YB)var3;
   }
}
