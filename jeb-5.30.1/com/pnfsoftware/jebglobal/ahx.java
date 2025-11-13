package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CSimulationException;
import java.util.Collection;
import java.util.List;

public class ahx extends aid implements ahh, ahi {
   ICExpression RF;

   public ahx(ICSwitchStm var1, ICExpression var2) {
      super(aid.eo.oW, aid.CU.q);
      this.oW = var1;
      this.RF = var2;
   }

   public ICExpression RF() {
      return this.RF;
   }

   @Override
   public ICStatement q(List var1, int var2, int[] var3) {
      ICStatement var4 = (ICStatement)var1.get(var2++);
      if (!(var4 instanceof ahx)) {
         throw new RuntimeException();
      } else {
         ICExpression var5 = ((ahx)var4).RF();
         int[] var6 = new int[1];
         ICSwitchStm var8 = (ICSwitchStm)this.oW;
         var8.reset();
         var8.setSwitchedExpression(var5);

         while (true) {
            var4 = (ICStatement)var1.get(var2++);
            if (var4 instanceof aia) {
               var3[0] = var2;
               return var8;
            }

            if (var4 instanceof ahy) {
               Collection var9 = ((ahy)var4).RF();
               var4 = (ICStatement)var1.get(var2);
               if (!(var4 instanceof ahl)) {
                  throw new RuntimeException();
               }

               ICStatement var7 = ((ahl)var4).q(var1, var2, var6);
               var2 = var6[0];
               var8.addCase(var9, (ICBlock)var7);
            } else {
               if (!(var4 instanceof ahz)) {
                  throw new RuntimeException();
               }

               var4 = (ICStatement)var1.get(var2);
               if (!(var4 instanceof ahl)) {
                  throw new RuntimeException();
               }

               ICStatement var15 = ((ahl)var4).q(var1, var2, var6);
               var2 = var6[0];
               var8.setDefaultBlock((ICBlock)var15);
            }
         }
      }
   }

   @Override
   public String toString() {
      return "switch: " + this.RF;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      Long var3 = this.RF.evaluate(var1, var2);
      if (var3 == null) {
         throw new CSimulationException("switch value eval");
      } else {
         return var3;
      }
   }
}
