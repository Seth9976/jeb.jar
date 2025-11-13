package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.tree.ICodeNode;
import com.pnfsoftware.jeb.core.units.code.ICodeHierarchy;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ayk implements ICodeHierarchy {
   @SerId(1)
   C pC;

   public ayk(C var1) {
      this.pC = var1;
   }

   public ayo pC() {
      return this.pC.A().ld();
   }

   @Override
   public ICodeNode findNode(String var1, boolean var2) {
      long var3 = this.pC.getCanonicalMemoryAddress(var1);
      if (var3 == -1L) {
         return null;
      } else {
         auu var5 = this.pC.pC(var3, !var2);
         return var5 == null ? null : this.pC.A().pC((auo)var5);
      }
   }
}
