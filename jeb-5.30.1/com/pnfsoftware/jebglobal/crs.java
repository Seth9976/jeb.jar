package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;

public class crs {
   crx q;
   IERoutineContext RF;

   crs(crx var1) {
      this.q = var1;
   }

   void q(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, -var2.getBitsize(), var1.address);
      Assert.a(var2.getBitsize() == var3.getBitsize());
      this.q.q(var2, var3, var1);
   }

   void RF(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      Assert.a(var3 instanceof IEMem);
      IEGeneric var4 = ((IEMem)var3).getReference();
      int var5 = var2.getBitsize();
      if (var5 < var4.getBitsize()) {
         var4 = var4.part(var5);
      } else if (var5 > var4.getBitsize()) {
         var4 = var4.zeroExtend(var5);
      }

      this.q.q(var2, var4, var1);
   }

   void xK(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, -var2.getBitsize(), var1.address);
      this.q.q(var2, var3, var1);
   }

   void Dw(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, var2.getBitsize(), var1.address);
      this.q.q(var2, var3, var1);
   }

   void Uv(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      Assert.a(var2.getBitsize() == var3.getBitsize());
      IEGeneric var4 = this.q.RF(var2.getBitsize(), var1.r);
      var1.r.add(this.RF.createAssign(var4, var2));
      this.q.q(var2, var3, var1);
      this.q.q(var3, var4, var1);
   }

   void oW(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.os.part(16);
      IECond var3 = this.RF.createCond(var2.msb(), this.q.wQ, this.q.uY);
      var1.r.add(this.RF.createAssign(this.q.fn.part(16), var3));
   }

   void gO(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.os.part(32);
      IECond var3 = this.RF.createCond(var2.msb(), this.q.ap, this.q.Oj);
      this.q.q(this.q.fn.part(32), var3, var1);
   }

   void nf(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.os.part(64);
      IECond var3 = this.RF.createCond(var2.msb(), this.q.Xi, this.q.RL);
      var1.r.add(this.RF.createAssign(this.q.fn.part(64), var3));
   }

   void gP(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.os.part(8);
      IECond var3 = this.RF.createCond(var2.msb(), this.q.gm, this.q.dg);
      var1.r.add(this.RF.createAssign(this.q.os.slice(8, 16), var3));
   }

   void za(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.os.part(16);
      IECond var3 = this.RF.createCond(var2.msb(), this.q.wQ, this.q.uY);
      IECompose var4 = this.RF.createCompose(var2, var3);
      this.q.q(this.q.os.part(32), var4, var1);
   }

   void lm(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.os.part(32);
      IECond var3 = this.RF.createCond(var2.msb(), this.q.ap, this.q.Oj);
      var1.r.add(this.RF.createAssign(this.q.os.slice(32, 64), var3));
   }

   void zz(ConverterInstructionEntry var1) {
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.Eq));
   }

   void JY(ConverterInstructionEntry var1) {
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.hP));
   }

   void HF(ConverterInstructionEntry var1) {
      var1.r.add(this.RF.createAssign(this.q.jk, this.q.Eq));
   }

   void LK(ConverterInstructionEntry var1) {
      var1.r.add(this.RF.createAssign(this.q.jk, this.q.hP));
   }

   void io(ConverterInstructionEntry var1) {
      var1.r.add(this.RF.createAssign(this.q.cy, this.q.Eq));
   }

   void qa(ConverterInstructionEntry var1) {
      var1.r.add(this.RF.createAssign(this.q.cy, this.q.hP));
   }

   void Hk(ConverterInstructionEntry var1) {
      var1.r.add(this.RF.createAssign(this.q.WX, EUtil.notB(this.q.WX)));
   }

   void Me(ConverterInstructionEntry var1) {
      if (!ctl.q((ctc)var1.insn)) {
         this.wF(var1);
      } else {
         int var2 = ((ctc)var1.insn).lm();
         IEGeneric var3 = this.q.iu.part(var2);
         var1.r.add(this.RF.createJump(var1.irAddress + 6, this.RF.createOperation(OperationType.LOG_EQ, var3, this.q.q(var3))));
         this.wF(var1);
         this.q.q(var3, this.RF.createOperation(OperationType.SUB, var3, this.RF.createImm(1L, var3.getBitsize())), var1);
         var1.r.add(this.RF.createJump(var1.irAddress, null));
      }
   }

   private void wF(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).lm();
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      int var5 = var3.getBitsize() / 8;
      IECond var6 = this.RF.createCond(this.q.jk, this.RF.createImm(-var5, var2), this.RF.createImm(var5, var2));
      IEGeneric var7 = this.q.mJ.part(var2);
      IEGeneric var8 = this.q.Bs.part(var2);
      this.q.q(var3, var4, var1);
      this.q.q(var7, this.RF.createOperation(OperationType.ADD, var7, var6), var1);
      this.q.q(var8, this.RF.createOperation(OperationType.ADD, var8, var6), var1);
   }

   void PV(ConverterInstructionEntry var1) {
      if (!ctl.q((ctc)var1.insn)) {
         this.If(var1);
      } else {
         int var2 = ((ctc)var1.insn).lm();
         IEGeneric var3 = this.q.iu.part(var2);
         var1.r.add(this.RF.createJump(var1.irAddress + 5, this.RF.createOperation(OperationType.LOG_EQ, var3, this.q.q(var3))));
         this.If(var1);
         this.q.q(var3, this.RF.createOperation(OperationType.SUB, var3, this.RF.createImm(1L, var3.getBitsize())), var1);
         var1.r.add(this.RF.createJump(var1.irAddress, null));
      }
   }

   private void If(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).lm();
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      int var5 = var3.getBitsize() / 8;
      IECond var6 = this.RF.createCond(this.q.jk, this.RF.createImm(-var5, var2), this.RF.createImm(var5, var2));
      IEGeneric var7 = this.q.mJ.part(var2);
      this.q.q(var3, var4, var1);
      this.q.q(var7, this.RF.createOperation(OperationType.ADD, var7, var6), var1);
   }

   void oQ(ConverterInstructionEntry var1) {
      if (!ctl.q((ctc)var1.insn)) {
         this.Dz(var1);
      } else {
         int var2 = ((ctc)var1.insn).lm();
         IEGeneric var3 = this.q.iu.part(var2);
         var1.r.add(this.RF.createJump(var1.irAddress + 5, this.RF.createOperation(OperationType.LOG_EQ, var3, this.q.q(var3))));
         this.Dz(var1);
         this.q.q(var3, this.RF.createOperation(OperationType.SUB, var3, this.RF.createImm(1L, var3.getBitsize())), var1);
         var1.r.add(this.RF.createJump(var1.irAddress, null));
      }
   }

   private void Dz(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).lm();
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      int var5 = var3.getBitsize() / 8;
      IECond var6 = this.RF.createCond(this.q.jk, this.RF.createImm(-var5, var2), this.RF.createImm(var5, var2));
      IEGeneric var7 = this.q.Bs.part(var2);
      this.q.q(var3, var4, var1);
      this.q.q(var7, this.RF.createOperation(OperationType.ADD, var7, var6), var1);
   }

   void xW(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).za[0];
      if (var2 == 243) {
         int var3 = ((ctc)var1.insn).lm();
         IEGeneric var4 = this.q.iu.part(var3);
         var1.r.add(this.RF.createJump(var1.irAddress + 11, this.RF.createOperation(OperationType.LOG_EQ, var4, this.q.q(var4))));
         this.mI(var1);
         this.q.q(var4, this.RF.createOperation(OperationType.SUB, var4, this.RF.createImm(1L, var4.getBitsize())), var1);
         var1.r.add(this.RF.createJump(var1.irAddress, this.q.GC));
      } else if (var2 == 242) {
         int var5 = ((ctc)var1.insn).lm();
         IEGeneric var6 = this.q.iu.part(var5);
         var1.r.add(this.RF.createJump(var1.irAddress + 11, this.RF.createOperation(OperationType.LOG_EQ, var6, this.q.q(var6))));
         this.mI(var1);
         this.q.q(var6, this.RF.createOperation(OperationType.SUB, var6, this.RF.createImm(1L, var6.getBitsize())), var1);
         var1.r.add(this.RF.createJump(var1.irAddress, this.RF.createOperation(OperationType.NOT, this.q.GC)));
      } else {
         this.mI(var1);
      }
   }

   private void mI(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).lm();
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      int var5 = var3.getBitsize() / 8;
      IECond var6 = this.RF.createCond(this.q.jk, this.RF.createImm(-var5, var2), this.RF.createImm(var5, var2));
      IEGeneric var7 = this.q.mJ.part(var2);
      IEGeneric var8 = this.q.Bs.part(var2);
      this.q.Yw.xK(var3, var4, var1.r);
      this.q.q(var7, this.RF.createOperation(OperationType.ADD, var7, var6), var1);
      this.q.q(var8, this.RF.createOperation(OperationType.ADD, var8, var6), var1);
   }

   void KT(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).za[0];
      if (var2 == 243) {
         int var3 = ((ctc)var1.insn).lm();
         IEGeneric var4 = this.q.iu.part(var3);
         var1.r.add(this.RF.createJump(var1.irAddress + 10, this.RF.createOperation(OperationType.LOG_EQ, var4, this.q.q(var4))));
         this.jq(var1);
         this.q.q(var4, this.RF.createOperation(OperationType.SUB, var4, this.RF.createImm(1L, var4.getBitsize())), var1);
         var1.r.add(this.RF.createJump(var1.irAddress, this.q.GC));
      } else if (var2 == 242) {
         int var5 = ((ctc)var1.insn).lm();
         IEGeneric var6 = this.q.iu.part(var5);
         var1.r.add(this.RF.createJump(var1.irAddress + 10, this.RF.createOperation(OperationType.LOG_EQ, var6, this.q.q(var6))));
         this.jq(var1);
         this.q.q(var6, this.RF.createOperation(OperationType.SUB, var6, this.RF.createImm(1L, var6.getBitsize())), var1);
         var1.r.add(this.RF.createJump(var1.irAddress, this.RF.createOperation(OperationType.NOT, this.q.GC)));
      } else {
         this.jq(var1);
      }
   }

   private void jq(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).lm();
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      int var5 = var3.getBitsize() / 8;
      IECond var6 = this.RF.createCond(this.q.jk, this.RF.createImm(-var5, var2), this.RF.createImm(var5, var2));
      IEGeneric var7 = this.q.Bs.part(var2);
      this.q.Yw.xK(var3, var4, var1.r);
      this.q.q(var7, this.RF.createOperation(OperationType.ADD, var7, var6), var1);
   }

   void Gf(ConverterInstructionEntry var1) {
      this.q(var1, true);
   }

   void Ef(ConverterInstructionEntry var1) {
      this.q(var1, false);
   }

   private void q(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      int var5 = var3.getBitsize();
      Assert.a(var3.getBitsize() == var4.getBitsize());
      IEGeneric var6 = this.q.q(var4);
      IEGeneric var7 = this.q.RF(var4);
      IEVar var8 = this.q.q(var5);
      int var9 = var1.irAddress;
      int var10 = var9 + 3;
      int var11 = var9 + 6;
      int var12 = var9 + 8;
      int var13 = var9 + 9;
      if (var2) {
         var1.r.add(this.RF.createJump(var12, EUtil.eq(var4, var6)));
         var1.r.add(this.RF.createAssign(this.q.GC, this.q.Eq));
         var1.r.add(this.RF.createAssign(var8, var6));
         var1.r.add(this.RF.createJump(var11, EUtil.andB(EUtil.shr(var4, var8), var7)));
         var1.r.add(this.RF.createAssign(var8, EUtil.add(var8, var7)));
         var1.r.add(this.RF.createJump(var10));
         var1.r.add(this.RF.createAssign(var3, var8));
         var1.r.add(this.RF.createJump(var13));
         var1.r.add(this.RF.createAssign(this.q.GC, this.q.hP));
      } else {
         var1.r.add(this.RF.createJump(var12, EUtil.eq(var4, var6)));
         var1.r.add(this.RF.createAssign(this.q.GC, this.q.Eq));
         var1.r.add(this.RF.createAssign(var8, EUtil.imm(var5 - 1, var5)));
         var1.r.add(this.RF.createJump(var11, EUtil.shr(var4, var8)));
         var1.r.add(this.RF.createAssign(var8, EUtil.sub(var8, var7)));
         var1.r.add(this.RF.createJump(var10));
         var1.r.add(this.RF.createAssign(var3, var8));
         var1.r.add(this.RF.createJump(var13));
         var1.r.add(this.RF.createAssign(this.q.GC, this.q.hP));
      }
   }

   void cC(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).za();
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      Assert.a(var3.getBitsize() == var2);
      IEGeneric var4 = this.q.RF(var2, var1.r);
      var1.r.add(this.RF.createAssign(var4, var3));
      if (var2 == 64) {
         var1.r.add(this.RF.createAssign(var3.slice(0, 8), var4.slice(56, 64)));
         var1.r.add(this.RF.createAssign(var3.slice(8, 16), var4.slice(48, 56)));
         var1.r.add(this.RF.createAssign(var3.slice(16, 24), var4.slice(40, 48)));
         var1.r.add(this.RF.createAssign(var3.slice(24, 32), var4.slice(32, 40)));
         var1.r.add(this.RF.createAssign(var3.slice(32, 40), var4.slice(24, 32)));
         var1.r.add(this.RF.createAssign(var3.slice(40, 48), var4.slice(16, 24)));
         var1.r.add(this.RF.createAssign(var3.slice(48, 56), var4.slice(8, 16)));
         var1.r.add(this.RF.createAssign(var3.slice(56, 64), var4.slice(0, 8)));
      } else if (var2 == 32) {
         var1.r.add(this.RF.createAssign(var3.slice(0, 8), var4.slice(24, 32)));
         var1.r.add(this.RF.createAssign(var3.slice(8, 16), var4.slice(16, 24)));
         var1.r.add(this.RF.createAssign(var3.slice(16, 24), var4.slice(8, 16)));
         var1.r.add(this.RF.createAssign(var3.slice(24, 32), var4.slice(0, 8)));
         if (((ctc)var1.insn).gO == 64
            && var3 instanceof IESlice
            && ((IESlice)var3).getBitStart() == 0
            && var3.getBitsize() == 32
            && ((IESlice)var3).getWholeExpression() instanceof IEVar) {
            IEVar var5 = (IEVar)((IESlice)var3).getWholeExpression();
            if (var5.getId() >= 0 && var5.getId() < 1024) {
               var1.r.add(this.RF.createAssign(var5.slice(32, 64), this.q.Oj));
            }
         }
      }
   }

   void sH(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      Assert.a(var2.getBitsize() == 8);
      int var4 = ((ctc)var1.insn).io.RF & 14;
      boolean var5 = (((ctc)var1.insn).io.RF & 1) != 0;

      Object var3 = switch (var4) {
         case 0 -> this.q.Cl;
         default -> throw new RuntimeException();
         case 2 -> this.q.WX;
         case 4 -> this.q.GC;
         case 6 -> this.RF.createOperation(OperationType.LOG_OR, this.q.WX, this.q.GC);
         case 8 -> this.q.KF;
         case 10 -> this.q.CB;
         case 12 -> this.RF.createOperation(OperationType.LOG_NEQ, this.q.KF, this.q.Cl);
         case 14 -> this.RF.createOperation(OperationType.LOG_OR, this.q.GC, this.RF.createOperation(OperationType.LOG_NEQ, this.q.KF, this.q.Cl));
      };
      IECond var6 = !var5 ? this.RF.createCond((IEGeneric)var3, this.q.hw, this.q.dg) : this.RF.createCond((IEGeneric)var3, this.q.dg, this.q.hw);
      var1.r.add(this.RF.createAssign(var2, var6));
   }

   void CE(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      Assert.a(var2.getBitsize() == var3.getBitsize());
      int var5 = ((ctc)var1.insn).io.RF & 14;
      boolean var6 = (((ctc)var1.insn).io.RF & 1) != 0;

      Object var4 = switch (var5) {
         case 0 -> this.q.Cl;
         default -> throw new RuntimeException();
         case 2 -> this.q.WX;
         case 4 -> this.q.GC;
         case 6 -> this.RF.createOperation(OperationType.LOG_OR, this.q.WX, this.q.GC);
         case 8 -> this.q.KF;
         case 10 -> this.q.CB;
         case 12 -> this.RF.createOperation(OperationType.LOG_NEQ, this.q.KF, this.q.Cl);
         case 14 -> this.RF.createOperation(OperationType.LOG_OR, this.q.GC, this.RF.createOperation(OperationType.LOG_NEQ, this.q.KF, this.q.Cl));
      };
      IECond var7 = !var6 ? this.RF.createCond((IEGeneric)var4, var3, var2) : this.RF.createCond((IEGeneric)var4, var2, var3);
      this.q.q(var2, var7, var1);
   }
}
