package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class aye extends auo implements INativeType, ayn {
   @SerId(1)
   private ayy pC;

   protected aye(ayy var1, String var2) {
      super(1073741824, var2);
      if (var1 == null) {
         throw new NullPointerException("Type item needs to be managed by a type manager");
      } else {
         this.pC = var1;
         this.pC(var1.wS());
      }
   }

   @Override
   public synchronized void addListener(INativeItemListener var1) {
      if (!this.pC.kS() || !(var1 instanceof aye) || ((aye)var1).pC != this.pC) {
         super.addListener(var1);
      }
   }

   @Override
   protected final void c_() {
      super.c_();
      this.pC.pC((INativeType)this);
   }

   @Override
   public final String getAddress(boolean var1) {
      return this.getSignature(var1);
   }

   @Override
   public String getSignature(boolean var1) {
      return this.pC(var1, null);
   }

   public abstract String pC(boolean var1, String var2);

   @Override
   public ayy kS() {
      return this.pC;
   }

   public auq wS() {
      return null;
   }

   @Override
   public abstract int getSize();

   public ayt UT() {
      return (ayt)this.pC.createReference(this);
   }
}
