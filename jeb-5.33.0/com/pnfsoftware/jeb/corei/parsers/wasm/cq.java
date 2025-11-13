package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.cht;

@Ser
public class cq extends cht {
   @SerId(1)
   DH pC;
   @SerId(2)
   bO A;

   cq(DH var1, bO var2) {
      this.pC = var1;
      this.A = var2;
   }

   cq(yt var1) {
      this.pC(var1);
      this.pC = (DH)var1.kS();
   }

   public DH pC() {
      return this.pC;
   }

   @Override
   protected String A() {
      return "__f" + this.wS();
   }

   public String kS() {
      return Strings.ff("proto=%s,body=%s", this.pC, this.A.A());
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s:%s", super.toString(), this.pC, this.A);
   }
}
