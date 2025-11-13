package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FX implements Iterable {
   private final List pC;

   public FX(List var1) {
      this.pC = var1;
   }

   @Override
   public Iterator iterator() {
      return this.pC.iterator();
   }

   public static FX pC(com.pnfsoftware.jeb.corei.parsers.arm.Ws var0) {
      ArrayList var1 = new ArrayList();
      var1.addAll(Arrays.asList(hT.UT, hT.kS, hT.wS, hT.A));
      Mu.kS.pC(var0);
      var1.add(Mu.kS);
      Mu.wS.pC(var0);
      var1.add(Mu.wS);
      return new FX(var1);
   }

   public static FX A(com.pnfsoftware.jeb.corei.parsers.arm.Ws var0) {
      ArrayList var1 = new ArrayList();
      var1.addAll(Arrays.asList(hT.sY, hT.ys, hT.E));
      Mu.pC.pC(var0);
      var1.add(Mu.pC);
      Mu.A.pC(var0);
      var1.add(Mu.A);
      return new FX(var1);
   }
}
