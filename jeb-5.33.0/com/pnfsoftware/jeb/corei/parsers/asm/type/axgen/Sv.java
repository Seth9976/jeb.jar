package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.List;

@SerDisabled
public class Sv {
   private List pC = new ArrayList();

   public void pC(K var1) {
      if (var1 != null) {
         this.pC.add(var1);
      }
   }

   public Av pC(String var1) {
      for (K var3 : this.pC) {
         Av var4 = var3.pC(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }
}
