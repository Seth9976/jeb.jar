package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Of extends jA {
   private List oW;

   public Of() {
      super(384, 8);
      this.oW = new ArrayList();
   }

   public Of(jA var1, uL var2, boolean var3) throws IOException {
      super(var1, 0);
      int var4 = this.gP(var2);
      int var5 = var4 / 4;
      this.oW = ArrayUtil.asList(zR.q(var2, var5));
   }

   public int q() {
      return this.oW.size();
   }

   public int q(int var1) {
      return var1 >= this.oW.size() ? 0 : (Integer)this.oW.get(var1);
   }

   public void q(int var1, int var2) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         while (var1 >= this.oW.size()) {
            this.oW.add(0);
         }

         this.oW.set(var1, var2);
      }
   }

   @Override
   public void q(pK var1) {
      super.q(var1);

      for (int var3 : this.oW) {
         var1.q.writeIntLE(var3);
      }

      this.RF(var1);
   }
}
