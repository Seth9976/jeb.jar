package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jebglobal.avy;

@SerDisabled
public enum NativeDecompilationStage {
   UNKNOWN,
   RAW_CONVERSION,
   IR_CONVERSION,
   SIMULATION,
   STACK_ANALYSIS,
   TYPES_APPLICATION,
   LIFTING,
   COMPLETED;

   public int getId() {
      avy var1 = avy.q(this);
      return var1 == null ? 0 : var1.q();
   }
}
