package com.pnfsoftware.jeb.core.units.code.android.dex;

import java.util.List;

public interface IDexValue {
   int VALUE_BYTE = 0;
   int VALUE_SHORT = 2;
   int VALUE_CHAR = 3;
   int VALUE_INT = 4;
   int VALUE_LONG = 6;
   int VALUE_FLOAT = 16;
   int VALUE_DOUBLE = 17;
   int VALUE_METHOD_TYPE = 21;
   int VALUE_METHOD_HANDLE = 22;
   int VALUE_STRING = 23;
   int VALUE_TYPE = 24;
   int VALUE_FIELD = 25;
   int VALUE_METHOD = 26;
   int VALUE_ENUM = 27;
   int VALUE_ARRAY = 28;
   int VALUE_ANNOTATION = 29;
   int VALUE_NULL = 30;
   int VALUE_BOOLEAN = 31;

   int getType();

   boolean isNull();

   byte getByte();

   short getShort();

   char getChar();

   int getInt();

   long getLong();

   float getFloat();

   double getDouble();

   int getMethodTypeIndex();

   int getMethodHandleIndex();

   int getStringIndex();

   int getTypeIndex();

   int getFieldIndex();

   int getMethodIndex();

   int getEnumIndex();

   List getArray();

   IDexAnnotation getAnnotation();

   boolean getBoolean();
}
