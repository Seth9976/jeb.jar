package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.Func;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;

@Ser
public class CU {
   @SerId(1)
   Func q;
   @SerId(2)
   Collection RF;

   public CU(Func var1) {
      this.q = var1;
   }

   public CU(Collection var1) {
      this.RF = var1;
   }

   public String q() {
      if (this.q != null) {
         return Strings.ff("unique match: %s", this.q.toString());
      } else {
         return this.RF != null ? Strings.ff("possible matches: %s", this.RF.toString()) : "";
      }
   }

   public Func RF() {
      return this.q;
   }

   public Collection xK() {
      return this.RF;
   }
}
