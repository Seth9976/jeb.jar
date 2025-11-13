package com.pnfsoftware.jeb.corei.parsers.utf8;

import com.pnfsoftware.jeb.core.output.text.IAnchor;
import com.pnfsoftware.jeb.core.output.text.ILine;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import java.util.List;

public class ej implements ITextDocumentPart {
   private List q;
   private List RF;

   public ej(List var1, List var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public List getAnchors() {
      return this.q;
   }

   @Override
   public IAnchor getAnchor(int var1) {
      return (IAnchor)this.q.get(var1);
   }

   @Override
   public int getCountOfAnchors() {
      return this.q.size();
   }

   @Override
   public List getLines() {
      return this.RF;
   }

   @Override
   public ILine getLine(int var1) {
      return (ILine)this.RF.get(var1);
   }

   @Override
   public int getCountOfLines() {
      return this.RF.size();
   }
}
