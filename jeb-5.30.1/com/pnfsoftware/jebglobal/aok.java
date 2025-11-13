package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;

public class aok {
   static EPatternCompiler.EPattern q = EPatternCompiler.EPattern.create("Dual-slot >=")
      .addInput("($1 >  $3) || (($1 == $3) && ($0 >=u $2))")
      .addInput("($1 >= $3) && (($1 != $3) || ($0 >=u $2))")
      .setOutput("($0 ... $1) >= ($2 ... $3)")
      .compile();
   static EPatternCompiler.EPattern RF = EPatternCompiler.EPattern.create("Dual-slot >")
      .addInput("($1 >  $3) || (($1 == $3) && ($0 >u $2))")
      .addInput("($1 >= $3) && (($1 != $3) || ($0 >u $2))")
      .setOutput("($0 ... $1) > ($2 ... $3)")
      .compile();
   static EPatternCompiler.EPattern xK = EPatternCompiler.EPattern.create("Dual-slot <=")
      .addInput("($1 <  $3) || (($1 == $3) && ($0 <=u $2))")
      .addInput("($1 <= $3) && (($1 != $3) || ($0 <=u $2))")
      .setOutput("($0 ... $1) <= ($2 ... $3)")
      .compile();
   static EPatternCompiler.EPattern Dw = EPatternCompiler.EPattern.create("Dual-slot <")
      .addInput("($1 <  $3) || (($1 == $3) && ($0 <u $2))")
      .addInput("($1 <= $3) && (($1 != $3) || ($0 <u $2))")
      .setOutput("($0 ... $1) < ($2 ... $3)")
      .compile();
   static EPatternCompiler.EPattern Uv = EPatternCompiler.EPattern.create("Dual-slot ==")
      .addInput("($0[H2] !=  $1[H2]) || ($0[H1] !=  $1[H1])")
      .setOutput("$0 != $1")
      .compile();
}
