package com.pnfsoftware.jeb.util.collect;

import java.util.List;

public interface IMultiSegmentMap {
   void clear();

   boolean isEmpty();

   int size();

   ISegment add(ISegment var1);

   ISegment getFirstSegmentContaining(Comparable var1);

   List getSegmentsContaining(Comparable var1);
}
