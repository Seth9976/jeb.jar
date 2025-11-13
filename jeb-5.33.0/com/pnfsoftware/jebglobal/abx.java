package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class abx {
   @SerId(1)
   private final long pC;
   @SerId(2)
   private final long A;
   @SerId(3)
   private final List kS;

   private abx(long var1, long var3, List var5) {
      this.pC = var1;
      this.A = var3;
      this.kS = var5;
   }

   public static abx pC(long var0, long var2, abu var4) {
      ArrayList var5 = new ArrayList();
      var5.add(var4);
      return new abx(var0, var2, var5);
   }

   public static abx pC(long var0, long var2, List var4) {
      return new abx(var0, var2, var4);
   }

   public long pC() {
      return this.pC;
   }

   public long A() {
      return this.A;
   }

   public List kS() {
      return this.kS;
   }
}
