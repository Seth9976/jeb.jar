package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cic extends cij {
   int pC;
   int A;
   int kS;

   public static cic pC(ByteBuffer var0) throws IOException {
      cic var1 = new cic();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var1.kS = var0.getShort() & '\uffff';
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("sum_name=%X,symbol=%X,module=%X", this.pC, this.A, this.kS);
   }
}
