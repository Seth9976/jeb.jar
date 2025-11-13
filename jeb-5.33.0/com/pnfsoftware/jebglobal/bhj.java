package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.PrettyPrinter;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;

class bhj extends PrettyPrinter {
   bhj(bhi.Av var1, boolean var2, int var3, bhi var4, File var5) {
      super(var2, var3);
      this.kS = var1;
      this.pC = var4;
      this.A = var5;
   }

   @Override
   protected String formatObject(Object var1) {
      if (var1 instanceof File var2) {
         String var3 = IO.getRelativePath(var2, this.A);
         if (var3 != null) {
            return "/" + var3.replace('\\', '/');
         }
      }

      return null;
   }
}
