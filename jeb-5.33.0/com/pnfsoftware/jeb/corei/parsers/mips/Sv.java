package com.pnfsoftware.jeb.corei.parsers.mips;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jebglobal.cfh;
import com.pnfsoftware.jebglobal.cfj;

public interface Sv extends IInstruction {
   ICodePointer pC(IMachineContext var1) throws ProcessorException;

   cfj[] pC();

   cfh A();

   byte[] kS();

   boolean wS();
}
