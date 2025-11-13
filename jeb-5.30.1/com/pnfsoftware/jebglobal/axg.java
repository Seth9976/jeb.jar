package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public abstract class axg extends axi implements INativeContinuousItem {
   public axg(int var1) {
      super(var1, null);
   }

   @Override
   protected final void c_() {
      super.c_();
      if (this.xK != null) {
         if (this.xK instanceof Bm) {
            ((Bm)this.xK).q(this.getMemoryAddress());
         }

         if (this.xK instanceof INativeCodeModel) {
            ((INativeCodeModel)this.xK).getReferenceManager().unrecordAllReferencesFrom(this.getMemoryAddress());
         }
      }
   }

   public Long xK() {
      return this.getMemoryAddress();
   }

   public Long Dw() {
      return this.getMemoryAddress() + this.getMemorySize();
   }
}
