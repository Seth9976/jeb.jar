package com.pnfsoftware.jebglobal;

public class asz extends RuntimeException {
   private static final long serialVersionUID = -146140748872138904L;
   String pC;

   public asz() {
      this("The input expression could not be converted to a Z3 expression");
   }

   public asz(String var1) {
      super(var1);
   }

   @Override
   public String getMessage() {
      String var1 = super.getMessage();
      if (this.pC != null) {
         var1 = var1 + this.pC;
      }

      return var1;
   }
}
