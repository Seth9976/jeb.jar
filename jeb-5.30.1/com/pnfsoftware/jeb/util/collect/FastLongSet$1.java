package com.pnfsoftware.jeb.util.collect;

import java.util.Iterator;
import java.util.List;

class FastLongSet$1 implements Iterator {
   List list;
   int i;

   FastLongSet$1(FastLongSet var1) {
      this.this$0 = var1;
      this.list = this.this$0.bitmap.ones();
      this.i = 0;
   }

   @Override
   public boolean hasNext() {
      return this.i < this.list.size();
   }

   public Long next() {
      return ((Integer)this.list.get(this.i++)).intValue() + this.this$0.from;
   }
}
