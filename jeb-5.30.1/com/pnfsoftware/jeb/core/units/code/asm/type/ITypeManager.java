package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListenable;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.base.ISimpleFilter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface ITypeManager extends INativeItemListenable {
   @Override
   void addListener(INativeItemListener var1);

   @Override
   void removeListener(INativeItemListener var1);

   ProcessorType getProcessorType();

   SubsystemType getSubsystemType();

   CompilerType getCompilerType();

   IPrimitiveTypeManager getPrimitives();

   ICallingConventionManager getCallingConventionManager();

   int getPointerSize();

   int getSlotSize();

   TypeLibraryService getTypeLibraryService();

   TypeStringParser getParser();

   Collection getTypes();

   Collection getTypes(ISimpleFilter var1);

   Collection getPrototypes();

   INativeType getType(String var1);

   INativeType getType(String var1, boolean var2);

   INativeType getType(String var1, boolean var2, boolean var3);

   INativeType getInteger(int var1, boolean var2);

   INativeType getExactInteger(int var1, boolean var2);

   INativeType getExactFloat(int var1);

   boolean deleteType(INativeType var1);

   IEnumerationType createEnumeration(String var1);

   IStructureType createStructureOrUnion(String var1, int var2, int var3);

   IStructureType createStructure(String var1);

   IStructureType createStructure(String var1, String... var2);

   IStructureTypeField addStructureField(IStructureType var1, String var2, String var3);

   IStructureTypeField addStructureField(IStructureType var1, String var2, INativeType var3);

   IStructureTypeField addStructureField(IStructureType var1, String var2, INativeType var3, int var4, int var5, int var6, int var7);

   boolean removeStructureField(IStructureType var1, IStructureTypeField var2);

   boolean renameStructureField(IStructureType var1, String var2, String var3);

   boolean renameStructureField(IStructureType var1, IStructureTypeField var2, String var3);

   IStructureType createUnion(String var1);

   IClassType createClassType(String var1);

   IClassType createClassType(String var1, int var2, int var3);

   Couple createVirtualTableDefinition(String var1, Long var2, Collection var3);

   void setClassVirtualTable(IClassType var1, IVirtualTableDefinition var2);

   void setClassSuperTypes(IClassType var1, Collection var2, Collection var3);

   void completeClassTypeInitialization(IClassType var1);

   IAliasType createAlias(String var1, INativeType var2);

   IAliasType createAlias(String var1, String var2);

   INativeType getVoid();

   IReferenceType getVoidReference();

   IReferenceType createReference(INativeType var1, int var2);

   IReferenceType createReference(INativeType var1);

   IArrayType createArray(String var1, int var2);

   IArrayType createArray(INativeType var1, int var2);

   IArrayType createArray(INativeType var1, int var2, boolean var3);

   IPrototypeItem createPrototype(INativeType var1, List var2);

   IPrototypeItem createPrototype(ICallingConvention var1, INativeType var2, List var3, Collection var4);

   IPrototypeItem createPrototypeEx(ICallingConvention var1, List var2, List var3, Collection var4);

   IPrototypeItem createPrototypeEx(String var1, List var2, List var3, Collection var4);

   void verify(INativeType var1);
}
