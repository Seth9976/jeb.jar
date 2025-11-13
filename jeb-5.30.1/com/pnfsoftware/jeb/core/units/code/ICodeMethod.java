package com.pnfsoftware.jeb.core.units.code;

import java.util.List;

public interface ICodeMethod extends ICodeItem {
   ICodeType getClassType();

   ICodeType getReturnType();

   List getParameterTypes();

   List getInstructions();
}
