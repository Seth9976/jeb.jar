package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Ser
public class ConcurrentList implements List {
   @SerId(1)
   private List list;
   @SerTransient
   private ReadWriteLock lock;

   @SerCustomInitPostGraph
   private void init() {
      this.lock = new ReentrantReadWriteLock();
   }

   public ConcurrentList() {
      this(new ArrayList());
   }

   public ConcurrentList(int var1) {
      this(new ArrayList(var1));
   }

   public ConcurrentList(ArrayList var1) {
      this.list = var1;
      this.lock = new ReentrantReadWriteLock();
   }

   @Override
   public boolean isEmpty() {
      this.lock.readLock().lock();

      boolean var1;
      try {
         var1 = this.list.isEmpty();
      } finally {
         this.lock.readLock().unlock();
      }

      return var1;
   }

   @Override
   public int size() {
      this.lock.readLock().lock();

      int var1;
      try {
         var1 = this.list.size();
      } finally {
         this.lock.readLock().unlock();
      }

      return var1;
   }

   @Override
   public Object get(int var1) {
      this.lock.readLock().lock();

      Object var2;
      try {
         var2 = this.list.get(var1);
      } finally {
         this.lock.readLock().unlock();
      }

      return var2;
   }

   public Object getSafe(int var1, Object var2) {
      this.lock.readLock().lock();

      Object var3;
      try {
         if (var1 < this.list.size()) {
            return this.list.get(var1);
         }

         var3 = var2;
      } finally {
         this.lock.readLock().unlock();
      }

      return var3;
   }

   @Override
   public Object set(int var1, Object var2) {
      this.lock.writeLock().lock();

      Object var3;
      try {
         var3 = this.list.set(var1, var2);
      } finally {
         this.lock.writeLock().unlock();
      }

      return var3;
   }

   public boolean setSafe(int var1, Object var2) {
      this.lock.writeLock().lock();

      boolean var3;
      try {
         if (var1 < this.list.size()) {
            this.list.set(var1, var2);
            return true;
         }

         var3 = false;
      } finally {
         this.lock.writeLock().unlock();
      }

      return var3;
   }

   @Override
   public void add(int var1, Object var2) {
      this.lock.writeLock().lock();

      try {
         this.list.add(var1, var2);
      } finally {
         this.lock.writeLock().unlock();
      }
   }

   public boolean addSafe(int var1, Object var2) {
      this.lock.writeLock().lock();

      boolean var3;
      try {
         if (var1 <= this.list.size()) {
            this.list.add(var1, var2);
            return true;
         }

         var3 = false;
      } finally {
         this.lock.writeLock().unlock();
      }

      return var3;
   }

   @Override
   public boolean add(Object var1) {
      this.lock.writeLock().lock();

      boolean var2;
      try {
         var2 = this.list.add(var1);
      } finally {
         this.lock.writeLock().unlock();
      }

      return var2;
   }

   @Override
   public boolean addAll(Collection var1) {
      this.lock.writeLock().lock();

      boolean var2;
      try {
         var2 = this.list.addAll(var1);
      } finally {
         this.lock.writeLock().unlock();
      }

      return var2;
   }

   @Override
   public boolean addAll(int var1, Collection var2) {
      this.lock.writeLock().lock();

      boolean var3;
      try {
         var3 = this.list.addAll(var1, var2);
      } finally {
         this.lock.writeLock().unlock();
      }

      return var3;
   }

   @Override
   public boolean contains(Object var1) {
      this.lock.readLock().lock();

      boolean var2;
      try {
         var2 = this.list.contains(var1);
      } finally {
         this.lock.readLock().unlock();
      }

      return var2;
   }

   @Override
   public boolean containsAll(Collection var1) {
      this.lock.readLock().lock();

      boolean var2;
      try {
         var2 = this.list.containsAll(var1);
      } finally {
         this.lock.readLock().unlock();
      }

      return var2;
   }

   @Override
   public void clear() {
      this.lock.writeLock().lock();

      try {
         this.list.clear();
      } finally {
         this.lock.writeLock().unlock();
      }
   }

   @Override
   public boolean remove(Object var1) {
      this.lock.writeLock().lock();

      boolean var2;
      try {
         var2 = this.list.remove(var1);
      } finally {
         this.lock.writeLock().unlock();
      }

      return var2;
   }

   @Override
   public Object remove(int var1) {
      this.lock.writeLock().lock();

      Object var2;
      try {
         var2 = this.list.remove(var1);
      } finally {
         this.lock.writeLock().unlock();
      }

      return var2;
   }

   public boolean removeSafe(int var1) {
      this.lock.writeLock().lock();

      boolean var2;
      try {
         if (var1 < this.list.size()) {
            this.list.remove(var1);
            return true;
         }

         var2 = false;
      } finally {
         this.lock.writeLock().unlock();
      }

      return var2;
   }

   @Override
   public boolean removeAll(Collection var1) {
      this.lock.writeLock().lock();

      boolean var2;
      try {
         var2 = this.list.removeAll(var1);
      } finally {
         this.lock.writeLock().unlock();
      }

      return var2;
   }

   @Override
   public boolean retainAll(Collection var1) {
      this.lock.writeLock().lock();

      boolean var2;
      try {
         var2 = this.list.retainAll(var1);
      } finally {
         this.lock.writeLock().unlock();
      }

      return var2;
   }

   @Override
   public int indexOf(Object var1) {
      this.lock.readLock().lock();

      int var2;
      try {
         var2 = this.list.indexOf(var1);
      } finally {
         this.lock.readLock().unlock();
      }

      return var2;
   }

   @Override
   public int lastIndexOf(Object var1) {
      this.lock.readLock().lock();

      int var2;
      try {
         var2 = this.list.lastIndexOf(var1);
      } finally {
         this.lock.readLock().unlock();
      }

      return var2;
   }

   @Override
   public Object[] toArray() {
      this.lock.readLock().lock();

      Object[] var1;
      try {
         var1 = this.list.toArray();
      } finally {
         this.lock.readLock().unlock();
      }

      return var1;
   }

   @Override
   public Object[] toArray(Object[] var1) {
      this.lock.readLock().lock();

      Object[] var2;
      try {
         var2 = this.list.toArray(var1);
      } finally {
         this.lock.readLock().unlock();
      }

      return var2;
   }

   @Override
   public List subList(int var1, int var2) {
      this.lock.readLock().lock();

      ArrayList var3;
      try {
         var3 = new ArrayList(this.list.subList(var1, var2));
      } finally {
         this.lock.readLock().unlock();
      }

      return var3;
   }

   @Override
   public ListIterator listIterator() {
      this.lock.readLock().lock();

      ListIterator var1;
      try {
         var1 = new ArrayList(this.list).listIterator();
      } finally {
         this.lock.readLock().unlock();
      }

      return var1;
   }

   @Override
   public ListIterator listIterator(int var1) {
      this.lock.readLock().lock();

      ListIterator var2;
      try {
         var2 = new ArrayList(this.list.subList(var1, this.list.size())).listIterator();
      } finally {
         this.lock.readLock().unlock();
      }

      return var2;
   }

   @Override
   public Iterator iterator() {
      this.lock.readLock().lock();

      Iterator var1;
      try {
         var1 = new ArrayList(this.list).iterator();
      } finally {
         this.lock.readLock().unlock();
      }

      return var1;
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return super.equals(var1);
   }

   @Override
   public String toString() {
      return "ConcurrentList@" + this.hashCode();
   }
}
