package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface QW {
   boolean pC();

   boolean A();

   QW pC(Sp var1, long var2, com.pnfsoftware.jeb.corei.parsers.arm.rQ var4, boolean var5);

   zx pC(INativeCodeAnalyzer var1, com.pnfsoftware.jeb.corei.parsers.arm.Av var2, Sp var3);
}
