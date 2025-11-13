package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class bee {
   int q;
   int RF;
   int xK;
   bdh[] Dw;

   static bee q(ByteBuffer var0) {
      bee var1 = new bee();
      var1.q = var0.getShort() & '\uffff';
      var1.RF = var0.getShort() & '\uffff';
      var1.xK = var0.getShort() & '\uffff';
      int var2 = var0.getShort() & '\uffff';
      var1.Dw = new bdh[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         var1.Dw[var3] = bdh.q(var0);
      }

      return var1;
   }

   public int q() {
      return this.q;
   }

   public String q(beb var1) {
      bdy var2 = (bdy)var1.RF(this.RF);
      return var2.xK();
   }

   public String RF(beb var1) {
      bdy var2 = (bdy)var1.RF(this.xK);
      return var2.xK();
   }

   public bdh[] RF() {
      return this.Dw;
   }

   public String xK(beb var1) {
      return this.q(var1) + ":" + this.RF(var1);
   }
}
