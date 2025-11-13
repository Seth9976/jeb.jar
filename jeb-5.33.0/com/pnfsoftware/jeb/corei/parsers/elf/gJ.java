package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class gJ {
   @SerId(1)
   private List pC = new ArrayList();

   public boolean pC(long var1) {
      for (ma var4 : this.pC) {
         if (var4.UT == var1) {
            return true;
         }
      }

      return false;
   }

   public void pC(ma var1) {
      if (!this.pC.contains(var1)) {
         this.pC.add(var1);
      }
   }

   public List pC() {
      return this.pC;
   }

   @Override
   public String toString() {
      return Strings.ff("%d tables:\n%s", this.pC.size(), this.pC);
   }
}
