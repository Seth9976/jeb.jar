package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

import java.util.HashSet;
import java.util.Set;

public class Nt extends ej {
   public Set q = new HashSet();

   @Override
   public String q() {
      if (this.q.isEmpty()) {
         return null;
      } else {
         StringBuilder var1 = new StringBuilder();
         var1.append("FAIL:");

         for (String var3 : this.q) {
            var1.append(var3 + System.getProperty("line.separator"));
         }

         return var1.toString();
      }
   }

   @Override
   public void q(String var1) {
      this.q.add(var1);
   }

   @Override
   public String RF() {
      return null;
   }
}
