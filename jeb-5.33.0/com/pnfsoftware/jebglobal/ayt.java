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
public class ayt extends aye implements IReferenceType {
   @SerId(1)
   aye pC;
   @SerId(2)
   int A;

   ayt(ayy var1, aye var2, int var3) {
      super(var1, null);
      if (var3 <= 0) {
         throw new IllegalArgumentException("Reference count must be >= 1");
      } else {
         this.pC(var2, false);
         this.wS(var3, true);
      }
   }

   @Override
   public int getSize() {
      return this.kS().getPointerSize();
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

   @Override
   public INativeType getPointedType() {
      return (INativeType)(this.A == 1 ? this.pC : this.kS().pC((INativeType)this.pC, this.A - 1));
   }

   @Override
   public int getReferenceCount() {
      return this.A;
   }

   public void wS(int var1, boolean var2) {
      this.vP();

      try (ACLock var3 = this.gp().a()) {
         if (var1 <= 0) {
            throw new IllegalArgumentException();
         }

         if (var1 == this.A) {
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
      if (this.pC != null && var1.getItem() == this.pC && var1.getType() == NativeItemEventType.DISPOSED) {
         this.xC();
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
   public String getName(boolean var1) {
      return this.pC == null ? "null" + Strings.generate('*', this.A) : this.pC.getName(var1) + Strings.generate('*', this.A);
   }

   @Override
   public void setName(String var1) {
      this.vP();
      throw new RuntimeException("Cannot set reference type name");
   }

   @Override
   public String pC(boolean var1, String var2) {
      if (this.pC == null) {
         return "null" + Strings.generate('*', this.A);
      } else if (this.pC instanceof ays) {
         return this.pC.pC(var1, "(" + Strings.generate('*', this.A) + Strings.safe(var2, "_") + ")");
      } else {
         return var2 == null ? this.pC.getSignature(var1) + Strings.generate('*', this.A) : this.pC.pC(var1, Strings.generate('*', this.A) + " " + var2);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("RefType(%s%s)", Strings.generate('*', this.A), this.pC);
   }
}
