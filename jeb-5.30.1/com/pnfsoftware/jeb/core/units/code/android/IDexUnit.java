package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.tree.ICodeNode;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexPoolType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCallSite;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodHandle;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexPackage;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexPrototype;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexString;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.core.units.code.android.render.IDexDisassemblyDocument;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Ser
public interface IDexUnit extends ICodeUnit {
   int ACC_PUBLIC = 1;
   int ACC_PRIVATE = 2;
   int ACC_PROTECTED = 4;
   int ACC_STATIC = 8;
   int ACC_FINAL = 16;
   int ACC_SYNCHRONIZED = 32;
   int ACC_VOLATILE = 64;
   int ACC_BRIDGE = 64;
   int ACC_TRANSIENT = 128;
   int ACC_VARARGS = 128;
   int ACC_NATIVE = 256;
   int ACC_INTERFACE = 512;
   int ACC_ABSTRACT = 1024;
   int ACC_STRICT = 2048;
   int ACC_SYNTHETIC = 4096;
   int ACC_ANNOTATION = 8192;
   int ACC_ENUM = 16384;
   int ACC_CONSTRUCTOR = 65536;
   int ACC_DECLARED_SYNCHRONIZED = 131072;
   int INLINE_AUTO = 0;
   int INLINE_BLOCKED = 1;
   int INLINE_ALLOWED = 2;
   int INLINE_FORCED = 3;
   int itagItemIdShift = 56;
   int itagBitsize = 8;
   int ITAG_STRING = 1;
   int ITAG_PACKAGE = 2;
   int ITAG_TYPE = 3;
   int ITAG_CLASS = 4;
   int ITAG_FIELD = 5;
   int ITAG_METHOD = 6;
   int ITAG_BYTECODE = 7;
   int ITAG_PARAMETER = 8;
   int ITAG_VARIABLE = 9;
   int ITAG_IMMEDIATE = 10;
   int ITAG_VIRTUAL_VAR = 11;
   int ITAG_CUSTOM = 16;
   String propnameContextInfoDb = "ContextInfoDb";

   int getCountOfDexEntries();

   default int getCountOfDexFiles() {
      return this.getCountOfDexEntries();
   }

   List getDexEntries();

   default List getDexFiles() {
      return this.getDexEntries();
   }

   IDexFile getDexEntry(int var1);

   default IDexFile getDexFile(int var1) {
      return this.getDexEntry(var1);
   }

   int findStringIndex(String var1);

   @Override
   List getStrings();

   IDexString getString(int var1);

   int getStringCount();

   IDexString addString(String var1);

   List getPrototypes();

   IDexPrototype getPrototype(int var1);

   IDexPrototype addPrototype(String var1);

   @Override
   List getTypes();

   IDexType getType(int var1);

   IDexType getType(String var1);

   IDexType addType(String var1);

   int getBadTypeCount();

   IDexClass getClass(String var1);

   @Override
   List getClasses();

   IDexClass getClass(int var1);

   @Override
   List getFields();

   IDexField getField(String var1);

   IDexField getField(int var1);

   IDexValue getStaticFieldInitializer(int var1);

   IDexField addField(String var1);

   IDexField addField(String var1, String var2, String var3);

   @Override
   List getMethods();

   IDexMethod getMethod(String var1);

   IDexMethod getMethod(int var1);

   IDexMethod addMethod(String var1);

   IDexMethod addMethod(String var1, String var2, String var3);

   IDexCallSite getCallSite(int var1);

   List getCallSites();

   IDexMethodHandle getMethodHandle(int var1);

   List getMethodHandles();

   @Override
   List getPackages();

   IDexPackage getPackage(String var1);

   IDexPackage addPackage(String var1);

   boolean moveTo(IDexItem var1, IDexItem var2, boolean var3, boolean var4);

   default boolean moveTo(IDexItem var1, IDexItem var2) {
      return this.moveTo(var1, var2, false, false);
   }

   long getInstructionCount();

   IDexDisassemblyDocument getDisassemblyDocument();

   @Override
   String getDisassembly();

   Collection getCrossReferences(DexPoolType var1, int var2, int var3);

   Collection getCrossReferences(DexPoolType var1, int var2);

   IDexReferenceManager getReferenceManager();

   DexCommentManager getCommentManager();

   Map getRenamedIdentifiers();

   Object getObjectById(long var1);

   void addDex(IInput var1) throws IOException;

   DexConstantLibrary getConstantsLibrary();

   IDexContextInfoProvider getContextInfoProvider();

   IDexDecompilerUnit getDecompiler();

   ICodeNode getTypeHierarchy(String var1, int var2, boolean var3);
}
