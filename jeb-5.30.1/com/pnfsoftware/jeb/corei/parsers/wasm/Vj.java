package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.cop;
import com.pnfsoftware.jebglobal.coq;
import java.util.List;

@Ser
public class Vj {
   private static final ILogger RF = GlobalLog.getLogger(Vj.class);
   @SerId(1)
   List q;

   Vj(List var1) {
      if (var1 != null && !var1.isEmpty()) {
         this.q = var1;
      } else {
         throw new NullPointerException();
      }
   }

   public long q(qx var1) throws cop {
      if (this.q.size() != 1) {
         throw new cop();
      } else {
         SG var2 = (SG)this.q.get(0);
         int var3 = var2.q();
         switch (var3) {
            case 35:
               int var4 = var2.za();
               tw var5 = var1.xK(var4);
               if (var5.gP()) {
                  return var5.RF.q(var1);
               } else {
                  Long var6 = var1.q(var5.JY());
                  if (var6 != null) {
                     return var6;
                  }

                  String var7 = Strings.ff("wasm: cannot evaluate instruction as an offset, will assume 0 to proceed with parsing: %s", var2);
                  RF.warn(var7);
                  JebCoreService.notifySilentExceptionToClient(new RuntimeException(var7));
                  return 0L;
               }
            case 65:
               return (Long)var2.nf()[0].Dw() & 4294967295L;
            case 66:
               return (Long)var2.nf()[0].Dw();
            case 67:
            case 68:
               throw new cop("Cannot be evaluated as an address: " + var2);
            default:
               throw new coq("Opcode not supported in initializer_expression: " + var3);
         }
      }
   }

   public int q() throws cop {
      if (this.q.size() != 1) {
         throw new cop();
      } else {
         SG var1 = (SG)this.q.get(0);
         int var2 = var1.q();
         switch (var2) {
            case 35:
               return var1.za();
            case 65:
            case 66:
            case 67:
            case 68:
               throw new cop();
            default:
               throw new coq("Opcode not supported in initializer_expression: " + var2);
         }
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("{{");
      int var2 = 0;

      for (SG var4 : this.q) {
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
