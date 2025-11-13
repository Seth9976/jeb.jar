package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;

public class ama {
   static EPatternCompiler.EPattern pC = EPatternCompiler.EPattern.create("Dual-slot &")
      .addInput("($0[H1] & $1[H1]) .=. ($0[H2] & $1[H2])")
      .setOutput("$0 & $1")
      .compile();
   static EPatternCompiler.EPattern A = EPatternCompiler.EPattern.create("Dual-slot |")
      .addInput("($0[H1] | $1[H1]) .=. ($0[H2] | $1[H2])")
      .setOutput("$0 | $1")
      .compile();
   static EPatternCompiler.EPattern kS = EPatternCompiler.EPattern.create("Dual-slot ^")
      .addInput("($0[H1] ^ $1[H1]) .=. ($0[H2] ^ $1[H2])")
      .setOutput("$0 ^ $1")
      .compile();
   static EPatternCompiler.EPattern wS = EPatternCompiler.EPattern.create("Dual-slot << High")
      .addInput("($1 << $2) | ($0 << ($2 - #3)) | ($0 >>u (#3 - $2))")
      .setOutput("(($0 .=. $1) << $2)[H2]")
      .setVerifier((var0, var1) -> {
         IEImm var2 = (IEImm)var1.getMatchedLeaf(3, IEImm.class);
         return var2.getValueAsLong() == var2.getBitsize();
      })
      .compile();
   static EPatternCompiler.EPattern UT = EPatternCompiler.EPattern.create("Dual-slot <<")
      .addInput("($0[H1] << $2) .=. ($0 << $2)[H2]")
      .addInput("($0 << $2)[H1] .=. ($0 << $2)[H2]")
      .setOutput("$0 << $2")
      .compile();
   static EPatternCompiler.EPattern E = EPatternCompiler.EPattern.create("Dual-slot >>u Low")
      .addInput("($0 >>u $2) | ($1 >>u ($2 - #3)) | ($1 << (#3 - $2))")
      .setOutput("(($0 .=. $1) >>u $2)[H1]")
      .setVerifier((var0, var1) -> {
         IEImm var2 = (IEImm)var1.getMatchedLeaf(3, IEImm.class);
         return var2.getValueAsLong() == var2.getBitsize();
      })
      .compile();
   static EPatternCompiler.EPattern sY = EPatternCompiler.EPattern.create("Dual-slot >>")
      .addInput("($0 >>u $2)[H1] .=. ($0[H2] >>u $2)")
      .addInput("($0 >>u $2)[H1] .=. ($0 >>u $2)[H2]")
      .setOutput("$0 >>u $2")
      .compile();
   static EPatternCompiler.EPattern ys = EPatternCompiler.EPattern.create("Dual-slot >> 2")
      .addInput("($0[H2] << (#3 - $2)) | ($0[H1] >>u $2)")
      .setOutput("($0 >>u $2)[H1]")
      .setVerifier(new amb())
      .compile();
   static EPatternCompiler.EPattern ld = EPatternCompiler.EPattern.create("Dual-slot Arm << High")
      .addInput(A("$2", "$1", "#3") + " | " + A("($2 - #3)", "$0", "#3") + pC("(#3 - $2)", "$0", "#3"))
      .setOutput(A("$2", "($0 .=. $1)", "(#3 * 2)") + "[H2]")
      .setVerifier((var0, var1) -> {
         IEImm var2 = (IEImm)var1.getMatchedLeaf(3, IEImm.class);
         return var2.getValueAsLong() == var2.getBitsize();
      })
      .compile();
   static EPatternCompiler.EPattern gp = EPatternCompiler.EPattern.create("Dual-slot Arm <<")
      .addInput(A("$2", "$0[H1]", "#3") + " .=. " + A("$2", "$0", "#4", "[H2]"))
      .setOutput(A("$2", "$0", "#4"))
      .setVerifier((var0, var1) -> {
         IEImm var2 = (IEImm)var1.getMatchedLeaf(3, IEImm.class);
         IEImm var3 = (IEImm)var1.getMatchedLeaf(4, IEImm.class);
         return var2.getValueAsLong() == var2.getBitsize() && var3.getValueAsLong() == var3.getBitsize() * 2;
      })
      .compile();
   static EPatternCompiler.EPattern oT = EPatternCompiler.EPattern.create("Dual-slot Arm >>u Low")
      .addInput(pC("$2", "$0", "#3") + " | " + pC("($2 - #3)", "$1", "#3") + A("(#3 - $2)", "$1", "#3"))
      .setOutput(pC("$2", "($0 .=. $1)", "(#3 * 2)") + "[H1]")
      .setVerifier((var0, var1) -> {
         IEImm var2 = (IEImm)var1.getMatchedLeaf(3, IEImm.class);
         return var2.getValueAsLong() == var2.getBitsize();
      })
      .compile();
   static EPatternCompiler.EPattern fI = EPatternCompiler.EPattern.create("Dual-slot Arm >>")
      .addInput(pC("$2", "$0", "#4", "[H1]") + " .=. " + pC("$2", "$0[H2]", "#3"))
      .setOutput(pC("$2", "$0", "#4"))
      .setVerifier((var0, var1) -> {
         IEImm var2 = (IEImm)var1.getMatchedLeaf(3, IEImm.class);
         IEImm var3 = (IEImm)var1.getMatchedLeaf(4, IEImm.class);
         return var2.getValueAsLong() == var2.getBitsize() && var3.getValueAsLong() == var3.getBitsize() * 2;
      })
      .compile();
   static EPatternCompiler.EPattern WR = EPatternCompiler.EPattern.create("Dual-slot Mips >>u")
      .addInput("x V0 = $2 & 20h", "x V10 = $6 >>u $2", "> (((V0 == 0) ? ((($6 * 2) << (~ $2)) | ($5 >>u $2)) : V10).=.((V0 == 0) ? V10 : 0 ))")
      .setOutput("($5 .=. $6) >>u $2")
      .compile();
   static EPatternCompiler.EPattern NS = EPatternCompiler.EPattern.create("Dual-slot Mips >>")
      .addInput("x V0 = $2 & 20h", "x V10 = $6 >> $2", "> (((V0 == 0) ? ((($6 * 2) << (~ $2)) | ($5 >>u $2)) : V10).=.((V0 != 0) ? ($6 >> 1Fh) : V10 ))")
      .setOutput("($5 .=. $6) >> $2")
      .compile();
   static EPatternCompiler.EPattern vP = EPatternCompiler.EPattern.create("Dual-slot Mips <<")
      .addInput("x V0 = $2 & 20h", "x V10 = $5 << $2", "> (((V0 == 0) ? V10 : 0 ).=.((V0 == 0) ? ((($5 >>u 1) >>u (~ $2)) | ($6 << $2)) : V10))")
      .setOutput("($5 .=. $6) << $2")
      .compile();

   private static String pC(String var0, String var1, String var2) {
      return pC(var0, var1, var2, "");
   }

   private static String pC(String var0, String var1, String var2, String var3) {
      return "((" + var0 + " <u " + var2 + ") ? (" + var1 + " >>u " + var0 + ")" + var3 + " : 0)";
   }

   private static String A(String var0, String var1, String var2) {
      return A(var0, var1, var2, "");
   }

   private static String A(String var0, String var1, String var2, String var3) {
      return "((" + var0 + " <u " + var2 + ") ? (" + var1 + " << " + var0 + ")" + var3 + " : 0)";
   }
}
