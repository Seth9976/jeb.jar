package com.pnfsoftware.jeb.core.units.code.asm.memory;

public class MemoryEvent {
   long address;
   int size;
   int protection;

   public MemoryEvent(long var1, int var3, int var4) {
      this.address = var1;
      this.size = var3;
      this.protection = var4;
   }

   public long getAddress() {
      return this.address;
   }

   public int getSize() {
      return this.size;
   }

   public int getProtection() {
      return this.protection;
   }
}
