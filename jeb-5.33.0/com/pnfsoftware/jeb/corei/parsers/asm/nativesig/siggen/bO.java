package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bO implements DH {
   private final Set pC;
   private Map A = new HashMap();

   public bO(Set var1, boolean var2) {
      if (var2) {
         this.pC = new HashSet();

         for (INativeSignature var4 : var1) {
            com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD var5 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD(
               (com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var4
            );
            this.pC.add(var5);
         }
      } else {
         this.pC = var1;
      }
   }

   @Override
   public void pC(INativeSignature var1, String var2) {
      if (!this.pC.contains(var1)) {
         throw new RuntimeException("unknown sig");
      } else {
         Maps.putMulti(this.A, var1, var2);
      }
   }

   @Override
   public List pC(INativeSignature var1) {
      return (List)this.A.get(var1);
   }

   public Set pC() {
      return this.pC;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bO var2 = (bO)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         return true;
      }
   }
}
