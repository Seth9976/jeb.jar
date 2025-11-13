package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class PassthroughUnitLock implements IUnitLock {
   private static final PassthroughUnitLock instance = new PassthroughUnitLock();

   public static PassthroughUnitLock getInstance() {
      return instance;
   }

   private PassthroughUnitLock() {
   }

   @Override
   public long getDefaultBlockTimeoutMs() {
      return 0L;
   }

   @Override
   public boolean isLocked() {
      return false;
   }

   @Override
   public boolean isLockedByCurrentThread() {
      return false;
   }

   @Override
   public void verifyLocked() {
   }

   @Override
   public ACLock a() {
      return this.a(0L);
   }

   @Override
   public ACLock a(long var1) {
      return new PassthroughUnitLock$1(this);
   }
}
