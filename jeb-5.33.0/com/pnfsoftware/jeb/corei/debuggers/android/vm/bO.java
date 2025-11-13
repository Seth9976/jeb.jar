package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import com.pnfsoftware.jebglobal.rG;
import java.io.IOException;
import java.util.List;

class bO extends AbstractCommandHandler {
   bO(Sv var1, ICommandManager var2, String var3, CommandParameter[] var4, String var5, String var6) {
      super(var2, var3, var4, var5, var6);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      try {
         InputToken[] var2 = this.parseInputToken(var1);
         Mh var3 = this.pC.pC(var2[0]);
         uX var4 = this.pC.pC(var3, var2[1]);
         String var5 = var2[2].getValue();
         byte var6 = 3;
         if (var2[3].getValue().equals("=")) {
            var6 = 4;
         }

         if (Sv.A(var5)) {
            if (!var3.A()) {
               return ExecutionResult.error(S.L("Thread is not paused"));
            } else if (var4 == null) {
               return ExecutionResult.error(S.L("Frame is not accessible. Is your thread paused?"));
            } else {
               String[] var14 = var5.split(":");
               String var15 = var14[0];
               String var16 = var14.length >= 2 ? var14[1] : null;
               List var17 = this.pC.pC(var2, var6, var3, var4, new String[]{var16});
               if (!var17.isEmpty() && var17.size() <= 1) {
                  ITypedValue var11 = this.pC((ITypedValue)var17.get(0));
                  boolean var12 = var4.pC(var15, var11);
                  return ExecutionResult.fromBoolean(var12);
               } else {
                  return ExecutionResult.error(S.L("Malformed value to set"));
               }
            }
         } else {
            IDebuggerVariable var7 = this.pC.UO.pC(var5, var3, var4);
            if (var7 != null) {
               List var8 = this.pC.pC(var2, var6, var3, var4, new String[]{null});
               if (!var8.isEmpty() && var8.size() <= 1) {
                  ITypedValue var9 = this.pC((ITypedValue)var8.get(0));
                  boolean var10 = var7.setTypedValue(var9);
                  if (var10) {
                     ((Tb)Sv.WR(this.pC)).notifyListeners(new JebEvent(J.UnitChange, null));
                  }

                  return ExecutionResult.fromBoolean(var10);
               } else {
                  return ExecutionResult.error(S.L("Malformed value to set"));
               }
            } else {
               return ExecutionResult.error(Strings.ff(S.L("Can not retrieve %s"), var5));
            }
         }
      } catch (Exception var13) {
         Sv.kS.catching(var13);
         return ExecutionResult.error(var13);
      }
   }

   private ITypedValue pC(ITypedValue var1) throws IOException, JebException {
      rG var2 = ((Tb)Sv.NS(this.pC)).pC(var1);
      return ((Tb)Sv.vP(this.pC)).pC(var2);
   }
}
