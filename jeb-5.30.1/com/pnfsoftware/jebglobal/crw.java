package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;

class crw {
   crx q;
   IERoutineContext RF;

   crw(crx var1) {
      this.q = var1;
   }

   private boolean q(IEGeneric var1) {
      return var1 instanceof IEVar || var1 instanceof IESlice && ((IESlice)var1).getWholeExpression() instanceof IEVar && ((IESlice)var1).getBitStart() == 0;
   }

   private IEVar RF(IEGeneric var1) {
      return var1 instanceof IEVar ? (IEVar)var1 : (IEVar)((IESlice)var1).getWholeExpression();
   }

   private boolean xK(IEGeneric var1) {
      return var1 instanceof IEMem;
   }

   void q(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      if ((!this.q(var2) || !this.q(var3)) && (!this.xK(var2) || !this.q(var3))) {
         if (!this.q(var2) || !this.xK(var3)) {
            throw new RuntimeException("Unsupported");
         }

         var1.r.add(this.RF.createAssign(var2.part(32), var3.part(32)));
         var1.r.add(this.RF.createAssign(var2.slice(32, 128), EUtil.zero(96)));
      } else {
         var1.r.add(this.RF.createAssign(var2.part(32), var3.part(32)));
      }
   }

   void RF(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      if ((!this.q(var2) || !this.q(var3)) && (!this.xK(var2) || !this.q(var3))) {
         if (!this.q(var2) || !this.xK(var3)) {
            throw new RuntimeException("Unsupported");
         }

         var1.r.add(this.RF.createAssign(var2.part(64), var3.part(64)));
         var1.r.add(this.RF.createAssign(var2.slice(64, 128), EUtil.zero(64)));
      } else {
         var1.r.add(this.RF.createAssign(var2.part(64), var3.part(64)));
      }
   }

   void q(ConverterInstructionEntry var1, int var2, int var3) {
      IEGeneric var4 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var5 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      this.q.q(var4.part(var2), var5.part(var2), var1, true);
      if ((var3 == -1 || var3 > var2) && this.q(var4)) {
         IEVar var6 = this.RF(var4);
         if (var3 == -1) {
            var3 = var6.getBitsize();
            if (var3 < var2) {
               return;
            }
         }

         int var7 = Math.min(var6.getBitsize(), var3);
         var1.r.add(this.RF.createAssign(var6.slice(var2, var7), EUtil.zero(var7 - var2)));
      }
   }

   void xK(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      this.q.q(var2, this.RF.createCompose(var3.bit(63), var3.bit(127), EUtil.zero(var2.getBitsize() - 2)), var1, true);
   }

   void q(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      var1.r.add(this.RF.createAssign(var3.part(var2), var4.slice(var2, var2 * 2)));
   }

   void RF(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      var1.r.add(this.RF.createAssign(var3.slice(var2, var2 * 2), var4.part(var2)));
   }

   void xK(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      if (var3.getBitsize() > var2) {
         var1.r.add(this.RF.createAssign(var3.slice(var2, var2 * 2), var4.part(var2)));
      } else {
         if (var4.getBitsize() <= var2) {
            throw new RuntimeException();
         }

         var1.r.add(this.RF.createAssign(var3.part(var2), var4.slice(var2, var2 * 2)));
      }
   }

