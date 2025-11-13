package com.pnfsoftware.jeb.corei.parsers.x86;

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
import com.pnfsoftware.jebglobal.ajr;
import java.util.ArrayList;
import java.util.Arrays;

class m {
   Yd pC;
   IERoutineContext A;
   private static final lB kS = new lB(0, 80, MG.pC(0, 3, 80));
   private static final lB wS = new lB(0, 80, MG.pC(1, 3, 80));

   m(Yd var1) {
      this.pC = var1;
   }

   private static final boolean pC(long var0, long var2) {
      return (var0 & var2) == var2;
   }

   private void kS(ConverterInstructionEntry var1, int var2) {
      IEImm var3 = this.A.createImm(var2, 2);

      for (IEVar var7 : this.pC.Um) {
         var1.r.add(this.A.createAssign(var7, var3));
      }
   }

   private IEGroupElt pC() {
      return this.A.createGroupElt(this.pC.uE, this.pC.So);
   }

   private IEGeneric pC(IEGeneric var1, int var2) {
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

   private IEGeneric pC(IEGeneric var1, int var2, int var3) {
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

   private boolean pC(IEGeneric var1) {
      return var1 instanceof IEVar
         || var1 instanceof IESlice && ((IESlice)var1).getWholeExpression() instanceof IEVar && ((IESlice)var1).getBitStart() == 0
         || var1 instanceof IEGroupElt && ((IEGroupElt)var1).getGroup() == this.pC.uE;
   }

   void pC(ConverterInstructionEntry var1) {
      var1.r.add(this.A.createNop());
   }

   void A(ConverterInstructionEntry var1) {
      this.ED(var1);
   }

   void kS(ConverterInstructionEntry var1) {
      this.xC(var1);
   }

   void wS(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      boolean var3 = this.pC(var2);
      if (var3) {
         var1.r.add(this.A.createAssign(this.pC.gy, var2));
      }

      this.ED(var1);
      Object var4;
      if (!var3) {
         var4 = this.pC(var2, 0);
      } else {
         var4 = this.pC.gy;
      }

      var1.r.add(this.A.createAssign(this.pC(), (IEGeneric)var4));
   }

   void pC(ConverterInstructionEntry var1, double var2) {
      this.ED(var1);
      ajr var4 = ajr.A(var2);
      var1.r.add(this.A.createAssign(this.pC(), var4));
   }

   void UT(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      boolean var3 = this.pC(var2);
      Object var4;
      if (var3) {
         var4 = this.pC();
      } else {
         var4 = this.pC(this.pC(), 0, var2.getBitsize());
      }

      var1.r.add(this.A.createAssign(var2, (IEGeneric)var4));
   }

   void E(ConverterInstructionEntry var1) {
      this.UT(var1);
      this.xC(var1);
   }

   void sY(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      this.ED(var1);
      IEGeneric var3 = this.pC(var2, 1);
      var1.r.add(this.A.createAssign(this.pC(), var3));
   }

   void ys(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEOperation var3 = EUtil.createConversionOperation(OperationType.FP2INT, this.pC(), var2.getBitsize());
      var1.r.add(this.A.createAssign(var2, var3));
   }

   void ld(ConverterInstructionEntry var1) {
      this.ys(var1);
      this.xC(var1);
   }

   void gp(ConverterInstructionEntry var1) {
      IEGroupElt var2 = this.pC();
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      var1.r.add(this.A.createAssign(this.pC.gy, var2));
      var1.r.add(this.A.createAssign(var2, var3));
      var1.r.add(this.A.createAssign(var3, this.pC.gy));
   }

   void pC(ConverterInstructionEntry var1, OperationType var2, boolean var3, boolean var4, boolean var5) {
      Object var6;
      IEGeneric var7;
      if (((vh)var1.insn).A().length == 1) {
         var6 = this.pC();
         var7 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      } else {
         if (((vh)var1.insn).A().length != 2) {
            throw new RuntimeException("unexpected operand count: " + ((vh)var1.insn).A().length);
         }

         var6 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
         var7 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      }

      IEGeneric var8 = this.pC(var7, var4 ? 1 : 0);
      IEOperation var9 = !var5 ? EUtil.op(var2, (IEGeneric)var6, var8) : EUtil.op(var2, var8, (IEGeneric)var6);
      var1.r.add(this.A.createAssign((IEGeneric)var6, var9));
      if (var3) {
         this.xC(var1);
      }
   }

   private void xC(ConverterInstructionEntry var1) {
      this.wS(var1, 1);
   }

   private void wS(ConverterInstructionEntry var1, int var2) {
      var1.r.add(this.A.createAssign(this.pC.So, EUtil.add(this.pC.So, this.A.createImm(var2, 3))));
   }

   private void ED(ConverterInstructionEntry var1) {
      this.UT(var1, 1);
   }

   private void UT(ConverterInstructionEntry var1, int var2) {
      var1.r.add(this.A.createAssign(this.pC.So, EUtil.sub(this.pC.So, this.A.createImm(var2, 3))));
   }

   void oT(ConverterInstructionEntry var1) {
      this.WR(var1);
      var1.r.remove(var1.r.size() - 2);
      this.pC(var1, 1.0);
   }

   void fI(ConverterInstructionEntry var1) {
      this.WR(var1);
      var1.r.remove(var1.r.size() - 2);
      this.ED(var1);
   }

   boolean WR(ConverterInstructionEntry var1) {
      long var2 = ((vh)var1.insn).gp.kS;
      ArrayList var4 = new ArrayList(Arrays.asList(((vh)var1.insn).A()));
      if (pC(var2, Bv.NS)) {
         var4.add(0, kS);
      }

      if (pC(var2, Bv.vP)) {
         var4.add(kS);
      }

      if (pC(var2, Bv.xC)) {
         var4.add(0, wS);
      }

      if (pC(var2, Bv.ED)) {
         var4.add(wS);
      }

      if (pC(var2, Bv.sO)) {
         this.ED(var1);
      }

      IEUntranslatedInstruction var5 = this.A.createUntranslatedInstruction(var1.address, ((vh)var1.insn).getMnemonic());
      ArrayList var6 = new ArrayList();
      ArrayList var7 = new ArrayList();

      for (int var8 = 0; var8 < var4.size(); var8++) {
         IEGeneric var9 = this.pC.pC((vh)var1.insn, (Av)var4.get(var8), -1, var1.address);
         if ((var8 != 0 || !pC(var2, Bv.WR)) && !pC(var2, Bv.Ab)) {
            var6.add(var9);
            if (var8 == 0 && pC(var2, Bv.fI) || pC(var2, Bv.UO)) {
               var7.add(var9.duplicate());
            }
         } else {
            var7.add(var9);
         }
      }

      var5.setParameterExpressions(var6);
      var5.setReturnExpressions(var7);
      var1.r.add(var5);
      if (pC(var2, Bv.LM)) {
         this.xC(var1);
      } else if (pC(var2, Bv.mv)) {
         this.wS(var1, 2);
      }

      return true;
   }

   void NS(ConverterInstructionEntry var1) {
      this.kS(var1, 3);
   }

   void pC(ConverterInstructionEntry var1, boolean var2, int var3) {
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var5 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
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
            IECond var13 = this.A
               .createCond(
                  EUtil.gtS(var4.slice(var12, var12 + 16), var9),
                  var7,
                  this.A.createCond(EUtil.ltS(var4.slice(var12, var12 + 16), var10), var8, var4.slice(var12, var12 + 8))
               );
            var1.r.add(this.A.createAssign(var4.slice(var11, var11 + 8), var13));
            var11 += 8;
         }

         for (byte var19 = 0; var19 < var6; var19 += 16) {
            IECond var22 = this.A
               .createCond(
                  EUtil.gtS(var5.slice(var19, var19 + 16), var9),
                  var7,
                  this.A.createCond(EUtil.ltS(var5.slice(var19, var19 + 16), var10), var8, var5.slice(var19, var19 + 8))
               );
            var1.r.add(this.A.createAssign(var4.slice(var11, var11 + 8), var22));
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
            IECond var23 = this.A
               .createCond(
                  EUtil.gtS(var4.slice(var20, var20 + 32), var16),
                  var14,
                  this.A.createCond(EUtil.ltS(var4.slice(var20, var20 + 32), var17), var15, var4.slice(var20, var20 + 16))
               );
            var1.r.add(this.A.createAssign(var4.slice(var18, var18 + 16), var23));
            var18 += 16;
         }

         for (byte var21 = 0; var21 < var6; var21 += 32) {
            IECond var24 = this.A
               .createCond(
                  EUtil.gtS(var5.slice(var21, var21 + 32), var16),
                  var14,
                  this.A.createCond(EUtil.ltS(var5.slice(var21, var21 + 32), var17), var15, var5.slice(var21, var21 + 16))
               );
            var1.r.add(this.A.createAssign(var4.slice(var18, var18 + 16), var24));
            var18 += 16;
         }
      }
   }

