package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.InstructionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;

public class crr {
   crx q;
   IERoutineContext RF;

   crr(crx var1) {
      this.q = var1;
   }

   void q(ConverterInstructionEntry var1) {
      Object var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      int var3 = ((ctc)var1.insn).za();
      if (var3 == 16) {
         Assert.a(var2 instanceof IEImm);
         var2 = this.RF.createImm(((IEImm)var2).getValueAsLong() & 65535L, this.q.rV.getBitsize());
      }

      IEImm var4 = this.RF.createImm(var1.address + ((ctc)var1.insn).getSize(), this.q.rV.getBitsize());
      IECond var5;
      if (((ctc)var1.insn).io.RF != 227) {
         int var7 = ((ctc)var1.insn).io.RF & 14;
         boolean var8 = (((ctc)var1.insn).io.RF & 1) != 0;

         Object var6 = switch (var7) {
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
         var5 = !var8 ? this.RF.createCond((IEGeneric)var6, (IEGeneric)var2, var4) : this.RF.createCond((IEGeneric)var6, var4, (IEGeneric)var2);
      } else {
         int var9 = ((ctc)var1.insn).lm();
         IEGeneric var10 = this.q.iu.part(var9);
         var5 = this.RF.createCond(var10, var4, (IEGeneric)var2);
      }

      var1.r.add(this.RF.createBranchAssign(this.q.rV, var5, false));
   }

   void RF(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).lm();
      IEGeneric var3 = this.q.iu.part(var2);
      Object var4 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      int var5 = ((ctc)var1.insn).za();
      if (var5 == 16) {
         Assert.a(var4 instanceof IEImm);
         var4 = this.RF.createImm(((IEImm)var4).getValueAsLong() & 65535L, this.q.rV.getBitsize());
      }

      IEImm var6 = this.RF.createImm(var1.address + ((ctc)var1.insn).getSize(), this.q.rV.getBitsize());
      IEOperation var7 = this.RF.createOperation(OperationType.SUB, var3, this.RF.createImm(1L, var3.getBitsize()));
      this.q.q(var3, var7, var1);
      int var8 = ((ctc)var1.insn).io.RF;
      Object var9;
      if (var8 == 226) {
         var9 = var3;
      } else if (var8 == 225) {
         var9 = this.RF.createOperation(OperationType.LOG_AND, var3, this.q.GC);
      } else {
         if (var8 != 224) {
            throw new RuntimeException();
         }

         var9 = this.RF.createOperation(OperationType.LOG_AND, var3, this.RF.createOperation(OperationType.NOT, this.q.GC));
      }

      var1.r.add(this.RF.createBranchAssign(this.q.rV, this.RF.createCond((IEGeneric)var9, (IEGeneric)var4, var6), false));
   }

   void xK(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      int var3 = ((ctc)var1.insn).io.RF;
      if (var3 == 233 || var3 == 65312) {
         int var4 = ((ctc)var1.insn).za();
         if (var4 == 16) {
            if (((ctc)var1.insn).gO == 64) {
               throw new RuntimeException("N/S");
            }

            var2 = var2.part(16).zeroExtend(this.q.rV.getBitsize());
         }
      }

      if (var3 != 233 && var3 != 235 && var3 != 65312) {
         throw new RuntimeException("TBI: jmp far");
      } else {
         IEAssign var5 = this.RF.createBranchAssign(this.q.rV, var2, ((ctc)var1.insn).getRoutineCall(var1.address).isBroken());
         var1.r.add(var5);
      }
   }

   void Dw(ConverterInstructionEntry var1) {
      this.q(var1, true, true);
   }

