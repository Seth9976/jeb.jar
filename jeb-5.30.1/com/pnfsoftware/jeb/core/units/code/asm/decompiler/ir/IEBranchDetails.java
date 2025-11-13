package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IEBranchDetails {
   List getDef();

   List getUse();

   List getSpoiled();

   List getDynamicTargetCandidates();

   boolean addCandidate(IBranchTarget var1);

   boolean addCandidate(int var1, IBranchTarget var2);

   boolean addCandidates(List var1);

   boolean setIncludeUnknownTarget(boolean var1);

   boolean isIncludeUnknownTarget();

   IPrototypeItem getNativePrototypeHint();

   boolean setNativePrototypeHint(IPrototypeItem var1);

   SPDDeterminer getStackPointerDeltaDeterminer();

   SPDC getStackPointerDelta();

   int getStackPointerDeltaValue();

   PreRoutineInvocationDetails getPreInvocationDetails();

   boolean setPreInvocationDetails(PreRoutineInvocationDetails var1);
}
