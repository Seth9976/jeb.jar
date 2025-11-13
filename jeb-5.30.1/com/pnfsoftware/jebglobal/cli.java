package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.math.MathUtil;
import java.util.ArrayList;
import java.util.List;

public class cli {
   cln q;
   IERoutineContext RF;

   public cli(cln var1) {
      this.q = var1;
   }

   public void q(cmd var1, List var2, long var3) {
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1], var3);
      var2.add(this.RF.createAssign(var5, var6.signExtend(var5.getBitsize())));
   }

   public void q(cmd var1, List var2, OperationType var3, boolean var4) {
      this.q(var1, var2, var3, var4, 32);
   }

   public void q(cmd var1, List var2, OperationType var3, boolean var4, long var5) {
      if (this.q.Qt != 64) {
         var2.add(this.q.q(var1, var5));
      } else {
         this.q(var1, var2, var3, var4, 64);
      }
   }

   public void q(cmd var1, List var2, OperationType var3, boolean var4, int var5) {
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(var5);
      IEGeneric var8 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]);
      IEOperation var9 = this.RF.createOperation(var3, var7, EUtil.safeExtend(var8, var5, var4));
      var2.add(this.RF.createAssign(var6, var9.signExtend(var6.getBitsize())));
   }

   public void q(cmd var1, List var2, OperationType var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      IEOperation var6 = this.RF.createOperation(var3, var5);
      var2.add(this.RF.createAssign(var4, var6.signExtend(var4.getBitsize())));
   }

   public void q(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      var2.add(this.RF.createAssign(var3, var4.countSuccessiveBits(true, true, var3.getBitsize())));
   }

   public void RF(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      var2.add(this.RF.createAssign(var3, var4.countSuccessiveBits(false, true, var3.getBitsize())));
   }

   public void q(cmd var1, List var2, boolean var3) {
      if (((clv[])var1.getOperands()).length == 2) {
         IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]).part(32);
         IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
         var2.add(
            this.RF
               .createAssign(
                  this.q.iO, this.RF.createOperation(var3 ? OperationType.DIV_S : OperationType.DIV_U, var4, var5).signExtend(this.q.iO.getBitsize())
               )
         );
         var2.add(
            this.RF
               .createAssign(
                  this.q.Qe, this.RF.createOperation(var3 ? OperationType.REM_S : OperationType.REM_U, var4, var5).signExtend(this.q.Qe.getBitsize())
               )
         );
      } else if (((clv[])var1.getOperands()).length == 3) {
         IEGeneric var7 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
         IEGeneric var8 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
         IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]).part(32);
         var2.add(
            this.RF.createAssign(var7, this.RF.createOperation(var3 ? OperationType.DIV_S : OperationType.DIV_U, var8, var6).signExtend(var7.getBitsize()))
         );
      }
   }

   public void RF(cmd var1, List var2, boolean var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]).part(32);
      var2.add(this.RF.createAssign(var4, this.RF.createOperation(var3 ? OperationType.REM_S : OperationType.REM_U, var5, var6).signExtend(var4.getBitsize())));
   }

   public void xK(cmd var1, List var2, boolean var3) {
      IEGeneric var4;
      IEGeneric var5;
      IEVar var6;
      IEVar var7;
      if (((clv[])var1.getOperands()).length == 3) {
         var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
         var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]).part(32);
         int var8 = RegisterUtil.getRegisterIndex(((clv[])var1.getOperands())[0].getOperandValue());
         var6 = this.q.Dw(var8);
         var7 = this.q.Uv(var8);
      } else {
         var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]).part(32);
         var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
         var6 = this.q.Dw(0);
         var7 = this.q.Uv(0);
      }

      IEOperation var9 = this.RF.createOperation(OperationType.MUL, var4, var5);
      var2.add(this.RF.createAssign(var6, var9.signExtend(var6.getBitsize())));
      var2.add(this.RF.createAssign(var7, this.q(var3, var4, var5).slice(32, 64).signExtend(var7.getBitsize())));
   }

   public void Dw(cmd var1, List var2, boolean var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]).part(32);
      IEOperation var7 = this.RF.createOperation(OperationType.MUL, var5, var6);
      var2.add(this.RF.createAssign(var4, var7.signExtend(var4.getBitsize())));
   }

   public void Uv(cmd var1, List var2, boolean var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]).part(32);
      IEGeneric var7 = this.q(var3, var5, var6);
      var2.add(this.RF.createAssign(var4, var7.slice(32, 64).signExtend(var4.getBitsize())));
   }

   public void oW(cmd var1, List var2, boolean var3) {
      this.q(var1, var2, var3, OperationType.ADD);
   }

   public void gO(cmd var1, List var2, boolean var3) {
      this.q(var1, var2, var3, OperationType.SUB);
   }

   public void q(cmd var1, List var2, boolean var3, OperationType var4) {
      IEGeneric var5;
      IEGeneric var6;
      IEVar var7;
      IEVar var8;
      if (((clv[])var1.getOperands()).length == 3) {
         var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
         var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]).part(32);
         int var9 = RegisterUtil.getRegisterIndex(((clv[])var1.getOperands())[0].getOperandValue());
         var7 = this.q.Dw(var9);
         var8 = this.q.Uv(var9);
      } else {
         var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]).part(32);
         var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
         var7 = this.q.Dw(0);
         var8 = this.q.Uv(0);
      }

      IECompose var13 = this.RF.createCompose(var7.part(32), var8.part(32));
      IEGeneric var10 = this.q(var3, var5, var6);
      IEOperation var11 = this.RF.createOperation(var4, var13, var10);
      var2.add(this.RF.createAssign(this.q.HK, var11.slice(32, 64)));
      IEOperation var12 = this.RF.createOperation(OperationType.MUL, var5, var6);
      var2.add(this.RF.createAssign(var7, this.RF.createOperation(var4, var7.part(32), var12).signExtend(var7.getBitsize())));
      var2.add(this.RF.createAssign(var8, this.q.HK.signExtend(var8.getBitsize())));
   }

   private IEGeneric q(boolean var1, IEGeneric var2, IEGeneric var3) {
      return var1
         ? this.RF.createOperation(OperationType.MUL, var2.signExtend(64), var3.signExtend(64))
         : this.RF.createOperation(OperationType.MUL, var2.zeroExtend(64), var3.zeroExtend(64));
   }

   public void RF(cmd var1, List var2, long var3) {
      if (this.q.Qt != 64) {
         var2.add(this.q.q(var1, var3));
      } else {
         this.q(var1, var2, 64);
      }
   }

   public void q(cmd var1, List var2, int var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]);
      int var6 = (int)((clv[])var1.getOperands())[2].getOperandValue();
      int var7 = (int)((clv[])var1.getOperands())[3].getOperandValue();
      IEGeneric var8 = var5.slice(var6, var6 + var7).zeroExtend(var3);
      var2.add(this.RF.createAssign(var4, var8.signExtend(var4.getBitsize())));
   }

   public void xK(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]);
      int var5 = (int)((clv[])var1.getOperands())[2].getOperandValue();
      int var6 = (int)((clv[])var1.getOperands())[3].getOperandValue();
      ArrayList var7 = new ArrayList();
      if (var5 != 0) {
         var7.add(var3.slice(0, var5));
      }

      var7.add(var4.slice(0, var6));
      if (var5 + var6 < 32) {
         var7.add(var3.slice(var5 + var6, 32));
      }

      IEGeneric var8 = EUtil.compose(this.RF, var7);
      var2.add(this.RF.createAssign(var3, var8.signExtend(var3.getBitsize())));
   }

   public void Dw(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]);
      var2.add(this.RF.createAssign(var3, var4.leftShift(16, 32).signExtend(var3.getBitsize())));
   }

   public void RF(cmd var1, List var2, OperationType var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]);
      IEOperation var7 = this.RF.createOperation(var3, var6, this.q.Kl.zeroExtend(var6.getBitsize()));
      var2.add(this.RF.createAssign(var4, this.RF.createCond(var7, var5, var4)));
   }

   public void Uv(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]).part(32);
      var2.add(this.RF.createAssign(var3, EUtil.andB(EUtil.notB(var4), EUtil.notB(var5)).signExtend(var3.getBitsize())));
   }

   public void oW(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      var2.add(this.RF.createAssign(var3, EUtil.sub(this.q.Kl, var4).signExtend(var3.getBitsize())));
   }

   public void q(cmd var1, List var2, OperationType var3, int var4) {
      this.q(var1, var2, var3, var4, 0);
   }

   public void q(cmd var1, List var2, long var3, OperationType var5, int var6) {
      if (this.q.Qt != 64) {
         var2.add(this.q.q(var1, var3));
      } else {
         this.q(var1, var2, var5, 64, var6);
      }
   }

   private void q(cmd var1, List var2, OperationType var3, int var4, int var5) {
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(var4);
      Object var8;
      if (((clv[])var1.getOperands())[2].getOperandType() == 1) {
         var8 = this.RF.createImm(((clv[])var1.getOperands())[2].getOperandValue() + var5, var4);
      } else {
         var8 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2], 0L).part(var4 == 32 ? 5 : 6).zeroExtend(var4);
         if (var5 != 0) {
            var8 = EUtil.add((IEGeneric)var8, this.RF.createImm(var5, var4));
         }
      }

      var2.add(this.RF.createAssign(var6, this.RF.createOperation(var3, var7, (IEGeneric)var8).signExtend(var6.getBitsize())));
   }

   public void RF(cmd var1, List var2, int var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]);
      var2.add(this.RF.createAssign(var4, var5.part(var3).signExtend(var4.getBitsize())));
   }

   public void gO(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]);
      IECompose var5 = this.RF.createCompose(var4.slice(8, 16), var4.part(8), var4.slice(24, 32), var4.slice(16, 24));
      var2.add(this.RF.createAssign(var3, var5.signExtend(var3.getBitsize())));
   }

   public void nf(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]).part(32);
      int var6 = (int)((clv[])var1.getOperands())[3].getOperandValue();
      IEGeneric var7 = var4.leftShift(var6);
      var2.add(this.RF.createAssign(var3, EUtil.add(var7, var5).signExtend(var3.getBitsize())));
   }

   public void gP(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]);
      IEOperation var6 = EUtil.add(var4, var5.leftShift(16, 32));
      var2.add(this.RF.createAssign(var3, var6.signExtend(var3.getBitsize())));
   }

   public void xK(cmd var1, List var2, long var3) {
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]);
      IEGeneric var7 = this.q.q(var3);
      IEOperation var8 = EUtil.add(var7, var6.leftShift(16, var7.getBitsize()));
      var2.add(this.RF.createAssign(var5, var8));
   }

   public void Dw(cmd var1, List var2, long var3) {
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      long var6 = -65536L & var3 + MathUtil.signExtend(((clv[])var1.getOperands())[1].getOperandValue(var3) << 16, 32);
      var2.add(this.RF.createAssign(var5, this.RF.createImm(var6, var5.getBitsize())));
   }

   public void Uv(cmd var1, List var2, long var3) {
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]).part(32);
      int var8 = this.q.q(var1, ((clv[])var1.getOperands())[3], var3) * 8;
      IEGeneric var9 = this.RF.createCompose(var6, var7).slice(32 - var8, 64 - var8);
      var2.add(this.RF.createAssign(var5, var9.signExtend(var5.getBitsize())));
   }

   public void za(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      ArrayList var5 = new ArrayList();

      for (int var6 = 0; var6 < 4; var6++) {
         for (int var7 = 7; var7 >= 0; var7--) {
            int var8 = var6 * 8 + var7;
            var5.add(var4.slice(var8, var8 + 1));
         }
      }

      var2.add(this.RF.createAssign(var3, EUtil.compose(this.RF, var5).signExtend(var3.getBitsize())));
   }
}
