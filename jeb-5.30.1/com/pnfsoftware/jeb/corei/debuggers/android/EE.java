package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerModule;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class EE extends AbstractCommandHandler {
   EE(nI var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      String var2 = getParameter(var1, 0);
      List var3 = this.q.Uv.getModules();
      if (var3 == null) {
         return ExecutionResult.GENERIC_ERROR;
      } else {
         Pattern var4 = null;
         if (var2 != null) {
            try {
               var4 = Pattern.compile(var2);
            } catch (PatternSyntaxException var10) {
            }
         }

         StringBuilder var5 = new StringBuilder();

         for (IDebuggerModule var7 : var3) {
            String var9 = Strings.safe(var7.getName());
            boolean var8;
            if (var4 != null) {
               var8 = var4.matcher(var9).find();
            } else if (var2 != null) {
               var8 = var9.contains(var2);
            } else {
               var8 = true;
            }

            if (var8) {
               Strings.ff(var5, "- %s\n", var7);
            }
         }

         return ExecutionResult.success(var5.toString());
      }
   }
}
