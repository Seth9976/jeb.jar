package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class add implements acz {
   public static final String q = "Microsoft Visual Studio";

   @Override
   public String getName() {
      return "Microsoft Visual Studio";
   }

   @Override
   public int getPropertyId() {
      return 6;
   }
}
