package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger32;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Characters;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.math.BigInteger;

@Ser
public class afs extends afo implements ICConstantInteger32 {
   @SerId(1)
   NumberFormatter Dw;
   @SerId(2)
   boolean Uv;
   @SerTransient
   int oW;

   afs(int var1) {
      this(var1, false);
   }

   afs(int var1, boolean var2) {
      super(null, var1);
      this.Uv = var2;
   }

   afs(ICType var1, int var2) {
      super(var1, var2);
   }

   public afs xK() {
      afs var1 = new afs((Integer)this.getValue());
      this.q(var1);
      var1.Dw = this.Dw;
      var1.Uv = this.Uv;
      return var1;
   }

   @Override
   public int getBitsize() {
      return 32;
   }

   @Override
   public boolean isUnsigned() {
      return this.Uv;
   }

   @Override
   public BigInteger getIntegerValue() {
      return BigInteger.valueOf(((Integer)this.RF).intValue());
   }

   @Override
   public long getValueAsLong() {
      return ((Integer)this.RF).intValue();
   }

   @Override
   public boolean isTrueLike() {
      return (Integer)this.RF != 0;
   }

   @Override
   public boolean isPositive() {
      return (Integer)this.RF > 0;
   }

   @Override
   public boolean isNegative() {
      return (Integer)this.RF < 0;
   }

   @Override
   protected void xK(COutputSink var1) {
      String var2 = null;
      long var3 = 0L;
      ItemClassIdentifiers var5 = ItemClassIdentifiers.NUMBER;
      if (this.Dw != null) {
         var2 = this.Dw.format(32, ((Integer)this.RF).intValue() & 4294967295L);
      } else if (this.q != null && "char".equals(this.q.getSignature())) {
         int var6 = (Integer)this.RF;
         if (Characters.isAsciiChar(var6)) {
            var2 = Strings.ff("'%s'", Formatter.escapeCharacter((char)((Integer)this.RF).intValue()));
            var5 = ItemClassIdentifiers.CHARACTER;
         }
      }

      if (var2 == null) {
         if (this.oW == 0) {
            if (this.isUnsigned()) {
               this.oW = afo.q(((Integer)this.RF).intValue() & 4294967295L, true);
            } else {
               this.oW = afo.q(((Integer)this.RF).intValue(), false);
            }
         }

         int var12 = this.oW;
         ICElement var7 = var1.astPeekSafe(1);
         if (var7 instanceof ICOperation) {
            COperatorType var8 = ((ICOperation)var7).getOperator().getType();
            if (var8 == COperatorType.OR || var8 == COperatorType.AND || var8 == COperatorType.XOR || var8 == COperatorType.NOT) {
               var12 = 16;
            }
         }

         if (var12 == 16) {
            var2 = "0x" + Integer.toUnsignedString((Integer)this.RF, 16);
         } else if (this.isUnsigned()) {
            var2 = Integer.toUnsignedString((Integer)this.RF);
         } else {
            var2 = Integer.toString((Integer)this.RF);
         }
      }

      IDynamicContentManager var13 = var1.getDynamicContentManager();
      if (var13 != null) {
         long var14 = ((Integer)this.RF).intValue() & 4294967295L;
         NativeCoordinates var9 = new NativeCoordinates(var14);
         String var10 = var13.getLabelName(var9);
         if (var10 != null) {
            var2 = "&" + var10;
            var3 = var13.getLabelItemId(var9);
            var5 = ItemClassIdentifiers.LABEL;
         }

         String var11 = var13.getPotentialDataAsString(var14);
         if (var11 != null) {
            var2 = "\"" + Formatter.escapeString(var11) + "\"";
            var3 = 0L;
            var5 = ItemClassIdentifiers.STRING_GENERATED;
         }

         if (var3 == 0L) {
            var3 = var13.getObjectItemId(this);
         }
      }

      this.q(var1, var2, var5, var3);
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
      return 31 * var1 + (this.RF == null ? 0 : ((Integer)this.RF).hashCode());
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
         afs var2 = (afs)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!((Integer)this.RF).equals(var2.RF)) {
            return false;
         }

         return true;
      }
   }
}
