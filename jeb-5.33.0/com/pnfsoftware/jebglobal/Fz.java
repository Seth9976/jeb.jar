package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Fz implements Iterable {
   private List pC = new ArrayList();

   public Fz() {
   }

   public Fz(AM... var1) {
      for (AM var5 : var1) {
         if (var5 != null) {
            this.pC.add(var5);
         }
      }
   }

   public int pC() {
      return this.pC.size();
   }

   public void pC(AM var1) {
      this.pC.add(var1);
   }

   public AM[] A() {
      return (AM[])this.pC.toArray(new AM[this.pC.size()]);
   }

   @Override
   public String toString() {
      return Arrays.toString((Object[])this.A());
   }

   @Override
   public Iterator iterator() {
      return new Fz.Av();
   }

   class Av implements Iterator {
      private int A = 0;

      @Override
      public boolean hasNext() {
         return this.A < Fz.this.pC.size();
      }

      public AM pC() {
         return (AM)Fz.this.pC.get(this.A++);
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
