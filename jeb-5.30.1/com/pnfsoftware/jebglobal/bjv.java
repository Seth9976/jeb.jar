package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bjv extends bkb {
   @SerId(1)
   private Map gO = new HashMap();

   @SerCustomInitPostGraph
   private void oW() {
      if (this.gO == null) {
         this.gO = new HashMap();

         for (bjo var2 : this.Uv) {
            String var3 = Strings.ff("%d-%d-%d", var2.getClassTypeIndex(), var2.getFieldTypeIndex(), var2.getNameIndex());
            this.gO.put(var3, var2);
         }
      }
   }

   public bjv(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      super("fields", var1, var2);
   }

   public synchronized bjo q(int var1, int var2, int var3) {
      String var4 = Strings.ff("%d-%d-%d", var1, var2, var3);
      bjo var5 = (bjo)this.gO.get(var4);
      if (var5 != null) {
         return var5;
      } else {
         int var6 = this.Uv.size();
         var5 = new bjo(this.xK, var6, var1, var2, var3);
         if (this.q(var5.getSignature(false), var5)) {
            this.gO.put(var4, var5);
         }

         return var5;
      }
   }

   public synchronized void q(int var1, bju var2) {
      ((bjo)this.q(var1)).q(var2);
   }

   public bjo q(String var1) {
      bjo var2 = (bjo)this.xK(var1);
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

            bjt var7 = this.xK.io().gO(var4);
            if (var7 == null) {
               return null;
            } else {
               bjn var8 = var7.xK();
               if (var8 == null) {
                  return null;
               } else {
                  for (com.pnfsoftware.jeb.corei.parsers.dex.oL var10 : this.xK.io().q(var8).getChildren()) {
                     com.pnfsoftware.jeb.corei.parsers.dex.CU var11 = var10.xK();
                     if (var11 instanceof bjo
                        && ((bjo)var11).getName(true).equals(var5)
                        && (var6 == null || ((bjo)var11).getFieldTypeSignature(true).equals(var6) || ((bjo)var11).getFieldTypeSignature(false).equals(var6))) {
                        return (bjo)var11;
                     }
                  }

                  return null;
               }
            }
         }
      }
   }
}