   void q(ConverterInstructionEntry var1, boolean var2, boolean var3) {
      if (!var2) {
         var3 = false;
      }

      int var4 = ((ctc)var1.insn).gO;
      Assert.a(this.q.Sz.getBitsize() == var4);
      IEImm var5 = this.RF.createImm(var1.address + ((ctc)var1.insn).getSize(), this.q.rV.getBitsize());
      IEGeneric var6 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      int var7 = ((ctc)var1.insn).io.RF;
      if (var7 != 232 && var7 != 65296) {
         if (!(var6 instanceof IEImm)) {
            throw new RuntimeException("callf [xxx] conversion not supported");
         }

         Object var12 = (IEImm)var6;
         int var13 = ((ctc)var1.insn).za();
         if (var13 == 16) {
            if (((ctc)var1.insn).gO == 64) {
               throw new RuntimeException("N/S");
            }

            var12 = ((IEGeneric)var12).part(16).zeroExtend(this.q.rV.getBitsize());
         }

         this.q.cY.q(var4, var13, this.q.Qu.zeroExtend(var13), var1.r);
         this.q.cY.q(var4, var13, var5.part(var13), var1.r);
         if (var2) {
            var1.r.add(this.RF.createBranchAssign(this.q.rV, (IEGeneric)var12, true));
         }
      } else {
         if (var3 && var6 instanceof IEImm var8 && var8.canReadAsAddress()) {
            long var9 = var8.getValueAsAddress();
            if (this.q.q(var1, var9)) {
               return;
            }
         }

         int var11 = ((ctc)var1.insn).za();
         if (var11 == 16) {
            if (((ctc)var1.insn).gO == 64) {
               throw new RuntimeException("N/S");
            }

            var6 = var6.part(16).zeroExtend(this.q.rV.getBitsize());
         }

         this.q.cY.q(var4, var11, var5.part(var11), var1.r);
         if (var2) {
            var1.r.add(this.RF.createBranchAssign(this.q.rV, var6, true));
         }
      }
   }

   void Uv(ConverterInstructionEntry var1) {
      int var2 = ((ctc)var1.insn).gO;
      Assert.a(this.q.Sz.getBitsize() == var2);
      IEVar var3 = this.q.q(this.q.rV.getBitsize());
      int var4 = ((ctc)var1.insn).io.RF;
      if (var4 == 194 || var4 == 195) {
         cte var12 = (cte)InstructionUtil.getOperand(var1.insn, 0, cte.class);
         long var13 = var12 == null ? 0L : var12.getOperandValue() & 65535L;
         int var8 = ((ctc)var1.insn).za();
         this.q.cY.RF(var2, var8, var3.part(var8), var1.r);
         if (var8 == 16 && this.q.rV.getBitsize() > 16) {
            var1.r.add(this.RF.createAssign(var3.slice(var8, this.q.rV.getBitsize()), this.q.Oj.slice(var8, this.q.rV.getBitsize())));
         }

         if (var13 != 0L) {
            IEGeneric var14 = this.q.Sz.part(var2);
            IEOperation var15 = EUtil.add(var14, this.RF.createImm(var13, var2));
            var1.r.add(this.RF.createAssign(var14, var15));
         }

         var1.r.add(this.RF.createBranchAssign(this.q.rV, var3, false));
      } else if (var4 != 202 && var4 != 203) {
         Assert.fail();
      } else {
         IEVar var5 = this.q.fe;
         cte var6 = (cte)InstructionUtil.getOperand(var1.insn, 0, cte.class);
         long var7 = var6 == null ? 0L : var6.getOperandValue() & 65535L;
         int var9 = ((ctc)var1.insn).za();
         this.q.cY.RF(var2, var9, var3.part(var9), var1.r);
         this.q.cY.RF(var2, var9, var5.part(var9), var1.r);
         if (var9 == 16 && this.q.rV.getBitsize() > 16) {
            var1.r.add(this.RF.createAssign(var3.slice(var9, this.q.rV.getBitsize()), this.q.Oj.slice(var9, this.q.rV.getBitsize())));
         }

         if (var7 != 0L) {
            IEGeneric var10 = this.q.Sz.part(var2);
            IEOperation var11 = EUtil.add(var10, this.RF.createImm(var7, var2));
            var1.r.add(this.RF.createAssign(var10, var11));
         }

         var1.r.add(this.RF.createAssign(this.q.Qu, var5.part(16)));
         var1.r.add(this.RF.createBranchAssign(this.q.rV, var3, false));
      }
   }
}
