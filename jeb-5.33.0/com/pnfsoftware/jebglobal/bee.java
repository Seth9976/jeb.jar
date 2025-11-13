package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForMethod;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.List;

@Ser
public final class bee implements IDexAnnotationForMethod {
   @SerId(1)
   private int pC;
   @SerId(2)
   private beb A;

   public bee(int var1, beb var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public int getMethodIndex() {
      return this.pC;
   }

   @Override
   public List getAnnotationItems() {
      return this.A == null ? Collections.emptyList() : this.A.pC();
   }
}
