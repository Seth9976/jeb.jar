package com.pnfsoftware.jebglobal;

public class cig extends RuntimeException {
   private static final long serialVersionUID = -146140748872138904L;
   String q;

   public cig() {
      this("Unexpected IR cannot be converted.");
   }

   public cig(String var1) {
      super(var1);
   }

   @Override
   public String getMessage() {
      String var1 = super.getMessage();
      if (this.q != null) {
         var1 = var1 + this.q;
      }

      return var1;
   }

   public void q(String var1) {
      this.q = var1;
   }
}
