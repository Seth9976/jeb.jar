package com.pnfsoftware.jebglobal;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class CI implements HostnameVerifier {
   CI(Xa var1) {
      this.q = var1;
   }

   @Override
   public boolean verify(String var1, SSLSession var2) {
      return true;
   }
}
