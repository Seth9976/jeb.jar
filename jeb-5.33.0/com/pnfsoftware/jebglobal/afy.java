package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import java.util.List;

public class afy extends agk implements afo, afr {
   ICPredicate A;

   public afy(ICIfStm var1, ICPredicate var2) {
      super(agk.Av.A, agk.Sv.pC);
      this.E = var1;
      this.A = var2;
   }

   @Override
   public ICPredicate pC() {
      return this.A;
   }

   @Override
   public ICStatement pC(List var1, int var2, int[] var3) {
      ICStatement var4 = (ICStatement)var1.get(var2++);
      if (!(var4 instanceof afy)) {
         throw new RuntimeException();
      } else {
         ICPredicate var5 = ((afy)var4).pC();
         var4 = (ICStatement)var1.get(var2);
         if (!(var4 instanceof afs)) {
            throw new RuntimeException();
         } else {
            int[] var6 = new int[1];
            ICStatement var7 = ((afs)var4).pC(var1, var2, var6);
            var2 = var6[0];
            ICIfStm var8 = (ICIfStm)this.E;
            var8.reset();
            var8.addBranch(var5, (ICBlock)var7);

            while (true) {
               var4 = (ICStatement)var1.get(var2++);
               if (var4 instanceof agb) {
                  var3[0] = var2;
                  return var8;
               }

               if (var4 instanceof afz) {
                  var5 = ((afz)var4).pC();
                  var4 = (ICStatement)var1.get(var2);
                  if (!(var4 instanceof afs)) {
                     throw new RuntimeException();
                  }

                  var7 = ((afs)var4).pC(var1, var2, var6);
                  var2 = var6[0];
                  var8.addBranch(var5, (ICBlock)var7);
               } else {
                  if (!(var4 instanceof aga)) {
                     throw new RuntimeException();
                  }

                  var4 = (ICStatement)var1.get(var2);
                  if (!(var4 instanceof afs)) {
                     throw new RuntimeException();
                  }

                  var7 = ((afs)var4).pC(var1, var2, var6);
                  var2 = var6[0];
                  var8.setDefaultBlock((ICBlock)var7);
               }
            }
         }
      }
   }

   @Override
   public String toString() {
      return "if: " + this.A;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      if (this.A.evaluate(var1, var2) != 0L) {
         var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      } else {
         var1.setControlWord(CMethodState.ControlWord.SKIP_NEXT_BLOCK);
      }

      return null;
   }
}
