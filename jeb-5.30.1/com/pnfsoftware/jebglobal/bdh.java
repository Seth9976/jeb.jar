package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class bdh {
   int q;
   byte[] RF;

   public static bdh q(ByteBuffer var0) {
      bdh var1 = new bdh();
      var1.q = var0.getShort() & '\uffff';
      int var2 = var0.getInt();
      var1.RF = new byte[var2];
      var0.get(var1.RF);
      return var1;
   }

   public String q(beb var1) {
      bdy var2 = (bdy)var1.RF(this.q);
      return var2.xK();
   }

   public byte[] q() {
      return this.RF;
   }

   public String RF(beb var1) {
      return this.q(var1);
   }
}
