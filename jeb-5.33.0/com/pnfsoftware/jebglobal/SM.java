package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.adb.AdbResult;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbWrapper;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Arrays;

public class SM implements Gg {
   AdbWrapper pC;
   String A;
   int kS;

   public SM(AdbWrapper var1, String var2, int var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public com.pnfsoftware.jeb.corei.debuggers.linux.K pC() {
      AdbResult var1 = this.pC.shell(2000L, null, this.A, Arrays.asList("cat", "/proc/" + this.kS + "/maps"));
      if (!var1.isSuccess(Boolean.TRUE, false)) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();

         for (String var6 : Strings.splitLines(Strings.decodeASCII(var1.getOutput()))) {
            com.pnfsoftware.jeb.corei.debuggers.linux.Ws var7 = com.pnfsoftware.jeb.corei.debuggers.linux.Ws.pC(var6);
            if (var7 != null) {
               var2.add(var7);
            }
         }

         return new com.pnfsoftware.jeb.corei.debuggers.linux.K(var2);
      }
   }

   @Override
   public boolean A() {
      return false;
   }
}
