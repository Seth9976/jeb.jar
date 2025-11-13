package com.pnfsoftware.jeb.util.collect;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeakPseudoList implements Iterable {
   private List list;

   public WeakPseudoList() {
      this.list = new ArrayList();
   }

   public WeakPseudoList(int var1) {
      this.list = new ArrayList(var1);
   }

   public WeakPseudoList(Iterable var1) {
      this.list = new ArrayList();
      this.addAll(var1);
   }

   @Override
   public Iterator iterator() {
      return new WeakPseudoList.Itr();
   }

   public int size() {
      return this.list.size();
   }

   public boolean isEmpty() {
      return this.list.isEmpty();
   }

   public void clear() {
      this.list.clear();
   }

   public Object get(int var1) {
      WeakReference var2 = (WeakReference)this.list.get(var1);
      return var2 == null ? null : var2.get();
   }

   public boolean add(Object var1) {
      return this.list.add(new WeakReference(var1));
   }

   public void add(int var1, Object var2) {
      this.list.add(var1, new WeakReference(var2));
   }

   public void addAll(Iterable var1) {
      for (Object var3 : var1) {
         this.list.add(new WeakReference(var3));
      }
   }

   public Object set(int var1, Object var2) {
      WeakReference var3 = (WeakReference)this.list.set(var1, new WeakReference(var2));
      return var3.get();
   }

   public Object remove(int var1) {
      WeakReference var2 = (WeakReference)this.list.remove(var1);
      return var2.get();
   }

   private class Itr implements Iterator {
      Iterator listIterator = WeakPseudoList.this.list.iterator();

      @Override
      public boolean hasNext() {
         return this.listIterator.hasNext();
      }

      @Override
      public Object next() {
         WeakReference var1 = (WeakReference)this.listIterator.next();
         return var1.get();
      }

      @Override
      public void remove() {
         this.listIterator.remove();
      }
   }
}
