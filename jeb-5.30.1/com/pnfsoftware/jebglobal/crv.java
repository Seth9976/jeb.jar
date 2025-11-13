package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.List;

public class crv {
   crx q;
   IERoutineContext RF;

   crv(crx var1) {
      this.q = var1;
   }

   void q(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).gO;
      Assert.a(this.q.Sz.getBitsize() == var2);
      int var3 = ((ctc)var1.insn).za();
      IEGeneric var4 = this.q.q((ctc)var1.insn, 0, -var3, var1.address);
      this.q(var2, var3, var4, var1.r);
   }

   void q(int var1, int var2, IEGeneric var3, List var4) {
      Assert.a(var3.getBitsize() == var2);
      IEGeneric var5 = var3;
      if (EUtil.getUsedVarIds(var3).contains(256)) {
         var5 = this.q.RF(var2, var4);
         var4.add(this.RF.createAssign(var5, var3));
      }

      IEGeneric var6 = this.q.Sz.part(var1);
      IEImm var7 = this.RF.createImm(var2 / 8, var1);
      var4.add(this.RF.createAssign(var6, this.RF.createOperation(OperationType.SUB, var6, var7)));
      var4.add(this.RF.createAssign(this.q.createStackMemoryAccess(var6, var2), var5));
   }

   void RF(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).gO;
      Assert.a(this.q.Sz.getBitsize() == var2);
      int var3 = ((ctc)var1.insn).za();
      IEGeneric var4 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      this.RF(var2, var3, var4, var1.r);
   }

   void RF(int var1, int var2, IEGeneric var3, List var4) {
      int var5 = var3.getBitsize();
      IEGeneric var6 = this.q.Sz.part(var1);
      if (var3 instanceof IEMem && EUtil.getUsedVarIds(var3).contains(256)) {
         IEGeneric var9 = this.q.RF(var5, var4);
         var4.add(this.RF.createAssign(var9, this.q.createStackMemoryAccess(var6, var5)));
         IEImm var8 = this.RF.createImm(var2 / 8, var1);
         var4.add(this.RF.createAssign(var6, this.RF.createOperation(OperationType.ADD, var6, var8)));
         var4.add(this.RF.createAssign(var3, var9));
      } else {
         var4.add(this.RF.createAssign(var3, this.q.createStackMemoryAccess(var6, var5)));
         IEImm var7 = this.RF.createImm(var2 / 8, var1);
         var4.add(this.RF.createAssign(var6, this.RF.createOperation(OperationType.ADD, var6, var7)));
      }
   }

   void xK(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).za();
      if (var2 != 16 && var2 != 32) {
         throw new RuntimeException();
      } else {
         int var3 = ((ctc)var1.insn).gO;
         Assert.a(this.q.Sz.getBitsize() == var3);
         IEImm var4 = this.RF.createImm(var2 / 8, var3);
         IEVar var5 = this.q.q(var3);
         var1.r.add(this.RF.createAssign(var5, this.q.Sz));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.SUB, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.createStackMemoryAccess(this.q.Sz, var2), this.q.os.part(var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.SUB, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.createStackMemoryAccess(this.q.Sz, var2), this.q.iu.part(var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.SUB, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.createStackMemoryAccess(this.q.Sz, var2), this.q.fn.part(var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.SUB, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.createStackMemoryAccess(this.q.Sz, var2), this.q.ZU.part(var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.SUB, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.createStackMemoryAccess(this.q.Sz, var2), var5.part(var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.SUB, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.createStackMemoryAccess(this.q.Sz, var2), this.q.fq.part(var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.SUB, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.createStackMemoryAccess(this.q.Sz, var2), this.q.mJ.part(var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.SUB, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.createStackMemoryAccess(this.q.Sz, var2), this.q.Bs.part(var2)));
      }
   }

   void Dw(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).za();
      if (var2 != 16 && var2 != 32) {
         throw new RuntimeException();
      } else {
         int var3 = ((ctc)var1.insn).gO;
         Assert.a(this.q.Sz.getBitsize() == var3);
         IEImm var4 = this.RF.createImm(var2 / 8, var3);
         var1.r.add(this.RF.createAssign(this.q.Bs.part(var2), this.q.createStackMemoryAccess(this.q.Sz, var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.ADD, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.mJ.part(var2), this.q.createStackMemoryAccess(this.q.Sz, var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.ADD, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.fq.part(var2), this.q.createStackMemoryAccess(this.q.Sz, var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.ADD, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.ADD, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.ZU.part(var2), this.q.createStackMemoryAccess(this.q.Sz, var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.ADD, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.fn.part(var2), this.q.createStackMemoryAccess(this.q.Sz, var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.ADD, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.iu.part(var2), this.q.createStackMemoryAccess(this.q.Sz, var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.ADD, this.q.Sz, var4)));
         var1.r.add(this.RF.createAssign(this.q.os.part(var2), this.q.createStackMemoryAccess(this.q.Sz, var2)));
         var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.ADD, this.q.Sz, var4)));
      }
   }

   void Uv(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).gO;
      Assert.a(this.q.Sz.getBitsize() == var2);
      int var3 = ((ctc)var1.insn).za();
      IEImm var4 = this.RF.createImm(var3 / 8, var2);
      var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.SUB, this.q.Sz, var4)));
      IECompose var5 = this.RF
         .createCompose(
            this.q.WX,
            this.q.hP,
            this.q.CB,
            this.q.Eq,
            this.q.C,
            this.q.Eq,
            this.q.GC,
            this.q.KF,
            this.q.rk,
            this.q.cy,
            this.q.jk,
            this.q.Cl,
            this.q.hM,
            this.q.kv,
            this.q.Eq
         );
      if (var3 == 32 || var3 == 64) {
         var5 = this.RF.createCompose(var5, this.q.Eq, this.q.Eq, this.q.Al, this.q.Kn, this.q.vh, this.q.Rd);
      }

      var1.r.add(this.RF.createAssign(this.q.createStackMemoryAccess(this.q.Sz, var3), var5.zeroExtend(var3)));
   }

   void oW(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).gO;
      Assert.a(this.q.Sz.getBitsize() == var2);
      int var3 = ((ctc)var1.insn).za();
      IEImm var4 = this.RF.createImm(var3 / 8, var2);
      IEGeneric var5 = this.q.RF(var3, var1.r);
      var1.r.add(this.RF.createAssign(var5, this.q.createStackMemoryAccess(this.q.Sz, var3)));
      var1.r.add(this.RF.createAssign(this.q.WX, var5.bit(0)));
      var1.r.add(this.RF.createAssign(this.q.CB, var5.bit(2)));
      var1.r.add(this.RF.createAssign(this.q.C, var5.bit(4)));
      var1.r.add(this.RF.createAssign(this.q.GC, var5.bit(6)));
      var1.r.add(this.RF.createAssign(this.q.KF, var5.bit(7)));
      var1.r.add(this.RF.createAssign(this.q.rk, var5.bit(8)));
      var1.r.add(this.RF.createAssign(this.q.cy, var5.bit(9)));
      var1.r.add(this.RF.createAssign(this.q.jk, var5.bit(10)));
      var1.r.add(this.RF.createAssign(this.q.Cl, var5.bit(11)));
      var1.r.add(this.RF.createAssign(this.q.kv, var5.bit(14)));
      if (var3 == 32 || var3 == 64) {
         var1.r.add(this.RF.createAssign(this.q.oS, this.q.Eq));
         var1.r.add(this.RF.createAssign(this.q.Al, var5.bit(18)));
         var1.r.add(this.RF.createAssign(this.q.Rd, var5.bit(21)));
      }

      var1.r.add(this.RF.createAssign(this.q.Sz, this.RF.createOperation(OperationType.ADD, this.q.Sz, var4)));
   }

   void gO(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).gO;
      Assert.a(this.q.Sz.getBitsize() == var2);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      Assert.a(var3.getBitsize() == 16 && var3 instanceof IEImm);
      int var4 = (int)EUtil.evaluateUnsignedLong_preVerified(var3);
      IEGeneric var5 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      Assert.a(var5.getBitsize() == 8 && var5 instanceof IEImm);
      int var6 = (int)EUtil.evaluate_preVerified(var5)._remU(this.RF.createImm(32L, 8)).getValueAsUnsignedLong();
      int var7 = ((ctc)var1.insn).za();
      IEGeneric var8 = this.q.fq.part(var7);
      IEGeneric var9 = this.q.Sz.part(var7);
      this.q(var2, var7, var8, var1.r);
      IEGeneric var10 = this.q.RF(var7, var1.r);
      var1.r.add(this.RF.createAssign(var10, this.q.Sz.part(var7)));
      if (var6 != 0) {
         for (int var11 = 1; var11 < var6; var11++) {
            IEGeneric var12 = this.q.fq.part(var2);
            var1.r.add(this.RF.createAssign(var12, EUtil.sub(var12, this.RF.createImm(var7 / 8, var7))));
            this.q(var2, var7, this.q.createStackMemoryAccess(this.q.fq.part(var2), var7), var1.r);
         }

         this.q(var2, var7, var10, var1.r);
      }

      var1.r.add(this.RF.createAssign(var8, var10));
      var1.r.add(this.RF.createAssign(var9, EUtil.sub(var9, this.RF.createImm(var4, var9.getBitsize()))));
   }

   void nf(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).gO;
      Assert.a(this.q.Sz.getBitsize() == var2);
      int var3 = ((ctc)var1.insn).za();
      IEGeneric var4 = this.q.fq.part(var2);
      IEGeneric var5 = this.q.Sz.part(var2);
      var1.r.add(this.RF.createAssign(var5, var4));
      IEGeneric var6 = this.q.fq.part(var3);
      this.RF(var2, var3, var6, var1.r);
   }
}
