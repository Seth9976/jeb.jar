package com.pnfsoftware.jeb.core.units.code.asm.memory;

import java.util.List;

public interface ILazyMemoryProvider {
   List getRanges();

   void getData(long var1, int var3, byte[] var4, int var5) throws MemoryException;
}
