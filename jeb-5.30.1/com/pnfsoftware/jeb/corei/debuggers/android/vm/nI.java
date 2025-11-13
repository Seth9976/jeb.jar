package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractValueComposite;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.AutocompletionResult;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class nI extends CU.eo {
   private List RF;

   nI(CU var1) {
      this.q = var1;
      this.RF = Arrays.asList("string", "object", "array", "boolean", "byte", "character", "short", "int", "long", "float", "double");
   }

   @Override
   public AutocompletionResult q(String var1, int var2, AbstractCommandHandler var3, InputToken[] var4) throws JebException {
      Object var5 = null;
      int var6 = var1.lastIndexOf(46);
      int var7 = var3.getParameterIndex("", "t");
      int var8 = var3.getParameterIndex("", "f");
      kY var9 = this.q.q(var7 == -1 ? null : var4[var7]);
      Xa var10 = this.q.q(var9, var8 == -1 ? null : var4[var8]);
      if (CU.RF(var1)) {
         if (var6 == -1) {
            int var11 = var1.indexOf(58);
            if (var11 != -1 && Strings.isNumber(var1.substring(1, var11)) && var1.lastIndexOf(58) == var11) {
               var1 = var1.substring(var11 + 1);
               return new AutocompletionResult(AutocompletionResult.filterStartsWith(var1, this.RF), ':');
            }

            return null;
         }

         var5 = this.q.q(var9, var10, var1.substring(0, var6), true);
      } else {
         if (var1.startsWith("t") && "this".startsWith(var1)) {
            return new AutocompletionResult(Arrays.asList("this"));
         }

         if (var6 != -1) {
            try {
               var5 = this.q.oQ.q(var1.substring(0, var6), var9, var10);
            } catch (IOException var15) {
               CU.xK.catching(var15);
               return null;
            }
         }
      }

      if (var5 != null) {
         var1 = var1.substring(var6 + 1);
         if (((IDebuggerVariable)var5).getTypedValue() instanceof AbstractValueComposite) {
            List var18 = ((AbstractValueComposite)((IDebuggerVariable)var5).getTypedValue()).getValue();
            ArrayList var12 = new ArrayList();

            for (IDebuggerVariable var14 : var18) {
               if (var14.getName().startsWith(var1)) {
                  var12.add(var14.getName());
               }
            }

            return new AutocompletionResult(var12, '.');
         }
      }

      return null;
   }
}
