package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Collection;

public class alq implements and {
   private StringBuilder RF = new StringBuilder();
   private boolean xK;
   private boolean Dw;
   private boolean Uv;
   public static final boolean q = false;

   public alq() {
      this(true, true, true);
   }

   public alq(boolean var1, boolean var2, boolean var3) {
      this.xK = var1;
      this.Dw = var2;
      this.Uv = var3;
   }

   @Override
   public boolean q() {
      return this.xK;
   }

   @Override
   public boolean RF() {
      return this.Dw;
   }

   @Override
   public boolean xK() {
      return this.Uv;
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
      this.RF.append(var1);
   }

   @Override
   public void append(String var1, ItemClassIdentifiers var2) {
      this.RF.append(var1);
   }

   @Override
   public void q(IEGeneric var1) {
      ((ane)var1).q(this);
   }

   @Override
   public void q(Collection var1) {
      this.append("[");
      int var2 = 0;

      for (IEGeneric var4 : var1) {
         if (var2 >= 1) {
            this.append(", ");
         }

         ((ane)var4).q(this);
         var2++;
      }

      this.append("]");
   }

   @Override
   public void q(int var1) {
      this.append(Strings.ff("%04X", var1));
   }

   @Override
   public void RF(IEGeneric var1) {
      if (this.Uv) {
         if (var1.getType() != null) {
            String var2 = var1.getType().toString();
            if (!var2.equals("?")) {
               this.RF.append("<<").append(var2).append(">>");
            }
         }
      }
   }

   @Override
   public String toString() {
      return this.RF.toString();
   }
}
