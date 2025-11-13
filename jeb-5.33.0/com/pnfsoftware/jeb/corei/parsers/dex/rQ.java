package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
@Ser
public class rQ {
   @SerId(1)
   vi pC;
   @SerId(2)
   Map A = new ConcurrentHashMap();
   @SerId(3)
   Map kS = new ConcurrentHashMap();

   public rQ(vi var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
      }
   }

   public Map pC() {
      return this.A;
   }

   public Map A() {
      return this.kS;
   }

   @Deprecated
   @Ser
   public static class Av {
      @SerId(1)
      String pC;
      @SerId(2)
      int A;

      public String pC() {
         return this.pC;
      }

      public int A() {
         return this.A;
      }

      @Override
      public int hashCode() {
         byte var1 = 1;
         return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
            rQ.Av var2 = (rQ.Av)var1;
            if (this.pC == null) {
               if (var2.pC != null) {
                  return false;
               }
            } else if (!this.pC.equals(var2.pC)) {
               return false;
            }

            return true;
         }
      }

      @Override
      public String toString() {
         return this.pC + (this.A == 0 ? "" : " (" + pC(this.A) + ")");
      }

      public static String pC(int var0) {
         return var0 + "";
      }
   }
}
