package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IOptimizerTarget;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class iZ {
   Vj q;
   IMasterOptimizer RF;
   IOptimizerTarget xK;
   Class Dw;

   public iZ(Vj var1) {
      this.q = var1;
   }

   public abstract IMasterOptimizer q(IOptimizerTarget var1);

   void q() {
      if (!this.q.q()) {
         for (int var1 = this.q.xK(); var1 <= this.q.RF(); var1++) {
            this.q(var1);
         }
      }
   }

   void q(int var1) {
      List var2 = this.q.q(var1);
      if (var2 != null) {
         for (Class var4 : var2) {
            this.q(var1, var4);
         }
      }
   }

   void q(int var1, Class var2) {
      try {
         IOptimizer var3 = (IOptimizer)var2.getConstructor(this.Dw).newInstance(this.xK);
         this.RF.registerOptimizer(var1, var3);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException var4) {
         throw new JebRuntimeException("error when generating opti");
      }
   }
}
