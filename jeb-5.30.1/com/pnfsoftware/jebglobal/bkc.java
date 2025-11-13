package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bkc extends bkb {
   @SerId(1)
   private Map gO = new HashMap();

   @SerCustomInitPostGraph
   private void oW() {
      if (this.gO == null) {
         this.gO = new HashMap();

         for (bjr var2 : this.Uv) {
            String var3 = Strings.ff("%d-%d-%s", var2.getShortyIndex(), var2.getReturnTypeIndex(), Arrays.toString(var2.getParameterTypeIndexes()));
            this.gO.put(var3, var2);
         }
      }
   }

   public bkc(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      super("protos", var1, var2);
   }

   public synchronized bjr q(int var1, int var2, int[] var3) {
      if (var3 == null) {
         var3 = new int[0];
      }

      String var4 = Strings.ff("%d-%d-%s", var1, var2, Arrays.toString(var3));
      bjr var5 = (bjr)this.gO.get(var4);
      if (var5 != null) {
         return var5;
      } else {
         int var6 = this.Uv.size();
         var5 = new bjr(this.xK, var6, var1, var2, var3);
         if (this.q(var5.generate(false), var5)) {
            this.gO.put(var4, var5);
         }

         return var5;
      }
   }
}
