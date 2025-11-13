package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class abw implements abu {
   @SerId(1)
   private final long pC;
   @SerId(2)
   private final List A;

   private abw(long var1, List var3) {
      this.pC = var1;
      this.A = var3;
   }

   public static abw pC(long var0, abt var2) {
      ArrayList var3 = new ArrayList();
      var3.add(var2);
      return new abw(var0, var3);
   }

   public static abw pC(long var0) {
      return new abw(var0, null);
   }

   @Override
   public boolean A() {
      return this.A == null;
   }

   @Override
   public long pC() {
      return this.pC;
   }

   @Override
   public List kS() {
      return this.A;
   }
}
