package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroupElt;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.Arrays;

class cro {
   crx q;
   IERoutineContext RF;
   private static final cte xK = new cte(0, 80, ctf.q(0, 3, 80));
   private static final cte Dw = new cte(0, 80, ctf.q(1, 3, 80));

   cro(crx var1) {
      this.q = var1;
   }

   private static final boolean q(long var0, long var2) {
      return (var0 & var2) == var2;
   }

   private void xK(ConverterInstructionEntry var1, int var2) {
      IEImm var3 = this.RF.createImm(var2, 2);

      for (IEVar var7 : this.q.wr) {
         var1.r.add(this.RF.createAssign(var7, var3));
      }
   }

   private IEGroupElt q() {
      return this.RF.createGroupElt(this.q.cO, this.q.Gg);
   }

   private IEGroupElt RF() {
      return this.RF.createGroupElt(this.q.cO, EUtil.add(this.q.Gg, this.RF.createImm(1L, 3)));
   }

   private IEGroupElt xK() {
      return this.RF.createGroupElt(this.q.pe, this.q.Gg);
   }

   private IEGeneric q(IEGeneric var1, int var2) {
      if (var2 == 0) {
         int var3 = var1.getBitsize();
         if (var3 == 32 || var3 == 64) {
            return EUtil.createConversionOperation(OperationType.FP2FP, var1, 80);
         }

         if (var3 == 80) {
            return var1;
         }
      } else if (var2 == 1) {
         return EUtil.createConversionOperation(OperationType.INT2FP, var1, 80);
      }

      throw new RuntimeException("TBI");
   }

   private IEGeneric q(IEGeneric var1, int var2, int var3) {
      if (var2 == 0 && var1.getBitsize() == 80) {
         if (var3 == 32 || var3 == 64) {
            return EUtil.createConversionOperation(OperationType.FP2FP, var1, var3);
         }

         if (var3 == 80) {
            return var1;
         }
      }

      throw new RuntimeException("TBI");
   }

   private boolean q(IEGeneric var1) {
      return var1 instanceof IEVar
         || var1 instanceof IESlice && ((IESlice)var1).getWholeExpression() instanceof IEVar && ((IESlice)var1).getBitStart() == 0
         || var1 instanceof IEGroupElt && ((IEGroupElt)var1).getGroup() == this.q.cO;
   }

   private boolean RF(IEGeneric var1) {
      return var1 instanceof IEMem;
   }

   void q(ConverterInstructionEntry var1) {
      var1.r.add(this.RF.createNop());
   }

   void RF(ConverterInstructionEntry var1) {
      this.Hk(var1);
   }

   void xK(ConverterInstructionEntry var1) {
      this.qa(var1);
   }

   void Dw(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      boolean var3 = this.q(var2);
      if (var3) {
         var1.r.add(this.RF.createAssign(this.q.LS, var2));
      }

      this.Hk(var1);
      Object var4;
      if (!var3) {
         var4 = this.q(var2, 0);
      } else {
         var4 = this.q.LS;
      }

      var1.r.add(this.RF.createAssign(this.q(), (IEGeneric)var4));
   }

   void q(ConverterInstructionEntry var1, double var2) {
      this.Hk(var1);
      alu var4 = alu.RF(var2);
      var1.r.add(this.RF.createAssign(this.q(), var4));
   }

   void Uv(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      boolean var3 = this.q(var2);
      Object var4;
      if (var3) {
         var4 = this.q();
      } else {
         var4 = this.q(this.q(), 0, var2.getBitsize());
      }

      var1.r.add(this.RF.createAssign(var2, (IEGeneric)var4));
   }

   void oW(ConverterInstructionEntry var1) {
      this.Uv(var1);
      this.qa(var1);
   }

   void gO(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      this.Hk(var1);
      IEGeneric var3 = this.q(var2, 1);
      var1.r.add(this.RF.createAssign(this.q(), var3));
   }

   void nf(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEOperation var3 = EUtil.createConversionOperation(OperationType.FP2INT, this.q(), var2.getBitsize());
      var1.r.add(this.RF.createAssign(var2, var3));
   }

   void gP(ConverterInstructionEntry var1) {
      this.nf(var1);
      this.qa(var1);
   }

   void za(ConverterInstructionEntry var1) {
      IEGroupElt var2 = this.q();
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      var1.r.add(this.RF.createAssign(this.q.LS, var2));
      var1.r.add(this.RF.createAssign(var2, var3));
      var1.r.add(this.RF.createAssign(var3, this.q.LS));
   }

