package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class abl implements abh {
   @Override
   public String getName() {
      return "Microsoft Visual Studio";
   }

   @Override
   public int getPropertyId() {
      return 6;
   }
}
