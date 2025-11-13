package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;

public interface IEOptFilterCanDiscard {
   boolean check(CFG var1, long var2, int var4);
}
