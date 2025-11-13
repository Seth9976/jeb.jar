package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum TypeCategory {
   VOID,
   POINTER,
   INTEGRAL,
   FLOAT,
   COMPLEX,
   VECTOR,
   COMPOSITE;
}
