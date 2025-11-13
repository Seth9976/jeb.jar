package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class wh extends yS {
   @SerId(1)
   private boolean gP;
   @SerId(2)
   private boolean za;
   @SerId(3)
   private int lm;

   public wh(CW var1, CW var2, boolean var3, boolean var4, int var5) {
      super(var1.getOperandValue(), q(var4, RF(var3, var4)) | 512 | 256, var1, var2);
      this.gP = var3;
      this.za = var4;
      this.lm = var5;
   }

   protected static int q(boolean var0, boolean var1) {
      return (var0 ? 1 : 0) | (var1 ? 131072 : 0);
   }

   @Override
   public CW[] q() {
      CW var1 = this.gO();
      if (var1 != null) {
         if (var1.getOperandType() == 0) {
            return new CW[]{this.RF(), var1};
         }

         if (var1.getOperandType() == 7) {
            CW[] var2 = ((yS)var1).q();
            if (var2.length > 0) {
               CW[] var3 = new CW[var2.length + 1];
               var3[0] = this.RF();
               System.arraycopy(var2, 0, var3, 1, var2.length);
               return var3;
            }
         }
      }

      return new CW[]{this.RF()};
   }

   private static boolean RF(boolean var0, boolean var1) {
      return !var1 && var0 ? false : var0;
   }

   public CW RF() {
      return this.oW()[0];
   }

   public CW xK() {
      return this.oW()[0] instanceof JK ? ((JK)this.RF()).q()[0] : this.oW()[0];
   }

   public CW gO() {
      return this.oW().length > 1 ? this.oW()[1] : null;
   }

   @Override
   public boolean Uv() {
      return !this.za || this.gP;
   }

   public boolean nf() {
      return this.za;
   }

   public boolean gP() {
      CW[] var1 = this.oW();
      return var1 != null && var1.length > 0 && var1[0].RF(this.lm) && this.Uv();
   }

   public CW q(long var1) {
      CW[] var3 = this.oW();
      if (this.za && !this.gP) {
         if (var3.length == 1) {
            if (var3[0] instanceof CW) {
               CW var4 = var3[0];
               if (var4.RF(this.lm)) {
                  return new fp(0L, this.lm, true, false);
               }
            }
         } else if (var3.length == 2 && var3[0] instanceof CW && var3[1] instanceof CW) {
            CW var7 = var3[0];
            CW var5 = var3[1];
            if (var7.RF(this.lm)) {
               Long var6 = var5.q(var1, var7.getOperandBitsize(), null);
               if (var6 != null) {
                  return new fp(var6, this.lm, true, false);
               }
            }
         }
      }

      return null;
   }

   @Override
   public Long q(long var1, int var3, IMachineContext var4) {
      CW[] var5 = this.oW();
      Long var6 = var5[0].q(var1, var3, var4);
      if (this.za) {
         Long var7 = var5[1].q(var1, var3, var4);
         return var7 != null && var6 != null ? var6 + var7 : var6;
      } else {
         return var6;
      }
   }

   @Override
   public long getOperandValue(long var1) {
      CW var3 = this.q(var1);
      return var3 == null ? super.getOperandValue(var1) : var3.getOperandValue(var1);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.gP ? 1231 : 1237);
      var1 = 31 * var1 + this.lm;
      return 31 * var1 + (this.za ? 1231 : 1237);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         wh var2 = (wh)var1;
         if (this.gP != var2.gP) {
            return false;
         } else {
            return this.lm != var2.lm ? false : this.za == var2.za;
         }
      }
   }
}
