package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class bbd extends axj implements INativeType, bbn {
   @SerId(1)
   private bby q;

   protected bbd(bby var1, String var2) {
      super(1073741824, var2);
      if (var1 == null) {
         throw new NullPointerException("Type item needs to be managed by a type manager");
      } else {
         this.q = var1;
         this.q(var1.oW());
      }
   }

   @Override
   public synchronized void addListener(INativeItemListener var1) {
      if (!this.q.Uv() || !(var1 instanceof bbd) || ((bbd)var1).q != this.q) {
         super.addListener(var1);
      }
   }

   @Override
   protected final void c_() {
      super.c_();
      this.q.q((INativeType)this);
   }

   @Override
   public final String getAddress(boolean var1) {
      return this.getSignature(var1);
   }

   @Override
   public String getSignature(boolean var1) {
      return this.q(var1, null);
   }

   public abstract String q(boolean var1, String var2);

   @Override
   public bby xK() {
      return this.q;
   }

   public axl Dw() {
      return null;
   }

   @Override
   public abstract int getSize();

   public bbt Uv() {
      return (bbt)this.q.createReference(this);
   }
}
