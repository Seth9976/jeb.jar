package com.pnfsoftware.jeb.corei.parsers.machofat;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class Sv {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;

   public static Sv pC(ByteBuffer var0) {
      Sv var1 = new Sv();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      return var1;
   }
}
