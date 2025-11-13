package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class tx {
   private static final ILogger gO = GlobalLog.getLogger(tx.class);
   @SerId(2)
   IEMem q;
   @SerId(3)
   IEGeneric RF;
   @SerId(4)
   IEVar xK;
   @SerId(5)
   IEGeneric Dw;
   @SerId(6)
   IEGlobalContext Uv;
   @SerId(7)
   List oW;

   public tx(IEGlobalContext var1) {
      this.Uv = var1;
   }

   public boolean q(IEGeneric var1, IEGeneric var2, int... var3) {
      this.RF = var1;
      this.Dw = var2;
      List var4 = tB.q(this.Uv, var1, var3);
      if (var4.size() != 0 && var4.size() <= 1) {
         this.xK = (IEVar)var4.get(0);
         this.oW = new ArrayList();
         var1.visitDepthPre(new OA(this));
         if (this.oW.size() != 0) {
            this.q = (IEMem)this.oW.get(0);
         }

         return var1.visitDepthPre(new HH(this));
      } else {
         return false;
      }
   }

   Long q(IVirtualMemory var1, int var2) {
      return this.q == null ? (long)var2 : this.q(var1, this.q, var2);
   }

   private Long q(IVirtualMemory var1, IEMem var2, int var3) {
      EState var4 = this.Dw(var1, var3);

      try {
         return var2.getReference().evaluateAddress(var4);
      } catch (Exception var5) {
         gO.debug("Cannot evaluate as address: %s", var2.getReference());
         return null;
      }
   }

   public long RF(IVirtualMemory var1, int var2) {
      return this.q(var1, var2, false);
   }

   public long xK(IVirtualMemory var1, int var2) {
      return this.q(var1, var2, true);
   }

   public long q(IVirtualMemory var1, int var2, boolean var3) {
      if (this.RF == null) {
         return -1L;
      } else {
         EState var4 = this.Dw(var1, var2);

         try {
            return this.RF.evaluateAddress(var4) | (this.Dw != null ? this.Dw.zeroExtend(32).evaluateUnsignedLong(var4) : 0L);
         } catch (Exception var6) {
            if (var3) {
               gO.catchingSilent(var6);
            }

            return -1L;
         }
      }
   }

   private EState Dw(IVirtualMemory var1, int var2) {
      EState var3 = new EState(var1.getStandardEndianess());
      var3.setMemory(var1);
      if (this.xK != null) {
         var3.setValue(this.xK, EUtil.imm(var2, this.xK.getBitsize()));
      }

      return var3;
   }

   public int q() {
      return this.q == null ? 4 : this.q.getBitsize() / 8;
   }

   public boolean RF() {
      return this.q != null;
   }

   public int xK() {
      return this.oW.size();
   }

   Long q(IVirtualMemory var1, int var2, int var3) {
      return var2 >= this.xK() ? (long)var3 : this.q(var1, (IEMem)this.oW.get(var2), var3);
   }

   public int q(int var1) {
      return ((IEMem)this.oW.get(var1)).getBitsize() / 8;
   }

   public boolean Dw() {
      if (this.RF != null && this.q != null && this.xK != null) {
         List var1 = tB.q(this.Uv, this.q);
         return !var1.isEmpty() && this.xK.equals(var1.get(0));
      } else {
         return false;
      }
   }

   public boolean Uv() {
      if (this.RF != null && this.xK != null) {
         List var1 = tB.q(this.Uv, this.RF);
         return !var1.isEmpty() && this.xK.equals(var1.get(0));
      } else {
         return false;
      }
   }

   public tx oW() {
      tx var1 = new tx(this.Uv);
      var1.q(this.RF.duplicate(), this.Dw.duplicate());
      return var1;
   }

   public tx q(bR var1, long var2) {
      tx var4 = new tx(this.Uv);
      IEGeneric var5 = this.RF.duplicate();
      var1.q(var5, var2);
      IEGeneric var6 = null;
      if (this.Dw != null) {
         var6 = this.Dw.duplicate();
         var1.q(var6, var2);
      }

      var4.q(var5, var6);
      return var4;
   }
}
