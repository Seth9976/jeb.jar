package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum NativeFeatureSignerID {
   UNKNOWN,
   ROUTINE_CODE_HASH,
   CALLED_ROUTINE_NAME,
   ROUTINE_SIZE,
   ROUTINE_1B_CTE,
   CALLED_ROUTINE_NAME_ONLY_EXTERN;
}
