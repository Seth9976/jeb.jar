package com.pnfsoftware.jebglobal;

import com.google.common.io.LittleEndianDataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class HN extends Ka implements sh {
   protected HN(LittleEndianDataInputStream var1, Ij var2) throws IOException {
      super(Jn.pC(var1), var2);
   }

   protected HN(ByteBuffer var1, Ij var2) {
      super(var1, var2);
   }

   @Override
   public void A(boolean var1) {
   }
}
