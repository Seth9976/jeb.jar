package com.pnfsoftware.jebglobal;

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
import java.util.List;

class crp {
   crx q;
   IERoutineContext RF;

   crp(crx var1) {
      this.q = var1;
   }

   void q(IEGeneric var1, List var2) {
      this.q.q(var1, var2);
   }

   void q(IEGeneric var1, IEGeneric var2, List var3) {
      this.q.q(var1, var2, var3);
   }

   void q(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      this.RF(var1, var2, var3, var4);
      this.xK(var1, var2, var3, var4);
   }

   void RF(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      var4.add(this.RF.createAssign(this.q.WX, this.RF.createOperation(OperationType.CARRY, var1, var2)));
   }

   void xK(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      var4.add(this.RF.createAssign(this.q.Cl, EUtil.buildOverflowFlag(var1, var2, var3, true)));
   }

   void Dw(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      this.Uv(var1, var2, var3, var4);
      this.oW(var1, var2, var3, var4);
   }

   void Uv(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      var4.add(this.RF.createAssign(this.q.WX, this.RF.createOperation(OperationType.LT_U, var1, var2)));
   }

   void oW(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      var4.add(this.RF.createAssign(this.q.Cl, EUtil.buildOverflowFlag(var1, var2, var3, false)));
   }

   void RF(IEGeneric var1, IEGeneric var2, List var3) {
      IEGeneric var4 = this.RF
         .createOperation(OperationType.SHR, var1, this.RF.createOperation(OperationType.SUB, var2, this.RF.createImm(1L, var2.getBitsize())))
         .lsb();
      IEOperation var5 = this.RF.createOperation(OperationType.LT_U, var2, this.RF.createImm(var1.getBitsize(), var2.getBitsize()));
      var3.add(this.RF.createAssign(this.q.WX, this.RF.createCond(this.RF.createOperation(OperationType.LOG_AND, var5, var2), var4, this.q.WX)));
   }

   static IEGeneric q(IEGeneric var0, IEGeneric var1, IEGeneric var2) {
      return EUtil.xorB(EUtil.xorB(var0, var1), var2).slice(4, 5);
   }

   static IEGeneric q(IEGeneric var0, IEGeneric var1, IEGeneric var2, boolean var3) {
      return var3 ? EUtil.ltU(var2, var0) : EUtil.ltU(var0, var1);
   }

   void q(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      int var4 = var3.getBitsize();
      IEGeneric var5 = this.q.RF((ctc)var1.insn, 1, var4, var1.address);
      Assert.a(var4 == var5.getBitsize());
      Assert.a(crx.q(var3, var5));
      IEGeneric var6 = this.q.RF(var4, var1.r);
      this.q.q(var6, var3, var1, false);
      Object var7 = var5;
      if (!(var5 instanceof IEImm)) {
         var7 = this.q.Dw(var4);
         this.q.q((IEGeneric)var7, var5, var1, false);
      }

      IEVar var8 = this.q.Uv(var4);
      this.q.q(var8, this.RF.createOperation(OperationType.ADD, var6, (IEGeneric)var7), var1);
      this.q.q(var3, var8, var1);
      if (!var2) {
         this.q(var8, var1.r);
         this.q.q(var6, (IEGeneric)var7, var8, var1.r);
         this.q(var6, (IEGeneric)var7, var8, var1.r);
      }
   }

