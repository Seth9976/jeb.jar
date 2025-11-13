package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJniEndpoint {
   boolean isStatic();

   String getMethodName();

   IUnit getUnit();

   Long getAddress();
}
