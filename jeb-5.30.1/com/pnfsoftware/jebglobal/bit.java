package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Ser
public class bit {
   @SerId(1)
   private biw[] q;
   @SerId(2)
   private biw[] RF;
   @SerId(3)
   private biw[] xK;

   public bit(biw[] var1, boolean var2) {
      this.q = var1;
      this.RF = var1;
      this.xK = var1;
      if (this.Uv()) {
         this.q(true);
      }
   }

   private boolean Uv() {
      HashMap var1 = new HashMap();

      for (biw var5 : this.q) {
         for (biv var7 : var5.getHandlers()) {
            var1.put(var7.getAddress(), var7);
         }
      }

      HashMap var13 = new HashMap();

      for (biw var24 : this.q) {
         for (int var8 : var24.nf()) {
            Object var9 = (List)var13.get(var8);
            if (var9 == null) {
               var9 = new ArrayList();
               var13.put(var8, var9);
            }

            boolean var10 = false;

            for (bit.eo var12 : var9) {
               if (var12.RF == var24.getTryAddress()) {
                  var12.RF = var24.RF();
                  var10 = true;
                  break;
               }
            }

            if (!var10) {
               var9.add(new bit.eo(var24.getTryAddress(), var24.RF(), (biv)var1.get(var8)));
            }
         }
      }

      ArrayList var15 = new ArrayList();

      for (List var21 : var13.values()) {
         var15.addAll(var21);
      }

      Collections.sort(var15, new biu(this));
      int var18 = 0;

      while (var18 < var15.size() - 1) {
         bit.eo var22 = (bit.eo)var15.get(var18);
         bit.eo var25 = (bit.eo)var15.get(var18 + 1);
         if (var22.q == var25.q && var22.RF == var25.RF) {
            var22.q(var25.xK);
            var15.remove(var18 + 1);
         } else {
            if (var25.q < var22.RF && var25.RF > var22.RF) {
               return false;
            }

            var18++;
         }
      }

      this.xK = new biw[var15.size()];

      for (int var19 = 0; var19 < var15.size(); var19++) {
         bit.eo var23 = (bit.eo)var15.get(var19);
         this.xK[var19] = new biw(var23.q, var23.RF - var23.q, var23.xK);
      }

      return true;
   }

   public void q(boolean var1) {
      this.q = var1 ? this.xK : this.RF;
   }

   public boolean q() {
      return this.xK != this.RF;
   }

   public int RF() {
      return this.q.length;
   }

   public biw q(int var1) {
      return this.q[var1];
   }

   public List xK() {
      HashSet var1 = new HashSet();

      for (biw var5 : this.q) {
         var1.add(var5.getTryAddress());
         var1.add(var5.RF());
      }

      return new ArrayList(var1);
   }

   public HashSet Dw() {
      HashSet var1 = new HashSet();

      for (biw var5 : this.q) {
         var1.addAll(var5.nf());
      }

      return var1;
   }

   public biv RF(int var1) {
      for (biw var5 : this.q) {
         for (biv var7 : var5.getHandlers()) {
            if (var7.getAddress() == var1) {
               return var7;
            }
         }
      }

      return null;
   }

   public static class eo {
      private int q;
      private int RF;
      private List xK = new ArrayList();

      public eo(int var1, int var2, biv var3) {
         this.q = var1;
         this.RF = var2;
         this.xK.add(var3);
      }

      public eo(int var1, int var2, List var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      public void q(List var1) {
         this.xK.addAll(var1);
      }
   }
}
