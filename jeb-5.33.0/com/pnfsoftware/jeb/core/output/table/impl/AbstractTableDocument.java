package com.pnfsoftware.jeb.core.output.table.impl;

import com.pnfsoftware.jeb.core.output.table.ICellCoordinates;
import com.pnfsoftware.jeb.core.output.table.ITableDocument;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.util.events.EventSource;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public abstract class AbstractTableDocument extends EventSource implements ITableDocument {
   @Override
   public void dispose() {
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return null;
   }

   @Override
   public String coordinatesToAddress(ICellCoordinates var1) {
      return var1 == null ? null : Strings.ff("%d,%d", var1.getRowIndex(), var1.getColumnIndex());
   }

   @Override
   public ICellCoordinates addressToCoordinates(String var1) {
      if (var1 == null) {
         return null;
      } else {
         List var2 = parseIntegerList(var1);
         if (var2 == null) {
            return null;
         } else if (var2.size() == 1) {
            return new CellCoordinates((Integer)var2.get(0), 0);
         } else {
            return var2.size() == 2 ? new CellCoordinates((Integer)var2.get(0), (Integer)var2.get(1)) : null;
         }
      }
   }

   static List parseIntegerList(String var0) {
      ArrayList var1 = new ArrayList();

      for (String var5 : var0.split(",")) {
         try {
            int var6 = Integer.parseInt(var5.trim());
            var1.add(var6);
         } catch (NumberFormatException var7) {
            return null;
         }
      }

      return var1;
   }
}
