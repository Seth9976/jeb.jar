package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;

public class amc {
   static EPatternCompiler.EPattern pC = EPatternCompiler.EPattern.create("Dual-slot >=")
      .addInput("($1 >  $3) || (($1 == $3) && ($0 >=u $2))")
      .addInput("($1 >= $3) && (($1 != $3) || ($0 >=u $2))")
      .setOutput("($0 ... $1) >= ($2 ... $3)")
      .compile();
   static EPatternCompiler.EPattern A = EPatternCompiler.EPattern.create("Dual-slot >")
      .addInput("($1 >  $3) || (($1 == $3) && ($0 >u $2))")
      .addInput("($1 >= $3) && (($1 != $3) || ($0 >u $2))")
      .setOutput("($0 ... $1) > ($2 ... $3)")
      .compile();
   static EPatternCompiler.EPattern kS = EPatternCompiler.EPattern.create("Dual-slot <=")
      .addInput("($1 <  $3) || (($1 == $3) && ($0 <=u $2))")
      .addInput("($1 <= $3) && (($1 != $3) || ($0 <=u $2))")
      .setOutput("($0 ... $1) <= ($2 ... $3)")
      .compile();
   static EPatternCompiler.EPattern wS = EPatternCompiler.EPattern.create("Dual-slot <")
      .addInput("($1 <  $3) || (($1 == $3) && ($0 <u $2))")
      .addInput("($1 <= $3) && (($1 != $3) || ($0 <u $2))")
      .setOutput("($0 ... $1) < ($2 ... $3)")
      .compile();
   static EPatternCompiler.EPattern UT = EPatternCompiler.EPattern.create("Dual-slot ==")
      .addInput("($0[H2] !=  $1[H2]) || ($0[H1] !=  $1[H1])")
      .setOutput("$0 != $1")
      .compile();
}
