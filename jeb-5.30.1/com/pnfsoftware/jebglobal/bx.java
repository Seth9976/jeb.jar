package com.pnfsoftware.jebglobal;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public final class bx extends oV {
   private final int oW;
   private final List gO = new ArrayList();

   protected bx(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
      this.oW = var1.getInt();
   }

   @Override
   protected void q(ByteBuffer var1) {
      super.q(var1);
      this.gO.addAll(this.Uv(var1));
   }

   private List Uv(ByteBuffer var1) {
      ArrayList var2 = new ArrayList(this.oW);
      int var3 = this.Uv + this.Dw();

      for (int var4 = var3 + 260 * this.oW; var3 < var4; var3 += 260) {
         var2.add(bx.eo.q(var1, var3));
      }

      return var2;
   }

   @Override
   protected oV.eo RF() {
      return oV.eo.HF;
   }

   @Override
   protected void xK(ByteBuffer var1) {
      super.xK(var1);
      var1.putInt(this.gO.size());
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      for (bx.eo var5 : this.gO) {
         var1.write(var5.q(var3));
      }
   }

   @Xv
   protected abstract static class eo implements vv {
      private static final int q = 260;

      public abstract int q();

      public abstract String RF();

      static bx.eo q(ByteBuffer var0, int var1) {
         int var2 = var0.getInt(var1);
         String var3 = yj.q(var0, var1 + 4);
         return new bx.eo.eo(var2, var3);
      }

      @Override
      public byte[] oW() throws IOException {
         return this.q(false);
      }

      @Override
      public byte[] q(boolean var1) throws IOException {
         ByteBuffer var2 = ByteBuffer.allocate(260).order(ByteOrder.LITTLE_ENDIAN);
         var2.putInt(this.q());
         yj.q(var2, this.RF());
         return var2.array();
      }

      static class eo extends bx.eo {
         int q;
         String RF;

         public eo(int var1, String var2) {
            this.q = var1;
            this.RF = var2;
         }

         @Override
         public int q() {
            return this.q;
         }

         @Override
         public String RF() {
            return this.RF;
         }

         @Override
         public int hashCode() {
            int var1 = 1;
            var1 = 31 * var1 + this.q;
            return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
               bx.eo.eo var2 = (bx.eo.eo)var1;
               if (this.q != var2.q) {
                  return false;
               } else {
                  if (this.RF == null) {
                     if (var2.RF != null) {
                        return false;
                     }
                  } else if (!this.RF.equals(var2.RF)) {
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
