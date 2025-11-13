package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import java.io.UnsupportedEncodingException;
import java.util.List;

class dc extends AbstractCommandHandler {
   dc(ub var1, ICommandManager var2, String var3, CommandParameter[] var4, String var5, String var6) {
      super(var2, var3, var4, var5, var6);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      InputToken[] var2;
      try {
         var2 = this.parseInputToken(var1);
      } catch (Exception var14) {
         ub.kS.catching(var14);
         return ExecutionResult.error(var14);
      }

      long var3 = this.pC.pC(var2[0].getValue());
      long var5 = Conversion.stringToLong(var2[1].getValue());
      if (var3 == -1L || var5 <= 0L) {
         return ExecutionResult.GENERIC_ERROR;
      } else if (!((ia)ub.wS(this.pC)).pC()) {
         return ExecutionResult.error("The native process must be suspended first");
      } else {
         this.pC.E = null;
         byte[] var7 = null;
         InputToken var8 = getToken(var1, 2);
         if (var8 != null) {
            if (var8.needsUnespaping()) {
               try {
                  var7 = var8.getBytes();
               } catch (UnsupportedEncodingException var13) {
               }
            } else {
               var7 = Formatter.hexStringToByteArray(var8.getValue());
            }
         }

         if (var7 != null && var7.length != 0) {
            long var9 = ((ia)ub.UT(this.pC)).getMemory().findBytes(var3, var5, var7);
            if (var9 < 0L) {
               return ExecutionResult.error("Not found");
            } else {
               long var11 = var3 + var9;
               this.pC.wS = var11 + 1L;
               this.pC.UT = var3 + var5;
               this.pC.E = var7;
               return ExecutionResult.success(Strings.ff("Found at %Xh", var11));
            }
         } else {
            return ExecutionResult.GENERIC_ERROR;
         }
      }
   }
}
