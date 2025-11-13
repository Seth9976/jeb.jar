package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cil extends cij {
   public static cil pC(ByteBuffer var0) throws IOException {
      cil var1 = new cil();
      var0.getInt();
      var0.getInt();
      var0.getShort();
      var1.wS = Strings.decodeUTF8(Winunp.parseCString(var0));
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("???:%s", this.wS);
   }
}
