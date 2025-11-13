package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class gR implements iD {
   private static iD q;

   public static synchronized iD xK() {
      if (q == null) {
         q = new gR();
      }

      return q;
   }

   private gR() {
   }

   @Override
   public boolean q() {
      return false;
   }

   @Override
   public boolean RF() {
      return false;
   }

   @Override
   public iD q(bR var1, long var2, fA var4, boolean var5) {
      return this;
   }

   @Override
   public BH q(INativeCodeAnalyzer var1, FS var2, bR var3) {
      return new jp(var1, var2, var3, null);
   }
}
