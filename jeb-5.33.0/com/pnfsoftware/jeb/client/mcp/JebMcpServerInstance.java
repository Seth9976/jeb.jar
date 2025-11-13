package com.pnfsoftware.jeb.client.mcp;

import com.pnfsoftware.jeb.clienti.mcp.Av;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import java.net.BindException;

public class JebMcpServerInstance {
   public static final int DEFAULT_PORT = 8425;
   public static final String DEFAULT_ENDPOINT = "/mcp";
   private static volatile IJebMcpServer jebmcpserver;
   private static volatile Thread thread;

   public static synchronized IJebMcpServer get() {
      return jebmcpserver;
   }

   public static synchronized IJebMcpServer start() {
      return start(8425, "/mcp");
   }

   public static synchronized IJebMcpServer start(int var0, String var1) {
      if (jebmcpserver != null) {
         return jebmcpserver;
      } else {
         IEnginesContext var2 = JebCoreService.getDefaultEnginesContext();
         int var3 = var0;
         int var4 = 3;

         while (true) {
            try {
               jebmcpserver = new Av(var3, var1, var2);
               break;
            } catch (Exception var7) {
               if (var4-- >= 0) {
                  Throwable var6 = var7.getCause();
                  if (var6 instanceof BindException) {
                     var4--;
                     var3++;
                     continue;
                  }
               }

               return null;
            }
         }

         thread = ThreadUtil.start(() -> jebmcpserver.serve());
         return jebmcpserver;
      }
   }

   public static synchronized boolean stop() {
      if (thread == null) {
         return false;
      } else {
         thread.interrupt();

         try {
            thread.join(5000L);
            thread = null;
            return true;
         } catch (InterruptedException var0) {
            return false;
         }
      }
   }

   static {
      Runtime.getRuntime().addShutdownHook(new JebMcpServerInstance$1());
   }
}
