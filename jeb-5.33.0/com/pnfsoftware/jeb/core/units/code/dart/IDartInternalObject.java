package com.pnfsoftware.jeb.core.units.code.dart;

import java.util.Map;

public interface IDartInternalObject {
   int getClassId();

   boolean isBase();

   Map getAttributes();

   Object getAttribute(String var1);
}
