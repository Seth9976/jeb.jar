package com.pnfsoftware.jebglobal;

import com.google.common.io.LittleEndianDataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cA extends Vz implements lZ {
   protected cA(LittleEndianDataInputStream var1, oV var2) throws IOException {
      super(PH.q(var1), var2);
   }

   protected cA(ByteBuffer var1, oV var2) {
      super(var1, var2);
   }

   @Override
   public void RF(boolean var1) {
   }
}
