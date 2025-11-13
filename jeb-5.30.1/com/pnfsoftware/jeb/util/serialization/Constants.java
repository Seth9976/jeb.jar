package com.pnfsoftware.jeb.util.serialization;

class Constants {
   static final int FLAG_MASK_V1 = -65344;
   static final int FLAG_MASK_V2 = -65408;
   static final int FLAG_MASK_V3 = -65408;
   static final int FLAG_NULL_OBJECT = 1;
   static final int FLAG_UNSERIALIZABLE_SUPERCLASS = 2;
   static final int FLAG_ALREADY_SERIALIZED = 4;
   static final int FLAG_SERIALIZED_DATA_FOLLOWS = 8;
   static final int FLAG_TYPEID_BY_NAME = 16;
   static final int FLAG_IS_ENUM = 32;
   static final int FLAG_TYPEID_BY_OBJREF = 64;
   static final int MASK_ARRAY_DIMENSIONS = 65280;
   static final int TYPE_ID_DELTA = 128;
}
