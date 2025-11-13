package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueObject;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueString;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.ch;
import java.util.List;

class oL extends AbstractCommandHandler {
   oL(CU var1, ICommandManager var2, String var3, CommandParameter[] var4, String var5, String var6) {
      super(var2, var3, var4, var5, var6);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      try {
         InputToken[] var2 = this.parseInputToken(var1);
         kY var3 = this.q.q(var2[0]);
         Xa var4 = this.q.q(var3, var2[1]);
         boolean var5 = var2[2] != null;
         String var6 = var2[3].getValue();
         String var7 = null;
         List var8;
         if (var6.equals("new")) {
            var8 = this.q.q(var2, 3, var3, var4, null);
         } else {
            var7 = var2[4].getValue();
            if (var7.contains("(")) {
               var2[4] = new InputToken(var7.substring(var7.indexOf(40)));
               var7 = var7.substring(0, var7.indexOf(40));
               var8 = this.q.q(var2, 4, var3, var4, null);
            } else {
               var8 = this.q.q(var2, 5, var3, var4, null);
            }
         }

         ITypedValue var9 = null;
         String var12 = var6.toLowerCase();
         if (Character.isDigit(var12.charAt(0))) {
            throw new JebException(this.q.xK(var6));
         } else {
            if (var12.startsWith("c") || var12.startsWith("l")) {
               long var10 = this.q.q(var6, var3, var4);
               Ux var18 = (Ux)((CI)CU.gO(this.q)).oW();
               List var14 = ((CI)CU.nf(this.q)).RF(var8);
               ch var15 = var18.JY().q(var3.getId(), -1L, var10, var7, var14);
               var9 = ((CI)CU.gP(this.q)).q(var15);
            } else if (var6.equals("new")) {
               if (var8.size() == 1) {
                  ITypedValue var13 = (ITypedValue)var8.get(0);
                  var9 = ((CI)CU.lm(this.q)).q(((CI)CU.za(this.q)).q(var13));
               }
            } else {
               Object var17;
               if (CU.RF(var6)) {
                  var17 = this.q.q(var3, var4, var6, true).getTypedValue();
               } else {
                  var17 = this.q.oQ.RF(var6, var3, var4);
               }

               if (var17 instanceof ValueString) {
                  var17 = new HA((CI)CU.zz(this.q), ((ValueString)var17).getObjectId(), 0L, "Ljava/lang/String;");
               } else if (!(var17 instanceof ValueObject)) {
                  return ExecutionResult.error("Invalid object Id");
               }

               var9 = ((ValueObject)var17).invoke(var7, var3.getId(), var8);
            }

            if (var9 == null) {
               return ExecutionResult.GENERIC_ERROR;
            } else {
               String var19 = var9.format();
               if (var5) {
                  var19 = this.q.q(var19, 1);
               }

               return ExecutionResult.success(var19);
            }
         }
      } catch (Exception var16) {
         CU.xK.catching(var16);
         return ExecutionResult.error(var16);
      }
   }
}
