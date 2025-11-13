package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

public class EE extends ej {
   private int q;
   private String RF = null;

   public void q(int var1) {
      this.q = var1;
   }

   public void RF(String var1) {
      this.RF = var1;
   }

   @Override
   public String q() {
      return null;
   }

   @Override
   public void q(String var1) {
   }

   @Override
   public String RF() {
      if (this.RF != null) {
         StringBuilder var1 = new StringBuilder();
         var1.append("Pred:").append(this.q).append(":").append(this.RF);
         var1.append(System.getProperty("line.separator"));
         return var1.toString();
      } else {
         return null;
      }
   }
}
