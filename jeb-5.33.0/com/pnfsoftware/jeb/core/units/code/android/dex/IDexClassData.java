package com.pnfsoftware.jeb.core.units.code.android.dex;

import java.util.List;

public interface IDexClassData {
   List getStaticFields();

   List getInstanceFields();

   List getDirectMethods();

   List getVirtualMethods();
}
