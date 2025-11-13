package com.pnfsoftware.jeb.util.net;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

class Net$1 extends Authenticator {
   Net$1(String var1, String var2) {
      this.val$user = var1;
      this.val$password = var2;
   }

   @Override
   protected PasswordAuthentication getPasswordAuthentication() {
      String var1 = this.getRequestingProtocol().toLowerCase();
      Object[] var10000 = new Object[]{var1, this.val$user, this.val$password};
      return new PasswordAuthentication(this.val$user, this.val$password.toCharArray());
   }
}
