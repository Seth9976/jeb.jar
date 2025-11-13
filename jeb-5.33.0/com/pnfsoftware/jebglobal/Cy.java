package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.debug.DebuggerThreadStatus;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueRaw;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import java.nio.ByteOrder;
import java.util.List;

class Cy extends AbstractCommandHandler {
   Cy(ub var1, ICommandManager var2, String var3, CommandParameter[] var4, String var5, String var6) {
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

      String var3 = var2[0].getValue();
      String var4 = var2[1].getValue();
      HX var5 = ((ia)ub.oT(this.pC)).gp();
      if (var5 == null) {
         return this.pC.xC();
      } else if (var5.getStatus() != DebuggerThreadStatus.PAUSED) {
         return ExecutionResult.error("Default thread must be paused");
      } else {
         HF var6 = var5.pC(0);

         for (IDebuggerVariable var9 : var6.getVariables()) {
            if (var9.getName().equals(var3) && var9.getTypedValue().getTypeName().equals("raw")) {
               long var10 = Conversion.stringToLong(var4, -1L);
               ByteOrder var12 = ((ia)ub.fI(this.pC)).A().ys().toByteOrder();
               byte[] var13 = new byte[((byte[])var9.getTypedValue().getValue()).length];
               EndianUtil.numberToBytes(var12, var10, var13);
               var9.setTypedValue(new ValueRaw(var13));
            }
         }

         return ExecutionResult.GENERIC_ERROR;
      }
   }
}