   void A(ConverterInstructionEntry var1, boolean var2, int var3) {
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var5 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      int var6 = var4.getBitsize();
      int var7 = 0;
      if (var2) {
         var7 = var6 / 2;
      }

      IEVar[] var8 = this.pC.A(var3);
      int var9 = 0;

      for (int var10 = 0; var10 < var6 / 2; var9++) {
         var1.r.add(this.A.createAssign(var8[var9], var4.slice(var7 + var10, var7 + var10 + var3)));
         var10 += var3;
      }

      for (int var12 = 0; var12 < var6 / 2; var9++) {
         var1.r.add(this.A.createAssign(var8[var9], var5.slice(var7 + var12, var7 + var12 + var3)));
         var12 += var3;
      }

      var9 = 0;

      for (int var13 = 0; var13 < var6 / 2; var9++) {
         var1.r.add(this.A.createAssign(var4.slice(2 * var13, 2 * var13 + var3), var8[var9]));
         var13 += var3;
      }

      for (int var14 = 0; var14 < var6 / 2; var9++) {
         var1.r.add(this.A.createAssign(var4.slice(2 * var14 + var3, 2 * var14 + var3 + var3), var8[var9]));
         var14 += var3;
      }
   }

   void pC(ConverterInstructionEntry var1, int var2, OperationType var3) {
      this.pC(var1, var2, var3, null, 0);
   }

