package com.pnfsoftware.jeb.util.encoding.zip.fsr;

import java.util.Iterator;

class ZipFailSafeReader$1 implements Iterable {
   ZipFailSafeReader$1(ZipFailSafeReader var1) {
      this.this$0 = var1;
   }

   @Override
   public Iterator iterator() {
      return this.this$0.new ZipEntriesIterator();
   }
}
