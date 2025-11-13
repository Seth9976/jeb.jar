package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum InstructionFlags {
   CONDITIONAL_EXEC,
   INTERRUPT_EXEC,
   UNPREDICTABLE_INSN,
   ROUTINE_TERMINATOR;
}
