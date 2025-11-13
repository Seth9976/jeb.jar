package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;

public interface IDynamicContentManager {
   long getObjectItemId(Object var1);

   String getComment(ICodeCoordinates var1);

   String getPreComment(ICodeCoordinates var1);

   String getMethodName(ICodeCoordinates var1);

   long getMethodItemId(ICodeCoordinates var1);

   long getLabelItemId(ICodeCoordinates var1);

   String getLabelName(ICodeCoordinates var1);

   void setLabelName(ICodeCoordinates var1, String var2);

   long getLocalVariableItemId(int var1, long var2);

   String getLocalVariableName(int var1, long var2);

   void setLocalVariableName(int var1, long var2, String var4);

   String getParamName(int var1, int var2);

   boolean setParamName(int var1, int var2, String var3);

   String getPackageOfMethod(int var1);

   long getTypeItemId(String var1);

   String getTypeSignature(String var1);

   long getSyntheticIdentifierItemId(int var1, int var2);

   long getStructureFieldItemId(String var1, int var2);

   String getStructureFieldName(String var1, int var2);

   String getNativeInstructionFormat(long var1);

   INativeStringItem getNativeString(int var1);

   String getPotentialDataAsString(long var1);
}
