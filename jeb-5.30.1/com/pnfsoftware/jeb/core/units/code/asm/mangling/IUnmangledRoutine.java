package com.pnfsoftware.jeb.core.units.code.asm.mangling;

import java.util.List;

public interface IUnmangledRoutine extends IUnmangledData {
   String getName();

   String getNameWithParameters(boolean var1);

   String getReturnType();

   List getParameterTypes();

   String getCallingConvention();

   List getAttributes();

   default boolean isConst() {
      return this.getAttributes() != null && this.getAttributes().contains("const");
   }

   String getSignature(boolean var1);

   String getSignature(boolean var1, boolean var2);

   String getPrototype(boolean var1);

   String getPrototype(boolean var1, boolean var2);
}
