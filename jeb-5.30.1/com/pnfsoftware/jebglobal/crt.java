package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.List;

public class crt {
   crx q;
   IERoutineContext RF;

   crt(crx var1) {
      this.q = var1;
   }

   void q(ConverterInstructionEntry var1) {
      var1.r.add(this.RF.createNop());
   }

   void RF(ConverterInstructionEntry var1) {
      IECompose var2 = this.RF.createCompose(this.q.WX, this.q.hP, this.q.CB, this.q.Eq, this.q.C, this.q.Eq, this.q.GC, this.q.KF);
      var1.r.add(this.RF.createAssign(this.q.os.slice(8, 16), var2));
   }

   void xK(ConverterInstructionEntry var1) {
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.os.bit(8)));
      var1.r.add(this.RF.createAssign(this.q.CB, this.q.os.bit(10)));
      var1.r.add(this.RF.createAssign(this.q.C, this.q.os.bit(12)));
      var1.r.add(this.RF.createAssign(this.q.GC, this.q.os.bit(14)));
      var1.r.add(this.RF.createAssign(this.q.KF, this.q.os.bit(15)));
   }

   void Dw(ConverterInstructionEntry var1) {
      this.q(var1, 0);
   }

   void Uv(ConverterInstructionEntry var1) {
      this.q(var1, 1);
   }

   void oW(ConverterInstructionEntry var1) {
      this.q(var1, 2);
   }

   void gO(ConverterInstructionEntry var1) {
      this.q(var1, 3);
   }

   private void q(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      int var5 = var3.getBitsize();
      IEOperation var10 = EUtil.remU(var4, this.RF.createImm(var5, var4.getBitsize()));
      IEGeneric var6 = EUtil.shr(var3, var10).bit(0);
      var1.r.add(this.RF.createAssign(this.q.WX, var6));
      Object var7;
      switch (var2) {
         case 1:
            var7 = EUtil.notB(this.q.WX);
            break;
         case 2:
            var7 = this.q.Eq;
            break;
         case 3:
            var7 = this.q.hP;
            break;
         default:
            return;
      }

      IEOperation var8 = EUtil.andB(var3, EUtil.notB(EUtil.shl(this.q.RF(var3), var10)));
      IEOperation var9 = EUtil.shl(((IEGeneric)var7).zeroExtend(var3.getBitsize()), var10);
      var1.r.add(this.RF.createAssign(var3, EUtil.orB(var8, var9)));
   }

   void nf(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.os.part(8);
      int var3 = ((ctc)var1.insn).lm();
      IEGeneric var4 = this.q.ZU.part(var3);
      IEGeneric var5 = var2.zeroExtend(var3);
      int var6 = ctf.q(((ctc)var1.insn).q(0));
      IEMem var7 = this.q.q(var6, EUtil.add(var4, var5), 8);
      var1.r.add(this.RF.createAssign(var2, var7));
   }

   void gP(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.os.part(8);
      IECond var3 = this.RF.createCond(this.q.WX, this.q.gm, this.q.dg);
      var1.r.add(this.RF.createAssign(var2, var3));
   }

   void za(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      var2.addSideEffectDefinedVariable(this.q.GC);
   }

   void lm(ConverterInstructionEntry var1) {
      this.q.q(var1, "read_IO", 2);
   }

   void zz(ConverterInstructionEntry var1) {
      this.q.q(var1, "write_io", 0);
   }

   void JY(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).lm();
      int var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address).getBitsize() / 8;
      IECond var4 = this.RF.createCond(this.q.jk, this.RF.createImm(-var3, var2), this.RF.createImm(var3, var2));
      IEGeneric var5 = this.q.Bs.part(var2);
      this.q.q(var1, "read_IO", 2);
      this.q.q(var5, this.RF.createOperation(OperationType.ADD, var5, var4), var1);
   }

   void HF(ConverterInstructionEntry var1) {
      if (!ctl.q((ctc)var1.insn)) {
         this.JY(var1);
      } else {
         int var2 = ((ctc)var1.insn).lm();
         IEGeneric var3 = this.q.iu.part(var2);
         var1.r.add(this.RF.createJump(var1.irAddress + 5, this.RF.createOperation(OperationType.LOG_EQ, var3, this.q.q(var3))));
         this.JY(var1);
         this.q.q(var3, this.RF.createOperation(OperationType.SUB, var3, this.RF.createImm(1L, var3.getBitsize())), var1);
         var1.r.add(this.RF.createJump(var1.irAddress, null));
      }
   }

   void LK(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).lm();
      int var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address).getBitsize() / 8;
      IECond var4 = this.RF.createCond(this.q.jk, this.RF.createImm(-var3, var2), this.RF.createImm(var3, var2));
      IEGeneric var5 = this.q.mJ.part(var2);
      this.q.q(var1, "write_IO", 0);
      this.q.q(var5, this.RF.createOperation(OperationType.ADD, var5, var4), var1);
   }

   void io(ConverterInstructionEntry var1) {
      if (!ctl.q((ctc)var1.insn)) {
         this.LK(var1);
      } else {
         int var2 = ((ctc)var1.insn).lm();
         IEGeneric var3 = this.q.iu.part(var2);
         var1.r.add(this.RF.createJump(var1.irAddress + 5, this.RF.createOperation(OperationType.LOG_EQ, var3, this.q.q(var3))));
         this.LK(var1);
         this.q.q(var3, this.RF.createOperation(OperationType.SUB, var3, this.RF.createImm(1L, var3.getBitsize())), var1);
         var1.r.add(this.RF.createJump(var1.irAddress, null));
      }
   }

   void q(ConverterInstructionEntry var1, IEVar var2, String var3) {
      IEUntranslatedInstruction var4 = this.q.q(var1, var3, 2);
      var4.addSideEffectDefinedVariable(var2);
   }

   void q(ConverterInstructionEntry var1, String var2) {
      IEUntranslatedInstruction var3 = this.q.q(var1, var2, 0);
      if (var3.getParameterExpressions().isEmpty()) {
         if (((ctc)var1.insn).q() == 204) {
            var3.setParameterExpressions(alu.q(3L, 8));
         } else if (((ctc)var1.insn).q() == 241) {
            var3.setParameterExpressions(alu.q(1L, 8));
         } else {
            Assert.fail("unexpected interrupt instruction");
         }
      }
   }

   void qa(ConverterInstructionEntry var1) {
      var1.r.add(this.RF.createJump(var1.irAddress + 2, EUtil.eq(this.q.Cl, this.q.Eq)));
      this.q.q(var1, "overflow_trap", 0);
   }

   void Hk(ConverterInstructionEntry var1) {
      this.q.q(var1, "interrupt_return", 0);
   }

   void Me(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      var2.addSideEffectDefinedVariable(this.q.GC);
   }

   void PV(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      var2.setParameterExpressions(this.q.iu);
      var2.setReturnExpressions(this.q.os, this.q.fn);
   }

   void oQ(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      var2.setParameterExpressions(this.q.iu, this.q.os, this.q.fn);
   }

   void xW(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      var2.setReturnExpressions(this.q.os, this.q.fn);
   }

   void KT(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      var2.setReturnExpressions(this.q.os, this.q.fn, this.q.iu);
   }

   void Gf(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      var2.setReturnExpressions(this.q.os, this.q.fn);
   }

   void Ef(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      var2.setParameterExpressions(this.q.os, this.q.iu);
      var2.setReturnExpressions(this.q.os, this.q.ZU, this.q.iu, this.q.fn);
   }

   void cC(ConverterInstructionEntry var1) {
      this.q.RF(var1);
   }

   void sH(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      var2.setParameterExpressions(this.q.iu);
      var2.setReturnExpressions(this.q.os, this.q.fn);
   }

   void CE(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      var2.setParameterExpressions(this.q.iu, this.q.os, this.q.fn);
   }

   void wF(ConverterInstructionEntry var1) {
      this.q.RF(var1);
   }

   void If(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      var2.setReturnExpression(this.q.of);
   }

   void Dz(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.q(var1, null, 2);
      var2.addSideEffectDefinedVariable(this.q.GC);
   }

   void mI(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.q(var1, null, 2);
      var2.addSideEffectDefinedVariable(this.q.GC);
   }

   void jq(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.q(var1, null, 1);
      ArrayList var3 = new ArrayList(var2.getParameterExpressions());
      var3.add(this.q.Xz[0].part(64));
      var2.setParameterExpressions(var3);
   }

   void ui(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.q.RF(var1);
      List var3 = var2.getParameterExpressions();
      ArrayList var4 = new ArrayList();
      var4.add((IEGeneric)var3.get(1));
      var4.add((IEGeneric)var3.get(0));
      ArrayList var5 = new ArrayList();
      var5.add((IEGeneric)var3.get(2));
      var5.add(this.q.fn.part(((IEGeneric)var3.get(2)).getBitsize()));
      var2.setParameterExpressions(var5);
      var2.setReturnExpressions(var4);
   }
}
