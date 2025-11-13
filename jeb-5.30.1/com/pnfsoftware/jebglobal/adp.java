package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class adp {
   @SerId(1)
   private final long q;
   @SerId(2)
   private final long RF;
   @SerId(3)
   private final List xK;

   private adp(long var1, long var3, List var5) {
      this.q = var1;
      this.RF = var3;
      this.xK = var5;
   }

   public static adp q(long var0, long var2, adm var4) {
      ArrayList var5 = new ArrayList();
      var5.add(var4);
      return new adp(var0, var2, var5);
   }

   public static adp q(long var0, long var2, List var4) {
      return new adp(var0, var2, var4);
   }

   public long q() {
      return this.q;
   }

   public long RF() {
      return this.RF;
   }

   public List xK() {
      return this.xK;
   }
}
