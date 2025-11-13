package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cps extends cpn {
   int q;

   public static cps q(ByteBuffer var0) throws IOException {
      cps var1 = new cps();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getShort() & '\uffff';
      var1.oW = Strings.decodeUTF8(Winunp.parseCString(var0));
      return var1;
   }

   public int q() {
      return this.q;
   }

   @Override
   public String toString() {
      return Strings.ff("type=%X,offset=%X,segment=%X:%s", this.q, this.RF, this.xK, this.oW);
   }
}
