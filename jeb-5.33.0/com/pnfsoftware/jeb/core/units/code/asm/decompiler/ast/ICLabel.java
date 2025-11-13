package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICLabel extends ICStatement {
   long getOffset();

   String getName();

   void generate(COutputSink var1, boolean var2);

   ICLabel duplicate();
}
