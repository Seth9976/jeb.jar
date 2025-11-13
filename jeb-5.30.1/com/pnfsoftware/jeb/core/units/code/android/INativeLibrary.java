package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;

public interface INativeLibrary {
   IELFUnit getElfUnit();

   boolean isBad();

   long getMappingAddress();
}
