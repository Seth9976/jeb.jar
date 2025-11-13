package com.pnfsoftware.jeb.corei.parsers.utf8;

import com.pnfsoftware.jeb.core.output.text.IAnchor;
import com.pnfsoftware.jeb.core.output.text.ILine;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import java.util.List;

public class Ws implements ITextDocumentPart {
   private List pC;
   private List A;

   public Ws(List var1, List var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public List getAnchors() {
      return this.pC;
   }

   @Override
   public IAnchor getAnchor(int var1) {
      return (IAnchor)this.pC.get(var1);
   }

   @Override
   public int getCountOfAnchors() {
      return this.pC.size();
   }

   @Override
   public List getLines() {
      return this.A;
   }

   @Override
   public ILine getLine(int var1) {
      return (ILine)this.A.get(var1);
   }

   @Override
   public int getCountOfLines() {
      return this.A.size();
   }
}
