package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICThrow extends ICTerminalStatement {
   @Override
   ICExpression getExpression();

   void setExpression(ICExpression var1);
}
