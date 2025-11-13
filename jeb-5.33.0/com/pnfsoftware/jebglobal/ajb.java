package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ajb implements Comparable {
   int pC;
   List A = new ArrayList();
   List kS = new ArrayList();
   List wS = new ArrayList();
   List UT = new ArrayList();
   Object E;
   Set sY = new HashSet();
   List ys = new ArrayList();
   List ld = new ArrayList();
   List gp = new ArrayList();
   List oT = new ArrayList();

   ajb(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
      }
   }

   List pC() {
      return new ArrayList(this.kS);
   }

   List A() {
      ArrayList var1 = new ArrayList(this.kS);
      var1.addAll(this.UT);
      return var1;
   }

   List kS() {
      ArrayList var1 = new ArrayList(this.A);
      var1.addAll(this.wS);
      return var1;
   }

   int wS() {
      return this.kS.size() + this.UT.size();
   }

   int UT() {
      return this.A.size() + this.wS.size();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.pC;
      var1 = 31 * var1 + (this.ys == null ? 0 : this.ys.hashCode());
      var1 = 31 * var1 + (this.ld == null ? 0 : this.ld.hashCode());
      var1 = 31 * var1 + (this.gp == null ? 0 : this.ys.hashCode());
      return 31 * var1 + (this.oT == null ? 0 : this.ld.hashCode());
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
         ajb var2 = (ajb)var1;
         if (this.pC != var2.pC) {
            return false;
         } else {
            if (this.ys == null) {
               if (var2.ys != null) {
                  return false;
               }
            } else if (!this.ys.equals(var2.ys)) {
               return false;
            }

            if (this.ld == null) {
               if (var2.ld != null) {
                  return false;
               }
            } else if (!this.ld.equals(var2.ld)) {
               return false;
            }

            if (this.gp == null) {
               if (var2.gp != null) {
                  return false;
               }
            } else if (!this.gp.equals(var2.gp)) {
               return false;
            }

            if (this.oT == null) {
               if (var2.oT != null) {
                  return false;
               }
            } else if (!this.oT.equals(var2.oT)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return Integer.toString(this.pC);
   }

   public int pC(ajb var1) {
      return this.pC - var1.pC;
   }
}
