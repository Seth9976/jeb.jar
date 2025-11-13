package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICallingConventionManager {
   ProcessorType getProcessorType();

   SubsystemType getSubsystemType();

   CompilerType getCompilerType();

   ICallingConvention getDefaultConvention();

   void setDefaultConvention(ICallingConvention var1);

   ICallingConvention getConvention(String var1);

   List getConventions();

   List getAllConventions();

   boolean addConvention(ICallingConvention var1);

   boolean removeConvention(ICallingConvention var1);
}
