package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IUnitLock {
   long getDefaultBlockTimeoutMs();

   boolean isLocked();

   boolean isLockedByCurrentThread();

   void verifyLocked();

   ACLock a();

   ACLock a(long var1);
}
