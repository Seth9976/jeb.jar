package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public interface IVariable extends ISegment, Comparable {
   int getBitsize();

   String getName();

   int getId();

   Collection getIds();

   int getBitFromId(int var1);

   int getIdFromBit(int var1);
}
