package com.pnfsoftware.jeb.util.concurrent;

public class DaemonThreadManager extends AbstractThreadManager {
   @Override
   public Thread create(Runnable var1) {
      Thread var2 = new Thread(var1);
      var2.setDaemon(true);
      return var2;
   }
}
