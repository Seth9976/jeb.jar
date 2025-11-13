package com.pnfsoftware.jeb.core.units.code;

public interface ICodeItem {
   int FLAG_PUBLIC = 1;
   int FLAG_PRIVATE = 2;
   int FLAG_PROTECTED = 4;
   int FLAG_STATIC = 8;
   int FLAG_FINAL = 16;
   int FLAG_SYNCHRONIZED = 32;
   int FLAG_VOLATILE = 64;
   int FLAG_BRIDGE = 64;
   int FLAG_TRANSIENT = 128;
   int FLAG_VARARGS = 128;
   int FLAG_NATIVE = 256;
   int FLAG_INTERFACE = 512;
   int FLAG_ABSTRACT = 1024;
   int FLAG_STRICT = 2048;
   int FLAG_SYNTHETIC = 4096;
   int FLAG_ANNOTATION = 8192;
   int FLAG_ENUM = 16384;
   int FLAG_CONSTRUCTOR = 65536;
   int FLAG_DECLARED_SYNCHRONIZED = 131072;
   int FLAG_INNER = 1048576;
   int FLAG_ANONYMOUS = 2097152;
   int FLAG_VIRTUAL = 268435456;
   int FLAG_DESTRUCTOR = 536870912;
   int FLAG_ARTIFICIAL = 1073741824;
   int FLAG_INTERNAL = Integer.MIN_VALUE;

   long getItemId();

   int getIndex();

   String getAddress(boolean var1);

   String getAddress();

   String getName(boolean var1);

   String getName();

   String getSignature(boolean var1);

   String getSignature(boolean var1, boolean var2);

   String getSignature();

   boolean isInternal();

   boolean isArtificial();

   int getGenericFlags();
}
