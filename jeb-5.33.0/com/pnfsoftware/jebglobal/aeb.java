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
public class aeb extends adv implements ICConstantIntegerLarge {
   @SerId(1)
   NumberFormatter wS;
   @SerId(2)
   int UT;

   aeb(int var1, BigInteger var2) {
      super(null, var2);
      this.UT = var1;
   }

   public aeb kS() {
      aeb var1 = new aeb(this.UT, (BigInteger)this.getValue());
      this.pC(var1);
      var1.wS = this.wS;
      return var1;
   }

   @Override
   public int getBitsize() {
      return this.UT;
   }

   @Override
   public boolean isUnsigned() {
      return false;
   }

   @Override
   public BigInteger getIntegerValue() {
      return (BigInteger)this.A;
   }

   @Override
   public long getValueAsLong() {
      return ((BigInteger)this.A).longValue();
   }

   @Override
   public boolean isTrueLike() {
      return ((BigInteger)this.A).signum() != 0;
   }

   @Override
   public boolean isPositive() {
      return ((BigInteger)this.A).signum() > 0;
   }

   @Override
   public boolean isNegative() {
      return ((BigInteger)this.A).signum() < 0;
   }

   @Override
   protected void kS(COutputSink var1) {
      BigInteger var2 = (BigInteger)this.A;
      if (((BigInteger)this.A).signum() < 0) {
         var2 = BigInteger.ONE.shiftLeft(this.UT).subtract(((BigInteger)this.A).negate());
      }

      String var3 = "0x" + var2.toString(16).toUpperCase() + "X";
      long var4 = 0L;
      ItemClassIdentifiers var6 = ItemClassIdentifiers.NUMBER;
      IDynamicContentManager var7 = var1.getDynamicContentManager();
      if (var7 != null) {
         if (this.wS != null) {
            var3 = this.wS.format(this.UT, (BigInteger)this.A) + "X";
         }

         var4 = var7.getObjectItemId(this);
      }

      this.pC(var1, var3, var6, var4);
   }

   @Override
   public boolean needsCustomFormatting() {
      return this.wS != null;
   }

   @Override
   public NumberFormatter getFormatter() {
      return this.pC(true);
   }

   public NumberFormatter pC(boolean var1) {
      if (this.wS == null && var1) {
         this.wS = new NumberFormatter();
         this.wS.setHexaNotationType(NumberFormatter.HexaNotationType._0x_prefix);
         this.wS.setBase(NumberFormatter.NumberBase.HEXADECIMAL);
      }

      return this.wS;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + Long.hashCode(this.UT);
      return 31 * var1 + (this.A == null ? 0 : ((BigInteger)this.A).hashCode());
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
         aeb var2 = (aeb)var1;
         if (this.UT != var2.UT) {
            return false;
         } else {
            if (this.A == null) {
               if (var2.A != null) {
                  return false;
               }
            } else if (!((BigInteger)this.A).equals(var2.A)) {
               return false;
            }

            return true;
         }
      }
   }
}
