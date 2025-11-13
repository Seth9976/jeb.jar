package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMemoryModel;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMemoryItem;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class aun extends auo implements INativeMemoryItem {
   @SerId(1)
   protected IMemoryModel A;

   public aun(int var1, String var2) {
      super(var1, var2);
   }

   public IMemoryModel ld() {
      return this.A;
   }

   public void pC(IMemoryModel var1) {
      if (this.A != null) {
         throw new IllegalStateException("The model was already set");
      } else {
         if (var1 != null) {
            this.A = var1;
            String var2 = this.A(true);
            if (var2 != null) {
               var1.getLabelManager().setLabel(this.getMemoryAddress(), var2, true, false, false);
            } else {
               var2 = var1.getLabelManager().getLabel(this.getMemoryAddress(), 0L, AutoLabelPolicy.OFF);
               if (var2 != null) {
                  this.A(var2);
               }
            }
         }
      }
   }

   @Override
   public String getName(boolean var1) {
      if (this.A != null && var1) {
         String var2 = this.A.getLabelManager().getLabel(this, 0L, AutoLabelPolicy.OFF);
         if (var2 != null) {
            return var2;
         }
      }

      return super.getName(var1);
   }

   public String A(boolean var1) {
      return super.getName(var1);
   }

   @Override
   public void setName(String var1) {
      try (ACLock var2 = this.gp().a()) {
         if (this.A != null) {
            this.A.getLabelManager().setLabel(this.getMemoryAddress(), var1, true, false, false);
         }

         super.setName(var1);
      }
   }

   public void A(String var1) {
      super.setName(var1);
   }

   @Override
   public final String getAddress(boolean var1) {
      return Strings.ff("%Xh", this.getMemoryAddress());
   }

   @Override
   public final String getLabel() {
      return this.getName(true);
   }
}
