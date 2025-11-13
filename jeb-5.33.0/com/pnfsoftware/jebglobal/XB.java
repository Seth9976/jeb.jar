package com.pnfsoftware.jebglobal;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public final class XB extends Ij {
   private final Ij.Av wS;
   private final byte[] UT;
   private final byte[] E;

   protected XB(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
      this.wS = Ij.Av.pC(var1.getShort(this.kS));
      this.UT = new byte[this.pC - 8];
      this.E = new byte[this.A - this.pC];
      var1.get(this.UT);
      var1.get(this.E);
   }

   @Override
   protected void kS(ByteBuffer var1) {
      var1.put(this.UT);
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      var1.write(this.E);
   }

   @Override
   protected Ij.Av A() {
      return this.wS;
   }
}
