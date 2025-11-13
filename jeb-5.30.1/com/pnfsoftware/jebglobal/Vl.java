package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.List;

@SerDisabled
class Vl {
   private static final ILogger q = GlobalLog.getLogger(Vl.class);
   private tx RF;
   private Vl.nI xK;
   private boolean Dw;

   public boolean q() {
      return this.Dw;
   }

   public void q(tx var1) {
      this.RF = var1;
   }

   public tx RF() {
      return this.RF;
   }

   public Vl.nI xK() {
      return this.xK;
   }

   public void q(int var1, int var2) {
      this.xK = new Vl.CU(var1, var2);
   }

   public void q(long var1) {
      ArrayList var3 = new ArrayList();
      var3.add(var1);
      this.xK = new Vl.eo(var3, new ArrayList(), false);
   }

   public void q(boolean var1, IVirtualMemory var2, Ia.CU var3, long var4) {
      if (var3.xK() >= 0) {
         this.xK = new Vl.CU(var3.oW, var3.xK());
         this.q(var2, (Vl.CU)this.xK, var4);
      } else {
         this.xK = new Vl.eo(var3.lm, var3.zz, var3.gP);
         if (var3.gP) {
            this.Dw = var3.lm.size() == 1;
            return;
         }
      }

      this.Dw = var1 && (var3.xK() >= 0 || !var3.lm.isEmpty());
   }

   public void q(IVirtualMemory var1, Vl.CU var2, long var3) {
      if (this.RF.Dw()) {
         Long var5 = var2.q(var1, var2.RF);
         if (var5 != null) {
            long var6 = var5 & -4L;
            if (var3 + 2L == var6 || var3 + 4L == var6) {
               long var8 = -1L;

               for (int var10 = var2.RF; var10 < var2.q; var10++) {
                  Long var11 = var2.q(var1, var10);
                  if (var11 == null) {
                     return;
                  }

                  if (var8 != -1L && var11 >= var8) {
                     var2.q = var10;
                     Object[] var10000 = new Object[]{var11, var3};
                  }

                  long var12 = this.RF.RF(var1, var10);
                  if (var12 >= var3 && (var8 == -1L || var12 < var8)) {
                     var8 = var12 & -2L;
                  }
               }
            }
         }
      }
   }

   class CU implements Vl.nI {
      int q = -1;
      int RF = 0;

      public CU(int var2, int var3) {
         this.RF = var2;
         this.q = var3;
      }

      @Override
      public int q() {
         return this.q;
      }

      @Override
      public Long q(IVirtualMemory var1, int var2) {
         return var2 >= this.RF && var2 < this.q ? Vl.this.RF.q(var1, var2) : null;
      }

      @Override
      public long RF(IVirtualMemory var1, int var2) {
         return Vl.this.RF.xK(var1, var2);
      }
   }

   class eo implements Vl.nI {
      boolean q = false;
      public List RF = new ArrayList();
      public List xK = new ArrayList();

      public eo(List var2, List var3, boolean var4) {
         this.RF = var2;
         this.xK = var3;
         this.q = var4;
      }

      @Override
      public int q() {
         return this.RF.size() + this.xK.size();
      }

      private int q(int var1) {
         if (var1 < this.RF.size()) {
            return ((Long)this.RF.get(var1)).intValue();
         } else {
            return var1 < this.RF.size() + this.xK.size() ? ((Long)this.xK.get(var1 - this.RF.size())).intValue() : -1;
         }
      }

      @Override
      public Long q(IVirtualMemory var1, int var2) {
         var2 = this.q(var2);
         return Vl.this.RF.q(var1, var2);
      }

      @Override
      public long RF(IVirtualMemory var1, int var2) {
         var2 = this.q(var2);
         return Vl.this.RF.xK(var1, var2);
      }
   }

   interface nI {
      int q();

      Long q(IVirtualMemory var1, int var2);

      long RF(IVirtualMemory var1, int var2);
   }
}
