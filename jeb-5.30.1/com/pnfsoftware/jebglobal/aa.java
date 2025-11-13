package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import java.util.List;

public class aa {
   uq q;
   IERoutineContext RF;
   boolean xK = true;

   public aa(uq var1) {
      this.q = var1;
   }

   public void q(boolean var1) {
      this.xK = var1;
   }

   public void q(fA var1, List var2, long var3) {
      long var5 = var1.RF()[0].getOperandValue(var3);
      IEImm var7 = this.q.q(var5);
      IEAssign var8 = this.q(var1, var2, var3, (IEGeneric)var7);
      this.q(var2, var8);
   }

   private void q(List var1, IEAssign var2) {
      var1.add(var2);
   }

   public void RF(fA var1, List var2, long var3) {
      IEGeneric var5 = this.q.q(var1, var1.RF()[0], this.q.JF, var3);
      this.q(var2, var5);
   }

   private IEGeneric q(fA var1, List var2, long var3, IEImm var5) {
      CW var6 = var1.RF()[0];
      if (GC.q(var6, this.q.JF)) {
         IEVar var7 = this.q.gO(var5.getBitsize());
         var2.add(this.RF.createAssign(var7, this.q.q()));
         var2.add(this.RF.createAssign(this.q.q(), var5));
         return var7;
      } else {
         var2.add(this.RF.createAssign(this.q.q(), var5));
         return this.q.q(var1, var6, this.q.JF, var3);
      }
   }

   public void xK(fA var1, List var2, long var3) {
      long var5 = var3;
      if (var1.getProcessorMode() == 16) {
         var5 = var3 | 1L;
      }

      var5 += var1.getSize();
      var2.add(this.RF.createAssign(this.q.q(), this.RF.createImm(var5, this.q.q().getBitsize())));
      long var7 = var1.RF()[0].getOperandValue(var3);
      IEImm var9 = this.q.q(var7);
      var2.add(this.q(var1, var2, var3, (IEGeneric)var9));
   }

   public void Dw(fA var1, List var2, long var3) {
      IEImm var5 = this.RF.createImm(var3 + var1.getSize(), this.q.q().getBitsize());
      IEGeneric var6 = this.q(var1, var2, var3, var5);
      var2.add(this.q(var1, var3, var6));
   }

   public void Uv(fA var1, List var2, long var3) {
      IEGeneric var5 = this.q.q(var1, var1.RF()[0], this.q.JF, var3);
      var2.add(this.q(var1, var3, var5));
   }

   public void oW(fA var1, List var2, long var3) {
      if (var1.RF()[0].getOperandType() != 0) {
         if (var1.q().xK()) {
            var2.add(this.RF.createAssign(this.q.fe, this.q.SM));
         } else if (var1.q().Dw()) {
            var2.add(this.RF.createAssign(this.q.fe, this.q.bj));
         }

         this.xK(var1, var2, var3);
      } else {
         IEImm var5;
         if (var1.getProcessorMode() == 32) {
            var5 = this.q.q(var3 + 4L);
         } else {
            var5 = this.q.q(var3 + 2L + 1L);
         }

         IEGeneric var6 = this.q(var1, var2, var3, var5);
         this.q(var2, var3, var6, true);
      }
   }

   public void q(List var1, IEGeneric var2) {
      this.q(var1, -1L, var2, false);
   }

   public void q(List var1, long var2, IEGeneric var4, boolean var5) {
      var1.add(this.RF.createAssign(this.q.fe, ((IEGeneric)var4).duplicate().bit(0)));
      if (this.xK) {
         if (var4 instanceof alu) {
            var4 = ((alu)var4)._testbit(0) ? this.RF((IEGeneric)var4) : this.xK((IEGeneric)var4);
         } else {
            var4 = VQ.q((ane)this.q((IEGeneric)var4), (ane)var4);
         }
      }

      var1.add(this.q(var2, (IEGeneric)var4, var5));
   }

   public IEAssign q(fA var1, List var2, long var3, IEGeneric var5) {
      if (var1.getProcessorMode() == 16) {
         var2.add(this.RF.createAssign(this.q.fe, this.q.bj));
      }

      if (this.xK) {
         if (var1.getProcessorMode() == 32) {
            var5 = VQ.q((ane)this.xK((IEGeneric)var5), (ane)var5);
         } else if (var1.getProcessorMode() == 16) {
            var5 = VQ.q((ane)this.RF((IEGeneric)var5), (ane)var5);
         }
      }

      return this.q(var1, var3, (IEGeneric)var5);
   }

