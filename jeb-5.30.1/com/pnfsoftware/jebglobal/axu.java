package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventSubType;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class axu extends axh {
   @SerId(1)
   private INativeMethodItem Dw;
   @SerId(2)
   private String Uv;
   @SerId(3)
   private bby zz;

   axu(bby var1, long var2, long var4, INativeMethodItem var6, String var7) {
      super(var2, var4);
      this.zz = var1;
      if (var6 != null) {
         this.q(var6);
      } else {
         if (var7 == null) {
            throw new IllegalArgumentException("A reference or a name is required to build a routine-reference item");
         }

         this.Uv = var7;
      }
   }

   @Override
   protected void RF() {
      super.RF();
      if (this.Dw != null) {
         this.Dw.removeListener(this);
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (this.Dw != null && var1.getItem() == this.Dw) {
         if (var1.getType() == NativeItemEventType.DISPOSED && !this.Dw.isPlaceholderMethod()) {
            this.PV();
         } else if (var1.getType() == NativeItemEventType.UPDATED && var1.getSubtype() == NativeItemEventSubType.ROUTINE_SET) {
            this.Dw.removeListener(this);
            this.Dw = (axp)var1.getDetails();
            this.Dw.addListener(this);
         } else {
            this.q(NativeItemEventType.UPDATED);
         }
      }
   }

   public void q(INativeMethodItem var1) {
      if (this.Dw != null) {
         throw new IllegalStateException();
      } else {
         this.Dw = var1;
         this.Uv = null;
         if (this.Dw != null) {
            this.Dw.addListener(this);
         }
      }
   }

   public INativeMethodItem cC() {
      return this.Dw;
   }

   public String sH() {
      return this.Dw == null ? this.Uv : this.Dw.getName(true);
   }

   public bbt CE() {
      return this.Dw == null ? this.zz.gP() : (bbt)this.Dw.getFunctionPointer();
   }

   @Override
   public String toString() {
      return Strings.ff("RoutineRefItem@%X(type:%s,routine:%s,name:%s)", this.getMemoryAddress(), this.CE(), this.cC(), this.sH());
   }
}
