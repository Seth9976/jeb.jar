package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.PathProcessor;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.util.List;

class pN extends AbstractCommandHandler {
   pN(ub var1, ICommandManager var2, String var3, CommandParameter[] var4, String var5, String var6) {
      super(var2, var3, var4, var5, var6);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      InputToken[] var2;
      try {
         var2 = this.parseInputToken(var1);
      } catch (Exception var10) {
         ub.kS.catching(var10);
         return ExecutionResult.error(var10);
      }

      long var3 = this.pC.pC(var2[0].getValue());
      int var5 = Conversion.stringToInt(var2[1].getValue(), -1);
      if (var3 >= 0L && var5 >= 0) {
         byte[] var6 = new byte[var5];
         var5 = ((ia)ub.A(this.pC)).readMemory(var3, var5, var6, 0);
         if (var5 < 0) {
            return ExecutionResult.GENERIC_ERROR;
         } else {
            String var7 = Strings.ff("Read %Xh bytes:\n%s", var5, Formatter.formatBinaryBlock(var6, 0, var5));
            String var8 = getParameter(var1, 2);
            if (var8 != null) {
               var8 = PathProcessor.ENV.decodeSinglePath(var8);
               if (var8 == null) {
                  return ExecutionResult.error("Invalid path");
               }

               File var9 = new File(var8);
               if (IO.writeFileSafe(var9, var6, 0, var5, true)) {
                  var7 = var7 + Strings.ff("\n%d(%Xh) bytes saved to file: %s", var5, var5, var9.getAbsolutePath());
               } else {
                  var7 = var7 + Strings.ff("\nContents could not be saved to file: %s", var8);
               }
            }

            return ExecutionResult.success(var7);
         }
      } else {
         return ExecutionResult.GENERIC_ERROR;
      }
   }
}
