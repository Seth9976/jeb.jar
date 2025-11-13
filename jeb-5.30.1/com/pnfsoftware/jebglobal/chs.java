package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class chs {
   private static final ILogger q = GlobalLog.getLogger(chs.class);
   private String RF;
   private ConcurrentHashMap xK = new ConcurrentHashMap();

   public static synchronized chs q(IDGlobalContext var0, String var1) {
      return (chs)var0.getData(var1, var1x -> new chs(var1));
   }

   public static void q(IDGlobalContext var0) {
      for (String var2 : var0.getDataKeys()) {
         if (var0.getData(var2) instanceof chs var4) {
            var4.xK.values().forEach(var0x -> var0x.gP());
         }
      }
   }

   public static void RF(IDGlobalContext var0, String var1) {
      for (String var3 : var0.getDataKeys()) {
         if (var0.getData(var3) instanceof chs var5) {
            chs.eo var6 = (chs.eo)var5.xK.get(var1);
            if (var6 != null) {
               var6.gP();
            }
         }
      }
   }

   public chs(String var1) {
      this.RF = var1;
   }

   public String q() {
      return this.RF;
   }

   public chs.eo q(String var1) {
      return (chs.eo)this.xK.get(var1);
   }

   public chs.eo q(String var1, boolean var2) {
      chs.eo var3 = (chs.eo)this.xK.get(var1);
      if (var3 == null && var2) {
         var3 = new chs.eo(var1);
         chs.eo var4 = (chs.eo)this.xK.putIfAbsent(var1, var3);
         if (var4 != null) {
            var3 = var4;
         }
      }

      return var3;
   }

   public static class eo {
      private String Dw;
      Map q = new ConcurrentHashMap();
      Map RF = new ConcurrentHashMap();
      volatile boolean xK;

      public eo(String var1) {
         this.Dw = var1;
      }

      public String q() {
         return this.Dw;
      }

      public void q(ICodeCoordinates var1, IDExpression var2) {
         this.q.put(var1, var2 != null ? var2.toString() : "<global>");
      }

      public void RF(ICodeCoordinates var1, IDExpression var2) {
         this.RF.put(var1, var2 != null ? var2.toString() : "<global>");
      }

      public void q(ICodeCoordinates var1, IDExpression var2, Exception var3) {
         this.RF.put(var1, var2 != null ? var2.toString() : "<global>");
      }

      public Map RF() {
         return Collections.unmodifiableMap(this.q);
      }

      public Map xK() {
         return Collections.unmodifiableMap(this.RF);
      }

      public int Dw() {
         return this.q.size();
      }

      public int Uv() {
         return this.RF.size();
      }

      public int oW() {
         return this.q.size() + this.RF.size();
      }

      public void q(boolean var1) {
         this.xK = var1;
      }

      boolean gO() {
         if (!this.xK) {
            return false;
         } else {
            int var1 = this.oW();
            if (var1 < 5) {
               return true;
            } else {
               int var2 = 100 * this.Dw() / var1;
               return var2 >= 20;
            }
         }
      }

      public void nf() {
         this.gP();
         this.xK = false;
      }

      public void gP() {
         this.q.clear();
         this.RF.clear();
      }
   }
}
