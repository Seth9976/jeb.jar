package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.lang.invoke.StringConcatFactory;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Ser
public class Av {
   @SerId(1)
   String pC;
   @SerId(2)
   String A;
   @SerId(3)
   zp kS;
   @SerId(4)
   Map wS;

   public Av(String var1) {
      this.pC = var1;
   }

   public String pC() {
      return this.A;
   }

   public cq pC(int var1) {
      return this.wS == null ? null : (cq)this.wS.get(var1);
   }

   public Map A() {
      return this.wS == null ? Collections.emptyMap() : this.wS;
   }

   cq A(int var1) {
      Assert.a(var1 >= 1);
      if (this.wS == null) {
         this.wS = new TreeMap();
      }

      cq var2 = (cq)this.wS.get(var1);
      if (var2 == null) {
         var2 = new cq();
         this.wS.put(var1, var2);
      }

      return var2;
   }

   public boolean kS() {
      return this.A == null && this.kS == null && (this.wS == null || this.wS.isEmpty());
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.pC);
   }
}
