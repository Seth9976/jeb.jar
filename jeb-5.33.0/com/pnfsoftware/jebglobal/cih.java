package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cih extends cif {
   int pC;

   public static cih pC(ByteBuffer var0) throws IOException {
      cih var1 = new cih();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var1.kS = var0.getShort() & '\uffff';
      var1.wS = Strings.decodeUTF8(Winunp.parseCString(var0));
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("flags=%X,offset=%X,segment=%X:%s", this.pC, this.A, this.kS, this.wS);
   }
}
