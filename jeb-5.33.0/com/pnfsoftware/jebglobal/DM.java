package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.InstructionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DM {
   private final IEGlobalContext pC;
   private final Sp A;
   private final Ro.Sv kS;
   private final Ro.Av wS;

   public DM(IEGlobalContext var1, Sp var2, Ro.Sv var3, Ro.Av var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   public DM.Ws pC(DM.K var1, Map var2, os var3, IEGeneric var4, DM.Av var5) {
      DM.Ws var6 = new DM.Ws();
      var5.kS = var1.pC;
      if (var3.A()) {
         long var7 = var3.kS();
         var1.A = (List)var2.get(var7);
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var9 = var1.kS();
         if (!var9.pC().isPCUpdated()) {
            if (var1.pC != var7 + InstructionUtil.getSizeOf(var1.A)) {
               var5.pC = null;
               var5.A = -1L;
            }

            var1.kS = var1.A.size();
         } else {
            var5.pC = var1.kS();
            var1.kS = var1.A.size() - 1;
            if (var9.pC().getBranchType() == IInsnEmulator.BranchType.CALL) {
               List var10 = Td.pC(this.pC, var4);
               List var11 = this.pC(var9.getProcessorMode());
               boolean var12 = false;

               for (IEVar var14 : var11) {
                  if (var10.contains(var14)) {
                     var12 = true;
                     break;
                  }
               }

               if (var12) {
                  return var6;
               }
            }
         }

         var1.pC = var7 + InstructionUtil.getSizeUntil(var1.A, var1.kS);
         if (var9.pC().isPCUpdated()) {
            var5.A = var1.pC;
         }

         com.pnfsoftware.jeb.corei.parsers.arm.rQ var15 = var1.kS();
         this.pC(var15);
         var6.pC = true;
      }

      return var6;
   }

   public DM.Av pC() {
      return new DM.Av();
   }

   boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1) {
      Integer var2 = Td.pC(var1);
      if (var1.wS().kS()) {
         this.kS.ys = false;
         return false;
      } else if (var2 != null) {
         if (!this.pC(var2)) {
            return false;
         } else {
            this.kS.ys = PU.kS(var2) || PU.wS(var2);
            this.wS.pC(var1, this.kS);
            this.kS.sY = true;
            return true;
         }
      } else {
         return false;
      }
   }

   private boolean pC(Integer var1) {
      return PU.A(var1) || PU.pC(var1);
   }

   private List pC(int var1) {
      ArrayList var2 = new ArrayList();
      int var3 = var1 == 64 ? 8 : 4;

      for (int var4 = 0; var4 < var3; var4++) {
         IEGeneric var5 = this.A.getRegisterVariableFromNativeRegisterId(var4);
         if (var5 instanceof IEVar) {
            var2.add(var5.asVar());
         }
      }

      return var2;
   }

   class Av {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ pC = null;
      long A = -1L;
      long kS = -1L;

      public boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Ro.K var2) {
         return this.pC != null
            && var1.getMnemonic().equals("MOVS")
            && var1.A()[0].equals(var1.A()[1])
            && Td.pC(DM.this.pC, DM.this.A, var1.A()[0], var2)
            && this.pC.UT().sY();
      }

      public DM.Sv pC() {
         if (this.pC.UT().A() == 0) {
            long var1 = this.A + this.pC.getSize();
            if (this.kS == var1) {
               DM.this.kS.E = 1;
            } else {
               DM.this.kS.UT = 1;
            }
         } else if (this.pC.UT().A() == 1) {
            long var3 = -1L;
            if (this.pC.A() != null && this.pC.A().length == 1) {
               switch (this.pC.A()[0].getOperandType()) {
                  case 1:
                  case 2:
                  case 3:
                     var3 = this.pC.A()[0].getOperandValue(this.A);
               }
            }

            if (var3 == -1L) {
               return DM.Sv.A;
            }

            if (var3 != this.kS) {
               DM.this.kS.UT = 1;
               return DM.Sv.kS;
            }
         }

         return DM.Sv.pC;
      }

      public DM.Av A() {
         DM.Av var1 = DM.this.new Av();
         var1.pC = this.pC;
         var1.A = this.A;
         var1.kS = this.kS;
         return var1;
      }

      @Override
      public String toString() {
         return this.pC == null ? "NULL" : Long.toHexString(this.A) + "h: " + this.pC.toString();
      }
   }

   static class K {
      long pC;
      List A;
      int kS;

      public void pC(long var1, List var3) {
         this.pC = var1;
         this.A = var3;
         this.kS = var3.size() - 1;
      }

      public void pC() {
         this.kS--;
         this.pC = this.pC - (this.kS >= 0 ? ((com.pnfsoftware.jeb.corei.parsers.arm.rQ)this.A.get(this.kS)).getSize() : 0L);
      }

      private com.pnfsoftware.jeb.corei.parsers.arm.rQ kS() {
         return (com.pnfsoftware.jeb.corei.parsers.arm.rQ)this.A.get(this.A.size() - 1);
      }

      public DM.K A() {
         DM.K var1 = new DM.K();
         var1.pC = this.pC;
         var1.A = this.A;
         var1.kS = this.kS;
         return var1;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();

         for (int var2 = Math.max(0, this.kS - 5); var2 < Math.min(this.A.size() - 1, this.kS + 5); var2++) {
            var1.append(this.kS == var2 ? "=> " : "   ");
            var1.append(this.A.get(var2)).append('\n');
         }

         return var1.toString();
      }
   }

   static enum Sv {
      pC,
      A,
      kS;
   }

   static class Ws {
      boolean pC = false;
   }
}
