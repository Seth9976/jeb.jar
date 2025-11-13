package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.List;

class bcu {
   bcx q;
   IERoutineContext RF;

   bcu(bcx var1) {
      this.q = var1;
   }

   private void q(List var1, IEGeneric var2, IEGeneric var3, boolean var4, boolean var5, boolean var6) {
      IEGeneric var7 = this.q.Dw(var1, var2);
      IEGeneric var8 = var3.equals(var2) ? var7 : var3;
      IEOperation var9 = this.RF.createOperation(OperationType.ADD, var2, var3);
      if (var4) {
         var9 = this.RF.createOperation(OperationType.ADD, var9, this.q.sH.zeroExtend(var2.getBitsize()));
      }

      IEGeneric var10 = this.q.q(var1, var2, var9);
      this.q.q(var1, var10);
      this.q.RF(var1, var10);
      this.q.q(var1, var7, var8, var10);
      this.q.q(var1, var7, var8, var10, var4, var5, var6);
      this.q.xK(var1, var10);
   }

   private void q(List var1, IEGeneric var2, IEGeneric var3, boolean var4, boolean var5, boolean var6, boolean var7) {
      IEGeneric var8 = var2;
      IEGeneric var9 = var3;
      if (var4) {
         var8 = this.q.Dw(var1, var2);
         var9 = var3.equals(var2) ? var8 : var3;
      }

      IEOperation var10 = this.RF.createOperation(OperationType.SUB, var2, var3);
      if (var5) {
         var10 = this.RF.createOperation(OperationType.SUB, var10, this.q.sH.zeroExtend(var2.getBitsize()));
      }

      IEGeneric var11;
      if (var4) {
         var11 = this.q.q(var1, var2, var10);
      } else {
         var11 = this.q.Dw(var1, var10);
      }

      this.q.q(var1, var11);
      this.q.RF(var1, var11);
      this.q.RF(var1, var8, var9, var11);
      this.q.RF(var1, var8, var9, var11, var5, var6, var7);
      if (var5) {
         this.q.xK(var1, var11);
      } else {
         var1.add(this.RF.createAssign(this.q.Dz, this.RF.createOperation(OperationType.LT_S, var8, var9)));
      }
   }

   void q(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var4 = this.q.q((bdc)var1.insn, 1, var1.address);
      this.q(var1.r, var3, var4, var2, true, true);
   }

   void RF(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var4 = this.q.q((bdc)var1.insn, 1, var1.address);
      this.q(var1.r, var3, var4, true, var2, true, true);
   }

   void q(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEImm var3 = this.q.Bu;
      this.q(var1.r, var2, var3, false, false, false);
   }

   void RF(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEImm var3 = this.q.Bu;
      this.q(var1.r, var2, var3, true, false, false, false);
   }

   void xK(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var3 = this.q.q((bdc)var1.insn, 1, var1.address);
      this.q(var1.r, var2, var3.zeroExtend(16), false, true, false);
   }

   void Dw(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var3 = this.q.q((bdc)var1.insn, 1, var1.address);
      this.q(var1.r, var2, var3.zeroExtend(16), true, false, true, false);
   }

   void Uv(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var3 = this.q.q((bdc)var1.insn, 1, var1.address);
      IEOperation var4 = this.RF.createOperation(OperationType.MUL, var2, var3);
      IEGeneric var5 = this.RF.createOperation(OperationType.MUL, var2.zeroExtend(16), var3.zeroExtend(16)).slice(8, 16);
      IEVar var6 = this.q.Ef[0];
      IEVar var7 = this.q.Ef[1];
      if (!var2.equals(var6) && !var3.equals(var6)) {
         this.q.q(var1.r, var6, var4);
         this.q.q(var1.r, var7, var5);
      } else {
         this.q.q(var1.r, this.q.Rr, var5);
         this.q.q(var1.r, var6, var4);
         this.q.q(var1.r, var7, this.q.Rr);
      }

      this.q.q(var1.r, this.RF.createOperation(OperationType.OR, var6, var7));
      this.q.q(var1.r, this.q.sH, var7.msb());
   }

   void q(ConverterInstructionEntry var1, OperationType var2) {
      IEGeneric var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var4 = this.q.q((bdc)var1.insn, 1, var1.address);
      IEOperation var5 = this.RF.createOperation(var2, var3, var4);
      IEGeneric var6 = this.q.q(var1.r, var3, var5);
      this.q.q(var1.r, var6);
      this.q.RF(var1.r, var6);
      this.q.q(var1.r, this.q.If, this.q.rL);
      this.q.xK(var1.r, var6);
   }

   void oW(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var3 = this.q.q((bdc)var1.insn, 1, var1.address);
      IEOperation var4 = this.RF.createOperation(OperationType.AND, var2, var3);
      this.q.q(var1.r, var4);
      this.q.RF(var1.r, var4);
      this.q.q(var1.r, this.q.If, this.q.rL);
      this.q.xK(var1.r, var4);
   }

   void xK(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var4 = this.q.q((bdc)var1.insn, 1, var1.address);
      this.q(var1.r, var3, var4, false, var2, true, true);
   }

