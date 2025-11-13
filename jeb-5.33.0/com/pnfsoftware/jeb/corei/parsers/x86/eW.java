package com.pnfsoftware.jeb.corei.parsers.x86;

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

class eW {
   Yd pC;
   IERoutineContext A;

   eW(Yd var1) {
      this.pC = var1;
   }

   void pC(IEGeneric var1, List var2) {
      this.pC.pC(var1, var2);
   }

   void pC(IEGeneric var1, IEGeneric var2, List var3) {
      this.pC.pC(var1, var2, var3);
   }

   void pC(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      this.A(var1, var2, var3, var4);
      this.kS(var1, var2, var3, var4);
   }

   void A(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      var4.add(this.A.createAssign(this.pC.Er, this.A.createOperation(OperationType.CARRY, var1, var2)));
   }

   void kS(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      var4.add(this.A.createAssign(this.pC.Cu, EUtil.buildOverflowFlag(var1, var2, var3, true)));
   }

   void wS(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      this.UT(var1, var2, var3, var4);
      this.E(var1, var2, var3, var4);
   }

   void UT(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      var4.add(this.A.createAssign(this.pC.Er, this.A.createOperation(OperationType.LT_U, var1, var2)));
   }

   void E(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      var4.add(this.A.createAssign(this.pC.Cu, EUtil.buildOverflowFlag(var1, var2, var3, false)));
   }

   static IEGeneric pC(IEGeneric var0, IEGeneric var1, IEGeneric var2) {
      return EUtil.xorB(EUtil.xorB(var0, var1), var2).slice(4, 5);
   }

   static IEGeneric pC(IEGeneric var0, IEGeneric var1, IEGeneric var2, boolean var3) {
      return var3 ? EUtil.ltU(var2, var0) : EUtil.ltU(var0, var1);
   }

   void pC(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      int var4 = var3.getBitsize();
      IEGeneric var5 = this.pC.A((vh)var1.insn, 1, var4, var1.address);
      Assert.a(var4 == var5.getBitsize());
      Assert.a(Yd.pC(var3, var5));
      IEGeneric var6 = this.pC.A(var4, var1.r);
      this.pC.pC(var6, var3, var1, false);
      Object var7 = var5;
      if (!(var5 instanceof IEImm)) {
         var7 = this.pC.wS(var4);
         this.pC.pC((IEGeneric)var7, var5, var1, false);
      }

      IEVar var8 = this.pC.UT(var4);
      this.pC.pC(var8, this.A.createOperation(OperationType.ADD, var6, (IEGeneric)var7), var1);
      this.pC.pC(var3, var8, var1);
      if (!var2) {
         this.pC(var8, var1.r);
         this.pC.pC(var6, (IEGeneric)var7, var8, var1.r);
         this.pC(var6, (IEGeneric)var7, var8, var1.r);
      }
   }

