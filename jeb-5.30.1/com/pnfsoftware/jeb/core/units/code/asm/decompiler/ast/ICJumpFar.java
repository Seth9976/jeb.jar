package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICJumpFar extends ICExpression, ICStatement {
   ICExpression getJumpsite();

   void setJumpsite(ICExpression var1);

   ICJumpFar duplicate();
}
