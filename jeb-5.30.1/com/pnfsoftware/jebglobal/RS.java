package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.SimpleCommandManager;

public class RS extends SimpleCommandManager {
   private oH q;

   public RS(oH var1) {
      this.q = var1;
      this.q();
   }

   private void q() {
      this.addChild(new Cj(this, this, "run", "Run or resume the target"));
      this.addChild(new kh(this, this, "read", "Read from memory"));
      this.addChild(new ji(this, this, "write", "Write to memory"));
      this.addChild(new qo(this, this, "thread", "Set or get information about a thread"));
      this.addChild(new Zm(this, this, "threads", "List the process threads"));
      this.addChild(new Gf(this, this, "registers"));
      this.addChild(new Fr(this, this, "libs"));
      this.addChild(new fd(this, this, "b", "Set breakpoint"));
      this.addChild(new EG(this, this, "bc"));
      this.addChild(new mk(this, this, "bl"));
      this.addChild(new bV(this, this, "set", new String[]{"reg", "value"}, "Set a register value (default thread)", ""));
   }

   long q(String var1) {
      return var1 != null && !var1.isEmpty() ? Conversion.stringToLong(var1, -1L) : -1L;
   }

   private static ExecutionResult q(String var0, String var1, String var2) {
      return ExecutionResult.error(Strings.ff("%s syntax is invalid. Expected parameters: %s (example: %s)", var0, var1, var2));
   }
}
