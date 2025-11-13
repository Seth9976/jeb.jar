package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bka extends bkb {
   @SerId(1)
   private Map gO = new HashMap();

   @SerCustomInitPostGraph
   private void oW() {
      if (this.gO == null) {
         this.gO = new HashMap();

         for (bjp var2 : this.Uv) {
            String var3 = Strings.ff("%d-%d-%d", var2.getClassTypeIndex(), var2.getPrototypeIndex(), var2.getNameIndex());
            this.gO.put(var3, var2);
         }
      }
   }

   public bka(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      super("methods", var1, var2);
   }

   public synchronized bjp q(int var1, int var2, int var3) {
      String var4 = Strings.ff("%d-%d-%d", var1, var2, var3);
      bjp var5 = (bjp)this.gO.get(var4);
      if (var5 != null) {
         return var5;
      } else {
         int var6 = this.Uv.size();
         var5 = new bjp(this.xK, var6, var1, var2, var3);
         if (this.q(var5.getSignature(false), var5)) {
            this.gO.put(var4, var5);
         }

         return var5;
      }
   }

   public synchronized void q(int var1, bjy var2) {
      ((bjp)this.q(var1)).q(var2);
   }

   public bjp q(String var1) {
      int var2 = var1.indexOf(43);
      if (var2 >= 0) {
         var1 = var1.substring(0, var2);
      }

      bjp var3 = (bjp)this.xK(var1);
      if (var3 != null) {
         return var3;
      } else {
         var2 = var1.indexOf("->");
         if (var2 < 0) {
            return null;
         } else {
            String var4 = var1.substring(0, var2);
            String var5 = var1.substring(var2 + 2);
            var2 = var5.indexOf("(");
            if (var2 < 0) {
               return null;
            } else {
               String var6 = var5.substring(0, var2);
               String var7 = var5.substring(var2);
               bjt var8 = this.xK.io().gO(var4);
               if (var8 == null) {
                  return null;
               } else {
                  bjn var9 = var8.xK();
                  if (var9 == null) {
                     return null;
                  } else {
                     for (com.pnfsoftware.jeb.corei.parsers.dex.oL var11 : this.xK.io().q(var9).getChildren()) {
                        com.pnfsoftware.jeb.corei.parsers.dex.CU var12 = var11.xK();
                        if (var12 instanceof bjp && ((bjp)var12).getName(true).equals(var6) && ((bjp)var12).Uv().generate(true).equals(var7)) {
                           return (bjp)var12;
                        }
                     }

                     return null;
                  }
               }
            }
         }
      }
   }
}
