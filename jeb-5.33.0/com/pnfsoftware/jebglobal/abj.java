package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class abj implements abg {
   @Override
   public String getName() {
      return "unknown (Linux)";
   }

   @Override
   public int getPropertyId() {
      return 2;
   }
}
