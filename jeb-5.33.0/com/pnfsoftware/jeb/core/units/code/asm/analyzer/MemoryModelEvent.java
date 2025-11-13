package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

public class MemoryModelEvent {
   MemoryModelEventType type;
   IMemoryModel model;
   Object details;

   public MemoryModelEvent(MemoryModelEventType var1, IMemoryModel var2, Object var3) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.type = var1;
         if (var2 == null) {
            throw new NullPointerException();
         } else {
            this.model = var2;
            this.details = var3;
         }
      }
   }

   public MemoryModelEvent(MemoryModelEventType var1, IMemoryModel var2) {
      this(var1, var2, null);
   }

   public MemoryModelEventType getType() {
      return this.type;
   }

   public IMemoryModel getModel() {
      return this.model;
   }

   public Object getDetails() {
      return this.details;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.getType()).append("[item=").append(this.getModel());
      var1.append(",details=").append(this.getDetails()).append("]");
      return var1.toString();
   }
}
