package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDTypeInfoProvider;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaTypeFactory {
   IJavaType getVoid();

   IJavaType getBoolean();

   IJavaType getByte();

   IJavaType getChar();

   IJavaType getShort();

   IJavaType getInt();

   IJavaType getLong();

   IJavaType getFloat();

   IJavaType getDouble();

   IJavaType getSingleSlotWildcard();

   IJavaType getDoubleSlotWildcard();

   IJavaType getSmallIntWildcard();

   IJavaType getGenericObjectWildcard();

   IJavaType getJavaLangObject();

   IJavaType getJavaLangClass();

   IJavaType getJavaLangString();

   IJavaType createType(String var1);

   IJavaType createArrayType(IJavaType var1, int var2);

   IJavaType parseType(String var1);

   IJavaType letterToType(char var1);

   IJavaType primitiveNameToType(String var1);

   IJavaType createWildcardType(String var1, boolean var2);

   IDTypeInfoProvider getTypeInfoProvider();
}
