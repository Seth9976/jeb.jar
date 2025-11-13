package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.core.output.text.ILine;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class StaticTextDocument extends AbstractTextDocument {
   @SerId(1)
   private List lines;

   public StaticTextDocument(List var1) {
      this.lines = new ArrayList(var1);
   }

   public StaticTextDocument(String var1) {
      this.lines = new ArrayList();

      for (String var5 : Strings.splitLines(var1)) {
         this.lines.add(new Line(var5));
      }
   }

   @Override
   public long getAnchorCount() {
      return this.lines.size();
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      long var5 = var1 - var4;
      if (var5 < 0L) {
         var5 = 0L;
      }

      long var7 = var1 + var3;
      if (var7 > this.lines.size()) {
         var7 = this.lines.size();
      }

      int var9 = 0;
      ArrayList var10 = new ArrayList();

      for (long var12 = var5; var12 < var7; var12++) {
         var10.add(new Anchor(var12, var9));
         var9++;
      }

      List var11 = this.lines.subList((int)var5, (int)var7);
      return new TextDocumentPart(var11, var10);
   }

   @Override
   protected boolean useLineDelta() {
      return false;
   }

   @Override
   protected boolean useDisplayLineNumber() {
      return super.useDisplayLineNumber();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (ILine var3 : this.lines) {
         var1.append(var3.toString());
      }

      return var1.toString();
   }
}
