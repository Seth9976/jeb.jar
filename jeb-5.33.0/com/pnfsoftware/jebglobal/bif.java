package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bif {
   @SerId(1)
   private String pC;
   @SerId(2)
   private Object A;
}
