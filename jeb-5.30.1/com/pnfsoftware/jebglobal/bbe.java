package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IAliasType;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bbe extends bbx implements IAliasType {
   @SerId(1)
   bbd q;

   bbe(bby var1, String var2, String var3, bbd var4) {
      super(var1, var2, var3);
      this.q(var4, false);
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (this.q != null && var1.getItem() == this.q) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            this.PV();
         } else {
            this.q(var1.getType());
         }
      }
   }

   @Override
   protected void RF() {
      super.RF();
      if (this.q != null) {
         this.q.removeListener(this);
      }
   }

   @Override
   public int getSize() {
      return this.q == null ? 0 : this.q.getSize();
   }

   public void q(bbd var1, boolean var2) {
      this.Me();

      try (ACLock var3 = this.za().a()) {
         if (this.q != null && var1 == null) {
            throw new IllegalArgumentException();
         }

         if (this.q == var1) {
            return;
         }

         if (this.q != null) {
            this.q.removeListener(this);
         }

         this.q = var1;
         this.q.addListener(this);
         if (var2) {
            this.q(NativeItemEventType.MODIFIED);
         }
      }
   }

   public bbd oW() {
      return this.q;
   }

   @Override
   public String toString() {
      return Strings.ff("AliasType({%s}->{%s})", this.getSignature(false), this.oW());
   }
}
