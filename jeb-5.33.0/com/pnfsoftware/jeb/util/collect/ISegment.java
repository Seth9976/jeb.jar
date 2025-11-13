package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ISegment {
   Comparable getBegin();

   Comparable getEnd();
}
