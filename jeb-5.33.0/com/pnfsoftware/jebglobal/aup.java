package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassDataItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Deprecated
@Ser
public class aup extends aun implements INativeClassDataItem {
   @SerId(1)
   private long pC;

   @Override
   public long getMemoryAddress() {
      return this.pC;
   }

   @Override
   public String toString() {
      return Strings.ff("ClassData@%X", this.getMemoryAddress());
   }
}
