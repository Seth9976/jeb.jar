package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMemoryModel;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMemoryItem;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class axi extends axj implements INativeMemoryItem {
   @SerId(1)
   protected IMemoryModel xK;

   public axi(int var1, String var2) {
      super(var1, var2);
   }

   public IMemoryModel gP() {
      return this.xK;
   }

   public void q(IMemoryModel var1) {
      if (this.xK != null) {
         throw new IllegalStateException("The model was already set");
      } else {
         if (var1 != null) {
            this.xK = var1;
            String var2 = this.RF(true);
            if (var2 != null) {
               var1.getLabelManager().setLabel(this.getMemoryAddress(), var2, true, false, false);
            } else {
               var2 = var1.getLabelManager().getLabel(this.getMemoryAddress(), 0L, AutoLabelPolicy.OFF);
               if (var2 != null) {
                  this.RF(var2);
               }
            }
         }
      }
   }

   @Override
   public String getName(boolean var1) {
      if (this.xK != null && var1) {
         String var2 = this.xK.getLabelManager().getLabel(this, 0L, AutoLabelPolicy.OFF);
         if (var2 != null) {
            return var2;
         }
      }

      return super.getName(var1);
   }

   public String RF(boolean var1) {
      return super.getName(var1);
   }

   @Override
   public void setName(String var1) {
      try (ACLock var2 = this.za().a()) {
         if (this.xK != null) {
            this.xK.getLabelManager().setLabel(this.getMemoryAddress(), var1, true, false, false);
         }

         super.setName(var1);
      }
   }

   public void RF(String var1) {
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
