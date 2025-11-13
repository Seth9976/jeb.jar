package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class tw implements iZ {
   private final INativeSignature q;
   private final Set RF;
   private Map xK = new HashMap();

   public tw(INativeSignature var1, Set var2, boolean var3) {
      if (var3) {
         this.q = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var1);
         this.RF = new HashSet();

         for (INativeSignature var5 : var2) {
            this.RF.add(new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var5));
         }
      } else {
         this.q = var1;
         this.RF = var2;
      }
   }

   @Override
   public void q(INativeSignature var1, String var2) {
      if (!this.RF.contains(var1) && !this.q.equals(var1)) {
         throw new RuntimeException("unknown sig");
      } else {
         Maps.putMulti(this.xK, var1, var2);
      }
   }

   @Override
   public List q(INativeSignature var1) {
      return (List)this.xK.get(var1);
   }

   public Set q() {
      return this.RF;
   }

   public INativeSignature RF() {
      return this.q;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         tw var2 = (tw)var1;
         if (this.q == null) {
            if (var2.q != null) {
               return false;
            }
         } else if (!this.q.equals(var2.q)) {
            return false;
         }

         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         return true;
      }
   }
}
