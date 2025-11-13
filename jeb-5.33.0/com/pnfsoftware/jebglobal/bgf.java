package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bgf extends bgg {
   @SerId(1)
   private Map sY = new HashMap();

   @SerCustomInitPostGraph
   private void UT() {
      if (this.sY == null) {
         this.sY = new HashMap();

         for (bfu var2 : this.UT) {
            String var3 = Strings.ff("%d-%d-%d", var2.getClassTypeIndex(), var2.getPrototypeIndex(), var2.getNameIndex());
            this.sY.put(var3, var2);
         }
      }
   }

   public bgf(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      super("methods", var1, var2);
   }

   public synchronized bfu pC(int var1, int var2, int var3) {
      String var4 = Strings.ff("%d-%d-%d", var1, var2, var3);
      bfu var5 = (bfu)this.sY.get(var4);
      if (var5 != null) {
         return var5;
      } else {
         int var6 = this.UT.size();
         var5 = new bfu(this.kS, var6, var1, var2, var3);
         if (this.pC(var5.getSignature(false), var5)) {
            this.sY.put(var4, var5);
         }

         return var5;
      }
   }

   public synchronized void pC(int var1, bgd var2) {
      ((bfu)this.pC(var1)).pC(var2);
   }

   public bfu pC(String var1) {
      int var2 = var1.indexOf(43);
      if (var2 >= 0) {
         var1 = var1.substring(0, var2);
      }

      bfu var3 = (bfu)this.kS(var1);
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
               bfy var8 = this.kS.ld().sY(var4);
               if (var8 == null) {
                  return null;
               } else {
                  bfs var9 = var8.A();
                  if (var9 == null) {
                     return null;
                  } else {
                     for (com.pnfsoftware.jeb.corei.parsers.dex.HE var11 : this.kS.ld().pC(var9).getChildren()) {
                        com.pnfsoftware.jeb.corei.parsers.dex.Sv var12 = var11.kS();
                        if (var12 instanceof bfu && ((bfu)var12).getName(true).equals(var6) && ((bfu)var12).UT().generate(true).equals(var7)) {
                           return (bfu)var12;
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
