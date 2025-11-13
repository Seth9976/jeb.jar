package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class RZ implements QW {
   private static QW pC;

   public static synchronized QW kS() {
      if (pC == null) {
         pC = new RZ();
      }

      return pC;
   }

   private RZ() {
   }

   @Override
   public boolean pC() {
      return false;
   }

   @Override
   public boolean A() {
      return false;
   }

   @Override
   public QW pC(Sp var1, long var2, com.pnfsoftware.jeb.corei.parsers.arm.rQ var4, boolean var5) {
      return this;
   }

   @Override
   public zx pC(INativeCodeAnalyzer var1, com.pnfsoftware.jeb.corei.parsers.arm.Av var2, Sp var3) {
      return new VX(var1, var2, var3, null);
   }
}