   void pC(ConverterInstructionEntry var1, int var2, OperationType var3, Boolean var4) {
      this.pC(var1, var2, var3, var4, 0);
   }

   void pC(ConverterInstructionEntry var1, int var2, OperationType var3, Boolean var4, int var5) {
      IEGeneric var6 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var7 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      int var8 = var6.getBitsize();
      Assert.a(var8 % var2 == 0);
      IEOperation var9 = null;
      if (var3 != OperationType.SHL && var3 != OperationType.SAR && var3 != OperationType.SHR) {
         Assert.a(var8 == var7.getBitsize());
      } else {
         var9 = EUtil.gtU(var7, this.A.createImm((var2 >>> var5) - 1, var7.getBitsize()));
      }

      for (int var10 = 0; var10 < var8; var10 += var2) {
         IEAssign var11;
         if (var3 == OperationType.LOG_EQ || var3 == OperationType.GT_S) {
            IECond var17 = this.A
               .createCond(EUtil.op(var3, var6.slice(var10, var10 + var2), var7.slice(var10, var10 + var2)), EUtil.minusOne(var2), EUtil.zero(var2));
            var11 = this.A.createAssign(var6.slice(var10, var10 + var2), var17);
         } else if (var3 == OperationType.ADD
            || var3 == OperationType.SUB
            || var3 == OperationType.ADD_SSAT
            || var3 == OperationType.ADD_USAT
            || var3 == OperationType.SUB_SSAT
            || var3 == OperationType.SUB_USAT) {
            var11 = this.A.createAssign(var6.slice(var10, var10 + var2), EUtil.op(var3, var6.slice(var10, var10 + var2), var7.slice(var10, var10 + var2)));
         } else if (var3 == OperationType.SHL || var3 == OperationType.SHR) {
            IEGeneric var16 = var7.getBitsize() <= var2 ? var7.signExtend(var2) : var7.part(var2);
            if (var5 != 0) {
               var16 = var16.leftShift(var5);
            }

            IECond var18 = this.A.createCond(var9, EUtil.zero(var2), EUtil.op(var3, var6.slice(var10, var10 + var2), var16));
            var11 = this.A.createAssign(var6.slice(var10, var10 + var2), var18);
         } else if (var3 == OperationType.SAR) {
            IEGeneric var12 = var7.getBitsize() <= var2 ? var7.signExtend(var2) : var7.part(var2);
            if (var5 != 0) {
               var12 = var12.leftShift(var5);
            }

            IECond var13 = this.A
               .createCond(
                  var9,
                  this.A.createCond(var6.slice(var10, var10 + var2).msb(), EUtil.minusOne(var2), EUtil.zero(var2)),
                  EUtil.op(var3, var6.slice(var10, var10 + var2), var12)
               );
            var11 = this.A.createAssign(var6.slice(var10, var10 + var2), var13);
         } else {
            if (var3 != OperationType.MUL2_S && var3 != OperationType.MUL2_U) {
               throw new RuntimeException();
            }

            IEOperation var14 = EUtil.op(var3, var6.slice(var10, var10 + var2), var7.slice(var10, var10 + var2));
            if (var4 != null) {
               IEGeneric var15 = var4 ? var14.slice(var14.getBitsize() / 2) : var14.part(var14.getBitsize() / 2);
               var11 = this.A.createAssign(var6.slice(var10, var10 + var2), var15);
            } else {
               var11 = this.A.createAssign(var6.slice(var10, var10 + var2 * 2), var14);
               var10 += var2;
            }
         }

         var1.r.add(var11);
      }
   }

