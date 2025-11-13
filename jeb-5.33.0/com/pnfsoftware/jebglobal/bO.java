package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.JebNet;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.net.Net;

public class bO {
   private static String pC = ckx.pC(
      "115079707919157655794004271337785014085416524893510536896198880749104676170401151569284119272680555328442077552676617959280390278294721779275314259285460870759092640181498776671131410915393392138554764830318522728405842846940336918929510079155531877060531469699591700457820297645355241372954344694889513864783"
   );
   private static String A = ckx.pC(new byte[]{118, 3, 0, 6, 4}, 1, 64);
   private Net kS;
   private String wS;
   private int UT;
   private int E;
   private String sY;
   private String ys;

   public bO(Net var1, String var2, int var3, int var4, String var5) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (var2 == null || var2.isEmpty()) {
         throw new IllegalArgumentException("Controller Interface is not set");
      } else if (var3 > 0 && var3 < 65535) {
         this.kS = var1;
         this.wS = var2;
         this.UT = var3;
         this.E = var4;
         this.sY = Strings.safe(var5);
         this.ys = Strings.ff("%s://%s:%d/probe", var4 == 1 ? "https" : "http", var2, var3);
      } else {
         throw new IllegalArgumentException("Illegal Controller Port value must be between 0 and 65535");
      }
   }

   public String pC() {
      return this.sY;
   }

   public String pC(String var1) {
      return JebNet.post(this.kS, this.ys, var1);
   }

   public void A(String var1) {
      JebNet.post(this.kS, this.ys, var1);
   }
}
