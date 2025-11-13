package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEMatchVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEPatternOptimizer;
import java.util.Arrays;
import java.util.Collection;

public class ase extends AbstractEPatternOptimizer {
   static EPatternCompiler.EPattern q = EPatternCompiler.EPattern.create()
      .addInput("(0 - ($0 ... 0)) & $1")
      .setOutput("$0 ? $1: 0")
      .setVerifier(new asf())
      .compile();
   static EPatternCompiler.EPattern RF = EPatternCompiler.EPattern.create().addInput("_msb((0 - $0) | $0)").setOutput("$0 != 0").compile();
   static EPatternCompiler.EPattern xK = EPatternCompiler.EPattern.create("(reg32 >>s 31) & val ==> 0 or val")
      .addInput("($0 >> @LASTBIT0) & #2")
      .setOutput("($0 >= 0) ? 0 : #2")
      .compile();
   static EPatternCompiler.EPattern Dw = EPatternCompiler.EPattern.create("Merge slices in Composition High")
      .addInput("$0[H1] .=. ($0[H2] + $1)")
      .setOutput("$0 + (0 .=. $1)")
      .compile();
   static EPatternCompiler.EPattern Uv = EPatternCompiler.EPattern.create("Immediate Compositions with condition Low")
      .addInput("($0 ? #1 : #2) .=. #3")
      .setOutput("$0 ? (#1 .=. #3) : (#2 .=. #3)")
      .compile();
   static EPatternCompiler.EPattern oW = EPatternCompiler.EPattern.create("Immediate Compositions with condition High")
      .addInput("#3 .=. ($0 ? #1 : #2)")
      .setOutput("$0 ? (#3 .=. #1) : (#3 .=. #2)")
      .compile();
   static EPatternCompiler.EPattern gO = EPatternCompiler.EPattern.create("Reused Condition False").addInput("$0 ? $1 : $0").setOutput("$0 ? $1 : 0").compile();
   static EPatternCompiler.EPattern nf = EPatternCompiler.EPattern.create("Reused Condition True").addInput("$0 ? $0 : $1").setOutput("$0 ? 1 : $1").compile();
   private static IEMatchVerifier Me = new asg();
   static EPatternCompiler.EPattern gP = EPatternCompiler.EPattern.create("Truncated signed division (1)")
      .addInput("(($0 .=. ((_msb $0) ? (-1) : 0)) / #1)[H1]")
      .setOutput("$0 / #1[H1]")
      .setVerifier(Me)
      .compile();
   static EPatternCompiler.EPattern za = EPatternCompiler.EPattern.create("Sign-bit of truncated signed division (1)")
      .addInput("_hsb (($0 .=. ((_msb $0) ? (-1) : 0)) / #1)")
      .setOutput("_msb ($0 / #1[H1])")
      .setVerifier(Me)
      .compile();
   static EPatternCompiler.EPattern lm = EPatternCompiler.EPattern.create("Truncated signed division (2)")
      .addInput("(#1 / ($0 .=. ((_msb $0) ? (-1) : 0)))[H1]")
      .setOutput("#1[H1] / $0")
      .setVerifier(Me)
      .compile();
   static EPatternCompiler.EPattern zz = EPatternCompiler.EPattern.create("Sign-bit of truncated signed division (2)")
      .addInput("_hsb (#1 / ($0 .=. ((_msb $0) ? (-1) : 0)))")
      .setOutput("_msb (#1[H1] / $0)")
      .setVerifier(Me)
      .compile();
   static EPatternCompiler.EPattern JY = EPatternCompiler.EPattern.create("Truncated signed division (3)")
      .addInput("(($0 .=. ((_msb $0) ? (-1) : 0)) / ($1 .=. ((_msb $1) ? (-1) : 0)))[H1]")
      .setOutput("$0 / $1")
      .compile();
   static EPatternCompiler.EPattern HF = EPatternCompiler.EPattern.create("Sign-bit of truncated signed division (3)")
      .addInput("_hsb (($0 .=. ((_msb $0) ? (-1) : 0)) / ($1 .=. ((_msb $1) ? (-1) : 0)))")
      .setOutput("_msb ($0 / $1)")
      .compile();
   private static IEMatchVerifier PV = new ash();
   static EPatternCompiler.EPattern LK = EPatternCompiler.EPattern.create("Truncated unsigned division (1)")
      .addInput("(($0 .=. 0) /u #1)[H1]")
      .setOutput("$0 /u #1[H1]")
      .setVerifier(PV)
      .compile();
   static EPatternCompiler.EPattern io = EPatternCompiler.EPattern.create("Truncated unsigned division (2)")
      .addInput("(#1 /u ($0 .=. 0))[H1]")
      .setOutput("#1[H1] /u $0")
      .setVerifier(PV)
      .compile();
   static EPatternCompiler.EPattern qa = EPatternCompiler.EPattern.create("Truncated unsigned division (3)")
      .addInput("(($0 .=. 0) /u ($1 .=. 0))[H1]")
      .setOutput("$0 /u $1")
      .compile();
   static EPatternCompiler.EPattern Hk = EPatternCompiler.EPattern.create("Character check via look-up table")
      .addInput("(V0 >u  $1) || (!(_lsb ($2 >>u V0)))")
      .addInput("(V0 <=u $1) &&   (_lsb ($2 >>u V0)) ")
      .setCustomReplacer(new asi())
      .compile();

   @Override
   protected Collection getPatterns() {
      return Arrays.asList(q, RF, xK, Dw, Uv, oW, gO, nf, gP, za, lm, zz, JY, HF, LK, io, qa, Hk);
   }
}
