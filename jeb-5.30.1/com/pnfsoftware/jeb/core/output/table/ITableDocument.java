package com.pnfsoftware.jeb.core.output.table;

import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.util.events.IEventSource;
import java.util.List;

public interface ITableDocument extends IGenericDocument, IEventSource {
   int getRowCount();

   List getColumnLabels();

   ITableDocumentPart getTablePart(int var1, int var2);

   ITableDocumentPart getTable();

   String coordinatesToAddress(ICellCoordinates var1);

   ICellCoordinates addressToCoordinates(String var1);
}
