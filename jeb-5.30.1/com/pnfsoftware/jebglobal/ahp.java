package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import java.util.List;

public class ahp extends aid implements ahh, ahi, ahk {
   ICStatement RF;
   ICPredicate xK;
   ICStatement Dw;
   boolean Uv = true;

   public ahp(ICForStm var1, ICStatement var2, ICPredicate var3, ICStatement var4) {
      super(aid.eo.Uv, aid.CU.q);
      this.oW = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   public ICStatement RF() {
      return this.RF;
   }

   @Override
   public ICPredicate q() {
      return this.xK;
   }

   public ICStatement xK() {
      return this.Dw;
   }

   @Override
   public ICStatement q(List var1, int var2, int[] var3) {
      ICStatement var4 = (ICStatement)var1.get(var2++);
      if (!(var4 instanceof ahp)) {
         throw new RuntimeException();
      } else {
         ICStatement var5 = ((ahp)var4).RF();
         ICPredicate var6 = ((ahp)var4).q();
         ICStatement var7 = ((ahp)var4).xK();
         var4 = (ICStatement)var1.get(var2);
         if (!(var4 instanceof ahl)) {
            throw new RuntimeException();
         } else {
            int[] var8 = new int[1];
            ICStatement var9 = ((ahl)var4).q(var1, var2, var8);
            var2 = var8[0];
            var4 = (ICStatement)var1.get(var2++);
            if (!(var4 instanceof ahq)) {
               throw new RuntimeException();
            } else {
               var3[0] = var2;
               ICForStm var10 = (ICForStm)this.oW;
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
      return "for: " + this.RF + " / " + this.xK + " / " + this.Dw;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      if (this.Uv) {
         this.RF.evaluate(var1, var2);
      } else {
         this.Dw.evaluate(var1, var2);
      }

      if (this.xK.evaluate(var1, var2) != 0L) {
         if (this.Uv) {
            this.Uv = false;
         }

         var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      } else {
         this.Uv = true;
         var1.setControlWord(CMethodState.ControlWord.SKIP_NEXT_BLOCK);
      }

      return null;
   }
}
