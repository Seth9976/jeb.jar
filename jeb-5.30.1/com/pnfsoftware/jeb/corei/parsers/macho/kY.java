package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class kY implements ej {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;

   @Override
   public int q() {
      return this.q;
   }

   @Override
   public int RF() {
      return this.RF;
   }

   public static kY q(ByteBuffer var0) {
      kY var1 = new kY();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      return var1;
   }
}
