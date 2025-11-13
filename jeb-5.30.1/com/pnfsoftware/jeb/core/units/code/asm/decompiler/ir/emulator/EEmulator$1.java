package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;

class EEmulator$1 implements EState.PointerSanitizer {
   EEmulator$1(EEmulator var1) {
      this.this$0 = var1;
   }

   @Override
   public long process(long var1) {
      return var1 & 281474976710655L;
   }
}
