package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class uX implements Ws {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;

   public static uX pC(ByteBuffer var0) {
      uX var1 = new uX();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      return var1;
   }
}
