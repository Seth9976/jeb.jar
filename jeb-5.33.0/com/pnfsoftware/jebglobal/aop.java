package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEMatchVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEPatternOptimizer;
import java.util.Arrays;
import java.util.Collection;

public class aop extends AbstractEPatternOptimizer {
   static EPatternCompiler.EPattern pC = EPatternCompiler.EPattern.create("High-level, Signed less-than-or-equal zero")
      .addInput("( ($0 == 0)    || ((_msb $0) != 0) )")
      .addInput("( ($0 == 0)    || ($0 < 0) )")
      .addInput("( ($0 ? 0 : 1) || ((_msb $0) != 0) )")
      .addInput("( ($0 ? 0 : 1) || ($0 < 0) )")
      .addInput("( (!($0))      || ((_msb $0) != 0) )")
      .addInput("( (!($0))      || ($0 < 0) )")
      .setOutput("$0 <= 0")
      .compile();
   static EPatternCompiler.EPattern A = EPatternCompiler.EPattern.create("High-level, Signed greater-than zero")
      .addInput("( ($0 != 0)    && ((_msb $0) == 0) )")
      .addInput("( ($0 != 0)    && ($0 >= 0) )")
      .addInput("( ($0 ? 1 : 0) && ((_msb $0) == 0) )")
      .addInput("( ($0 ? 1 : 0) && ($0 >= 0) )")
      .addInput("(  $0          && ((_msb $0) == 0) )")
      .addInput("(  $0          && ($0 >= 0) )")
      .setOutput("$0 > 0")
      .compile();
   static EPatternCompiler.EPattern kS = EPatternCompiler.EPattern.create("High-level, Unsigned less-than-or-equal")
      .addInput("( (!($0 _carry #1)) || ($0 == #2) )")
      .setOutput("$0 <=u #2")
      .setVerifier(new aop.Av())
      .compile();
   static EPatternCompiler.EPattern wS = EPatternCompiler.EPattern.create("High-level, Unsigned greater-than")
      .addInput("( ($0 _carry #1) && ($0 != #2) )")
      .setOutput("$0 >u #2")
      .setVerifier(new aop.Av())
      .compile();
   static EPatternCompiler.EPattern UT = EPatternCompiler.EPattern.create("Signed greater-than-or-equal 1")
      .addInput("(_msb($0 - $1)) == (_msb((($0 - $1) ^ $0) & ($0 ^ $1)))")
      .setOutput("$0 >= $1")
      .compile();
   static EPatternCompiler.EPattern E = EPatternCompiler.EPattern.create("Signed less-than 1")
      .addInput("(_msb($0 - $1)) != (_msb((($0 - $1) ^ $0) & ($0 ^ $1)))")
      .setOutput("$0 < $1")
      .compile();
   static EPatternCompiler.EPattern sY = EPatternCompiler.EPattern.create("Unsigned less-than 1")
      .addInput("_msb(((($0 - $1) ^ $0) & ($0 ^ $1)) ^ ($0 - $1) ^ $0 ^ $1)")
      .setOutput("$0 <u $1")
      .compile();
   static EPatternCompiler.EPattern ys = EPatternCompiler.EPattern.create("Unsigned less-than-or-equal 1")
      .addInput("_msb( (($0 ^ $1) | (~ ($0 - $1))) & ($0 | (~ $1)) )")
      .addInput("( (($0 ^ $1) | (~ ($0 - $1))) & ($0 | (~ $1)) ) < (0)")
      .setOutput("$1 <=u $0")
      .compile();
   static EPatternCompiler.EPattern ld = EPatternCompiler.EPattern.create("Unsigned less-than-or-equal 2")
      .addInput("( (($0 ^ $1) | (~ ($0 - $1))) & ($0 | (~ $1)) ) >>u #2")
      .setOutput("($1 <=u $0) ... 0")
      .setVerifier(new aop.Sv())
      .compile();
   static EPatternCompiler.EPattern gp = EPatternCompiler.EPattern.create("Unsigned less-than 2")
      .addInput("_msb(((~ $0) & $1) | ((~ ($0 ^ $1)) & ($0 - $1)))")
      .addInput("_msb(((~ $0) & $1) | (((~ $0) | $1) & ($0 - $1)))")
      .setOutput("$0 <u $1")
      .compile();
   static EPatternCompiler.EPattern oT = EPatternCompiler.EPattern.create("Unsigned less-than 3")
      .addInput("(((~ $0) & $1) | ((~ ($0 ^ $1)) & ($0 - $1))) >>u #2")
      .addInput("(((~ $0) & $1) | (((~ $0) | $1) & ($0 - $1))) >>u #2")
      .setOutput("($0 <u $1) ... 0")
      .setVerifier(new aop.Sv())
      .compile();
   static EPatternCompiler.EPattern fI = EPatternCompiler.EPattern.create("Signed less-than-or-equal 1")
      .addInput("_msb(($0 | (~ $1)) & (($0 ^ $1) | (~ ($1 - $0))))")
      .addInput("_msb( ((~ ($0 ^ $1)) >> 1) + ($0 & (~ $1)) )")
      .setOutput("$0 <= $1")
      .compile();
   static EPatternCompiler.EPattern WR = EPatternCompiler.EPattern.create("Signed less-than-or-equal 2")
      .addInput("(($0 | (~ $1)) & (($0 ^ $1) | (~ ($1 - $0)))) >>u #2")
      .addInput("(((~ ($0 ^ $1)) >> 1) + ($0 & (~ $1))) >>u #2")
      .setOutput("($0 <= $1) ... 0")
      .setVerifier(new aop.Sv())
      .compile();
   static EPatternCompiler.EPattern NS = EPatternCompiler.EPattern.create("Signed sub ge 0").addInput("($0 - $1) >= 0").setOutput("$0 >= $1").compile();
   static EPatternCompiler.EPattern vP = EPatternCompiler.EPattern.create("Signed sub gt 0").addInput("($0 - $1) > 0").setOutput("$0 > $1").compile();
   static EPatternCompiler.EPattern xC = EPatternCompiler.EPattern.create("Signed sub le 0").addInput("($0 - $1) <= 0").setOutput("$0 <= $1").compile();
   static EPatternCompiler.EPattern ED = EPatternCompiler.EPattern.create("Signed sub lt 0").addInput("($0 - $1) < 0").setOutput("$0 < $1").compile();
   static EPatternCompiler.EPattern Sc = EPatternCompiler.EPattern.create("Merge conditions to LAND")
      .addInput("$0 ? $1 : #2")
      .setOutput("$0 && $1")
      .setVerifier((var0, var1) -> EUtil.isBitZero((IEGeneric)var1.getMatchedLeaf(2, IEImm.class)))
      .compile();
   static EPatternCompiler.EPattern ah = EPatternCompiler.EPattern.create("Merge condition to LNOTAND")
      .addInput("$0 ? #1 : $2")
      .setOutput("(!($0)) && $2")
      .setVerifier((var0, var1) -> EUtil.isBitZero((IEGeneric)var1.getMatchedLeaf(1, IEImm.class)))
      .compile();
   static EPatternCompiler.EPattern eP = EPatternCompiler.EPattern.create("Dual slot equality")
      .addInput("($0[H1] == $1[H1]) && ($0[H2] == $1[H2])")
      .addInput("($0[H2] == $1[H2]) && ($0[H1] == $1[H1])")
      .setOutput("$0 == $1")
      .compile();
   static EPatternCompiler.EPattern UO = EPatternCompiler.EPattern.create("Dual slot equality low")
      .addInput("($0[H1] == $2) && ($0[H2] == 0)")
      .addInput("($0[H2] == 0) && ($0[H1] == $2)")
      .setOutput("$0 == ($2 .=. 0)")
      .compile();

   @Override
   protected Collection getPatterns() {
      return Arrays.asList(pC, A, kS, wS, UT, E, sY, ys, ld, gp, oT, fI, WR, NS, vP, xC, ED, Sc, ah, eP, UO);
   }

   private static class Av implements IEMatchVerifier {
      @Override
      public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
         IEImm var3 = (IEImm)var2.getMatchedLeaf(1, IEImm.class);
         IEImm var4 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
         return var3._add(var4).isZero();
      }
   }

   private static class Sv implements IEMatchVerifier {
      @Override
      public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
         IEGeneric var3 = (IEGeneric)var2.getMatchedLeaf(0, IEImm.class);
         IEImm var4 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
         return var4.getValueAsLong() == var3.getBitsize() - 1;
      }
   }
}
