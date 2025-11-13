package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class ban {
   int pC;
   int A;
   int kS;
   azp[] wS;

   static ban pC(ByteBuffer var0) {
      ban var1 = new ban();
      var1.pC = var0.getShort() & '\uffff';
      var1.A = var0.getShort() & '\uffff';
      var1.kS = var0.getShort() & '\uffff';
      int var2 = var0.getShort() & '\uffff';
      var1.wS = new azp[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         var1.wS[var3] = azp.pC(var0);
      }

      return var1;
   }

   public int pC() {
      return this.pC;
   }

   public String pC(bai var1) {
      baf var2 = (baf)var1.pC(this.A);
      return var2.A();
   }

   public String A(bai var1) {
      baf var2 = (baf)var1.pC(this.kS);
      return var2.A();
   }
}
