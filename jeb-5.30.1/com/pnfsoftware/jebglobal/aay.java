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
public class aay implements aao {
   @SerId(1)
   private final Set q;
   @SerId(2)
   private final Set RF;
   @SerId(3)
   private final Set xK;
   @SerId(4)
   private final DataGapAnalysisStyle Dw;
   @SerId(5)
   private final CodeGapAnalysisStyle Uv;
   @SerId(6)
   private final aap.eo oW;
   @SerId(7)
   private final Boolean gO;
   @SerId(8)
   private final Boolean nf;
   @SerId(9)
   private final Integer gP;
   @SerId(10)
   private final Boolean za;
   @SerId(11)
   private final Integer lm;
   @SerId(12)
   private final Integer zz;
   @SerId(13)
   private final Integer JY;
   @SerId(14)
   private final ctk.eo HF;

   private aay(aay.eo var1) {
      this.q = var1.oW;
      this.RF = var1.RF;
      this.xK = var1.Dw;
      this.Dw = var1.gO;
      this.Uv = var1.nf;
      this.oW = var1.gP;
      this.gO = var1.za;
      this.nf = var1.lm;
      this.gP = var1.zz;
      this.za = var1.JY;
      this.lm = var1.HF;
      this.zz = var1.LK;
      this.JY = var1.io;
      this.HF = var1.qa;
   }

   @Override
   public Set q() {
      return this.q;
   }

   @Override
   public Set RF() {
      return this.RF;
   }

   @Override
   public Set xK() {
      return this.xK;
   }

   @Override
   public boolean q(INativeCodeUnit var1) {
      return true;
   }

   @Override
   public List Dw() {
      return null;
   }

   @Override
   public DataGapAnalysisStyle Uv() {
      return this.Dw;
   }

   @Override
   public CodeGapAnalysisStyle oW() {
      return this.Uv;
   }

   @Override
   public aap.eo RF(INativeCodeUnit var1) {
      return this.oW;
   }

   @Override
   public ctk.eo xK(INativeCodeUnit var1) {
      return this.HF;
   }

   @Override
   public Boolean gO() {
      return this.gO;
   }

   @Override
   public Boolean nf() {
      return this.nf;
   }

   @Override
   public Integer gP() {
      return this.gP;
   }

   @Override
   public Boolean za() {
      return this.za;
   }

   @Override
   public Integer lm() {
      return this.lm;
   }

   @Override
   public Integer zz() {
      return this.zz;
   }

   @Override
   public Integer JY() {
      return this.JY;
   }

   public static class eo {
      private static final Set q = new HashSet();
      private Set RF = q;
      private static final Set xK = new HashSet();
      private Set Dw = xK;
      private static final Set Uv = new HashSet();
      private Set oW = Uv;
      private DataGapAnalysisStyle gO = null;
      private CodeGapAnalysisStyle nf = null;
      private aap.eo gP = null;
      private Boolean za = null;
      private Boolean lm = null;
      private Integer zz = null;
      private Boolean JY = null;
      private Integer HF = null;
      private Integer LK = null;
      private Integer io = null;
      private ctk.eo qa = null;

      public aay.eo q(String... var1) {
         this.oW = new HashSet(Arrays.asList(var1));
         return this;
      }

      public aay.eo q(ProcessorType... var1) {
         this.RF = new HashSet(Arrays.asList(var1));
         return this;
      }

      public aay.eo q(ICompiler... var1) {
         this.Dw = new HashSet(Arrays.asList(var1));
         return this;
      }

      public aay.eo q(DataGapAnalysisStyle var1) {
         this.gO = var1;
         return this;
      }

      public aay.eo q(CodeGapAnalysisStyle var1) {
         this.nf = var1;
         return this;
      }

      public aay.eo q(aap.eo var1) {
         this.gP = var1;
         return this;
      }

      public aay.eo q(ctk.eo var1) {
         this.qa = var1;
         return this;
      }

      public aay.eo q(boolean var1) {
         this.za = var1;
         return this;
      }

      public aay.eo RF(boolean var1) {
         this.lm = var1;
         return this;
      }

      public aay.eo q(int var1) {
         this.zz = var1;
         return this;
      }

      public aay.eo xK(boolean var1) {
         this.JY = var1;
         return this;
      }

      public aay.eo RF(int var1) {
         this.HF = var1;
         return this;
      }

      public aay.eo xK(int var1) {
         this.LK = var1;
         return this;
      }

      public aay.eo Dw(int var1) {
         this.io = var1;
         return this;
      }

      public aay q() {
         return new aay(this);
      }

      static {
         q.add(ProcessorType.UNKNOWN);
         xK.add(ICompiler.COMP_UNKNOWN);
         Uv.add("generic");
      }
   }
}
