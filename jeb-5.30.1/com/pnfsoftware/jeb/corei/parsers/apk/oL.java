package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.android.IJniEndpoint;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class oL implements IJniEndpoint {
   @SerId(1)
   private String q;

   public oL(String var1) {
      this.q = var1;
   }

   @Override
   public boolean isStatic() {
      return true;
   }

   @Override
   public String getMethodName() {
      return this.q;
   }

   @Override
   public IUnit getUnit() {
      return null;
   }

   @Override
   public Long getAddress() {
      return null;
   }
}
