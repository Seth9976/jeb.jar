package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.lang.invoke.StringConcatFactory;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Ser
public class CU {
   @SerId(1)
   String q;
   @SerId(2)
   String RF;
   @SerId(3)
   qV xK;
   @SerId(4)
   Map Dw;

   public CU(String var1) {
      this.q = var1;
   }

   public String q() {
      return this.q;
   }

   public String RF() {
      return this.RF;
   }

   public qV xK() {
      return this.xK;
   }

   public iZ q(int var1) {
      return this.Dw == null ? null : (iZ)this.Dw.get(var1);
   }

   public Map Dw() {
      return this.Dw == null ? Collections.emptyMap() : this.Dw;
   }

   iZ RF(int var1) {
      Assert.a(var1 >= 1);
      if (this.Dw == null) {
         this.Dw = new TreeMap();
      }

      iZ var2 = (iZ)this.Dw.get(var1);
      if (var2 == null) {
         var2 = new iZ();
         this.Dw.put(var1, var2);
      }

      return var2;
   }

   public boolean Uv() {
      return this.RF == null && this.xK == null && (this.Dw == null || this.Dw.isEmpty());
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.q);
   }
}
