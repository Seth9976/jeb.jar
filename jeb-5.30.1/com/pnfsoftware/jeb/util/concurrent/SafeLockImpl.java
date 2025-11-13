package com.pnfsoftware.jeb.util.concurrent;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.objects.SerReentrantReadWriteLock;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Ser
public class SafeLockImpl implements ISafeLock {
   @SerId(1)
   private SerReentrantReadWriteLock l = new SerReentrantReadWriteLock();

   public ReentrantReadWriteLock getInternalLock() {
      return this.l.get();
   }

   public void lockWrite() {
      this.l.writeLock().lock();
   }

   public void unlockWrite() {
      this.l.writeLock().unlock();
   }

   public void lockRead() {
      this.l.readLock().lock();
   }

   public void unlockRead() {
      this.l.readLock().unlock();
   }

   public void lock(boolean var1) {
      if (var1) {
         this.lockWrite();
      } else {
         this.lockRead();
      }
   }

   public void unlock(boolean var1) {
      if (var1) {
         this.unlockWrite();
      } else {
         this.unlockRead();
      }
   }

   @Override
   public ACLock ro() {
      return new SafeLockImpl.ACSafeLock(this, false);
   }

   @Override
   public ACLock rw() {
      return new SafeLockImpl.ACSafeLock(this, true);
   }

   @SerDisabled
   static class ACSafeLock implements ACLock {
      private SafeLockImpl lock;
      private boolean forWriting;
      private AtomicBoolean locked;

      ACSafeLock(SafeLockImpl var1, boolean var2) {
         this.lock = var1;
         this.forWriting = var2;
         this.locked = new AtomicBoolean();
         var1.lock(var2);
         this.locked.set(true);
      }

      @Override
      public void close() {
         if (this.locked.compareAndSet(true, false)) {
            this.lock.unlock(this.forWriting);
         }
      }
   }
}
