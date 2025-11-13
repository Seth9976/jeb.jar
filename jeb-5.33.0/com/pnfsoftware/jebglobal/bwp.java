package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;

public class bwp extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (IDInstruction var3 : this.cfg.instructions()) {
         if (var3.isAssign() && var3.getAssignSource() instanceof IDImm var4) {
            IJavaType var8 = var4.getType();
            if (var8.isInt()) {
               IJavaType var6 = var3.getAssignDestination().getType();
               if (var6.isBoolean() || var6.isByte() || var6.isChar() || var6.isShort()) {
                  IDImm var7 = var4.duplicateWithDifferentType(var6);
                  var3.setAssignSource(var7);
                  var1++;
               }
            }
         }
      }

      return var1;
   }
}
