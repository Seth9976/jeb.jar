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
public class aft extends afo implements ICConstantInteger64 {
   @SerId(1)
   NumberFormatter Dw;
   @SerId(2)
   boolean Uv;
   @SerTransient
   int oW;

   aft(long var1) {
      this(var1, false);
   }

   aft(long var1, boolean var3) {
      super(null, var1);
      this.Uv = var3;
   }

   public aft xK() {
      aft var1 = new aft((Long)this.getValue());
      this.q(var1);
      var1.Dw = this.Dw;
      var1.Uv = this.Uv;
      return var1;
   }

   @Override
   public int getBitsize() {
      return 64;
   }

   @Override
   public boolean isUnsigned() {
      return this.Uv;
   }

   @Override
   public BigInteger getIntegerValue() {
      return BigInteger.valueOf((Long)this.RF);
   }

   @Override
   public long getValueAsLong() {
      return (Long)this.RF;
   }

   @Override
   public boolean isTrueLike() {
      return (Long)this.RF != 0L;
   }

   @Override
   public boolean isPositive() {
      return (Long)this.RF > 0L;
   }

   @Override
   public boolean isNegative() {
      return (Long)this.RF < 0L;
   }

   @Override
   protected void xK(COutputSink var1) {
      String var2;
      if (this.Dw != null) {
         var2 = this.Dw.format(64, (Long)this.RF) + "L";
      } else {
         if (this.oW == 0) {
            this.oW = afo.q((Long)this.RF, this.isUnsigned());
         }

         int var3 = this.oW;
         ICElement var4 = var1.astPeekSafe(1);
         if (var4 instanceof ICOperation) {
            COperatorType var5 = ((ICOperation)var4).getOperator().getType();
            if (var5 == COperatorType.OR || var5 == COperatorType.AND || var5 == COperatorType.XOR || var5 == COperatorType.NOT) {
               var3 = 16;
            }
         }

         if (var3 == 16) {
            var2 = "0x" + Long.toUnsignedString((Long)this.RF, 16) + "L";
         } else if (this.isUnsigned()) {
            var2 = Long.toUnsignedString((Long)this.RF) + "L";
         } else {
            var2 = Long.toString((Long)this.RF) + "L";
         }
      }

      long var12 = 0L;
      ItemClassIdentifiers var13 = ItemClassIdentifiers.NUMBER;
      IDynamicContentManager var6 = var1.getDynamicContentManager();
      if (var6 != null) {
         long var7 = (Long)this.RF;
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

      this.q(var1, var2, var13, var12);
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
         this.Dw.setBase(NumberFormatter.NumberBase.DECIMAL);
      }

      return this.Dw;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.RF == null ? 0 : ((Long)this.RF).hashCode());
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
         aft var2 = (aft)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!((Long)this.RF).equals(var2.RF)) {
            return false;
         }

         return true;
      }
   }
}
