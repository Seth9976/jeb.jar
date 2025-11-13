package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.function.BiFunction;
import java.util.function.Predicate;

@Ser
public interface ISegmentMap extends NavigableMap {
   ISegment add(ISegment var1);

   ISegment addAndMerge(ISegment var1, BiFunction var2);

   ISegment put(Comparable var1, ISegment var2);

   @Override
   void putAll(Map var1);

   ISegment getSegmentContaining(Comparable var1);

   ISegment getSegmentAfter(Comparable var1);

   ISegment getSegmentBefore(Comparable var1);

   SortedMap getOverlappingSegmentsMap(Comparable var1, boolean var2, Comparable var3, boolean var4);

   SortedMap getOverlappingSegmentsMap(Comparable var1, boolean var2, Comparable var3, boolean var4, Predicate var5);

   boolean isEmptyRange(Comparable var1, Comparable var2);

   SortedMap contiguousSubMap(Comparable var1, Comparable var2, ISegmentFactory var3);

   List fillGaps(Comparable var1, Comparable var2, ISegmentFactory var3);

   List generateGapItems(Comparable var1, boolean var2, Comparable var3, boolean var4, ISegmentFactory var5, boolean var6);

   List generateGaps(Comparable var1, boolean var2, Comparable var3, boolean var4, ISegmentGapVerifier var5);

   List generateGaps(Comparable var1, boolean var2, Comparable var3, boolean var4);
}
