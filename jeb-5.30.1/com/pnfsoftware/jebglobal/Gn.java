package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;

@Xv
public abstract class Gn implements vv {
   public static final int q = 8;

   public abstract int q();

   public abstract Gn.CU RF();

   public abstract int xK();

   public static Gn q(ByteBuffer var0) {
      int var1 = var0.getShort() & '\uffff';
      var0.get();
      Gn.CU var2 = Gn.CU.q(var0.get());
      int var3 = var0.getInt();
      return new Gn.eo(var1, var2, var3);
   }

   @Override
   public byte[] oW() {
      return this.q(false);
   }

   @Override
   public byte[] q(boolean var1) {
      ByteBuffer var2 = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
      var2.putShort((short)this.q());
      var2.put((byte)0);
      var2.put(this.RF().q());
      var2.putInt(this.xK());
      return var2.array();
   }

   static f Dw() {
      return new du();
   }

   public static enum CU {
      q(0),
      RF(1),
      xK(2),
      Dw(3),
      Uv(4),
      oW(5),
      gO(6),
      nf(7),
      gP(16),
      za(17),
      lm(18),
      zz(28),
      JY(29),
      HF(30),
      LK(31);

      private final byte io;
      private static final Map qa;

      private CU(int var3) {
         this.io = UnsignedBytes.checkedCast(var3);
      }

      public byte q() {
         return this.io;
      }

      public static Gn.CU q(byte var0) {
         return (Gn.CU)Preconditions.checkNotNull((Gn.CU)qa.get(var0));
      }

      static {
         Builder var0 = ImmutableMap.builder();

         for (Gn.CU var4 : values()) {
            var0.put(var4.q(), var4);
         }

         qa = var0.build();
      }
   }

   static class eo extends Gn {
      int RF;
      Gn.CU xK;
      int Dw;

      public eo(int var1, Gn.CU var2, int var3) {
         this.RF = var1;
         this.xK = var2;
         this.Dw = var3;
      }

      @Override
      public int q() {
         return this.RF;
      }

      @Override
      public Gn.CU RF() {
         return this.xK;
      }

      @Override
      public int xK() {
         return this.Dw;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.Dw;
         var1 = 31 * var1 + this.RF;
         return 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
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
            Gn.eo var2 = (Gn.eo)var1;
            if (this.Dw != var2.Dw) {
               return false;
            } else {
               return this.RF != var2.RF ? false : this.xK == var2.xK;
            }
         }
      }
   }
}
