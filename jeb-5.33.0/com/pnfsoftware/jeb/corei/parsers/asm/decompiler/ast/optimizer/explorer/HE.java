package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashSet;
import java.util.List;

public class HE implements K {
   private static final ILogger pC = GlobalLog.getLogger(HE.class);
   private final RC.Sv A = RC.Sv.wS;
   private final RC.Av kS;

   public HE(RC.Av var1) {
      this.kS = var1;
   }

   @Override
   public RC.K pC(List var1) {
      RC.K var2 = new RC.K(this.A, var1.size());

      for (cq var4 : var1) {
         HashSet var5 = new HashSet();
         HashSet var6 = new HashSet();
         boolean var7 = false;

         for (cq.Av var9 : var4.kS) {
            RC.Av var10 = var9.A();
            if (var10.equals(this.kS)) {
               if (!var9.pC()) {
                  var7 = true;
               } else {
                  var7 = false;
                  if (!var5.isEmpty()) {
                     var6.addAll(var5);
                     var5.clear();
                  }
               }
            } else if (var7 && var9.pC()) {
               var5.add(var10);
            }
         }

         for (RC.Av var12 : var6) {
            var2.pC(var12);
         }
      }

      return var2;
   }
}
