package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class Oo {
   public boolean q;
   public boolean RF;
   public boolean xK;
   public boolean Dw;
   public boolean Uv;
   public boolean oW;
   public boolean gO;
   public boolean nf;
   public boolean gP;
   public boolean za;
   public boolean lm;
   public boolean zz;
   public boolean JY;
   public boolean HF;
   public boolean LK;
   public boolean io;
   public boolean qa;
   public boolean Hk;
   public boolean Me;
   public boolean PV;
   public boolean oQ;

   public static Oo q(Hv var0) throws IOException {
      Oo var1 = new Oo();
      var1.q = var0.Dw();
      var1.RF = var0.Dw();
      var1.xK = var0.Dw();
      var1.Dw = var0.Dw();
      var1.Uv = var0.Dw();
      var1.oW = var0.Dw();
      var1.gO = var0.Dw();
      var1.nf = var0.Dw();
      var1.gP = var0.Dw();
      var1.za = var0.Dw();
      var1.lm = var0.Dw();
      var1.zz = var0.Dw();
      var1.JY = var0.Dw();
      var1.HF = var0.Dw();
      var1.LK = var0.Dw();
      var1.io = var0.Dw();
      var1.qa = var0.Dw();
      var1.Hk = var0.Dw();
      var1.Me = var0.Dw();
      var1.PV = var0.Dw();
      var1.oQ = var0.Dw();
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
      Strings.ff(var1, "- canGetMonitorInfo: %b\n", this.gO);
      Strings.ff(var1, "- canRedefineClasses: %b\n", this.nf);
      Strings.ff(var1, "- canAddMethod: %b\n", this.gP);
      Strings.ff(var1, "- canUnrestrictedlyRedefineClasses: %b\n", this.za);
      Strings.ff(var1, "- canPopFrames: %b\n", this.lm);
      Strings.ff(var1, "- canUseInstanceFilters: %b\n", this.zz);
      Strings.ff(var1, "- canGetSourceDebugExtension: %b\n", this.JY);
      Strings.ff(var1, "- canRequestVMDeathEvent: %b\n", this.HF);
      Strings.ff(var1, "- canSetDefaultStratum: %b\n", this.LK);
      Strings.ff(var1, "- canGetInstanceInfo: %b\n", this.io);
      Strings.ff(var1, "- canRequestMonitorEvents: %b\n", this.qa);
      Strings.ff(var1, "- canGetMonitorFrameInfo: %b\n", this.Hk);
      Strings.ff(var1, "- canUseSourceNameFilters: %b\n", this.Me);
      Strings.ff(var1, "- canGetConstantPool: %b\n", this.PV);
      Strings.ff(var1, "- canForceEarlyReturn: %b", this.oQ);
      return var1.toString();
   }
}
