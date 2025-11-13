package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bbt extends bbd implements IReferenceType {
   @SerId(1)
   bbd q;
   @SerId(2)
   int RF;

   bbt(bby var1, bbd var2, int var3) {
      super(var1, null);
      if (var3 <= 0) {
         throw new IllegalArgumentException("Reference count must be >= 1");
      } else {
         this.q(var2, false);
         this.Dw(var3, true);
      }
   }

   @Override
   public int getSize() {
      return this.xK().getPointerSize();
   }

   public bbd oW() {
      return this.q;
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

   @Override
   public INativeType getPointedType() {
      return (INativeType)(this.RF == 1 ? this.q : this.xK().q((INativeType)this.q, this.RF - 1));
   }

   @Override
   public int getReferenceCount() {
      return this.RF;
   }

   public void Dw(int var1, boolean var2) {
      this.Me();

      try (ACLock var3 = this.za().a()) {
         if (var1 <= 0) {
            throw new IllegalArgumentException();
         }

         if (var1 == this.RF) {
            return;
         }

         this.RF = var1;
         if (var2) {
            this.q(NativeItemEventType.MODIFIED);
         }
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (this.q != null && var1.getItem() == this.q && var1.getType() == NativeItemEventType.DISPOSED) {
         this.PV();
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
   public String getName(boolean var1) {
      return this.q == null ? "null" + Strings.generate('*', this.RF) : this.q.getName(var1) + Strings.generate('*', this.RF);
   }

   @Override
   public void setName(String var1) {
      this.Me();
      throw new RuntimeException("Cannot set reference type name");
   }

   @Override
   public String q(boolean var1, String var2) {
      if (this.q == null) {
         return "null" + Strings.generate('*', this.RF);
      } else if (this.q instanceof bbs) {
         return this.q.q(var1, "(" + Strings.generate('*', this.RF) + Strings.safe(var2, "_") + ")");
      } else {
         return var2 == null ? this.q.getSignature(var1) + Strings.generate('*', this.RF) : this.q.q(var1, Strings.generate('*', this.RF) + " " + var2);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("RefType(%s%s)", Strings.generate('*', this.RF), this.q);
   }
}
