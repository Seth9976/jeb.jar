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

public class arc extends AbstractEPatternOptimizer {
   static EPatternCompiler.EPattern q = EPatternCompiler.EPattern.create("High-level, Signed less-than-or-equal zero")
      .addInput("( ($0 == 0)    || ((_msb $0) != 0) )")
      .addInput("( ($0 == 0)    || ($0 < 0) )")
      .addInput("( ($0 ? 0 : 1) || ((_msb $0) != 0) )")
      .addInput("( ($0 ? 0 : 1) || ($0 < 0) )")
      .addInput("( (!($0))      || ((_msb $0) != 0) )")
      .addInput("( (!($0))      || ($0 < 0) )")
      .setOutput("$0 <= 0")
      .compile();
   static EPatternCompiler.EPattern RF = EPatternCompiler.EPattern.create("High-level, Signed greater-than zero")
      .addInput("( ($0 != 0)    && ((_msb $0) == 0) )")
      .addInput("( ($0 != 0)    && ($0 >= 0) )")
      .addInput("( ($0 ? 1 : 0) && ((_msb $0) == 0) )")
      .addInput("( ($0 ? 1 : 0) && ($0 >= 0) )")
      .addInput("(  $0          && ((_msb $0) == 0) )")
      .addInput("(  $0          && ($0 >= 0) )")
      .setOutput("$0 > 0")
      .compile();
   static EPatternCompiler.EPattern xK = EPatternCompiler.EPattern.create("High-level, Unsigned less-than-or-equal")
      .addInput("( (!($0 _carry #1)) || ($0 == #2) )")
      .setOutput("$0 <=u #2")
      .setVerifier(new arc.eo())
      .compile();
   static EPatternCompiler.EPattern Dw = EPatternCompiler.EPattern.create("High-level, Unsigned greater-than")
      .addInput("( ($0 _carry #1) && ($0 != #2) )")
      .setOutput("$0 >u #2")
      .setVerifier(new arc.eo())
      .compile();
   static EPatternCompiler.EPattern Uv = EPatternCompiler.EPattern.create("Signed greater-than-or-equal 1")
      .addInput("(_msb($0 - $1)) == (_msb((($0 - $1) ^ $0) & ($0 ^ $1)))")
      .setOutput("$0 >= $1")
      .compile();
   static EPatternCompiler.EPattern oW = EPatternCompiler.EPattern.create("Signed less-than 1")
      .addInput("(_msb($0 - $1)) != (_msb((($0 - $1) ^ $0) & ($0 ^ $1)))")
      .setOutput("$0 < $1")
      .compile();
   static EPatternCompiler.EPattern gO = EPatternCompiler.EPattern.create("Unsigned less-than 1")
      .addInput("_msb(((($0 - $1) ^ $0) & ($0 ^ $1)) ^ ($0 - $1) ^ $0 ^ $1)")
      .setOutput("$0 <u $1")
      .compile();
   static EPatternCompiler.EPattern nf = EPatternCompiler.EPattern.create("Unsigned less-than-or-equal 1")
      .addInput("_msb( (($0 ^ $1) | (~ ($0 - $1))) & ($0 | (~ $1)) )")
      .addInput("( (($0 ^ $1) | (~ ($0 - $1))) & ($0 | (~ $1)) ) < (0)")
      .setOutput("$1 <=u $0")
      .compile();
   static EPatternCompiler.EPattern gP = EPatternCompiler.EPattern.create("Unsigned less-than-or-equal 2")
      .addInput("( (($0 ^ $1) | (~ ($0 - $1))) & ($0 | (~ $1)) ) >>u #2")
      .setOutput("($1 <=u $0) ... 0")
      .setVerifier(new arc.CU())
      .compile();
   static EPatternCompiler.EPattern za = EPatternCompiler.EPattern.create("Unsigned less-than 2")
      .addInput("_msb(((~ $0) & $1) | ((~ ($0 ^ $1)) & ($0 - $1)))")
      .addInput("_msb(((~ $0) & $1) | (((~ $0) | $1) & ($0 - $1)))")
      .setOutput("$0 <u $1")
      .compile();
   static EPatternCompiler.EPattern lm = EPatternCompiler.EPattern.create("Unsigned less-than 3")
      .addInput("(((~ $0) & $1) | ((~ ($0 ^ $1)) & ($0 - $1))) >>u #2")
      .addInput("(((~ $0) & $1) | (((~ $0) | $1) & ($0 - $1))) >>u #2")
      .setOutput("($0 <u $1) ... 0")
      .setVerifier(new arc.CU())
      .compile();
   static EPatternCompiler.EPattern zz = EPatternCompiler.EPattern.create("Signed less-than-or-equal 1")
      .addInput("_msb(($0 | (~ $1)) & (($0 ^ $1) | (~ ($1 - $0))))")
      .addInput("_msb( ((~ ($0 ^ $1)) >> 1) + ($0 & (~ $1)) )")
      .setOutput("$0 <= $1")
      .compile();
   static EPatternCompiler.EPattern JY = EPatternCompiler.EPattern.create("Signed less-than-or-equal 2")
      .addInput("(($0 | (~ $1)) & (($0 ^ $1) | (~ ($1 - $0)))) >>u #2")
      .addInput("(((~ ($0 ^ $1)) >> 1) + ($0 & (~ $1))) >>u #2")
      .setOutput("($0 <= $1) ... 0")
      .setVerifier(new arc.CU())
      .compile();
   static EPatternCompiler.EPattern HF = EPatternCompiler.EPattern.create("Signed sub ge 0").addInput("($0 - $1) >= 0").setOutput("$0 >= $1").compile();
   static EPatternCompiler.EPattern LK = EPatternCompiler.EPattern.create("Signed sub gt 0").addInput("($0 - $1) > 0").setOutput("$0 > $1").compile();
   static EPatternCompiler.EPattern io = EPatternCompiler.EPattern.create("Signed sub le 0").addInput("($0 - $1) <= 0").setOutput("$0 <= $1").compile();
   static EPatternCompiler.EPattern qa = EPatternCompiler.EPattern.create("Signed sub lt 0").addInput("($0 - $1) < 0").setOutput("$0 < $1").compile();
   static EPatternCompiler.EPattern Hk = EPatternCompiler.EPattern.create("Merge conditions to LAND")
      .addInput("$0 ? $1 : #2")
      .setOutput("$0 && $1")
      .setVerifier((var0, var1) -> EUtil.isBitZero((IEGeneric)var1.getMatchedLeaf(2, IEImm.class)))
      .compile();
   static EPatternCompiler.EPattern Me = EPatternCompiler.EPattern.create("Merge condition to LNOTAND")
      .addInput("$0 ? #1 : $2")
      .setOutput("(!($0)) && $2")
      .setVerifier((var0, var1) -> EUtil.isBitZero((IEGeneric)var1.getMatchedLeaf(1, IEImm.class)))
      .compile();
   static EPatternCompiler.EPattern PV = EPatternCompiler.EPattern.create("Dual slot equality")
      .addInput("($0[H1] == $1[H1]) && ($0[H2] == $1[H2])")
      .addInput("($0[H2] == $1[H2]) && ($0[H1] == $1[H1])")
      .setOutput("$0 == $1")
      .compile();
   static EPatternCompiler.EPattern oQ = EPatternCompiler.EPattern.create("Dual slot equality low")
      .addInput("($0[H1] == $2) && ($0[H2] == 0)")
      .addInput("($0[H2] == 0) && ($0[H1] == $2)")
      .setOutput("$0 == ($2 .=. 0)")
      .compile();

   @Override
   protected Collection getPatterns() {
      return Arrays.asList(q, RF, xK, Dw, Uv, oW, gO, nf, gP, za, lm, zz, JY, HF, LK, io, qa, Hk, Me, PV, oQ);
   }

   private static class CU implements IEMatchVerifier {
      @Override
      public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
         IEGeneric var3 = (IEGeneric)var2.getMatchedLeaf(0, IEImm.class);
         IEImm var4 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
         return var4.getValueAsLong() == var3.getBitsize() - 1;
      }
   }

   private static class eo implements IEMatchVerifier {
      @Override
      public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
         IEImm var3 = (IEImm)var2.getMatchedLeaf(1, IEImm.class);
         IEImm var4 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
         return var3._add(var4).isZero();
      }
   }
}
