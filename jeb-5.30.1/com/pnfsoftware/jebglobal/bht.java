package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationElement;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public final class bht implements IDexAnnotationElement {
   @SerId(1)
   private int q;
   @SerId(2)
   private bia RF;

   bht(int var1, bia var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public int getNameIndex() {
      return this.q;
   }

   @Override
   public IDexValue getValue() {
      return this.RF;
   }
}
