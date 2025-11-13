package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ank implements IEVisitor {
   ank(ani var1, Map var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEMem var4) {
         alj var5 = new alj(var4.getReference());
         if (var5.pC() && var5.UT()) {
            IEVar var6 = var5.kS();
            if (var6 != null && var6.getType() != null && var6.getType().isWildcardPointer() && var6.getType().getPointedBitsize() == 0) {
               Object var7 = (Set)this.pC.get(var6);
               if (var7 == null) {
                  var7 = new HashSet();
                  this.pC.put(var6, var7);
               }

               int var8 = var4.getBitsize();
               var7.add(var8);
            }
         }
      }
   }
}
