package com.pnfsoftware.jeb.util.collect;

import java.util.Collection;
import java.util.Iterator;

class CollectionUtil$1 implements Iterable {
   CollectionUtil$1(Collection var1, Collection var2) {
      this.val$coll1 = var1;
      this.val$coll2 = var2;
   }

   @Override
   public Iterator iterator() {
      return new CollectionUtil.DoubleCollIterator(this.val$coll1, this.val$coll2);
   }
}
