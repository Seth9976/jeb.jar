package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cid extends cij {
   int pC;
   int A;

   public static cid pC(ByteBuffer var0) throws IOException {
      cid var1 = new cid();
      var1.pC = var0.getInt();
      var1.A = var0.getShort() & '\uffff';
      var1.wS = Strings.decodeUTF8(Winunp.parseCString(var0));
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("type=%X,value=%X:%s", this.pC, this.A, this.wS);
   }
}
