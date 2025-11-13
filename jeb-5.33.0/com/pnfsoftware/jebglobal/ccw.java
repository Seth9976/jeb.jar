package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ccw {
   private static final ILogger pC = GlobalLog.getLogger(ccw.class);
   private String A;
   private ConcurrentHashMap kS = new ConcurrentHashMap();

   public static synchronized ccw pC(IDGlobalContext var0, String var1) {
      return (ccw)var0.getData(var1, var1x -> new ccw(var1));
   }

   public static void pC(IDGlobalContext var0) {
      for (String var2 : var0.getDataKeys()) {
         if (var0.getData(var2) instanceof ccw var4) {
            var4.kS.values().forEach(var0x -> var0x.E());
         }
      }
   }

   public static void A(IDGlobalContext var0, String var1) {
      for (String var3 : var0.getDataKeys()) {
         if (var0.getData(var3) instanceof ccw var5) {
            ccw.Av var6 = (ccw.Av)var5.kS.get(var1);
            if (var6 != null) {
               var6.E();
            }
         }
      }
   }

   public ccw(String var1) {
      this.A = var1;
   }

   public ccw.Av pC(String var1) {
      return (ccw.Av)this.kS.get(var1);
   }

   public ccw.Av pC(String var1, boolean var2) {
      ccw.Av var3 = (ccw.Av)this.kS.get(var1);
      if (var3 == null && var2) {
         var3 = new ccw.Av(var1);
         ccw.Av var4 = (ccw.Av)this.kS.putIfAbsent(var1, var3);
         if (var4 != null) {
            var3 = var4;
         }
      }

      return var3;
   }

   public static class Av {
      private String wS;
      Map pC = new ConcurrentHashMap();
      Map A = new ConcurrentHashMap();
      volatile boolean kS;

      public Av(String var1) {
         this.wS = var1;
      }

      public void pC(ICodeCoordinates var1, IDExpression var2) {
         this.pC.put(var1, var2 != null ? var2.toString() : "<global>");
      }

      public void A(ICodeCoordinates var1, IDExpression var2) {
         this.A.put(var1, var2 != null ? var2.toString() : "<global>");
      }

      public void pC(ICodeCoordinates var1, IDExpression var2, Exception var3) {
         this.A.put(var1, var2 != null ? var2.toString() : "<global>");
      }

      public Map pC() {
         return Collections.unmodifiableMap(this.A);
      }

      public int A() {
         return this.pC.size();
      }

      public int kS() {
         return this.A.size();
      }

      public int wS() {
         return this.pC.size() + this.A.size();
      }

      public void pC(boolean var1) {
         this.kS = var1;
      }

      boolean UT() {
         if (!this.kS) {
            return false;
         } else {
            int var1 = this.wS();
            if (var1 < 5) {
               return true;
            } else {
               int var2 = 100 * this.A() / var1;
               return var2 >= 20;
            }
         }
      }

      public void E() {
         this.pC.clear();
         this.A.clear();
      }
   }
}
