package com.pnfsoftware.jeb.util.concurrent;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ISafeLock {
   ACLock ro();

   ACLock rw();
}
