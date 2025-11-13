package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public abstract class bop extends bio {
   IJavaStatement ys;
   bop.Av ld;
   bop.Sv gp;

   public bop(bop.Av var1, bop.Sv var2) {
      this.ld = var1;
      this.gp = var2;
   }

   public bop.Av E() {
      return this.ld;
   }

   public bop.Sv sY() {
      return this.gp;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      throw new RuntimeException("Do not call: a pseudo-statement should not be generated");
   }

   @Override
   public JavaElementType getElementType() {
      return null;
   }

   @Override
   public String toString() {
      return this.getClass().getSimpleName() + "@" + this.hashCode();
   }

   public bop ys() {
      throw new RuntimeException("Do not use");
   }

   public static enum Av {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY,
      ys,
      ld;
   }

   public static enum Sv {
      pC,
      A,
      kS;
   }
}
