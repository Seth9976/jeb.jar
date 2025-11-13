package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AutocompletionResult;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandNode;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.nio.ByteBuffer;
import java.util.List;

public class ub extends com.pnfsoftware.jeb.corei.debuggers.android.K {
   private static final ILogger kS = GlobalLog.getLogger(ub.class);
   private long wS;
   private long UT;
   private byte[] E;
   private final CommandParameter sY = new CommandParameter("path", "The file path on the debugged target", false);
   private final CommandParameter ys = new CommandParameter("address", "The virtual memory address: prefixed by 0x for hexadecimal value", false);
   private final CommandParameter ld = new CommandParameter("size", "The virtual memory size to read (in bytes)", false);
   private final CommandParameter gp = new CommandParameter("hex-bytes", "The bytes to write in hexadecimal form, without space (for example: 11AAFF00)", false);
   private final CommandParameter oT = new CommandParameter("reg", "The register id (architecture dependent, use 'registers' to list all registers)", false);

   public ub(ia var1) {
      super(var1);
      this.A();
      this.ED();
   }

   @Override
   protected ExecutionResult preCheck() {
      ExecutionResult var1 = super.preCheck();
      if (var1 != null && Strings.isBlank(var1.getMessage())) {
         String var2 = "The native debugger is " + (((ia)this.pC()).isAttached() ? "" : "not ") + "attached.";
         var1 = ExecutionResult.error(var2);
      }

      return var1;
   }

   private ExecutionResult xC() {
      return !((ia)this.pC()).pC()
         ? ExecutionResult.error("Native debugger is not suspended and no Default thread is selected")
         : ExecutionResult.error("No Default thread is selected.");
   }

   private void ED() {
      this.pC(new md(this, this, "supported", "Display options supported by the GDB stub (read-only)"));
      this.pC(
         new pN(
            this,
            this,
            "readmem",
            new CommandParameter[]{this.ys, this.ld, new CommandParameter("file", "Path to destination file to write raw bytes", true)},
            "Read from virtual memory",
            ""
         )
      );
      this.pC(new ja(this, this, "writemem", new CommandParameter[]{this.ys, this.gp}, "Write to virtual memory", ""));
      this.pC(
         new dc(
            this,
            this,
            "findmem",
            new CommandParameter[]{
               this.ys,
               new CommandParameter("maxsize", "Maximum size to use for finding in memory", false),
               new CommandParameter("pattern", "The bytes to find in hexadecimal form, without space (for example: 12345678)", false)
            },
            "Search for bytes in virtual memory",
            ""
         )
      );
      this.pC(new PL(this, this, "findnext", "Continue a search initiated by `findmem`"));
      this.pC(new xZ(this, this, "registers", "View the registers of the default thread"));
      this.pC(new Hw(this, this, "get", new CommandParameter[]{this.oT}, "Get a register value (default thread)", ""));
      this.pC(
         new Cy(
            this,
            this,
            "set",
            new CommandParameter[]{
               this.oT, new CommandParameter("value", "The value to set. Prefix by 0x for hexadecimal, 0 for octal and no prefix for decimal", false)
            },
            "Set a register value (default thread)",
            ""
         )
      );
      this.pC(
         new kC(
            this,
            this,
            "readfile",
            new CommandParameter[]{this.sY, new CommandParameter("dstpath", "Path to destination file on the local filesystem", true)},
            "Read a file from the remote filesystem",
            ""
         )
      );
   }

   @Override
   protected long pC(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         long var2 = Conversion.stringToLong(var1, -1L);
         if (var2 != -1L) {
            return var2;
         } else {
            var2 = this.A(var1);
            return var2 != -1L ? var2 : -1L;
         }
      } else {
         return -1L;
      }
   }

   private long A(String var1) {
      HX var2 = ((ia)this.pC()).gp();
      if (var2 == null) {
         return -1L;
      } else {
         LD var3 = var2.pC(true);
         if (var3 == null) {
            return -1L;
         } else {
            int var4 = 0;

            while (var4 < var3.size() && !Strings.equalsIgnoreCase(var3.getName(var4), var1)) {
               var4++;
            }

            if (var4 >= var3.size()) {
               return -1L;
            } else {
               byte[] var5 = var3.getValue(var4);
               ByteBuffer var6 = ByteBuffer.wrap(var5).order(((ia)this.pC()).A().ys().toByteOrder());
               switch (var3.getBitsize(var4)) {
                  case 8:
                     return var6.get() & 255L;
                  case 16:
                     return var6.getShort() & 65535L;
                  case 32:
                     return var6.getInt() & 4294967295L;
                  case 64:
                     return var6.getLong();
                  default:
                     return -1L;
               }
            }
         }
      }
   }

   @Override
   public AutocompletionResult pC(List var1, ICommandNode var2) {
      return null;
   }
}
