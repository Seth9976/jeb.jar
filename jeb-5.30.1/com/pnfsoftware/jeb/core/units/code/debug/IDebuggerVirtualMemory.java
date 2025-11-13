package com.pnfsoftware.jeb.core.units.code.debug;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;

public interface IDebuggerVirtualMemory extends IVirtualMemory {
   long findBytes(long var1, long var3, byte[] var5);
}
