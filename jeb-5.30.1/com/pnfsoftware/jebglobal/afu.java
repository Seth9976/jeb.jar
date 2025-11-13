package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantIntegerLarge;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;

@Ser
public class afu extends afo implements ICConstantIntegerLarge {
   @SerId(1)
   NumberFormatter Dw;
   @SerId(2)
   int Uv;

   afu(int var1, BigInteger var2) {
      super(null, var2);
      this.Uv = var1;
   }

   public afu xK() {
      afu var1 = new afu(this.Uv, (BigInteger)this.getValue());
      this.q(var1);
      var1.Dw = this.Dw;
      return var1;
   }

   @Override
   public int getBitsize() {
      return this.Uv;
   }

   @Override
   public boolean isUnsigned() {
      return false;
   }

   @Override
   public BigInteger getIntegerValue() {
      return (BigInteger)this.RF;
   }

   @Override
   public long getValueAsLong() {
      return ((BigInteger)this.RF).longValue();
   }

   @Override
   public boolean isTrueLike() {
      return ((BigInteger)this.RF).signum() != 0;
   }

   @Override
   public boolean isPositive() {
      return ((BigInteger)this.RF).signum() > 0;
   }

   @Override
   public boolean isNegative() {
      return ((BigInteger)this.RF).signum() < 0;
   }

   @Override
   protected void xK(COutputSink var1) {
      BigInteger var2 = (BigInteger)this.RF;
      if (((BigInteger)this.RF).signum() < 0) {
         var2 = BigInteger.ONE.shiftLeft(this.Uv).subtract(((BigInteger)this.RF).negate());
      }

      String var3 = "0x" + var2.toString(16).toUpperCase() + "X";
      long var4 = 0L;
      ItemClassIdentifiers var6 = ItemClassIdentifiers.NUMBER;
      IDynamicContentManager var7 = var1.getDynamicContentManager();
      if (var7 != null) {
         if (this.Dw != null) {
            var3 = this.Dw.format(this.Uv, (BigInteger)this.RF) + "X";
         }

         var4 = var7.getObjectItemId(this);
      }

      this.q(var1, var3, var6, var4);
   }

   @Override
   public boolean needsCustomFormatting() {
      return this.Dw != null;
   }

   @Override
   public NumberFormatter getFormatter() {
      return this.q(true);
   }

   public NumberFormatter q(boolean var1) {
      if (this.Dw == null && var1) {
         this.Dw = new NumberFormatter();
         this.Dw.setHexaNotationType(NumberFormatter.HexaNotationType._0x_prefix);
         this.Dw.setBase(NumberFormatter.NumberBase.HEXADECIMAL);
      }

      return this.Dw;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + Long.hashCode(this.Uv);
      return 31 * var1 + (this.RF == null ? 0 : ((BigInteger)this.RF).hashCode());
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
         afu var2 = (afu)var1;
         if (this.Uv != var2.Uv) {
            return false;
         } else {
            if (this.RF == null) {
               if (var2.RF != null) {
                  return false;
               }
            } else if (!((BigInteger)this.RF).equals(var2.RF)) {
               return false;
            }

            return true;
         }
      }
   }
}
