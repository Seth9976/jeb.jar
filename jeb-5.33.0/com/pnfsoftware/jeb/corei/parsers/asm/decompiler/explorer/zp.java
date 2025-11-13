package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

public class zp extends Ws {
   private int pC;
   private String A = null;

   public void pC(int var1) {
      this.pC = var1;
   }

   public void pC(String var1) {
      this.A = var1;
   }

   @Override
   public String pC() {
      return null;
   }

   @Override
   public String A() {
      if (this.A != null) {
         StringBuilder var1 = new StringBuilder();
         var1.append("Pred:").append(this.pC).append(":").append(this.A);
         var1.append(System.getProperty("line.separator"));
         return var1.toString();
      } else {
         return null;
      }
   }
}
