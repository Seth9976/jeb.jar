package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bbf extends bbd implements IArrayType {
   @SerId(1)
   bbd q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;

   bbf(bby var1, bbd var2, int var3) {
      super(var1, null);
      if (var3 < 0) {
         throw new IllegalArgumentException("An array type must have 0 or more elements");
      } else {
         this.q(var2, false);
         this.Dw(var3, true);
      }
   }

   @Override
   public String getName(boolean var1) {
      return this.q == null ? Strings.ff("null[%d]", this.RF) : Strings.ff("%s[%d]", this.q.getName(var1), this.RF);
   }

   @Override
   public void setName(String var1) {
      this.Me();
      throw new RuntimeException("Cannot set array type name");
   }

   @Override
   public String q(boolean var1, String var2) {
      if (this.q == null) {
         return "null[" + this.RF + "]";
      } else {
         var2 = Strings.safe(var2) + "[" + this.RF + "]";
         return this.q.q(var1, var2);
      }
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

         if (var1 instanceof bbs) {
            throw new IllegalArgumentException("Cannot create array of prototypes");
         }

         if (this.q != null) {
            this.q.removeListener(this);
         }

         this.q = var1;
         this.xK = this.q.getSize();
         this.q.addListener(this);
         if (var2) {
            this.q(NativeItemEventType.MODIFIED);
         }
      }
   }

   @Override
   public int getElementCount() {
      return this.RF;
   }

   public void Dw(int var1, boolean var2) {
      this.Me();

      try (ACLock var3 = this.za().a()) {
         if (this.RF < 0) {
            throw new IllegalArgumentException();
         }

         if (this.RF == var1) {
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
      if (this.q != null && var1.getItem() == this.q) {
         if (var1.getType().isDeepChange()) {
            this.RF = this.RF * this.xK;
            this.q(this.xK().Uv("unsigned char"), true);
         } else {
            this.q(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   public int getSize() {
      return this.RF * this.xK;
   }

   @Override
   protected void RF() {
      super.RF();
      if (this.q != null) {
         this.q.removeListener(this);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("ArrayType(%s[%d])", this.oW(), this.getElementCount());
   }
}
