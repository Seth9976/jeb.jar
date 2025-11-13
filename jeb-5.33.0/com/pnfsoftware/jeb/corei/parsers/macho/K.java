package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class K implements Ws {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   long kS;
   @SerId(4)
   long wS;

   public static K pC(ByteBuffer var0) {
      K var1 = new K();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var1.kS = var0.getLong();
      var1.wS = var0.getLong();
      return var1;
   }
}
