package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class bte implements Comparable {
   int q;
   List RF = new ArrayList();
   List xK = new ArrayList();
   List Dw = new ArrayList();
   List Uv = new ArrayList();
   Object oW;
   Set gO = new HashSet();
   List nf = new ArrayList();
   List gP = new ArrayList();
   List za = new ArrayList();
   List lm = new ArrayList();

   bte(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
      }
   }

   List q(boolean var1) {
      return !var1 ? this.q() : this.xK();
   }

   int RF(boolean var1) {
      return !var1 ? this.xK.size() : this.xK.size() + this.Uv.size();
   }

   List q() {
      return new ArrayList(this.xK);
   }

   List RF() {
      return new ArrayList(this.Uv);
   }

   List xK() {
      ArrayList var1 = new ArrayList(this.xK);
      var1.addAll(this.Uv);
      return var1;
   }

   List Dw() {
      return new ArrayList(this.RF);
   }

   List Uv() {
      return new ArrayList(this.Dw);
   }

   List oW() {
      ArrayList var1 = new ArrayList(this.RF);
      var1.addAll(this.Dw);
      return var1;
   }

   int gO() {
      return this.xK.size() + this.Uv.size();
   }

   int nf() {
      return this.RF.size() + this.Dw.size();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.q;
      var1 = 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
      var1 = 31 * var1 + (this.gP == null ? 0 : this.gP.hashCode());
      var1 = 31 * var1 + (this.za == null ? 0 : this.nf.hashCode());
      return 31 * var1 + (this.lm == null ? 0 : this.gP.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bte var2 = (bte)var1;
         if (this.q != var2.q) {
            return false;
         } else {
            if (this.nf == null) {
               if (var2.nf != null) {
                  return false;
               }
            } else if (!this.nf.equals(var2.nf)) {
               return false;
            }

            if (this.gP == null) {
               if (var2.gP != null) {
                  return false;
               }
            } else if (!this.gP.equals(var2.gP)) {
               return false;
            }

            if (this.za == null) {
               if (var2.za != null) {
                  return false;
               }
            } else if (!this.za.equals(var2.za)) {
               return false;
            }

            if (this.lm == null) {
               if (var2.lm != null) {
                  return false;
               }
            } else if (!this.lm.equals(var2.lm)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return Integer.toString(this.q);
   }

   public int q(bte var1) {
      return this.q - var1.q;
   }
}
