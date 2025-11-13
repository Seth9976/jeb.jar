package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import java.util.List;

public class afu extends agk implements afo, afp {
   public afu(ICDoWhileStm var1) {
      super(agk.Av.kS, agk.Sv.pC);
      this.E = var1;
   }

   @Override
   public ICStatement pC(List var1, int var2, int[] var3) {
      ICStatement var4 = (ICStatement)var1.get(var2++);
      if (!(var4 instanceof afu)) {
         throw new RuntimeException();
      } else {
         var4 = (ICStatement)var1.get(var2);
         if (!(var4 instanceof afs)) {
            throw new RuntimeException();
         } else {
            int[] var5 = new int[1];
            ICStatement var6 = ((afs)var4).pC(var1, var2, var5);
            var2 = var5[0];
            var4 = (ICStatement)var1.get(var2++);
            if (!(var4 instanceof afv)) {
               throw new RuntimeException();
            } else {
               ICPredicate var7 = ((afv)var4).pC();
               var3[0] = var2;
               ICDoWhileStm var8 = (ICDoWhileStm)this.E;
               var8.reset();
               var8.setBody((ICBlock)var6);
               var8.setPredicate(var7);
               return var8;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "do-while";
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      return null;
   }
}
