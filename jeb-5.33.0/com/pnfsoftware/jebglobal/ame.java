package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;

public class ame {
   static EPatternCompiler.EPattern pC = EPatternCompiler.EPattern.create("Carry calc. var/imm normalization ")
      .setInput("(V0 + 1) _CARRY #2")
      .setOutput("V0 _CARRY (#2 + 1)")
      .compile(1);
   static EPatternCompiler.EPattern A = EPatternCompiler.EPattern.create().setInput("(V0 + 1) >=u #2").setOutput("V0 >=u (#2 - 1)").compile(1);
   static EPatternCompiler.EPattern kS = EPatternCompiler.EPattern.create("Dual-slot subtraction 1")
      .addInput("($0[H1] - V2[H1]) ... ($0[H2] + (- (($0[H1] <u V2[H1]) ... 0)) + (- V2[H2]))")
      .addInput("($0 - V2)[H1] ... ($0[H2] + (- (($0[H1] <u V2[H1]) ... 0)) + (- V2[H2]))")
      .setOutput("$0 - V2")
      .compile(1);
   static EPatternCompiler.EPattern wS = EPatternCompiler.EPattern.create("Dual-slot subtraction 2")
      .setInput("($0[H1] - #2) ... ($0[H2] - (($0[H1] <u #2) ... 0))")
      .setOutput("$0 - (#2 ... 0)")
      .compile(1);
   static EPatternCompiler.EPattern UT = EPatternCompiler.EPattern.create("Dual-slot subtraction 3")
      .setInput("($0[H1] - #2) ... ($0[H2] + (- (($0[H1] <u #2) ... 0)) + (- #3))")
      .setOutput("$0 - (#2 ... #3)")
      .compile(1);
   static EPatternCompiler.EPattern E = EPatternCompiler.EPattern.create("Dual-slot subtraction 3")
      .addInput("($0[H1] - $1[H1]) ... ($0[H2] + (~($1[H2])) + (($0[H1] >=u $1[H1]) ... 0) )")
      .addInput("($0[H1] - $1[H1]) ... (($0[H2] - $1[H2]) - ((($0[H1] - $1[H1]) >u $0[H1]) ... 0) )")
      .setOutput("$0 - $1")
      .compile();
   static EPatternCompiler.EPattern sY = EPatternCompiler.EPattern.create("Dual-slot subtraction 4")
      .addInput("x V4 = V2", "  V2 = ($0 - V2)", "> V3 = ($1 + (- (($0 <u V4) ... 0)) + (- V3))")
      .setOutput("V12 = V10 - V11")
      .setCustomReplacer(new alz.Av())
      .compile(3);
   static EPatternCompiler.EPattern ys = EPatternCompiler.EPattern.create("Dual-slot subtraction 5")
      .addInput("> V4 = ($0[H1] - V2[H1])", "  V5 = ($0[H2] + (- (($0[H1] <u V2[H1]) ... 0)) + (- V2[H2]))")
      .setOutput("V1 = $0 - V2")
      .setCustomReplacer(new alz.Sv())
      .compile(1);
   static EPatternCompiler.EPattern ld = EPatternCompiler.EPattern.create("Dual-slot subtraction 6")
      .addInput("  V4 = (V0 - #2)", "> V5 = (V1 - (($0 <u #2) ... 0))")
      .setOutput("V12 = V10 - #11")
      .setCustomReplacer(new alz.K())
      .compile(3);
}
