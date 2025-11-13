package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IAliasType extends INativeType {
   INativeType getAliasedType();
}
