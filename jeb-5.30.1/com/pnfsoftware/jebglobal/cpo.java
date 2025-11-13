package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cpo extends cpr {
   int q;
   int RF;
   int xK;

   public static cpo q(ByteBuffer var0) throws IOException {
      cpo var1 = new cpo();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getShort() & '\uffff';
      var1.oW = Strings.decodeUTF8(Winunp.parseCString(var0));
      return var1;
   }

   public int q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
   }

   public int xK() {
      return this.xK;
   }

   @Override
   public String toString() {
      return Strings.ff("sum_name=%X,symbol=%X,module=%X:%s", this.q, this.RF, this.xK, this.oW);
   }
}
