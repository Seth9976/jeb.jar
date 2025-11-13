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

public class age extends agk implements afo, afp {
   ICExpression A;

   public age(ICSwitchStm var1, ICExpression var2) {
      super(agk.Av.E, agk.Sv.pC);
      this.E = var1;
      this.A = var2;
   }

   public ICExpression A() {
      return this.A;
   }

   @Override
   public ICStatement pC(List var1, int var2, int[] var3) {
      ICStatement var4 = (ICStatement)var1.get(var2++);
      if (!(var4 instanceof age)) {
         throw new RuntimeException();
      } else {
         ICExpression var5 = ((age)var4).A();
         int[] var6 = new int[1];
         ICSwitchStm var8 = (ICSwitchStm)this.E;
         var8.reset();
         var8.setSwitchedExpression(var5);

         while (true) {
            var4 = (ICStatement)var1.get(var2++);
            if (var4 instanceof agh) {
               var3[0] = var2;
               return var8;
            }

            if (var4 instanceof agf) {
               Collection var9 = ((agf)var4).A();
               var4 = (ICStatement)var1.get(var2);
               if (!(var4 instanceof afs)) {
                  throw new RuntimeException();
               }

               ICStatement var7 = ((afs)var4).pC(var1, var2, var6);
               var2 = var6[0];
               var8.addCase(var9, (ICBlock)var7);
            } else {
               if (!(var4 instanceof agg)) {
                  throw new RuntimeException();
               }

               var4 = (ICStatement)var1.get(var2);
               if (!(var4 instanceof afs)) {
                  throw new RuntimeException();
               }

               ICStatement var15 = ((afs)var4).pC(var1, var2, var6);
               var2 = var6[0];
               var8.setDefaultBlock((ICBlock)var15);
            }
         }
      }
   }

   @Override
   public String toString() {
      return "switch: " + this.A;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      Long var3 = this.A.evaluate(var1, var2);
      if (var3 == null) {
         throw new CSimulationException("switch value eval");
      } else {
         return var3;
      }
   }
}
