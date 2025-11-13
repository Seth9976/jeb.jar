package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.android.IJniEndpoint;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class DH implements IJniEndpoint {
   @SerId(1)
   private IUnit pC;
   @SerId(2)
   private Long A;

   public DH(IUnit var1, Long var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public boolean isStatic() {
      return false;
   }

   @Override
   public String getMethodName() {
      return null;
   }

   @Override
   public IUnit getUnit() {
      return this.pC;
   }

   @Override
   public Long getAddress() {
      return this.A;
   }
}
