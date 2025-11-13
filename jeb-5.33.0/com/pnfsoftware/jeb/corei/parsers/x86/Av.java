package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface Av extends IInstructionOperandGeneric {
   boolean pC();

   boolean A();
}
