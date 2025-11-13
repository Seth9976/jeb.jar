package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.java.IJavaType;

public interface IDAllocObjectInfo extends IDInvokeInfo {
   IJavaType getObjectType();

   IDAllocObjectInfo duplicate();
}
