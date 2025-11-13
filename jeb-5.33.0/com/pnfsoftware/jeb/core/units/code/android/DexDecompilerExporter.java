package com.pnfsoftware.jeb.core.units.code.android;

import com.google.common.base.Throwables;
import com.pnfsoftware.jeb.core.units.code.DecompilationOptions;
import com.pnfsoftware.jeb.core.units.code.DecompilerExporter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.regex.Pattern;

public class DexDecompilerExporter extends DecompilerExporter {
   private static final ILogger logger = GlobalLog.getLogger(DexDecompilerExporter.class);
   private static HashMap letterToPrim = new HashMap();

   public DexDecompilerExporter(IDexDecompilerUnit var1) {
      super(var1);
   }

   @Override
   public boolean process() {
      return this.processTopLevelClasses();
   }

   @Override
   protected void customizeOptions(boolean var1, DecompilationOptions.Builder var2) {
      if (var1 && this.outputFolder != null) {
         this.usesCustomDecompWriter = true;
         var2.postDecompilationCallback(var1x -> {
            try {
               this.writeClassDecompilation(var1x);
            } catch (Exception var3) {
               System.err.println(Throwables.getStackTraceAsString(var3));
            }
         });
      }
   }

   @Override
   public void setSignaturePattern(Pattern var1) {
      super.setSignaturePattern(var1);
   }

   @Override
   protected String generateMethodFileName(String var1) {
      JvmMethodSig var2 = JvmMethodSig.parse(var1);
      StringBuilder var3 = new StringBuilder();
      var3.append(this.convertTypeName(var2.csig));
      var3.append(".");
      String var5 = var2.mname;

      var3.append(switch (var5) {
         case "<clinit>" -> "{}";
         case "<init>" -> {
            String[] var7 = var2.csig.substring(1, var2.csig.length() - 1).split("/");
            String var8 = var7[var7.length - 1];
            yield var8;
         }
         default -> var2.mname;
      });
      var3.append("(");
      int var9 = 0;

      for (String var11 : var2.partypes) {
         if (var9 > 0) {
            var3.append(",");
         }

         var3.append(this.convertTypeName(var11));
         var9++;
      }

      var3.append(")");
      var3.append(this.convertTypeName(var2.rettype));
      var3.append(".java");
      return var3.toString();
   }

   private String convertTypeName(String var1) {
      int var2 = 0;
      char var3 = var1.charAt(var2);

      while (var3 == '[') {
         var3 = var1.charAt(++var2);
      }

      var3 = var1.charAt(var2);
      if (var3 == 'L') {
         var1 = var1.substring(var2 + 1, var1.length() - 1).replace('/', '.');
      } else {
         var1 = (String)letterToPrim.get(var3);
      }

      return var1 + Strings.generate("[]", var2);
   }

   @Override
   protected String generateClassFileName(String var1) {
      return var1.substring(1, var1.length() - 1) + ".java";
   }

   static {
      letterToPrim.put('Z', "boolean");
      letterToPrim.put('B', "byte");
      letterToPrim.put('C', "char");
      letterToPrim.put('S', "short");
      letterToPrim.put('I', "int");
      letterToPrim.put('J', "long");
      letterToPrim.put('F', "float");
      letterToPrim.put('D', "double");
      letterToPrim.put('V', "");
   }
}
