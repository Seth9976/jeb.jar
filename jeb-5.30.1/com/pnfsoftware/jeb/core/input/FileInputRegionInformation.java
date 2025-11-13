package com.pnfsoftware.jeb.core.input;

import com.pnfsoftware.jeb.util.format.Strings;

public class FileInputRegionInformation implements IInputLocation {
   private long offset;
   private long size;
   private IInput input;

   public FileInputRegionInformation(long var1) {
      this(var1, 0L);
   }

   public FileInputRegionInformation(long var1, long var3) {
      this(var1, var3, null);
   }

   public FileInputRegionInformation(long var1, long var3, IInput var5) {
      if (var1 < 0L) {
         throw new IllegalArgumentException(Strings.ff("Offset cannot be negative (%d)", var1));
      } else {
         this.offset = var1;
         this.size = var3;
         this.input = var5;
      }
   }

   public long getOffset() {
      return this.offset;
   }

   public long getSize() {
      return this.size;
   }

   public IInput getInput() {
      return this.input;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "@%X", this.offset);
      if (this.size > 0L) {
         Strings.ff(var1, ",+%X", this.size);
      } else if (this.size < 0L) {
         Strings.ff(var1, ",-%X", -this.size);
      }

      if (this.input != null) {
         String var2 = this.input.getName();
         if (var2 != null) {
            Strings.ff(var1, "[%s]", var2);
         }
      }

      return var1.toString();
   }
}
