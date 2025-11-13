package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

@FunctionalInterface
public interface IEMatchVerifier {
   boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2);
}
