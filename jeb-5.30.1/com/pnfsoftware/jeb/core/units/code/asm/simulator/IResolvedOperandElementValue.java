package com.pnfsoftware.jeb.core.units.code.asm.simulator;

public interface IResolvedOperandElementValue {
   int TYPE_UNKNOWN = 0;
   int TYPE_POINTER = 1;

   int getType();

   byte[] getBytes();
}
