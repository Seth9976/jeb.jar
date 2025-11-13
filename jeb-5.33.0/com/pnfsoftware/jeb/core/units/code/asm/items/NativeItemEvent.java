package com.pnfsoftware.jeb.core.units.code.asm.items;

public class NativeItemEvent {
   NativeItemEventType type;
   INativeItem item;
   NativeItemEventSubType subtype;
   Object details;

   public NativeItemEvent(NativeItemEventType var1, INativeItem var2, NativeItemEventSubType var3, Object var4) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.type = var1;
         if (var2 == null) {
            throw new NullPointerException();
         } else {
            this.item = var2;
            this.subtype = var3;
            this.details = var4;
         }
      }
   }

   public NativeItemEvent(NativeItemEventType var1, INativeItem var2) {
      this(var1, var2, null, null);
   }

   public NativeItemEventType getType() {
      return this.type;
   }

   public INativeItem getItem() {
      return this.item;
   }

   public NativeItemEventSubType getSubtype() {
      return this.subtype;
   }

   public Object getDetails() {
      return this.details;
   }

   @Override
   public String toString() {
      String var1 = this.getType() + "[item=" + this.getItem();
      if (this.getSubtype() != null) {
         var1 = var1 + ",subtype=" + this.getSubtype();
      }

      if (this.getDetails() != null) {
         var1 = var1 + ",details=" + this.getDetails();
      }

      return var1 + "]";
   }
}
