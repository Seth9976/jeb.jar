package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import java.util.List;

public class ahr extends aid implements ahh, ahk {
   ICPredicate RF;

   public ahr(ICIfStm var1, ICPredicate var2) {
      super(aid.eo.RF, aid.CU.q);
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
      if (!(var4 instanceof ahr)) {
         throw new RuntimeException();
      } else {
         ICPredicate var5 = ((ahr)var4).q();
         var4 = (ICStatement)var1.get(var2);
         if (!(var4 instanceof ahl)) {
            throw new RuntimeException();
         } else {
            int[] var6 = new int[1];
            ICStatement var7 = ((ahl)var4).q(var1, var2, var6);
            var2 = var6[0];
            ICIfStm var8 = (ICIfStm)this.oW;
            var8.reset();
            var8.addBranch(var5, (ICBlock)var7);

            while (true) {
               var4 = (ICStatement)var1.get(var2++);
               if (var4 instanceof ahu) {
                  var3[0] = var2;
                  return var8;
               }

               if (var4 instanceof ahs) {
                  var5 = ((ahs)var4).q();
                  var4 = (ICStatement)var1.get(var2);
                  if (!(var4 instanceof ahl)) {
                     throw new RuntimeException();
                  }

                  var7 = ((ahl)var4).q(var1, var2, var6);
                  var2 = var6[0];
                  var8.addBranch(var5, (ICBlock)var7);
               } else {
                  if (!(var4 instanceof aht)) {
                     throw new RuntimeException();
                  }

                  var4 = (ICStatement)var1.get(var2);
                  if (!(var4 instanceof ahl)) {
                     throw new RuntimeException();
                  }

                  var7 = ((ahl)var4).q(var1, var2, var6);
                  var2 = var6[0];
                  var8.setDefaultBlock((ICBlock)var7);
               }
            }
         }
      }
   }

   @Override
   public String toString() {
      return "if: " + this.RF;
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
