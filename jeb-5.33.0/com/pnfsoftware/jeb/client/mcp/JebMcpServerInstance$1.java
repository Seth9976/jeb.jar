package com.pnfsoftware.jeb.client.mcp;

class JebMcpServerInstance$1 extends Thread {
   @Override
   public void start() {
      if (JebMcpServerInstance.thread != null) {
         JebMcpServerInstance.stop();
      }
   }
}
