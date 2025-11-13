package com.pnfsoftware.jeb.core.units.code.asm.memory;

public class Page {
   long address;
   int protection;
   byte[] data;

   public Page(long var1, int var3, byte[] var4) {
      if (var4 == null) {
         throw new IllegalArgumentException();
      } else {
         this.address = var1;
         this.protection = var3;
         this.data = var4;
      }
   }

   public long getAddress() {
      return this.address;
   }

   public int getSize() {
      return this.data.length;
   }

   public int getProtection() {
      return this.protection;
   }

   public byte[] getData() {
      return this.data;
   }
}
