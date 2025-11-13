package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.InterruptionException;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCodeItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.util.collect.CacheMap;
import com.pnfsoftware.jeb.util.collect.ConcurrentHashSet;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class bgx {
   private static final ILogger pC = GlobalLog.getLogger(bgx.class);
   private final IDexUnit A;
   private final long kS;
   private final long wS;
   private boolean UT;
   private final Map E = new ConcurrentHashMap();
   private final Map sY = new ConcurrentHashMap();
   private volatile List ys;
   private volatile int ld;
   private Thread gp;
   private final Set oT = new ConcurrentHashSet();
   private volatile int fI;
   private volatile Thread WR;
   private final Set NS = new ConcurrentHashSet();

   public bgx(IDexUnit var1, long var2, long var4) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
         this.kS = var2;
         this.wS = var4;
      }
   }

   public boolean pC() {
      boolean var1 = true;
      if (this.gp != null && this.gp.isAlive()) {
         this.gp.interrupt();

         try {
            this.gp.join(1000L);
         } catch (InterruptedException var3) {
         }

         var1 &= !this.gp.isAlive();
      }

      if (this.WR != null && this.WR.isAlive()) {
         this.WR.interrupt();

         try {
            this.WR.join(1000L);
         } catch (InterruptedException var2) {
         }

         var1 &= !this.WR.isAlive();
      }

      return var1;
   }

   public List A() {
      this.kS();
      if (this.ys == null) {
         synchronized (this) {
            if (this.ys == null) {
               List var2 = new bgx.Sv().A();
               this.ys = Collections.unmodifiableList(var2);
            }
         }
      }

      return this.ys;
   }

   private bgx.K A(int var1) {
      bgx.K var2 = (bgx.K)this.E.get(var1);
      if (var2 == null) {
         var2 = new bgx.K(var1);
         this.E.put(var1, var2);
      }

      return var2;
   }

   private void pC(int var1, long var2, int var4) {
      long var5 = var2 | (long)var1 << 32;
      this.sY.put(var5, var4);
   }

   public int pC(IDexMethod var1, long var2) {
      return this.pC(var1.getIndex(), var2);
   }

   public int pC(int var1, long var2) {
      this.kS();
      long var4 = var2 | (long)var1 << 32;
      return (Integer)this.sY.getOrDefault(var4, -1);
   }

   private void kS() {
      if (this.ld == 0 || this.ld == 2 && this.UT) {
         synchronized (this) {
            boolean var2 = true;
            if (this.gp != null) {
               if (this.gp.isAlive()) {
                  var2 = false;
               } else {
                  this.gp = null;
               }
            }

            if (var2) {
               if (this.ld == 2 && this.UT) {
                  this.ld = 0;
               }

               if (this.ld == 0) {
                  this.UT = false;
                  this.ld = 1;
                  if (this.kS < 0L) {
                     this.wS();
                  } else {
                     this.oT.clear();
                     this.gp = ThreadUtil.start("jeb-dex-cg-builder", this::wS);
                  }
               }
            }
         }
      }

      if (this.ld == 1 && this.kS > 0L) {
         Thread var1 = this.gp;
         if (var1 != null && var1.isAlive() && Thread.currentThread() != var1 && this.oT.add(Thread.currentThread().getId())) {
            try {
               var1.join(this.kS);
            } catch (InterruptedException var4) {
               throw new RuntimeException(var4);
            }
         }
      }
   }

   private void wS() {
      long var1 = System.currentTimeMillis();

      try {
         this.UT();
      } catch (InterruptionException var5) {
         pC.debug("Interruption: %s", var5.getMessage());
         return;
      }

      this.ld = 2;
      long var3 = System.currentTimeMillis() - var1;
      pC.debug("cg: build time: %s", TimeFormatter.formatExecutionTime(var3));
   }

   private void UT() {
      bhd var1 = ((com.pnfsoftware.jeb.corei.parsers.dex.vi)this.A).hK();
      CacheMap var2 = new CacheMap(2000);
      int var3 = 0;
      int var4 = 0;

      for (IDexMethod var6 : this.A.getMethods()) {
         IDexMethodData var7 = var6.getData();
         if (var7 != null) {
            IDexCodeItem var8 = var7.getCodeItem();
            if (var8 != null) {
               if (Thread.interrupted()) {
                  throw new InterruptionException(S.L("Interrupted thread (most likely due to dex unit disposal)"));
               }

               bgx.K var9 = this.A(var6.getIndex());

               for (IDalvikInstruction var11 : var8.getInstructions()) {
                  int var13 = var11.getOpcode();
                  switch (var13) {
                     case 110:
                     case 111:
                     case 112:
                     case 113:
                     case 114:
                     case 116:
                     case 117:
                     case 118:
                     case 119:
                     case 120:
                        int var12 = (int)var11.getParameter(0).getValue();
                        IDexMethod var14 = this.A.getMethod(var12);
                        String var15 = var14.getSignature(false);
                        if (!var15.startsWith("[")) {
                           var3++;
                           int var16 = -1;
                           if (var13 != 112 && var13 != 118) {
                              bhd.Av var17 = (bhd.Av)var2.get(var14.getIndex());
                              if (var17 == null) {
                                 boolean var18 = var13 == 113 || var13 == 119;
                                 var17 = var1.pC(!var18, var14, true, true, false);
                                 var2.put(var14.getIndex(), var17);
                              }

                              if (var17.pC() && var17.A().size() == 1) {
                                 var16 = (Integer)var17.A().iterator().next();
                              }
                           } else {
                              var16 = var12;
                           }

                           if (var16 < 0) {
                              var4++;
                           } else {
                              var9.A.add(var16);
                              this.A(var16).kS.add(var9.pC);
                              this.pC(var9.pC, var11.getOffset(), var16);
                           }
                        }
                     case 115:
                  }
               }
            }
         }
      }

      Object[] var10000 = new Object[]{var4, var3};
   }

   public Boolean pC(int var1) {
      this.kS();
      this.E();
      bgx.K var2 = (bgx.K)this.E.get(var1);
      return var2 == null ? null : var2.wS;
   }

   private void E() {
      if (this.fI == 0) {
         synchronized (this) {
            boolean var2 = true;
            if (this.WR != null) {
               if (this.WR.isAlive()) {
                  var2 = false;
               } else {
                  this.WR = null;
               }
            }

            if (var2 && this.fI == 0) {
               this.fI = 1;
               if (this.wS < 0L) {
                  this.sY();
               } else {
                  this.NS.clear();
                  this.WR = ThreadUtil.start("jeb-dex-cg-recdet-builder", this::sY);
               }
            }
         }
      }

      if (this.fI == 1 && this.wS > 0L) {
         Thread var1 = this.WR;
         if (var1 != null && var1.isAlive() && Thread.currentThread() != var1 && this.NS.add(Thread.currentThread().getId())) {
            try {
               var1.join(this.wS);
            } catch (InterruptedException var4) {
               throw new RuntimeException(var4);
            }
         }
      }
   }

   private void sY() {
      long var1 = System.currentTimeMillis();
      bgx.Ws var3 = new bgx.Ws();

      try {
         var3.pC();
      } catch (InterruptionException var6) {
         pC.debug("Interruption: %s", var6.getMessage());
         return;
      }

      this.fI = 2;
      long var4 = System.currentTimeMillis() - var1;
      pC.debug("cg: rec-det time: %s", TimeFormatter.formatExecutionTime(var4));
   }

   private static class Av implements Comparable {
      int pC;
      int A;
      Set kS;

      Av(int var1, int var2, Set var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      public int pC(bgx.Av var1) {
         int var2 = this.A - var1.A;
         return var2 != 0 ? var2 : -(this.kS.size() - var1.kS.size());
      }
   }

   private static class K {
      int pC;
      Set A = new ConcurrentHashSet();
      Set kS = new ConcurrentHashSet();
      volatile Boolean wS;

      K(int var1) {
         this.pC = var1;
      }
   }

   private class Sv implements Iterator {
      List pC = new ArrayList();
      Map A = new HashMap();
      boolean kS;

      Sv() {
         for (bgx.K var3 : bgx.this.E.values()) {
            bgx.Av var4 = new bgx.Av(var3.pC, var3.A.size(), new HashSet(var3.kS));
            this.pC.add(var4);
            this.A.put(var4.pC, var4);
         }

         Collections.sort(this.pC);
      }

      @Override
      public boolean hasNext() {
         return !this.pC.isEmpty();
      }

      public Integer pC() {
         if (Thread.interrupted()) {
            throw new InterruptionException(S.L("Interrupted thread (most likely due to dex unit disposal)"));
         } else if (this.pC.isEmpty()) {
            throw new NoSuchElementException();
         } else {
            if (this.kS && ((bgx.Av)this.pC.get(0)).A != 0) {
               Collections.sort(this.pC);
               this.kS = false;
            }

            bgx.Av var1 = (bgx.Av)this.pC.remove(0);

            for (int var3 : var1.kS) {
               bgx.Av var4 = (bgx.Av)this.A.get(var3);
               if (var4 != null) {
                  var4.A--;
               }
            }

            this.kS = true;
            return var1.pC;
         }
      }

      @Override
      public void remove() {
         throw new RuntimeException();
      }

      public List A() {
         long var1 = System.currentTimeMillis();
         ArrayList var3 = new ArrayList(this.pC.size());

         while (this.hasNext()) {
            int var4 = this.pC();
            if (bgx.this.A.getMethod(var4).isInternal()) {
               var3.add(var4);
            }
         }

         long var6 = System.currentTimeMillis() - var1;
         Object[] var10000 = new Object[]{TimeFormatter.formatExecutionTime(var6)};
         return var3;
      }
   }

   private class Ws {
      private Set A = new HashSet();
      private List kS = new ArrayList();
      private Map wS = new HashMap();

      void pC() {
         while (bgx.this.ld != 2) {
            try {
               Thread.sleep(50L);
            } catch (InterruptedException var6) {
               throw new InterruptionException(S.L("Interrupted thread (most likely due to dex unit disposal)"), var6);
            }
         }

         ArrayList var1 = new ArrayList(bgx.this.A());

         while (!var1.isEmpty()) {
            int var2 = (Integer)var1.remove(var1.size() - 1);
            this.pC(var2);
         }

         this.A.clear();

         for (Integer var3 : this.kS) {
            this.pC(var3, var3);
         }

         for (bgx.K var10 : bgx.this.E.values()) {
            var10.wS = var10.A.contains(var10.pC);
         }

         for (Set var11 : this.wS.values()) {
            if (var11.size() >= 2) {
               for (Integer var5 : var11) {
                  ((bgx.K)bgx.this.E.get(var5)).wS = true;
               }
            }
         }
      }

      private void pC(int var1) {
         if (this.A.add(var1)) {
            for (Integer var3 : ((bgx.K)bgx.this.E.get(var1)).A) {
               this.pC(var3);
            }

            this.kS.add(0, var1);
         }
      }

      private void pC(int var1, int var2) {
         if (this.A.add(var1)) {
            Object var3 = (Set)this.wS.get(var2);
            if (var3 == null) {
               var3 = new HashSet();
               this.wS.put(var2, var3);
            }

            var3.add(var1);

            for (Integer var5 : ((bgx.K)bgx.this.E.get(var1)).kS) {
               this.pC(var5, var2);
            }
         }
      }
   }
}
