package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class cq implements Ws {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;

   public static cq pC(ByteBuffer var0) {
      cq var1 = new cq();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var1.kS = var0.getInt();
      var1.wS = var0.getInt();
      return var1;
   }
}