   void gO(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      this.q.q(var1.r, var2, this.RF.createOperation(OperationType.XOR, var2, var2));
      this.q.q(var1.r, this.q.CE, this.q.eJ);
      this.q.q(var1.r, this.q.wF, this.q.rL);
      this.q.q(var1.r, this.q.If, this.q.rL);
      this.q.q(var1.r, this.q.Dz, this.q.rL);
   }

   void nf(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      this.q.q(var1.r, var2, this.q.IN);
   }

   void gP(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEOperation var3 = this.RF.createOperation(OperationType.NOT, var2);
      IEGeneric var4 = this.q.q(var1.r, var2, var3);
      this.q.q(var1.r, var4);
      this.q.RF(var1.r, var4);
      this.q.q(var1.r, this.q.If, this.q.rL);
      this.q.q(var1.r, this.q.sH, this.q.eJ);
      this.q.xK(var1.r, var4);
   }

   void za(ConverterInstructionEntry var1) {
      IEImm var2 = this.RF.createImm(0L, 8);
      IEGeneric var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var5 = this.q.Dw(var1.r, var3);
      IEOperation var6 = this.RF.createOperation(OperationType.SUB, var2, var3);
      IEGeneric var7 = this.q.q(var1.r, var3, var6);
      this.q.q(var1.r, var7);
      this.q.RF(var1.r, var7);
      this.q.RF(var1.r, var2, var5, var7);
      this.q.RF(var1.r, var2, var5, var7, false, true, true);
      this.q.xK(var1.r, var7);
   }

   void lm(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var3 = this.q.q((bdc)var1.insn, 1, var1.address);
      IEOperation var4 = this.RF.createOperation(OperationType.OR, var2, this.RF.createOperation(OperationType.SHL, this.q.Bu, var3));
      this.q.q(var1.r, var2, var4);
   }

   void zz(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var3 = this.q.q((bdc)var1.insn, 1, var1.address);
      IEOperation var4 = this.RF
         .createOperation(OperationType.AND, var2, this.RF.createOperation(OperationType.NOT, this.RF.createOperation(OperationType.SHL, this.q.Bu, var3)));
      this.q.q(var1.r, var2, var4);
   }

   void JY(ConverterInstructionEntry var1) {
      this.xK(var1, 0);
   }

   void HF(ConverterInstructionEntry var1) {
      this.xK(var1, 1);
   }

   private void xK(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var4 = this.q.Dw(var1.r, var3);
      IEOperation var5 = this.RF.createOperation(OperationType.SHL, var3, this.q.Bu);
      if (var2 == 1) {
         var5 = this.RF.createOperation(OperationType.OR, var5, this.q.sH.zeroExtend(8));
      }

      this.q.q(var1.r, var3, var5);
      this.q.q(var1.r, var3);
      this.q.RF(var1.r, var3);
      this.q.q(var1.r, this.q.sH, var4.bit(7));
      this.q.q(var1.r, this.q.mI, var4.bit(3));
      this.q.q(var1.r, this.q.If, this.RF.createOperation(OperationType.XOR, this.q.wF, this.q.sH));
      this.q.xK(var1.r, var3);
   }

   void LK(ConverterInstructionEntry var1) {
      this.Dw(var1, 0);
   }

   void io(ConverterInstructionEntry var1) {
      this.Dw(var1, 1);
   }

   void qa(ConverterInstructionEntry var1) {
      this.Dw(var1, 2);
   }

   private void Dw(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var4 = this.q.Dw(var1.r, var3);
      OperationType var5 = var2 != 2 ? OperationType.SHR : OperationType.SAR;
      IEOperation var6 = this.RF.createOperation(var5, var3, this.q.Bu);
      if (var2 == 1) {
         var6 = this.RF.createOperation(OperationType.OR, var6, this.q.sH.zeroExtend(8).leftShift(7));
      }

      this.q.q(var1.r, var3, var6);
      this.q.q(var1.r, var3);
      this.q.RF(var1.r, var3);
      this.q.q(var1.r, this.q.sH, var4.bit(0));
      this.q.q(var1.r, this.q.If, this.RF.createOperation(OperationType.XOR, this.q.wF, this.q.sH));
      this.q.xK(var1.r, var3);
   }

   void Hk(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IECompose var3 = this.RF.createCompose(var2.slice(4, 8), var2.slice(0, 4));
      this.q.q(var1.r, var2, var3);
   }

   void q(ConverterInstructionEntry var1, int var2) {
      IEVar var3 = this.q.TX[var2];
      this.q.q(var1.r, var3, this.q.eJ);
   }

   void RF(ConverterInstructionEntry var1, int var2) {
      IEVar var3 = this.q.TX[var2];
      this.q.q(var1.r, var3, this.q.rL);
   }

   void Me(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var3 = this.q.q((bdc)var1.insn, 1, var1.address);
      this.q.q(var1.r, this.q.jq, this.RF.createOperation(OperationType.SHR, var2, var3).bit(0));
   }

   void PV(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var3 = this.q.q((bdc)var1.insn, 1, var1.address);
      IEOperation var4 = this.RF.createOperation(OperationType.NOT, this.RF.createOperation(OperationType.SHL, this.q.Bu, var3));
      var4 = this.RF.createOperation(OperationType.AND, var2, var4);
      var4 = this.RF.createOperation(OperationType.OR, var4, this.RF.createOperation(OperationType.SHL, this.q.jq.zeroExtend(8), var3));
      this.q.q(var1.r, var2, var4);
   }
}
