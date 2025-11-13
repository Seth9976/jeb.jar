package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cpu extends cpr {
   int q;

   public static cpu q(ByteBuffer var0) throws IOException {
      cpu var1 = new cpu();
      var1.q = var0.getInt();
      var1.oW = Strings.decodeUTF8(Winunp.parseCString(var0));
      return var1;
   }

   public int q() {
      return this.q;
   }

   @Override
   public String toString() {
      return Strings.ff("type=%X:%s", this.q, this.oW);
   }
}
