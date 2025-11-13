package com.pnfsoftware.jebglobal;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public final class aL extends Ij {
   private final int wS;
   private final List UT = new ArrayList();

   protected aL(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
      this.wS = var1.getInt();
   }

   @Override
   protected void pC(ByteBuffer var1) {
      super.pC(var1);
      this.UT.addAll(this.wS(var1));
   }

   private List wS(ByteBuffer var1) {
      ArrayList var2 = new ArrayList(this.wS);
      int var3 = this.kS + this.kS();

      for (int var4 = var3 + 260 * this.wS; var3 < var4; var3 += 260) {
         var2.add(aL.Av.pC(var1, var3));
      }

      return var2;
   }

   @Override
   protected Ij.Av A() {
      return Ij.Av.NS;
   }

   @Override
   protected void kS(ByteBuffer var1) {
      super.kS(var1);
      var1.putInt(this.UT.size());
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      for (aL.Av var5 : this.UT) {
         var1.write(var5.pC(var3));
      }
   }

   protected abstract static class Av {
      public abstract int pC();

      public abstract String A();

      static aL.Av pC(ByteBuffer var0, int var1) {
         int var2 = var0.getInt(var1);
         String var3 = cD.pC(var0, var1 + 4);
         return new aL.Av.Av(var2, var3);
      }

      public byte[] pC(boolean var1) throws IOException {
         ByteBuffer var2 = ByteBuffer.allocate(260).order(ByteOrder.LITTLE_ENDIAN);
         var2.putInt(this.pC());
         cD.pC(var2, this.A());
         return var2.array();
      }

      static class Av extends aL.Av {
         int pC;
         String A;

         public Av(int var1, String var2) {
            this.pC = var1;
            this.A = var2;
         }

         @Override
         public int pC() {
            return this.pC;
         }

         @Override
         public String A() {
            return this.A;
         }

         @Override
         public int hashCode() {
            int var1 = 1;
            var1 = 31 * var1 + this.pC;
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
               aL.Av.Av var2 = (aL.Av.Av)var1;
               if (this.pC != var2.pC) {
                  return false;
               } else {
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

         @Override
         public String toString() {
            return super.toString();
         }
      }
   }
}
