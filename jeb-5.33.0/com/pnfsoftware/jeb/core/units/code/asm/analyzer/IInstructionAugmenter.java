package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

public interface IInstructionAugmenter {
   boolean isArtificialEndOfBlock(long var1);

   boolean isReversedBranchSemantic(long var1);

   IBranchResolution getDynamicBranchResolution(long var1);

   IRegistersResolution getRegisterValueResolution(long var1);
}
