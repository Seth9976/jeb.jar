package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICOperatorFactory {
   ICOperator get(COperatorType var1);

   ICOperator createCastOperator(ICType var1);

   ICOperator createCustomOperator(String var1, int var2);
}
