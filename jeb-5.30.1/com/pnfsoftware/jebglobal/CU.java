package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CU implements Runnable {
   private static final ILogger q = GlobalLog.getLogger(CU.class);
   private Map RF = new HashMap();
   private int xK = Licensing.user_count;
   private static final long Dw = 3000L;
   private static final long Uv = 120000L;

   public CU() {
      q.info(S.L("Note: %d client(s) may simultaneously connect to this controller instance"), this.xK);
   }

   @Override
   public void run() {
      while (true) {
         this.RF();

         try {
            Thread.sleep(3000L);
         } catch (InterruptedException var1) {
            return;
         }
      }
   }

   private synchronized int RF() {
      long var1 = System.currentTimeMillis();
      HashSet var3 = new HashSet();

      for (long var5 : this.RF.keySet()) {
         com.pnfsoftware.jebglobal.eo var7 = (com.pnfsoftware.jebglobal.eo)this.RF.get(var5);
         if (var1 - var7.Uv() >= 120000L) {
            var3.add(var5);
         }
      }

      int var9 = 0;

      for (long var6 : var3) {
         com.pnfsoftware.jebglobal.eo var8 = (com.pnfsoftware.jebglobal.eo)this.RF.remove(var6);
         if (var8 != null) {
            var9++;
         }
      }

      return var9;
   }

   public synchronized boolean q(long var1, long var3, String var5, int var6) {
      com.pnfsoftware.jebglobal.eo var7 = (com.pnfsoftware.jebglobal.eo)this.RF.get(var1);
      if (var7 == null) {
         if (this.RF.size() >= this.xK) {
            q.info(S.L("%s:%d: Refusing client session %d: all seats are being used"), var5, var6, var1);
            return false;
         }

         var7 = new com.pnfsoftware.jebglobal.eo(var1);
         this.RF.put(var1, var7);
         q.info(S.L("%s:%d: Registering client session %d"), var5, var6, var1);
      }

      var7.RF(var3, var5, var6);
      return true;
   }

   public synchronized boolean q(long var1, long var3) {
      com.pnfsoftware.jebglobal.eo var5 = (com.pnfsoftware.jebglobal.eo)this.RF.get(var1);
      if (var5 != null) {
         var5.q(var3);
         if (var5.xK() == 0 && this.RF.remove(var1) != null) {
            q.info(S.L("Unregistering client session %d"), var1);
            return true;
         }
      }

      return false;
   }

   public synchronized CU.eo q() {
      CU.eo var1 = new CU.eo(this.xK, this.RF.size());
      int var2 = 0;

      for (com.pnfsoftware.jebglobal.eo var4 : this.RF.values()) {
         var1.RF[var2] = new CU.eo.eo(var4.Dw(), var4.Uv(), var4.xK());
         int var5 = 0;

         for (com.pnfsoftware.jebglobal.eo.eo var7 : var4.RF()) {
            var1.RF[var2].xK[var5] = new CU.eo.CU(var7.RF(), var7.xK());
            var5++;
         }

         var2++;
      }

      return var1;
   }

   public static class eo {
      public int q;
      public com.pnfsoftware.jebglobal.CU.eo.eo[] RF;

      public eo(int var1, int var2) {
         this.q = var1;
         this.RF = new com.pnfsoftware.jebglobal.CU.eo.eo[var2];
      }

      public static class CU {
         public String q;
         public int RF;

         public CU(String var1, int var2) {
            this.q = var1;
            this.RF = var2;
         }
      }

      public static class eo {
         public long q;
         public long RF;
         public com.pnfsoftware.jebglobal.CU.eo.CU[] xK;

         public eo(long var1, long var3, int var5) {
            this.q = var1;
            this.RF = var3;
            this.xK = new com.pnfsoftware.jebglobal.CU.eo.CU[var5];
         }
      }
   }
}
