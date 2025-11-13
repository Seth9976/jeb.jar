package com.pnfsoftware.jeb.util.io;

class IO$1 extends Thread {
   @Override
   public synchronized void start() {
      IO.deleteDirectory(IO.safeTempFolder);
   }
}
