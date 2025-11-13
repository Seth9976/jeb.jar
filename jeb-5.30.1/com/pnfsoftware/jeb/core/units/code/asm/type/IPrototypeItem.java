package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.code.ICodePrototype;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface IPrototypeItem extends ICodePrototype, INativeType {
   int getCountOfReturns();

   INativeType getReturnType();

   List getReturnTypes();

   int getCountOfParameters();

   @Override
   List getParameterTypes();

   List getParameterNames();

   String getRoutineName();

   ICallingConvention getCallingConvention();

   Collection getPrototypeAttributes();

   boolean isNoReturn();
}
