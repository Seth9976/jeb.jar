package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICConstant extends ICExpression {
   Object getValue();

   ICType getType();

   boolean isTrueLike();

   void setOrigin(String var1);

   String getOrigin();

   ICConstant duplicate();
}
