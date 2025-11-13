package com.pnfsoftware.jeb.util.net;

import java.net.Proxy.Type;

// $VF: synthetic class
class NetProxyInfo$2 {
   static {
      try {
         $SwitchMap$java$net$Proxy$Type[Type.DIRECT.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$java$net$Proxy$Type[Type.HTTP.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }

      try {
         $SwitchMap$java$net$Proxy$Type[Type.SOCKS.ordinal()] = 3;
      } catch (NoSuchFieldError var0) {
      }
   }
}
