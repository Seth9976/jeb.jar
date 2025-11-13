package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ProcessorVariant {
   @SerId(1)
   String name;

   public ProcessorVariant(String var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.name = var1;
      }
   }

   public String getName() {
      return this.name;
   }
}
