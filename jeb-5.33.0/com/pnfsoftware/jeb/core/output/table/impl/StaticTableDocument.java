package com.pnfsoftware.jeb.core.output.table.impl;

import com.pnfsoftware.jeb.core.output.table.ITableDocumentPart;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class StaticTableDocument extends AbstractTableDocument {
   @SerId(1)
   private List columnLabels;
   @SerId(2)
   private List rows;

   public StaticTableDocument(List var1, List var2) {
      if (var1 == null || var1.isEmpty()) {
         throw new IllegalArgumentException("Null or empty column labels");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Null rows");
      } else {
         this.columnLabels = new ArrayList(var1);
         this.rows = new ArrayList(var2);
      }
   }

   @Override
   public int getRowCount() {
      return this.rows.size();
   }

   @Override
   public List getColumnLabels() {
      return this.columnLabels;
   }

   @Override
   public ITableDocumentPart getTable() {
      return this.getTablePart(0, this.getRowCount());
   }

   @Override
   public ITableDocumentPart getTablePart(int var1, int var2) {
      if (var1 < 0) {
         var1 = 0;
      }

      if (var1 >= this.getRowCount()) {
         var1 = this.getRowCount();
      }

      int var3 = var1 + var2;
      if (var3 < 0) {
         var3 = 0;
      }

      if (var3 >= this.getRowCount()) {
         var3 = this.getRowCount();
      }

      List var4 = this.rows.subList(var1, var3);
      return new TableDocumentPart(var1, var4);
   }
}
