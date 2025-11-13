package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public abstract class bsh {
   private static final ILogger wS = GlobalLog.getLogger(bsh.class);
   IDMethodContext pC;
   CFG A;
   int kS;

   public bsh(IDMethodContext var1, int var2) {
      this.pC = var1;
      this.A = var1.getCfg();
      this.kS = var2;
   }

   public bsh(IDMethodContext var1) {
      this(var1, Integer.MAX_VALUE);
   }

   protected abstract boolean pC(IDCallInfo var1);

   public int pC() {
      int var1 = 0;
      IDInstruction var2 = (IDInstruction)this.A.getLast().getLast();
      IDInstruction var3 = null;
      brm var4 = new brm(this.pC);

      label57:
      while (true) {
         for (int var5 = 0; var5 < this.A.size(); var5++) {
            BasicBlock var6 = this.A.get(var5);

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
                     if (var5 + 1 >= this.A.size()) {
                        com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("unexpected"), this.pC.getMethodSignature(), -1);
                        return var1;
                     }

                     var9 = (IDInstruction)this.A.get(var5 + 1).get(0);
                  }
               }

               if (!var8.visitDepthPost(new bsi(this, var4, var6, var7))) {
                  this.A.invalidateDataFlowAnalysis();
                  if (++var1 >= this.kS) {
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
