package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IEnumerationElement;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ayl implements IEnumerationElement {
   @SerId(1)
   String pC;
   @SerId(2)
   int A;

   ayl(String var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var1;
         this.A = var2;
      }
   }

   @Override
   public String getName() {
      return this.pC;
   }

   @Override
   public int getValue() {
      return this.A;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%d", this.getName(), this.getValue());
   }
}
