package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IEnumerationElement;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bbl implements IEnumerationElement {
   @SerId(1)
   String q;
   @SerId(2)
   int RF;

   bbl(String var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
         this.RF = var2;
      }
   }

   @Override
   public String getName() {
      return this.q;
   }

   @Override
   public int getValue() {
      return this.RF;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%d", this.getName(), this.getValue());
   }
}
