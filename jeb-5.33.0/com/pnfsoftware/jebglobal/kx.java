package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.List;

@SerDisabled
public abstract class kx implements Sj {
   private static final ILogger ys = GlobalLog.getLogger(kx.class);
   protected String pC = "unk";
   protected final a A;
   protected final Tw kS;
   protected final IVirtualMemory wS;
   protected final IProcessor UT;
   private int ld = 1;
   private int gp = 1;
   protected List E;
   protected List sY = new ArrayList();
   private int oT;
   private long fI;
   private long WR;
   private long NS;

   public kx(a var1) {
      this.A = var1;
      this.kS = var1.ys();
      this.wS = var1.getMemory();
      this.UT = var1.getProcessor();
      this.E = var1.getAnalysisRanges().asList();
   }

   public long pC() {
      return this.NS;
   }

   public void pC(int var1) {
      if (var1 < 1) {
         throw new IllegalArgumentException();
      } else {
         this.ld = var1;
      }
   }

   public void A(int var1) {
      if (var1 < 1) {
         throw new IllegalArgumentException();
      } else {
         this.gp = var1;
      }
   }

   public Qb A() {
      while (this.oT < this.E.size()) {
         Couple var1 = (Couple)this.E.get(this.oT);
         Qb var2 = this.A((Long)var1.getFirst(), (Long)var1.getSecond());
         if (var2 != null) {
            return var2;
         }

         this.oT++;
         this.fI = 0L;
      }

      return null;
   }

   public void kS() {
      this.oT = 0;
      this.fI = 0L;
   }

   public void pC(long var1, long var3) {
      this.sY.add(new Couple(var1, var3));
   }

   protected boolean wS() {
      return false;
   }

   protected long pC(long var1) {
      INativeContinuousItem var3 = this.kS.getNextItem(this.fI);
      return var3 == null ? var1 : Math.min(var1, (Long)var3.getBegin());
   }

   protected Qb A(long var1, long var3) {
      if (this.fI == 0L) {
         this.fI = var1;
      }

      while (this.fI < var3) {
         while (true) {
            INativeContinuousItem var5 = this.kS.getItemOver(this.fI);
            if (var5 == null) {
               this.WR = this.pC(var3);
               if (!this.wS()) {
                  Couple var6 = this.kS(this.fI);
                  if (var6 != null) {
                     this.fI = (Long)var6.getSecond();
                     break;
                  }

                  if (this.gp > 1) {
                     this.fI = Longs.divUnsigned(this.fI + this.gp - 1L, this.gp) * this.gp;
                  }

                  int var7 = 0;
                  int var8 = this.ld;

                  while (this.fI < this.WR) {
                     this.A.pC(S.L("Analyzing: %Xh..."), this.fI);
                     long var9 = System.currentTimeMillis();
                     long var11 = this.fI;
                     List var13 = this.A(this.fI, this.WR, true);
                     this.NS = this.NS + (System.currentTimeMillis() - var9);
                     if (var13 != null && var13.size() != 0) {
                        Qb var14 = new Qb(this.fI, var13);
                        if (this.fI == var11) {
                           this.fI = this.fI + this.ld;
                        }

                        return var14;
                     }

                     if (++var7 % 50000 == 0) {
                        var8 *= 2;
                     }

                     if (this.fI == var11) {
                        this.fI += var8;
                     }
                  }
                  break;
               }
            } else {
               this.fI = (Long)var5.getEnd();
            }
         }
      }

      return null;
   }

   private Couple kS(long var1) {
      for (Couple var4 : this.sY) {
         if (var1 >= (Long)var4.getFirst() && var1 < (Long)var4.getSecond()) {
            return var4;
         }
      }

      return null;
   }

   public long UT() {
      return this.fI;
   }

   public long E() {
      return this.WR;
   }

   public void A(long var1) {
      this.fI = var1;
   }

   protected Object pC(long var1, long var3, boolean var5) {
      List var6 = this.A(var1, var3, var5);
      return var6 != null && var6.size() != 0 ? var6.get(0) : null;
   }

   protected abstract List A(long var1, long var3, boolean var5);

   @Override
   public void pC(Vr.Av var1, Object var2) {
   }

   public List sY() {
      return this.sY;
   }

   public void pC(List var1) {
      this.sY = var1;
   }

   public String ys() {
      return this.pC;
   }
}
