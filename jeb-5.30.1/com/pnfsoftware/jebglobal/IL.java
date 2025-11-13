package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.DataGapAnalysisStyle;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;
import java.util.Set;

@Ser
public abstract class IL implements aao {
   @SerId(1)
   private final aay q;

   public IL(aay var1) {
      this.q = var1;
   }

   @Override
   public Set q() {
      return this.q.q();
   }

   @Override
   public Set RF() {
      return this.q.RF();
   }

   @Override
   public Set xK() {
      return this.q.xK();
   }

   @Override
   public boolean q(INativeCodeUnit var1) {
      return this.q.q(var1);
   }

   @Override
   public List Dw() {
      return this.q.Dw();
   }

   @Override
   public DataGapAnalysisStyle Uv() {
      return this.q.Uv();
   }

   @Override
   public CodeGapAnalysisStyle oW() {
      return this.q.oW();
   }

   @Override
   public aap.eo RF(INativeCodeUnit var1) {
      return this.q.RF(var1);
   }

   @Override
   public ctk.eo xK(INativeCodeUnit var1) {
      return this.q.xK(var1);
   }

   @Override
   public Boolean gO() {
      return this.q.gO();
   }

   @Override
   public Boolean nf() {
      return this.q.nf();
   }

   @Override
   public Integer gP() {
      return this.q.gP();
   }

   @Override
   public Boolean za() {
      return this.q.za();
   }

   @Override
   public Integer lm() {
      return this.q.lm();
   }

   @Override
   public Integer zz() {
      return this.q.zz();
   }

   @Override
   public Integer JY() {
      return this.q.JY();
   }

   @Override
   public String toString() {
      return this.getClass().getSimpleName();
   }
}
