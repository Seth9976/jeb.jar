package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ayg extends aye implements IArrayType {
   @SerId(1)
   aye pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;

   ayg(ayy var1, aye var2, int var3) {
      super(var1, null);
      if (var3 < 0) {
         throw new IllegalArgumentException("An array type must have 0 or more elements");
      } else {
         this.pC(var2, false);
         this.wS(var3, true);
      }
   }

   @Override
   public String getName(boolean var1) {
      return this.pC == null ? Strings.ff("null[%d]", this.A) : Strings.ff("%s[%d]", this.pC.getName(var1), this.A);
   }

   @Override
   public void setName(String var1) {
      this.vP();
      throw new RuntimeException("Cannot set array type name");
   }

   @Override
   public String pC(boolean var1, String var2) {
      if (this.pC == null) {
         return "null[" + this.A + "]";
      } else {
         var2 = Strings.safe(var2) + "[" + this.A + "]";
         return this.pC.pC(var1, var2);
      }
   }

   public aye E() {
      return this.pC;
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

         if (var1 instanceof ays) {
            throw new IllegalArgumentException("Cannot create array of prototypes");
         }

         if (this.pC != null) {
            this.pC.removeListener(this);
         }

         this.pC = var1;
         this.kS = this.pC.getSize();
         this.pC.addListener(this);
         if (var2) {
            this.pC(NativeItemEventType.MODIFIED);
         }
      }
   }

   @Override
   public int getElementCount() {
      return this.A;
   }

   public void wS(int var1, boolean var2) {
      this.vP();

      try (ACLock var3 = this.gp().a()) {
         if (this.A < 0) {
            throw new IllegalArgumentException();
         }

         if (this.A == var1) {
            return;
         }

         this.A = var1;
         if (var2) {
            this.pC(NativeItemEventType.MODIFIED);
         }
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (this.pC != null && var1.getItem() == this.pC) {
         if (var1.getType().isDeepChange()) {
            this.A = this.A * this.kS;
            this.pC(this.kS().UT("unsigned char"), true);
         } else {
            this.pC(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   public int getSize() {
      return this.A * this.kS;
   }

   @Override
   protected void A() {
      super.A();
      if (this.pC != null) {
         this.pC.removeListener(this);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("ArrayType(%s[%d])", this.E(), this.getElementCount());
   }
}
