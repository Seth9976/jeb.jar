package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantString;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;

class apr implements IEVisitor {
   apr(apq var1, AddressableInstruction var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEImm var4
         && var4.getBitsize() == this.RF.q.getGlobalContext().getAddressBitsize()
         && var4.canReadAsAddress()
         && this.RF.q(var4, var2, var3, null)) {
         long var5 = var4.getValueAsAddress();
         Object var7 = this.RF.q.getNativeContext().getNativeItemAt(var5);
         if (var7 == null) {
            var7 = this.RF.q.getNativeContext().getNativeItemOver(var5);
            if (var7 instanceof INativeStringItem var8) {
               axw var9 = new axw(
                  var5,
                  ((INativeContinuousItem)var7).getMemorySize() - (var5 - ((INativeContinuousItem)var7).getMemoryAddress()),
                  (bbd)var8.getType(),
                  var8.getStringType(),
                  null
               );
               String var10 = DataStringUtil.determineValue(var9, this.RF.q.getNativeContext().getMemory(), false);
               var9.xK(var10);
               var7 = var9;
            }
         }

         if (var7 instanceof INativeStringItem var14) {
            String var15 = var14.getValue();
            if (var15 != null) {
               ICConstantString var16 = this.RF.RF.createString(var15, ((INativeContinuousItem)var7).getItemId(), var14.getIndex());
               IEImm var11;
               boolean var12;
               if (var4.isMutable()) {
                  var12 = var4.setCustomAST(var16);
                  var11 = var4;
               } else {
                  IEImm var13 = var4.duplicateWithType(null);
                  var12 = var13.setCustomAST(var16) && var2.replaceSubExpression(var4, var13);
                  var11 = var13;
               }

               if (var12) {
                  var11.setType(this.RF.q.getWildcardTypeManager().create("char*"));
                  this.RF.xK.add(new apq.eo((int)this.q.getOffset(), var5, var15));
               }
            }
         }
      }
   }
}
