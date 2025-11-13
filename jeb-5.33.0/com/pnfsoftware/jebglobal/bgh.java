package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bgh extends bgg {
   @SerId(1)
   private Map sY = new HashMap();

   @SerCustomInitPostGraph
   private void UT() {
      if (this.sY == null) {
         this.sY = new HashMap();

         for (bfw var2 : this.UT) {
            String var3 = Strings.ff("%d-%d-%s", var2.getShortyIndex(), var2.getReturnTypeIndex(), Arrays.toString(var2.getParameterTypeIndexes()));
            this.sY.put(var3, var2);
         }
      }
   }

   public bgh(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      super("protos", var1, var2);
   }

   public synchronized bfw pC(int var1, int var2, int[] var3) {
      if (var3 == null) {
         var3 = new int[0];
      }

      String var4 = Strings.ff("%d-%d-%s", var1, var2, Arrays.toString(var3));
      bfw var5 = (bfw)this.sY.get(var4);
      if (var5 != null) {
         return var5;
      } else {
         int var6 = this.UT.size();
         var5 = new bfw(this.kS, var6, var1, var2, var3);
         if (this.pC(var5.generate(false), var5)) {
            this.sY.put(var4, var5);
         }

         return var5;
      }
   }
}
