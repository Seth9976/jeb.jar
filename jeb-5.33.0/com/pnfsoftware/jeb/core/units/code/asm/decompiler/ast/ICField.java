package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICField extends ICDecompilableElement {
   String getName();

   ICType getType();

   ICType getOwnerType();

   void generateName(COutputSink var1, boolean var2);

   ICField duplicate();
}
