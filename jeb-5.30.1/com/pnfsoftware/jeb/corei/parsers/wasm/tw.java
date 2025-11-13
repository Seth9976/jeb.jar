package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.cop;
import com.pnfsoftware.jebglobal.cox;

@Ser
public class tw extends cox {
   @SerId(1)
   EE q;
   @SerId(2)
   Vj RF;

   public tw(EE var1, Vj var2) {
      this.q = var1;
      this.RF = var2;
   }

   public tw(PY var1) {
      this.q(var1);
      this.q = (EE)var1.xK();
   }

   public Integer q() {
      if (this.RF != null) {
         try {
            return this.RF.q();
         } catch (cop var1) {
         }
      }

      return null;
   }

   @Override
   protected String Uv() {
      return "__g" + this.gO();
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s:%s", super.toString(), this.q, this.RF);
   }
}
