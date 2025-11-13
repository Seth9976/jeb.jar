package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bfn {
   private Map pC = new HashMap();

   public bfn(bev var1) {
      for (bfb var5 : var1.wS()) {
         int var6 = var5.getTryAddress();
         bfn.Av var7 = this.A(var6);
         var7.A.add(var5);
         int var8 = var5.A();
         var7 = this.A(var8);
         var7.kS.add(var5);

         for (bfa var10 : var5.getHandlers()) {
            var7 = this.A(var10.getAddress());
            var7.wS.add(var10);
         }
      }
   }

   private bfn.Av A(int var1) {
      bfn.Av var2 = (bfn.Av)this.pC.get(var1);
      if (var2 == null) {
         var2 = new bfn.Av(var1);
         this.pC.put(var1, var2);
      }

      return var2;
   }

   public bfn.Av pC(int var1) {
      return (bfn.Av)this.pC.get(var1);
   }

   public static class Av {
      int pC;
      List A = new ArrayList();
      List kS = new ArrayList();
      List wS = new ArrayList();

      Av(int var1) {
         this.pC = var1;
      }
   }
}
