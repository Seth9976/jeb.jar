package com.pnfsoftware.jeb.client.telemetry;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandardTelemetryEndpoint implements ITelemetryEndpoint {
   private static final ILogger logger = GlobalLog.getLogger(StandardTelemetryEndpoint.class);
   private static final int maxRecordsPerRequest = 200;
   private long uuid;
   private INet net;
   private boolean allLooksGood;

   public StandardTelemetryEndpoint(INet var1, long var2) {
      this.net = var1;
      this.uuid = var2;
   }

   @Override
   public boolean canDump() {
      if (this.allLooksGood) {
         return true;
      } else {
         try {
            this.net.query("https://www.pnfsoftware.com/ping");
            this.allLooksGood = true;
            return true;
         } catch (IOException var2) {
            logger.catchingSilent(var2);
            this.allLooksGood = false;
            return false;
         }
      }
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   @Override
   public boolean dump(List var1) {
      if (var1.isEmpty()) {
         return true;
      } else {
         short var2;
         try {
            var2 = 0;
         } catch (Exception var8) {
            logger.catchingSilent(var8);
            return false;
         }

         while (true) {
            try {
               if (var2 >= var1.size()) {
                  return true;
               }

               if (var2 > 0) {
                  Thread.sleep(5000L);
               }

               try {
                  String var3 = Strings.ff(
                     "%s/telemetry/record?product=jeb&licenseid=%d&uuid=%d", "https://www.pnfsoftware.com", Licensing.license_id, this.uuid
                  );
                  HashMap var4 = new HashMap();
                  int var5 = Math.min(var1.size(), var2 + 200);
                  var4.put("records", Strings.encodeList(var1.subList(var2, var5)));
                  String var6 = this.net.post(var3, null, var4);
                  Object[] var10000 = new Object[]{var6};
               } catch (IOException var7) {
                  logger.catchingSilent(var7);
                  this.allLooksGood = false;
                  return false;
               }
            } catch (Exception var9) {
               logger.catchingSilent(var9);
               return false;
            }

            var2 += 200;
         }
      }
   }
}
