package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotation;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public final class bea implements IDexAnnotationItem {
   @SerId(1)
   private int pC;
   @SerId(2)
   private bdy A;

   public bea(int var1, bdy var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public int getVisibility() {
      return this.pC;
   }

   @Override
   public String formatVisibility() {
      switch (this.pC) {
         case 0:
            return "build";
         case 1:
            return "runtime";
         case 2:
            return "system";
         default:
            return "unknown";
      }
   }

   @Override
   public IDexAnnotation getAnnotation() {
      return this.A;
   }

   @Override
   public String toString() {
      return this.formatVisibility() + ":" + this.getAnnotation();
   }
}
