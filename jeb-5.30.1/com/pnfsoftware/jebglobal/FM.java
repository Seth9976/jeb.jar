package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.PathProcessor;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import java.io.File;
import java.util.List;

class FM extends AbstractCommandHandler {
   FM(if var1, ICommandManager var2, String var3, CommandParameter[] var4, String var5, String var6) {
      super(var2, var3, var4, var5, var6);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      InputToken[] var2;
      try {
         var2 = this.parseInputToken(var1);
      } catch (Exception var9) {
         if.xK.catching(var9);
         return ExecutionResult.error(var9);
      }

      String var3 = var2[0].getValue();
      byte[] var4 = ((um)if.JY(this.q)).RF().Uv(var3);
      if (var4 == null) {
         return ExecutionResult.GENERIC_ERROR;
      } else {
         int var5 = var4.length;
         String var6 = Strings.ff("Read %Xh bytes:\n%s", var5, Formatter.formatBinaryBlock(var4, 0, var5));
         String var7 = getParameter(var1, 1);
         if (var7 != null) {
            var7 = PathProcessor.ENV.decodeSinglePath(var7);
            if (var7 == null) {
               return ExecutionResult.error("Invalid path");
            }

            File var8 = new File(var7);
            if (com.pnfsoftware.jeb.util.io.IO.writeFileSafe(var8, var4, 0, var5, true)) {
               var6 = var6 + Strings.ff("\n%d(%Xh) bytes saved to file: %s", var5, var5, var8.getAbsolutePath());
            } else {
               var6 = var6 + Strings.ff("\nContents could not be saved to file: %s", var7);
            }
         }

         return ExecutionResult.success(var6);
      }
   }
}
