package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.table.ICell;
import com.pnfsoftware.jeb.core.output.table.ICellCoordinates;
import com.pnfsoftware.jeb.core.output.table.ITableDocumentPart;
import com.pnfsoftware.jeb.core.output.table.ITableRow;
import com.pnfsoftware.jeb.core.output.table.impl.AbstractTableDocument;
import com.pnfsoftware.jeb.core.output.table.impl.Cell;
import com.pnfsoftware.jeb.core.output.table.impl.CellCoordinates;
import com.pnfsoftware.jeb.core.output.table.impl.TableDocumentPart;
import com.pnfsoftware.jeb.core.output.table.impl.TableRow;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.IELFProgramEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SerDisabled
public class PY extends AbstractTableDocument {
   private IELFUnit q;
   private TableDocumentPart RF;

   public PY(IELFUnit var1) {
      this.q = var1;
      ArrayList var2 = new ArrayList();

      for (IELFProgramEntry var4 : var1.getProgramEntries()) {
         ArrayList var5 = new ArrayList();
         var5.add(new Cell(ELF.getPTString(var4.getType(), var1.getHeader().getMachine())));
         var5.add(new Cell(Strings.ff("%Xh", var4.getVirtualSize())));
         var5.add(new Cell(Strings.ff("%Xh", var4.getVirtualAddress())));
         var5.add(new Cell(Strings.ff("%Xh", var4.getSize())));
         var5.add(new Cell(Strings.ff("%Xh", var4.getOffset())));
         var5.add(new Cell(Strings.ff("%Xh", var4.getFlags())));
         var2.add(new TableRow(var5));
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
      return Arrays.asList(S.L("Type"), S.L("Memory Size"), S.L("Address"), S.L("File Size"), S.L("Offset"), S.L("Flags"));
   }

   @Override
   public ITableDocumentPart getTablePart(int var1, int var2) {
      return this.getTable();
   }

   @Override
   public ITableDocumentPart getTable() {
      return this.RF;
   }

   @Override
   public String coordinatesToAddress(ICellCoordinates var1) {
      if (var1 == null) {
         return null;
      } else {
         ITableRow var2 = (ITableRow)this.RF.getRows().get(var1.getRowIndex());
         return this.q(var2);
      }
   }

   @Override
   public ICellCoordinates addressToCoordinates(String var1) {
      if (var1 == null) {
         return null;
      } else {
         for (int var2 = 0; var2 < this.getRowCount(); var2++) {
            ITableRow var3 = (ITableRow)this.RF.getRows().get(var2);
            if (var1.equals(this.q(var3))) {
               return new CellCoordinates(var2, 0);
            }
         }

         return super.addressToCoordinates(var1);
      }
   }

   private String q(ITableRow var1) {
      return ((ICell)var1.getCells().get(0)).getLabel() + "@" + ((ICell)var1.getCells().get(2)).getLabel();
   }
}
