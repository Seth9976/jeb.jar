package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICControlBreaker extends ICStatement {
   ICLabel getLabel();

   void setLabel(ICLabel var1);

   ICControlBreaker duplicate();
}
