package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import java.util.HashSet;
import java.util.Set;

public class bfm {
   com.pnfsoftware.jeb.corei.parsers.dex.vi pC;
   bev A;
   bfj kS;
   bfn wS;
   Set UT = new HashSet();
   Set E = new HashSet();
   bfm.Av sY = new bfm.Av();

   bfm(bev var1, com.pnfsoftware.jeb.corei.parsers.dex.vi var2, bfj var3) {
      this.A = var1;
      this.pC = var2;
      this.kS = var3;
   }

   void pC(bfn var1) {
      this.wS = var1;
   }

   void pC(int var1, boolean var2) {
      if (var2) {
         if (!this.UT.add(var1)) {
            this.sY.pC.add(var1);
         }
      } else {
         this.E.add(var1);
      }
   }

   bfm.Av pC() {
      for (int var2 : this.E) {
         if (!this.UT.contains(var2)) {
            this.sY.kS.add(var2);
         }
      }

      for (int var4 : this.UT) {
         if (!this.E.contains(var4)) {
            this.sY.A.add(var4);
         }
      }

      return this.sY;
   }

   void pC(bev var1, int var2, boolean var3) {
      InstructionCoordinates var4 = new InstructionCoordinates(var1.pC(), var2);
      String var5 = this.pC.pC((ICodeCoordinates)var4);
      if (var5 == null) {
         if (this.wS != null) {
            bfn.Av var6 = this.wS.pC(var2);
            if (var6 != null) {
               if (!var6.A.isEmpty()) {
                  var5 = "try_" + bev.kS(var2);
               } else if (!var6.kS.isEmpty()) {
                  var5 = "tryend_" + bev.kS(var2);
               } else if (!var6.wS.isEmpty()) {
                  var5 = "catch_" + bev.kS(var2);
               }
            }
         }

         if (var5 == null) {
            var5 = bev.kS(var2);
         }
      }

      this.kS.appendAndRecord(":" + var5, ItemClassIdentifiers.LABEL, this.pC.pC(var4), var3 ? 1 : 0);
      this.pC(var2, var3);
   }

   static class Av {
      Set pC = new HashSet();
      Set A = new HashSet();
      Set kS = new HashSet();
   }
}
