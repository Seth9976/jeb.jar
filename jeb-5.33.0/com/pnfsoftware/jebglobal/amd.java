package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;

public class amd {
   static EPatternCompiler.EPattern pC = EPatternCompiler.EPattern.create("Dual-slot multiplication")
      .addInput("(($0[H1] .=. 0) * ($1[H1] .=. 0)) + (0 .=. (($0[H2] * $1[H1]) + ($0[H1] * $1[H2])) )")
      .addInput("($0[H1] * $1[H1]) .=. (($0[H2] * $1[H1]) + ($0[H1] * $1[H2]) + (($0[H1] .=. 0 ) * ($1[H1] .=. 0))[H2])")
      .setOutput("$0 * $1")
      .compile();
}
