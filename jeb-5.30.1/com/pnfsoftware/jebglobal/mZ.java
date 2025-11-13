package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import java.util.HashMap;
import java.util.Map;

public class mZ {
   public static final String[] q = new String[]{"EQ", "NE", "CS", "CC", "MI", "PL", "VS", "VC", "HI", "LS", "GE", "LT", "GT", "LE", "AL", "NV"};
   public static final int RF = 65535;
   public static final int xK = 65536;
   public static final int Dw = 262144;
   public static final int Uv = 15728640;
   public static final int oW = 0;
   public static final int gO = 1048576;
   public static final int nf = 2097152;
   public static final int gP = 3145728;
   public static final int za = 4194304;
   public static final int lm = 251658240;
   public static final int zz = 16777216;
   public static final int JY = 33554432;
   private int HF;
   private static Map LK;

   private mZ(int var1) {
      this.HF = var1;
   }

   Integer q() {
      return this.HF;
   }

   public Integer RF() {
      return this.HF & 65535;
   }

   private boolean gP() {
      return (this.HF & 65536) != 0;
   }

   private boolean za() {
      return (this.HF & 33554432) != 0;
   }

   public boolean xK() {
      return !this.gP() && (this.HF & 15728640) == 0 && (this.HF & 251658240) != 16777216;
   }

   public boolean Dw() {
      return (this.HF & 262144) != 0;
   }

   @Deprecated
   public boolean Uv() {
      return this.oW();
   }

   public boolean oW() {
      switch (this.HF & 15728640) {
         case 0:
            return this.gP() || this.RF() == 14 || this.RF() == 15;
         default:
            return false;
      }
   }

   public boolean gO() {
      return !this.gP() && (this.HF & 15728640) == 0 && this.RF() != 14 && this.RF() != 15;
   }

   public boolean q(IMachineContext var1, CW[] var2, int var3) {
      if (this.oW()) {
         return true;
      } else {
         switch (this.HF & 15728640) {
            case 0:
               int var10 = rT.xK(var1);
               return q(this.RF(), var10);
            case 1048576:
            case 2097152:
               long var9 = rT.q(var1, var2[0].getOperandValue(), var3);
               boolean var11 = var9 == 0L;
               return (this.HF & 15728640) == 1048576 ? var11 : !var11;
            case 3145728:
            case 4194304:
               long var4 = rT.q(var1, var2[0].getOperandValue(), 64);
               long var6 = 1L << (int)var2[1].getOperandValue();
               boolean var8 = (var4 & var6) == 0L;
               return (this.HF & 15728640) == 3145728 ? var8 : !var8;
            default:
               return false;
         }
      }
   }

   public static boolean q(int var0, int var1) {
      boolean var2 = RF(var1, var0 >>> 1);
      return (var0 & 1) == 0 ? var2 : !var2;
   }

   private static boolean RF(int var0, int var1) {
      switch (var1) {
         case 0:
            return (var0 & 4) == 4;
         case 1:
            return (var0 & 2) == 2;
         case 2:
            return (var0 & 8) == 8;
         case 3:
            return (var0 & 1) == 1;
         case 4:
            return (var0 & 6) == 2;
         case 5:
            return (var0 & 9) == 9 || (var0 & 9) == 0;
         case 6:
            return (var0 & 13) == 9 || (var0 & 13) == 0;
         default:
            return true;
      }
   }

   protected String nf() {
      return this.xK() && (this.za() || this.RF() < 14) ? (this.za() ? "." : "") + q[this.RF()] : "";
   }

   public static mZ q(Integer var0) {
      if (var0 == null) {
         var0 = 65536;
      }

      if (LK == null) {
         LK = new HashMap();
      }

      mZ var1 = (mZ)LK.get(var0);
      if (var1 == null) {
         var1 = new mZ(var0);
         LK.put(var0, var1);
      }

      return var1;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.HF;
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
         mZ var2 = (mZ)var1;
         return this.HF == var2.HF;
      }
   }
}
