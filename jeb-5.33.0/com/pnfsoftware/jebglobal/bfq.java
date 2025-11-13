package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bfq extends bgg {
   @SerId(1)
   private Map sY = new HashMap();

   @SerCustomInitPostGraph
   private void UT() {
      if (this.sY == null) {
         this.sY = new HashMap();

         for (bfs var2 : this.UT) {
            String var3 = var2.kS + "";
            this.sY.put(var3, var2);
         }
      }
   }

   public bfq(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      super("classes", var1, var2);
   }

   public synchronized bfs pC(int var1, int var2, int var3, int[] var4, int var5, bec var6, bfp var7, beg[] var8, int var9) {
      String var10 = var1 + "";
      bfs var11 = (bfs)this.sY.get(var10);
      if (var11 != null) {
         this.kS.logWarn(true, S.L("Duplicate class: %s"), var11.getSignature(false));
         return var11;
      } else {
         int var12 = this.UT.size();
         var11 = new bfs(this.kS, var12, var1, var2, var3, var4, var5, var6, var7, var8, var9);
         if (this.pC(var11.getSignature(false), var11)) {
            this.sY.put(var10, var11);
         }

         return var11;
      }
   }

   public bfs pC(String var1) {
      bfs var2 = (bfs)this.kS(var1);
      if (var2 != null) {
         return var2;
      } else {
         bfy var3 = this.kS.ld().sY(var1);
         return var3 == null ? null : var3.A();
      }
   }

   public synchronized bfs A(String var1) {
      if (var1 == null) {
         return null;
      } else {
         bfs var2 = null;

         for (bfs var4 : this.UT) {
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
