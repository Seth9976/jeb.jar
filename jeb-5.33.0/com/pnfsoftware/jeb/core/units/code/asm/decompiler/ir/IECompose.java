package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IECompose extends IEGeneric, Iterable {
   int getPartsCount();

   List getParts();

   IEGeneric getPart(int var1);

   IEGeneric getLowPart();

   IEGeneric getHighPart();

   void replacePart(int var1, IEGeneric var2);
}
