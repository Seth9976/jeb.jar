package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.output.code.coordinates.ClassCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.units.code.ICodeHierarchy;

public class vn implements ICodeHierarchy {
   private bK q;

   public vn(bK var1) {
      this.q = var1;
   }

   public oL q() {
      return this.q.io().za();
   }

   public oL q(String var1, boolean var2) {
      ICodeCoordinates var3 = this.q.xK().q(var1);
      if (var3 == null) {
         return null;
      } else {
         Object var4 = null;
         if (var3 instanceof ClassCoordinates) {
            int var5 = ((ClassCoordinates)var3).getClassId();
            var4 = this.q.nf(var5);
         } else if (var3 instanceof FieldCoordinates) {
            int var6 = ((FieldCoordinates)var3).getFieldId();
            var4 = this.q.oW(var6);
         } else if (var3 instanceof MethodCoordinates) {
            int var7 = ((MethodCoordinates)var3).getMethodId();
            var4 = this.q.gO(var7);
         } else if (var3 instanceof InstructionCoordinates && var2) {
            int var8 = ((InstructionCoordinates)var3).getMethodId();
            var4 = this.q.gO(var8);
         }

         return var4 == null ? null : this.q.io().q((CU)var4);
      }
   }
}
