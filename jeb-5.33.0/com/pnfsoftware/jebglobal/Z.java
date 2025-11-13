package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class Z extends FH {
   private static Yg A(Z.Av var0) {
      return new fj(var0.name().replace("_", " "));
   }

   static Z pC(Z.Av var0) {
      Yg var1 = A(var0);
      return new Z(0, var1);
   }

   static Z pC(Z.Av var0, int var1) {
      Yg var2 = A(var0);
      return var0 != Z.Av.UT && var0 != Z.Av.vP ? new Z(var1, var2, Yg.pC((long)var1, 589824)) : new Z(var1, var2);
   }

   static Z pC(Z.Av var0, Yg var1) {
      Yg var2 = A(var0);
      return new Z(0, var2, var1);
   }

   private Z(int var1, Yg... var2) {
      super(var1, 256, var2);
   }

   public Z.Av A() {
      Yg var1 = this.E()[0];
      Z.Av var2;
      if (var1 instanceof fj) {
         String var3 = var1.getAlias(0L).replace(" ", "_");
         var2 = Z.Av.valueOf(var3);
      } else {
         var2 = Z.Av.values()[(int)var1.getOperandValue()];
      }

      if (var2 == null) {
         throw new IllegalStateException("Can not retrieve shift type");
      } else {
         return var2;
      }
   }

   public Yg kS() {
      Yg[] var1 = this.E();
      return var1.length < 2 ? null : var1[1];
   }

   @Override
   public Yg[] pC() {
      Yg var1 = this.kS();
      return var1 != null && var1.isRegister() ? new Yg[]{var1} : new Yg[0];
   }

   @Ser
   public static enum Av {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY,
      ys,
      ld,
      gp,
      oT,
      fI,
      WR,
      NS,
      vP,
      xC;

      public boolean pC() {
         switch (this) {
            case A:
            case pC:
            case kS:
            case wS:
            case UT:
               return true;
            default:
               return false;
         }
      }

      public boolean A() {
         switch (this) {
            case sY:
            case ys:
            case ld:
            case gp:
            case oT:
            case fI:
            case WR:
            case NS:
               return true;
            default:
               return false;
         }
      }
   }
}
