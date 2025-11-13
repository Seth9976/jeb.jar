package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class cpk extends cpr {
   int q;
   int RF;
   int xK;

   public static cpk q(ByteBuffer var0) throws IOException {
      cpk var1 = new cpk();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getShort() & '\uffff';
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
      return Strings.ff("sum_name=%X,symbol=%X,module=%X", this.q, this.RF, this.xK);
   }
}
