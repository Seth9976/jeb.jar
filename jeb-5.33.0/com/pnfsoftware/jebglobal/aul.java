package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public abstract class aul extends aun implements INativeContinuousItem {
   public aul(int var1) {
      super(var1, null);
   }

   @Override
   protected final void c_() {
      super.c_();
      if (this.A != null) {
         if (this.A instanceof ZH) {
            ((ZH)this.A).pC(this.getMemoryAddress());
         }

         if (this.A instanceof INativeCodeModel) {
            ((INativeCodeModel)this.A).getReferenceManager().unrecordAllReferencesFrom(this.getMemoryAddress());
         }
      }
   }

   public Long kS() {
      return this.getMemoryAddress();
   }

   public Long wS() {
      return this.getMemoryAddress() + this.getMemorySize();
   }
}
