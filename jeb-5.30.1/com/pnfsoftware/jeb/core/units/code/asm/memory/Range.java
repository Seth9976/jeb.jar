package com.pnfsoftware.jeb.core.units.code.asm.memory;

public class Range {
   public long start;
   public long end;
   public int protection;

   public Range(long var1, long var3, int var5) {
      this.start = var1;
      this.end = var3;
      this.protection = var5;
   }
}
