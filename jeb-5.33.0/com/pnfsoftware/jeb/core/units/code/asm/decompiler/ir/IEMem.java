package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IEMem extends IEGeneric {
   int FLAG_HINT_EXTERNAL = 256;

   IEGeneric getSegment();

   IEGeneric getReference();

   void setSegment(IEGeneric var1);

   void setReference(IEGeneric var1);
}
