package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTuple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class afi extends adn implements ICTuple {
   @SerId(1)
   List pC;

   afi(List var1) {
      this.pC = var1 == null ? new ArrayList() : new ArrayList(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Tuple;
   }

   public afi A() {
      ArrayList var1 = new ArrayList(this.pC.size());

      for (ICExpression var3 : this.pC) {
         var1.add((ICLeftExpression)var3.duplicate());
      }

      afi var4 = new afi(var1);
      super.pC(var4);
      return var4;
   }

   @Override
   public int getCountOfEntries() {
      return this.pC.size();
   }

   @Override
   public ICExpression getEntry(int var1) {
      return (ICExpression)this.pC.get(var1);
   }

   @Override
   public List getEntries() {
      return this.pC;
   }

   @Override
   public void setEntries(List var1) {
      this.pC = new ArrayList(var1);
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.pC);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      for (int var3 = 0; var3 < this.pC.size(); var3++) {
         ICExpression var4 = (ICExpression)this.pC.get(var3);
         if (var4 == var1) {
            if (!(var2 instanceof ICLeftExpression)) {
               return false;
            }

            this.pC.set(var3, (ICLeftExpression)var2);
            return true;
         }
      }

      return false;
   }

   @Override
   public void generate(COutputSink var1) {
      if (this.pC.size() >= 2) {
         var1.paren();
      }

      int var2 = 0;

      for (ICExpression var4 : this.pC) {
         if (var2 > 0) {
            var1.append(", ");
         }

         var4.generate(var1);
         var2++;
      }

      if (this.pC.size() >= 2) {
         var1.parenClose();
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("(");
      int var2 = 0;

      for (ICExpression var4 : this.pC) {
         if (var2 > 0) {
            var1.append(", ");
         }

         var1.append(var4);
         var2++;
      }

      var1.append(")");
      return var1.toString();
   }
}
