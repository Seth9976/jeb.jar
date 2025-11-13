package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;

public interface dW extends IInsnEmulator {
   int pC(int var1, long var2);

   boolean pC();

   boolean kS();

   boolean wS();

   int getFlags();

   dW A(Yg[] var1, int var2);

   Long pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, IMachineContext var4) throws ProcessorException;
}
