package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class kJ {
   public boolean pC;
   public boolean A;
   public boolean kS;
   public boolean wS;
   public boolean UT;
   public boolean E;
   public boolean sY;
   public boolean ys;
   public boolean ld;
   public boolean gp;
   public boolean oT;
   public boolean fI;
   public boolean WR;
   public boolean NS;
   public boolean vP;
   public boolean xC;
   public boolean ED;
   public boolean Sc;
   public boolean ah;
   public boolean eP;
   public boolean UO;

   public static kJ pC(AN var0) throws IOException {
      kJ var1 = new kJ();
      var1.pC = var0.kS();
      var1.A = var0.kS();
      var1.kS = var0.kS();
      var1.wS = var0.kS();
      var1.UT = var0.kS();
      var1.E = var0.kS();
      var1.sY = var0.kS();
      var1.ys = var0.kS();
      var1.ld = var0.kS();
      var1.gp = var0.kS();
      var1.oT = var0.kS();
      var1.fI = var0.kS();
      var1.WR = var0.kS();
      var1.NS = var0.kS();
      var1.vP = var0.kS();
      var1.xC = var0.kS();
      var1.ED = var0.kS();
      var1.Sc = var0.kS();
      var1.ah = var0.kS();
      var1.eP = var0.kS();
      var1.UO = var0.kS();
      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "- canWatchFieldModification: %b\n", this.pC);
      Strings.ff(var1, "- canWatchFieldAccess: %b\n", this.A);
      Strings.ff(var1, "- canGetBytecodes: %b\n", this.kS);
      Strings.ff(var1, "- canGetSyntheticAttribute: %b\n", this.wS);
      Strings.ff(var1, "- canGetOwnedMonitorInfo: %b\n", this.UT);
      Strings.ff(var1, "- canGetCurrentContendedMonitor: %b\n", this.E);
      Strings.ff(var1, "- canGetMonitorInfo: %b\n", this.sY);
      Strings.ff(var1, "- canRedefineClasses: %b\n", this.ys);
      Strings.ff(var1, "- canAddMethod: %b\n", this.ld);
      Strings.ff(var1, "- canUnrestrictedlyRedefineClasses: %b\n", this.gp);
      Strings.ff(var1, "- canPopFrames: %b\n", this.oT);
      Strings.ff(var1, "- canUseInstanceFilters: %b\n", this.fI);
      Strings.ff(var1, "- canGetSourceDebugExtension: %b\n", this.WR);
      Strings.ff(var1, "- canRequestVMDeathEvent: %b\n", this.NS);
      Strings.ff(var1, "- canSetDefaultStratum: %b\n", this.vP);
      Strings.ff(var1, "- canGetInstanceInfo: %b\n", this.xC);
      Strings.ff(var1, "- canRequestMonitorEvents: %b\n", this.ED);
      Strings.ff(var1, "- canGetMonitorFrameInfo: %b\n", this.Sc);
      Strings.ff(var1, "- canUseSourceNameFilters: %b\n", this.ah);
      Strings.ff(var1, "- canGetConstantPool: %b\n", this.eP);
      Strings.ff(var1, "- canForceEarlyReturn: %b", this.UO);
      return var1.toString();
   }
}
