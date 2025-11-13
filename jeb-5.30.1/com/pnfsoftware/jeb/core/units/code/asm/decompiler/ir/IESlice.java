package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IESlice extends IEGeneric {
   IEGeneric getWholeExpression();

   IERange getRange();

   int getBitStart();

   int getBitEnd();
}
