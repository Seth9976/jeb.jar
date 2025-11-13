package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.table.ITableDocumentPart;
import com.pnfsoftware.jeb.core.output.table.impl.AbstractTableDocument;
import com.pnfsoftware.jeb.core.output.table.impl.TableDocumentPart;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.Arrays;
import java.util.List;

@SerDisabled
public class KD extends AbstractTableDocument {
   private IPECOFFUnit pC;
   private TableDocumentPart A;

   @Override
   public IUnit getUnit() {
      return this.pC;
   }

   @Override
   public int getRowCount() {
      return this.A.getRows().size();
   }

   @Override
   public List getColumnLabels() {
      return Arrays.asList(S.L("Name"), S.L("Virtual Size"), S.L("RVA"), S.L("Physical Size"), S.L("Offset"), S.L("Flags"));
   }

   @Override
   public ITableDocumentPart getTablePart(int var1, int var2) {
      return this.getTable();
   }

   @Override
   public ITableDocumentPart getTable() {
      return this.A;
   }
}
