package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IEGroup extends IEGeneric {
   String getName();

   int getElementBitsize();

   int getElementsCount();

   List getElements();

   IEGeneric getElement(int var1);
}
