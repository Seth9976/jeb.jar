package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class rQ implements DH {
   private final INativeSignature pC;
   private final Set A;
   private Map kS = new HashMap();

   public rQ(INativeSignature var1, Set var2, boolean var3) {
      if (var3) {
         this.pC = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var1);
         this.A = new HashSet();

         for (INativeSignature var5 : var2) {
            this.A.add(new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var5));
         }
      } else {
         this.pC = var1;
         this.A = var2;
      }
   }

   @Override
   public void pC(INativeSignature var1, String var2) {
      if (!this.A.contains(var1) && !this.pC.equals(var1)) {
         throw new RuntimeException("unknown sig");
      } else {
         Maps.putMulti(this.kS, var1, var2);
      }
   }

   @Override
   public List pC(INativeSignature var1) {
      return (List)this.kS.get(var1);
   }

   public Set pC() {
      return this.A;
   }

   public INativeSignature A() {
      return this.pC;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         rQ var2 = (rQ)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return true;
      }
   }
}
