package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.DataGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class VM implements RP {
   @SerId(1)
   private final Set pC;
   @SerId(2)
   private final Set A;
   @SerId(3)
   private final Set kS;
   @SerId(4)
   private final DataGapAnalysisStyle wS;
   @SerId(5)
   private final CodeGapAnalysisStyle UT;
   @SerId(6)
   private final iL.Av E;
   @SerId(7)
   private final Boolean sY;
   @SerId(8)
   private final Boolean ys;
   @SerId(9)
   private final Integer ld;
   @SerId(10)
   private final Boolean gp;
   @SerId(11)
   private final Integer oT;
   @SerId(12)
   private final Integer fI;
   @SerId(13)
   private final Integer WR;
   @SerId(14)
   private final com.pnfsoftware.jeb.corei.parsers.x86.wn.Av NS;

   private VM(VM.Av var1) {
      this.pC = var1.E;
      this.A = var1.A;
      this.kS = var1.wS;
      this.wS = var1.sY;
      this.UT = var1.ys;
      this.E = var1.ld;
      this.sY = var1.gp;
      this.ys = var1.oT;
      this.ld = var1.fI;
      this.gp = var1.WR;
      this.oT = var1.NS;
      this.fI = var1.vP;
      this.WR = var1.xC;
      this.NS = var1.ED;
   }

   @Override
   public Set pC() {
      return this.pC;
   }

   @Override
   public Set A() {
      return this.A;
   }

   @Override
   public Set kS() {
      return this.kS;
   }

   @Override
   public boolean pC(INativeCodeUnit var1) {
      return true;
   }

   @Override
   public List wS() {
      return null;
   }

   @Override
   public DataGapAnalysisStyle UT() {
      return this.wS;
   }

   @Override
   public CodeGapAnalysisStyle E() {
      return this.UT;
   }

   @Override
   public iL.Av A(INativeCodeUnit var1) {
      return this.E;
   }

   @Override
   public com.pnfsoftware.jeb.corei.parsers.x86.wn.Av kS(INativeCodeUnit var1) {
      return this.NS;
   }

   @Override
   public Boolean sY() {
      return this.sY;
   }

   @Override
   public Boolean ys() {
      return this.ys;
   }

   @Override
   public Integer ld() {
      return this.ld;
   }

   @Override
   public Boolean gp() {
      return this.gp;
   }

   @Override
   public Integer oT() {
      return this.oT;
   }

   @Override
   public Integer fI() {
      return this.fI;
   }

   @Override
   public Integer WR() {
      return this.WR;
   }

   public static class Av {
      private static final Set pC = new HashSet();
      private Set A = pC;
      private static final Set kS = new HashSet();
      private Set wS = kS;
      private static final Set UT = new HashSet();
      private Set E = UT;
      private DataGapAnalysisStyle sY = null;
      private CodeGapAnalysisStyle ys = null;
      private iL.Av ld = null;
      private Boolean gp = null;
      private Boolean oT = null;
      private Integer fI = null;
      private Boolean WR = null;
      private Integer NS = null;
      private Integer vP = null;
      private Integer xC = null;
      private com.pnfsoftware.jeb.corei.parsers.x86.wn.Av ED = null;

      public VM.Av pC(String... var1) {
         this.E = new HashSet(Arrays.asList(var1));
         return this;
      }

      public VM.Av pC(ProcessorType... var1) {
         this.A = new HashSet(Arrays.asList(var1));
         return this;
      }

      public VM.Av pC(ICompiler... var1) {
         this.wS = new HashSet(Arrays.asList(var1));
         return this;
      }

      public VM.Av pC(DataGapAnalysisStyle var1) {
         this.sY = var1;
         return this;
      }

      public VM.Av pC(CodeGapAnalysisStyle var1) {
         this.ys = var1;
         return this;
      }

      public VM.Av pC(iL.Av var1) {
         this.ld = var1;
         return this;
      }

      public VM.Av pC(com.pnfsoftware.jeb.corei.parsers.x86.wn.Av var1) {
         this.ED = var1;
         return this;
      }

      public VM.Av pC(boolean var1) {
         this.gp = var1;
         return this;
      }

      public VM.Av A(boolean var1) {
         this.oT = var1;
         return this;
      }

      public VM.Av pC(int var1) {
         this.fI = var1;
         return this;
      }

      public VM.Av kS(boolean var1) {
         this.WR = var1;
         return this;
      }

      public VM.Av A(int var1) {
         this.NS = var1;
         return this;
      }

      public VM.Av kS(int var1) {
         this.vP = var1;
         return this;
      }

      public VM.Av wS(int var1) {
         this.xC = var1;
         return this;
      }

      public VM pC() {
         return new VM(this);
      }

      static {
         pC.add(ProcessorType.UNKNOWN);
         kS.add(ICompiler.COMP_UNKNOWN);
         UT.add("generic");
      }
   }
}
