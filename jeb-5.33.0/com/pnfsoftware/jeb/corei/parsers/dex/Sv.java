package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class Sv implements IDexItem {
   @SerId(1)
   protected vi pC;

   public Sv(vi var1) {
      this.pC = var1;
   }

   @Override
   public IDexUnit getDex() {
      return this.pC;
   }

   @Override
   public long getItemId() {
      return this.pC.pC(this);
   }

   @Override
   public String getAddress() {
      return this.getAddress(true);
   }

   @Override
   public final String getName() {
      return this.getName(true);
   }

   @Override
   public final String getSignature() {
      return this.getSignature(true);
   }

   @Override
   public boolean isRenamed() {
      String var1 = this.getName(true);
      return var1 != null && !var1.equals(this.getName(false));
   }

   protected boolean pC() {
      return true;
   }
}
