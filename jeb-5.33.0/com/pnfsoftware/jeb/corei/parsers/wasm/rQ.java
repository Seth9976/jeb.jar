package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.chl;
import com.pnfsoftware.jebglobal.cht;

@Ser
public class rQ extends cht {
   @SerId(1)
   zp pC;
   @SerId(2)
   qt A;

   public rQ(zp var1, qt var2) {
      this.pC = var1;
      this.A = var2;
   }

   public rQ(RC var1) {
      this.pC(var1);
      this.pC = (zp)var1.kS();
   }

   public Integer pC() {
      if (this.A != null) {
         try {
            return this.A.pC();
         } catch (chl var1) {
         }
      }

      return null;
   }

   @Override
   protected String A() {
      return "__g" + this.wS();
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s:%s", super.toString(), this.pC, this.A);
   }
}
