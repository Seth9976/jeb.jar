package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantPointer;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aec extends adv implements ICConstantPointer {
   aec(long var1) {
      super(null, var1);
   }

   @Override
   public boolean isNull() {
      return (Long)this.A == 0L;
   }

   @Override
   public boolean isTrueLike() {
      return !this.isNull();
   }

   @Override
   protected void kS(COutputSink var1) {
      if (this.isNull()) {
         var1.appendAndRecord("NULL", ItemClassIdentifiers.NUMBER);
      } else {
         String var2 = Strings.ff("0x%X", this.A);
         long var3 = 0L;
         ItemClassIdentifiers var5 = ItemClassIdentifiers.NUMBER;
         IDynamicContentManager var6 = var1.getDynamicContentManager();
         if (var6 != null) {
            long var7 = (Long)this.A;
            NativeCoordinates var9 = new NativeCoordinates(var7);
            String var10 = var6.getLabelName(var9);
            if (var10 != null) {
               var2 = "&" + var10;
               var3 = var6.getLabelItemId(var9);
               var5 = ItemClassIdentifiers.LABEL;
            }

            String var11 = var6.getPotentialDataAsString(var7);
            if (var11 != null) {
               var2 = "\"" + Formatter.escapeString(var11) + "\"";
               var3 = 0L;
               var5 = ItemClassIdentifiers.STRING_GENERATED;
            }

            if (var3 == 0L) {
               var3 = var6.getObjectItemId(this);
            }
         }

         this.pC(var1, var2, var5, var3);
      }
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
         aec var2 = (aec)var1;
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
