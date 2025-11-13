package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICArrayElement extends ICLeftExpression {
   ICExpression getArray();

   ICExpression getElementIndex();

   void setArray(ICExpression var1);

   void setEltIndex(ICExpression var1);

   ICArrayElement duplicate();
}
