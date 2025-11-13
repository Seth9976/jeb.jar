package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.List;

class ayw {
   String q;
   char[] RF;
   int xK;
   int Dw;
   List Uv;
   List oW;
   azb gO;
   int nf;
   boolean gP;
   boolean za;
   public int lm;

   public ayw(String var1) {
      this.q = var1;
      this.RF = var1.toCharArray();
      this.xK = 0;
      this.Uv = new ArrayList();
      this.oW = new ArrayList();
   }

   public ayw(ayw var1) {
      this.q = var1.q;
      this.RF = this.q.toCharArray();
      this.xK = var1.xK;
      this.Uv = new ArrayList(var1.Uv);
      this.oW = new ArrayList(var1.oW);
      this.nf = var1.nf;
   }

   public char q() {
      try {
         return this.RF[this.xK];
      } catch (ArrayIndexOutOfBoundsException var1) {
         return '\u0000';
      }
   }

   public char RF() {
      try {
         return this.RF[this.xK + 1];
      } catch (ArrayIndexOutOfBoundsException var1) {
         return '\u0000';
      }
   }

   public void q(int var1) {
      this.xK += var1;
   }

   public boolean q(char var1) {
      if (this.q() == var1) {
         this.xK++;
         return true;
      } else {
         return false;
      }
   }

   public char xK() {
      char var1 = this.q();
      this.xK++;
      return var1;
   }

   public String Dw() {
      return this.q.substring(this.xK);
   }

   public boolean q(azb var1) {
      if (var1 == null) {
         return false;
      } else {
         this.oW.add(var1);
         return true;
      }
   }
}