   void q(ConverterInstructionEntry var1, int var2, int var3, int var4) {
      IEGeneric var5 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var6 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      OperationType var7 = null;
      boolean var8 = false;
      int var9 = 0;

      for (int var10 = 0; var10 < var3; var10++) {
         IEGeneric var11 = var5.slice(var9, var9 + var4);
         IEGeneric var12 = var6.slice(var9, var9 + var4);
         Object var13;
         if (var2 >= 0 && var2 <= 6) {
            if (var7 == null) {
               switch (var2) {
                  case 0:
                     var7 = OperationType.FADD;
                     break;
                  case 1:
                     var7 = OperationType.FSUB;
                     break;
                  case 2:
                     var7 = OperationType.FMUL;
                     break;
                  case 3:
                     var7 = OperationType.FDIV;
                     break;
                  case 4:
                     var7 = OperationType.AND;
                     break;
                  case 5:
                     var7 = OperationType.OR;
                     break;
                  case 6:
                     var7 = OperationType.XOR;
                     break;
                  default:
                     throw new RuntimeException();
               }
            }

            var13 = this.RF.createOperation(var7, var11, var12);
         } else if (var2 == 7) {
            var13 = this.RF.createOperation(OperationType.AND, this.RF.createOperation(OperationType.NOT, var11), var12);
         } else if (var2 >= 8 && var2 <= 9) {
            if (var7 == null) {
               switch (var2) {
                  case 8:
                     var7 = OperationType.FLT;
                     break;
                  case 9:
                     var7 = OperationType.FGT;
                     break;
                  default:
                     throw new RuntimeException();
               }
            }

            var13 = this.RF.createCond(this.RF.createOperation(var7, var11, var12), var11.duplicate(), var12.duplicate());
         } else {
            if (var2 < 10 || var2 > 17) {
               throw new RuntimeException();
            }

            if (var7 == null) {
               int var14 = var2 - 10;
               if (var14 >= 4) {
                  var8 = true;
                  var14 -= 4;
               }

               switch (var14) {
                  case 0:
                     var7 = OperationType.FEQ;
                     break;
                  case 1:
                     var7 = OperationType.FLT;
                     break;
                  case 2:
                     var7 = OperationType.FLE;
                     break;
                  case 3:
                     var7 = OperationType.FUN;
                     break;
                  default:
                     throw new RuntimeException();
               }
            }

            IEImm var16 = EUtil.minusOne(var11.getBitsize());
            IEImm var15 = EUtil.zero(var11.getBitsize());
            if (!var8) {
               var13 = this.RF.createCond(this.RF.createOperation(var7, var11, var12), var16, var15);
            } else {
               var13 = this.RF.createCond(this.RF.createOperation(var7, var11, var12), var15, var16);
            }
         }

         var1.r.add(this.RF.createAssign(var11.duplicate(), (IEGeneric)var13));
         var9 += var4;
      }
   }

   void q(ConverterInstructionEntry var1, int var2, boolean var3) {
      IEGeneric var4 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var5 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      IEGeneric var6 = var4.part(var2);
      IEGeneric var7 = var5.part(var2);
      int var8 = var1.irAddress;
      var1.r.add(this.RF.createJump(var8 + 5, EUtil.op(OperationType.NOT, this.RF.createOperation(OperationType.FEQ, var6, var7))));
      this.q.q(this.q.GC, this.q.hP, var1);
      this.q.q(this.q.CB, this.q.Eq, var1);
      this.q.q(this.q.WX, this.q.Eq, var1);
      var1.r.add(this.RF.createJump(var8 + 18));
      var1.r.add(this.RF.createJump(var8 + 10, EUtil.op(OperationType.NOT, this.RF.createOperation(OperationType.FGT, var6.duplicate(), var7.duplicate()))));
      this.q.q(this.q.GC, this.q.Eq, var1);
      this.q.q(this.q.CB, this.q.Eq, var1);
      this.q.q(this.q.WX, this.q.Eq, var1);
      var1.r.add(this.RF.createJump(var8 + 18));
      var1.r.add(this.RF.createJump(var8 + 15, EUtil.op(OperationType.NOT, this.RF.createOperation(OperationType.FLT, var6.duplicate(), var7.duplicate()))));
      this.q.q(this.q.GC, this.q.Eq, var1);
      this.q.q(this.q.CB, this.q.Eq, var1);
      this.q.q(this.q.WX, this.q.hP, var1);
      var1.r.add(this.RF.createJump(var8 + 18));
      this.q.q(this.q.GC, this.q.hP, var1);
      this.q.q(this.q.CB, this.q.hP, var1);
      this.q.q(this.q.WX, this.q.hP, var1);
      this.q.q(this.q.Cl, this.q.Eq, var1);
      this.q.q(this.q.C, this.q.Eq, var1);
      this.q.q(this.q.KF, this.q.Eq, var1);
   }

   void Dw(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      var1.r.add(this.RF.createAssign(var2.part(32), EUtil.createConversionOperation(OperationType.INT2FP, var3.part(32), 32)));
      var1.r.add(this.RF.createAssign(var2.slice(32, 64), EUtil.createConversionOperation(OperationType.INT2FP, var3.slice(32, 64), 32)));
   }

