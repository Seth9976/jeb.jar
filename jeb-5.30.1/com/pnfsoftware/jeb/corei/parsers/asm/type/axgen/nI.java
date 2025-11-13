package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SerDisabled
public class nI implements vn {
   private List q = new ArrayList();

   public void q(ej var1) {
      if (var1 != null) {
         this.q.add(var1);
      }
   }

   public List q() {
      return Collections.unmodifiableList(this.q);
   }

   @Override
   public CU q(String var1) {
      for (ej var3 : this.q) {
         CU var4 = var3.q(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }
}
