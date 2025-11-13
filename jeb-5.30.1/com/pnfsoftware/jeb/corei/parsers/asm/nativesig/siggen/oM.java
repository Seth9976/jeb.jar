package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class oM implements iZ {
   private final Set q;
   private Map RF = new HashMap();

   public oM(Set var1, boolean var2) {
      if (var2) {
         this.q = new HashSet();

         for (INativeSignature var4 : var1) {
            com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV var5 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV(
               (com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var4
            );
            this.q.add(var5);
         }
      } else {
         this.q = var1;
      }
   }

   @Override
   public void q(INativeSignature var1, String var2) {
      if (!this.q.contains(var1)) {
         throw new RuntimeException("unknown sig");
      } else {
         Maps.putMulti(this.RF, var1, var2);
      }
   }

   @Override
   public List q(INativeSignature var1) {
      return (List)this.RF.get(var1);
   }

   public Set q() {
      return this.q;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
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
         oM var2 = (oM)var1;
         if (this.q == null) {
            if (var2.q != null) {
               return false;
            }
         } else if (!this.q.equals(var2.q)) {
            return false;
         }

         return true;
      }
   }
}
