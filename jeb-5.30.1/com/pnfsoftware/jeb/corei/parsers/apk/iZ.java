package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.android.IJniEndpoint;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class iZ implements IJniEndpoint {
   @SerId(1)
   private IUnit q;
   @SerId(2)
   private Long RF;

   public iZ(IUnit var1, Long var2) {
      this.q = var1;
      this.RF = var2;
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
      return this.q;
   }

   @Override
   public Long getAddress() {
      return this.RF;
   }
}
