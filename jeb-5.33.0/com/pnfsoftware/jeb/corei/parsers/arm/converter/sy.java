package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jebglobal.Yg;
import com.pnfsoftware.jebglobal.pY;
import java.util.List;

public class sy {
   HE pC;
   IERoutineContext A;

   public sy(HE var1) {
      this.pC = var1;
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      Yg var5 = var1.A()[1];
      if (!this.pC(var5)) {
         var2.add(this.pC.pC(var1, var3, true));
      } else if (RegisterUtil.getRegisterIndex(var5.getOperandValue()) != RegisterBankArm.APSR) {
         var2.add(this.pC.pC(var1, var3, true));
      } else {
         pY var6 = this.pC.pC(var1, var1.A()[0]);
         this.pC
            .pC(
               var1,
               var2,
               var6,
               this.A
                  .createCompose(
                     this.pC(),
                     this.pC.FE,
                     this.A(),
                     this.pC.FE,
                     this.pC.wS(6),
                     this.pC.Ek,
                     this.pC.wS(7),
                     this.pC.z,
                     this.pC.RW.wS,
                     this.pC.RW.kS,
                     this.pC.RW.A,
                     this.pC.RW.pC
                  )
            );
      }
   }

   private IEGeneric pC() {
      return this.A.createImm(HE.Av.pC.pC(), 5);
   }

   private IEGeneric A() {
      return this.pC.wS(3);
   }

   boolean pC(Yg var1) {
      return var1.getOperandType() == 0 && RegisterUtil.getRegisterGroup(var1.getOperandValue()) == 11;
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      Yg var5 = var1.A()[0];
      if (!this.pC(var5)) {
         IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var2.add(this.pC.A(var1, var3, true, var6));
      } else if ((RegisterUtil.getRegisterIndex(var5.getOperandValue()) & 16) != 0) {
         IEGeneric var9 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var2.add(this.pC.A(var1, var3, true, var9));
      } else {
         IEGeneric var10 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         long var7 = RegisterUtil.getRegisterIndex(var5.getOperandValue());
         this.pC(var10, (int)var7, var2);
      }
   }

   private void pC(IEGeneric var1, int var2, List var3) {
      if ((var2 & 8) != 0) {
         var3.add(this.A.createAssign(this.pC.z, var1.bit(27)));
         var3.add(this.A.createAssign(this.pC.RW.wS, var1.bit(28)));
         var3.add(this.A.createAssign(this.pC.RW.kS, var1.bit(29)));
         var3.add(this.A.createAssign(this.pC.RW.A, var1.bit(30)));
         var3.add(this.A.createAssign(this.pC.RW.pC, var1.bit(31)));
      }

      if ((var2 & 4) != 0) {
         var3.add(this.A.createAssign(this.pC.Ek, var1.slice(16, 20)));
      }
   }

   public void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.A()[0]);
      String var6 = var1.A()[1].getRegisterName(var1.A()[1].getOperandValue(var3));
      if ("DAIF".equals(var6)) {
         this.pC.pC(var1, var2, var5, EUtil.compose(this.A, EUtil.imm(0L, 6), this.pC.sO, this.pC.mv, this.pC.LM, this.pC.EX, EUtil.imm(0L, 54)));
      } else if ("SPSel".equals(var6)) {
         this.pC.pC(var1, var2, var5, EUtil.compose(this.A, this.pC.os, EUtil.imm(0L, 63)));
      } else if ("NZCV".equals(var6)) {
         this.pC.pC(var1, var2, var5, EUtil.compose(this.A, EUtil.imm(0L, 28), this.pC.RW.wS, this.pC.RW.kS, this.pC.RW.A, this.pC.RW.pC, EUtil.imm(0L, 32)));
      } else {
         IEGeneric var7 = this.pC.pC(var1, var1.A()[1], 64, var3);
         this.pC.pC(var1, var2, var5, var7);
      }
   }

   public void wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      if (var1.A()[0].getOperandType() == 0) {
         IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         IEUntranslatedInstruction var6 = this.pC.pC(var1, var3, true);
         var6.addSideEffectUsedVariable((IEVar)var5);
         var2.add(var6);
      } else if (var1.A()[0].getOperandType() == 6) {
         long var9 = var1.A()[1].getOperandValue(var3) & 15L;
         String var7 = var1.A()[0].getAlias(0L);
         switch (var7) {
            case "SPSel":
               var2.add(this.A.createAssign(this.pC.os, this.A.createImm(var9 & 1L, 1)));
               break;
            case "DAIFSet":
               var2.add(this.A.createAssign(this.pC.EX, EUtil.orB(this.pC.EX, this.A.createImm((var9 & 8L) >>> 3, 1))));
               var2.add(this.A.createAssign(this.pC.LM, EUtil.orB(this.pC.EX, this.A.createImm((var9 & 4L) >>> 2, 1))));
               var2.add(this.A.createAssign(this.pC.mv, EUtil.orB(this.pC.EX, this.A.createImm((var9 & 2L) >>> 1, 1))));
               var2.add(this.A.createAssign(this.pC.sO, EUtil.orB(this.pC.EX, this.A.createImm(var9 & 1L, 1))));
               break;
            case "DAIFClr":
               var2.add(this.A.createAssign(this.pC.EX, EUtil.andB(this.pC.EX, this.A.createImm((~var9 & 8L) >>> 3, 1))));
               var2.add(this.A.createAssign(this.pC.LM, EUtil.andB(this.pC.EX, this.A.createImm((~var9 & 4L) >>> 2, 1))));
               var2.add(this.A.createAssign(this.pC.mv, EUtil.andB(this.pC.EX, this.A.createImm((~var9 & 2L) >>> 1, 1))));
               var2.add(this.A.createAssign(this.pC.sO, EUtil.andB(this.pC.EX, this.A.createImm(~var9 & 1L, 1))));
               break;
            default:
               var2.add(this.pC.pC(var1, var3, true));
         }
      } else {
         var2.add(this.pC.pC(var1, var3, true));
      }
   }
}
