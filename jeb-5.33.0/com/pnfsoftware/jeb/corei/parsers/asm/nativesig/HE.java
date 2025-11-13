package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class HE {
   @SerId(1)
   private INativeSignature pC;
   @SerId(2)
   private List A;

   public HE(INativeSignature var1, List var2) {
      this.pC = var1;
      this.A = var2;
   }

   public INativeSignature pC() {
      return this.pC;
   }

   public List A() {
      return this.A;
   }

   public boolean pC(String var1) {
      INativeSignature var2 = this.pC();
      if (var2 != null && ((KD)var2).pC(var1)) {
         return true;
      } else {
         if (this.A() != null) {
            for (INativeSignature var4 : this.A()) {
               if (((KD)var4).pC(var1)) {
                  return true;
               }
            }
         }

         return false;
      }
   }
}
