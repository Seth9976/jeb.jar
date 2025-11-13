package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jebglobal.ckz;

class Av extends ckz {
   int pC;

   Av(ApkIdentifier var1, IInput var2) {
      super(var2);
      this.A = var1;
   }

   @Override
   public void pC() {
      this.pC = 0;
   }

   @Override
   public Boolean pC(String var1) {
      if (var1.equals("classes.dex")) {
         return true;
      } else {
         if (var1.equals("AndroidManifest.xml") || var1.equals("resources.arsc")) {
            this.pC |= 1;
         } else if (var1.equals("classes.jar")) {
            this.pC |= 2;
         }

         return null;
      }
   }

   @Override
   public boolean A() {
      return this.pC == 1;
   }
}
