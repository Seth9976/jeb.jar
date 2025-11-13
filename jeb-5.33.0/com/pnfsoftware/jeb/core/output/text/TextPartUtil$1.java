package com.pnfsoftware.jeb.core.output.text;

import java.util.Iterator;

class TextPartUtil$1 implements Iterable {
   TextPartUtil$1(ITextDocumentPart var1) {
      this.val$part = var1;
   }

   @Override
   public Iterator iterator() {
      return new TextPartUtil.LineIterator(this.val$part);
   }
}
