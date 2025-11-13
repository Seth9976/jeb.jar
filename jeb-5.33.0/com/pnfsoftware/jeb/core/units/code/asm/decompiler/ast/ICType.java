package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICType extends ICElement {
   String getSignature();

   String getBaseTypeSignature();

   boolean isVoid();

   boolean isAlias();

   boolean isFunctionPointer();

   boolean isReference();

   boolean isSynthetic();

   ICType duplicate();
}
