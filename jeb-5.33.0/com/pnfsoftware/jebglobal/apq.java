package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEMatchVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEPatternOptimizer;
import java.util.Arrays;
import java.util.Collection;

public class apq extends AbstractEPatternOptimizer {
   static EPatternCompiler.EPattern pC = EPatternCompiler.EPattern.create()
      .addInput("(0 - ($0 ... 0)) & $1")
      .setOutput("$0 ? $1: 0")
      .setVerifier(new apr())
      .compile();
   static EPatternCompiler.EPattern A = EPatternCompiler.EPattern.create().addInput("_msb((0 - $0) | $0)").setOutput("$0 != 0").compile();
   static EPatternCompiler.EPattern kS = EPatternCompiler.EPattern.create("(reg32 >>s 31) & val ==> 0 or val")
      .addInput("($0 >> @LASTBIT0) & #2")
      .setOutput("($0 >= 0) ? 0 : #2")
      .compile();
   static EPatternCompiler.EPattern wS = EPatternCompiler.EPattern.create("Merge slices in Composition High")
      .addInput("$0[H1] .=. ($0[H2] + $1)")
      .setOutput("$0 + (0 .=. $1)")
      .compile();
   static EPatternCompiler.EPattern UT = EPatternCompiler.EPattern.create("Immediate Compositions with condition Low")
      .addInput("($0 ? #1 : #2) .=. #3")
      .setOutput("$0 ? (#1 .=. #3) : (#2 .=. #3)")
      .compile();
   static EPatternCompiler.EPattern E = EPatternCompiler.EPattern.create("Immediate Compositions with condition High")
      .addInput("#3 .=. ($0 ? #1 : #2)")
      .setOutput("$0 ? (#3 .=. #1) : (#3 .=. #2)")
      .compile();
   static EPatternCompiler.EPattern sY = EPatternCompiler.EPattern.create("Reused Condition False").addInput("$0 ? $1 : $0").setOutput("$0 ? $1 : 0").compile();
   static EPatternCompiler.EPattern ys = EPatternCompiler.EPattern.create("Reused Condition True").addInput("$0 ? $0 : $1").setOutput("$0 ? 1 : $1").compile();
   private static IEMatchVerifier ah = new aps();
   static EPatternCompiler.EPattern ld = EPatternCompiler.EPattern.create("Truncated signed division (1)")
      .addInput("(($0 .=. ((_msb $0) ? (-1) : 0)) / #1)[H1]")
      .setOutput("$0 / #1[H1]")
      .setVerifier(ah)
      .compile();
   static EPatternCompiler.EPattern gp = EPatternCompiler.EPattern.create("Sign-bit of truncated signed division (1)")
      .addInput("_hsb (($0 .=. ((_msb $0) ? (-1) : 0)) / #1)")
      .setOutput("_msb ($0 / #1[H1])")
      .setVerifier(ah)
      .compile();
   static EPatternCompiler.EPattern oT = EPatternCompiler.EPattern.create("Truncated signed division (2)")
      .addInput("(#1 / ($0 .=. ((_msb $0) ? (-1) : 0)))[H1]")
      .setOutput("#1[H1] / $0")
      .setVerifier(ah)
      .compile();
   static EPatternCompiler.EPattern fI = EPatternCompiler.EPattern.create("Sign-bit of truncated signed division (2)")
      .addInput("_hsb (#1 / ($0 .=. ((_msb $0) ? (-1) : 0)))")
      .setOutput("_msb (#1[H1] / $0)")
      .setVerifier(ah)
      .compile();
   static EPatternCompiler.EPattern WR = EPatternCompiler.EPattern.create("Truncated signed division (3)")
      .addInput("(($0 .=. ((_msb $0) ? (-1) : 0)) / ($1 .=. ((_msb $1) ? (-1) : 0)))[H1]")
      .setOutput("$0 / $1")
      .compile();
   static EPatternCompiler.EPattern NS = EPatternCompiler.EPattern.create("Sign-bit of truncated signed division (3)")
      .addInput("_hsb (($0 .=. ((_msb $0) ? (-1) : 0)) / ($1 .=. ((_msb $1) ? (-1) : 0)))")
      .setOutput("_msb ($0 / $1)")
      .compile();
   private static IEMatchVerifier eP = new apt();
   static EPatternCompiler.EPattern vP = EPatternCompiler.EPattern.create("Truncated unsigned division (1)")
      .addInput("(($0 .=. 0) /u #1)[H1]")
      .setOutput("$0 /u #1[H1]")
      .setVerifier(eP)
      .compile();
   static EPatternCompiler.EPattern xC = EPatternCompiler.EPattern.create("Truncated unsigned division (2)")
      .addInput("(#1 /u ($0 .=. 0))[H1]")
      .setOutput("#1[H1] /u $0")
      .setVerifier(eP)
      .compile();
   static EPatternCompiler.EPattern ED = EPatternCompiler.EPattern.create("Truncated unsigned division (3)")
      .addInput("(($0 .=. 0) /u ($1 .=. 0))[H1]")
      .setOutput("$0 /u $1")
      .compile();
   static EPatternCompiler.EPattern Sc = EPatternCompiler.EPattern.create("Character check via look-up table")
      .addInput("(V0 >u  $1) || (!(_lsb ($2 >>u V0)))")
      .addInput("(V0 <=u $1) &&   (_lsb ($2 >>u V0)) ")
      .setCustomReplacer(new apu())
      .compile();

   @Override
   protected Collection getPatterns() {
      return Arrays.asList(pC, A, kS, wS, UT, E, sY, ys, ld, gp, oT, fI, WR, NS, vP, xC, ED, Sc);
   }
}
