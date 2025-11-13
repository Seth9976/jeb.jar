package com.pnfsoftware.jeb.core.output.table.impl;

import com.pnfsoftware.jeb.core.output.table.ICellCoordinates;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class CellCoordinates implements ICellCoordinates {
   @SerId(1)
   private int rowIndex;
   @SerId(2)
   private int columnIndex;

   public CellCoordinates(int var1, int var2) {
      this.rowIndex = var1;
      this.columnIndex = var2;
   }

   @Override
   public int getRowIndex() {
      return this.rowIndex;
   }

   @Override
   public int getColumnIndex() {
      return this.columnIndex;
   }

   @Override
   public String toString() {
      return Strings.ff("%d,%d", this.rowIndex, this.columnIndex);
   }
}
