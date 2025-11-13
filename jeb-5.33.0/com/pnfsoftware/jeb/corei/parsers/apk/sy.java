package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.android.IJniEndpoint;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class sy implements IJniEndpoint {
   @SerId(1)
   private String pC;

   public sy(String var1) {
      this.pC = var1;
   }

   @Override
   public boolean isStatic() {
      return true;
   }

   @Override
   public String getMethodName() {
      return this.pC;
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
