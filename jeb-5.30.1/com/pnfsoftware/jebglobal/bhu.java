package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotation;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public final class bhu implements IDexAnnotationItem {
   @SerId(1)
   private int q;
   @SerId(2)
   private bhs RF;

   public bhu(int var1, bhs var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public int getVisibility() {
      return this.q;
   }

   @Override
   public String formatVisibility() {
      switch (this.q) {
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
      return this.RF;
   }

   @Override
   public String toString() {
      return this.formatVisibility() + ":" + this.getAnnotation();
   }
}
