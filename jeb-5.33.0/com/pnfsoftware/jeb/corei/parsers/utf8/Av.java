package com.pnfsoftware.jeb.corei.parsers.utf8;

import com.pnfsoftware.jeb.core.events.JebEventSource;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.table.ICellCoordinates;
import com.pnfsoftware.jeb.core.output.table.ITableDocument;
import com.pnfsoftware.jeb.core.output.table.impl.Cell;
import com.pnfsoftware.jeb.core.output.table.impl.TableDocumentPart;
import com.pnfsoftware.jeb.core.output.table.impl.TableRow;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

@SerDisabled
public class Av extends JebEventSource implements ITableDocument {
   private TreeMap pC;

   public Av(rQ var1) {
      this.pC = new TreeMap(var1.A());
   }

   @Override
   public void dispose() {
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return null;
   }

   @Override
   public List getColumnLabels() {
      return Arrays.asList("Value", "Count");
   }

   @Override
   public int getRowCount() {
      return this.pC.size();
   }

   public TableDocumentPart pC() {
      return this.pC(0, this.getRowCount());
   }

   public TableDocumentPart pC(int var1, int var2) {
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

      int var4 = var3 - var1;
      Iterator var5 = this.pC.keySet().iterator();

      while (var1-- > 0) {
         var5.next();
      }

      ArrayList var6 = new ArrayList();

      while (var4-- > 0) {
         String var7 = (String)var5.next();
         int var8 = (Integer)this.pC.get(var7);
         ItemClassIdentifiers var9 = var7.length() != 4 ? null : ItemClassIdentifiers.INFO_WARNING;
         long var10 = var9 == null ? 0L : 1L;
         List var12 = Arrays.asList(new Cell(var7, var9, var10, 0), new Cell(Integer.toString(var8)));
         var6.add(new TableRow(var12));
      }

      return new TableDocumentPart(var1, var6);
   }

   @Override
   public String coordinatesToAddress(ICellCoordinates var1) {
      return null;
   }

   @Override
   public ICellCoordinates addressToCoordinates(String var1) {
      return null;
   }
}
