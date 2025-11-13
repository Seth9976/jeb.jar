package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bfc {
   private List pC;
   private List A;

   public bfc(List var1) {
      int var2 = 0;

      for (bfb var4 : var1) {
         if (var4.getTryAddress() < var2) {
            throw new IllegalArgumentException();
         }

         var2 = var4.A();
      }

      this.pC = var1;
   }

   public List pC() {
      return Collections.unmodifiableList(this.pC);
   }

   public List A() {
      if (this.A == null) {
         this.A = this.kS();
      }

      return this.A;
   }

   public int pC(int var1) {
      Integer var2 = null;

      for (bfb var4 : this.pC) {
         for (bfa var6 : var4.kS) {
            if (var6.getAddress() == var1) {
               int var7 = var6.A();
               if (var2 == null) {
                  var2 = var7;
               } else if (var7 != var2) {
                  return -1;
               }
            }
         }
      }

      if (var2 == null) {
         throw new IllegalArgumentException(Strings.ff("No handler found at address: %Xh", var1));
      } else {
         return var2;
      }
   }

   private List kS() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();

      for (bfb var4 : this.pC) {
         var2.add(var4.pC());
      }

      ArrayList var16 = new ArrayList();
      var16.add(var2);

      while (!var16.isEmpty()) {
         List var15 = (List)var16.remove(0);
         ArrayList var17 = new ArrayList();
         var1.add(var17);

         while (!var15.isEmpty()) {
            bfb var5 = (bfb)var15.get(0);
            int var6 = var5.getTryAddress();
            int var7 = var5.A();
            List var8 = Lists.reverse(var5.getHandlers());

            int var9;
            for (var9 = 1; var9 < var15.size(); var9++) {
               bfb var10 = (bfb)var15.get(var9);
               if (var10.getTryAddress() != var7) {
                  break;
               }

               int var11 = Math.min(var8.size(), var10.wS());
               int var12 = 0;

               for (bfa var14 : var8) {
                  if (var12 >= var11 || !var14.equals(var10.kS(var10.wS() - 1 - var12))) {
                     break;
                  }

                  var12++;
               }

               if (var12 == 0) {
                  break;
               }

               var8 = var8.subList(0, var12);
               var7 = var10.A();
            }

            int var18 = var9;
            if (var9 == 1) {
               var17.add(var5);
               var15.remove(0);
            } else {
               Lists.reverse(var8);
               bfb var19 = new bfb(var6, var7 - var6, var8);
               var17.add(var19);
               ArrayList var20 = new ArrayList();

               while (var18-- > 0) {
                  bfb var21 = (bfb)var15.remove(0);
                  if (!this.pC(var21, var8.size())) {
                     var20.add(var21);
                  }
               }

               if (!var20.isEmpty()) {
                  var16.add(var20);
               }
            }
         }
      }

      return var1;
   }

   private boolean pC(bfb var1, int var2) {
      int var3 = var1.kS.size() - var2;

      while (var2-- > 0) {
         var1.kS.remove(var3);
      }

      return var1.kS.isEmpty();
   }

   @Override
   public String toString() {
      return this.pC.toString();
   }
}
