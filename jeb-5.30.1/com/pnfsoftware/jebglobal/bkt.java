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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class bkt {
   private static final ILogger q = GlobalLog.getLogger(bkt.class);
   private final IDexUnit RF;
   private final long xK;
   private final long Dw;
   private boolean Uv;
   private final Map oW = new ConcurrentHashMap();
   private final Map gO = new ConcurrentHashMap();
   private volatile List nf;
   private volatile int gP;
   private Thread za;
   private final Set lm = new ConcurrentHashSet();
   private volatile int zz;
   private volatile Thread JY;
   private final Set HF = new ConcurrentHashSet();

   public bkt(IDexUnit var1, long var2, long var4) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
         this.xK = var2;
         this.Dw = var4;
      }
   }

   public bkt(IDexUnit var1) {
      this(var1, -1L, -1L);
   }

   public boolean q() {
      boolean var1 = true;
      if (this.za != null && this.za.isAlive()) {
         this.za.interrupt();

         try {
            this.za.join(1000L);
         } catch (InterruptedException var3) {
         }

         var1 &= !this.za.isAlive();
      }

      if (this.JY != null && this.JY.isAlive()) {
         this.JY.interrupt();

         try {
            this.JY.join(1000L);
         } catch (InterruptedException var2) {
         }

         var1 &= !this.JY.isAlive();
      }

      return var1;
   }

   public Collection q(int var1) {
      this.Uv();
      bkt.nI var2 = (bkt.nI)this.oW.get(var1);
      return (Collection)(var2 == null ? Collections.emptyList() : Collections.unmodifiableSet(var2.RF));
   }

   public Collection RF(int var1) {
      this.Uv();
      bkt.nI var2 = (bkt.nI)this.oW.get(var1);
      return (Collection)(var2 == null ? Collections.emptyList() : Collections.unmodifiableSet(var2.xK));
   }

   public List RF() {
      this.Uv();
      if (this.nf == null) {
         synchronized (this) {
            if (this.nf == null) {
               List var2 = new bkt.CU().RF();
               this.nf = Collections.unmodifiableList(var2);
            }
         }
      }

      return this.nf;
   }

   private bkt.nI Dw(int var1) {
      bkt.nI var2 = (bkt.nI)this.oW.get(var1);
      if (var2 == null) {
         var2 = new bkt.nI(var1);
         this.oW.put(var1, var2);
      }

      return var2;
   }

   private void q(int var1, long var2, int var4) {
      long var5 = var2 | (long)var1 << 32;
      this.gO.put(var5, var4);
   }

   public int q(IDexMethod var1, long var2) {
      return this.q(var1.getIndex(), var2);
   }

   public int q(int var1, long var2) {
      this.Uv();
      long var4 = var2 | (long)var1 << 32;
      return (Integer)this.gO.getOrDefault(var4, -1);
   }

   public int xK() {
      return this.gP;
   }

   private void Uv() {
      if (this.gP == 0 || this.gP == 2 && this.Uv) {
         synchronized (this) {
            boolean var2 = true;
            if (this.za != null) {
               if (this.za.isAlive()) {
                  var2 = false;
               } else {
                  this.za = null;
               }
            }

            if (var2) {
               if (this.gP == 2 && this.Uv) {
                  this.gP = 0;
               }

               if (this.gP == 0) {
                  this.Uv = false;
                  this.gP = 1;
                  if (this.xK < 0L) {
                     this.oW();
                  } else {
                     this.lm.clear();
                     this.za = ThreadUtil.start("jeb-dex-cg-builder", this::oW);
                  }
               }
            }
         }
      }

      if (this.gP == 1 && this.xK > 0L) {
         Thread var1 = this.za;
         if (var1 != null && var1.isAlive() && Thread.currentThread() != var1 && this.lm.add(Thread.currentThread().getId())) {
            try {
               var1.join(this.xK);
            } catch (InterruptedException var4) {
               throw new RuntimeException(var4);
            }
         }
      }
   }

   private void oW() {
      long var1 = System.currentTimeMillis();

      try {
         this.gO();
      } catch (InterruptionException var5) {
         q.debug("Interruption: %s", var5.getMessage());
         return;
      }

      this.gP = 2;
      long var3 = System.currentTimeMillis() - var1;
      q.debug("cg: build time: %s", TimeFormatter.formatExecutionTime(var3));
   }

   private void gO() {
      bkz var1 = ((com.pnfsoftware.jeb.corei.parsers.dex.bK)this.RF).TX();
      CacheMap var2 = new CacheMap(2000);
      int var3 = 0;
      int var4 = 0;

      for (IDexMethod var6 : this.RF.getMethods()) {
         IDexMethodData var7 = var6.getData();
         if (var7 != null) {
            IDexCodeItem var8 = var7.getCodeItem();
            if (var8 != null) {
               if (Thread.interrupted()) {
                  throw new InterruptionException(S.L("Interrupted thread (most likely due to dex unit disposal)"));
               }

               bkt.nI var9 = this.Dw(var6.getIndex());

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
                        IDexMethod var14 = this.RF.getMethod(var12);
                        String var15 = var14.getSignature(false);
                        if (!var15.startsWith("[")) {
                           var3++;
                           int var16 = -1;
                           if (var13 != 112 && var13 != 118) {
                              bkz.eo var17 = (bkz.eo)var2.get(var14.getIndex());
                              if (var17 == null) {
                                 boolean var18 = var13 == 113 || var13 == 119;
                                 var17 = var1.q(!var18, var14, true, true, false);
                                 var2.put(var14.getIndex(), var17);
                              }

                              if (var17.q() && var17.RF().size() == 1) {
                                 var16 = (Integer)var17.RF().iterator().next();
                              }
                           } else {
                              var16 = var12;
                           }

                           if (var16 < 0) {
                              var4++;
                           } else {
                              var9.RF.add(var16);
                              this.Dw(var16).xK.add(var9.q);
                              this.q(var9.q, var11.getOffset(), var16);
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

   public Boolean xK(int var1) {
      this.Uv();
      this.nf();
      bkt.nI var2 = (bkt.nI)this.oW.get(var1);
      return var2 == null ? null : var2.Dw;
   }

   public int Dw() {
      return this.zz;
   }

   private void nf() {
      if (this.zz == 0) {
         synchronized (this) {
            boolean var2 = true;
            if (this.JY != null) {
               if (this.JY.isAlive()) {
                  var2 = false;
               } else {
                  this.JY = null;
               }
            }

            if (var2 && this.zz == 0) {
               this.zz = 1;
               if (this.Dw < 0L) {
                  this.gP();
               } else {
                  this.HF.clear();
                  this.JY = ThreadUtil.start("jeb-dex-cg-recdet-builder", this::gP);
               }
            }
         }
      }

      if (this.zz == 1 && this.Dw > 0L) {
         Thread var1 = this.JY;
         if (var1 != null && var1.isAlive() && Thread.currentThread() != var1 && this.HF.add(Thread.currentThread().getId())) {
            try {
               var1.join(this.Dw);
            } catch (InterruptedException var4) {
               throw new RuntimeException(var4);
            }
         }
      }
   }

   private void gP() {
      long var1 = System.currentTimeMillis();
      bkt.ej var3 = new bkt.ej();

      try {
         var3.q();
      } catch (InterruptionException var6) {
         q.debug("Interruption: %s", var6.getMessage());
         return;
      }

      this.zz = 2;
      long var4 = System.currentTimeMillis() - var1;
      q.debug("cg: rec-det time: %s", TimeFormatter.formatExecutionTime(var4));
   }

   private class CU implements Iterator {
      List q = new ArrayList();
      Map RF = new HashMap();
      boolean xK;

      CU() {
         for (bkt.nI var3 : bkt.this.oW.values()) {
            bkt.eo var4 = new bkt.eo(var3.q, var3.RF.size(), new HashSet(var3.xK));
            this.q.add(var4);
            this.RF.put(var4.q, var4);
         }

         Collections.sort(this.q);
      }

      @Override
      public boolean hasNext() {
         return !this.q.isEmpty();
      }

      public Integer q() {
         if (Thread.interrupted()) {
            throw new InterruptionException(S.L("Interrupted thread (most likely due to dex unit disposal)"));
         } else if (this.q.isEmpty()) {
            throw new NoSuchElementException();
         } else {
            if (this.xK && ((bkt.eo)this.q.get(0)).RF != 0) {
               Collections.sort(this.q);
               this.xK = false;
            }

            bkt.eo var1 = (bkt.eo)this.q.remove(0);

            for (int var3 : var1.xK) {
               bkt.eo var4 = (bkt.eo)this.RF.get(var3);
               if (var4 != null) {
                  var4.RF--;
               }
            }

            this.xK = true;
            return var1.q;
         }
      }

      @Override
      public void remove() {
         throw new RuntimeException();
      }

      public List RF() {
         long var1 = System.currentTimeMillis();
         ArrayList var3 = new ArrayList(this.q.size());

         while (this.hasNext()) {
            int var4 = this.q();
            if (bkt.this.RF.getMethod(var4).isInternal()) {
               var3.add(var4);
            }
         }

         long var6 = System.currentTimeMillis() - var1;
         Object[] var10000 = new Object[]{TimeFormatter.formatExecutionTime(var6)};
         return var3;
      }
   }

   private class ej {
      private Set RF = new HashSet();
      private List xK = new ArrayList();
      private Map Dw = new HashMap();

      void q() {
         while (bkt.this.gP != 2) {
            try {
               Thread.sleep(50L);
            } catch (InterruptedException var6) {
               throw new InterruptionException(S.L("Interrupted thread (most likely due to dex unit disposal)"), var6);
            }
         }

         ArrayList var1 = new ArrayList(bkt.this.RF());

         while (!var1.isEmpty()) {
            int var2 = (Integer)var1.remove(var1.size() - 1);
            this.q(var2);
         }

         this.RF.clear();

         for (Integer var3 : this.xK) {
            this.q(var3, var3);
         }

         for (bkt.nI var10 : bkt.this.oW.values()) {
            var10.Dw = var10.RF.contains(var10.q);
         }

         for (Set var11 : this.Dw.values()) {
            if (var11.size() >= 2) {
               for (Integer var5 : var11) {
                  ((bkt.nI)bkt.this.oW.get(var5)).Dw = true;
               }
            }
         }
      }

      private void q(int var1) {
         if (this.RF.add(var1)) {
            for (Integer var3 : ((bkt.nI)bkt.this.oW.get(var1)).RF) {
               this.q(var3);
            }

            this.xK.add(0, var1);
         }
      }

      private void q(int var1, int var2) {
         if (this.RF.add(var1)) {
            Object var3 = (Set)this.Dw.get(var2);
            if (var3 == null) {
               var3 = new HashSet();
               this.Dw.put(var2, var3);
            }

            var3.add(var1);

            for (Integer var5 : ((bkt.nI)bkt.this.oW.get(var1)).xK) {
               this.q(var5, var2);
            }
         }
      }
   }

   private static class eo implements Comparable {
      int q;
      int RF;
      Set xK;

      eo(int var1, int var2, Set var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      public int q(bkt.eo var1) {
         int var2 = this.RF - var1.RF;
         return var2 != 0 ? var2 : -(this.xK.size() - var1.xK.size());
      }
   }

   private static class nI {
      int q;
      Set RF = new ConcurrentHashSet();
      Set xK = new ConcurrentHashSet();
      volatile Boolean Dw;

      nI(int var1) {
         this.q = var1;
      }
   }
}
