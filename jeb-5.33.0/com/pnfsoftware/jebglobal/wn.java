package com.pnfsoftware.jebglobal;

import java.net.Socket;

public abstract class wn implements jJ {
   protected Socket pC;

   @Override
   public boolean pC() {
      return this.pC != null && this.pC.isConnected() && !this.pC.isClosed();
   }

   @Override
   public Socket a_() {
      return this.pC;
   }
}
