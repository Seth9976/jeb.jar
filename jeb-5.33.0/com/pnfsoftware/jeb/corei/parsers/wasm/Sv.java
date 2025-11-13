package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Sv {
   @SerId(1)
   int pC;
   @SerId(2)
   qt A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;

   Sv(int var1, qt var2, int var3, int var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   public CharSequence pC(byte[] var1) {
      return Formatter.formatBinaryBlock(var1, this.kS, this.wS);
   }

   @Override
   public String toString() {
      return Strings.ff("mem_index=%d,target_offset=%s,src=%Xh(%Xh)", this.pC, this.A, this.kS, this.wS);
   }
}