   void q(ConverterInstructionEntry var1, OperationType var2, boolean var3, boolean var4, boolean var5) {
      Object var6;
      IEGeneric var7;
      if (((ctc)var1.insn).Dw().length == 1) {
         var6 = this.q();
         var7 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      } else {
         if (((ctc)var1.insn).Dw().length != 2) {
            throw new RuntimeException("unexpected operand count: " + ((ctc)var1.insn).Dw().length);
         }

         var6 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
         var7 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      }

      IEGeneric var8 = this.q(var7, var4 ? 1 : 0);
      IEOperation var9 = !var5 ? EUtil.op(var2, (IEGeneric)var6, var8) : EUtil.op(var2, var8, (IEGeneric)var6);
      var1.r.add(this.RF.createAssign((IEGeneric)var6, var9));
      if (var3) {
         this.qa(var1);
      }
   }

   private void qa(ConverterInstructionEntry var1) {
      this.Dw(var1, 1);
   }

   private void Dw(ConverterInstructionEntry var1, int var2) {
      var1.r.add(this.RF.createAssign(this.q.Gg, EUtil.add(this.q.Gg, this.RF.createImm(var2, 3))));
   }

   private void Hk(ConverterInstructionEntry var1) {
      this.Uv(var1, 1);
   }

   private void Uv(ConverterInstructionEntry var1, int var2) {
      var1.r.add(this.RF.createAssign(this.q.Gg, EUtil.sub(this.q.Gg, this.RF.createImm(var2, 3))));
   }

   void lm(ConverterInstructionEntry var1) {
      this.HF(var1);
      var1.r.remove(var1.r.size() - 2);
      this.q(var1, 1.0);
   }

   void zz(ConverterInstructionEntry var1) {
      this.HF(var1);
      var1.r.remove(var1.r.size() - 2);
      this.Hk(var1);
   }

   void JY(ConverterInstructionEntry var1) {
      this.HF(var1);
      var1.r.remove(var1.r.size() - 2);
      this.Hk(var1);
   }

   boolean HF(ConverterInstructionEntry var1) {
      long var2 = ((ctc)var1.insn).io.xK;
      ArrayList var4 = new ArrayList(Arrays.asList(((ctc)var1.insn).Dw()));
      if (q(var2, csx.HF)) {
         var4.add(0, xK);
      }

      if (q(var2, csx.LK)) {
         var4.add(xK);
      }

      if (q(var2, csx.io)) {
         var4.add(0, Dw);
      }

      if (q(var2, csx.qa)) {
         var4.add(Dw);
      }

      if (q(var2, csx.jq)) {
         this.Hk(var1);
      }

      IEUntranslatedInstruction var5 = this.RF.createUntranslatedInstruction(var1.address, ((ctc)var1.insn).getMnemonic());
      ArrayList var6 = new ArrayList();
      ArrayList var7 = new ArrayList();

      for (int var8 = 0; var8 < var4.size(); var8++) {
         IEGeneric var9 = this.q.q((ctc)var1.insn, (cqj)var4.get(var8), -1, var1.address);
         if ((var8 != 0 || !q(var2, csx.JY)) && !q(var2, csx.xW)) {
            var6.add(var9);
            if (var8 == 0 && q(var2, csx.zz) || q(var2, csx.oQ)) {
               var7.add(var9.duplicate());
            }
         } else {
            var7.add(var9);
         }
      }

      var5.setParameterExpressions(var6);
      var5.setReturnExpressions(var7);
      var1.r.add(var5);
      if (q(var2, csx.Dz)) {
         this.qa(var1);
      } else if (q(var2, csx.mI)) {
         this.Dw(var1, 2);
      }

      return true;
   }

   void LK(ConverterInstructionEntry var1) {
      this.xK(var1, 3);
   }

