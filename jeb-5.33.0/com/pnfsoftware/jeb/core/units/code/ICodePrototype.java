package com.pnfsoftware.jeb.core.units.code;

import java.util.List;

public interface ICodePrototype extends ICodeItem {
   ICodeType getReturnType();

   List getParameterTypes();

   boolean isVariableArgument();
}
