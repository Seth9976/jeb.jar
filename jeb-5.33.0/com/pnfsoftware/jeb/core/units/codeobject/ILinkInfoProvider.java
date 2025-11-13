package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;

public interface ILinkInfoProvider {
   long resolveImportedSymbol(String var1, int var2, IVirtualMemory var3, IELFUnit var4);
}
