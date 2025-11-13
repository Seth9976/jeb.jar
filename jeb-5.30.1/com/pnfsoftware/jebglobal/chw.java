package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DExecutionParameters;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.ArrayList;
import java.util.HashMap;

public class chw extends AbstractDOptimizer {
   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public int perform() {
      IDState var1 = this.g.getEmulator();
      var1.pushContext("emu_ctx");
      var1.pushFrame(this.ctx.getMethodSignature());
      boolean var2 = var1.setExceptionHandlingEnabled(true);
      DExecutionParameters var3 = new DExecutionParameters(this.ctx);
      var3.pc = 0;
      boolean var10 = false /* VF: Semaphore variable */;

      IDImm var4;
      label35: {
         try {
            var10 = true;
            var4 = var1.execute(var3);
            var10 = false;
            break label35;
         } catch (Exception var11) {
            var10 = false;
         } finally {
            if (var10) {
               var1.deleteTopContext();
               var1.setExceptionHandlingEnabled(var2);
            }
         }

         byte var6 = 0;
         var1.deleteTopContext();
         var1.setExceptionHandlingEnabled(var2);
         return var6;
      }

      var1.deleteTopContext();
      var1.setExceptionHandlingEnabled(var2);
      if (var4.isRef()) {
         return 0;
      } else {
         IDInstruction var5 = this.ctx.createReturn(var4);
         var5.setOffset(0L);
         ArrayList var13 = new ArrayList();
         var13.add(var5);
         CFG var7 = new CFG(var13, null);
         this.ctx.replaceCFG(var7, new HashMap());
         return 1;
      }
   }
}
