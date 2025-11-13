package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEPatternOptimizer;
import java.util.Arrays;
import java.util.Collection;

public class auf extends AbstractEPatternOptimizer {
   static EPatternCompiler.EPattern q = EPatternCompiler.EPattern.create("Obfuscated integer additions")
      .addInput("0 - ((0 - $0) - $1)")
      .addInput("0 - (0 - ($0 + $1))")
      .addInput("($0 & $1) + ($0 | $1)")
      .addInput("(($0 & $1) * 2) + ($0 ^ $1)")
      .addInput("(($0 | $1) * 2) - ($0 ^ $1)")
      .setOutput("$0 + $1")
      .compile(2);
   static EPatternCompiler.EPattern RF = EPatternCompiler.EPattern.create("Obfuscated integer subtractions")
      .addInput("0 - ((0 - $0) + $1)")
      .addInput("0 - (0 - ($0 - $1))")
      .addInput("($0 & (~ $1)) - ((~ $0) & $1)")
      .addInput("($0 ^ $1) - (2 * ((~ $0) & $1))")
      .addInput("(2 * ($0 & (~ $1))) - ($0 ^ $1)")
      .setOutput("$0 - $1")
      .compile(2);
   static EPatternCompiler.EPattern xK = EPatternCompiler.EPattern.create("Obfuscated bitwise-xor [with conditions]")
      .addInput("($0 + $1 + (- (($0 & $1) * 2)))")
      .addInput("((2 * ($0 | $1)) + (- $0) + (- $1))")
      .addInput("($0 + (- $1) + (2 * ((~ $0) & $1)))")
      .setOutput("$0 ^ $1")
      .compile(2);
   static EPatternCompiler.EPattern Dw = EPatternCompiler.EPattern.create("a+b-a=b (and variants)").addInput("($0 + $1 + (- $0))").setOutput("$1").compile(258);
   static EPatternCompiler.EPattern Uv = EPatternCompiler.EPattern.create("((x&a)|b)-^((x&a)|c) => b-c [with conditions]")
      .addInput("(($0 & #1) | #2) _ncsub (($0 & #1) | #3)")
      .setOutput("#2 - #3")
      .setVerifier(new aug())
      .compile(258);
   static EPatternCompiler.EPattern oW = EPatternCompiler.EPattern.create("((x&a)^b)+(x&b) => (x&a)|b [with conditions]")
      .addInput("(($0 & #1) ^ #2) + ($0 & #2)")
      .setOutput("($0 & #1) | #2")
      .setVerifier(new auh())
      .compile(2);
   static EPatternCompiler.EPattern gO = EPatternCompiler.EPattern.create("((x&a)^b)|(x&b) => (x&a)|b")
      .addInput("(($0 & #1) ^ #2) | ($0 & #2)")
      .setOutput("($0 & #1) | #2")
      .compile(2);
   static EPatternCompiler.EPattern nf = EPatternCompiler.EPattern.create("(x&a)|b + (x&a)^c => b+c [with conditions]")
      .addInput("(($0 & #1) | #2) _ncadd (($0 & #1) ^ #3)")
      .setOutput("#4")
      .setVerifier(new aui())
      .compile(258);
   static EPatternCompiler.EPattern gP = EPatternCompiler.EPattern.create("(~x&a)|b + (x&a)|c => a+b+c [with conditions]")
      .addInput("(((~ $0) & #1) | #2) _ncadd (($0 & #1) | #3)")
      .setOutput("#4")
      .setVerifier(new auj())
      .compile(258);
   static EPatternCompiler.EPattern za = EPatternCompiler.EPattern.create("(x+a)-(x|a) => (x&a)==0?0:a [with a a power of 2]")
      .addInput("($0 + #1) - ($0 | #1)")
      .addInput("($0 - ($0 | #1)) + #1")
      .setOutput("(($0 & #1) == 0) ? 0 : #1")
      .setVerifier(new auk())
      .compile(258);
   static EPatternCompiler.EPattern lm = EPatternCompiler.EPattern.create("((x & a) ^ b) + ((((~x | c) & d) | (x & e)) ^ f) => b+(d^f) [with conditions]")
      .addInput("(($0 & #1) ^ #2) + (((((~ $0) | #3) & #4) | ($0 & #5)) ^ #6)")
      .setOutput("#2 + (#4 ^ #6)")
      .setVerifier(new aul())
      .compile(258);

   public auf() {
      super(false);
      this.addTag("deobfuscator");
   }

   @Override
   protected Collection getPatterns() {
      return Arrays.asList(q, RF, xK, Dw, Uv, oW, gO, nf, gP, za, lm);
   }
}
