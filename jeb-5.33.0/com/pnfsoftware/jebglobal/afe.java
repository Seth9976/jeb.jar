package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSource;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSourceElement;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class afe extends adn implements ICSource {
   @SerId(1)
   List pC = new ArrayList();

   afe() {
   }

   @Override
   public CElementType getElementType() {
      return null;
   }

   public afe A() {
      afe var1 = new afe();
      super.pC(var1);
      var1.pC = new ArrayList(this.pC.size());

      for (ICSourceElement var3 : this.pC) {
         var1.pC.add(var3.duplicate());
      }

      return var1;
   }

   @Override
   public List getDeclarations() {
      return this.pC;
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.pC);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      for (int var3 = 0; var3 < this.pC.size(); var3++) {
         ICSourceElement var4 = (ICSourceElement)this.pC.get(var3);
         if (var1 == var4) {
            if (!(var2 instanceof ICSourceElement)) {
               return false;
            }

            this.pC.set(var3, (ICSourceElement)var2);
            return true;
         }
      }

      return false;
   }

   @Override
   public void generate(COutputSink var1) {
      for (ICSourceElement var3 : this.pC) {
         var3.generate(var1);
         var1.eol();
      }
   }
}