   void q(ConverterInstructionEntry var1) {
      boolean var2 = true;
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.RF((ctc)var1.insn, 1, var3.getBitsize(), var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      Assert.a(crx.q(var3, var4));
      IEGeneric var5 = this.q.RF(var3.getBitsize(), var1.r);
      this.q.q(var5, var3, var1, false);
      IEOperation var6 = this.RF.createOperation(OperationType.ADD, var3, var4);
      this.q.q(var3, var6, var1);
      this.q(var3, var1.r);
      if (var3 == var4) {
         var4 = var5;
         var2 = false;
      }

      this.q.q(var5, var4, var3, var1.r);
      this.q(var5, var4, var3, var1.r);
      if (var2) {
         this.q.q(var4, var5, var1);
      }
   }

   void RF(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.RF((ctc)var1.insn, 1, var2.getBitsize(), var1.address);
      IEGeneric var4 = this.q.os.part(var2.getBitsize());
      IEOperation var5 = this.RF.createOperation(OperationType.SUB, var4, var2);
      this.q(var5, var1.r);
      this.Dw(var4, var2, var5, var1.r);
      this.q.q(var4, var2, var5, var1.r);
      int var6 = var1.irAddress + var1.r.size();
      var1.r.add(this.RF.createJump(var6 + 3, this.RF.createOperation(OperationType.LOG_EQ, this.q.GC, this.q.Eq)));
      var1.r.add(this.RF.createAssign(var2, var3));
      var1.r.add(this.RF.createJump(var6 + 4, null));
      var1.r.add(this.RF.createAssign(var4, var2));
   }

   void RF(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      int var4 = var3.getBitsize();
      IEGeneric var5 = this.q.RF((ctc)var1.insn, 1, var4, var1.address);
      Assert.a(var4 == var5.getBitsize());
      Assert.a(crx.q(var3, var5));
      if (var2) {
         this.q.q(var3, EUtil.add(var3, EUtil.add(var5, this.q.WX.zeroExtend(var4))), var1);
      } else {
         IEVar var6 = this.q.xK(var4);
         this.q.q(var6, EUtil.add(var3, this.q.WX.zeroExtend(var4)), var1);
         IEVar var7 = this.q.Dw(var4);
         this.q.q(var7, var5, var1);
         this.q.q(var3, EUtil.add(var6, var7), var1);
         this.q(var3, var1.r);
         var1.r
            .add(
               this.RF
                  .createAssign(
                     this.q.Cl,
                     EUtil.orL(
                        EUtil.andL(
                           EUtil.eq(this.q.WX, this.q.hP),
                           EUtil.andL(EUtil.eq(var6, this.q.hP.zeroExtend(var4)._shl(var4 - 1)), EUtil.geS(var7, this.q.q(var7)))
                        ),
                        EUtil.buildOverflowFlag(var6, var7, var3, true)
                     )
                  )
            );
         var1.r
            .add(
               this.RF
                  .createAssign(this.q.C, EUtil.orL(EUtil.andL(EUtil.eq(this.q.WX, this.q.hP), EUtil.eq(var6.part(4), EUtil.zero(4))), q(var6, var7, var3)))
            );
         var1.r
            .add(
               this.RF
                  .createAssign(this.q.WX, EUtil.orL(EUtil.andL(EUtil.eq(this.q.WX, this.q.hP), EUtil.eq(var6, this.q.q(var6))), q(var6, var7, var3, true)))
            );
      }
   }

   void xK(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      int var4 = var3.getBitsize();
      IEGeneric var5 = this.q.RF((ctc)var1.insn, 1, var4, var1.address);
      Assert.a(var4 == var5.getBitsize());
      Assert.a(crx.q(var3, var5));
      IEGeneric var6 = this.q.RF(var3.getBitsize(), var1.r);
      this.q.q(var6, var3, var1, false);
      Object var7 = var5;
      if (!(var5 instanceof IEImm)) {
         var7 = this.q.Dw(var4);
         this.q.q((IEGeneric)var7, var5, var1, false);
      }

      IEVar var8 = this.q.Uv(var4);
      this.q.q(var8, this.RF.createOperation(OperationType.SUB, var6, (IEGeneric)var7), var1);
      this.q.q(var3, var8, var1);
      if (!var2) {
         this.q(var8, var1.r);
         this.q.q(var6, (IEGeneric)var7, var8, var1.r);
         this.Dw(var6, (IEGeneric)var7, var8, var1.r);
      }
   }

   void Dw(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.RF((ctc)var1.insn, 1, var3.getBitsize(), var1.address);
      int var5 = var3.getBitsize();
      Assert.a(var5 == var4.getBitsize());
      Assert.a(crx.q(var3, var4));
      if (var2) {
         this.q.q(var3, EUtil.sub(var3, EUtil.add(var4, this.q.WX.zeroExtend(var5))), var1);
      } else {
         IEVar var6 = this.q.xK(var5);
         this.q.q(var6, var3, var1, false);
         IEVar var7 = this.q.Dw(var5);
         this.q.q(var7, EUtil.add(var4, this.q.WX.zeroExtend(var5)), var1);
         this.q.q(var3, EUtil.sub(var6, var7), var1);
         this.q(var3, var1.r);
         var1.r
            .add(
               this.RF
                  .createAssign(
                     this.q.Cl,
                     EUtil.orL(
                        EUtil.andL(
                           EUtil.eq(this.q.WX, this.q.hP),
                           EUtil.andL(EUtil.eq(var7, this.q.hP.zeroExtend(var5)._shl(var5 - 1)), EUtil.ltS(var6, this.q.q(var6)))
                        ),
                        EUtil.buildOverflowFlag(var6, var7, var3, false)
                     )
                  )
            );
         var1.r
            .add(
               this.RF
                  .createAssign(this.q.C, EUtil.orL(EUtil.andL(EUtil.eq(this.q.WX, this.q.hP), EUtil.eq(var7.part(4), EUtil.zero(4))), q(var6, var7, var3)))
            );
         var1.r
            .add(
               this.RF
                  .createAssign(this.q.WX, EUtil.orL(EUtil.andL(EUtil.eq(this.q.WX, this.q.hP), EUtil.eq(var7, this.q.q(var7))), q(var6, var7, var3, false)))
            );
      }
   }

   void Uv(ConverterInstructionEntry var1, boolean var2) {
      if (var2) {
         var1.r.add(this.RF.createNop());
      } else {
         IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
         IEGeneric var4 = this.q.RF((ctc)var1.insn, 1, var3.getBitsize(), var1.address);
         this.xK(var3, var4, var1.r);
      }
   }

   void xK(IEGeneric var1, IEGeneric var2, List var3) {
      Assert.a(var1.getBitsize() == var2.getBitsize());
      Assert.a(crx.q(var1, var2));
      IEOperation var4 = this.RF.createOperation(OperationType.SUB, var1.duplicate(), var2.duplicate());
      this.q(var4, var3);
      this.Dw(var1, var2, var4, var3);
      this.q.q(var1, var2, var4, var3);
   }

   void oW(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEImm var4 = this.RF.createImm(1L, var3.getBitsize());
      Assert.a(crx.q(var3));
      IEGeneric var5 = null;
      if (!var2) {
         var5 = this.q.RF(var3.getBitsize(), var1.r);
         this.q.q(var5, var3, var1, false);
      }

      IEOperation var6 = this.RF.createOperation(OperationType.ADD, var3, var4);
      this.q.q(var3, var6, var1);
      if (!var2) {
         this.q(var3, var1.r);
         this.q.q(var5, var4, var3, var1.r);
         this.xK(var5, var4, var3, var1.r);
      }
   }

   void gO(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEImm var4 = this.RF.createImm(-1L, var3.getBitsize());
      Assert.a(crx.q(var3));
      IEGeneric var5 = null;
      if (!var2) {
         var5 = this.q.RF(var3.getBitsize(), var1.r);
         this.q.q(var5, var3, var1, false);
      }

      IEOperation var6 = this.RF.createOperation(OperationType.ADD, var3, var4);
      this.q.q(var3, var6, var1);
      if (!var2) {
         this.q(var3, var1.r);
         this.q.q(var5, var4, this.RF.createOperation(OperationType.NOT, var3), var1.r);
         this.xK(var5, var4, var3, var1.r);
      }
   }

   void nf(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEImm var4 = this.RF.createImm(0L, var3.getBitsize());
      Assert.a(crx.q(var3));
      IEGeneric var5 = null;
      if (!var2) {
         var5 = this.q.RF(var3.getBitsize(), var1.r);
         this.q.q(var5, var3, var1, false);
      }

      IEOperation var6 = this.RF.createOperation(OperationType.SUB, var4, var3);
      this.q.q(var3, var6, var1);
      if (!var2) {
         this.q(var3, var1.r);
         this.Dw(var4, var5, var3, var1.r);
         this.q.q(var4, var5, var3, var1.r);
      }
   }

   void gP(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      Assert.a(crx.q(var3));
      int var4 = var3.getBitsize();
      if (var4 == 8) {
         IEGeneric var5 = this.q.os.part(16);
         IEOperation var6 = this.RF.createOperation(OperationType.MUL, this.q.os.part(8).zeroExtend(16), var3.zeroExtend(16));
         var1.r.add(this.RF.createAssign(var5, var6));
         if (var2) {
            return;
         }

         IECond var7 = this.RF.createCond(var5.slice(8, 16), this.q.hP, this.q.Eq);
         var1.r.add(this.RF.createAssign(this.q.WX, var7));
         var1.r.add(this.RF.createAssign(this.q.Cl, var7));
      } else {
         if (var4 != 16 && var4 != 32 && var4 != 64) {
            throw new RuntimeException();
         }

         IEGeneric var11 = this.q.os.part(var4);
         IEGeneric var12 = this.q.fn.part(var4);
         IEVar var13 = this.q.q(this.q.os.getBitsize(), var1.r);
         var1.r.add(this.RF.createAssign(var13.part(this.q.os.getBitsize()), this.q.os));
         IEOperation var8 = this.RF.createOperation(OperationType.MUL, this.q.os.part(var4), var3);
         this.q.q(var11, var8, var1);
         IEOperation var9 = this.RF.createOperation(OperationType.MUL, var13.part(var4).zeroExtend(2 * var4), var3.zeroExtend(2 * var4));
         this.q.q(var12, var9.slice(var4, 2 * var4), var1);
         if (var2) {
            return;
         }

         IECond var10 = this.RF.createCond(var12, this.q.hP, this.q.Eq);
         var1.r.add(this.RF.createAssign(this.q.WX, var10));
         var1.r.add(this.RF.createAssign(this.q.Cl, var10));
      }
   }

   void za(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      Assert.a(crx.q(var3));
      int var4 = var3.getBitsize();
      boolean var5 = ((ctc)var1.insn).io.RF == 63016 || ((ctc)var1.insn).io.RF == 63272;
      if (var5) {
         if (var4 == 8) {
            IEGeneric var6 = this.q.os.part(16);
            IEOperation var7 = this.RF.createOperation(OperationType.MUL, this.q.os.part(8).signExtend(16), var3.signExtend(16));
            var1.r.add(this.RF.createAssign(var6, var7));
            if (var2) {
               return;
            }

            IECond var8 = this.RF.createCond(this.RF.createOperation(OperationType.SUB, var6, var6.part(8).signExtend(16)), this.q.hP, this.q.Eq);
            var1.r.add(this.RF.createAssign(this.q.WX, var8));
            var1.r.add(this.RF.createAssign(this.q.Cl, var8));
         } else {
            if (var4 != 16 && var4 != 32 && var4 != 64) {
               throw new RuntimeException();
            }

            IEGeneric var15 = this.q.os.part(var4);
            IEGeneric var16 = this.q.fn.part(var4);
            IEVar var18 = this.q.q(this.q.os.getBitsize(), var1.r);
            var1.r.add(this.RF.createAssign(var18.part(this.q.os.getBitsize()), this.q.os));
            IEOperation var9 = this.RF.createOperation(OperationType.MUL, this.q.os.part(var4), var3);
            this.q.q(var15, var9, var1);
            IEOperation var10 = this.RF.createOperation(OperationType.MUL, var18.part(var4).signExtend(2 * var4), var3.signExtend(2 * var4));
            this.q.q(var16, var10.slice(var4, 2 * var4), var1);
            if (var2) {
               return;
            }

            IECond var11 = this.RF
               .createCond(this.RF.createOperation(OperationType.SUB, this.RF.createCompose(var15, var16), var15.signExtend(2 * var4)), this.q.hP, this.q.Eq);
            var1.r.add(this.RF.createAssign(this.q.WX, var11));
            var1.r.add(this.RF.createAssign(this.q.Cl, var11));
         }
      } else {
         IEGeneric var17 = this.q.RF(var4, var1.r);
         var1.r.add(this.RF.createAssign(var17, var3));
         IEGeneric var19 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
         Assert.a(var4 == var19.getBitsize());
         boolean var21 = var19.equals(var3);
         int var22 = ((ctc)var1.insn).Dw().length;
         IEOperation var23 = null;
         if (var22 == 2) {
            var23 = this.RF.createOperation(OperationType.MUL, var3, var19);
         } else if (var22 == 3) {
            IEGeneric var12 = this.q.q((ctc)var1.insn, 2, -var4, var1.address);
            var23 = this.RF.createOperation(OperationType.MUL, var19, var12);
         }

         this.q.q(var3, var23, var1);
         if (var2) {
            return;
         }

         if (!var21) {
            var19 = this.q.q((ctc)var1.insn, 1, -2 * var4, var1.address);
            Assert.a(crx.q(var19));
         } else {
            var19 = var17.signExtend(2 * var4);
         }

         IEGeneric var24;
         if (var22 == 3) {
            var24 = this.q.q((ctc)var1.insn, 2, -2 * var4, var1.address);
            Assert.a(crx.q(var24));
         } else {
            if (var22 != 2) {
               throw new RuntimeException();
            }

            var24 = var19;
            var19 = var17.signExtend(2 * var4);
         }

         IEOperation var13 = this.RF.createOperation(OperationType.MUL, var19, var24);
         IECond var14 = this.RF.createCond(this.RF.createOperation(OperationType.SUB, var13, var3.signExtend(2 * var4)), this.q.hP, this.q.Eq);
         var1.r.add(this.RF.createAssign(this.q.WX, var14));
         var1.r.add(this.RF.createAssign(this.q.Cl, var14));
      }
   }

   void lm(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      int var5 = var3.getBitsize();
      Object var4;
      if (var5 == 8) {
         var4 = this.q.os.part(16);
      } else {
         if (var5 != 16 && var5 != 32 && var5 != 64) {
            throw new RuntimeException();
         }

         IEGeneric var6 = this.q.os.part(var5);
         IEGeneric var7 = this.q.fn.part(var5);
         var4 = this.RF.createCompose(var6, var7);
      }

      IEOperation var9 = this.RF.createOperation(OperationType.DIV_U, ((IEGeneric)var4).duplicate(), var3.duplicate().zeroExtend(2 * var5));
      IEOperation var10 = this.RF.createOperation(OperationType.REM_U, ((IEGeneric)var4).duplicate(), var3.duplicate().zeroExtend(2 * var5));
      if (var5 == 8) {
         IEAssign var8 = this.RF.createAssign((IEGeneric)var4, this.RF.createCompose(var9.part(8), var10.part(8)));
         var1.r.add(var8);
      } else {
         IEGeneric var11 = this.q.RF(var5, var1.r);
         var1.r.add(this.RF.createAssign(var11, var10.part(var5)));
         this.q.q(this.q.os.part(var5), var9.part(var5), var1);
         this.q.q(this.q.fn.part(var5), var11, var1);
      }
   }

   void zz(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      int var5 = var3.getBitsize();
      Object var4;
      if (var5 == 8) {
         var4 = this.q.os.part(16);
      } else {
         if (var5 != 16 && var5 != 32 && var5 != 64) {
            throw new RuntimeException();
         }

         IEGeneric var6 = this.q.os.part(var5);
         IEGeneric var7 = this.q.fn.part(var5);
         var4 = this.RF.createCompose(var6, var7);
      }

      IEOperation var9 = this.RF.createOperation(OperationType.DIV_S, ((IEGeneric)var4).duplicate(), var3.duplicate().signExtend(2 * var5));
      IEOperation var10 = this.RF.createOperation(OperationType.REM_S, ((IEGeneric)var4).duplicate(), var3.duplicate().signExtend(2 * var5));
      if (var5 == 8) {
         var1.r.add(this.RF.createAssign((IEGeneric)var4, this.RF.createCompose(var9.part(8), var10.part(8))));
      } else {
         IEGeneric var8 = this.q.RF(var5, var1.r);
         var1.r.add(this.RF.createAssign(var8, var10.part(var5)));
         this.q.q(this.q.os.part(var5), var9.part(var5), var1);
         this.q.q(this.q.fn.part(var5), var8, var1);
      }
   }

   void xK(ConverterInstructionEntry var1) {
      IEOperation var2 = EUtil.orL(EUtil.gtU(EUtil.andB(this.q.Ag, this.RF.createImm(15L, 8)), this.RF.createImm(9L, 8)), EUtil.eq(this.q.C, this.q.hP));
      var1.r.add(this.RF.createJump(var1.irAddress + 4, var2));
      var1.r.add(this.RF.createAssign(this.q.C, this.q.Eq));
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.Eq));
      var1.r.add(this.RF.createJump(var1.irAddress + 7, null));
      var1.r.add(this.RF.createAssign(this.q.CW, EUtil.add(this.q.CW, this.RF.createImm(262L, 16))));
      var1.r.add(this.RF.createAssign(this.q.C, this.q.hP));
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.hP));
      var1.r.add(this.RF.createAssign(this.q.Ag, EUtil.andB(this.q.Ag, this.RF.createImm(15L, 8))));
   }

   void Dw(ConverterInstructionEntry var1) {
      IEOperation var2 = EUtil.orL(EUtil.gtU(EUtil.andB(this.q.Ag, this.RF.createImm(15L, 8)), this.RF.createImm(9L, 8)), EUtil.eq(this.q.C, this.q.hP));
      var1.r.add(this.RF.createJump(var1.irAddress + 4, var2));
      var1.r.add(this.RF.createAssign(this.q.C, this.q.Eq));
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.Eq));
      var1.r.add(this.RF.createJump(var1.irAddress + 8, null));
      var1.r.add(this.RF.createAssign(this.q.CW, EUtil.sub(this.q.CW, this.RF.createImm(6L, 16))));
      var1.r.add(this.RF.createAssign(this.q.rp, EUtil.sub(this.q.rp, this.RF.createImm(1L, 8))));
      var1.r.add(this.RF.createAssign(this.q.C, this.q.hP));
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.hP));
      var1.r.add(this.RF.createAssign(this.q.Ag, EUtil.andB(this.q.Ag, this.RF.createImm(15L, 8))));
   }

   void Uv(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      Assert.a(var2.getBitsize() == 8);
      IEGeneric var3 = this.q.RF(8, var1.r);
      var1.r.add(this.RF.createAssign(var3, this.q.Ag));
      var1.r.add(this.RF.createAssign(this.q.rp, EUtil.divU(var3, var2)));
      var1.r.add(this.RF.createAssign(this.q.Ag, EUtil.remU(var3, var2)));
      this.q.q(this.q.Ag, var1.r);
   }

   void oW(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      Assert.a(var2.getBitsize() == 8);
      var1.r.add(this.RF.createAssign(this.q.Ag, EUtil.andB(EUtil.add(this.q.Ag, EUtil.mul(this.q.rp, var2)), this.RF.createImm(255L, 8))));
      var1.r.add(this.RF.createAssign(this.q.rp, this.q.dg));
      this.q.q(this.q.Ag, var1.r);
   }

   void gO(ConverterInstructionEntry var1) {
      IEVar var2 = this.q.q(8, var1.r);
      var1.r.add(this.RF.createAssign(var2.part(8), this.q.Ag));
      var1.r.add(this.RF.createAssign(var2.bit(8), this.q.WX));
      IEOperation var3 = EUtil.orL(EUtil.gtU(EUtil.andB(this.q.Ag, this.RF.createImm(15L, 8)), this.RF.createImm(9L, 8)), this.q.C);
      var1.r.add(this.RF.createJump(var1.irAddress + 6, var3));
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.Eq));
      var1.r.add(this.RF.createAssign(this.q.C, this.q.Eq));
      var1.r.add(this.RF.createJump(var1.irAddress + 9, null));
      var1.r.add(this.RF.createAssign(this.q.Ag, EUtil.add(this.q.Ag, this.RF.createImm(6L, 8))));
      var1.r.add(this.RF.createAssign(this.q.WX, EUtil.orB(this.q.WX, EUtil.geU(var2.part(8), this.RF.createImm(250L, 8)))));
      var1.r.add(this.RF.createAssign(this.q.C, this.q.hP));
      var3 = EUtil.orL(EUtil.gtU(var2.part(8), this.RF.createImm(153L, 8)), var2.bit(8));
      var1.r.add(this.RF.createJump(var1.irAddress + 12, var3));
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.Eq));
      var1.r.add(this.RF.createJump(var1.irAddress + 14, null));
      var1.r.add(this.RF.createAssign(this.q.Ag, EUtil.add(this.q.Ag, this.RF.createImm(96L, 8))));
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.hP));
   }

   void nf(ConverterInstructionEntry var1) {
      IEVar var2 = this.q.q(8, var1.r);
      var1.r.add(this.RF.createAssign(var2.part(8), this.q.Ag));
      var1.r.add(this.RF.createAssign(var2.bit(8), this.q.WX));
      IEOperation var3 = EUtil.orL(EUtil.gtU(EUtil.andB(this.q.Ag, this.RF.createImm(15L, 8)), this.RF.createImm(9L, 8)), this.q.C);
      var1.r.add(this.RF.createJump(var1.irAddress + 6, var3));
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.Eq));
      var1.r.add(this.RF.createAssign(this.q.C, this.q.Eq));
      var1.r.add(this.RF.createJump(var1.irAddress + 9, null));
      var1.r.add(this.RF.createAssign(this.q.Ag, EUtil.sub(this.q.Ag, this.RF.createImm(6L, 8))));
      var1.r.add(this.RF.createAssign(this.q.WX, EUtil.orB(this.q.WX, EUtil.ltU(var2.part(8), this.RF.createImm(6L, 8)))));
      var1.r.add(this.RF.createAssign(this.q.C, this.q.hP));
      var3 = EUtil.orL(EUtil.gtU(var2.part(8), this.RF.createImm(153L, 8)), var2.bit(8));
      var1.r.add(this.RF.createJump(var1.irAddress + 12, EUtil.notB(var3)));
      var1.r.add(this.RF.createAssign(this.q.Ag, EUtil.sub(this.q.Ag, this.RF.createImm(96L, 8))));
      var1.r.add(this.RF.createAssign(this.q.WX, this.q.hP));
   }
}