   private IEGeneric q(IEGeneric var1) {
      return this.RF.createCond(var1.part(1), this.RF(var1.duplicate()), this.xK(var1.duplicate()));
   }

   private IEGeneric RF(IEGeneric var1) {
      return (IEGeneric)(var1 instanceof alu ? ((alu)var1)._shr(1)._shl(1) : this.RF.createCompose(this.q.SM, var1.slice(1, var1.getBitsize())));
   }

   private IEGeneric xK(IEGeneric var1) {
      return (IEGeneric)(var1 instanceof alu ? ((alu)var1)._shr(2)._shl(2) : this.RF.createCompose(this.q.Uv(2), var1.slice(2, var1.getBitsize())));
   }

   private IEAssign q(fA var1, long var2, IEGeneric var4) {
      if (var1.getProcessorMode() == 64) {
         var4 = var4.zeroExtend(this.q.JF);
         return this.q(var2, var4, var1.q().getBranchType() == IInsnEmulator.BranchType.CALL);
      } else {
         var4 = var4.zeroExtend(this.q.JF);
         return this.q(var2, var4, var1.q().getBranchType() == IInsnEmulator.BranchType.CALL);
      }
   }

   public void gO(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, false);
   }

   public void nf(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, true);
   }

   private void RF(fA var1, List var2, long var3, boolean var5) {
      IEVar var6 = this.q.getProgramCounter();
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], -1L);
      IEGeneric var8 = this.q.q(var1, var1.RF()[1], this.q.JF, var3);
      IEOperation var9 = var5 ? EUtil.ne(var7, this.q.Uv(var7.getBitsize())) : EUtil.eq(var7, this.q.Uv(var7.getBitsize()));
      IEAssign var10 = this.q(var1, var2, var3, var8);
      IEImm var11 = this.RF.createImm(var3 + var1.getSize(), var6.getBitsize());
      var2.add(
         this.RF.createBranchAssign(var6, this.RF.createCond(var9, var10.getSrcOperand(), var11), var1.q().getBranchType() == IInsnEmulator.BranchType.CALL)
      );
   }

   public void gP(fA var1, List var2, long var3) {
      Object var5;
      if (var1.RF() != null && var1.RF().length != 0) {
         var5 = this.q.q(var1, var1.RF()[0], this.q.JF, var3);
      } else {
         var5 = this.q.q();
      }

      var2.add(this.q(var1, var3, (IEGeneric)var5));
   }

   public void za(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, false);
   }

   public void lm(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true);
   }

   public void q(fA var1, List var2, long var3, boolean var5) {
      IEVar var6 = this.q.getProgramCounter();
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
      int var8 = (int)var1.RF()[1].getOperandValue();
      IEGeneric var9 = var7.bit(var8);
      IEGeneric var10 = this.q.q(var1, var1.RF()[2], this.q.JF, var3);
      IEOperation var11 = var5 ? EUtil.ne(var9, this.q.SM) : EUtil.eq(var9, this.q.SM);
      IEAssign var12 = this.q(var1, var3, var10);
      IEImm var13 = this.RF.createImm(var3 + var1.getSize(), var6.getBitsize());
      var2.add(
         this.RF.createBranchAssign(var6, this.RF.createCond(var11, var12.getSrcOperand(), var13), var1.q().getBranchType() == IInsnEmulator.BranchType.CALL)
      );
   }

   public void zz(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, 8);
   }

   public void JY(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, 16);
   }

   public void q(fA var1, List var2, long var3, int var5) {
      CW[] var6 = var1.RF()[0].oW();
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)var6[0], var3);
      IEGeneric var8 = this.q.q(var1, (IInstructionOperand)var6[1], var3);
      IEGeneric var9 = this.RF.createMem(EUtil.add(var7, var8), var5).zeroExtend(this.q.JF);
      IEAssign var10 = this.q(var1, var2, var3, this.q.q(var1, var3, var9.leftShift(1)));
      var2.add(var10);
   }

   private IEAssign q(long var1, IEGeneric var3, boolean var4) {
      return this.RF.createBranchAssign(this.q.getProgramCounter(), var3, var4);
   }
}
