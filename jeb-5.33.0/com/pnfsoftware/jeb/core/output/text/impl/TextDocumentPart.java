package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.core.output.text.IAnchor;
import com.pnfsoftware.jeb.core.output.text.ILine;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import java.util.Arrays;
import java.util.List;

public class TextDocumentPart implements ITextDocumentPart {
   private List lines;
   private List anchors;
   public static final TextDocumentPart EMPTY = new TextDocumentPart(Arrays.asList(ILine.EMPTY_LINE), Arrays.asList(new Anchor(0L, 0)));

   public TextDocumentPart(List var1, List var2) {
      this.lines = var1;
      this.anchors = var2;
   }

   @Override
   public List getLines() {
      return this.lines;
   }

   @Override
   public ILine getLine(int var1) {
      return (ILine)this.lines.get(var1);
   }

   @Override
   public int getCountOfLines() {
      return this.lines.size();
   }

   @Override
   public List getAnchors() {
      return this.anchors;
   }

   @Override
   public IAnchor getAnchor(int var1) {
      return (IAnchor)this.anchors.get(var1);
   }

   @Override
   public int getCountOfAnchors() {
      return this.anchors.size();
   }
}
