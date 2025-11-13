package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.output.code.coordinates.ClassCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.units.code.ICodeHierarchy;

public class yt implements ICodeHierarchy {
   private vi pC;

   public yt(vi var1) {
      this.pC = var1;
   }

   public HE pC() {
      return this.pC.ld().ys();
   }

   public HE pC(String var1, boolean var2) {
      ICodeCoordinates var3 = this.pC.A().pC(var1);
      if (var3 == null) {
         return null;
      } else {
         Object var4 = null;
         if (var3 instanceof ClassCoordinates) {
            int var5 = ((ClassCoordinates)var3).getClassId();
            var4 = this.pC.ys(var5);
         } else if (var3 instanceof FieldCoordinates) {
            int var6 = ((FieldCoordinates)var3).getFieldId();
            var4 = this.pC.E(var6);
         } else if (var3 instanceof MethodCoordinates) {
            int var7 = ((MethodCoordinates)var3).getMethodId();
            var4 = this.pC.sY(var7);
         } else if (var3 instanceof InstructionCoordinates && var2) {
            int var8 = ((InstructionCoordinates)var3).getMethodId();
            var4 = this.pC.sY(var8);
         }

         return var4 == null ? null : this.pC.ld().pC((Sv)var4);
      }
   }
}
