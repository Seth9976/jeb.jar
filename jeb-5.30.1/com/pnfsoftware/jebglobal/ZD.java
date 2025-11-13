package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class ZD extends yS {
   private ZD(CW var1, DH var2) {
      super(var1, var2);
   }

   public static ZD q(CW var0, DH.eo var1) {
      return new ZD(var0, DH.q(var1));
   }

   public static ZD q(CW var0, DH.eo var1, int var2) {
      return new ZD(var0, DH.q(var1, var2));
   }

   public static ZD q(CW var0, DH.eo var1, CW var2) {
      return new ZD(var0, DH.q(var1, var2));
   }

   @Override
   public CW[] q() {
      CW[] var1 = this.xK().q();
      if (var1.length > 0) {
         CW[] var2 = new CW[var1.length + 1];
         var2[0] = this.RF();
         System.arraycopy(var1, 0, var2, 1, var1.length);
         return var2;
      } else {
         return new CW[]{this.RF()};
      }
   }

   @Override
   public Long q(long var1, int var3, IMachineContext var4) {
      CW var5 = this.RF();
      DH var6 = this.xK();
      CW var7 = var6.xK();
      if (!var5.RF(var3) && var4 == null) {
         return var5.isImmediate() && var7 != null && var7.isImmediate() && var6.RF() != DH.eo.Uv
            ? q(var5.getOperandValue(), var6.RF(), var7.getOperandValue(), null, var3 != 64)
            : super.q(var1, var3, var4);
      } else {
         long var8 = var4 == null ? var1 + (var3 == 16 ? 4 : 8) : rT.q(var4, var5.getOperandValue(), var3);
         long var10 = 0L;
         if (var7 != null) {
            if (var7.getOperandType() == 0) {
               if (!var7.RF(var3) && var4 == null) {
                  return super.q(var1, var3, var4);
               }

               var10 = var4 == null ? var1 : rT.q(var4, var7.getOperandValue(), var3);
            } else {
               var10 = (int)var7.getOperandValue(var1);
            }
         }

         return q(var8, var6.RF(), var10, var4, RegisterUtil.getRegisterBitsize(var5.getOperandValue()) != 64);
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static Long q(long var0, DH.eo var2, long var3, IMachineContext var5, boolean var6) {
      switch (var2) {
         case q:
            return var0 << (int)var3;
         case RF:
            return var0 >>> (int)var3;
         case xK:
            return var6 ? (int)var0 >> (int)var3 : var0 >> (int)var3;
         case Dw:
            return var6 ? Integer.rotateRight((int)var0, (int)var3) : Long.rotateRight(var0, (int)var3);
         case Uv:
            if (!rT.q(var5)) {
               return null;
            }

            boolean var7 = rT.RF(var5);
            return var0 >>> 1 | (var7 ? Integer.MIN_VALUE : 0);
         case oW:
         default:
            return var0 << (int)var3 | (1 << (int)var3) - 1;
      }
   }

   public CW RF() {
      return this.oW()[0];
   }

   public DH xK() {
      return (DH)this.oW()[1];
   }
}
