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
public class rn implements IUnitLock {
   private static final ILogger pC = GlobalLog.getLogger(rn.class);
   @SerId(1)
   private SerReentrantLock A = new SerReentrantLock();
   @SerId(2)
   private long kS;

   public rn() {
      this(0L);
   }

   public rn(long var1) {
      this.kS = var1;
   }

   @Override
   public long getDefaultBlockTimeoutMs() {
      return this.kS;
   }

   @Override
   public boolean isLocked() {
      return this.A.isLocked();
   }

   @Override
   public boolean isLockedByCurrentThread() {
      return this.A.isHeldByCurrentThread();
   }

   @Override
   public ACLock a() {
      return this.a(this.kS);
   }

   @Override
   public ACLock a(long var1) {
      return new rn.Av(var1);
   }

   @Override
   public void verifyLocked() {
      if (!this.A.isHeldByCurrentThread()) {
         JebRuntimeException var1 = new JebRuntimeException("The model lock should be owned by the current thread!");
         JebCoreService.notifySilentExceptionToClient(var1);
      }
   }

   @SerDisabled
   class Av extends AbstractAutoClosingLock {
      public Av(long var2) {
         super(rn.this.A);
         if (var2 == 0L) {
            if (!rn.this.A.tryLock()) {
               throw new UnitLockedException();
            }
         } else if (var2 < 0L) {
            rn.this.A.lock();
         } else {
            try {
               if (!rn.this.A.tryLock(var2, TimeUnit.MILLISECONDS)) {
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
