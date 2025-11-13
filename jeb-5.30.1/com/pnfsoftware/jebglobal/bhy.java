package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForMethod;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.List;

@Ser
public final class bhy implements IDexAnnotationForMethod {
   @SerId(1)
   private int q;
   @SerId(2)
   private bhv RF;

   public bhy(int var1, bhv var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public int getMethodIndex() {
      return this.q;
   }

   public bhv q() {
      return this.RF == null ? bhv.q : this.RF;
   }

   @Override
   public List getAnnotationItems() {
      return this.RF == null ? Collections.emptyList() : this.RF.RF();
   }
}
