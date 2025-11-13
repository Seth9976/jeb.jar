package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationElement;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public final class bdz implements IDexAnnotationElement {
   @SerId(1)
   private int pC;
   @SerId(2)
   private beg A;

   bdz(int var1, beg var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public int getNameIndex() {
      return this.pC;
   }

   @Override
   public IDexValue getValue() {
      return this.A;
   }
}
