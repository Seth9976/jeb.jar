package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForParameter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public final class bhz implements IDexAnnotationForParameter {
   @SerId(1)
   private int q;
   @SerId(2)
   private bhv[] RF;

   public bhz(int var1, bhv[] var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public int getMethodIndex() {
      return this.q;
   }

   @Override
   public List getAnnotationItemSets() {
      ArrayList var1 = new ArrayList();

      for (bhv var5 : this.RF) {
         if (var5 == null) {
            var1.add(Collections.emptyList());
         } else {
            var1.add(var5.RF());
         }
      }

      return Collections.unmodifiableList(var1);
   }
}
