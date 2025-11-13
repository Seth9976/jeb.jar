package com.pnfsoftware.jebglobal;

import java.net.Socket;

public abstract class fC implements tz {
   protected Socket q;

   @Override
   public boolean q() {
      return this.q != null && this.q.isConnected() && !this.q.isClosed();
   }

   @Override
   public Socket a_() {
      return this.q;
   }
}
