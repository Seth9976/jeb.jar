package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

import java.util.HashSet;
import java.util.Set;

public class Sv extends Ws {
   public Set pC = new HashSet();

   @Override
   public String pC() {
      if (this.pC.isEmpty()) {
         return null;
      } else {
         StringBuilder var1 = new StringBuilder();
         var1.append("CCALL:");

         for (String var3 : this.pC) {
            var1.append(var3 + System.getProperty("line.separator"));
         }

         return var1.toString();
      }
   }

   public void pC(String var1) {
      this.pC.add(var1);
   }

   @Override
   public String A() {
      return null;
   }
}
