package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class oL {
   @SerId(1)
   private INativeSignature q;
   @SerId(2)
   private List RF;

   public oL(INativeSignature var1, List var2) {
      this.q = var1;
      this.RF = var2;
   }

   public INativeSignature q() {
      return this.q;
   }

   public List RF() {
      return this.RF;
   }

   public boolean q(String var1) {
      INativeSignature var2 = this.q();
      if (var2 != null && ((qV)var2).q(var1)) {
         return true;
      } else {
         if (this.RF() != null) {
            for (INativeSignature var4 : this.RF()) {
               if (((qV)var4).q(var1)) {
                  return true;
               }
            }
         }

         return false;
      }
   }
}
