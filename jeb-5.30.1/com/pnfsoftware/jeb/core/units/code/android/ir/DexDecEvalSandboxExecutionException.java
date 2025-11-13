package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.util.format.Formatter;

public class DexDecEvalSandboxExecutionException extends DexDecEvaluationException {
   private static final long serialVersionUID = 1L;

   public DexDecEvalSandboxExecutionException(Throwable var1) {
      super("An exception was raised during sandboxing: " + prepareMsg(var1), var1);
   }

   private static String prepareMsg(Throwable var0) {
      if (var0 == null) {
         throw new IllegalArgumentException();
      } else {
         StringBuilder var1 = new StringBuilder();

         for (int var2 = 0; var0 != null; var2++) {
            if (var2 >= 1) {
               var1.append(" / ");
            }

            var1.append(var0.getClass().getName());
            if (var0.getMessage() != null) {
               var1.append(" ('").append(Formatter.escapeString(var0.getMessage())).append("')");
            }

            var0 = var0.getCause();
         }

         return var1.toString();
      }
   }
}
