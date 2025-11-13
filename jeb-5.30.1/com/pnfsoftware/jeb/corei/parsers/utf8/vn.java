package com.pnfsoftware.jeb.corei.parsers.utf8;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.output.table.impl.Cell;
import com.pnfsoftware.jeb.core.output.table.impl.StaticTableDocument;
import com.pnfsoftware.jeb.core.output.table.impl.TableRow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

class vn extends AbstractTransientUnitRepresentation {
   vn(tw var1, String var2, boolean var3) {
      super(var2, var3);
      this.q = var1;
   }

   @Override
   public IGenericDocument createDocument() {
      ArrayList var1 = new ArrayList();
      Map var2 = this.q.RF();

      for (String var4 : var2.keySet()) {
         int var5 = (Integer)var2.get(var4);
         var1.add(new TableRow(new Cell(var4), new Cell(var5 + "")));
      }

      return new StaticTableDocument(Arrays.asList(S.L("Value"), S.L("Count")), var1);
   }
}
