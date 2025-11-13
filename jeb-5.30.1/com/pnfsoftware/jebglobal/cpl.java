package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cpl extends cpr {
   int q;
   int RF;
   int xK;

   public static cpl q(ByteBuffer var0) throws IOException {
      cpl var1 = new cpl();
      var1.q = var0.getInt();
      var1.RF = var0.getShort() & '\uffff';
      var1.oW = Strings.decodeUTF8(Winunp.parseCString(var0));
      return var1;
   }

   public int q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
   }

   @Override
   public String toString() {
      return Strings.ff("type=%X,value=%X:%s", this.q, this.RF, this.oW);
   }
}
