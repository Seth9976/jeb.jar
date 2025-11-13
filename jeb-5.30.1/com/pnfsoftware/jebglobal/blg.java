package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.PrettyPrinter;
import java.io.File;

class blg extends PrettyPrinter {
   blg(blf.eo var1, boolean var2, int var3, blf var4, File var5) {
      super(var2, var3);
      this.xK = var1;
      this.q = var4;
      this.RF = var5;
   }

   @Override
   protected String formatObject(Object var1) {
      if (var1 instanceof File) {
         String var2 = com.pnfsoftware.jeb.util.io.IO.getRelativePath((File)var1, this.RF);
         if (var2 != null) {
            return "/" + var2.replace('\\', '/');
         }
      }

      return null;
   }
}
