package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.unicorn.UnicornLibrary;
import com.pnfsoftware.jeb.util.base.Couple;
import com.sun.jna.Pointer;

class Arm64EmuVerifier$2 implements UnicornLibrary.MemoryHook {
   Arm64EmuVerifier$2(Arm64EmuVerifier var1) {
      this.this$0 = var1;
   }

   @Override
   public int callback(Pointer var1, int var2, long var3, int var5, long var6, Pointer var8) {
      if (var2 != 16) {
         if (var2 == 17) {
            this.this$0.lastMemWrites.add(new Couple(var3, var5));
         } else if (var2 == 21 || var2 == 19 || var2 == 20) {
            long var9 = var3 & ~(this.this$0.pagesize - 1);
            boolean var11 = this.this$0.loadMemoryPage(var9);
            return var11 ? 1 : 0;
         }
      }

      return 0;
   }
}
