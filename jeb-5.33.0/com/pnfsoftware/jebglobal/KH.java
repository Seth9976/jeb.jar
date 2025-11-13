package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class KH extends FH {
   @SerId(1)
   private boolean A;
   @SerId(2)
   private boolean kS;
   @SerId(3)
   private int wS;

   public KH(Yg var1, Yg var2, boolean var3, boolean var4, int var5) {
      super(var1.getOperandValue(), pC(var4, A(var3, var4)) | 512 | 256, var1, var2);
      this.A = var3;
      this.kS = var4;
      this.wS = var5;
   }

   protected static int pC(boolean var0, boolean var1) {
      return (var0 ? 1 : 0) | (var1 ? 131072 : 0);
   }

   @Override
   public Yg[] pC() {
      Yg var1 = this.sY();
      if (var1 != null) {
         if (var1.getOperandType() == 0) {
            return new Yg[]{this.A(), var1};
         }

         if (var1.getOperandType() == 7) {
            Yg[] var2 = ((FH)var1).pC();
            if (var2.length > 0) {
               Yg[] var3 = new Yg[var2.length + 1];
               var3[0] = this.A();
               System.arraycopy(var2, 0, var3, 1, var2.length);
               return var3;
            }
         }
      }

      return new Yg[]{this.A()};
   }

   private static boolean A(boolean var0, boolean var1) {
      return !var1 && var0 ? false : var0;
   }

   public Yg A() {
      return this.E()[0];
   }

   public Yg kS() {
      return this.E()[0] instanceof hy ? ((hy)this.A()).pC()[0] : this.E()[0];
   }

   public Yg sY() {
      return this.E().length > 1 ? this.E()[1] : null;
   }

   @Override
   public boolean UT() {
      return !this.kS || this.A;
   }

   public boolean ys() {
      return this.kS;
   }

   public boolean ld() {
      Yg[] var1 = this.E();
      return var1 != null && var1.length > 0 && var1[0].A(this.wS) && this.UT();
   }

   public Yg pC(long var1) {
      Yg[] var3 = this.E();
      if (this.kS && !this.A) {
         if (var3.length == 1) {
            if (var3[0] instanceof Yg) {
               Yg var4 = var3[0];
               if (var4.A(this.wS)) {
                  return new cv(0L, this.wS, true, false);
               }
            }
         } else if (var3.length == 2 && var3[0] instanceof Yg && var3[1] instanceof Yg) {
            Yg var7 = var3[0];
            Yg var5 = var3[1];
            if (var7.A(this.wS)) {
               Long var6 = var5.pC(var1, var7.getOperandBitsize(), null);
               if (var6 != null) {
                  return new cv(var6, this.wS, true, false);
               }
            }
         }
      }

      return null;
   }

   @Override
   public Long pC(long var1, int var3, IMachineContext var4) {
      Yg[] var5 = this.E();
      Long var6 = var5[0].pC(var1, var3, var4);
      if (this.kS) {
         Long var7 = var5[1].pC(var1, var3, var4);
         return var7 != null && var6 != null ? var6 + var7 : var6;
      } else {
         return var6;
      }
   }

   @Override
   public long getOperandValue(long var1) {
      Yg var3 = this.pC(var1);
      return var3 == null ? super.getOperandValue(var1) : var3.getOperandValue(var1);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.A ? 1231 : 1237);
      var1 = 31 * var1 + this.wS;
      return 31 * var1 + (this.kS ? 1231 : 1237);
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
         KH var2 = (KH)var1;
         if (this.A != var2.A) {
            return false;
         } else {
            return this.wS != var2.wS ? false : this.kS == var2.kS;
         }
      }
   }
}
