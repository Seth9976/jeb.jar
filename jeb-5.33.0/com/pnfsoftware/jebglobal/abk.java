package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class abk implements abh {
   @Override
   public String getName() {
      return "unknown (Windows)";
   }

   @Override
   public int getPropertyId() {
      return 5;
   }
}
