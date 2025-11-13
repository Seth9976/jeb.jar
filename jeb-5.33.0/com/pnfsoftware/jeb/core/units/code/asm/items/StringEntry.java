package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;

public class StringEntry {
   String str;
   long address;
   int size;
   StringEncoding encoding;
   INativeStringItem item;

   public StringEntry(String var1, long var2, int var4, StringEncoding var5, INativeStringItem var6) {
      this.str = var1;
      this.address = var2;
      this.size = var4;
      this.encoding = var5;
   }

   public long getAddress() {
      return this.address;
   }

   public int getMemorySize() {
      return this.size;
   }

   public String getString() {
      return this.str;
   }

   public StringEncoding getEncoding() {
      return this.encoding;
   }

   public INativeStringItem getItem() {
      return this.item;
   }

   @Override
   public String toString() {
      return this.str;
   }
}
