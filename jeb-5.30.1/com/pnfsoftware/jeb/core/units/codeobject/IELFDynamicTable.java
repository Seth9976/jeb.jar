package com.pnfsoftware.jeb.core.units.codeobject;

import java.util.List;

public interface IELFDynamicTable {
   List getPreInitializers();

   List getInitializers();

   List getFinalizers();

   String getLibraryName();

   String getSearchPath();

   List getRequiredLibs();
}
