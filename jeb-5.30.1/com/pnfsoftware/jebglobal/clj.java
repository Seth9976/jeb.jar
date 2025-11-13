package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import java.util.List;

public class clj {
   cln q;
   IERoutineContext RF;

   public clj(cln var1) {
      this.q = var1;
   }

   public void q(cmd var1, List var2, long var3) {
      if (var1.RF().getBranchType() == IInsnEmulator.BranchType.CALL) {
         var2.add(this.RF.createAssign(this.q.Jh, this.q.q(var3, var1.RF().q() ? 8 : 4)));
      }

      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0], var3).zeroExtend(this.q.Xz.getBitsize());
      if (var5 instanceof IEImm) {
         var2.add(this.RF.createBranchAssign(this.q.Xz, var5, var1.RF().getBranchType() == IInsnEmulator.BranchType.CALL));
      } else {
         var2.add(this.RF.createAssign(this.q.So, var5));
         var2.add(this.RF.createBranchAssign(this.q.Xz, this.q.So, var1.RF().getBranchType() == IInsnEmulator.BranchType.CALL));
      }
   }

   public void RF(cmd var1, List var2, long var3) {
      if (((clv[])var1.getOperands()).length == 1) {
         this.q(var1, var2, var3);
      } else {
         IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0], var3);
         IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1], var3);
         var2.add(this.RF.createAssign(var5, this.q.q(var3, 8)));
         var2.add(this.RF.createAssign(this.q.So, var6));
         var2.add(this.RF.createBranchAssign(this.q.Xz, this.q.So, var1.RF().getBranchType() == IInsnEmulator.BranchType.CALL));
      }
   }

   public void xK(cmd var1, List var2, long var3) {
      if (var1.RF().getBranchType() == IInsnEmulator.BranchType.CALL) {
         var2.add(this.RF.createAssign(this.q.Jh, this.q.q(var3, var1.RF().q() ? 8 : 4)));
      }

      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0], var3);
      Object var6 = var5;
      if (((clv[])var1.getOperands()).length > 1) {
         IEImm var7 = this.RF.createImm(((clv[])var1.getOperands())[1].getOperandValue(), var5.getBitsize());
         var6 = EUtil.add(var5, var7);
      }

      var2.add(this.RF.createAssign(this.q.So, (IEGeneric)var6));
      var2.add(this.RF.createBranchAssign(this.q.Xz, this.q.So, var1.RF().getBranchType() == IInsnEmulator.BranchType.CALL));
   }

   public void Dw(cmd var1, List var2, long var3) {
      var2.add(this.RF.createAssign(this.q.Jh, this.q.q(var3, 8)));
   }

   public void q(cmd var1, List var2, long var3, int var5) {
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0], var3);
      Object var7;
      if (((clv[])var1.getOperands()).length == 2) {
         var7 = this.q.Kl.zeroExtend(var6.getBitsize());
      } else {
         var7 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1], var3);
      }

      IEGeneric var8 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[((clv[])var1.getOperands()).length - 1], var3);
      IEGeneric var9 = this.q(var1.oW(), var6, (IEGeneric)var7);
      IEGeneric var10 = this.q.q(var3, var5);
      this.q(var1, var2, var9, var8.zeroExtend(var6.getBitsize()), var10);
   }

   private void q(cmd var1, List var2, IEGeneric var3, IEGeneric var4, IEGeneric var5) {
      if (var1.RF().getBranchType() == IInsnEmulator.BranchType.CALL) {
         var2.add(this.RF.createAssign(this.q.Jh, var5));
      }

      var2.add(this.RF.createAssign(this.q.AG, var3));
      if (!(var4 instanceof IEImm)) {
         var2.add(this.RF.createAssign(this.q.So, (IEGeneric)var4));
         var4 = this.q.So;
      }

      var2.add(
         this.RF
            .createBranchAssign(this.q.Xz, this.RF.createCond(this.q.AG, (IEGeneric)var4, var5), var1.RF().getBranchType() == IInsnEmulator.BranchType.CALL)
      );
   }

   private IEGeneric q(cmb var1, IEGeneric var2, IEGeneric var3) {
      switch (var1) {
         case zz:
            return this.q(var2, var3);
         case JY:
            return EUtil.notL(this.q(var2, var3));
         default:
            return this.RF.createOperation(this.q(var1), var2, var3);
      }
   }

   private IEGeneric q(IEGeneric var1, IEGeneric var2) {
      var1 = var1.part(32);
      var2 = var2.part(32);
      int var3 = var1.getBitsize() + 1;
      IEOperation var4 = EUtil.add(var1.signExtend(var3), var2.signExtend(var3));
      return this.RF.createOperation(OperationType.LOG_NEQ, var4.msb(), var4.duplicate().bit(var1.getBitsize() - 1));
   }

   private OperationType q(cmb var1) {
      switch (var1) {
         case RF:
            return OperationType.LOG_EQ;
         case xK:
            return OperationType.LOG_NEQ;
         case gO:
            return OperationType.GE_S;
         case oW:
            return OperationType.GT_S;
         case Uv:
            return OperationType.LE_S;
         case Dw:
            return OperationType.LT_S;
         case lm:
            return OperationType.GE_U;
         case za:
            return OperationType.GT_U;
         case gP:
            return OperationType.LE_U;
         case nf:
            return OperationType.LT_U;
         default:
            throw new UnsupportedConversionException("Can not evaluate condition " + var1);
      }
   }

   public void q(cmd var1, List var2, long var3, boolean var5) {
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[((clv[])var1.getOperands()).length - 1], var3);
      Object var7 = ((clv[])var1.getOperands()).length == 2 ? this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]) : this.q.er[0];
      Object var8 = var5 ? var7 : EUtil.notL((IEGeneric)var7);
      IEGeneric var9 = this.q.q(var3, 8);
      this.q(var1, var2, (IEGeneric)var8, var6, var9);
   }

   public void RF(cmd var1, List var2, long var3, boolean var5) {
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[((clv[])var1.getOperands()).length - 1], var3);
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEImm var8;
      if (var7.getBitsize() == 32) {
         var8 = this.RF.createImm(0.0F);
      } else {
         var8 = this.RF.createImm(0.0);
      }

      IEOperation var9 = this.RF.createOperation(var5 ? OperationType.FEQ : OperationType.FNE, var7, var8);
      IEGeneric var10 = this.q.q(var3, 8);
      this.q(var1, var2, var9, var6, var10);
   }

   public void Uv(cmd var1, List var2, long var3) {
   }

   public void oW(cmd var1, List var2, long var3) {
      this.q(var1, var2, var3);
      var2.add(this.RF.createAssign(this.q.GO, this.RF.createOperation(OperationType.LOG_NOT, this.q.GO)));
   }
}
