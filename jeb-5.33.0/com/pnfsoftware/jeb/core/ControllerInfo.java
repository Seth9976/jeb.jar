package com.pnfsoftware.jeb.core;

import java.net.InetSocketAddress;

public class ControllerInfo {
   private InetSocketAddress address;
   private int protocol;
   private String clientMessage;

   public ControllerInfo(InetSocketAddress var1, int var2, String var3) {
      this.address = var1;
      this.protocol = var2;
      this.clientMessage = var3;
   }

   public InetSocketAddress getAddress() {
      return this.address;
   }

   public String getInterface() {
      return this.address.getHostString();
   }

   public int getPort() {
      return this.address.getPort();
   }

   public int getProtocol() {
      return this.protocol;
   }

   public boolean isSecure() {
      return this.protocol == 1;
   }

   public String getClientMessage() {
      return this.clientMessage;
   }
}
