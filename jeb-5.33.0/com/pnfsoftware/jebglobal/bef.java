package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForParameter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public final class bef implements IDexAnnotationForParameter {
   @SerId(1)
   private int pC;
   @SerId(2)
   private beb[] A;

   public bef(int var1, beb[] var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public int getMethodIndex() {
      return this.pC;
   }

   @Override
   public List getAnnotationItemSets() {
      ArrayList var1 = new ArrayList();

      for (beb var5 : this.A) {
         if (var5 == null) {
            var1.add(Collections.emptyList());
         } else {
            var1.add(var5.pC());
         }
      }

      return Collections.unmodifiableList(var1);
   }
}
