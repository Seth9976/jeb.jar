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
public abstract class PA implements aan {
   private static final ILogger nf = GlobalLog.getLogger(PA.class);
   protected String q = "unk";
   protected final aae RF;
   protected final aaf xK;
   protected final IVirtualMemory Dw;
   protected final IProcessor Uv;
   private int gP = 1;
   private int za = 1;
   protected List oW;
   protected List gO = new ArrayList();
   private int lm;
   private long zz;
   private long JY;
   private long HF;

   public PA(aae var1) {
      this.RF = var1;
      this.xK = var1.za();
      this.Dw = var1.getMemory();
      this.Uv = var1.getProcessor();
      this.oW = var1.getAnalysisRanges().asList();
   }

   public long q() {
      return this.HF;
   }

   public void q(int var1) {
      if (var1 < 1) {
         throw new IllegalArgumentException();
      } else {
         this.gP = var1;
      }
   }

   public void RF(int var1) {
      if (var1 < 1) {
         throw new IllegalArgumentException();
      } else {
         this.za = var1;
      }
   }

   public aad RF() {
      while (this.lm < this.oW.size()) {
         Couple var1 = (Couple)this.oW.get(this.lm);
         aad var2 = this.RF((Long)var1.getFirst(), (Long)var1.getSecond());
         if (var2 != null) {
            return var2;
         }

         this.lm++;
         this.zz = 0L;
      }

      return null;
   }

   public void xK() {
      this.lm = 0;
      this.zz = 0L;
   }

   public void q(long var1, long var3) {
      this.gO.add(new Couple(var1, var3));
   }

   protected boolean Dw() {
      return false;
   }

   protected long q(long var1) {
      INativeContinuousItem var3 = this.xK.getNextItem(this.zz);
      return var3 == null ? var1 : Math.min(var1, (Long)var3.getBegin());
   }

   protected aad RF(long var1, long var3) {
      if (this.zz == 0L) {
         this.zz = var1;
      }

      while (this.zz < var3) {
         while (true) {
            INativeContinuousItem var5 = this.xK.getItemOver(this.zz);
            if (var5 == null) {
               this.JY = this.q(var3);
               if (!this.Dw()) {
                  Couple var6 = this.Dw(this.zz);
                  if (var6 != null) {
                     this.zz = (Long)var6.getSecond();
                     break;
                  }

                  if (this.za > 1) {
                     this.zz = Longs.divUnsigned(this.zz + this.za - 1L, this.za) * this.za;
                  }

                  int var7 = 0;
                  int var8 = this.gP;

                  while (this.zz < this.JY) {
                     this.RF.q(S.L("Analyzing: %Xh..."), this.zz);
                     long var9 = System.currentTimeMillis();
                     long var11 = this.zz;
                     List var13 = this.RF(this.zz, this.JY, true);
                     this.HF = this.HF + (System.currentTimeMillis() - var9);
                     if (var13 != null && var13.size() != 0) {
                        aad var14 = new aad(this.zz, var13);
                        if (this.zz == var11) {
                           this.zz = this.zz + this.gP;
                        }

                        return var14;
                     }

                     if (++var7 % 50000 == 0) {
                        var8 *= 2;
                     }

                     if (this.zz == var11) {
                        this.zz += var8;
                     }
                  }
                  break;
               }
            } else {
               this.zz = (Long)var5.getEnd();
            }
         }
      }

      return null;
   }

   private Couple Dw(long var1) {
      for (Couple var4 : this.gO) {
         if (var1 >= (Long)var4.getFirst() && var1 < (Long)var4.getSecond()) {
            return var4;
         }
      }

      return null;
   }

   public long Uv() {
      return this.zz;
   }

   public long oW() {
      return this.JY;
   }

   public void RF(long var1) {
      this.zz = var1;
   }

   public void xK(long var1) {
      this.JY = var1;
   }

   protected Object q(long var1, long var3, boolean var5) {
      List var6 = this.RF(var1, var3, var5);
      return var6 != null && var6.size() != 0 ? var6.get(0) : null;
   }

   protected abstract List RF(long var1, long var3, boolean var5);

   @Override
   public void q(aaw.eo var1, Object var2) {
   }

   public List gO() {
      return this.gO;
   }

   public void q(List var1) {
      this.gO = var1;
   }

   public String nf() {
      return this.q;
   }
}