   void q(ConverterInstructionEntry var1, boolean var2, int var3) {
      IEGeneric var4 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var5 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      int var6 = var4.getBitsize();
      Assert.a(var6 == var5.getBitsize());
      Assert.a(var3 == 16 || var3 == 32);
      if (var3 == 16) {
         IEImm var7;
         IEImm var8;
         IEImm var9;
         IEImm var10;
         if (var2) {
            var7 = EUtil.imm(127L, 8);
            var8 = EUtil.imm(-128L, 8);
            var9 = EUtil.imm(127L, 16);
            var10 = EUtil.imm(-128L, 16);
         } else {
            var7 = EUtil.imm(255L, 8);
            var8 = EUtil.imm(0L, 8);
            var9 = EUtil.imm(255L, 16);
            var10 = EUtil.imm(0L, 16);
         }

         byte var11 = 0;

         for (byte var12 = 0; var12 < var6; var12 += 16) {
            IECond var13 = this.RF
               .createCond(
                  EUtil.gtS(var4.slice(var12, var12 + 16), var9),
                  var7,
                  this.RF.createCond(EUtil.ltS(var4.slice(var12, var12 + 16), var10), var8, var4.slice(var12, var12 + 8))
               );
            var1.r.add(this.RF.createAssign(var4.slice(var11, var11 + 8), var13));
            var11 += 8;
         }

         for (byte var19 = 0; var19 < var6; var19 += 16) {
            IECond var22 = this.RF
               .createCond(
                  EUtil.gtS(var5.slice(var19, var19 + 16), var9),
                  var7,
                  this.RF.createCond(EUtil.ltS(var5.slice(var19, var19 + 16), var10), var8, var5.slice(var19, var19 + 8))
               );
            var1.r.add(this.RF.createAssign(var4.slice(var11, var11 + 8), var22));
            var11 += 8;
         }
      } else {
         IEImm var14;
         IEImm var15;
         IEImm var16;
         IEImm var17;
         if (var2) {
            var14 = EUtil.imm(32767L, 16);
            var15 = EUtil.imm(-32768L, 16);
            var16 = EUtil.imm(32767L, 32);
            var17 = EUtil.imm(-32768L, 32);
         } else {
            var14 = EUtil.imm(65535L, 16);
            var15 = EUtil.imm(0L, 16);
            var16 = EUtil.imm(65535L, 32);
            var17 = EUtil.imm(0L, 32);
         }

         byte var18 = 0;

         for (byte var20 = 0; var20 < var6; var20 += 32) {
            IECond var23 = this.RF
               .createCond(
                  EUtil.gtS(var4.slice(var20, var20 + 32), var16),
                  var14,
                  this.RF.createCond(EUtil.ltS(var4.slice(var20, var20 + 32), var17), var15, var4.slice(var20, var20 + 16))
               );
            var1.r.add(this.RF.createAssign(var4.slice(var18, var18 + 16), var23));
            var18 += 16;
         }

         for (byte var21 = 0; var21 < var6; var21 += 32) {
            IECond var24 = this.RF
               .createCond(
                  EUtil.gtS(var5.slice(var21, var21 + 32), var16),
                  var14,
                  this.RF.createCond(EUtil.ltS(var5.slice(var21, var21 + 32), var17), var15, var5.slice(var21, var21 + 16))
               );
            var1.r.add(this.RF.createAssign(var4.slice(var18, var18 + 16), var24));
            var18 += 16;
         }
      }
   }

   void RF(ConverterInstructionEntry var1, boolean var2, int var3) {
      IEGeneric var4 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var5 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      int var6 = var4.getBitsize();
      int var7 = 0;
      if (var2) {
         var7 = var6 / 2;
      }

      IEVar[] var8 = this.q.RF(var3);
      int var9 = 0;

      for (int var10 = 0; var10 < var6 / 2; var9++) {
         var1.r.add(this.RF.createAssign(var8[var9], var4.slice(var7 + var10, var7 + var10 + var3)));
         var10 += var3;
      }

      for (int var12 = 0; var12 < var6 / 2; var9++) {
         var1.r.add(this.RF.createAssign(var8[var9], var5.slice(var7 + var12, var7 + var12 + var3)));
         var12 += var3;
      }

      var9 = 0;

      for (int var13 = 0; var13 < var6 / 2; var9++) {
         var1.r.add(this.RF.createAssign(var4.slice(2 * var13, 2 * var13 + var3), var8[var9]));
         var13 += var3;
      }

      for (int var14 = 0; var14 < var6 / 2; var9++) {
         var1.r.add(this.RF.createAssign(var4.slice(2 * var14 + var3, 2 * var14 + var3 + var3), var8[var9]));
         var14 += var3;
      }
   }

   void q(ConverterInstructionEntry var1, int var2, OperationType var3) {
      this.q(var1, var2, var3, null, 0);
   }

   void q(ConverterInstructionEntry var1, int var2, OperationType var3, Boolean var4) {
      this.q(var1, var2, var3, var4, 0);
   }

