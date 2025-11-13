package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class DH extends yS {
   private static CW RF(DH.eo var0) {
      return new sJ(var0.name().replace("_", " "));
   }

   static DH q(DH.eo var0) {
      CW var1 = RF(var0);
      return new DH(0, var1);
   }

   static DH q(DH.eo var0, int var1) {
      CW var2 = RF(var0);
      return var0 != DH.eo.Uv && var0 != DH.eo.LK ? new DH(var1, var2, CW.q((long)var1, 589824)) : new DH(var1, var2);
   }

   static DH q(DH.eo var0, CW var1) {
      CW var2 = RF(var0);
      return new DH(0, var2, var1);
   }

   private DH(int var1, CW... var2) {
      super(var1, 256, var2);
   }

   public DH.eo RF() {
      CW var1 = this.oW()[0];
      DH.eo var2;
      if (var1 instanceof sJ) {
         String var3 = var1.getAlias(0L).replace(" ", "_");
         var2 = DH.eo.valueOf(var3);
      } else {
         var2 = DH.eo.values()[(int)var1.getOperandValue()];
      }

      if (var2 == null) {
         throw new IllegalStateException("Can not retrieve shift type");
      } else {
         return var2;
      }
   }

   public CW xK() {
      CW[] var1 = this.oW();
      return var1.length < 2 ? null : var1[1];
   }

   @Override
   public CW[] q() {
      CW var1 = this.xK();
      return var1 != null && var1.isRegister() ? new CW[]{var1} : new CW[0];
   }

   @Ser
   public static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO,
      nf,
      gP,
      za,
      lm,
      zz,
      JY,
      HF,
      LK,
      io;

      // $VF: Unable to simplify switch on enum
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      public boolean q() {
         switch (this) {
            case RF:
            case q:
            case xK:
            case Dw:
            case Uv:
               return true;
            default:
               return false;
         }
      }

      // $VF: Unable to simplify switch on enum
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      public boolean RF() {
         switch (this) {
            case gO:
            case nf:
            case gP:
            case za:
            case lm:
            case zz:
            case JY:
            case HF:
               return true;
            default:
               return false;
         }
      }
   }
}
