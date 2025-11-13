package com.pnfsoftware.jeb.core.output.tree;

import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.util.events.IEventSource;
import java.util.List;

public interface ITreeDocument extends IGenericDocument, IEventSource {
   List getRoots();

   List getColumnLabels();

   int getInitialExpansionLevel();

   String coordinatesToAddress(INodeCoordinates var1);

   INodeCoordinates addressToCoordinates(String var1);

   default String getReferencedAddress(INode var1) {
      return null;
   }
}
