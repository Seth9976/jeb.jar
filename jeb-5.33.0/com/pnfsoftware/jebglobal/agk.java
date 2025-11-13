package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public abstract class agk extends ado {
   ICStatement E;
   agk.Av sY;
   agk.Sv ys;

   public agk(agk.Av var1, agk.Sv var2) {
      this.sY = var1;
      this.ys = var2;
   }

   public agk.Av wS() {
      return this.sY;
   }

   public agk.Sv UT() {
      return this.ys;
   }

   @Override
   public void generate(COutputSink var1) {
      throw new RuntimeException();
   }

   @Override
   public String toString() {
      return "PseudoStatement [originalStatement=" + this.E + ", type=" + this.sY + ", where=" + this.ys + "]";
   }

   @Override
   public ICStatement duplicate() {
      throw new RuntimeException("deep duplication not implemented for pseudo-statements");
   }

   @Override
   public CElementType getElementType() {
      return null;
   }

   public static enum Av {
      pC,
      A,
      kS,
      wS,
      UT,
      E;
   }

   public static enum Sv {
      pC,
      A,
      kS;
   }
}
