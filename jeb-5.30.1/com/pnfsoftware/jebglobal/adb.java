package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class adb implements acy {
   public static final String q = "unknown (Linux)";

   @Override
   public String getName() {
      return "unknown (Linux)";
   }

   @Override
   public int getPropertyId() {
      return 2;
   }
}
