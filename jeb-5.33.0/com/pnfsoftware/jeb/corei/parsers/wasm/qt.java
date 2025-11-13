package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.chl;
import com.pnfsoftware.jebglobal.chm;
import java.util.List;

@Ser
public class qt {
   private static final ILogger A = GlobalLog.getLogger(qt.class);
   @SerId(1)
   List pC;

   qt(List var1) {
      if (var1 != null && !var1.isEmpty()) {
         this.pC = var1;
      } else {
         throw new NullPointerException();
      }
   }

   public long pC(Hv var1) throws chl {
      if (this.pC.size() != 1) {
         throw new chl();
      } else {
         m var2 = (m)this.pC.get(0);
         int var3 = var2.pC();
         switch (var3) {
            case 35:
               int var4 = var2.gp();
               rQ var5 = var1.A(var4);
               if (var5.E()) {
                  return var5.A.pC(var1);
               } else {
                  Long var6 = var1.pC(var5.ld());
                  if (var6 != null) {
                     return var6;
                  }

                  String var7 = Strings.ff("wasm: cannot evaluate instruction as an offset, will assume 0 to proceed with parsing: %s", var2);
                  A.warn(var7);
                  JebCoreService.notifySilentExceptionToClient(new RuntimeException(var7));
                  return 0L;
               }
            case 65:
               return (Long)var2.ys()[0].kS() & 4294967295L;
            case 66:
               return (Long)var2.ys()[0].kS();
            case 67:
            case 68:
               throw new chl("Cannot be evaluated as an address: " + var2);
            default:
               throw new chm("Opcode not supported in initializer_expression: " + var3);
         }
      }
   }

   public int pC() throws chl {
      if (this.pC.size() != 1) {
         throw new chl();
      } else {
         m var1 = (m)this.pC.get(0);
         int var2 = var1.pC();
         switch (var2) {
            case 35:
               return var1.gp();
            case 65:
            case 66:
            case 67:
            case 68:
               throw new chl();
            default:
               throw new chm("Opcode not supported in initializer_expression: " + var2);
         }
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("{{");
      int var2 = 0;

      for (m var4 : this.pC) {
         if (var2 > 0) {
            var1.append(",");
         }

         var1.append(var4);
         var2++;
      }

      var1.append("}}");
      return var1.toString();
   }
}
