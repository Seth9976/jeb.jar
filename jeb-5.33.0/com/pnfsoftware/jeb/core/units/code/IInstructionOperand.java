package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IInstructionOperand {
   String format(IInstruction var1, long var2);
}
