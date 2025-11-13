package com.pnfsoftware.jeb.core.units.code.asm;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchResolution;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface INativeContext {
   int getCodeContainerType();

   SubsystemType getSubsystemType();

   ICompiler getDetectedCompiler();

   void trackNativeItem(INativeType var1, IEventListener var2);

   IProcessor getProcessor();

   IVirtualMemory getMemory();

   ITypeManager getTypeManager();

   long getVirtualImageBase();

   long getImageSize();

   List getRoutines();

   INativeMethodItem getRoutine(long var1);

   INativeMethodItem getRoutineOver(long var1);

   INativeMethodItem getRoutineByName(String var1);

   List getFields();

   INativeFieldItem getField(long var1);

   INativeContinuousItem getNativeItemAt(long var1);

   INativeContinuousItem getNativeItemOver(long var1);

   INativeMethodItem createMethodReference(String var1, IPrototypeItem var2, INativeMethodDataItem var3);

   IBranchResolution getDynamicBranchResolution(long var1);

   boolean recordDynamicBranchTarget(long var1, boolean var3, IBranchTarget var4);

   void requestRoutineReanalysis(INativeMethodItem var1);
}
