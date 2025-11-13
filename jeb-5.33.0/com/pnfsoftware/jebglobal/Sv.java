package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Sv implements Runnable {
   private static final ILogger pC = GlobalLog.getLogger(Sv.class);
   private Map A = new HashMap();
   private int kS = Licensing.user_count;

   public Sv() {
      pC.info(S.L("Note: %d client(s) may simultaneously connect to this controller instance"), this.kS);
   }

   @Override
   public void run() {
      while (true) {
         this.A();

         try {
            Thread.sleep(3000L);
         } catch (InterruptedException var1) {
            return;
         }
      }
   }

   private synchronized int A() {
      long var1 = System.currentTimeMillis();
      HashSet var3 = new HashSet();

      for (long var5 : this.A.keySet()) {
         com.pnfsoftware.jebglobal.Av var7 = (com.pnfsoftware.jebglobal.Av)this.A.get(var5);
         if (var1 - var7.wS() >= 120000L) {
            var3.add(var5);
         }
      }

      int var9 = 0;

      for (long var6 : var3) {
         com.pnfsoftware.jebglobal.Av var8 = (com.pnfsoftware.jebglobal.Av)this.A.remove(var6);
         if (var8 != null) {
            var9++;
         }
      }

      return var9;
   }

   public synchronized boolean pC(long var1, long var3, String var5, int var6, String var7, String var8, String var9) {
      com.pnfsoftware.jebglobal.Av var10 = (com.pnfsoftware.jebglobal.Av)this.A.get(var1);
      if (var10 == null) {
         if (this.A.size() >= this.kS) {
            pC.info(S.L("%s:%d: Refusing client session %d: all seats are being used"), var5, var6, var1);
            return false;
         }

         var10 = new com.pnfsoftware.jebglobal.Av(var1);
         this.A.put(var1, var10);
         pC.info(S.L("%s:%d: Registering client session %d"), var5, var6, var1);
      }

      var10.pC(var7);
      var10.A(var8);
      var10.kS(var9);
      var10.A(var3, var5, var6);
      return true;
   }

   public synchronized boolean pC(long var1, long var3) {
      com.pnfsoftware.jebglobal.Av var5 = (com.pnfsoftware.jebglobal.Av)this.A.get(var1);
      if (var5 != null) {
         var5.pC(var3);
         if (var5.A() == 0 && this.A.remove(var1) != null) {
            pC.info(S.L("Unregistering client session %d"), var1);
            return true;
         }
      }

      return false;
   }

   public synchronized Sv.Av pC() {
      Sv.Av var1 = new Sv.Av(this.kS, this.A.size());
      int var2 = 0;

      for (com.pnfsoftware.jebglobal.Av var4 : this.A.values()) {
         var1.A[var2] = new Sv.Av.Av(var4.kS(), var4.wS(), var4.A(), var4.E(), var4.sY(), var4.ys());
         int var5 = 0;

         for (com.pnfsoftware.jebglobal.Av.Av var7 : var4.pC()) {
            var1.A[var2].kS[var5] = new Sv.Av.Sv(var7.pC(), var7.A());
            var5++;
         }

         var2++;
      }

      return var1;
   }

   public static class Av {
      public int pC;
      public com.pnfsoftware.jebglobal.Sv.Av.Av[] A;

      public Av(int var1, int var2) {
         this.pC = var1;
         this.A = new com.pnfsoftware.jebglobal.Sv.Av.Av[var2];
      }

      public static class Av {
         public long pC;
         public long A;
         public com.pnfsoftware.jebglobal.Sv.Av.Sv[] kS;
         public String wS;
         public String UT;
         public String E;

         public Av(long var1, long var3, int var5, String var6, String var7, String var8) {
            this.pC = var1;
            this.A = var3;
            this.kS = new com.pnfsoftware.jebglobal.Sv.Av.Sv[var5];
            this.wS = var6;
            this.UT = var7;
            this.E = var8;
         }
      }

      public static class Sv {
         public String pC;
         public int A;

         public Sv(String var1, int var2) {
            this.pC = var1;
            this.A = var2;
         }
      }
   }
}
