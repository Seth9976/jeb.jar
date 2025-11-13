package com.pnfsoftware.jeb.corei.parsers.jar;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jebglobal.ckz;

class Av extends ckz {
   int pC;

   Av(JarIdentifier var1, IInput var2) {
      super(var2);
      this.A = var1;
   }

   @Override
   public void pC() {
      this.pC = 0;
   }

   @Override
   public Boolean pC(String var1) {
      if ((this.pC & 1) == 0 && var1.startsWith("META-INF/")) {
         this.pC |= 1;
         if (this.pC == 3) {
            return true;
         }
      } else if ((this.pC & 2) == 0 && var1.endsWith(".class")) {
         this.pC |= 2;
         if (this.pC == 3) {
            return true;
         }
      }

      return null;
   }

   @Override
   public boolean A() {
      return this.pC == 3;
   }
}
