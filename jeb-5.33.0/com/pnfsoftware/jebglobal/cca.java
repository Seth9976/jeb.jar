package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;

public class cca extends AbstractDOptimizer {
   public cca() {
      this.addTag("deobfuscator");
   }

   @Override
   public int perform() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         int var2 = 0;

         for (BasicBlock var4 : this.cfg) {
            IDInstruction var5 = (IDInstruction)var4.getLast();
            String var6 = null;
            if (var5.isThrow() && var5.getThrowExpression() instanceof IDNewInfo var7) {
               for (IDExpression var9 : var7.getArguments()) {
                  if (var9.hasSideEffects(this.ctx, true)) {
                  }
               }

               String var13 = var7.getMethodSignature();
               var6 = JvmMethodSig.parse(var13).csig;
            }

            if (var6 != null) {
               BasicBlock var11 = null;

               for (IDExceptionHandler var10 : var1.getBlockHandlers((int)var4.getAddress())) {
                  if (DUtil.canHandlerCatchException(this.ctx, var10, var6)) {
                     var11 = this.cfg.getBlockAt((long)var10.getAddress());
                     if (((IDInstruction)var11.get(0)).isStoreException()) {
                        var11 = null;
                     }
                     break;
                  }
               }

               if (var11 != null) {
                  ((IDInstruction)var4.getLast()).transformToJump((int)var11.getAddress());
                  this.cfg.addEdge(var4, var11);
                  var2++;
               }
            }
         }

         if (var2 > 0) {
            this.cleanGraph();
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var2;
      }
   }
}
