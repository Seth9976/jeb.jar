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

public class if extends com.pnfsoftware.jeb.corei.debuggers.android.nI {
   private static final ILogger xK = GlobalLog.getLogger(if.class);
   private long Dw;
   private long Uv;
   private byte[] oW;
   private final CommandParameter gO = new CommandParameter("path", "The file path on the debugged target", false);
   private final CommandParameter nf = new CommandParameter("address", "The virtual memory address: prefixed by 0x for hexadecimal value", false);
   private final CommandParameter gP = new CommandParameter("size", "The virtual memory size to read (in bytes)", false);
   private final CommandParameter za = new CommandParameter("hex-bytes", "The bytes to write in hexadecimal form, without space (for example: 11AAFF00)", false);
   private final CommandParameter lm = new CommandParameter("reg", "The register id (architecture dependent, use 'registers' to list all registers)", false);

   public if(um var1) {
      super(var1);
      this.RF();
      this.Hk();
   }

   @Override
   protected ExecutionResult preCheck() {
      ExecutionResult var1 = super.preCheck();
      if (var1 != null && Strings.isBlank(var1.getMessage())) {
         String var2 = "The native debugger is " + (((um)this.q()).isAttached() ? "" : "not ") + "attached.";
         var1 = ExecutionResult.error(var2);
      }

      return var1;
   }

   private ExecutionResult qa() {
      return !((um)this.q()).q()
         ? ExecutionResult.error("Native debugger is not suspended and no Default thread is selected")
         : ExecutionResult.error("No Default thread is selected.");
   }

   private void Hk() {
      this.q(new Gw(this, this, "supported", "Display options supported by the GDB stub (read-only)"));
      this.q(
         new hG(
            this,
            this,
            "readmem",
            new CommandParameter[]{this.nf, this.gP, new CommandParameter("file", "Path to destination file to write raw bytes", true)},
            "Read from virtual memory",
            ""
         )
      );
      this.q(new sq(this, this, "writemem", new CommandParameter[]{this.nf, this.za}, "Write to virtual memory", ""));
      this.q(
         new Jr(
            this,
            this,
            "findmem",
            new CommandParameter[]{
               this.nf,
               new CommandParameter("maxsize", "Maximum size to use for finding in memory", false),
               new CommandParameter("pattern", "The bytes to find in hexadecimal form, without space (for example: 12345678)", false)
            },
            "Search for bytes in virtual memory",
            ""
         )
      );
      this.q(new EZ(this, this, "findnext", "Continue a search initiated by `findmem`"));
      this.q(new gv(this, this, "registers", "View the registers of the default thread"));
      this.q(new Lk(this, this, "get", new CommandParameter[]{this.lm}, "Get a register value (default thread)", ""));
      this.q(
         new VK(
            this,
            this,
            "set",
            new CommandParameter[]{
               this.lm, new CommandParameter("value", "The value to set. Prefix by 0x for hexadecimal, 0 for octal and no prefix for decimal", false)
            },
            "Set a register value (default thread)",
            ""
         )
      );
      this.q(
         new FM(
            this,
            this,
            "readfile",
            new CommandParameter[]{this.gO, new CommandParameter("dstpath", "Path to destination file on the local filesystem", true)},
            "Read a file from the remote filesystem",
            ""
         )
      );
   }

   @Override
   protected long q(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         long var2 = Conversion.stringToLong(var1, -1L);
         if (var2 != -1L) {
            return var2;
         } else {
            var2 = this.RF(var1);
            return var2 != -1L ? var2 : -1L;
         }
      } else {
         return -1L;
      }
   }

   private long RF(String var1) {
      Cg var2 = ((um)this.q()).lm();
      if (var2 == null) {
         return -1L;
      } else {
         Ht var3 = var2.q(true);
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
               ByteBuffer var6 = ByteBuffer.wrap(var5).order(((um)this.q()).RF().gP().toByteOrder());
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
   public AutocompletionResult q(List var1, ICommandNode var2) {
      return null;
   }
}
