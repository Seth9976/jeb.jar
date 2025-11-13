package com.pnfsoftware.jebglobal;

public class PZ {
   public int pC;
   public String A;
   public String kS;
   public long wS;

   public PZ(int var1, String var2, String var3, long var4) {
      if (var2 != null && var3 != null) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public PZ(int var1, String var2, String var3) {
      this(var1, var2, var3, -1L);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.pC;
      var1 = 31 * var1 + (int)(this.wS ^ this.wS >>> 32);
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         PZ var2 = (PZ)var1;
         if (this.pC != var2.pC) {
            return false;
         } else if (this.wS != var2.wS) {
            return false;
         } else {
            if (this.kS == null) {
               if (var2.kS != null) {
                  return false;
               }
            } else if (!this.kS.equals(var2.kS)) {
               return false;
            }

            if (this.A == null) {
               if (var2.A != null) {
                  return false;
               }
            } else if (!this.A.equals(var2.A)) {
               return false;
            }

            return true;
         }
      }
   }

   public static String pC(int var0) {
      switch (var0) {
         case 0:
            return "type";
         case 1:
            return "field";
         case 2:
            return "method";
         case 3:
            return "bytecode";
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(pC(this.pC)).append('=').append(this.A).append('.').append(this.kS);
      if (this.pC == 3) {
         var1.append(':').append(this.wS);
      }

      return var1.toString();
   }
}
