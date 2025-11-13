package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICDecl extends ICLeftExpression, ICSourceElement, ICStatement {
   boolean isParameterDeclaration();

   ICType getType();

   ICIdentifier getIdentifier();

   String getName();

   ICDecl duplicate();
}
