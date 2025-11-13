package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class apu implements IEVisitor {
   apu(aps var1, Map var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEMem var4) {
         ano var5 = new ano(var4.getReference());
         if (var5.q() && var5.Uv()) {
            IEVar var6 = var5.xK();
            if (var6 != null && var6.getType() != null && var6.getType().isWildcardPointer() && var6.getType().getPointedBitsize() == 0) {
               Object var7 = (Set)this.q.get(var6);
               if (var7 == null) {
                  var7 = new HashSet();
                  this.q.put(var6, var7);
               }

               int var8 = var4.getBitsize();
               var7.add(var8);
            }
         }
      }
   }
}
