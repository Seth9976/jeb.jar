package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

public class StandardMergeController implements IMergeController {
   @Override
   public void onMatch(int var1) {
   }

   @Override
   public boolean onMismatch(int var1) {
      return true;
   }

   @Override
   public boolean onOutputOnly(int var1) {
      return false;
   }

   @Override
   public boolean onInputOnly(int var1) {
      return false;
   }
}
