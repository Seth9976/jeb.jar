package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cig extends cij {
   int pC;
   int A;
   int kS;

   public static cig pC(ByteBuffer var0) throws IOException {
      cig var1 = new cig();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var1.kS = var0.getShort() & '\uffff';
      var1.wS = Strings.decodeUTF8(Winunp.parseCString(var0));
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("sum_name=%X,symbol=%X,module=%X:%s", this.pC, this.A, this.kS, this.wS);
   }
}
