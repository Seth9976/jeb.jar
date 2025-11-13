package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class lv implements Iterable {
   private final List q;

   public lv(List var1) {
      this.q = var1;
   }

   @Override
   public Iterator iterator() {
      return this.q.iterator();
   }

   public static lv q(mC var0) {
      ArrayList var1 = new ArrayList();
      var1.addAll(Arrays.asList(Sx.Uv, Sx.xK, Sx.Dw, Sx.RF));
      IB.xK.q(var0);
      var1.add(IB.xK);
      IB.Dw.q(var0);
      var1.add(IB.Dw);
      return new lv(var1);
   }

   public static lv RF(mC var0) {
      ArrayList var1 = new ArrayList();
      var1.addAll(Arrays.asList(Sx.gO, Sx.nf, Sx.oW));
      IB.q.q(var0);
      var1.add(IB.q);
      IB.RF.q(var0);
      var1.add(IB.RF);
      return new lv(var1);
   }
}
