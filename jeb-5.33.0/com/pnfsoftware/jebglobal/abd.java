package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class abd implements abf {
   @Override
   public String getName() {
      return "Android ART (dex2oat)";
   }

   @Override
   public int getPropertyId() {
      return 3;
   }
}
