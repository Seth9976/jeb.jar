package com.pnfsoftware.jebglobal;

public class bol extends bop {
   public bol() {
      super(bop.Av.ys, bop.Sv.A);
   }

   @Override
   public String toString() {
      return "try-except (end of guarded block)";
   }
}
