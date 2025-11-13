package com.pnfsoftware.jeb.corei.parsers.jar;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jebglobal.cvq;

class eo extends cvq {
   int q;

   eo(JarIdentifier var1, IInput var2) {
      super(var2);
      this.RF = var1;
   }

   @Override
   public void q() {
      this.q = 0;
   }

   @Override
   public Boolean q(String var1) {
      if ((this.q & 1) == 0 && var1.startsWith("META-INF/")) {
         this.q |= 1;
         if (this.q == 3) {
            return true;
         }
      } else if ((this.q & 2) == 0 && var1.endsWith(".class")) {
         this.q |= 2;
         if (this.q == 3) {
            return true;
         }
      }

      return null;
   }

   @Override
   public boolean RF() {
      return this.q == 3;
   }
}
