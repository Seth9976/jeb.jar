package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.SimpleCommandManager;

public class vK extends SimpleCommandManager {
   private aI pC;

   public vK(aI var1) {
      this.pC = var1;
      this.pC();
   }

   private void pC() {
      this.addChild(new uk(this, this, "run", "Run or resume the target"));
      this.addChild(new yn(this, this, "read", "Read from memory"));
      this.addChild(new mc(this, this, "write", "Write to memory"));
      this.addChild(new pO(this, this, "thread", "Set or get information about a thread"));
      this.addChild(new xM(this, this, "threads", "List the process threads"));
      this.addChild(new Zt(this, this, "registers"));
      this.addChild(new Ny(this, this, "libs"));
      this.addChild(new Oh(this, this, "b", "Set breakpoint"));
      this.addChild(new ZK(this, this, "bc"));
      this.addChild(new Yh(this, this, "bl"));
      this.addChild(new SN(this, this, "set", new String[]{"reg", "value"}, "Set a register value (default thread)", ""));
   }

   long pC(String var1) {
      return var1 != null && !var1.isEmpty() ? Conversion.stringToLong(var1, -1L) : -1L;
   }

   private static ExecutionResult pC(String var0, String var1, String var2) {
      return ExecutionResult.error(Strings.ff("%s syntax is invalid. Expected parameters: %s (example: %s)", var0, var1, var2));
   }
}
