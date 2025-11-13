package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class acv implements acx {
   public static final String q = "Android ART (dex2oat)";

   @Override
   public String getName() {
      return "Android ART (dex2oat)";
   }

   @Override
   public int getPropertyId() {
      return 3;
   }
}
