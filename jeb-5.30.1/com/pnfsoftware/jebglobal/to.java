package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class to implements Iterable {
   private List q = new ArrayList();

   public to() {
   }

   public to(Vm... var1) {
      for (Vm var5 : var1) {
         if (var5 != null) {
            this.q.add(var5);
         }
      }
   }

   public int q() {
      return this.q.size();
   }

   public void RF() {
      this.q.clear();
   }

   public void q(Vm var1) {
      this.q.add(var1);
   }

   public Vm q(int var1) {
      return (Vm)this.q.get(var1);
   }

   public Vm RF(int var1) {
      for (Vm var3 : this.q) {
         if (var3.q == var1) {
            return var3;
         }
      }

      return null;
   }

   public Vm[] xK() {
      return (Vm[])this.q.toArray(new Vm[this.q.size()]);
   }

   @Override
   public String toString() {
      return Arrays.toString((Object[])this.xK());
   }

   @Override
   public Iterator iterator() {
      return new to.eo();
   }

   class eo implements Iterator {
      private int RF = 0;

      @Override
      public boolean hasNext() {
         return this.RF < to.this.q.size();
      }

      public Vm q() {
         return (Vm)to.this.q.get(this.RF++);
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
