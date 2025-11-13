package com.pnfsoftware.jeb.corei.parsers.machofat;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class CU {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;

   public static CU q(ByteBuffer var0) {
      CU var1 = new CU();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      return var1;
   }
}
