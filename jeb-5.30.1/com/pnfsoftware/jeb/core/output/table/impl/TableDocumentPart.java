package com.pnfsoftware.jeb.core.output.table.impl;

import com.pnfsoftware.jeb.core.output.table.ITableDocumentPart;
import java.util.List;

public class TableDocumentPart implements ITableDocumentPart {
   private int firstRowIndex;
   private List rows;

   public TableDocumentPart(int var1, List var2) {
      this.firstRowIndex = var1;
      this.rows = var2;
   }

   @Override
   public int getFirstRowIndex() {
      return this.firstRowIndex;
   }

   @Override
   public List getRows() {
      return this.rows;
   }
}
