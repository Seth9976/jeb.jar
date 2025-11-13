package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cpt extends cpr {
   public static cpt q(ByteBuffer var0) throws IOException {
      cpt var1 = new cpt();
      var0.getInt();
      var0.getInt();
      var0.getShort();
      var1.oW = Strings.decodeUTF8(Winunp.parseCString(var0));
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("???:%s", this.oW);
   }
}
