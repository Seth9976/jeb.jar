package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import java.util.List;

public class aib extends aid implements ahh, ahi, ahk {
   ICPredicate RF;

   public aib(ICWhileStm var1, ICPredicate var2) {
      super(aid.eo.Dw, aid.CU.q);
      this.oW = var1;
      this.RF = var2;
   }

   @Override
   public ICPredicate q() {
      return this.RF;
   }

   @Override
   public ICStatement q(List var1, int var2, int[] var3) {
      ICStatement var4 = (ICStatement)var1.get(var2++);
      if (!(var4 instanceof aib)) {
         throw new RuntimeException();
      } else {
         ICPredicate var5 = ((aib)var4).q();
         var4 = (ICStatement)var1.get(var2);
         if (!(var4 instanceof ahl)) {
            throw new RuntimeException();
         } else {
            int[] var6 = new int[1];
            ICStatement var7 = ((ahl)var4).q(var1, var2, var6);
            var2 = var6[0];
            var4 = (ICStatement)var1.get(var2++);
            if (!(var4 instanceof aic)) {
               throw new RuntimeException();
            } else {
               var3[0] = var2;
               ICWhileStm var8 = (ICWhileStm)this.oW;
               var8.reset();
               var8.setPredicate(var5);
               var8.setBody((ICBlock)var7);
               return var8;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "while: " + this.RF;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      if (this.RF.evaluate(var1, var2) != 0L) {
         var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      } else {
         var1.setControlWord(CMethodState.ControlWord.SKIP_NEXT_BLOCK);
      }

      return null;
   }
}
