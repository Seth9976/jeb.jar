package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICInstanceField;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class aes extends adn implements ICInstanceField {
   @SerId(1)
   String pC;
   @SerId(2)
   ICExpression A;
   @SerId(3)
   boolean kS;

   aes(String var1, ICExpression var2, boolean var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public CElementType getElementType() {
      return CElementType.InstanceField;
   }

   @Override
   public String getFieldAddress() {
      return this.pC;
   }

   @Override
   public ICField getField() {
      return adj.pC(this, this.pC);
   }

   @Override
   public ICExpression getInstance() {
      return this.A;
   }

   @Override
   public boolean isPointed() {
      return this.kS;
   }

   private void pC(String var1, COutputSink var2) {
      ICField var3 = adj.pC(this, var1);
      if (var3 != null) {
         var3.generateName(var2, false);
      } else {
         long var5 = 0L;
         var2.appendAndRecord(var1, ItemClassIdentifiers.FIELD_NAME, var5);
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      this.A.generate(var1);
      if (this.kS) {
         var1.append("->");
      } else {
         var1.append(".");
      }

      this.pC(this.pC, var1);
      this.A(var1);
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.A);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.A == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.A = (ICExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      return 31 * var1 + (this.kS ? 1231 : 1237);
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
         aes var2 = (aes)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return this.kS == var2.kS;
      }
   }

   public aes A() {
      aes var1 = new aes(this.pC, this.A.duplicate(), this.kS);
      super.pC(var1);
      return var1;
   }

   @Override
   public String toString() {
      return this.A + "->" + this.pC;
   }
}
