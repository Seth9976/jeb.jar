package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import java.util.List;

public class afs extends agk implements afo {
   public afs(ICBlock var1) {
      super(agk.Av.pC, agk.Sv.pC);
      this.E = var1;
   }

   @Override
   public ICStatement pC(List var1, int var2, int[] var3) {
      ICStatement var4 = (ICStatement)var1.get(var2++);
      if (!(var4 instanceof afs)) {
         throw new RuntimeException();
      } else {
         ICBlock var5 = (ICBlock)this.E;
         var5.reset();

         while (true) {
            var4 = (ICStatement)var1.get(var2);
            if (var4 instanceof aft) {
               var3[0] = ++var2;
               return var5;
            }

            if (var4 instanceof afo) {
               int[] var6 = new int[1];
               ICStatement var7 = ((afo)var4).pC(var1, var2, var6);
               var5.add(var7);
               var2 = var6[0];
            } else {
               var5.add(var4);
               var2++;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "{";
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      return null;
   }
}
