package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface INativeCodeModel extends IInstructionAugmenter, IMemoryModel {
   boolean isBasicBlockHeader(long var1);

   BasicBlock getBasicBlockHeader(long var1);

   boolean isRoutineHeader(long var1);

   List getContainedRoutineAddresses(long var1);

   List getRoutineAddresses();

   IReferenceManager getReferenceManager();

   ICallGraphManager getCallGraphManager();
}
