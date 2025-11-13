package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.ICodeType;
import java.util.List;

public interface IDexType extends ICodeType, IDexItem {
   IDexClass getImplementingClass();

   boolean isClass();

   boolean isArray();

   int getDimensions();

   boolean isPrimitive();

   boolean isArrayOfPrimitive();

   String getNonArrayClass();

   IDexType getMasterType();

   List getDependantTypes();

   @Override
   String getSignature();

   @Override
   String getSignature(boolean var1, boolean var2);

   String getSignature(boolean var1, boolean var2, boolean var3);
}
