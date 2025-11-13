package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public abstract class bwt {
   private static final ILogger Dw = GlobalLog.getLogger(bwt.class);
   IDMethodContext q;
   CFG RF;
   int xK;

   public bwt(IDMethodContext var1, int var2) {
      this.q = var1;
      this.RF = var1.getCfg();
      this.xK = var2;
   }

   public bwt(IDMethodContext var1) {
      this(var1, Integer.MAX_VALUE);
   }

   protected abstract boolean q(IDCallInfo var1);

   protected void RF(IDCallInfo var1) {
   }

   public int q() {
      int var1 = 0;
      IDInstruction var2 = (IDInstruction)this.RF.getLast().getLast();
      IDInstruction var3 = null;
      bvv var4 = new bvv(this.q);

      label57:
      while (true) {
         for (int var5 = 0; var5 < this.RF.size(); var5++) {
            BasicBlock var6 = this.RF.get(var5);

            for (int var7 = 0; var7 < var6.size(); var7++) {
               IDInstruction var8 = (IDInstruction)var6.get(var7);
               if (var3 != null) {
                  if (var3 != var8) {
                     continue;
                  }

                  var3 = null;
               }

               IDInstruction var9 = null;
               if (var8 != var2) {
                  if (var7 + 1 < var6.size()) {
                     var9 = (IDInstruction)var6.get(var7 + 1);
                  } else {
                     if (var5 + 1 >= this.RF.size()) {
                        com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("unexpected"), this.q.getMethodSignature(), -1);
                        return var1;
                     }

                     var9 = (IDInstruction)this.RF.get(var5 + 1).get(0);
                  }
               }

               if (!var8.visitDepthPost(new bwu(this, var4, var6, var7))) {
                  this.RF.invalidateDataFlowAnalysis();
                  if (++var1 >= this.xK) {
                     return var1;
                  }

                  if (var9 != null) {
                     var3 = var9;
                     continue label57;
                  }
               }

               if (var9 == null) {
                  return var1;
               }
            }
         }

         return var1;
      }
   }
}
