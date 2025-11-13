package com.pnfsoftware.jebglobal;

public class bnz extends bop {
   public bnz() {
      super(bop.Av.A, bop.Sv.kS);
   }

   @Override
   public String toString() {
      return "if-end";
   }
}
