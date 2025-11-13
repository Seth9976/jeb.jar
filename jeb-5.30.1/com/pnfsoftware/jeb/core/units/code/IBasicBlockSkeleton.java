package com.pnfsoftware.jeb.core.units.code;

import java.util.List;

public interface IBasicBlockSkeleton extends Iterable {
   long getFirstAddress();

   long getLastAddress();

   long getEndAddress();

   List getInsntructions();

   List getDstOffsets();
}
