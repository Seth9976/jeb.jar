package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.ArrayList;
import java.util.List;

public class Lb {
   uq q;
   IERoutineContext RF;

   public Lb(uq var1) {
      this.q = var1;
   }

   public void q(fA var1, List var2, long var3) {
      CW var5 = var1.RF()[0];
      CZ var6 = this.q.q(var1, var5);
      ii var7 = this.q.q(var1, var1.RF()[1], var3, var5.getOperandBitsize(), this.q.er);
      this.q(var1, var2, var6, var7);
   }

   public void RF(fA var1, List var2, long var3) {
      CW var5 = var1.RF()[0];
      CZ var6 = this.q.q(var1, var5);
      ii var7 = this.q.q(var1, var1.RF()[1], var3, var5.getOperandBitsize(), this.q.er);
      var7 = new ii(EUtil.notB(var7.q()), var7.RF());
      this.q(var1, var2, var6, var7);
   }

   public void q(fA var1, List var2) {
      CZ var3 = this.q.q(var1, var1.RF()[0]);
      CW[] var4 = var1.RF()[1].oW();
      int var5;
      int var6;
      if (var1.RF()[1].getOperandType() == 7 && var4.length >= 2) {
         var5 = (int)var4[0].getOperandValue();
         var6 = (int)var4[1].getOperandValue();
      } else {
         var5 = (int)var1.RF()[1].getOperandValue();
         var6 = 0;
      }

      IEGeneric[] var7 = new IEGeneric[var1.RF()[0].getOperandBitsize() >>> 4];

      for (int var8 = 0; var8 < var7.length; var8++) {
         if (var8 * 16 == var6) {
            var7[var8] = this.RF.createImm(var5, 16);
         } else {
            var7[var8] = var3.RF().slice(var8 * 16, (var8 + 1) * 16);
         }
      }

      this.q.q(var1, var2, var3, EUtil.compose(this.RF, var7));
   }

   public void xK(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var6;
      IEGeneric var7;
      if (var1.RF().length == 2) {
         var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      } else {
         var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[2], var3);
      }

