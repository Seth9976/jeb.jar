package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbWrapper;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidDeviceUtil;
import com.pnfsoftware.jeb.util.base.PathProcessor;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import java.io.File;
import java.util.List;

class Nt extends AbstractCommandHandler {
   Nt(CU var1, ICommandManager var2, String var3, CommandParameter[] var4, String var5, String var6) {
      super(var2, var3, var4, var5, var6);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      try {
         InputToken[] var2 = this.parseInputToken(var1);
         String var3 = var2[0].getValue();
         String var4 = var2[1].getValue();
         var4 = PathProcessor.ENV.decodeSinglePath(var4);
         if (var4 == null) {
            return ExecutionResult.error(S.L("Invalid path"));
         } else {
            AdbWrapper var5 = ((CI)CU.io(this.q)).Uv();
            if (var5 == null) {
               return ExecutionResult.error(S.L("Cannot use adb"));
            } else {
               String var6 = new File(var4).getAbsolutePath();
               boolean var7 = AndroidDeviceUtil.pullFile(var5, var3, var4);
               return !var7
                  ? ExecutionResult.error(Strings.ff(S.L("Cannot download file %s to %s"), var3, var6))
                  : ExecutionResult.success(Strings.ff(S.L("File downloaded to %s"), var6));
            }
         }
      } catch (Exception var8) {
         return ExecutionResult.error(var8);
      }
   }
}
