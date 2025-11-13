package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashSet;
import java.util.List;

public class Uz implements Nt {
   private static final ILogger q = GlobalLog.getLogger(Uz.class);
   private final Bu.CU RF = Bu.CU.Dw;
   private final Bu.eo xK;

   public Uz(Bu.eo var1) {
      this.xK = var1;
   }

   @Override
   public Bu.nI q(List var1) {
      Bu.nI var2 = new Bu.nI(this.RF, var1.size());

      for (EE var4 : var1) {
         HashSet var5 = new HashSet();
         HashSet var6 = new HashSet();
         boolean var7 = false;

         for (EE.eo var9 : var4.xK) {
            Bu.eo var10 = var9.RF();
            if (var10.equals(this.xK)) {
               if (!var9.q()) {
                  var7 = true;
               } else {
                  var7 = false;
                  if (!var5.isEmpty()) {
                     var6.addAll(var5);
                     var5.clear();
                  }
               }
            } else if (var7 && var9.q()) {
               var5.add(var10);
            }
         }

         for (Bu.eo var12 : var6) {
            var2.q(var12);
         }
      }

      return var2;
   }
}
