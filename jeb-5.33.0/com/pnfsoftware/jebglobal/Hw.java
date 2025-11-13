package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import java.nio.ByteBuffer;
import java.util.List;

class Hw extends AbstractCommandHandler {
   Hw(ub var1, ICommandManager var2, String var3, CommandParameter[] var4, String var5, String var6) {
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

      String var3 = var2[0].getValue();
      HX var4 = ((ia)ub.ld(this.pC)).gp();
      if (var4 == null) {
         return this.pC.xC();
      } else {
         LD var5 = var4.pC(true);
         if (var5 == null) {
            return ExecutionResult.GENERIC_ERROR;
         } else {
            int var6 = 0;

            while (var6 < var5.size() && !Strings.equalsIgnoreCase(var5.getName(var6), var3)) {
               var6++;
            }

            if (var6 >= var5.size()) {
               return ExecutionResult.error("Invalid register name");
            } else {
               byte[] var7 = var5.getValue(var6);
               ByteBuffer var8 = ByteBuffer.wrap(var7).order(((ia)ub.gp(this.pC)).A().ys().toByteOrder());
               String var9 = var5.getName(var6) + "= ";

               return ExecutionResult.success(switch (var5.getBitsize(var6)) {
                  case 8 -> var9 + Strings.ff("%02Xh", var8.get() & 255);
                  case 16 -> var9 + Strings.ff("%04Xh", var8.getShort() & '\uffff');
                  case 32 -> var9 + Strings.ff("%08Xh", var8.getInt());
                  case 64 -> var9 + Strings.ff("%016Xh", var8.getLong());
                  default -> "(bytes) " + Formatter.formatBinaryLine(var7);
               });
            }
         }
      }
   }
}
