package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public abstract class Rx extends Ij {
   private final int wS;
   private final int UT;

   protected Rx(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
      this.wS = var1.getInt();
      this.UT = var1.getInt();
   }

   @Override
   protected final void kS(ByteBuffer var1) {
      super.kS(var1);
      var1.putInt(this.wS);
      var1.putInt(this.UT);
   }
}
