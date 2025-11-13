package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface IWildcardPrototype extends INativeItem {
   IWildcardTypeManager getWildcardTypeManager();

   ICallingConvention getCallingConvention();

   IWildcardType getReturnType();

   List getReturnTypes();

   IWildcardType getReturnType(int var1);

   List getParameterTypes();

   IWildcardType getParameterType(int var1);

   boolean isVariableArgument();

   Collection getPrototypeAttributes();

   boolean hasPrototypeAttributes();

   IPrototypeItem resolve();
}
