package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bga extends bgg {
   @SerId(1)
   private Map sY = new HashMap();

   @SerCustomInitPostGraph
   private void UT() {
      if (this.sY == null) {
         this.sY = new HashMap();

         for (bft var2 : this.UT) {
            String var3 = Strings.ff("%d-%d-%d", var2.getClassTypeIndex(), var2.getFieldTypeIndex(), var2.getNameIndex());
            this.sY.put(var3, var2);
         }
      }
   }

   public bga(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      super("fields", var1, var2);
   }

   public synchronized bft pC(int var1, int var2, int var3) {
      String var4 = Strings.ff("%d-%d-%d", var1, var2, var3);
      bft var5 = (bft)this.sY.get(var4);
      if (var5 != null) {
         return var5;
      } else {
         int var6 = this.UT.size();
         var5 = new bft(this.kS, var6, var1, var2, var3);
         if (this.pC(var5.getSignature(false), var5)) {
            this.sY.put(var4, var5);
         }

         return var5;
      }
   }

   public synchronized void pC(int var1, bfz var2) {
      ((bft)this.pC(var1)).pC(var2);
   }

   public bft pC(String var1) {
      bft var2 = (bft)this.kS(var1);
      if (var2 != null) {
         return var2;
      } else {
         int var3 = var1.indexOf("->");
         if (var3 < 0) {
            return null;
         } else {
            String var4 = var1.substring(0, var3);
            String var5 = var1.substring(var3 + 2);
            String var6 = null;
            var3 = var5.indexOf(":");
            if (var3 >= 0) {
               var6 = var5.substring(var3 + 1);
               var5 = var5.substring(0, var3);
            }

            bfy var7 = this.kS.ld().sY(var4);
            if (var7 == null) {
               return null;
            } else {
               bfs var8 = var7.A();
               if (var8 == null) {
                  return null;
               } else {
                  for (com.pnfsoftware.jeb.corei.parsers.dex.HE var10 : this.kS.ld().pC(var8).getChildren()) {
                     com.pnfsoftware.jeb.corei.parsers.dex.Sv var11 = var10.kS();
                     if (var11 instanceof bft
                        && ((bft)var11).getName(true).equals(var5)
                        && (var6 == null || ((bft)var11).getFieldTypeSignature(true).equals(var6) || ((bft)var11).getFieldTypeSignature(false).equals(var6))) {
                        return (bft)var11;
                     }
                  }

                  return null;
               }
            }
         }
      }
   }
}
