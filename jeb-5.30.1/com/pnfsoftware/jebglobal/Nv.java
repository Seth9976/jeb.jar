package com.pnfsoftware.jebglobal;

public class Nv {
   public static final int q = 0;
   public static final int RF = 1;
   public static final int xK = 2;
   public static final int Dw = 3;
   public int Uv;
   public String oW;
   public String gO;
   public long nf;

   public Nv(int var1, String var2, String var3, long var4) {
      if (var2 != null && var3 != null) {
         this.Uv = var1;
         this.oW = var2;
         this.gO = var3;
         this.nf = var4;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public Nv(int var1, String var2, String var3) {
      this(var1, var2, var3, -1L);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.Uv;
      var1 = 31 * var1 + (int)(this.nf ^ this.nf >>> 32);
      var1 = 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
      return 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
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
         Nv var2 = (Nv)var1;
         if (this.Uv != var2.Uv) {
            return false;
         } else if (this.nf != var2.nf) {
            return false;
         } else {
            if (this.gO == null) {
               if (var2.gO != null) {
                  return false;
               }
            } else if (!this.gO.equals(var2.gO)) {
               return false;
            }

            if (this.oW == null) {
               if (var2.oW != null) {
                  return false;
               }
            } else if (!this.oW.equals(var2.oW)) {
               return false;
            }

            return true;
         }
      }
   }

   public static String q(int var0) {
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
      var1.append(q(this.Uv)).append('=').append(this.oW).append('.').append(this.gO);
      if (this.Uv == 3) {
         var1.append(':').append(this.nf);
      }

      return var1.toString();
   }
}
