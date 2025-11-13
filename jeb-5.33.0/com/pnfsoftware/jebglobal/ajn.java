package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Collection;

public class ajn implements akz {
   private StringBuilder pC = new StringBuilder();
   private boolean A;
   private boolean kS;
   private boolean wS;

   public ajn() {
      this(true, true, true);
   }

   public ajn(boolean var1, boolean var2, boolean var3) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
   }

   @Override
   public boolean pC() {
      return this.A;
   }

   @Override
   public boolean A() {
      return this.kS;
   }

   @Override
   public void paren() {
      this.append("(");
   }

   @Override
   public void parenClose() {
      this.append(")");
   }

   @Override
   public void brace() {
      this.append("{");
   }

   @Override
   public void braceClose() {
      this.append("}");
   }

   @Override
   public void bracket() {
      this.append("[");
   }

   @Override
   public void bracketClose() {
      this.append("]");
   }

   @Override
   public void append(String var1) {
      this.pC.append(var1);
   }

   @Override
   public void append(String var1, ItemClassIdentifiers var2) {
      this.pC.append(var1);
   }

   @Override
   public void pC(IEGeneric var1) {
      ((ala)var1).pC(this);
   }

   @Override
   public void pC(Collection var1) {
      this.append("[");
      int var2 = 0;

      for (IEGeneric var4 : var1) {
         if (var2 >= 1) {
            this.append(", ");
         }

         ((ala)var4).pC(this);
         var2++;
      }

      this.append("]");
   }

   @Override
   public void pC(int var1) {
      this.append(Strings.ff("%04X", var1));
   }

   @Override
   public void A(IEGeneric var1) {
      if (this.wS) {
         if (var1.getType() != null) {
            String var2 = var1.getType().toString();
            if (!var2.equals("?")) {
               this.pC.append("<<").append(var2).append(">>");
            }
         }
      }
   }

   @Override
   public String toString() {
      return this.pC.toString();
   }
}
