package com.pnfsoftware.jeb.util.primitives;

import java.util.Iterator;

class Longs$1 implements Iterable {
   Longs$1(long var1, long var3, long var5) {
      this.val$start = var1;
      this.val$end = var3;
      this.val$step = var5;
   }

   @Override
   public Iterator iterator() {
      return new Longs.It(this.val$start, this.val$end, this.val$step);
   }
}
