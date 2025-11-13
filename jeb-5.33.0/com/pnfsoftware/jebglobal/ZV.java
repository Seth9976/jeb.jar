package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class ZV extends FH {
   private ZV(Yg var1, Z var2) {
      super(var1, var2);
   }

   public static ZV pC(Yg var0, Z.Av var1) {
      return new ZV(var0, Z.pC(var1));
   }

   public static ZV pC(Yg var0, Z.Av var1, int var2) {
      return new ZV(var0, Z.pC(var1, var2));
   }

   public static ZV pC(Yg var0, Z.Av var1, Yg var2) {
      return new ZV(var0, Z.pC(var1, var2));
   }

   @Override
   public Yg[] pC() {
      Yg[] var1 = this.kS().pC();
      if (var1.length > 0) {
         Yg[] var2 = new Yg[var1.length + 1];
         var2[0] = this.A();
         System.arraycopy(var1, 0, var2, 1, var1.length);
         return var2;
      } else {
         return new Yg[]{this.A()};
      }
   }

   @Override
   public Long pC(long var1, int var3, IMachineContext var4) {
      Yg var5 = this.A();
      Z var6 = this.kS();
      Yg var7 = var6.kS();
      if (!var5.A(var3) && var4 == null) {
         return var5.isImmediate() && var7 != null && var7.isImmediate() && var6.A() != Z.Av.UT
            ? pC(var5.getOperandValue(), var6.A(), var7.getOperandValue(), null, var3 != 64)
            : super.pC(var1, var3, var4);
      } else {
         long var8 = var4 == null ? var1 + (var3 == 16 ? 4 : 8) : PM.pC(var4, var5.getOperandValue(), var3);
         long var10 = 0L;
         if (var7 != null) {
            if (var7.getOperandType() == 0) {
               if (!var7.A(var3) && var4 == null) {
                  return super.pC(var1, var3, var4);
               }

               var10 = var4 == null ? var1 : PM.pC(var4, var7.getOperandValue(), var3);
            } else {
               var10 = (int)var7.getOperandValue(var1);
            }
         }

         return pC(var8, var6.A(), var10, var4, RegisterUtil.getRegisterBitsize(var5.getOperandValue()) != 64);
      }
   }

   public static Long pC(long var0, Z.Av var2, long var3, IMachineContext var5, boolean var6) {
      switch (var2) {
         case pC:
            return var0 << (int)var3;
         case A:
            return var0 >>> (int)var3;
         case kS:
            return var6 ? (int)var0 >> (int)var3 : var0 >> (int)var3;
         case wS:
            return var6 ? Integer.rotateRight((int)var0, (int)var3) : Long.rotateRight(var0, (int)var3);
         case UT:
            if (!PM.pC(var5)) {
               return null;
            }

            boolean var7 = PM.A(var5);
            return var0 >>> 1 | (var7 ? Integer.MIN_VALUE : 0);
         case E:
         default:
            return var0 << (int)var3 | (1 << (int)var3) - 1;
      }
   }

   public Yg A() {
      return this.E()[0];
   }

   public Z kS() {
      return (Z)this.E()[1];
   }
}
