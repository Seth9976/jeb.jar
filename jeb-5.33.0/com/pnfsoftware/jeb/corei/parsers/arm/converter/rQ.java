package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import com.pnfsoftware.jebglobal.LC;
import com.pnfsoftware.jebglobal.Yg;
import com.pnfsoftware.jebglobal.ajr;
import com.pnfsoftware.jebglobal.ala;
import com.pnfsoftware.jebglobal.yP;
import java.util.List;

public class rQ {
   HE pC;
   IERoutineContext A;
   boolean kS = true;

   public rQ(HE var1) {
      this.pC = var1;
   }

   public void pC(boolean var1) {
      this.kS = var1;
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      long var5 = var1.A()[0].getOperandValue(var3);
      IEImm var7 = this.pC.pC(var5);
      IEAssign var8 = this.pC(var1, var2, var3, (IEGeneric)var7);
      this.pC(var2, var8);
   }

   private void pC(List var1, IEAssign var2) {
      var1.add(var2);
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      IEGeneric var5 = this.pC.pC(var1, var1.A()[0], this.pC.fI, var3);
      this.pC(var2, var5);
   }

   private IEGeneric pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, IEImm var5) {
      Yg var6 = var1.A()[0];
      if (LC.pC(var6, this.pC.fI)) {
         IEVar var7 = this.pC.E(var5.getBitsize());
         var2.add(this.A.createAssign(var7, this.pC.pC()));
         var2.add(this.A.createAssign(this.pC.pC(), var5));
         return var7;
      } else {
         var2.add(this.A.createAssign(this.pC.pC(), var5));
         return this.pC.pC(var1, var6, this.pC.fI, var3);
      }
   }

   public void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      long var5 = var3;
      if (var1.getProcessorMode() == 16) {
         var5 = var3 | 1L;
      }

      var5 += var1.getSize();
      var2.add(this.A.createAssign(this.pC.pC(), this.A.createImm(var5, this.pC.pC().getBitsize())));
      long var7 = var1.A()[0].getOperandValue(var3);
      IEImm var9 = this.pC.pC(var7);
      var2.add(this.pC(var1, var2, var3, (IEGeneric)var9));
   }

   public void wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      IEImm var5 = this.A.createImm(var3 + var1.getSize(), this.pC.pC().getBitsize());
      IEGeneric var6 = this.pC(var1, var2, var3, var5);
      var2.add(this.pC(var1, var3, var6));
   }

   public void UT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      IEGeneric var5 = this.pC.pC(var1, var1.A()[0], this.pC.fI, var3);
      var2.add(this.pC(var1, var3, var5));
   }

   public void E(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      if (var1.A()[0].getOperandType() != 0) {
         if (var1.pC().kS()) {
            var2.add(this.A.createAssign(this.pC.rl, this.pC.FE));
         } else if (var1.pC().wS()) {
            var2.add(this.A.createAssign(this.pC.rl, this.pC.Aj));
         }

         this.kS(var1, var2, var3);
      } else {
         IEImm var5;
         if (var1.getProcessorMode() == 32) {
            var5 = this.pC.pC(var3 + 4L);
         } else {
            var5 = this.pC.pC(var3 + 2L + 1L);
         }

         IEGeneric var6 = this.pC(var1, var2, var3, var5);
         this.pC(var2, var3, var6, true);
      }
   }

   public void pC(List var1, IEGeneric var2) {
      this.pC(var1, -1L, var2, false);
   }

   public void pC(List var1, long var2, IEGeneric var4, boolean var5) {
      var1.add(this.A.createAssign(this.pC.rl, ((IEGeneric)var4).duplicate().bit(0)));
      if (this.kS) {
         if (var4 instanceof ajr) {
            var4 = ((ajr)var4)._testbit(0) ? this.A((IEGeneric)var4) : this.kS((IEGeneric)var4);
         } else {
            var4 = yP.pC((ala)this.pC((IEGeneric)var4), (ala)var4);
         }
      }

      var1.add(this.pC(var2, (IEGeneric)var4, var5));
   }

   public IEAssign pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, IEGeneric var5) {
      if (var1.getProcessorMode() == 16) {
         var2.add(this.A.createAssign(this.pC.rl, this.pC.Aj));
      }

      if (this.kS) {
         if (var1.getProcessorMode() == 32) {
            var5 = yP.pC((ala)this.kS((IEGeneric)var5), (ala)var5);
         } else if (var1.getProcessorMode() == 16) {
            var5 = yP.pC((ala)this.A((IEGeneric)var5), (ala)var5);
         }
      }

      return this.pC(var1, var3, (IEGeneric)var5);
   }

   private IEGeneric pC(IEGeneric var1) {
      return this.A.createCond(var1.part(1), this.A(var1.duplicate()), this.kS(var1.duplicate()));
   }

   private IEGeneric A(IEGeneric var1) {
      return (IEGeneric)(var1 instanceof ajr ? ((ajr)var1)._shr(1)._shl(1) : this.A.createCompose(this.pC.FE, var1.slice(1, var1.getBitsize())));
   }

   private IEGeneric kS(IEGeneric var1) {
      return (IEGeneric)(var1 instanceof ajr ? ((ajr)var1)._shr(2)._shl(2) : this.A.createCompose(this.pC.wS(2), var1.slice(2, var1.getBitsize())));
   }

   private IEAssign pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, IEGeneric var4) {
      if (var1.getProcessorMode() == 64) {
         var4 = var4.zeroExtend(this.pC.fI);
         return this.pC(var2, var4, var1.pC().getBranchType() == IInsnEmulator.BranchType.CALL);
      } else {
         var4 = var4.zeroExtend(this.pC.fI);
         return this.pC(var2, var4, var1.pC().getBranchType() == IInsnEmulator.BranchType.CALL);
      }
   }

   public void sY(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, false);
   }

   public void ys(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, true);
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5) {
      IEVar var6 = this.pC.getProgramCounter();
      IEGeneric var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], -1L);
      IEGeneric var8 = this.pC.pC(var1, var1.A()[1], this.pC.fI, var3);
      IEOperation var9 = var5 ? EUtil.ne(var7, this.pC.wS(var7.getBitsize())) : EUtil.eq(var7, this.pC.wS(var7.getBitsize()));
      IEAssign var10 = this.pC(var1, var2, var3, var8);
      IEImm var11 = this.A.createImm(var3 + var1.getSize(), var6.getBitsize());
      var2.add(
         this.A.createBranchAssign(var6, this.A.createCond(var9, var10.getSrcOperand(), var11), var1.pC().getBranchType() == IInsnEmulator.BranchType.CALL)
      );
   }

   public void ld(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      Object var5;
      if (var1.A() != null && var1.A().length != 0) {
         var5 = this.pC.pC(var1, var1.A()[0], this.pC.fI, var3);
      } else {
         var5 = this.pC.pC();
      }

      var2.add(this.pC(var1, var3, (IEGeneric)var5));
   }

   public void gp(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, false);
   }

   public void oT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true);
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5) {
      IEVar var6 = this.pC.getProgramCounter();
      IEGeneric var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
      int var8 = (int)var1.A()[1].getOperandValue();
      IEGeneric var9 = var7.bit(var8);
      IEGeneric var10 = this.pC.pC(var1, var1.A()[2], this.pC.fI, var3);
      IEOperation var11 = var5 ? EUtil.ne(var9, this.pC.FE) : EUtil.eq(var9, this.pC.FE);
      IEAssign var12 = this.pC(var1, var3, var10);
      IEImm var13 = this.A.createImm(var3 + var1.getSize(), var6.getBitsize());
      var2.add(
         this.A.createBranchAssign(var6, this.A.createCond(var11, var12.getSrcOperand(), var13), var1.pC().getBranchType() == IInsnEmulator.BranchType.CALL)
      );
   }

   public void fI(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, 8);
   }

   public void WR(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, 16);
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, int var5) {
      Yg[] var6 = var1.A()[0].E();
      IEGeneric var7 = this.pC.pC(var1, (IInstructionOperand)var6[0], var3);
      IEGeneric var8 = this.pC.pC(var1, (IInstructionOperand)var6[1], var3);
      IEGeneric var9 = this.A.createMem(EUtil.add(var7, var8), var5).zeroExtend(this.pC.fI);
      IEAssign var10 = this.pC(var1, var2, var3, this.pC.pC(var1, var3, var9.leftShift(1)));
      var2.add(var10);
   }

   private IEAssign pC(long var1, IEGeneric var3, boolean var4) {
      return this.A.createBranchAssign(this.pC.getProgramCounter(), var3, var4);
   }
}
