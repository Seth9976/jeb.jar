package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Uw extends FH {
   public Uw(int var1, int var2, Yg... var3) {
      super(pC(var3), var1, 770 | var2, var3);
   }

   private static int pC(Yg[] var0) {
      int var1 = 0;

      for (Yg var5 : var0) {
         var1 += var5.getOperandBitsize();
      }

      return var1;
   }

   @Override
   public Yg[] pC() {
      return this.E();
   }

   private boolean pC(Yg var1, Yg var2, int var3, int var4) {
      if (var3 == 0 && var4 >= 13) {
         return false;
      } else if (var3 == 6) {
         return false;
      } else if (var1.getFlags() == 0 && var2.getFlags() == 0) {
         if (RegisterUtil.getRegisterBitsize(var1.getOperandValue()) != RegisterUtil.getRegisterBitsize(var2.getOperandValue())) {
            return false;
         } else {
            boolean var5 = var2 instanceof lw;
            if (var5 != (var1 instanceof lw)) {
               return false;
            } else {
               return var5 && !Strings.equals(var1.getSuffix(null), var2.getSuffix(null))
                  ? false
                  : var4 == RegisterUtil.getRegisterIndex(var1.getOperandValue()) + 1;
            }
         }
      } else {
         return false;
      }
   }

   @Override
   public IInstructionOperandGeneric merge(long var1) {
      Yg[] var3 = this.E();
      if (var3 != null && var3.length >= 3) {
         ArrayList var4 = new ArrayList();
         Yg var5 = var3[0];
         Yg var6 = var3[0];
         boolean var7 = false;

         for (int var8 = 1; var8 < var3.length; var8++) {
            Yg var9 = var3[var8];
            long var10 = var9.getOperandValue();
            int var12 = RegisterUtil.getRegisterGroup(var10);
            int var13 = RegisterUtil.getRegisterIndex(var10);
            if (var9.getOperandType() == 0 && this.pC(var6, var9, var12, var13)) {
               var6 = var9;
               if (var8 + 1 == var3.length) {
                  var7 |= this.pC(var4, var5, var9);
               }
            } else {
               var7 |= this.pC(var4, var5, var6);
               var5 = var9;
               var6 = var9;
               if (var8 + 1 == var3.length) {
                  var4.add(var9);
               }
            }
         }

         if (var7) {
            if (var4.size() > 1) {
               int var14 = RegisterUtil.getRegisterGroup(var5.getOperandValue());
               if (var14 != 0) {
                  return null;
               }
            }

            return new Uw.Av(this, (int)this.value, this.getFlags(), (Yg[])var4.toArray(new Yg[var4.size()]));
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private boolean pC(List var1, Yg var2, Yg var3) {
      if (var2.getOperandValue() == var3.getOperandValue()) {
         var1.add(var2);
      } else {
         if (var2.getOperandValue() + 1L != var3.getOperandValue()) {
            short var4 = 4096;
            if ((this.getFlags() & 64) != 0) {
               var4 |= 8192;
            }

            var1.add(new Uw.Av(this, var2.getOperandValue(), var4, var2, var3));
            return true;
         }

         var1.add(var2);
         var1.add(var3);
      }

      return false;
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         return !super.equals(var1) ? false : this.getClass() == var1.getClass();
      }
   }

   @Ser
   public static class Av extends FH {
      @SerId(1)
      Uw A;

      public Av(Uw var1, long var2, int var4, Yg... var5) {
         super(var1.getOperandBitsize(), var2, var4, var5);
         this.A = var1;
      }

      @Override
      public Yg[] pC() {
         return this.A.pC();
      }
   }
}
