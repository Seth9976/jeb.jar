package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IEGroupElt extends IEGeneric {
   IEGroup getGroup();

   IEGeneric getIndex();
}
