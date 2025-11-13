package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.IUnitLock;
import com.pnfsoftware.jeb.core.units.UnitLockedException;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.concurrent.AbstractAutoClosingLock;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.objects.SerReentrantLock;
import java.util.concurrent.TimeUnit;

@Ser
public class be implements IUnitLock {
   private static final ILogger q = GlobalLog.getLogger(be.class);
   @SerId(1)
   private SerReentrantLock RF = new SerReentrantLock();
   @SerId(2)
   private long xK;

   public be() {
      this(0L);
   }

   public be(long var1) {
      this.xK = var1;
   }

   public void q(long var1) {
      this.xK = var1;
   }

   @Override
   public long getDefaultBlockTimeoutMs() {
      return this.xK;
   }

   @Override
   public boolean isLocked() {
      return this.RF.isLocked();
   }

   @Override
   public boolean isLockedByCurrentThread() {
      return this.RF.isHeldByCurrentThread();
   }

   @Override
   public ACLock a() {
      return this.a(this.xK);
   }

   @Override
   public ACLock a(long var1) {
      return new be.eo(var1);
   }

   @Override
   public void verifyLocked() {
      if (!this.RF.isHeldByCurrentThread()) {
         JebRuntimeException var1 = new JebRuntimeException("The model lock should be owned by the current thread!");
         JebCoreService.notifySilentExceptionToClient(var1);
      }
   }

   @SerDisabled
   class eo extends AbstractAutoClosingLock {
      public eo(long var2) {
         super(be.this.RF);
         if (var2 == 0L) {
            if (!be.this.RF.tryLock()) {
               throw new UnitLockedException();
            }
         } else if (var2 < 0L) {
            be.this.RF.lock();
         } else {
            try {
               if (!be.this.RF.tryLock(var2, TimeUnit.MILLISECONDS)) {
                  throw new UnitLockedException();
               }
            } catch (InterruptedException var5) {
               throw new UnitLockedException(var5);
            }
         }

         this.locked.set(true);
      }
   }
}
