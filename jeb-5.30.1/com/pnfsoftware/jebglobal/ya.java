package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import java.util.List;

public class ya {
   uq q;
   IERoutineContext RF;

   public ya(uq var1) {
      this.q = var1;
   }

   public void q(fA var1, List var2, long var3) {
      CW var5 = var1.RF()[1];
      if (!this.q(var5)) {
         var2.add(this.q.q(var1, var3, true));
      } else if (RegisterUtil.getRegisterIndex(var5.getOperandValue()) != RegisterBankArm.APSR) {
         var2.add(this.q.q(var1, var3, true));
      } else {
         CZ var6 = this.q.q(var1, var1.RF()[0]);
         this.q
            .q(
               var1,
               var2,
               var6,
               this.RF
                  .createCompose(
                     this.q(),
                     this.q.SM,
                     this.RF(),
                     this.q.SM,
                     this.q.Uv(6),
                     this.q.So,
                     this.q.Uv(7),
                     this.q.Kl,
                     this.q.Of.Dw,
                     this.q.Of.xK,
                     this.q.Of.RF,
                     this.q.Of.q
                  )
            );
      }
   }

   private IEGeneric q() {
      return this.RF.createImm(uq.CU.q.q(), 5);
   }

   private IEGeneric RF() {
      return this.q.Uv(3);
   }

   boolean q(CW var1) {
      return var1.getOperandType() == 0 && RegisterUtil.getRegisterGroup(var1.getOperandValue()) == 11;
   }

   public void RF(fA var1, List var2, long var3) {
      CW var5 = var1.RF()[0];
      if (!this.q(var5)) {
         IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var2.add(this.q.RF(var1, var3, true, var6));
      } else if ((RegisterUtil.getRegisterIndex(var5.getOperandValue()) & 16) != 0) {
         IEGeneric var9 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var2.add(this.q.RF(var1, var3, true, var9));
      } else {
         IEGeneric var10 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         long var7 = RegisterUtil.getRegisterIndex(var5.getOperandValue());
         this.q(var10, (int)var7, var2);
      }
   }

   private void q(IEGeneric var1, int var2, List var3) {
      if ((var2 & 8) != 0) {
         var3.add(this.RF.createAssign(this.q.Kl, var1.bit(27)));
         var3.add(this.RF.createAssign(this.q.Of.Dw, var1.bit(28)));
         var3.add(this.RF.createAssign(this.q.Of.xK, var1.bit(29)));
         var3.add(this.RF.createAssign(this.q.Of.RF, var1.bit(30)));
         var3.add(this.RF.createAssign(this.q.Of.q, var1.bit(31)));
      }

      if ((var2 & 4) != 0) {
         var3.add(this.RF.createAssign(this.q.So, var1.slice(16, 20)));
      }
   }

   public void xK(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      String var6 = var1.RF()[1].getRegisterName(var1.RF()[1].getOperandValue(var3));
      if ("DAIF".equals(var6)) {
         this.q.q(var1, var2, var5, EUtil.compose(this.RF, EUtil.imm(0L, 6), this.q.HO, this.q.Up, this.q.QZ, this.q.GO, EUtil.imm(0L, 54)));
      } else if ("SPSel".equals(var6)) {
         this.q.q(var1, var2, var5, EUtil.compose(this.RF, this.q.cv, EUtil.imm(0L, 63)));
      } else if ("NZCV".equals(var6)) {
         this.q.q(var1, var2, var5, EUtil.compose(this.RF, EUtil.imm(0L, 28), this.q.Of.Dw, this.q.Of.xK, this.q.Of.RF, this.q.Of.q, EUtil.imm(0L, 32)));
      } else {
         IEGeneric var7 = this.q.q(var1, var1.RF()[1], 64, var3);
         this.q.q(var1, var2, var5, var7);
      }
   }

   public void Dw(fA var1, List var2, long var3) {
      if (var1.RF()[0].getOperandType() == 0) {
         IEGeneric var5 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         IEUntranslatedInstruction var6 = this.q.q(var1, var3, true);
         var6.addSideEffectUsedVariable((IEVar)var5);
         var2.add(var6);
      } else if (var1.RF()[0].getOperandType() == 6) {
         long var9 = var1.RF()[1].getOperandValue(var3) & 15L;
         String var7 = var1.RF()[0].getAlias(0L);
         switch (var7) {
            case "SPSel":
               var2.add(this.RF.createAssign(this.q.cv, this.RF.createImm(var9 & 1L, 1)));
               break;
            case "DAIFSet":
               var2.add(this.RF.createAssign(this.q.GO, EUtil.orB(this.q.GO, this.RF.createImm((var9 & 8L) >>> 3, 1))));
               var2.add(this.RF.createAssign(this.q.QZ, EUtil.orB(this.q.GO, this.RF.createImm((var9 & 4L) >>> 2, 1))));
               var2.add(this.RF.createAssign(this.q.Up, EUtil.orB(this.q.GO, this.RF.createImm((var9 & 2L) >>> 1, 1))));
               var2.add(this.RF.createAssign(this.q.HO, EUtil.orB(this.q.GO, this.RF.createImm(var9 & 1L, 1))));
               break;
            case "DAIFClr":
               var2.add(this.RF.createAssign(this.q.GO, EUtil.andB(this.q.GO, this.RF.createImm((~var9 & 8L) >>> 3, 1))));
               var2.add(this.RF.createAssign(this.q.QZ, EUtil.andB(this.q.GO, this.RF.createImm((~var9 & 4L) >>> 2, 1))));
               var2.add(this.RF.createAssign(this.q.Up, EUtil.andB(this.q.GO, this.RF.createImm((~var9 & 2L) >>> 1, 1))));
               var2.add(this.RF.createAssign(this.q.HO, EUtil.andB(this.q.GO, this.RF.createImm(~var9 & 1L, 1))));
               break;
            default:
               var2.add(this.q.q(var1, var3, true));
         }
      } else {
         var2.add(this.q.q(var1, var3, true));
      }
   }
}
