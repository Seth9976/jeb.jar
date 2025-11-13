package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.List;

@SerDisabled
class CW {
   private static final ILogger pC = GlobalLog.getLogger(CW.class);
   private Ei A;
   private CW.K kS;
   private boolean wS;

   public boolean pC() {
      return this.wS;
   }

   public void pC(Ei var1) {
      this.A = var1;
   }

   public Ei A() {
      return this.A;
   }

   public CW.K kS() {
      return this.kS;
   }

   public void pC(int var1, int var2) {
      this.kS = new CW.Sv(var1, var2);
   }

   public void pC(long var1) {
      ArrayList var3 = new ArrayList();
      var3.add(var1);
      this.kS = new CW.Av(var3, new ArrayList(), false);
   }

   public void pC(boolean var1, IVirtualMemory var2, Ro.Sv var3, long var4) {
      if (var3.A() >= 0) {
         this.kS = new CW.Sv(var3.E, var3.A());
         this.pC(var2, (CW.Sv)this.kS, var4);
      } else {
         this.kS = new CW.Av(var3.oT, var3.fI, var3.ld);
         if (var3.ld) {
            this.wS = var3.oT.size() == 1;
            return;
         }
      }

      this.wS = var1 && (var3.A() >= 0 || !var3.oT.isEmpty());
   }

   public void pC(IVirtualMemory var1, CW.Sv var2, long var3) {
      if (this.A.wS()) {
         Long var5 = var2.pC(var1, var2.A);
         if (var5 != null) {
            long var6 = var5 & -4L;
            if (var3 + 2L == var6 || var3 + 4L == var6) {
               long var8 = -1L;

               for (int var10 = var2.A; var10 < var2.pC; var10++) {
                  Long var11 = var2.pC(var1, var10);
                  if (var11 == null) {
                     return;
                  }

                  if (var8 != -1L && var11 >= var8) {
                     var2.pC = var10;
                     Object[] var10000 = new Object[]{var11, var3};
                  }

                  long var12 = this.A.A(var1, var10);
                  if (var12 >= var3 && (var8 == -1L || var12 < var8)) {
                     var8 = var12 & -2L;
                  }
               }
            }
         }
      }
   }

   class Av implements CW.K {
      boolean pC = false;
      public List A = new ArrayList();
      public List kS = new ArrayList();

      public Av(List var2, List var3, boolean var4) {
         this.A = var2;
         this.kS = var3;
         this.pC = var4;
      }

      @Override
      public int pC() {
         return this.A.size() + this.kS.size();
      }

      private int pC(int var1) {
         if (var1 < this.A.size()) {
            return ((Long)this.A.get(var1)).intValue();
         } else {
            return var1 < this.A.size() + this.kS.size() ? ((Long)this.kS.get(var1 - this.A.size())).intValue() : -1;
         }
      }

      @Override
      public Long pC(IVirtualMemory var1, int var2) {
         var2 = this.pC(var2);
         return CW.this.A.pC(var1, var2);
      }

      @Override
      public long A(IVirtualMemory var1, int var2) {
         var2 = this.pC(var2);
         return CW.this.A.kS(var1, var2);
      }
   }

   interface K {
      int pC();

      Long pC(IVirtualMemory var1, int var2);

      long A(IVirtualMemory var1, int var2);
   }

   class Sv implements CW.K {
      int pC = -1;
      int A = 0;

      public Sv(int var2, int var3) {
         this.A = var2;
         this.pC = var3;
      }

      @Override
      public int pC() {
         return this.pC;
      }

      @Override
      public Long pC(IVirtualMemory var1, int var2) {
         return var2 >= this.A && var2 < this.pC ? CW.this.A.pC(var1, var2) : null;
      }

      @Override
      public long A(IVirtualMemory var1, int var2) {
         return CW.this.A.kS(var1, var2);
      }
   }
}
