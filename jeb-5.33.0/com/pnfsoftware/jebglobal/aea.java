package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger64;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.math.BigInteger;

@Ser
public class aea extends adv implements ICConstantInteger64 {
   @SerId(1)
   NumberFormatter wS;
   @SerId(2)
   boolean UT;
   @SerTransient
   int E;

   aea(long var1) {
      this(var1, false);
   }

   aea(long var1, boolean var3) {
      super(null, var1);
      this.UT = var3;
   }

   public aea kS() {
      aea var1 = new aea((Long)this.getValue());
      this.pC(var1);
      var1.wS = this.wS;
      var1.UT = this.UT;
      return var1;
   }

   @Override
   public int getBitsize() {
      return 64;
   }

   @Override
   public boolean isUnsigned() {
      return this.UT;
   }

   @Override
   public BigInteger getIntegerValue() {
      return BigInteger.valueOf((Long)this.A);
   }

   @Override
   public long getValueAsLong() {
      return (Long)this.A;
   }

   @Override
   public boolean isTrueLike() {
      return (Long)this.A != 0L;
   }

   @Override
   public boolean isPositive() {
      return (Long)this.A > 0L;
   }

   @Override
   public boolean isNegative() {
      return (Long)this.A < 0L;
   }

   @Override
   protected void kS(COutputSink var1) {
      String var2;
      if (this.wS != null) {
         var2 = this.wS.format(64, (Long)this.A) + "L";
      } else {
         if (this.E == 0) {
            this.E = adv.pC((Long)this.A, this.isUnsigned());
         }

         int var3 = this.E;
         ICElement var4 = var1.astPeekSafe(1);
         if (var4 instanceof ICOperation) {
            COperatorType var5 = ((ICOperation)var4).getOperator().getType();
            if (var5 == COperatorType.OR || var5 == COperatorType.AND || var5 == COperatorType.XOR || var5 == COperatorType.NOT) {
               var3 = 16;
            }
         }

         if (var3 == 16) {
            var2 = "0x" + Long.toUnsignedString((Long)this.A, 16) + "L";
         } else if (this.isUnsigned()) {
            var2 = Long.toUnsignedString((Long)this.A) + "L";
         } else {
            var2 = Long.toString((Long)this.A) + "L";
         }
      }

      long var12 = 0L;
      ItemClassIdentifiers var13 = ItemClassIdentifiers.NUMBER;
      IDynamicContentManager var6 = var1.getDynamicContentManager();
      if (var6 != null) {
         long var7 = (Long)this.A;
         NativeCoordinates var9 = new NativeCoordinates(var7);
         String var10 = var6.getLabelName(var9);
         if (var10 != null) {
            var2 = "&" + var10;
            var12 = var6.getLabelItemId(var9);
            var13 = ItemClassIdentifiers.LABEL;
         }

         String var11 = var6.getPotentialDataAsString(var7);
         if (var11 != null) {
            var2 = "\"" + Formatter.escapeString(var11) + "\"";
            var12 = 0L;
            var13 = ItemClassIdentifiers.STRING_GENERATED;
         }

         if (var12 == 0L) {
            var12 = var6.getObjectItemId(this);
         }
      }

      this.pC(var1, var2, var13, var12);
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
         this.wS.setBase(NumberFormatter.NumberBase.DECIMAL);
      }

      return this.wS;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.A == null ? 0 : ((Long)this.A).hashCode());
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
         aea var2 = (aea)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!((Long)this.A).equals(var2.A)) {
            return false;
         }

         return true;
      }
   }
}
