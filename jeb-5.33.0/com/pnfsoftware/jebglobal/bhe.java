package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bhe {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   List kS;
   @SerId(4)
   List wS;
   @SerId(5)
   List UT;
   @SerId(6)
   List E;

   bhe(int var1) {
      this.pC = var1;
      this.A = -1;
      this.kS = new ArrayList();
      this.wS = new ArrayList();
      this.UT = new ArrayList();
      this.E = new ArrayList();
   }

   public boolean pC() {
      return this.A < 0;
   }

   public int A() {
      return this.pC;
   }

   public int kS() {
      return this.A;
   }

   public List wS() {
      return this.wS;
   }

   public List UT() {
      ArrayList var1 = new ArrayList(this.kS);
      if (this.A >= 0) {
         var1.add(this.A);
      }

      return var1;
   }
}
