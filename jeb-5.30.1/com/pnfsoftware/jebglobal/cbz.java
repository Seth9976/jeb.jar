package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDAllocObjectInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;

public class cbz extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);
            if (var5.isInvoke()
               && var5.getInvokeData() instanceof IDCallInfo var6
               && var6.getInvokeType() == DInvokeType.DIRECT
               && var6.getMethodName().equals("<init>")) {
               IDExpression var16 = var6.getArgument(0);
               IDAllocObjectInfo var17 = null;
               if (var16 instanceof IDAllocObjectInfo var18) {
                  var17 = var18;
               } else if (var16 instanceof IDOperation var21 && var21.isCast() && var21.getRight() instanceof IDAllocObjectInfo var12) {
                  var17 = var12;
               }

               if (var17 != null) {
                  IDNewInfo var19 = q(this.g, var17, var6);
                  var5.morph(DOpcodeType.IR_INVOKE, null, var19);
                  var1++;
               }
            } else if (var4 < var3.size() - 1 && var5.isAssignToVar() && var5.getAssignSource() instanceof IDAllocObjectInfo var7) {
               IDInstruction var15 = (IDInstruction)var3.get(var4 + 1);
               IDExpression var9 = var5.getAssignDestination();
               if (var15.isInvoke()
                  && var15.getInvokeData() instanceof IDCallInfo var10
                  && var10.getMethodName().equals("<init>")
                  && var10.getInvokeType() == DInvokeType.DIRECT
                  && this.q(var9, var10.getArgument(0))) {
                  IDNewInfo var20 = q(this.g, var7, var10);
                  var5.setAssignSource(var20);
                  var15.transformToNop();
                  var1++;
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   static IDNewInfo q(IDGlobalContext var0, IDAllocObjectInfo var1, IDCallInfo var2) {
      int var3 = var2.getCountOfArguments();
      IDExpression[] var4 = (IDExpression[])var2.getArguments().subList(1, var3).toArray(new IDExpression[var3 - 1]);
      String var5 = var2.getMethodSignature();
      JvmMethodSig var6 = JvmMethodSig.parse(var5);
      return var0.createNewInfo(
         var1.getObjectType(), var0.getTypeFactory().createType(var6.csig), var0.createIndex(var0.getDex().getMethod(var5).getIndex()), var4, var5
      );
   }

   private boolean q(IDExpression var1, IDExpression var2) {
      if (var1.equals(var1)) {
         return true;
      } else if (var2 instanceof IDOperation var3 && var3.isCast()) {
         IDExpression var4 = var3.getRight();
         return var1.equals(var4);
      } else {
         return false;
      }
   }
}
