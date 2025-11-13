package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IFlowInformation {
   boolean isBroken();

   boolean isBrokenUnknown();

   boolean isBrokenKnown();

   List getTargets();

   boolean mustComputeFallThrough();

   boolean noFallThrough();

   int getDelaySlotCount();
}
