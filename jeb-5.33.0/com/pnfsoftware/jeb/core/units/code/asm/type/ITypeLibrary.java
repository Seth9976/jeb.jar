package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.base.ISimpleFilter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface ITypeLibrary {
   ProcessorType getPrimaryProcessorType();

   List getProcessorTypes();

   SubsystemType getPrimarySubsystemType();

   List getSubsystemTypes();

   CompilerType getCompilerType();

   int getGroupId();

   double getPriorityOrder();

   int getUuid();

   int getVersion();

   long getCreationTimestamp();

   String getName();

   String getDescription();

   String getAuthor();

   Collection getTypes();

   Collection getTypes(ISimpleFilter var1);

   Collection getRoutines();

   CodeConstantManager getConstantManager();
}
