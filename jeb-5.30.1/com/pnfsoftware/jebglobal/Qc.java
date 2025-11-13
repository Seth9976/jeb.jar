package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.adb.AdbResult;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbWrapper;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Arrays;

public class Qc implements Xh {
   AdbWrapper q;
   String RF;
   int xK;

   public Qc(AdbWrapper var1, String var2, int var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   @Override
   public com.pnfsoftware.jeb.corei.debuggers.linux.nI q() {
      AdbResult var1 = this.q.shell(2000L, null, this.RF, Arrays.asList("cat", "/proc/" + this.xK + "/maps"));
      if (!var1.isSuccess(Boolean.TRUE, false)) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();

         for (String var6 : Strings.splitLines(Strings.decodeASCII(var1.getOutput()))) {
            com.pnfsoftware.jeb.corei.debuggers.linux.ej var7 = com.pnfsoftware.jeb.corei.debuggers.linux.ej.q(var6);
            if (var7 != null) {
               var2.add(var7);
            }
         }

         return new com.pnfsoftware.jeb.corei.debuggers.linux.nI(var2);
      }
   }

   @Override
   public boolean RF() {
      return false;
   }
}
