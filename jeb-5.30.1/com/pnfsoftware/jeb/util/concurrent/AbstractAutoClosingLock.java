package com.pnfsoftware.jeb.util.concurrent;

import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jeb.util.serialization.objects.SerReentrantLock;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

@SerDisabled
public class AbstractAutoClosingLock implements ACLock {
   private Lock lock;
   protected AtomicBoolean locked = new AtomicBoolean();

   public AbstractAutoClosingLock(SerReentrantLock var1) {
      this.lock = var1;
   }

   @Override
   public final void close() {
      if (this.locked.compareAndSet(true, false)) {
         this.lock.unlock();
      }
   }
}
