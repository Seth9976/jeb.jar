package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.DataGapAnalysisStyle;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;
import java.util.Set;

@Ser
public abstract class YA implements RP {
   @SerId(1)
   private final VM pC;

   public YA(VM var1) {
      this.pC = var1;
   }

   @Override
   public Set pC() {
      return this.pC.pC();
   }

   @Override
   public Set A() {
      return this.pC.A();
   }

   @Override
   public Set kS() {
      return this.pC.kS();
   }

   @Override
   public boolean pC(INativeCodeUnit var1) {
      return this.pC.pC(var1);
   }

   @Override
   public List wS() {
      return this.pC.wS();
   }

   @Override
   public DataGapAnalysisStyle UT() {
      return this.pC.UT();
   }

   @Override
   public CodeGapAnalysisStyle E() {
      return this.pC.E();
   }

   @Override
   public iL.Av A(INativeCodeUnit var1) {
      return this.pC.A(var1);
   }

   @Override
   public com.pnfsoftware.jeb.corei.parsers.x86.wn.Av kS(INativeCodeUnit var1) {
      return this.pC.kS(var1);
   }

   @Override
   public Boolean sY() {
      return this.pC.sY();
   }

   @Override
   public Boolean ys() {
      return this.pC.ys();
   }

   @Override
   public Integer ld() {
      return this.pC.ld();
   }

   @Override
   public Boolean gp() {
      return this.pC.gp();
   }

   @Override
   public Integer oT() {
      return this.pC.oT();
   }

   @Override
   public Integer fI() {
      return this.pC.fI();
   }

   @Override
   public Integer WR() {
      return this.pC.WR();
   }

   @Override
   public String toString() {
      return this.getClass().getSimpleName();
   }
}
