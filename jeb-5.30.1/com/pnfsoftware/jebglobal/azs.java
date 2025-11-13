package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Deprecated
@Ser
public class azs extends azo {
   @SerId(1)
   private final String q;

   public azs(String var1) {
      this.q = var1;
   }

   @Override
   public String getType() {
      return "TargetName";
   }

   public String q() {
      return this.q;
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
