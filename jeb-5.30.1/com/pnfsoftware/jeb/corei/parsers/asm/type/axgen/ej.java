package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.Map;

@Ser
public class ej implements vn {
   @SerId(1)
   oM q;
   @SerId(2)
   Map RF;

   public ej(oM var1, Map var2) {
      this.q = var1;
      this.RF = var2;
   }

   public String q() {
      return this.q.q();
   }

   public String RF() {
      return this.q.RF();
   }

   public String xK() {
      return this.q.xK();
   }

   public long Dw() {
      return this.q.Dw();
   }

   public int Uv() {
      return this.RF.size();
   }

   public Map oW() {
      return Collections.unmodifiableMap(this.RF);
   }

   @Override
   public CU q(String var1) {
      if (var1 == null) {
         return null;
      } else {
         if (var1.startsWith("→")) {
            var1 = var1.substring("→".length());
         }

         return (CU)this.RF.get(var1);
      }
   }
}
