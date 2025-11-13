package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum DecompilationStatus {
   IN_PROCESS,
   NEED_RECONVERSION,
   COMPLETED;
}
