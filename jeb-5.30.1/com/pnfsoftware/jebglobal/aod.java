package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;

class aod implements IEVisitor {
   aod(aoc var1) {
      this.q = var1;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEMem var4) {
         int var5 = var4.getBitsize();
         IEGeneric var6 = var4.getReference();
         if (var6 instanceof IEVar && ((IEVar)var6).isGlobalReference()) {
            IEVar var7 = (IEVar)var6;
            Long var8 = var7.getAddress();
            if (var8 != null) {
               IEVar var9 = this.q.q.getGlobalContext().getGlobalVariable(var8);
               if (var9 == null) {
                  INativeContinuousItem var10 = this.q.q.getNativeContext().getNativeItemAt(var8);
                  if (var10 instanceof INativeDataItem && var10.getMemorySize() * 8L == var5) {
                     var9 = this.q.q.getGlobalContext().createGlobalVariable(var8, var5);
                     ((amy)var9).q(var10.getName());
                     INativeType var11 = ((INativeDataItem)var10).getType();
                     if (var11 != null) {
                        var9.setType(this.q.q.getWildcardTypeManager().create(var11));
                     }
                  }
               }

               if (var9 != null && var9.getBitsize() == var5 && var2.replaceSubExpression(var1, var9)) {
                  this.q.RF++;
               }
            }
         }
      }
   }
}
