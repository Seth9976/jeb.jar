package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import java.util.List;

public class ahn extends aid implements ahh, ahi {
   public ahn(ICDoWhileStm var1) {
      super(aid.eo.xK, aid.CU.q);
      this.oW = var1;
   }

   @Override
   public ICStatement q(List var1, int var2, int[] var3) {
      ICStatement var4 = (ICStatement)var1.get(var2++);
      if (!(var4 instanceof ahn)) {
         throw new RuntimeException();
      } else {
         var4 = (ICStatement)var1.get(var2);
         if (!(var4 instanceof ahl)) {
            throw new RuntimeException();
         } else {
            int[] var5 = new int[1];
            ICStatement var6 = ((ahl)var4).q(var1, var2, var5);
            var2 = var5[0];
            var4 = (ICStatement)var1.get(var2++);
            if (!(var4 instanceof aho)) {
               throw new RuntimeException();
            } else {
               ICPredicate var7 = ((aho)var4).q();
               var3[0] = var2;
               ICDoWhileStm var8 = (ICDoWhileStm)this.oW;
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
