package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

public interface IMergeController {
   void onMatch(int var1);

   boolean onMismatch(int var1);

   boolean onOutputOnly(int var1);

   boolean onInputOnly(int var1);
}