   void q(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      var1.r.add(this.RF.createAssign(var3.part(32), EUtil.createConversionOperation(OperationType.FP2INT, var4.part(32), 32)));
      var1.r.add(this.RF.createAssign(var3.slice(32, 64), EUtil.createConversionOperation(OperationType.FP2INT, var4.slice(32, 64), 32)));
   }

   void Uv(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      var1.r.add(this.RF.createAssign(var2.part(32), EUtil.createConversionOperation(OperationType.INT2FP, var3.part(32), 32)));
   }

   void RF(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      var1.r.add(this.RF.createAssign(var3.part(32), EUtil.createConversionOperation(OperationType.FP2INT, var4.part(32), 32)));
   }

   void xK(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      var1.r.add(this.RF.createAssign(var3.part(32), EUtil.createConversionOperation(OperationType.FP2INT, var4.part(64), 32)));
   }

   void oW(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      var1.r.add(this.RF.createAssign(var2.part(64), EUtil.createConversionOperation(OperationType.INT2FP, var3.part(32), 64)));
   }

   void gO(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      var1.r.add(this.RF.createAssign(var2.part(32), EUtil.createConversionOperation(OperationType.FP2FP, var3.part(64), 32)));
   }

   void nf(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      var1.r.add(this.RF.createAssign(var2.part(64), EUtil.createConversionOperation(OperationType.FP2FP, var3.part(32), 64)));
   }

   void gP(ConverterInstructionEntry var1) {
      this.xK(var1, true);
   }

   void q(ConverterInstructionEntry var1, OperationType var2, int var3, int var4, int var5, int var6) {
      this.q(var1, var2, var3, var4, var5, var6, false);
   }

   void q(ConverterInstructionEntry var1, OperationType var2, int var3, int var4, int var5, int var6, boolean var7) {
      IEGeneric var8 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var9 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      Assert.a(var4 == 32 || var4 == 64);
      Assert.a(var5 == 32 || var5 == 64);
      IEVar[] var10 = var5 == 32 ? this.q.SM : this.q.bj;
      int var11 = 0;

      for (int var12 = 0; var12 < var3; var12++) {
         var1.r.add(this.RF.createAssign(var10[var12], EUtil.createConversionOperation(var2, var9.slice(var11, var11 + var4), var5)));
         var11 += var4;
      }

      var11 = 0;

      for (int var14 = 0; var14 < var3; var14++) {
         var1.r.add(this.RF.createAssign(var8.slice(var11, var11 + var5), var10[var14]));
         var11 += var5;
      }

      Assert.a(var6 == 128 || var6 == 64);
      int var15 = var6 - var3 * var5;
      if (var15 > 0) {
         var1.r.add(this.RF.createAssign(var8.slice(var3 * var5, 128), alu.q(0L, var15)));
      }
   }

   void za(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      var1.r.add(this.RF.createAssign(this.q.iK, var2));
   }

   void lm(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      var1.r.add(this.RF.createAssign(var2, this.q.iK));
   }

   void zz(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      int var4 = ((ctc)var1.insn).lm();
      IEGeneric var5 = this.q.Bs.part(var4);
      byte var6 = 0;

      for (int var7 = 0; var7 < 16; var7++) {
         IEGeneric var8 = var2.slice(var6, var6 + 8);
         IEMem var9 = this.RF.createMem(EUtil.add(var5, EUtil.imm(var7, var4)), 8);
         var1.r.add(this.RF.createJump(var1.irAddress + 2 * var7 + 2, EUtil.notB(var3.bit(var6 + 7))));
         var1.r.add(this.RF.createAssign(var9, var8));
         var6 += 8;
      }
   }

   void JY(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      var1.r.add(this.RF.createAssign(var2, var3));
      if (this.q(var2)) {
         IEVar var4 = this.RF(var2);
         if (var4 != null && var4.getBitsize() > var3.getBitsize()) {
            int var5 = var4.getBitsize() - var3.getBitsize();
            var1.r.add(this.RF.createAssign(var4.slice(var3.getBitsize()), alu.q(0L, var5)));
         }
      }
   }
}
