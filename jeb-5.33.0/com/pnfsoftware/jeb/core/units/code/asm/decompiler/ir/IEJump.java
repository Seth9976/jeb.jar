package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IEJump extends IEJumpWithOptionalCondition {
   int getFallthroughAddress(long var1);

   int getBranchAddress();

   void setBranchAddress(int var1);
}
