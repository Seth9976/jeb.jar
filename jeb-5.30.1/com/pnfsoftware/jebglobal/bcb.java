package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.CodeConstantManager;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class bcb {
   CodeConstantManager q = new CodeConstantManager();

   public bcb(File var1) throws IOException {
      this(com.pnfsoftware.jeb.util.io.IO.readLines(var1));
   }

   public bcb(Collection var1) {
      int var2 = 0;

      for (String var4 : var1) {
         var2++;
         var4 = var4.trim();
         if (var4.length() != 0 && !var4.startsWith("#")) {
            int var5 = var4.indexOf(58);
            if (var5 < 0) {
               throw new RuntimeException(var2 + ": Bad line entry: " + var4);
            }

            try {
               String var6 = var4.substring(0, var5);
               String var8 = var4.substring(var5 + 1);
               Object var7;
               if (var8.startsWith("\"")) {
                  var7 = var8.substring(1, var8.length() - 1);
               } else if (var8.endsWith("L")) {
                  if (var8.startsWith("-")) {
                     var7 = Long.parseLong(var8.substring(0, var8.length() - 1));
                  } else {
                     var7 = Long.parseUnsignedLong(var8.substring(0, var8.length() - 1));
                  }
               } else if (var8.startsWith("-")) {
                  var7 = Integer.parseInt(var8);
               } else {
                  var7 = Integer.parseUnsignedInt(var8);
               }

               this.q.addConstant(var6, var7);
            } catch (Exception var9) {
               throw new RuntimeException(var2 + ": Illegal constant: " + var4, var9);
            }
         }
      }
   }

   public void q(bck var1) {
      var1.Dw = this.q;
   }
}
