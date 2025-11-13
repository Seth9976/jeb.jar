package com.pnfsoftware.jeb.corei.parsers.x86;

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

public class Sb {
   Yd pC;
   IERoutineContext A;

   Sb(Yd var1) {
      this.pC = var1;
   }

   void pC(ConverterInstructionEntry var1) {
      Object var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      int var3 = ((vh)var1.insn).sY();
      if (var3 == 16) {
         Assert.a(var2 instanceof IEImm);
         var2 = this.A.createImm(((IEImm)var2).getValueAsLong() & 65535L, this.pC.hK.getBitsize());
      }

      IEImm var4 = this.A.createImm(var1.address + ((vh)var1.insn).getSize(), this.pC.hK.getBitsize());
      IECond var5;
      if (((vh)var1.insn).gp.A != 227) {
         int var7 = ((vh)var1.insn).gp.A & 14;
         boolean var8 = (((vh)var1.insn).gp.A & 1) != 0;

         Object var6 = switch (var7) {
            case 0 -> this.pC.Cu;
            default -> throw new RuntimeException();
            case 2 -> this.pC.Er;
            case 4 -> this.pC.EX;
            case 6 -> this.A.createOperation(OperationType.LOG_OR, this.pC.Er, this.pC.EX);
            case 8 -> this.pC.LM;
            case 10 -> this.pC.FE;
            case 12 -> this.A.createOperation(OperationType.LOG_NEQ, this.pC.LM, this.pC.Cu);
            case 14 -> this.A.createOperation(OperationType.LOG_OR, this.pC.EX, this.A.createOperation(OperationType.LOG_NEQ, this.pC.LM, this.pC.Cu));
         };
         var5 = !var8 ? this.A.createCond((IEGeneric)var6, (IEGeneric)var2, var4) : this.A.createCond((IEGeneric)var6, var4, (IEGeneric)var2);
      } else {
         int var9 = ((vh)var1.insn).ys();
         IEGeneric var10 = this.pC.ah.part(var9);
         var5 = this.A.createCond(var10, var4, (IEGeneric)var2);
      }

      var1.r.add(this.A.createBranchAssign(this.pC.hK, var5, false));
   }

   void A(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).ys();
      IEGeneric var3 = this.pC.ah.part(var2);
      Object var4 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      int var5 = ((vh)var1.insn).sY();
      if (var5 == 16) {
         Assert.a(var4 instanceof IEImm);
         var4 = this.A.createImm(((IEImm)var4).getValueAsLong() & 65535L, this.pC.hK.getBitsize());
      }

      IEImm var6 = this.A.createImm(var1.address + ((vh)var1.insn).getSize(), this.pC.hK.getBitsize());
      IEOperation var7 = this.A.createOperation(OperationType.SUB, var3, this.A.createImm(1L, var3.getBitsize()));
      this.pC.pC(var3, var7, var1);
      int var8 = ((vh)var1.insn).gp.A;
      Object var9;
      if (var8 == 226) {
         var9 = var3;
      } else if (var8 == 225) {
         var9 = this.A.createOperation(OperationType.LOG_AND, var3, this.pC.EX);
      } else {
         if (var8 != 224) {
            throw new RuntimeException();
         }

         var9 = this.A.createOperation(OperationType.LOG_AND, var3, this.A.createOperation(OperationType.NOT, this.pC.EX));
      }

