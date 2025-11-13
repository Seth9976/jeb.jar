package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.JebNet;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.net.Net;

public class oM {
   private static String q = cvm.q(
      "115079707919157655794004271337785014085416524893510536896198880749104676170401151569284119272680555328442077552676617959280390278294721779275314259285460870759092640181498776671131410915393392138554764830318522728405842846940336918929510079155531877060531469699591700457820297645355241372954344694889513864783"
   );
   private static String RF = cvm.q(new byte[]{-78, 3, 0, 6, 4}, 1, 132);
   private Net xK;
   private String Dw;
   private int Uv;
   private String oW;

   public oM(Net var1, String var2, int var3, int var4) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (var2 == null || var2.isEmpty()) {
         throw new IllegalArgumentException("Controller Interface is not set");
      } else if (var3 > 0 && var3 < 65535) {
         this.xK = var1;
         this.Dw = var2;
         this.Uv = var3;
         this.oW = Strings.ff("%s://%s:%d/probe", var4 == 1 ? "https" : "http", var2, var3);
      } else {
         throw new IllegalArgumentException("Illegal Controller Port value must be between 0 and 65535");
      }
   }

   public String q() {
      return this.Dw;
   }

   public int RF() {
      return this.Uv;
   }

   public static int xK() {
      return 23477;
   }

   public static String Dw() {
      return RF;
   }

   public static String Uv() {
      return q;
   }

   public String q(String var1) {
      return JebNet.post(this.xK, this.oW, var1);
   }

   public void RF(String var1) {
      JebNet.post(this.xK, this.oW, var1);
   }
}
