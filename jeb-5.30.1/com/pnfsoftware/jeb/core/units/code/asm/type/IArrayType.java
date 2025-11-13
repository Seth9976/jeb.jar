package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IArrayType extends INativeType {
   INativeType getElementType();

   int getElementCount();
}
