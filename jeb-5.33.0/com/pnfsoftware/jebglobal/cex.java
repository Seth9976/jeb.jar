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

public class cex {
   cfb pC;
   IERoutineContext A;

   public cex(cfb var1) {
      this.pC = var1;
   }

   public void pC(cfq var1, List var2, long var3) {
      if (var1.A().getBranchType() == IInsnEmulator.BranchType.CALL) {
         var2.add(this.A.createAssign(this.pC.xC, this.pC.pC(var3, var1.A().pC() ? 8 : 4)));
      }

      IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0], var3).zeroExtend(this.pC.WR.getBitsize());
      if (var5 instanceof IEImm) {
         var2.add(this.A.createBranchAssign(this.pC.WR, var5, var1.A().getBranchType() == IInsnEmulator.BranchType.CALL));
      } else {
         var2.add(this.A.createAssign(this.pC.z, var5));
         var2.add(this.A.createBranchAssign(this.pC.WR, this.pC.z, var1.A().getBranchType() == IInsnEmulator.BranchType.CALL));
      }
   }

   public void A(cfq var1, List var2, long var3) {
      if (((cfj[])var1.getOperands()).length == 1) {
         this.pC(var1, var2, var3);
      } else {
         IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0], var3);
         IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[1], var3);
         var2.add(this.A.createAssign(var5, this.pC.pC(var3, 8)));
         var2.add(this.A.createAssign(this.pC.z, var6));
         var2.add(this.A.createBranchAssign(this.pC.WR, this.pC.z, var1.A().getBranchType() == IInsnEmulator.BranchType.CALL));
      }
   }

   public void kS(cfq var1, List var2, long var3) {
      if (var1.A().getBranchType() == IInsnEmulator.BranchType.CALL) {
         var2.add(this.A.createAssign(this.pC.xC, this.pC.pC(var3, var1.A().pC() ? 8 : 4)));
      }

      IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0], var3);
      Object var6 = var5;
      if (((cfj[])var1.getOperands()).length > 1) {
         IEImm var7 = this.A.createImm(((cfj[])var1.getOperands())[1].getOperandValue(), var5.getBitsize());
         var6 = EUtil.add(var5, var7);
      }

      var2.add(this.A.createAssign(this.pC.z, (IEGeneric)var6));
      var2.add(this.A.createBranchAssign(this.pC.WR, this.pC.z, var1.A().getBranchType() == IInsnEmulator.BranchType.CALL));
   }

   public void wS(cfq var1, List var2, long var3) {
      var2.add(this.A.createAssign(this.pC.xC, this.pC.pC(var3, 8)));
   }

   public void pC(cfq var1, List var2, long var3, int var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0], var3);
      Object var7;
      if (((cfj[])var1.getOperands()).length == 2) {
         var7 = this.pC.rl.zeroExtend(var6.getBitsize());
      } else {
         var7 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[1], var3);
      }

      IEGeneric var8 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[((cfj[])var1.getOperands()).length - 1], var3);
      IEGeneric var9 = this.pC(var1.E(), var6, (IEGeneric)var7);
      IEGeneric var10 = this.pC.pC(var3, var5);
      this.pC(var1, var2, var9, var8.zeroExtend(var6.getBitsize()), var10);
   }

   private void pC(cfq var1, List var2, IEGeneric var3, IEGeneric var4, IEGeneric var5) {
      if (var1.A().getBranchType() == IInsnEmulator.BranchType.CALL) {
         var2.add(this.A.createAssign(this.pC.xC, var5));
      }

      var2.add(this.A.createAssign(this.pC.Ek, var3));
      if (!(var4 instanceof IEImm)) {
         var2.add(this.A.createAssign(this.pC.z, (IEGeneric)var4));
         var4 = this.pC.z;
      }

      var2.add(
         this.A.createBranchAssign(this.pC.WR, this.A.createCond(this.pC.Ek, (IEGeneric)var4, var5), var1.A().getBranchType() == IInsnEmulator.BranchType.CALL)
      );
   }

   private IEGeneric pC(cfo var1, IEGeneric var2, IEGeneric var3) {
      switch (var1) {
         case fI:
            return this.pC(var2, var3);
         case WR:
            return EUtil.notL(this.pC(var2, var3));
         default:
            return this.A.createOperation(this.pC(var1), var2, var3);
      }
   }

   private IEGeneric pC(IEGeneric var1, IEGeneric var2) {
      var1 = var1.part(32);
      var2 = var2.part(32);
      int var3 = var1.getBitsize() + 1;
      IEOperation var4 = EUtil.add(var1.signExtend(var3), var2.signExtend(var3));
      return this.A.createOperation(OperationType.LOG_NEQ, var4.msb(), var4.duplicate().bit(var1.getBitsize() - 1));
   }

   private OperationType pC(cfo var1) {
      switch (var1) {
         case A:
            return OperationType.LOG_EQ;
         case kS:
            return OperationType.LOG_NEQ;
         case sY:
            return OperationType.GE_S;
         case E:
            return OperationType.GT_S;
         case UT:
            return OperationType.LE_S;
         case wS:
            return OperationType.LT_S;
         case oT:
            return OperationType.GE_U;
         case gp:
            return OperationType.GT_U;
         case ld:
            return OperationType.LE_U;
         case ys:
            return OperationType.LT_U;
         default:
            throw new UnsupportedConversionException("Can not evaluate condition " + var1);
      }
   }

   public void pC(cfq var1, List var2, long var3, boolean var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[((cfj[])var1.getOperands()).length - 1], var3);
      Object var7 = ((cfj[])var1.getOperands()).length == 2 ? this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]) : this.pC.hK[0];
      Object var8 = var5 ? var7 : EUtil.notL((IEGeneric)var7);
      IEGeneric var9 = this.pC.pC(var3, 8);
      this.pC(var1, var2, (IEGeneric)var8, var6, var9);
   }

   public void A(cfq var1, List var2, long var3, boolean var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[((cfj[])var1.getOperands()).length - 1], var3);
      IEGeneric var7 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEImm var8;
      if (var7.getBitsize() == 32) {
         var8 = this.A.createImm(0.0F);
      } else {
         var8 = this.A.createImm(0.0);
      }

      IEOperation var9 = this.A.createOperation(var5 ? OperationType.FEQ : OperationType.FNE, var7, var8);
      IEGeneric var10 = this.pC.pC(var3, 8);
      this.pC(var1, var2, var9, var6, var10);
   }

   public void UT(cfq var1, List var2, long var3) {
      this.pC(var1, var2, var3);
      var2.add(this.A.createAssign(this.pC.Aj, this.A.createOperation(OperationType.LOG_NOT, this.pC.Aj)));
   }
}
