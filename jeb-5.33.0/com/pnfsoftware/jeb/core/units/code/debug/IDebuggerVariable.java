package com.pnfsoftware.jeb.core.units.code.debug;

public interface IDebuggerVariable {
   int FLAG_PUBLIC = 1;
   int FLAG_PRIVATE = 2;
   int FLAG_PROTECTED = 4;
   int FLAG_STATIC = 8;
   int FLAG_FINAL = 16;

   String getName();

   String getAlternateName();

   int getFlags();

   boolean canEditValue();

   ITypedValue getTypedValue();

   boolean setTypedValue(ITypedValue var1);

   boolean canEditType();

   boolean setTypeHint(String var1);

   String format();
}
