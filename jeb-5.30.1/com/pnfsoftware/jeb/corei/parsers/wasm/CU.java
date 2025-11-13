package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class CU {
   @SerId(1)
   int q;
   @SerId(2)
   Vj RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;

   CU(int var1, Vj var2, int var3, int var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   public CharSequence q(byte[] var1) {
      return Formatter.formatBinaryBlock(var1, this.xK, this.Dw);
   }

   @Override
   public String toString() {
      return Strings.ff("mem_index=%d,target_offset=%s,src=%Xh(%Xh)", this.q, this.RF, this.xK, this.Dw);
   }
}
