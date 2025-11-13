package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Deprecated
@Ser
public class awv extends awr {
   @SerId(1)
   private final String pC;

   @Override
   public String getType() {
      return "TargetName";
   }

   public String pC() {
      return this.pC;
   }

   @Override
   public boolean importTo(INativeItem var1) {
      return false;
   }

   @Override
   public boolean isPrintable() {
      return true;
   }
}
