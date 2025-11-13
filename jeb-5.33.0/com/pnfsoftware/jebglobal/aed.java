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
public class aed extends adv implements ICConstantString {
   @SerId(1)
   long wS;
   @SerId(2)
   Integer UT;

   aed(String var1, long var2) {
      this(var1, var2, null);
   }

   aed(String var1, long var2, Integer var4) {
      super(null, var1);
      this.wS = var2;
      this.UT = var4;
   }

   @Override
   public boolean isTrueLike() {
      return ((String)this.A).length() != 0;
   }

   @Override
   protected void kS(COutputSink var1) {
      boolean var2 = ((String)this.A).length() <= 500;
      String var3 = "\"" + Formatter.escapeString((CharSequence)this.A, var2) + "\"";
      ItemClassIdentifiers var4 = ItemClassIdentifiers.STRING;
      if (this.kS != null) {
         var4 = ItemClassIdentifiers.STRING_GENERATED;
      } else if (this.UT != null && var1.getDynamicContentManager() != null) {
         IDynamicContentManager var5 = var1.getDynamicContentManager();
         INativeStringItem var6 = var5.getNativeString(this.UT);
         if (var6 != null) {
            String var7 = (String)var6.getAttribute("GeneratedFrom", String.class);
            if (var7 != null) {
               var4 = ItemClassIdentifiers.STRING_GENERATED;
            }
         }
      } else {
         var4 = ItemClassIdentifiers.STRING;
      }

      this.pC(var1, var3, var4, this.wS);
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.A == null ? 0 : ((String)this.A).hashCode());
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
         aed var2 = (aed)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!((String)this.A).equals(var2.A)) {
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
