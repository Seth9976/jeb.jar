package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.table.ITableDocumentPart;
import com.pnfsoftware.jeb.core.output.table.impl.AbstractTableDocument;
import com.pnfsoftware.jeb.core.output.table.impl.Cell;
import com.pnfsoftware.jeb.core.output.table.impl.TableDocumentPart;
import com.pnfsoftware.jeb.core.output.table.impl.TableRow;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SerDisabled
public class qV extends AbstractTableDocument {
   private IPECOFFUnit q;
   private TableDocumentPart RF;

   public qV(vn var1) {
      this.q = var1;
      ArrayList var2 = new ArrayList();

      for (iZ var6 : var1.xK) {
         ArrayList var7 = new ArrayList();
         var7.add(new Cell(Strings.decodeASCII(var6.RF)));
         var7.add(new Cell(Strings.ff("%Xh", var6.xK)));
         var7.add(new Cell(Strings.ff("%Xh", var6.Dw)));
         var7.add(new Cell(Strings.ff("%Xh", var6.Uv)));
         var7.add(new Cell(Strings.ff("%Xh", var6.oW)));
         var7.add(new Cell(Strings.ff("%Xh", var6.lm)));
         var2.add(new TableRow(var7));
      }

      this.RF = new TableDocumentPart(0, var2);
   }

   @Override
   public IUnit getUnit() {
      return this.q;
   }

   @Override
   public int getRowCount() {
      return this.RF.getRows().size();
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
      return this.RF;
   }
}
