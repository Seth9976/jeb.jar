package com.pnfsoftware.jeb.util.primitives;

import java.util.Iterator;

class Integers$1 implements Iterable {
   Integers$1(int var1, int var2, int var3) {
      this.val$start = var1;
      this.val$end = var2;
      this.val$step = var3;
   }

   @Override
   public Iterator iterator() {
      return new Integers.It(this.val$start, this.val$end, this.val$step);
   }
}
