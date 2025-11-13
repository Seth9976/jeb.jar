package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterEncoding;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class vD {
   private static final ILogger q = GlobalLog.getLogger(vD.class);
   private pF RF = new pF(true);

   public vD(nO var1) throws IOException, WI {
      for (int var2 = 0; var2 < 1024; var2++) {
         String var3;
         try {
            var3 = var1.q(var2 + "");
         } catch (JG var20) {
            throw var20;
         } catch (WI var21) {
            break;
         }

         String var4 = null;
         String var5 = null;
         int var6 = 0;
         int var7 = -1;
         RegisterEncoding var8 = null;

         for (String var12 : var3.split(";")) {
            if (!var12.isEmpty()) {
               String[] var13 = var12.split(":");
               if (var13.length == 2) {
                  String var14 = var13[0];
                  String var15 = var13[1];
                  switch (var14) {
                     case "name":
                        var4 = var15;
                        break;
                     case "alt-name":
                        var5 = var15;
                        break;
                     case "bitsize":
                        var6 = Conversion.stringToInt(var15);
                        break;
                     case "offset":
                        var7 = Conversion.stringToInt(var15, -1);
                        break;
                     case "encoding":
                        try {
                           var8 = RegisterEncoding.valueOf(var15);
                        } catch (RuntimeException var19) {
                           q.error("Unknown register encoding: %s", var15);
                        }
                     case "format":
                     case "set":
                     case "ehframe":
                     case "dwarf":
                     case "generic":
                     case "container-regs":
                     case "invalidate-regs":
                        break;
                     default:
                        RuntimeException var18 = new RuntimeException("lldb reg layout, unknown key: " + var14);
                        JebCoreService.notifySilentExceptionToClient(var18);
                  }
               }
            }
         }

         this.RF.RF(var7, var6, var4, var5, var8);
      }

      this.RF.q();
   }

   public pF q() {
      return this.RF;
   }
}
