package com.pnfsoftware.jeb.core.output.table.impl;

import com.pnfsoftware.jeb.core.output.table.ITableRow;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Ser
public class TableRow implements ITableRow {
   @SerId(1)
   private List cells;

   public TableRow(List var1) {
      this.cells = new ArrayList(var1);
   }

   public TableRow(Cell... var1) {
      this.cells = new ArrayList(Arrays.asList(var1));
   }

   @Override
   public List getCells() {
      return this.cells;
   }

   public void setCells(List var1) {
      this.cells = var1;
   }
}