      var1.r.add(this.A.createBranchAssign(this.pC.hK, this.A.createCond((IEGeneric)var9, (IEGeneric)var4, var6), false));
   }

   void kS(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      int var3 = ((vh)var1.insn).gp.A;
      if (var3 == 233 || var3 == 65312) {
         int var4 = ((vh)var1.insn).sY();
         if (var4 == 16) {
            if (((vh)var1.insn).pC == 64) {
               throw new RuntimeException("N/S");
            }

            var2 = var2.part(16).zeroExtend(this.pC.hK.getBitsize());
         }
      }

      if (var3 != 233 && var3 != 235 && var3 != 65312) {
         throw new RuntimeException("TBI: jmp far");
      } else {
         IEAssign var5 = this.A.createBranchAssign(this.pC.hK, var2, ((vh)var1.insn).getRoutineCall(var1.address).isBroken());
         var1.r.add(var5);
      }
   }

   void wS(ConverterInstructionEntry var1) {
      this.pC(var1, true, true);
   }

   void pC(ConverterInstructionEntry var1, boolean var2, boolean var3) {
      if (!var2) {
         var3 = false;
      }

      int var4 = ((vh)var1.insn).pC;
      Assert.a(this.pC.Ab.getBitsize() == var4);
      IEImm var5 = this.A.createImm(var1.address + ((vh)var1.insn).getSize(), this.pC.hK.getBitsize());
      IEGeneric var6 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      int var7 = ((vh)var1.insn).gp.A;
      if (var7 != 232 && var7 != 65296) {
         if (!(var6 instanceof IEImm)) {
            throw new RuntimeException("callf [xxx] conversion not supported");
         }

         Object var12 = (IEImm)var6;
         int var13 = ((vh)var1.insn).sY();
         if (var13 == 16) {
            if (((vh)var1.insn).pC == 64) {
               throw new RuntimeException("N/S");
            }

            var12 = ((IEGeneric)var12).part(16).zeroExtend(this.pC.hK.getBitsize());
         }

         this.pC.ys.pC(var4, var13, this.pC.WR.zeroExtend(var13), var1.r);
         this.pC.ys.pC(var4, var13, var5.part(var13), var1.r);
         if (var2) {
            var1.r.add(this.A.createBranchAssign(this.pC.hK, (IEGeneric)var12, true));
         }
      } else {
         if (var3 && var6 instanceof IEImm var8 && var8.canReadAsAddress()) {
            long var9 = var8.getValueAsAddress();
            if (this.pC.pC(var1, var9)) {
               return;
            }
         }

         int var11 = ((vh)var1.insn).sY();
         if (var11 == 16) {
            if (((vh)var1.insn).pC == 64) {
               throw new RuntimeException("N/S");
            }

            var6 = var6.part(16).zeroExtend(this.pC.hK.getBitsize());
         }

         this.pC.ys.pC(var4, var11, var5.part(var11), var1.r);
         if (var2) {
            var1.r.add(this.A.createBranchAssign(this.pC.hK, var6, true));
         }
      }
   }

   void UT(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).pC;
      Assert.a(this.pC.Ab.getBitsize() == var2);
      IEVar var3 = this.pC.pC(this.pC.hK.getBitsize());
      int var4 = ((vh)var1.insn).gp.A;
      if (var4 == 194 || var4 == 195) {
         lB var12 = (lB)InstructionUtil.getOperand(var1.insn, 0, lB.class);
         long var13 = var12 == null ? 0L : var12.getOperandValue() & 65535L;
         int var8 = ((vh)var1.insn).sY();
         this.pC.ys.A(var2, var8, var3.part(var8), var1.r);
         if (var8 == 16 && this.pC.hK.getBitsize() > 16) {
            var1.r.add(this.A.createAssign(var3.slice(var8, this.pC.hK.getBitsize()), this.pC.JF.slice(var8, this.pC.hK.getBitsize())));
         }

         if (var13 != 0L) {
            IEGeneric var14 = this.pC.Ab.part(var2);
            IEOperation var15 = EUtil.add(var14, this.A.createImm(var13, var2));
            var1.r.add(this.A.createAssign(var14, var15));
         }

         var1.r.add(this.A.createBranchAssign(this.pC.hK, var3, false));
      } else if (var4 != 202 && var4 != 203) {
         Assert.fail();
      } else {
         IEVar var5 = this.pC.fn;
         lB var6 = (lB)InstructionUtil.getOperand(var1.insn, 0, lB.class);
         long var7 = var6 == null ? 0L : var6.getOperandValue() & 65535L;
         int var9 = ((vh)var1.insn).sY();
         this.pC.ys.A(var2, var9, var3.part(var9), var1.r);
         this.pC.ys.A(var2, var9, var5.part(var9), var1.r);
         if (var9 == 16 && this.pC.hK.getBitsize() > 16) {
            var1.r.add(this.A.createAssign(var3.slice(var9, this.pC.hK.getBitsize()), this.pC.JF.slice(var9, this.pC.hK.getBitsize())));
         }

         if (var7 != 0L) {
            IEGeneric var10 = this.pC.Ab.part(var2);
            IEOperation var11 = EUtil.add(var10, this.A.createImm(var7, var2));
            var1.r.add(this.A.createAssign(var10, var11));
         }

         var1.r.add(this.A.createAssign(this.pC.WR, var5.part(16)));
         var1.r.add(this.A.createBranchAssign(this.pC.hK, var3, false));
      }
   }
}