   void pC(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      int var5 = var3.getBitsize();
      Assert.a(var5 == var4.getBitsize());

      var1.r.add(this.A.createAssign(var3, switch (var2) {
         case 0 -> EUtil.andB(var3, var4);
         case 1 -> EUtil.andB(EUtil.notB(var3), var4);
         case 2 -> EUtil.orB(var3, var4);
         case 3 -> EUtil.xorB(var3, var4);
         default -> throw new RuntimeException();
      }));
   }

   void A(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      this.pC.pC(var3.part(var2), var4.part(var2), var1, true);
      if (this.pC(var3) && var3.getBitsize() > var2) {
         int var5 = Math.min(var3.getBitsize(), 128);
         var1.r.add(this.A.createAssign(var3.slice(var2, var5), EUtil.zero(var5 - var2)));
      }
   }

   void vP(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      this.pC.pC(this.pC.So, EUtil.zero(this.pC.So.getBitsize()), var1);
      this.kS(var1, 0);

      for (int var4 = 0; var4 < 8; var4++) {
         var1.r.add(this.A.createJump(var1.irAddress + var1.r.size() + 2, EUtil.ne(var3.bit(var4 * 8 + 7), this.pC.OI)));
         IEMem var5 = this.A.createMem(EUtil.add(this.pC.Ek, EUtil.imm(var4, this.pC.Ek.getBitsize())), 8);
         IEGeneric var6 = var2.slice(var4 * 8, var4 * 8 + 8);
         var1.r.add(this.A.createAssign(var5, var6));
      }
   }
}
