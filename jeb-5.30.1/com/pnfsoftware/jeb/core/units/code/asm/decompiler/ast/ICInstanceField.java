package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICInstanceField extends ICLeftExpression {
   String getFieldAddress();

   ICField getField();

   ICExpression getInstance();

   boolean isPointed();

   ICInstanceField duplicate();
}
