package com.pnfsoftware.jebglobal;

import com.google.common.io.LittleEndianDataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;

public class eH extends gT implements sh {
   boolean UT = true;

   protected eH(LittleEndianDataInputStream var1, Ij var2) throws IOException {
      super(Jn.pC(var1, 10), var2);
   }

   protected eH(ByteBuffer var1, Ij var2) {
      super(var1, var2);
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      if (this.UT) {
         Jn.pC(var1, this.wS.values(), var3 ? 1 : 0, true);
      } else {
         super.pC(var1, var2, var3);
      }
   }

   @Override
   public void A(boolean var1) {
      this.UT = var1;

      for (Ij var3 : this.wS.values()) {
         if (var3 instanceof sh) {
            ((sh)var3).A(var1);
         }
      }
   }

   @Override
   public Ij wS(ByteBuffer var1) {
      return Ij.pC(var1, this);
   }
}
