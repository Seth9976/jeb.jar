package com.pnfsoftware.jebglobal;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class tT extends Ij {
   private final List wS = new ArrayList();

   protected tT(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
   }

   @Override
   protected void pC(ByteBuffer var1) {
      super.pC(var1);
      this.wS.addAll(this.wS(var1));
   }

   private List wS(ByteBuffer var1) {
      int var2 = (this.wS() - this.kS()) / 4;
      ArrayList var3 = new ArrayList(var2);
      int var4 = this.kS + this.kS();
      var1.mark();
      var1.position(var4);

      for (int var5 = 0; var5 < var2; var5++) {
         var3.add(var1.getInt());
      }

      var1.reset();
      return var3;
   }

   @Override
   protected Ij.Av A() {
      return Ij.Av.gp;
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      super.pC(var1, var2, var3);

      for (Integer var5 : this.wS) {
         var1.writeInt(var5);
      }
   }
}
