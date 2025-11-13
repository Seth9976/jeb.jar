package com.pnfsoftware.jeb.util.serialization.objects;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

@Ser
public class SerReentrantReadWriteLock implements ReadWriteLock {
   @SerId(1)
   private boolean fair;
   @SerTransient
   private ReentrantReadWriteLock l;

   @SerCustomInit
   private void init() {
      this.l = new ReentrantReadWriteLock(this.fair);
   }

   public SerReentrantReadWriteLock() {
      this.init();
   }

   public SerReentrantReadWriteLock(boolean var1) {
      this.fair = var1;
      this.init();
   }

   public ReentrantReadWriteLock get() {
      return this.l;
   }

   public WriteLock writeLock() {
      return this.l.writeLock();
   }

   public ReadLock readLock() {
      return this.l.readLock();
   }

   public final boolean isFair() {
      return this.l.isFair();
   }

   public int getReadLockCount() {
      return this.l.getReadLockCount();
   }

   public boolean isWriteLocked() {
      return this.l.isWriteLocked();
   }

   public boolean isWriteLockedByCurrentThread() {
      return this.l.isWriteLockedByCurrentThread();
   }

   public int getWriteHoldCount() {
      return this.l.getWriteHoldCount();
   }

   public int getReadHoldCount() {
      return this.l.getReadHoldCount();
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
