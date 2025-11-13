package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cpp extends cpn {
   public static final int q = 1;
   public static final int Dw = 2;
   int Uv;

   public static cpp q(ByteBuffer var0) throws IOException {
      cpp var1 = new cpp();
      var1.Uv = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getShort() & '\uffff';
      var1.oW = Strings.decodeUTF8(Winunp.parseCString(var0));
      return var1;
   }

   public int q() {
      return this.Uv;
   }

   @Override
   public String toString() {
      return Strings.ff("flags=%X,offset=%X,segment=%X:%s", this.Uv, this.RF, this.xK, this.oW);
   }
}
