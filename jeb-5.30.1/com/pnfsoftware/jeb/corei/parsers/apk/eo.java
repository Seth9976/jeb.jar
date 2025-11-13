package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jebglobal.cvq;

class eo extends cvq {
   int q;

   eo(ApkIdentifier var1, IInput var2) {
      super(var2);
      this.RF = var1;
   }

   @Override
   public void q() {
      this.q = 0;
   }

   @Override
   public Boolean q(String var1) {
      if (var1.equals("classes.dex")) {
         return true;
      } else {
         if (var1.equals("AndroidManifest.xml") || var1.equals("resources.arsc")) {
            this.q |= 1;
         } else if (var1.equals("classes.jar")) {
            this.q |= 2;
         }

         return null;
      }
   }

   @Override
   public boolean RF() {
      return this.q == 1;
   }
}
