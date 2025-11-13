package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.List;

class avz {
   String pC;
   char[] A;
   int kS;
   List wS;
   List UT;
   awe E;
   int sY;
   boolean ys;
   boolean ld;
   public int gp;

   public avz(String var1) {
      this.pC = var1;
      this.A = var1.toCharArray();
      this.kS = 0;
      this.wS = new ArrayList();
      this.UT = new ArrayList();
   }

   public avz(avz var1) {
      this.pC = var1.pC;
      this.A = this.pC.toCharArray();
      this.kS = var1.kS;
      this.wS = new ArrayList(var1.wS);
      this.UT = new ArrayList(var1.UT);
      this.sY = var1.sY;
   }

   public char pC() {
      try {
         return this.A[this.kS];
      } catch (ArrayIndexOutOfBoundsException var1) {
         return '\u0000';
      }
   }

   public char A() {
      try {
         return this.A[this.kS + 1];
      } catch (ArrayIndexOutOfBoundsException var1) {
         return '\u0000';
      }
   }

   public void pC(int var1) {
      this.kS += var1;
   }

   public boolean pC(char var1) {
      if (this.pC() == var1) {
         this.kS++;
         return true;
      } else {
         return false;
      }
   }

   public char kS() {
      char var1 = this.pC();
      this.kS++;
      return var1;
   }

   public String wS() {
      return this.pC.substring(this.kS);
   }

   public boolean pC(awe var1) {
      if (var1 == null) {
         return false;
      } else {
         this.UT.add(var1);
         return true;
      }
   }
}
