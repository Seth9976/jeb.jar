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
public class agx extends afg implements ICSource {
   @SerId(1)
   List q = new ArrayList();

   agx() {
   }

   @Override
   public CElementType getElementType() {
      return null;
   }

   public agx RF() {
      agx var1 = new agx();
      super.q(var1);
      var1.q = new ArrayList(this.q.size());

      for (ICSourceElement var3 : this.q) {
         var1.q.add(var3.duplicate());
      }

      return var1;
   }

   @Override
   public List getDeclarations() {
      return this.q;
   }

   public void q(ICSourceElement var1) {
      this.q.add(var1);
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.q);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      for (int var3 = 0; var3 < this.q.size(); var3++) {
         ICSourceElement var4 = (ICSourceElement)this.q.get(var3);
         if (var1 == var4) {
            if (!(var2 instanceof ICSourceElement)) {
               return false;
            }

            this.q.set(var3, (ICSourceElement)var2);
            return true;
         }
      }

      return false;
   }

   @Override
   public void generate(COutputSink var1) {
      for (ICSourceElement var3 : this.q) {
         var3.generate(var1);
         var1.eol();
      }
   }
}