   void q(ConverterInstructionEntry var1, int var2, OperationType var3, Boolean var4, int var5) {
      IEGeneric var6 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var7 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      int var8 = var6.getBitsize();
      Assert.a(var8 % var2 == 0);
      IEOperation var9 = null;
      if (var3 != OperationType.SHL && var3 != OperationType.SAR && var3 != OperationType.SHR) {
         Assert.a(var8 == var7.getBitsize());
      } else {
         var9 = EUtil.gtU(var7, this.RF.createImm((var2 >>> var5) - 1, var7.getBitsize()));
      }

      for (int var10 = 0; var10 < var8; var10 += var2) {
         IEAssign var11;
         if (var3 == OperationType.LOG_EQ || var3 == OperationType.GT_S) {
            IECond var17 = this.RF
               .createCond(EUtil.op(var3, var6.slice(var10, var10 + var2), var7.slice(var10, var10 + var2)), EUtil.minusOne(var2), EUtil.zero(var2));
            var11 = this.RF.createAssign(var6.slice(var10, var10 + var2), var17);
         } else if (var3 == OperationType.ADD
            || var3 == OperationType.SUB
            || var3 == OperationType.ADD_SSAT
            || var3 == OperationType.ADD_USAT
            || var3 == OperationType.SUB_SSAT
            || var3 == OperationType.SUB_USAT) {
            var11 = this.RF.createAssign(var6.slice(var10, var10 + var2), EUtil.op(var3, var6.slice(var10, var10 + var2), var7.slice(var10, var10 + var2)));
         } else if (var3 == OperationType.SHL || var3 == OperationType.SHR) {
            IEGeneric var16 = var7.getBitsize() <= var2 ? var7.signExtend(var2) : var7.part(var2);
            if (var5 != 0) {
               var16 = var16.leftShift(var5);
            }

            IECond var18 = this.RF.createCond(var9, EUtil.zero(var2), EUtil.op(var3, var6.slice(var10, var10 + var2), var16));
            var11 = this.RF.createAssign(var6.slice(var10, var10 + var2), var18);
         } else if (var3 == OperationType.SAR) {
            IEGeneric var12 = var7.getBitsize() <= var2 ? var7.signExtend(var2) : var7.part(var2);
            if (var5 != 0) {
               var12 = var12.leftShift(var5);
            }

            IECond var13 = this.RF
               .createCond(
                  var9,
                  this.RF.createCond(var6.slice(var10, var10 + var2).msb(), EUtil.minusOne(var2), EUtil.zero(var2)),
                  EUtil.op(var3, var6.slice(var10, var10 + var2), var12)
               );
            var11 = this.RF.createAssign(var6.slice(var10, var10 + var2), var13);
         } else {
            if (var3 != OperationType.MUL2_S && var3 != OperationType.MUL2_U) {
               throw new RuntimeException();
            }

            IEOperation var14 = EUtil.op(var3, var6.slice(var10, var10 + var2), var7.slice(var10, var10 + var2));
            if (var4 != null) {
               IEGeneric var15 = var4 ? var14.slice(var14.getBitsize() / 2) : var14.part(var14.getBitsize() / 2);
               var11 = this.RF.createAssign(var6.slice(var10, var10 + var2), var15);
            } else {
               var11 = this.RF.createAssign(var6.slice(var10, var10 + var2 * 2), var14);
               var10 += var2;
            }
         }

         var1.r.add(var11);
      }
   }

   void q(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      int var5 = var3.getBitsize();
      Assert.a(var5 == var4.getBitsize());

      var1.r.add(this.RF.createAssign(var3, switch (var2) {
         case 0 -> EUtil.andB(var3, var4);
         case 1 -> EUtil.andB(EUtil.notB(var3), var4);
         case 2 -> EUtil.orB(var3, var4);
         case 3 -> EUtil.xorB(var3, var4);
         default -> throw new RuntimeException();
      }));
   }

   void RF(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      this.q.q(var3.part(var2), var4.part(var2), var1, true);
      if (this.q(var3) && var3.getBitsize() > var2) {
         int var5 = Math.min(var3.getBitsize(), 128);
         var1.r.add(this.RF.createAssign(var3.slice(var2, var5), EUtil.zero(var5 - var2)));
      }
   }

   void io(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      this.q.q(this.q.Gg, EUtil.zero(this.q.Gg.getBitsize()), var1);
      this.xK(var1, 0);

      for (int var4 = 0; var4 < 8; var4++) {
         var1.r.add(this.RF.createJump(var1.irAddress + var1.r.size() + 2, EUtil.ne(var3.bit(var4 * 8 + 7), this.q.hP)));
         IEMem var5 = this.RF.createMem(EUtil.add(this.q.Bs, EUtil.imm(var4, this.q.Bs.getBitsize())), 8);
         IEGeneric var6 = var2.slice(var4 * 8, var4 * 8 + 8);
         var1.r.add(this.RF.createAssign(var5, var6));
      }
   }
}
