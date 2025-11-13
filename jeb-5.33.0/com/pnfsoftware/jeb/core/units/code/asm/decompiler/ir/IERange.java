package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IERange extends IEGeneric {
   int getBegin();

   int getEnd();

   int getRangeLength();

   IERange shift(int var1);
}
