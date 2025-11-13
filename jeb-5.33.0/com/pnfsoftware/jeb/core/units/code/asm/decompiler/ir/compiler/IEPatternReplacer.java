package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

@FunctionalInterface
public interface IEPatternReplacer {
   Boolean replace(EPatternMatcher var1, EPatternMatcher.Result var2);
}
