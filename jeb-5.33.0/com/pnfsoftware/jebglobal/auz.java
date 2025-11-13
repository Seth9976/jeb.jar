package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventSubType;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class auz extends aum {
   @SerId(1)
   private INativeMethodItem kS;
   @SerId(2)
   private String wS;
   @SerId(3)
   private ayy UT;

   auz(ayy var1, long var2, long var4, INativeMethodItem var6, String var7) {
      super(var2, var4);
      this.UT = var1;
      if (var6 != null) {
         this.pC(var6);
      } else {
         if (var7 == null) {
            throw new IllegalArgumentException("A reference or a name is required to build a routine-reference item");
         }

         this.wS = var7;
      }
   }

   @Override
   protected void A() {
      super.A();
      if (this.kS != null) {
         this.kS.removeListener(this);
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (this.kS != null && var1.getItem() == this.kS) {
         if (var1.getType() == NativeItemEventType.DISPOSED && !this.kS.isPlaceholderMethod()) {
            this.xC();
         } else if (var1.getType() == NativeItemEventType.UPDATED && var1.getSubtype() == NativeItemEventSubType.ROUTINE_SET) {
            this.kS.removeListener(this);
            this.kS = (auu)var1.getDetails();
            this.kS.addListener(this);
         } else {
            this.pC(NativeItemEventType.UPDATED);
         }
      }
   }

   public void pC(INativeMethodItem var1) {
      if (this.kS != null) {
         throw new IllegalStateException();
      } else {
         this.kS = var1;
         this.wS = null;
         if (this.kS != null) {
            this.kS.addListener(this);
         }
      }
   }

   public INativeMethodItem UO() {
      return this.kS;
   }

   public String Ab() {
      return this.kS == null ? this.wS : this.kS.getName(true);
   }

   public ayt rl() {
      return this.kS == null ? this.UT.E() : (ayt)this.kS.getFunctionPointer();
   }

   @Override
   public String toString() {
      return Strings.ff("RoutineRefItem@%X(type:%s,routine:%s,name:%s)", this.getMemoryAddress(), this.rl(), this.UO(), this.Ab());
   }
}
