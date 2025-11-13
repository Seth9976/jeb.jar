package com.pnfsoftware.jebglobal;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class ao implements HostnameVerifier {
   ao(GA var1) {
      this.pC = var1;
   }

   @Override
   public boolean verify(String var1, SSLSession var2) {
      return true;
   }
}