   void pC(ConverterInstructionEntry var1) {
      boolean var2 = true;
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.A((vh)var1.insn, 1, var3.getBitsize(), var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      Assert.a(Yd.pC(var3, var4));
      IEGeneric var5 = this.pC.A(var3.getBitsize(), var1.r);
      this.pC.pC(var5, var3, var1, false);
      IEOperation var6 = this.A.createOperation(OperationType.ADD, var3, var4);
      this.pC.pC(var3, var6, var1);
      this.pC(var3, var1.r);
      if (var3 == var4) {
         var4 = var5;
         var2 = false;
      }

      this.pC.pC(var5, var4, var3, var1.r);
      this.pC(var5, var4, var3, var1.r);
      if (var2) {
         this.pC.pC(var4, var5, var1);
      }
   }

   void A(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.A((vh)var1.insn, 1, var2.getBitsize(), var1.address);
      IEGeneric var4 = this.pC.Sc.part(var2.getBitsize());
      IEOperation var5 = this.A.createOperation(OperationType.SUB, var4, var2);
      this.pC(var5, var1.r);
      this.wS(var4, var2, var5, var1.r);
      this.pC.pC(var4, var2, var5, var1.r);
      int var6 = var1.irAddress + var1.r.size();
      var1.r.add(this.A.createJump(var6 + 3, this.A.createOperation(OperationType.LOG_EQ, this.pC.EX, this.pC.Bc)));
      var1.r.add(this.A.createAssign(var2, var3));
      var1.r.add(this.A.createJump(var6 + 4, null));
      var1.r.add(this.A.createAssign(var4, var2));
   }

   void A(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      int var4 = var3.getBitsize();
      IEGeneric var5 = this.pC.A((vh)var1.insn, 1, var4, var1.address);
      Assert.a(var4 == var5.getBitsize());
      Assert.a(Yd.pC(var3, var5));
      if (var2) {
         this.pC.pC(var3, EUtil.add(var3, EUtil.add(var5, this.pC.Er.zeroExtend(var4))), var1);
      } else {
         IEVar var6 = this.pC.kS(var4);
         this.pC.pC(var6, EUtil.add(var3, this.pC.Er.zeroExtend(var4)), var1);
         IEVar var7 = this.pC.wS(var4);
         this.pC.pC(var7, var5, var1);
         this.pC.pC(var3, EUtil.add(var6, var7), var1);
         this.pC(var3, var1.r);
         var1.r
            .add(
               this.A
                  .createAssign(
                     this.pC.Cu,
                     EUtil.orL(
                        EUtil.andL(
                           EUtil.eq(this.pC.Er, this.pC.OI),
                           EUtil.andL(EUtil.eq(var6, this.pC.OI.zeroExtend(var4)._shl(var4 - 1)), EUtil.geS(var7, this.pC.pC(var7)))
                        ),
                        EUtil.buildOverflowFlag(var6, var7, var3, true)
                     )
                  )
            );
         var1.r
            .add(
               this.A
                  .createAssign(
                     this.pC.Aj, EUtil.orL(EUtil.andL(EUtil.eq(this.pC.Er, this.pC.OI), EUtil.eq(var6.part(4), EUtil.zero(4))), pC(var6, var7, var3))
                  )
            );
         var1.r
            .add(
               this.A
                  .createAssign(
                     this.pC.Er, EUtil.orL(EUtil.andL(EUtil.eq(this.pC.Er, this.pC.OI), EUtil.eq(var6, this.pC.pC(var6))), pC(var6, var7, var3, true))
                  )
            );
      }
   }

   void kS(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      int var4 = var3.getBitsize();
      IEGeneric var5 = this.pC.A((vh)var1.insn, 1, var4, var1.address);
      Assert.a(var4 == var5.getBitsize());
      Assert.a(Yd.pC(var3, var5));
      IEGeneric var6 = this.pC.A(var3.getBitsize(), var1.r);
      this.pC.pC(var6, var3, var1, false);
      Object var7 = var5;
      if (!(var5 instanceof IEImm)) {
         var7 = this.pC.wS(var4);
         this.pC.pC((IEGeneric)var7, var5, var1, false);
      }

      IEVar var8 = this.pC.UT(var4);
      this.pC.pC(var8, this.A.createOperation(OperationType.SUB, var6, (IEGeneric)var7), var1);
      this.pC.pC(var3, var8, var1);
      if (!var2) {
         this.pC(var8, var1.r);
         this.pC.pC(var6, (IEGeneric)var7, var8, var1.r);
         this.wS(var6, (IEGeneric)var7, var8, var1.r);
      }
   }

   void wS(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.A((vh)var1.insn, 1, var3.getBitsize(), var1.address);
      int var5 = var3.getBitsize();
      Assert.a(var5 == var4.getBitsize());
      Assert.a(Yd.pC(var3, var4));
      if (var2) {
         this.pC.pC(var3, EUtil.sub(var3, EUtil.add(var4, this.pC.Er.zeroExtend(var5))), var1);
      } else {
         IEVar var6 = this.pC.kS(var5);
         this.pC.pC(var6, var3, var1, false);
         IEVar var7 = this.pC.wS(var5);
         this.pC.pC(var7, EUtil.add(var4, this.pC.Er.zeroExtend(var5)), var1);
         this.pC.pC(var3, EUtil.sub(var6, var7), var1);
         this.pC(var3, var1.r);
         var1.r
            .add(
               this.A
                  .createAssign(
                     this.pC.Cu,
                     EUtil.orL(
                        EUtil.andL(
                           EUtil.eq(this.pC.Er, this.pC.OI),
                           EUtil.andL(EUtil.eq(var7, this.pC.OI.zeroExtend(var5)._shl(var5 - 1)), EUtil.ltS(var6, this.pC.pC(var6)))
                        ),
                        EUtil.buildOverflowFlag(var6, var7, var3, false)
                     )
                  )
            );
         var1.r
            .add(
               this.A
                  .createAssign(
                     this.pC.Aj, EUtil.orL(EUtil.andL(EUtil.eq(this.pC.Er, this.pC.OI), EUtil.eq(var7.part(4), EUtil.zero(4))), pC(var6, var7, var3))
                  )
            );
         var1.r
            .add(
               this.A
                  .createAssign(
                     this.pC.Er, EUtil.orL(EUtil.andL(EUtil.eq(this.pC.Er, this.pC.OI), EUtil.eq(var7, this.pC.pC(var7))), pC(var6, var7, var3, false))
                  )
            );
      }
   }

   void UT(ConverterInstructionEntry var1, boolean var2) {
      if (var2) {
         var1.r.add(this.A.createNop());
      } else {
         IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
         IEGeneric var4 = this.pC.A((vh)var1.insn, 1, var3.getBitsize(), var1.address);
         this.A(var3, var4, var1.r);
      }
   }

   void A(IEGeneric var1, IEGeneric var2, List var3) {
      Assert.a(var1.getBitsize() == var2.getBitsize());
      Assert.a(Yd.pC(var1, var2));
      IEOperation var4 = this.A.createOperation(OperationType.SUB, var1.duplicate(), var2.duplicate());
      this.pC(var4, var3);
      this.wS(var1, var2, var4, var3);
      this.pC.pC(var1, var2, var4, var3);
   }

   void E(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEImm var4 = this.A.createImm(1L, var3.getBitsize());
      Assert.a(Yd.pC(var3));
      IEGeneric var5 = null;
      if (!var2) {
         var5 = this.pC.A(var3.getBitsize(), var1.r);
         this.pC.pC(var5, var3, var1, false);
      }

      IEOperation var6 = this.A.createOperation(OperationType.ADD, var3, var4);
      this.pC.pC(var3, var6, var1);
      if (!var2) {
         this.pC(var3, var1.r);
         this.pC.pC(var5, var4, var3, var1.r);
         this.kS(var5, var4, var3, var1.r);
      }
   }

   void sY(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEImm var4 = this.A.createImm(-1L, var3.getBitsize());
      Assert.a(Yd.pC(var3));
      IEGeneric var5 = null;
      if (!var2) {
         var5 = this.pC.A(var3.getBitsize(), var1.r);
         this.pC.pC(var5, var3, var1, false);
      }

      IEOperation var6 = this.A.createOperation(OperationType.ADD, var3, var4);
      this.pC.pC(var3, var6, var1);
      if (!var2) {
         this.pC(var3, var1.r);
         this.pC.pC(var5, var4, this.A.createOperation(OperationType.NOT, var3), var1.r);
         this.kS(var5, var4, var3, var1.r);
      }
   }

   void ys(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEImm var4 = this.A.createImm(0L, var3.getBitsize());
      Assert.a(Yd.pC(var3));
      IEGeneric var5 = null;
      if (!var2) {
         var5 = this.pC.A(var3.getBitsize(), var1.r);
         this.pC.pC(var5, var3, var1, false);
      }

      IEOperation var6 = this.A.createOperation(OperationType.SUB, var4, var3);
      this.pC.pC(var3, var6, var1);
      if (!var2) {
         this.pC(var3, var1.r);
         this.wS(var4, var5, var3, var1.r);
         this.pC.pC(var4, var5, var3, var1.r);
      }
   }

   void ld(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      Assert.a(Yd.pC(var3));
      int var4 = var3.getBitsize();
      if (var4 == 8) {
         IEGeneric var5 = this.pC.Sc.part(16);
         IEOperation var6 = this.A.createOperation(OperationType.MUL, this.pC.Sc.part(8).zeroExtend(16), var3.zeroExtend(16));
         var1.r.add(this.A.createAssign(var5, var6));
         if (var2) {
            return;
         }

         IECond var7 = this.A.createCond(var5.slice(8, 16), this.pC.OI, this.pC.Bc);
         var1.r.add(this.A.createAssign(this.pC.Er, var7));
         var1.r.add(this.A.createAssign(this.pC.Cu, var7));
      } else {
         if (var4 != 16 && var4 != 32 && var4 != 64) {
            throw new RuntimeException();
         }

         IEGeneric var11 = this.pC.Sc.part(var4);
         IEGeneric var12 = this.pC.eP.part(var4);
         IEVar var13 = this.pC.pC(this.pC.Sc.getBitsize(), var1.r);
         var1.r.add(this.A.createAssign(var13.part(this.pC.Sc.getBitsize()), this.pC.Sc));
         IEOperation var8 = this.A.createOperation(OperationType.MUL, this.pC.Sc.part(var4), var3);
         this.pC.pC(var11, var8, var1);
         IEOperation var9 = this.A.createOperation(OperationType.MUL, var13.part(var4).zeroExtend(2 * var4), var3.zeroExtend(2 * var4));
         this.pC.pC(var12, var9.slice(var4, 2 * var4), var1);
         if (var2) {
            return;
         }

         IECond var10 = this.A.createCond(var12, this.pC.OI, this.pC.Bc);
         var1.r.add(this.A.createAssign(this.pC.Er, var10));
         var1.r.add(this.A.createAssign(this.pC.Cu, var10));
      }
   }

   void gp(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      Assert.a(Yd.pC(var3));
      int var4 = var3.getBitsize();
      boolean var5 = ((vh)var1.insn).gp.A == 63016 || ((vh)var1.insn).gp.A == 63272;
      if (var5) {
         if (var4 == 8) {
            IEGeneric var6 = this.pC.Sc.part(16);
            IEOperation var7 = this.A.createOperation(OperationType.MUL, this.pC.Sc.part(8).signExtend(16), var3.signExtend(16));
            var1.r.add(this.A.createAssign(var6, var7));
            if (var2) {
               return;
            }

            IECond var8 = this.A.createCond(this.A.createOperation(OperationType.SUB, var6, var6.part(8).signExtend(16)), this.pC.OI, this.pC.Bc);
            var1.r.add(this.A.createAssign(this.pC.Er, var8));
            var1.r.add(this.A.createAssign(this.pC.Cu, var8));
         } else {
            if (var4 != 16 && var4 != 32 && var4 != 64) {
               throw new RuntimeException();
            }

            IEGeneric var15 = this.pC.Sc.part(var4);
            IEGeneric var16 = this.pC.eP.part(var4);
            IEVar var18 = this.pC.pC(this.pC.Sc.getBitsize(), var1.r);
            var1.r.add(this.A.createAssign(var18.part(this.pC.Sc.getBitsize()), this.pC.Sc));
            IEOperation var9 = this.A.createOperation(OperationType.MUL, this.pC.Sc.part(var4), var3);
            this.pC.pC(var15, var9, var1);
            IEOperation var10 = this.A.createOperation(OperationType.MUL, var18.part(var4).signExtend(2 * var4), var3.signExtend(2 * var4));
            this.pC.pC(var16, var10.slice(var4, 2 * var4), var1);
            if (var2) {
               return;
            }

            IECond var11 = this.A
               .createCond(this.A.createOperation(OperationType.SUB, this.A.createCompose(var15, var16), var15.signExtend(2 * var4)), this.pC.OI, this.pC.Bc);
            var1.r.add(this.A.createAssign(this.pC.Er, var11));
            var1.r.add(this.A.createAssign(this.pC.Cu, var11));
         }
      } else {
         IEGeneric var17 = this.pC.A(var4, var1.r);
         var1.r.add(this.A.createAssign(var17, var3));
         IEGeneric var19 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
         Assert.a(var4 == var19.getBitsize());
         boolean var21 = var19.equals(var3);
         int var22 = ((vh)var1.insn).A().length;
         IEOperation var23 = null;
         if (var22 == 2) {
            var23 = this.A.createOperation(OperationType.MUL, var3, var19);
         } else if (var22 == 3) {
            IEGeneric var12 = this.pC.pC((vh)var1.insn, 2, -var4, var1.address);
            var23 = this.A.createOperation(OperationType.MUL, var19, var12);
         }

         this.pC.pC(var3, var23, var1);
         if (var2) {
            return;
         }

         if (!var21) {
            var19 = this.pC.pC((vh)var1.insn, 1, -2 * var4, var1.address);
            Assert.a(Yd.pC(var19));
         } else {
            var19 = var17.signExtend(2 * var4);
         }

         IEGeneric var24;
         if (var22 == 3) {
            var24 = this.pC.pC((vh)var1.insn, 2, -2 * var4, var1.address);
            Assert.a(Yd.pC(var24));
         } else {
            if (var22 != 2) {
               throw new RuntimeException();
            }

            var24 = var19;
            var19 = var17.signExtend(2 * var4);
         }

         IEOperation var13 = this.A.createOperation(OperationType.MUL, var19, var24);
         IECond var14 = this.A.createCond(this.A.createOperation(OperationType.SUB, var13, var3.signExtend(2 * var4)), this.pC.OI, this.pC.Bc);
         var1.r.add(this.A.createAssign(this.pC.Er, var14));
         var1.r.add(this.A.createAssign(this.pC.Cu, var14));
      }
   }

   void oT(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      int var5 = var3.getBitsize();
      Object var4;
      if (var5 == 8) {
         var4 = this.pC.Sc.part(16);
      } else {
         if (var5 != 16 && var5 != 32 && var5 != 64) {
            throw new RuntimeException();
         }

         IEGeneric var6 = this.pC.Sc.part(var5);
         IEGeneric var7 = this.pC.eP.part(var5);
         var4 = this.A.createCompose(var6, var7);
      }

      IEOperation var9 = this.A.createOperation(OperationType.DIV_U, ((IEGeneric)var4).duplicate(), var3.duplicate().zeroExtend(2 * var5));
      IEOperation var10 = this.A.createOperation(OperationType.REM_U, ((IEGeneric)var4).duplicate(), var3.duplicate().zeroExtend(2 * var5));
      if (var5 == 8) {
         IEAssign var8 = this.A.createAssign((IEGeneric)var4, this.A.createCompose(var9.part(8), var10.part(8)));
         var1.r.add(var8);
      } else {
         IEGeneric var11 = this.pC.A(var5, var1.r);
         var1.r.add(this.A.createAssign(var11, var10.part(var5)));
         this.pC.pC(this.pC.Sc.part(var5), var9.part(var5), var1);
         this.pC.pC(this.pC.eP.part(var5), var11, var1);
      }
   }

   void fI(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      int var5 = var3.getBitsize();
      Object var4;
      if (var5 == 8) {
         var4 = this.pC.Sc.part(16);
      } else {
         if (var5 != 16 && var5 != 32 && var5 != 64) {
            throw new RuntimeException();
         }

         IEGeneric var6 = this.pC.Sc.part(var5);
         IEGeneric var7 = this.pC.eP.part(var5);
         var4 = this.A.createCompose(var6, var7);
      }

      IEOperation var9 = this.A.createOperation(OperationType.DIV_S, ((IEGeneric)var4).duplicate(), var3.duplicate().signExtend(2 * var5));
      IEOperation var10 = this.A.createOperation(OperationType.REM_S, ((IEGeneric)var4).duplicate(), var3.duplicate().signExtend(2 * var5));
      if (var5 == 8) {
         var1.r.add(this.A.createAssign((IEGeneric)var4, this.A.createCompose(var9.part(8), var10.part(8))));
      } else {
         IEGeneric var8 = this.pC.A(var5, var1.r);
         var1.r.add(this.A.createAssign(var8, var10.part(var5)));
         this.pC.pC(this.pC.Sc.part(var5), var9.part(var5), var1);
         this.pC.pC(this.pC.eP.part(var5), var8, var1);
      }
   }

   void kS(ConverterInstructionEntry var1) {
      IEOperation var2 = EUtil.orL(EUtil.gtU(EUtil.andB(this.pC.UH, this.A.createImm(15L, 8)), this.A.createImm(9L, 8)), EUtil.eq(this.pC.Aj, this.pC.OI));
      var1.r.add(this.A.createJump(var1.irAddress + 4, var2));
      var1.r.add(this.A.createAssign(this.pC.Aj, this.pC.Bc));
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.Bc));
      var1.r.add(this.A.createJump(var1.irAddress + 7, null));
      var1.r.add(this.A.createAssign(this.pC.Xs, EUtil.add(this.pC.Xs, this.A.createImm(262L, 16))));
      var1.r.add(this.A.createAssign(this.pC.Aj, this.pC.OI));
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.OI));
      var1.r.add(this.A.createAssign(this.pC.UH, EUtil.andB(this.pC.UH, this.A.createImm(15L, 8))));
   }

   void wS(ConverterInstructionEntry var1) {
      IEOperation var2 = EUtil.orL(EUtil.gtU(EUtil.andB(this.pC.UH, this.A.createImm(15L, 8)), this.A.createImm(9L, 8)), EUtil.eq(this.pC.Aj, this.pC.OI));
      var1.r.add(this.A.createJump(var1.irAddress + 4, var2));
      var1.r.add(this.A.createAssign(this.pC.Aj, this.pC.Bc));
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.Bc));
      var1.r.add(this.A.createJump(var1.irAddress + 8, null));
      var1.r.add(this.A.createAssign(this.pC.Xs, EUtil.sub(this.pC.Xs, this.A.createImm(6L, 16))));
      var1.r.add(this.A.createAssign(this.pC.VD, EUtil.sub(this.pC.VD, this.A.createImm(1L, 8))));
      var1.r.add(this.A.createAssign(this.pC.Aj, this.pC.OI));
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.OI));
      var1.r.add(this.A.createAssign(this.pC.UH, EUtil.andB(this.pC.UH, this.A.createImm(15L, 8))));
   }

   void UT(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      Assert.a(var2.getBitsize() == 8);
      IEGeneric var3 = this.pC.A(8, var1.r);
      var1.r.add(this.A.createAssign(var3, this.pC.UH));
      var1.r.add(this.A.createAssign(this.pC.VD, EUtil.divU(var3, var2)));
      var1.r.add(this.A.createAssign(this.pC.UH, EUtil.remU(var3, var2)));
      this.pC.pC(this.pC.UH, var1.r);
   }

   void E(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      Assert.a(var2.getBitsize() == 8);
      var1.r.add(this.A.createAssign(this.pC.UH, EUtil.andB(EUtil.add(this.pC.UH, EUtil.mul(this.pC.VD, var2)), this.A.createImm(255L, 8))));
      var1.r.add(this.A.createAssign(this.pC.VD, this.pC.RW));
      this.pC.pC(this.pC.UH, var1.r);
   }

   void sY(ConverterInstructionEntry var1) {
      IEVar var2 = this.pC.pC(8, var1.r);
      var1.r.add(this.A.createAssign(var2.part(8), this.pC.UH));
      var1.r.add(this.A.createAssign(var2.bit(8), this.pC.Er));
      IEOperation var3 = EUtil.orL(EUtil.gtU(EUtil.andB(this.pC.UH, this.A.createImm(15L, 8)), this.A.createImm(9L, 8)), this.pC.Aj);
      var1.r.add(this.A.createJump(var1.irAddress + 6, var3));
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.Bc));
      var1.r.add(this.A.createAssign(this.pC.Aj, this.pC.Bc));
      var1.r.add(this.A.createJump(var1.irAddress + 9, null));
      var1.r.add(this.A.createAssign(this.pC.UH, EUtil.add(this.pC.UH, this.A.createImm(6L, 8))));
      var1.r.add(this.A.createAssign(this.pC.Er, EUtil.orB(this.pC.Er, EUtil.geU(var2.part(8), this.A.createImm(250L, 8)))));
      var1.r.add(this.A.createAssign(this.pC.Aj, this.pC.OI));
      var3 = EUtil.orL(EUtil.gtU(var2.part(8), this.A.createImm(153L, 8)), var2.bit(8));
      var1.r.add(this.A.createJump(var1.irAddress + 12, var3));
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.Bc));
      var1.r.add(this.A.createJump(var1.irAddress + 14, null));
      var1.r.add(this.A.createAssign(this.pC.UH, EUtil.add(this.pC.UH, this.A.createImm(96L, 8))));
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.OI));
   }

   void ys(ConverterInstructionEntry var1) {
      IEVar var2 = this.pC.pC(8, var1.r);
      var1.r.add(this.A.createAssign(var2.part(8), this.pC.UH));
      var1.r.add(this.A.createAssign(var2.bit(8), this.pC.Er));
      IEOperation var3 = EUtil.orL(EUtil.gtU(EUtil.andB(this.pC.UH, this.A.createImm(15L, 8)), this.A.createImm(9L, 8)), this.pC.Aj);
      var1.r.add(this.A.createJump(var1.irAddress + 6, var3));
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.Bc));
      var1.r.add(this.A.createAssign(this.pC.Aj, this.pC.Bc));
      var1.r.add(this.A.createJump(var1.irAddress + 9, null));
      var1.r.add(this.A.createAssign(this.pC.UH, EUtil.sub(this.pC.UH, this.A.createImm(6L, 8))));
      var1.r.add(this.A.createAssign(this.pC.Er, EUtil.orB(this.pC.Er, EUtil.ltU(var2.part(8), this.A.createImm(6L, 8)))));
      var1.r.add(this.A.createAssign(this.pC.Aj, this.pC.OI));
      var3 = EUtil.orL(EUtil.gtU(var2.part(8), this.A.createImm(153L, 8)), var2.bit(8));
      var1.r.add(this.A.createJump(var1.irAddress + 12, EUtil.notB(var3)));
      var1.r.add(this.A.createAssign(this.pC.UH, EUtil.sub(this.pC.UH, this.A.createImm(96L, 8))));
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.OI));
   }
}
