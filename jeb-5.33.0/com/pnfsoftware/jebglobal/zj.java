package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import java.util.HashMap;
import java.util.Map;

public class zj {
   public static final String[] pC = new String[]{"EQ", "NE", "CS", "CC", "MI", "PL", "VS", "VC", "HI", "LS", "GE", "LT", "GT", "LE", "AL", "NV"};
   private int A;
   private static Map kS;

   private zj(int var1) {
      this.A = var1;
   }

   Integer pC() {
      return this.A;
   }

   public Integer A() {
      return this.A & 65535;
   }

   private boolean ld() {
      return (this.A & 65536) != 0;
   }

   private boolean gp() {
      return (this.A & 33554432) != 0;
   }

   public boolean kS() {
      return !this.ld() && (this.A & 15728640) == 0 && (this.A & 251658240) != 16777216;
   }

   public boolean wS() {
      return (this.A & 262144) != 0;
   }

   @Deprecated
   public boolean UT() {
      return this.E();
   }

   public boolean E() {
      switch (this.A & 15728640) {
         case 0:
            return this.ld() || this.A() == 14 || this.A() == 15;
         default:
            return false;
      }
   }

   public boolean sY() {
      return !this.ld() && (this.A & 15728640) == 0 && this.A() != 14 && this.A() != 15;
   }

   public boolean pC(IMachineContext var1, Yg[] var2, int var3) {
      if (this.E()) {
         return true;
      } else {
         switch (this.A & 15728640) {
            case 0:
               int var10 = PM.kS(var1);
               return pC(this.A(), var10);
            case 1048576:
            case 2097152:
               long var9 = PM.pC(var1, var2[0].getOperandValue(), var3);
               boolean var11 = var9 == 0L;
               return (this.A & 15728640) == 1048576 ? var11 : !var11;
            case 3145728:
            case 4194304:
               long var4 = PM.pC(var1, var2[0].getOperandValue(), 64);
               long var6 = 1L << (int)var2[1].getOperandValue();
               boolean var8 = (var4 & var6) == 0L;
               return (this.A & 15728640) == 3145728 ? var8 : !var8;
            default:
               return false;
         }
      }
   }

   public static boolean pC(int var0, int var1) {
      boolean var2 = A(var1, var0 >>> 1);
      return (var0 & 1) == 0 ? var2 : !var2;
   }

   private static boolean A(int var0, int var1) {
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

   protected String ys() {
      return this.kS() && (this.gp() || this.A() < 14) ? (this.gp() ? "." : "") + pC[this.A()] : "";
   }

   public static zj pC(Integer var0) {
      if (var0 == null) {
         var0 = 65536;
      }

      if (kS == null) {
         kS = new HashMap();
      }

      zj var1 = (zj)kS.get(var0);
      if (var1 == null) {
         var1 = new zj(var0);
         kS.put(var0, var1);
      }

      return var1;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.A;
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
         zj var2 = (zj)var1;
         return this.A == var2.A;
      }
   }
}
