package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.InstructionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class gP {
   private final IEGlobalContext q;
   private final bR RF;
   private final Ia.CU xK;
   private final Ia.eo Dw;

   public gP(IEGlobalContext var1, bR var2, Ia.CU var3, Ia.eo var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   public gP.ej q(gP.nI var1, Map var2, LI var3, IEGeneric var4, gP.eo var5) {
      gP.ej var6 = new gP.ej();
      var5.xK = var1.q;
      if (var3.RF()) {
         long var7 = var3.xK();
         var1.RF = (List)var2.get(var7);
         fA var9 = var1.xK();
         if (!var9.q().isPCUpdated()) {
            if (var1.q != var7 + InstructionUtil.getSizeOf(var1.RF)) {
               var5.q = null;
               var5.RF = -1L;
            }

            var1.xK = var1.RF.size();
         } else {
            var5.q = var1.xK();
            var1.xK = var1.RF.size() - 1;
            if (var9.q().getBranchType() == IInsnEmulator.BranchType.CALL) {
               List var10 = tB.q(this.q, var4);
               List var11 = this.q(var9.getProcessorMode());
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

         var1.q = var7 + InstructionUtil.getSizeUntil(var1.RF, var1.xK);
         if (var9.q().isPCUpdated()) {
            var5.RF = var1.q;
         }

         fA var15 = var1.xK();
         this.q(var15);
         var6.q = true;
      }

      return var6;
   }

   public gP.eo q() {
      return new gP.eo();
   }

   boolean q(fA var1) {
      Integer var2 = tB.q(var1);
      if (var1.Dw().xK()) {
         this.xK.nf = false;
         return false;
      } else if (var2 != null) {
         if (!this.q(var2)) {
            return false;
         } else {
            this.xK.nf = OC.xK(var2) || OC.Dw(var2);
            this.Dw.q(var1, this.xK);
            this.xK.gO = true;
            return true;
         }
      } else {
         return false;
      }
   }

   private boolean q(Integer var1) {
      return OC.RF(var1) || OC.q(var1);
   }

   private List q(int var1) {
      ArrayList var2 = new ArrayList();
      int var3 = var1 == 64 ? 8 : 4;

      for (int var4 = 0; var4 < var3; var4++) {
         IEGeneric var5 = this.RF.getRegisterVariableFromNativeRegisterId(var4);
         if (var5 instanceof IEVar) {
            var2.add(var5.asVar());
         }
      }

      return var2;
   }

   static enum CU {
      q,
      RF,
      xK;
   }

   static class ej {
      boolean q = false;
   }

   class eo {
      fA q = null;
      long RF = -1L;
      long xK = -1L;

      public boolean q(fA var1, Ia.nI var2) {
         return this.q != null
            && var1.getMnemonic().equals("MOVS")
            && var1.RF()[0].equals(var1.RF()[1])
            && tB.q(gP.this.q, gP.this.RF, var1.RF()[0], var2)
            && this.q.Uv().gO();
      }

      public gP.CU q() {
         if (this.q.Uv().RF() == 0) {
            long var1 = this.RF + this.q.getSize();
            if (this.xK == var1) {
               gP.this.xK.oW = 1;
            } else {
               gP.this.xK.Uv = 1;
            }
         } else if (this.q.Uv().RF() == 1) {
            long var3 = -1L;
            if (this.q.RF() != null && this.q.RF().length == 1) {
               switch (this.q.RF()[0].getOperandType()) {
                  case 1:
                  case 2:
                  case 3:
                     var3 = this.q.RF()[0].getOperandValue(this.RF);
               }
            }

            if (var3 == -1L) {
               return gP.CU.RF;
            }

            if (var3 != this.xK) {
               gP.this.xK.Uv = 1;
               return gP.CU.xK;
            }
         }

         return gP.CU.q;
      }

      public gP.eo RF() {
         gP.eo var1 = gP.this.new eo();
         var1.q = this.q;
         var1.RF = this.RF;
         var1.xK = this.xK;
         return var1;
      }

      @Override
      public String toString() {
         return this.q == null ? "NULL" : Long.toHexString(this.RF) + "h: " + this.q.toString();
      }
   }

   static class nI {
      long q;
      List RF;
      int xK;

      public void q(long var1, List var3) {
         this.q = var1;
         this.RF = var3;
         this.xK = var3.size() - 1;
      }

      public void q() {
         this.xK--;
         this.q = this.q - (this.xK >= 0 ? ((fA)this.RF.get(this.xK)).getSize() : 0L);
      }

      private fA xK() {
         return (fA)this.RF.get(this.RF.size() - 1);
      }

      public gP.nI RF() {
         gP.nI var1 = new gP.nI();
         var1.q = this.q;
         var1.RF = this.RF;
         var1.xK = this.xK;
         return var1;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();

         for (int var2 = Math.max(0, this.xK - 5); var2 < Math.min(this.RF.size() - 1, this.xK + 5); var2++) {
            var1.append(this.xK == var2 ? "=> " : "   ");
            var1.append(this.RF.get(var2)).append('\n');
         }

         return var1.toString();
      }
   }
}
