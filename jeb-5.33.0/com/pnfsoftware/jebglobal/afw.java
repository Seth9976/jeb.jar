package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import java.util.List;

public class afw extends agk implements afo, afp, afr {
   ICStatement A;
   ICPredicate kS;
   ICStatement wS;
   boolean UT = true;

   public afw(ICForStm var1, ICStatement var2, ICPredicate var3, ICStatement var4) {
      super(agk.Av.UT, agk.Sv.pC);
      this.E = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   public ICStatement A() {
      return this.A;
   }

   @Override
   public ICPredicate pC() {
      return this.kS;
   }

   public ICStatement kS() {
      return this.wS;
   }

   @Override
   public ICStatement pC(List var1, int var2, int[] var3) {
      ICStatement var4 = (ICStatement)var1.get(var2++);
      if (!(var4 instanceof afw)) {
         throw new RuntimeException();
      } else {
         ICStatement var5 = ((afw)var4).A();
         ICPredicate var6 = ((afw)var4).pC();
         ICStatement var7 = ((afw)var4).kS();
         var4 = (ICStatement)var1.get(var2);
         if (!(var4 instanceof afs)) {
            throw new RuntimeException();
         } else {
            int[] var8 = new int[1];
            ICStatement var9 = ((afs)var4).pC(var1, var2, var8);
            var2 = var8[0];
            var4 = (ICStatement)var1.get(var2++);
            if (!(var4 instanceof afx)) {
               throw new RuntimeException();
            } else {
               var3[0] = var2;
               ICForStm var10 = (ICForStm)this.E;
               var10.reset();
               var10.setPreStatement(var5);
               var10.setPredicate(var6);
               var10.setPostStatement(var7);
               var10.setBody((ICBlock)var9);
               return var10;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "for: " + this.A + " / " + this.kS + " / " + this.wS;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      if (this.UT) {
         this.A.evaluate(var1, var2);
      } else {
         this.wS.evaluate(var1, var2);
      }

      if (this.kS.evaluate(var1, var2) != 0L) {
         if (this.UT) {
            this.UT = false;
         }

         var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      } else {
         this.UT = true;
         var1.setControlWord(CMethodState.ControlWord.SKIP_NEXT_BLOCK);
      }

      return null;
   }
}
