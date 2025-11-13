package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassDataItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Deprecated
@Ser
public class axk extends axi implements INativeClassDataItem {
   @SerId(1)
   private long q;

   public axk(long var1) {
      super(Integer.MIN_VALUE, null);
      this.q = var1;
   }

   @Override
   public long getMemoryAddress() {
      return this.q;
   }

   @Override
   public String toString() {
      return Strings.ff("ClassData@%X", this.getMemoryAddress());
   }
}
