package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class adc implements acz {
   public static final String q = "unknown (Windows)";

   @Override
   public String getName() {
      return "unknown (Windows)";
   }

   @Override
   public int getPropertyId() {
      return 5;
   }
}
