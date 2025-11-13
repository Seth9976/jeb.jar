package com.pnfsoftware.jeb.core.output.text;

import java.util.Collections;
import java.util.List;

public interface ITextDocumentPart {
   List getLines();

   default int getCountOfLines() {
      return this.getLines().size();
   }

   default ILine getLine(int var1) {
      return (ILine)this.getLines().get(var1);
   }

   List getAnchors();

   default int getCountOfAnchors() {
      return this.getAnchors().size();
   }

   default IAnchor getAnchor(int var1) {
      return (IAnchor)this.getAnchors().get(var1);
   }

   default List getObjectLocations() {
      return Collections.emptyList();
   }
}