      ii var8 = this.q.q(DH.eo.valueOf(var1.Dw().q()), var6, var7, var5.gO(), this.q.er, var1.getProcessorMode());
      this.q(var1, var2, var5, var8);
   }

   public void Dw(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var6;
      if (var1.RF().length == 1) {
         var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
      } else {
         var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      }

      ii var7 = this.q.q(DH.eo.valueOf(var1.Dw().q()), var6, null, var5.gO(), this.q.er, var1.getProcessorMode());
      this.q(var1, var2, var5, var7);
   }

   protected void q(fA var1, List var2, CZ var3, ii var4) {
      if (var4.xK()) {
         IEVar var5 = this.q.er;
         var2.add(this.RF.createAssign(var5, this.q.RF()));
      } else {
         this.q.RF();
      }

      if (var1.Dw().RF()) {
         this.q.Of.q(var4.q(), var2);
         if (var4.RF() != null) {
            var2.add(this.RF.createAssign(this.q.RF(), var4.RF()));
         }

         this.q.q(var1, var2, var3, var4.q());
      } else {
         this.q.q(var1, var2, var3, var4.q(), true);
      }
   }

   public void RF(fA var1, List var2) {
      CZ var3 = this.q.q(var1, var1.RF()[0]);
      int var4 = (int)var1.RF()[1].getOperandValue();
      this.q.q(var1, var2, var3, this.RF.createCompose(var3.RF().part(16), this.RF.createImm(var4, 16)));
   }

   public void Uv(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.RF);
   }

   public void oW(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.q);
   }

   public void gO(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.xK);
   }

   public void nf(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.Dw);
   }

   public void gP(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.Uv);
   }

   public void za(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.oW);
   }

   public void lm(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.gO);
   }

   public void zz(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.nf);
   }

   public void JY(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.gP);
   }

   public void HF(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.za);
   }

   public void LK(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.lm);
   }

   public void io(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.zz);
   }

   public void qa(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.JY);
   }

   public void Hk(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.nI.HF);
   }

   private void q(fA var1, List var2, long var3, Lb.nI var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var7;
      ii var8;
      if (var1.RF().length == 2) {
         CW var9 = var1.RF()[1];
         var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var8 = this.q.q(var1, var9, var3, var7.getBitsize(), this.q.er);
      } else {
         CW var10 = var1.RF()[2];
         var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var8 = this.q.q(var1, var10, var3, var7.getBitsize(), this.q.er);
      }

      this.q(var1, var2, var5, var6, var7, var8, false);
   }

   public void Me(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, Lb.nI.lm);
   }

   public void PV(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, Lb.nI.q);
   }

   public void oQ(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, Lb.nI.Uv);
   }

   public void xW(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, Lb.nI.xK);
   }

   private void RF(fA var1, List var2, long var3, Lb.nI var5) {
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.q(0), var3);
      ii var7 = this.q.q(var1, var1.q(1), var3, var6.getBitsize(), this.q.RF());
      this.q(var1, var2, var5, null, var6, var7, true);
   }

   public void q(fA var1, List var2, Lb.nI var3, CZ var4, IEGeneric var5, ii var6, boolean var7) {
      boolean var9 = var7 || var1.Dw().RF();
      IEVar var8;
      if (!var6.xK() && (var3.io.HF != Lb.eo.xK || !var9 || var4 == null)) {
         var8 = this.q.RF();
      } else {
         var8 = this.q.er;
         var2.add(this.RF.createAssign(var8, this.q.RF()));
      }

      var5 = var3.q(var5);
      IEGeneric var10 = var6.q();
      var10 = var3.RF(var10);

      YO var11 = switch (var3.q()) {
         case SUB -> throw new UnsupportedConversionException();
         case ADD -> this.q(var5, var10, (IEGeneric)(var3.io.HF == Lb.eo.xK ? var8 : (var3.io.HF == Lb.eo.q ? null : this.RF.createImm(1L, var5.getBitsize()))));
         case AND, OR, XOR -> {
            IEOperation var12 = this.RF.createOperation(var3.q(), var5, var10);
            IEGeneric var13 = var12.duplicate().msb();
            IECond var14 = this.RF.createCond(var12.duplicate(), this.q.Uv(1), this.q.Dw(1));
            yield new YO(var12, var13, var14, var6.RF(), null);
         }
         default -> throw new IllegalConversionException("setflags is not managed for OperationType " + var3.q());
      };
      if (var11 == null) {
         throw new UnsupportedConversionException("Can not process operation type " + var3.q());
      } else {
         ArrayList var17 = new ArrayList();
         IEGeneric var18 = null;
         if (var9) {
            var18 = this.q.Of.q(this.q, var17, var4, var11);
         }

         if (var18 != null) {
            this.q.q(var1, var2, var18, var4.RF());
         }

         if (var4 != null) {
            this.q.q(var1, var2, var4, var11.q(), true);
         }

         if (!var17.isEmpty()) {
            var2.addAll(var17);
         }
      }
   }

   public YO q(List var1, IEGeneric var2, IEGeneric var3, Lb.ej var4) {
      var2 = var4.q(var2);
      var3 = var4.RF(var3);
      IEVar var5 = null;
      if (var4.HF == Lb.eo.xK) {
         var5 = this.q.gO(1);
         var1.add(this.RF.createAssign(var5, this.q.RF()));
      }

      return this.q(var2, var3, (IEGeneric)(var5 != null ? var5 : (var4.HF == Lb.eo.q ? null : EUtil.one(var2.getBitsize()))));
   }

   public YO q(IEGeneric var1, IEGeneric var2, IEGeneric var3) {
      boolean var5 = var3 != null && EUtil.isImmValue(var3, 1L) && var2.isOperation(OperationType.NOT);
      Object var4;
      if (var5) {
         var4 = EUtil.sub(var1, var2.asOperation().getOperand1());
      } else if (EUtil.isImmZero(var1)) {
         var4 = var3 == null ? var2 : EUtil.add(var2, var3.zeroExtend(var1.getBitsize()));
      } else {
         var4 = var3 == null ? EUtil.add(var1, var2) : EUtil.add(var1, var2, var3.zeroExtend(var1.getBitsize()));
      }

      IEGeneric var6 = ((IEGeneric)var4).duplicate().msb();
      IECond var7 = this.RF.createCond(((IEGeneric)var4).duplicate(), this.q.Uv(1), this.q.Dw(1));
      if (var3 == null) {
         IEGeneric var15 = EUtil.buildCarryFlag(var1, var2, (IEGeneric)var4, true, true);
         IEGeneric var17 = EUtil.buildOverflowFlag(var1, var2, (IEGeneric)var4, true);
         return new YO((IEGeneric)var4, var6, var7, var15, var17);
      } else if (var5) {
         IEOperation var14 = EUtil.geU(var1, var2.asOperation().getOperand1());
         IEGeneric var16 = EUtil.buildOverflowFlag(var1, var2.asOperation().getOperand1(), (IEGeneric)var4, false);
         return new YO((IEGeneric)var4, var6, var7, var14, var16);
      } else {
         int var8 = var1.getBitsize() * 2;
         IEOperation var9 = EUtil.add(var1.zeroExtend(var8), var2.zeroExtend(var8), var3.zeroExtend(var8));
         IEOperation var10 = EUtil.add(var1.signExtend(var8), var2.signExtend(var8), var3.zeroExtend(var8));
         IEGeneric var11 = var9.duplicate().part(var1.getBitsize());
         IECond var12 = this.RF.createCond(EUtil.eq(var11.duplicate().zeroExtend(var8), var9), this.q.Uv(1), this.q.Dw(1));
         IECond var13 = this.RF.createCond(EUtil.eq(var11.duplicate().signExtend(var8), var10), this.q.Uv(1), this.q.Dw(1));
         return new YO((IEGeneric)var4, var6, var7, var12, var13);
      }
   }

   public void KT(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, null, false);
   }

   public void q(fA var1, List var2, long var3, OperationType var5) {
      this.q(var1, var2, var3, var5, false);
   }

   public void q(fA var1, List var2, long var3, OperationType var5, boolean var6) {
      CZ var7 = this.q.q(var1, var1.RF()[0]);
      Object var10 = null;
      IEGeneric var8;
      IEGeneric var9;
      if (var5 != null) {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var9 = this.q.q(var1, var1.RF()[2], var8.getBitsize(), var3);
         if (var6) {
            var10 = this.q.Uv(var8.getBitsize());
         } else {
            var10 = this.q.q(var1, var1.RF()[3], var8.getBitsize(), var3);
         }
      } else if (var1.RF().length == 2) {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var9 = this.q.q(var1, var1.RF()[1], var8.getBitsize(), var3);
      } else {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var9 = this.q.q(var1, var1.RF()[2], var8.getBitsize(), var3);
      }

      IEOperation var11 = this.RF.createOperation(OperationType.MUL, var8, var9);
      if (var5 != null) {
         var11 = this.RF.createOperation(var5, (IEGeneric)var10, var11);
      }

      if (var1.Dw().RF()) {
         this.q.Of.q(var11, var2);
      }

      this.q.q(var1, var2, var7, var11);
   }

   public void xK(fA var1, List var2) {
      CZ var3 = this.q.q(var1, var1.RF()[0]);
      int var4 = (int)var1.RF()[1].getOperandValue();
      int var5 = (int)var1.RF()[2].getOperandValue();
      ArrayList var6 = new ArrayList();
      if (var4 > 0) {
         var6.add(var3.RF().part(var4));
      }

      var6.add(this.q.Uv(var5));
      if (var4 + var5 < var1.RF()[0].getOperandBitsize()) {
         var6.add(var3.RF().slice(var4 + var5, var1.RF()[0].getOperandBitsize()));
      }

      IEGeneric var7 = EUtil.compose(this.RF, var6);
      this.q.q(var1, var2, var3, var7);
   }

   public void Gf(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      int var7 = (int)var1.RF()[2].getOperandValue();
      int var8 = (int)var1.RF()[3].getOperandValue();
      ArrayList var9 = new ArrayList();
      if (var7 > 0) {
         var9.add(var5.RF().part(var7));
      }

      var9.add(var6.part(var8));
      if (var7 + var8 < var1.RF()[0].getOperandBitsize()) {
         var9.add(var5.RF().slice(var7 + var8, var1.RF()[0].getOperandBitsize()));
      }

      IEGeneric var10 = EUtil.compose(this.RF, var9);
      this.q.q(var1, var2, var5, var10);
   }

   public void Ef(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      int var7 = (int)var1.RF()[2].getOperandValue();
      int var8 = (int)var1.RF()[3].getOperandValue();
      ArrayList var9 = new ArrayList();
      if (var8 > 0) {
         var9.add(var6.slice(var7, var7 + var8));
      }

      if (var8 < var1.RF()[0].getOperandBitsize()) {
         var9.add(var5.RF().slice(var8, var1.RF()[0].getOperandBitsize()));
      }

      IEGeneric var10 = EUtil.compose(this.RF, var9);
      this.q.q(var1, var2, var5, var10);
   }

   public void cC(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      this.q.q(var1, var2, var5, var6.countSuccessiveBits(false, true, var5.gO()));
   }

   public void sH(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      this.q
         .q(var1, var2, var5, EUtil.xorB(var6.slice(1, var6.getBitsize()), var6.slice(0, var6.getBitsize() - 1)).countSuccessiveBits(false, true, var5.gO()));
   }

   public void CE(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, false);
   }

   public void wF(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true);
   }

   private void q(fA var1, List var2, long var3, boolean var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var7;
      IEGeneric var8;
      if (var1.RF().length > 2) {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var7 = this.q.q(var1, var1.RF()[2], var8.getBitsize(), var3);
      } else {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var7 = this.q.q(var1, var1.RF()[1], var8.getBitsize(), var3);
      }

      if (var5) {
         this.q.q(var1, var2, var6, this.RF.createCompose(var7.part(16), var8.slice(16, 32)));
      } else {
         this.q.q(var1, var2, var6, this.RF.createCompose(var8.part(16), var7.slice(16, 32)));
      }
   }

   public void If(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      int var7 = var1.RF()[0].getOperandBitsize();
      IEGeneric[] var8 = new IEGeneric[var7];

      for (int var9 = 0; var9 < var7; var9++) {
         var8[var7 - 1 - var9] = var6.bit(var9);
      }

      this.q.q(var1, var2, var5, EUtil.compose(this.RF, var8));
   }

   public void Dz(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      int var7 = var1.RF()[0].getOperandBitsize() >>> 3;
      IEGeneric[] var8 = new IEGeneric[var7];

      for (int var9 = 0; var9 < var7; var9++) {
         var8[var9] = var6.slice((var7 - 1 - var9) * 8, (var7 - var9) * 8);
      }

      this.q.q(var1, var2, var5, EUtil.compose(this.RF, var8));
   }

   public void mI(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      int var7 = var1.RF()[0].getOperandBitsize() >>> 3;
      IEGeneric[] var8 = new IEGeneric[var7];

      for (byte var9 = 0; var9 < var7; var9 += 2) {
         var8[var9] = var6.slice((var9 + 1) * 8, (var9 + 2) * 8);
         var8[var9 + 1] = var6.slice(var9 * 8, (var9 + 1) * 8);
      }

      this.q.q(var1, var2, var5, EUtil.compose(this.RF, var8));
   }

   public void jq(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      int var7 = var1.RF()[0].getOperandBitsize() >>> 3;
      IEGeneric[] var8 = new IEGeneric[var7];

      for (byte var9 = 0; var9 < var7; var9 += 4) {
         var8[var9] = var6.slice((var9 + 3) * 8, (var9 + 4) * 8);
         var8[var9 + 1] = var6.slice((var9 + 2) * 8, (var9 + 3) * 8);
         var8[var9 + 2] = var6.slice((var9 + 1) * 8, (var9 + 2) * 8);
         var8[var9 + 3] = var6.slice(var9 * 8, (var9 + 1) * 8);
      }

      this.q.q(var1, var2, var5, EUtil.compose(this.RF, var8));
   }

   public void ui(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      IEGeneric[] var7 = new IEGeneric[]{var6.slice(8, 16), var6.slice(0, 8).signExtend(24)};
      this.q.q(var1, var2, var5, EUtil.compose(this.RF, var7));
   }

   public void TX(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, true);
   }

   public void Rr(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, false);
   }

   private void RF(fA var1, List var2, long var3, boolean var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      int var8 = this.q.q(var1, var1.RF()[2], var3);
      int var9 = this.q.q(var1, var1.RF()[3], var3);
      int var10 = var8 + var9 - 1;
      if (var10 < var1.RF()[0].getOperandBitsize()) {
         IEGeneric var11 = var7.slice(var8, var10 + 1);
         this.q.q(var1, var2, var6, EUtil.extend(var11, var6.gO(), var5));
      } else if (var1.getProcessorMode() == 64) {
         this.q.yn.q(var1, var2, var3, var8, var8 + var9 - 1, true, var5);
      } else {
         var2.add(this.q.q(var1, var3, true, var6.q()));
      }
   }

   public void EB(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, OperationType.DIV_S);
   }

   public void Xo(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, OperationType.DIV_U);
   }

   public void RF(fA var1, List var2, long var3, OperationType var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var7;
      IEGeneric var8;
      if (var1.RF().length > 2) {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var7 = this.q.q(var1, var1.RF()[2], var8.getBitsize(), var3);
      } else {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var7 = this.q.q(var1, var1.RF()[1], var8.getBitsize(), var3);
      }

      IEGeneric var9 = this.RF.createOperation(var5, var8, var7).zeroExtend(var6.gO());
      IEOperation var10 = EUtil.eq(var7.duplicate(), this.q.Uv(var7.getBitsize()));
      this.q.q(var1, var2, var6, this.RF.createCond(var10, this.q.Uv(var6.gO()), var9));
   }

   public void Bu(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.q, true);
   }

   public void IN(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.RF, true);
   }

   public void rL(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.xK, true);
   }

   public void eJ(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.Dw, true);
   }

   public void YN(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.q, false);
   }

   public void Rv(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.RF, false);
   }

   public void zx(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.xK, false);
   }

   private void q(fA var1, List var2, long var3, Lb.oM var5, boolean var6) {
      CW var7 = var1.RF()[0];
      CZ var8 = this.q.q(var1, var7);
      IEGeneric var9;
      if (var1.RF().length > 1) {
         var9 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      } else {
         var9 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         if (var1.RF()[0].getOperandType() == 7) {
            var9 = this.q.q(var1, var1.RF()[0].oW()[0]).RF();
         }
      }

      IEGeneric var10 = q(this.RF, null, var9, var5, var6, var7.getOperandBitsize());
      this.q.q(var1, var2, var8, var10);
   }

   static IEGeneric q(IERoutineContext var0, IEGeneric var1, IEGeneric var2, Lb.oM var3, boolean var4, int var5) {
      switch (var3) {
         case q:
            IEGeneric var10 = var2.part(8);
            return q(var1, EUtil.extend(var10, var5, var4));
         case RF:
            IEGeneric var9 = EUtil.extend(var2.duplicate().part(8), 16, var4);
            IEGeneric var7 = EUtil.extend(var2.slice(16, 24), 16, var4);
            if (var1 != null) {
               return var0.createCompose(EUtil.add(var1.part(16), var9), EUtil.add(var1.slice(16, 32), var7));
            }

            return var0.createCompose(var9, var7);
         case xK:
            IEGeneric var8 = var2.part(16);
            return q(var1, EUtil.extend(var8, var5, var4));
         case Dw:
            IEGeneric var6 = var2.part(32);
            return q(var1, EUtil.extend(var6, var5, var4));
         default:
            return null;
      }
   }

   static IEGeneric q(IEGeneric var0, IEGeneric var1) {
      if (var0 == null && var1 == null) {
         return null;
      } else if (var0 == null) {
         return var1;
      } else {
         return (IEGeneric)(var1 == null ? var0 : EUtil.add(var0, var1));
      }
   }

   private static enum CU {
      q,
      RF,
      xK;
   }

   static enum ej {
      q(Lb.CU.q, Lb.CU.q, null),
      RF(Lb.CU.q, Lb.CU.RF, null),
      xK(Lb.CU.RF, Lb.CU.q, null),
      Dw(Lb.CU.q, Lb.CU.q, Lb.eo.q),
      Uv(Lb.CU.q, Lb.CU.q, Lb.eo.xK),
      oW(Lb.CU.q, Lb.CU.RF, Lb.eo.RF),
      gO(Lb.CU.q, Lb.CU.RF, Lb.eo.xK),
      nf(Lb.CU.xK, Lb.CU.RF, Lb.eo.xK),
      gP(Lb.CU.RF, Lb.CU.q, Lb.eo.RF),
      za(Lb.CU.RF, Lb.CU.q, Lb.eo.xK),
      lm(Lb.CU.xK, Lb.CU.RF, Lb.eo.RF);

      private final Lb.CU zz;
      private final Lb.CU JY;
      private final Lb.eo HF;

      private ej(Lb.CU var3, Lb.CU var4, Lb.eo var5) {
         this.zz = var3;
         this.JY = var4;
         this.HF = var5;
      }

      IEGeneric q(IEGeneric var1) {
         return this.q(var1, this.zz);
      }

      IEGeneric RF(IEGeneric var1) {
         return this.q(var1, this.JY);
      }

      private IEGeneric q(IEGeneric var1, Lb.CU var2) {
         switch (var2) {
            case RF:
               return EUtil.notB(var1);
            case xK:
               return EUtil.zero(var1.getBitsize());
            case q:
               return var1;
            default:
               throw new UnsupportedConversionException();
         }
      }
   }

   private static enum eo {
      q,
      RF,
      xK;
   }

   static enum nI {
      q(OperationType.ADD, Lb.ej.Dw),
      RF(OperationType.ADD, Lb.ej.Uv),
      xK(OperationType.AND, Lb.ej.q),
      Dw(OperationType.AND, Lb.ej.RF),
      Uv(OperationType.XOR, Lb.ej.q),
      oW(OperationType.XOR, Lb.ej.RF),
      gO(OperationType.OR, Lb.ej.RF),
      nf(OperationType.OR, Lb.ej.q),
      gP(OperationType.ADD, Lb.ej.gP),
      za(OperationType.ADD, Lb.ej.za),
      lm(OperationType.ADD, Lb.ej.oW),
      zz(OperationType.ADD, Lb.ej.lm),
      JY(OperationType.ADD, Lb.ej.gO),
      HF(OperationType.ADD, Lb.ej.nf);

      private final OperationType LK;
      private final Lb.ej io;

      private nI(OperationType var3, Lb.ej var4) {
         this.LK = var3;
         this.io = var4;
      }

      OperationType q() {
         return this.LK;
      }

      IEGeneric q(IEGeneric var1) {
         return this.io.q(var1);
      }

      IEGeneric RF(IEGeneric var1) {
         return this.io.RF(var1);
      }
   }

   public static enum oM {
      q,
      RF,
      xK,
      Dw;
   }
}
