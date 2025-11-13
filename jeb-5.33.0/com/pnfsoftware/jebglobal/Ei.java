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
public class Ei {
   private static final ILogger sY = GlobalLog.getLogger(Ei.class);
   @SerId(2)
   IEMem pC;
   @SerId(3)
   IEGeneric A;
   @SerId(4)
   IEVar kS;
   @SerId(5)
   IEGeneric wS;
   @SerId(6)
   IEGlobalContext UT;
   @SerId(7)
   List E;

   public Ei(IEGlobalContext var1) {
      this.UT = var1;
   }

   public boolean pC(IEGeneric var1, IEGeneric var2, int... var3) {
      this.A = var1;
      this.wS = var2;
      List var4 = Td.pC(this.UT, var1, var3);
      if (var4.size() != 0 && var4.size() <= 1) {
         this.kS = (IEVar)var4.get(0);
         this.E = new ArrayList();
         var1.visitDepthPre(new Ql(this));
         if (this.E.size() != 0) {
            this.pC = (IEMem)this.E.get(0);
         }

         return var1.visitDepthPre(new iK(this));
      } else {
         return false;
      }
   }

   Long pC(IVirtualMemory var1, int var2) {
      return this.pC == null ? (long)var2 : this.pC(var1, this.pC, var2);
   }

   private Long pC(IVirtualMemory var1, IEMem var2, int var3) {
      EState var4 = this.wS(var1, var3);

      try {
         return var2.getReference().evaluateAddress(var4);
      } catch (Exception var5) {
         sY.debug("Cannot evaluate as address: %s", var2.getReference());
         return null;
      }
   }

   public long A(IVirtualMemory var1, int var2) {
      return this.pC(var1, var2, false);
   }

   public long kS(IVirtualMemory var1, int var2) {
      return this.pC(var1, var2, true);
   }

   public long pC(IVirtualMemory var1, int var2, boolean var3) {
      if (this.A == null) {
         return -1L;
      } else {
         EState var4 = this.wS(var1, var2);

         try {
            return this.A.evaluateAddress(var4) | (this.wS != null ? this.wS.zeroExtend(32).evaluateUnsignedLong(var4) : 0L);
         } catch (Exception var6) {
            if (var3) {
               sY.catchingSilent(var6);
            }

            return -1L;
         }
      }
   }

   private EState wS(IVirtualMemory var1, int var2) {
      EState var3 = new EState(var1.getStandardEndianess());
      var3.setMemory(var1);
      if (this.kS != null) {
         var3.setValue(this.kS, EUtil.imm(var2, this.kS.getBitsize()));
      }

      return var3;
   }

   public int pC() {
      return this.pC == null ? 4 : this.pC.getBitsize() / 8;
   }

   public boolean A() {
      return this.pC != null;
   }

   public int kS() {
      return this.E.size();
   }

   Long pC(IVirtualMemory var1, int var2, int var3) {
      return var2 >= this.kS() ? (long)var3 : this.pC(var1, (IEMem)this.E.get(var2), var3);
   }

   public int pC(int var1) {
      return ((IEMem)this.E.get(var1)).getBitsize() / 8;
   }

   public boolean wS() {
      if (this.A != null && this.pC != null && this.kS != null) {
         List var1 = Td.pC(this.UT, this.pC);
         return !var1.isEmpty() && this.kS.equals(var1.get(0));
      } else {
         return false;
      }
   }

   public boolean UT() {
      if (this.A != null && this.kS != null) {
         List var1 = Td.pC(this.UT, this.A);
         return !var1.isEmpty() && this.kS.equals(var1.get(0));
      } else {
         return false;
      }
   }

   public Ei pC(Sp var1, long var2) {
      Ei var4 = new Ei(this.UT);
      IEGeneric var5 = this.A.duplicate();
      var1.pC(var5, var2);
      IEGeneric var6 = null;
      if (this.wS != null) {
         var6 = this.wS.duplicate();
         var1.pC(var6, var2);
      }

      var4.pC(var5, var6);
      return var4;
   }
}
