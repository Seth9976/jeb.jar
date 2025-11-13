package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IBinaryPattern {
   byte[] getBinary();

   byte[] getMask();

   int getRealStartOffset();

   int getProcessorMode();

   boolean validate(INativeCodeAnalyzer var1, long var2, byte[] var4, int var5, int var6);

   Object getExtra();
}
