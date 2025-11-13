package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bjl extends bkb {
   @SerId(1)
   private Map gO = new HashMap();

   @SerCustomInitPostGraph
   private void oW() {
      if (this.gO == null) {
         this.gO = new HashMap();

         for (bjn var2 : this.Uv) {
            String var3 = var2.xK + "";
            this.gO.put(var3, var2);
         }
      }
   }

   public bjl(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      super("classes", var1, var2);
   }

   public synchronized bjn q(int var1, int var2, int var3, int[] var4, int var5, bhw var6, bjk var7, bia[] var8, int var9) {
      String var10 = var1 + "";
      bjn var11 = (bjn)this.gO.get(var10);
      if (var11 != null) {
         this.xK.logWarn(true, S.L("Duplicate class: %s"), var11.getSignature(false));
         return var11;
      } else {
         int var12 = this.Uv.size();
         var11 = new bjn(this.xK, var12, var1, var2, var3, var4, var5, var6, var7, var8, var9);
         if (this.q(var11.getSignature(false), var11)) {
            this.gO.put(var10, var11);
         }

         return var11;
      }
   }

   public bjn q(String var1) {
      bjn var2 = (bjn)this.xK(var1);
      if (var2 != null) {
         return var2;
      } else {
         bjt var3 = this.xK.io().gO(var1);
         return var3 == null ? null : var3.xK();
      }
   }

   public synchronized bjn RF(String var1) {
      if (var1 == null) {
         return null;
      } else {
         bjn var2 = null;

         for (bjn var4 : this.Uv) {
            if (var1.equals(var4.getName(true)) || var1.equals(var4.getName(false))) {
               if (var2 != null) {
                  return null;
               }

               var2 = var4;
            }
         }

         return var2;
      }
   }
}
