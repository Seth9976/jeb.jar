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
public class adz extends adv implements ICConstantInteger32 {
   @SerId(1)
   NumberFormatter wS;
   @SerId(2)
   boolean UT;
   @SerTransient
   int E;

   adz(int var1) {
      this(var1, false);
   }

   adz(int var1, boolean var2) {
      super(null, var1);
      this.UT = var2;
   }

   adz(ICType var1, int var2) {
      super(var1, var2);
   }

   public adz kS() {
      adz var1 = new adz((Integer)this.getValue());
      this.pC(var1);
      var1.wS = this.wS;
      var1.UT = this.UT;
      return var1;
   }

   @Override
   public int getBitsize() {
      return 32;
   }

   @Override
   public boolean isUnsigned() {
      return this.UT;
   }

   @Override
   public BigInteger getIntegerValue() {
      return BigInteger.valueOf(((Integer)this.A).intValue());
   }

   @Override
   public long getValueAsLong() {
      return ((Integer)this.A).intValue();
   }

   @Override
   public boolean isTrueLike() {
      return (Integer)this.A != 0;
   }

   @Override
   public boolean isPositive() {
      return (Integer)this.A > 0;
   }

   @Override
   public boolean isNegative() {
      return (Integer)this.A < 0;
   }

   @Override
   protected void kS(COutputSink var1) {
      String var2 = null;
      long var3 = 0L;
      ItemClassIdentifiers var5 = ItemClassIdentifiers.NUMBER;
      if (this.wS != null) {
         var2 = this.wS.format(32, ((Integer)this.A).intValue() & 4294967295L);
      } else if (this.pC != null && "char".equals(this.pC.getSignature())) {
         int var6 = (Integer)this.A;
         if (Characters.isAsciiChar(var6)) {
            var2 = Strings.ff("'%s'", Formatter.escapeCharacter((char)((Integer)this.A).intValue()));
            var5 = ItemClassIdentifiers.CHARACTER;
         }
      }

      if (var2 == null) {
         if (this.E == 0) {
            if (this.isUnsigned()) {
               this.E = adv.pC(((Integer)this.A).intValue() & 4294967295L, true);
            } else {
               this.E = adv.pC(((Integer)this.A).intValue(), false);
            }
         }

         int var12 = this.E;
         ICElement var7 = var1.astPeekSafe(1);
         if (var7 instanceof ICOperation) {
            COperatorType var8 = ((ICOperation)var7).getOperator().getType();
            if (var8 == COperatorType.OR || var8 == COperatorType.AND || var8 == COperatorType.XOR || var8 == COperatorType.NOT) {
               var12 = 16;
            }
         }

         if (var12 == 16) {
            var2 = "0x" + Integer.toUnsignedString((Integer)this.A, 16);
         } else if (this.isUnsigned()) {
            var2 = Integer.toUnsignedString((Integer)this.A);
         } else {
            var2 = Integer.toString((Integer)this.A);
         }
      }

      IDynamicContentManager var13 = var1.getDynamicContentManager();
      if (var13 != null) {
         long var14 = ((Integer)this.A).intValue() & 4294967295L;
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

      this.pC(var1, var2, var5, var3);
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
      return 31 * var1 + (this.A == null ? 0 : ((Integer)this.A).hashCode());
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
         adz var2 = (adz)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!((Integer)this.A).equals(var2.A)) {
            return false;
         }

         return true;
      }
   }
}
