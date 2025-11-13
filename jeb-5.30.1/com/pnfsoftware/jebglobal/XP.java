package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class XP {
   public boolean q;
   public boolean RF;
   public boolean xK;
   public boolean Dw;
   public boolean Uv;
   public boolean oW;
   public boolean gO;

   public static XP q(Hv var0) throws IOException {
      XP var1 = new XP();
      var1.q = var0.Dw();
      var1.RF = var0.Dw();
      var1.xK = var0.Dw();
      var1.Dw = var0.Dw();
      var1.Uv = var0.Dw();
      var1.oW = var0.Dw();
      var1.gO = var0.Dw();
      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "- canWatchFieldModification: %b\n", this.q);
      Strings.ff(var1, "- canWatchFieldAccess: %b\n", this.RF);
      Strings.ff(var1, "- canGetBytecodes: %b\n", this.xK);
      Strings.ff(var1, "- canGetSyntheticAttribute: %b\n", this.Dw);
      Strings.ff(var1, "- canGetOwnedMonitorInfo: %b\n", this.Uv);
      Strings.ff(var1, "- canGetCurrentContendedMonitor: %b\n", this.oW);
      Strings.ff(var1, "- canGetMonitorInfo: %b", this.gO);
      return var1.toString();
   }
}
