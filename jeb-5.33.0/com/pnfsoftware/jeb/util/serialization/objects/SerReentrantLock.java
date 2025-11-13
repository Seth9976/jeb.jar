package com.pnfsoftware.jeb.util.serialization.objects;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Ser
public class SerReentrantLock implements Lock {
   @SerId(1)
   private boolean fair;
   @SerTransient
   private ReentrantLock l;

   @SerCustomInit
   private void init() {
      this.l = new ReentrantLock(this.fair);
   }

   public SerReentrantLock() {
      this.init();
   }

   public SerReentrantLock(boolean var1) {
      this.fair = var1;
      this.init();
   }

   public ReentrantLock get() {
      return this.l;
   }

   @Override
   public void lock() {
      this.l.lock();
   }

   @Override
   public void unlock() {
      this.l.unlock();
   }

   public boolean isHeldByCurrentThread() {
      return this.l.isHeldByCurrentThread();
   }

   @Override
   public void lockInterruptibly() throws InterruptedException {
      this.l.lockInterruptibly();
   }

   @Override
   public boolean tryLock() {
      return this.l.tryLock();
   }

   @Override
   public boolean tryLock(long var1, TimeUnit var3) throws InterruptedException {
      return this.l.tryLock(var1, var3);
   }

   @Override
   public Condition newCondition() {
      return this.l.newCondition();
   }

   public int getHoldCount() {
      return this.l.getHoldCount();
   }

   public boolean isLocked() {
      return this.l.isLocked();
   }

   public final boolean isFair() {
      return this.l.isFair();
   }

   public final boolean hasQueuedThreads() {
      return this.l.hasQueuedThreads();
   }

   public final boolean hasQueuedThread(Thread var1) {
      return this.l.hasQueuedThread(var1);
   }

   public final int getQueueLength() {
      return this.l.getQueueLength();
   }

   public boolean hasWaiters(Condition var1) {
      return this.l.hasWaiters(var1);
   }

   public int getWaitQueueLength(Condition var1) {
      return this.l.getWaitQueueLength(var1);
   }
}
