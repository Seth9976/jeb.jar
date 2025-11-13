package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEPatternOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Arrays;
import java.util.Collection;

public class atc extends AbstractEPatternOptimizer {
   private static final StructuredLogger RF = aeg.q(atc.class);
   static EPatternCompiler.EPattern q = EPatternCompiler.EPattern.create("(N+1)*N is an even number")
      .addInput("(($0 + 1) *  $0) &  1")
      .addInput("(($0 - 1) *  $0) &  1")
      .addInput("(($0 + 1) *  $0) %  2")
      .addInput("(($0 - 1) *  $0) %  2")
      .addInput("(($0 + 1) *  $0) %u 2")
      .addInput("(($0 - 1) *  $0) %u 2")
      .setOutput("0")
      .compile();

   public atc() {
      this.addTag("deobfuscator");
   }

   @Override
   protected Collection getPatterns() {
      return Arrays.asList(q);
   }
}
