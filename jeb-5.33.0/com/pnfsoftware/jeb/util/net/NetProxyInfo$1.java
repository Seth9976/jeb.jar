package com.pnfsoftware.jeb.util.net;

import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

class NetProxyInfo$1 implements Authenticator {
   NetProxyInfo$1(NetProxyInfo var1, String var2, String var3) {
      this.this$0 = var1;
      this.val$user = var2;
      this.val$password = var3;
   }

   public Request authenticate(Route var1, Response var2) throws IOException {
      String var3 = Credentials.basic(this.val$user, this.val$password);
      Object[] var10000 = new Object[]{this.val$user, this.val$password};
      return var2.request().newBuilder().header("Proxy-Authorization", var3).build();
   }
}
