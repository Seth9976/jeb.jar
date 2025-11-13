package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import java.util.ArrayList;
import java.util.List;

public class cez {
   cfb pC;
   IERoutineContext A;

   public cez(cfb var1) {
      this.pC = var1;
   }

   public void pC(cfq var1, List var2) {
      byte var3 = 32;
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]).part(var3);
      IEMem var5 = this.pC.pC(var1, ((cfj[])var1.getOperands())[1], (int)var3);
      var2.add(this.A.createAssign(var5, this.A.createCond(this.pC.ah, var4, var5)));
      var2.add(this.A.createAssign(this.pC.ah, this.pC.UO));
   }

   public void A(cfq var1, List var2) {
      this.A(var1, var2, 32);
      var2.add(this.A.createAssign(this.pC.ah, this.pC.Ab));
   }

   public void pC(cfq var1, List var2, int var3) {
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]).part(var3);
      IEMem var5 = this.pC.pC(var1, ((cfj[])var1.getOperands())[1], var3);
      var2.add(this.A.createAssign(var5, var4));
   }

   public void A(cfq var1, List var2, int var3) {
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEMem var5 = this.pC.pC(var1, ((cfj[])var1.getOperands())[1], var3);
      var2.add(this.A.createAssign(var4, var5.signExtend(var4.getBitsize())));
   }

   public void kS(cfq var1, List var2, int var3) {
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEMem var5 = this.pC.pC(var1, ((cfj[])var1.getOperands())[1], var3);
      var2.add(this.A.createAssign(var4, var5.zeroExtend(var4.getBitsize())));
   }

   public void pC(cfq var1, List var2, int var3, boolean var4, int var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEGeneric var7 = this.pC.pC(var1, ((cfj[])var1.getOperands())[1]);
      IEOperation var8 = EUtil.andB(var7, this.A.createImm(~(var3 - 1), var3 * 8).signExtend(var7.getBitsize()));
      IEOperation var9 = EUtil.andB(var7, this.A.createImm(var3 - 1, var7.getBitsize()));
      if (var4) {
         IEStatement[] var10 = new IEStatement[var3];
         var10[0] = this.A.createAssign(var6, this.A.createMem(var7, var3 * 8).signExtend(var6.getBitsize()));

         for (int var11 = 1; var11 < var3; var11++) {
            var10[var11] = this.A
               .createAssign(var6, this.A.createCompose(var6.part(var11 * 8), this.A.createMem(var7, (var3 - var11) * 8)).signExtend(var6.getBitsize()));
         }

         var2.addAll(this.pC(var5, var9, var10));
      } else {
         IEStatement[] var12 = new IEStatement[var3];

         for (int var13 = 0; var13 < var3 - 1; var13++) {
            var12[var13] = this.A
               .createAssign(
                  var6, this.A.createCompose(var6.part((var3 - (var13 + 1)) * 8), this.A.createMem(var8, (var13 + 1) * 8)).signExtend(var6.getBitsize())
               );
         }

         var12[var3 - 1] = this.A.createAssign(var6, this.A.createMem(var8, var3 * 8).signExtend(var6.getBitsize()));
         var2.addAll(this.pC(var5, var9, var12));
      }
   }

   public void A(cfq var1, List var2, int var3, boolean var4, int var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEGeneric var7 = this.pC.pC(var1, ((cfj[])var1.getOperands())[1]);
      IEOperation var8 = EUtil.andB(var7, this.A.createImm(~(var3 - 1), var3 * 8).signExtend(var7.getBitsize()));
      IEOperation var9 = EUtil.andB(var7, this.A.createImm(var3 - 1, var7.getBitsize()));
      if (var4) {
         IEStatement[] var10 = new IEStatement[var3];

         for (int var11 = 0; var11 < var3 - 1; var11++) {
            var10[var11] = this.A
               .createAssign(
                  var6, this.A.createCompose(this.A.createMem(var8, (var11 + 1) * 8), var6.slice((var11 + 1) * 8, var3 * 8)).signExtend(var6.getBitsize())
               );
         }

         var10[var3 - 1] = this.A.createAssign(var6, this.A.createMem(var8, var3 * 8).signExtend(var6.getBitsize()));
         var2.addAll(this.pC(var5, var9, var10));
      } else {
         IEStatement[] var12 = new IEStatement[var3];
         var12[0] = this.A.createAssign(var6, this.A.createMem(var7, var3 * 8).signExtend(var6.getBitsize()));

         for (int var13 = 1; var13 < var3; var13++) {
            var12[var13] = this.A
               .createAssign(
                  var6,
                  this.A.createCompose(this.A.createMem(var7, (var3 - var13) * 8), var6.slice((var3 - var13) * 8, var3 * 8)).signExtend(var6.getBitsize())
               );
         }

         var2.addAll(this.pC(var5, var9, var12));
      }
   }

   public void kS(cfq var1, List var2, int var3, boolean var4, int var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEGeneric var7 = this.pC.pC(var1, ((cfj[])var1.getOperands())[1]);
      IEOperation var8 = EUtil.andB(var7, this.A.createImm(~(var3 - 1), var3 * 8).signExtend(var7.getBitsize()));
      IEOperation var9 = EUtil.andB(var7, this.A.createImm(var3 - 1, var7.getBitsize()));
      if (var4) {
         IEStatement[] var10 = new IEStatement[var3];

         for (int var11 = 0; var11 < var3; var11++) {
            switch ((var3 - var11) * 8) {
               case 8:
               case 16:
               case 32:
               case 64:
                  var10[var11] = this.A.createAssign(this.A.createMem(var7, (var3 - var11) * 8), var6.slice(var11 * 8, var3 * 8));
                  break;
               default:
                  var10[var11] = this.A
                     .createAssign(this.A.createMem(var8, var3 * 8), this.A.createCompose(var6.slice(var11 * 8, var3 * 8), this.A.createMem(var8, var11 * 8)));
            }
         }

         var2.addAll(this.pC(var5, var9, var10));
      } else {
         IEStatement[] var12 = new IEStatement[var3];

         for (int var13 = 0; var13 < var3; var13++) {
            switch ((var13 + 1) * 8) {
               case 8:
               case 16:
               case 32:
               case 64:
                  var12[var13] = this.A.createAssign(this.A.createMem(var8, (var13 + 1) * 8), var6.slice((var3 - (var13 + 1)) * 8, var3 * 8));
                  break;
               default:
                  var12[var13] = this.A
                     .createAssign(
                        this.A.createMem(var8, var3 * 8),
                        this.A
                           .createCompose(
                              var6.slice((var3 - (var13 + 1)) * 8, var3 * 8),
                              this.A.createMem(EUtil.add(var7, this.A.createImm(1L, this.pC.E)), (var3 - (var13 + 1)) * 8)
                           )
                     );
            }
         }

         var2.addAll(this.pC(var5, var9, var12));
      }
   }

   public void wS(cfq var1, List var2, int var3, boolean var4, int var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEGeneric var7 = this.pC.pC(var1, ((cfj[])var1.getOperands())[1]);
      IEOperation var8 = EUtil.andB(var7, this.A.createImm(~(var3 - 1), var3 * 8).signExtend(var7.getBitsize()));
      IEOperation var9 = EUtil.andB(var7, this.A.createImm(var3 - 1, var7.getBitsize()));
      if (var4) {
         IEStatement[] var10 = new IEStatement[var3];

         for (int var11 = 0; var11 < var3; var11++) {
            switch ((var11 + 1) * 8) {
               case 8:
               case 16:
               case 32:
               case 64:
                  var10[var11] = this.A.createAssign(this.A.createMem(var8, (var11 + 1) * 8), var6.part((var11 + 1) * 8));
                  break;
               default:
                  var10[var11] = this.A
                     .createAssign(
                        this.A.createMem(var8, var3 * 8),
                        this.A
                           .createCompose(
                              this.A.createMem(EUtil.add(var7, this.A.createImm(1L, this.pC.E)), (var3 - (var11 + 1)) * 8), var6.part((var11 + 1) * 8)
                           )
                     );
            }
         }

         var2.addAll(this.pC(var5, var9, var10));
      } else {
         IEStatement[] var12 = new IEStatement[var3];

         for (int var13 = 0; var13 < var3; var13++) {
            switch ((var3 - var13) * 8) {
               case 8:
               case 16:
               case 32:
               case 64:
                  var12[var13] = this.A.createAssign(this.A.createMem(var7, (var3 - var13) * 8), var6.part((var3 - var13) * 8));
                  break;
               default:
                  var12[var13] = this.A
                     .createAssign(this.A.createMem(var8, var3 * 8), this.A.createCompose(this.A.createMem(var8, var13 * 8), var6.part((var3 - var13) * 8)));
            }
         }

         var2.addAll(this.pC(var5, var9, var12));
      }
   }

   private List pC(int var1, IEGeneric var2, IEStatement... var3) {
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();

      for (int var6 = 0; var6 < var3.length; var6++) {
         if (var6 < var3.length - 1) {
            var4.add(new cez.Av(this.A.createImm(var6, var2.getBitsize()), var3[var6]));
         } else {
            var5.add(var3[var6]);
         }
      }

      return pC(this.A, var1, var2, var4, var5);
   }

   public void pC(cfq var1, List var2, long var3, boolean var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEGeneric var7 = this.pC.pC(var1, ((cfj[])var1.getOperands())[1], var3, this.pC.E);
      var2.add(this.A.createAssign(var6, EUtil.extend(this.A.createMem(var7, 32), var6.getBitsize(), var5)));
   }

   public void pC(cfq var1, List var2, boolean var3) {
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEVar var5;
      if (((cfj[])var1.getOperands()).length == 2) {
         int var6 = RegisterUtil.getRegisterIndex(((cfj[])var1.getOperands())[1].getOperandValue());
         var5 = var3 ? this.pC.kS(var6) : this.pC.wS(var6);
      } else {
         var5 = var3 ? this.pC.kS(0) : this.pC.wS(0);
      }

      var2.add(this.A.createAssign(var4, var5));
   }

   public void A(cfq var1, List var2, boolean var3) {
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEVar var5;
      if (((cfj[])var1.getOperands()).length == 2) {
         int var6 = RegisterUtil.getRegisterIndex(((cfj[])var1.getOperands())[1].getOperandValue());
         var5 = var3 ? this.pC.kS(var6) : this.pC.wS(var6);
      } else {
         var5 = var3 ? this.pC.kS(0) : this.pC.wS(0);
      }

      var2.add(this.A.createAssign(var5, var4));
   }

   cfq pC(cfq var1, cfq var2, boolean var3) {
      cft var4 = null;
      if (var2 == null) {
         return var4;
      } else {
         String var5 = var2.getMnemonic();
         boolean var6 = var1.getMnemonic().endsWith("L");
         if (var5.equals(var1.getMnemonic().substring(0, 2) + (var6 ? "R" : "L"))
            && ((cfj[])var1.getOperands())[0].getOperandValue() == ((cfj[])var2.getOperands())[0].getOperandValue()) {
            cfq var7 = var6 ? var1 : var2;
            cfq var8 = var6 ? var2 : var1;
            IInstructionOperand[] var9 = ((cfj[])var7.getOperands())[1].getOperands();
            IInstructionOperand[] var10 = ((cfj[])var8.getOperands())[1].getOperands();
            if (((cfj)var9[1]).getOperandValue() == ((cfj)var10[1]).getOperandValue()) {
               int var11 = var5.charAt(1) == 'D' ? 8 : 4;
               long var12 = ((cfj)var9[0]).getOperandValue();
               long var14 = ((cfj)var10[0]).getOperandValue();
               if (var3 && var12 + var11 - 1L == var14) {
                  var4 = new cft(var7, var1.getMnemonic().substring(0, 2));
               } else if (!var3 && var12 == var14 + var11 - 1L) {
                  var4 = new cft(var8, var1.getMnemonic().substring(0, 2));
               }
            }
         }

         return var4;
      }
   }

   public static List pC(IERoutineContext var0, int var1, IEGeneric var2, List var3, List var4) {
      ArrayList var5 = new ArrayList();
      int var6 = var1 + var3.size() + 1 + (var4 == null ? 0 : var4.size());
      int var7 = 0;

      for (int var8 = 0; var8 < var3.size(); var8++) {
         var5.add(var0.createJump(var6 + var7, var0.createOperation(OperationType.LOG_EQ, var2, ((cez.Av)var3.get(var8)).pC)));
         var7 += ((cez.Av)var3.get(var8)).A.size() + 1;
      }

      int var10 = var6 + var7 - 1;
      if (var4 != null) {
         var5.addAll(var4);
      }

      var5.add(var0.createJump(var10, null));

      for (int var9 = 0; var9 < var3.size(); var9++) {
         var5.addAll(((cez.Av)var3.get(var9)).A);
         if (var9 != var3.size() - 1) {
            var5.add(var0.createJump(var10, null));
         }
      }

      var5.add(var0.createNop());
      return var5;
   }

   public static class Av {
      IEGeneric pC;
      List A;

      public Av(IEGeneric var1, IEStatement var2) {
         this.pC = var1;
         this.A = new ArrayList();
         this.A.add(var2);
      }
   }
}
