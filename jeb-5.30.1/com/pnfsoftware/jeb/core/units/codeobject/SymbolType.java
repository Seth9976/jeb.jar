package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum SymbolType {
   UNKNOWN,
   NOTYPE,
   FILE,
   SEGMENT,
   SECTION,
   OBJECT,
   VARIABLE,
   FUNCTION,
   PTROBJECT,
   PTRVARIABLE,
   PTRFUNCTION,
   FORWARDED_FUNCTION,
   EXTERN_FUNCTION,
   EXTERN_DATA,
   CODE,
   FUNCTION_MAYBE;

   public boolean isExtern() {
      return this == EXTERN_FUNCTION || this == EXTERN_DATA;
   }
}
