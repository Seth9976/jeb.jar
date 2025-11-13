package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IAliasType;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ayf extends ayx implements IAliasType {
   @SerId(1)
   aye pC;

   ayf(ayy var1, String var2, String var3, aye var4) {
      super(var1, var2, var3);
      this.pC(var4, false);
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (this.pC != null && var1.getItem() == this.pC) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            this.xC();
         } else {
            this.pC(var1.getType());
         }
      }
   }

   @Override
   protected void A() {
      super.A();
      if (this.pC != null) {
         this.pC.removeListener(this);
      }
   }

   @Override
   public int getSize() {
      return this.pC == null ? 0 : this.pC.getSize();
   }

   public void pC(aye var1, boolean var2) {
      this.vP();

      try (ACLock var3 = this.gp().a()) {
         if (this.pC != null && var1 == null) {
            throw new IllegalArgumentException();
         }

         if (this.pC == var1) {
            return;
         }

         if (this.pC != null) {
            this.pC.removeListener(this);
         }

         this.pC = var1;
         this.pC.addListener(this);
         if (var2) {
            this.pC(NativeItemEventType.MODIFIED);
         }
      }
   }

   public aye E() {
      return this.pC;
   }

   @Override
   public String toString() {
      return Strings.ff("AliasType({%s}->{%s})", this.getSignature(false), this.E());
   }
}
