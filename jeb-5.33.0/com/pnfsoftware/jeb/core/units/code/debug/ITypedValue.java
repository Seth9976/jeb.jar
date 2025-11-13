package com.pnfsoftware.jeb.core.units.code.debug;

public interface ITypedValue {
   String TYPE_RAW = "raw";
   String TYPE_BOOLEAN = "boolean";
   String TYPE_BYTE = "byte";
   String TYPE_CHARACTER = "char";
   String TYPE_SHORT = "short";
   String TYPE_INTEGER = "int";
   String TYPE_LONG = "long";
   String TYPE_FLOAT = "float";
   String TYPE_DOUBLE = "double";
   String TYPE_STRING = "string";
   String TYPE_OBJECT = "object";
   String TYPE_ARRAY = "array";

   String getTypeName();

   String format();

   Object getValue();
}
