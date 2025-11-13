package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bji {
   private Map q = new HashMap();

   public bji(bip var1) {
      for (biw var5 : var1.Uv()) {
         int var6 = var5.getTryAddress();
         bji.eo var7 = this.RF(var6);
         var7.RF.add(var5);
         int var8 = var5.RF();
         var7 = this.RF(var8);
         var7.xK.add(var5);

         for (biv var10 : var5.getHandlers()) {
            var7 = this.RF(var10.getAddress());
            var7.Dw.add(var10);
         }
      }
   }

   private bji.eo RF(int var1) {
      bji.eo var2 = (bji.eo)this.q.get(var1);
      if (var2 == null) {
         var2 = new bji.eo(var1);
         this.q.put(var1, var2);
      }

      return var2;
   }

   public bji.eo q(int var1) {
      return (bji.eo)this.q.get(var1);
   }

   public static class eo {
      int q;
      List RF = new ArrayList();
      List xK = new ArrayList();
      List Dw = new ArrayList();

      eo(int var1) {
         this.q = var1;
      }
   }
}
