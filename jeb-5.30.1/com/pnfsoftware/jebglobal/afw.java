package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantString;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class afw extends afo implements ICConstantString {
   @SerId(1)
   long Dw;
   @SerId(2)
   Integer Uv;

   afw(String var1, long var2) {
      this(var1, var2, null);
   }

   afw(String var1, long var2, Integer var4) {
      super(null, var1);
      this.Dw = var2;
      this.Uv = var4;
   }

   public long xK() {
      return this.Dw;
   }

   @Override
   public boolean isTrueLike() {
      return ((String)this.RF).length() != 0;
   }

   @Override
   protected void xK(COutputSink var1) {
      boolean var2 = ((String)this.RF).length() <= 500;
      String var3 = "\"" + Formatter.escapeString((CharSequence)this.RF, var2) + "\"";
      ItemClassIdentifiers var4 = ItemClassIdentifiers.STRING;
      if (this.xK != null) {
         var4 = ItemClassIdentifiers.STRING_GENERATED;
      } else if (this.Uv != null && var1.getDynamicContentManager() != null) {
         IDynamicContentManager var5 = var1.getDynamicContentManager();
         INativeStringItem var6 = var5.getNativeString(this.Uv);
         if (var6 != null) {
            String var7 = (String)var6.getAttribute("GeneratedFrom", String.class);
            if (var7 != null) {
               var4 = ItemClassIdentifiers.STRING_GENERATED;
            }
         }
      } else {
         var4 = ItemClassIdentifiers.STRING;
      }

      this.q(var1, var3, var4, this.Dw);
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.RF == null ? 0 : ((String)this.RF).hashCode());
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
         afw var2 = (afw)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!((String)this.RF).equals(var2.RF)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      return this.hashCode() & 4294967295L;
   }
}
