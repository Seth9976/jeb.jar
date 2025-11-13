package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;

@Xv
public abstract class rV {
   private static final int q = -16777216;
   private static final int RF = 24;
   private static final int xK = 16711680;
   private static final int Dw = 16;
   private static final int Uv = 65535;
   private static final int oW = 0;

   public abstract int q();

   public abstract int RF();

   public abstract int xK();

   public static rV q(int var0) {
      int var1 = (var0 & 0xFF000000) >>> 24;
      int var2 = (var0 & 0xFF0000) >>> 16;
      int var3 = (var0 & 65535) >>> 0;
      return q(var1, var2, var3);
   }

   public static rV q(int var0, int var1, int var2) {
      Preconditions.checkState((var0 & 0xFF) == var0);
      Preconditions.checkState((var1 & 0xFF) == var1);
      Preconditions.checkState((var2 & 65535) == var2);
      return new rV.eo(var0, var1, var2);
   }

   static class eo extends rV {
      int q;
      int RF;
      int xK;

      public eo(int var1, int var2, int var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      @Override
      public int q() {
         return this.q;
      }

      @Override
      public int RF() {
         return this.RF;
      }

      @Override
      public int xK() {
         return this.xK;
      }
   }
}
