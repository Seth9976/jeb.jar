package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jebglobal.MX;
import com.pnfsoftware.jebglobal.OA;
import com.pnfsoftware.jebglobal.ajr;
import com.pnfsoftware.jebglobal.lw;
import com.pnfsoftware.jebglobal.mN;
import com.pnfsoftware.jebglobal.pY;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class io {
   HE pC;
   IERoutineContext A;

   public io(HE var1) {
      this.pC = var1;
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      String var5 = var1.getMnemonic();
      if (var5.contains(".")) {
         var5 = var5.substring(0, var5.indexOf("."));
      }

      switch (var5) {
         case "DUP":
         case "MOV":
         case "UMOV":
         case "MOVI":
            this.kS(var1, var2, null);
            break;
         case "SMOV":
            this.A(var1, var2, null, true);
            break;
         case "MVN":
         case "MVNI":
            this.kS(var1, var2, OperationType.NOT);
            break;
         case "ADD":
            this.kS(var1, var2, OperationType.ADD);
            break;
         case "AND":
            this.kS(var1, var2, OperationType.AND);
            break;
         case "BIC":
            this.pC(var1, var2, Sv.K.wS);
            break;
         case "EOR":
            this.kS(var1, var2, OperationType.XOR);
            break;
         case "NEG":
            this.pC(var1, var2, Sv.K.fI);
            break;
         case "ORR":
            this.kS(var1, var2, OperationType.OR);
            break;
         case "ORN":
            this.pC(var1, var2, Sv.K.sY);
            break;
         case "SUB":
            this.kS(var1, var2, OperationType.SUB);
            break;
         case "SHL":
            this.kS(var1, var2, OperationType.SHL);
            break;
         case "USHR":
            this.kS(var1, var2, OperationType.SHR);
            break;
         case "SSHR":
            this.kS(var1, var2, OperationType.SAR);
            break;
         case "SADDW":
         case "SADDW2":
         case "UADDW":
         case "UADDW2":
            this.pC(var1, var2, OperationType.ADD, var5);
            break;
         case "SSUBW":
         case "SSUBW2":
         case "USUBW":
         case "USUBW2":
            this.pC(var1, var2, OperationType.SUB, var5);
            break;
         case "SADDL":
         case "SADDL2":
         case "UADDL":
         case "UADDL2":
            this.A(var1, var2, OperationType.ADD, var5);
            break;
         case "SSUBL":
         case "SSUBL2":
         case "USUBL":
         case "USUBL2":
            this.A(var1, var2, OperationType.SUB, var5);
            break;
         case "ADDP":
            if (var1.pC(0) instanceof lw) {
               this.pC(var1, var2, 2, (var1x, var2x) -> this.pC(var1x, (var0, var1xx) -> EUtil.add(var0, var1xx), var2x[1], var2x[2]));
            } else {
               this.A(var1, var2, (var0, var1x) -> EUtil.add(var0, var1x));
            }
            break;
         case "ADDV":
            this.A(var1, var2, (var0, var1x) -> EUtil.add(var0, var1x));
            break;
         case "SADDLP":
            this.pC(var1, var2, 1, (var1x, var2x) -> this.pC(var1x, var2x[1], true));
            break;
         case "UADDLP":
            this.pC(var1, var2, 1, (var1x, var2x) -> this.pC(var1x, var2x[1], false));
            break;
         case "SADDLV":
            this.pC(var1, var2, true, (var0, var1x) -> EUtil.add(var0, var1x));
            break;
         case "UADDLV":
            this.pC(var1, var2, false, (var0, var1x) -> EUtil.add(var0, var1x));
            break;
         case "SADALP":
            this.pC(var1, var2, 1, (var1x, var2x) -> this.pC(var1x, var2x[0], var2x[1], true));
            break;
         case "UADALP":
            this.pC(var1, var2, 1, (var1x, var2x) -> this.pC(var1x, var2x[0], var2x[1], false));
            break;
         case "CNT":
            this.pC(var1, var2, 1, (var1x, var2x) -> this.pC.pC.A(var2x[1].A(var1x)));
            break;
         case "BIF":
            this.pC(var1, var2, 2, (var1x, var2x) -> this.pC.gp.pC(var1x, var2x[0], var2x[1], var2x[2]));
            break;
         case "BIT":
            this.pC(var1, var2, 2, (var1x, var2x) -> this.pC.gp.A(var1x, var2x[0], var2x[1], var2x[2]));
            break;
         case "BSL":
            this.pC(var1, var2, 2, (var1x, var2x) -> this.pC.gp.kS(var1x, var2x[0], var2x[1], var2x[2]));
            break;
         case "EXT":
            this.pC(
               var1,
               var2,
               (var2x, var3x) -> this.A
                  .createCompose(var2x.A(), var3x.A())
                  .slice((int)var1.pC(3).getOperandValue() * 8, (int)var1.pC(3).getOperandValue() * 8 + var2x.UT())
            );
            break;
         case "FCVT":
            this.UT(var1, var2, var3, OperationType.FP2FP);
            break;
         case "FCVTZS":
            this.pC(var1, var2, var3, OperationType.FP2INT);
            break;
         case "FCVTZU":
            this.pC(var1, var2, var3, OperationType.FP2UINT);
            break;
         case "FCVTPS":
            this.A(var1, var2, var3, OperationType.FP2INT);
            break;
         case "FCVTPU":
            this.A(var1, var2, var3, OperationType.FP2UINT);
            break;
         case "SCVTF":
            this.kS(var1, var2, var3, OperationType.INT2FP);
            break;
         case "UCVTF":
            this.kS(var1, var2, var3, OperationType.UINT2FP);
            break;
         case "CMEQ":
            this.pC(var1, var2, OperationType.LOG_EQ);
            break;
         case "CMGE":
            this.pC(var1, var2, OperationType.GE_S);
            break;
         case "CMGT":
            this.pC(var1, var2, OperationType.GT_S);
            break;
         case "CMHI":
            this.pC(var1, var2, OperationType.GT_U);
            break;
         case "CMHS":
            this.pC(var1, var2, OperationType.GE_U);
            break;
         case "CMLE":
            this.pC(var1, var2, OperationType.LE_S);
            break;
         case "CMLT":
            this.pC(var1, var2, OperationType.LT_S);
            break;
         case "CMTST":
            this.pC(var1, var2, OperationType.LOG_AND);
            break;
         case "FCCMP":
         case "FCCMPE":
            this.A(var1, var2, var3);
            break;
         case "FCSEL":
            this.kS(var1, var2, var3);
            break;
         case "FCMP":
         case "FCMPE":
            this.wS(var1, var2, var3);
            break;
         case "SMIN":
            this.pC(var1, var2, 2, (var1x, var2x) -> EUtil.min(this.A, var2x[1].A(var1x), var2x[2].A(var1x), true));
            break;
         case "SMAX":
            this.pC(var1, var2, 2, (var1x, var2x) -> EUtil.max(this.A, var2x[1].A(var1x), var2x[2].A(var1x), true));
            break;
         case "UMIN":
            this.pC(var1, var2, 2, (var1x, var2x) -> EUtil.min(this.A, var2x[1].A(var1x), var2x[2].A(var1x), false));
            break;
         case "UMAX":
            this.pC(var1, var2, 2, (var1x, var2x) -> EUtil.max(this.A, var2x[1].A(var1x), var2x[2].A(var1x), false));
            break;
         case "SMINV":
            this.pC(var1, var2, true, true);
            break;
         case "SMAXV":
            this.pC(var1, var2, false, true);
            break;
         case "UMINV":
            this.pC(var1, var2, true, false);
            break;
         case "UMAXV":
            this.pC(var1, var2, false, false);
            break;
         case "SMINP":
            this.pC(var1, var2, 2, (var1x, var2x) -> this.pC(var1x, (var1xx, var2xx) -> EUtil.min(this.A, var1xx, var2xx, true), var2x[1], var2x[2]));
            break;
         case "SMAXP":
            this.pC(var1, var2, 2, (var1x, var2x) -> this.pC(var1x, (var1xx, var2xx) -> EUtil.max(this.A, var1xx, var2xx, true), var2x[1], var2x[2]));
            break;
         case "UMINP":
            this.pC(var1, var2, 2, (var1x, var2x) -> this.pC(var1x, (var1xx, var2xx) -> EUtil.min(this.A, var1xx, var2xx, false), var2x[1], var2x[2]));
            break;
         case "UMAXP":
            this.pC(var1, var2, 2, (var1x, var2x) -> this.pC(var1x, (var1xx, var2xx) -> EUtil.max(this.A, var1xx, var2xx, false), var2x[1], var2x[2]));
            break;
         case "ABS":
            this.pC(var1, var2, 1, (var1x, var2x) -> EUtil.abs(this.A, var2x[1].A(var1x)));
            break;
         case "SSHL":
            this.pC(var1, var2, OperationType.SHL, true);
            break;
         case "USHL":
            this.pC(var1, var2, OperationType.SHL, false);
            break;
         case "SHLL":
         case "SSHLL":
            this.kS(var1, var2, OperationType.SHL, true, 1);
            break;
         case "SHLL2":
         case "SSHLL2":
            this.kS(var1, var2, OperationType.SHL, true, 2);
            break;
         case "USHLL":
            this.kS(var1, var2, OperationType.SHL, false, 1);
            break;
         case "USHLL2":
            this.kS(var1, var2, OperationType.SHL, false, 2);
            break;
         case "SXTL":
            this.kS(var1, var2, OperationType.SHL, true, 1);
            break;
         case "SXTL2":
            this.kS(var1, var2, OperationType.SHL, true, 2);
            break;
         case "UXTL":
            this.kS(var1, var2, OperationType.SHL, false, 1);
            break;
         case "UXTL2":
            this.kS(var1, var2, OperationType.SHL, false, 2);
            break;
         case "SHRN":
            this.wS(var1, var2, OperationType.SHR, false, 1);
            break;
         case "SHRN2":
            this.wS(var1, var2, OperationType.SHR, false, 2);
            break;
         case "UZP1":
            this.pC(
               var1,
               var2,
               2,
               (var1x, var2x) -> this.pC(var2x[var1x < var2x[0].kS() / 2 ? 1 : 2], var1x < var2x[0].kS() / 2 ? var1x : var1x - var2x[0].kS() / 2)
            );
            break;
         case "UZP2":
            this.pC(
               var1, var2, 2, (var1x, var2x) -> this.A(var2x[var1x < var2x[0].kS() / 2 ? 1 : 2], var1x < var2x[0].kS() / 2 ? var1x : var1x - var2x[0].kS() / 2)
            );
            break;
         case "XTN":
            this.pC(var1, var2, 1, (var0, var1x) -> var1x[1].A(var0).part(var1x[1].wS() / 2));
            break;
         case "XTN2":
            this.pC(
               var1,
               var2,
               1,
               (var1x, var2x) -> (IEGeneric)(var1x < var2x[1].kS() ? this.pC.wS(var2x[0].wS()) : var2x[1].A(var1x - var2x[1].kS()).part(var2x[1].wS() / 2))
            );
            break;
         case "ZIP1":
            this.pC(var1, var2, 2, (var0, var1x) -> var1x[var0 % 2 + 1].A(var0 >>> 1));
            break;
         case "ZIP2":
            this.pC(var1, var2, 2, (var0, var1x) -> var1x[var0 % 2 + 1].A((var1x[0].kS() >>> 1) + (var0 >>> 1)));
            break;
         case "RBIT":
            this.pC(var1, var2, 1, (var1x, var2x) -> this.pC.pC.pC(var2x[1].A(var1x)));
            break;
         case "REV16":
            this.pC(var1, var2, 1, (var1x, var2x) -> this.pC(var2x[1], var1x.intValue(), 16));
            break;
         case "REV32":
            this.pC(var1, var2, 1, (var1x, var2x) -> this.pC(var2x[1], var1x.intValue(), 32));
            break;
         case "REV64":
            this.pC(var1, var2, 1, (var1x, var2x) -> this.pC(var2x[1], var1x.intValue(), 64));
            break;
         case "FMOV":
            this.kS(var1, var2, null);
            break;
         case "FADD":
            this.A(var1, var2, OperationType.FADD);
            break;
         case "FSUB":
            this.A(var1, var2, OperationType.FSUB);
            break;
         case "FMUL":
            this.A(var1, var2, OperationType.FMUL);
            break;
         case "FDIV":
            this.A(var1, var2, OperationType.FDIV);
            break;
         case "FABS":
            this.pC(var1, var2, 1, true, (var1x, var2x) -> EUtil.fabs(this.A, var2x[1].A(var1x)));
            break;
         case "FMIN":
            this.pC(var1, var2, 2, true, (var1x, var2x) -> EUtil.fmin(this.A, var2x[1].A(var1x), var2x[2].A(var1x)));
            break;
         case "FMAX":
            this.pC(var1, var2, 2, true, (var1x, var2x) -> EUtil.fmax(this.A, var2x[1].A(var1x), var2x[2].A(var1x)));
            break;
         case "FNEG":
            this.pC(var1, var2, 1, true, (var1x, var2x) -> this.A.createOperation(OperationType.FSUB, this.pC.wS(var2x[1].wS()), var2x[1].A(var1x)));
            break;
         case "FNMUL":
            this.pC(
               var1,
               var2,
               2,
               true,
               (var1x, var2x) -> this.A
                  .createOperation(
                     OperationType.FSUB, this.pC.wS(var2x[1].wS()), this.A.createOperation(OperationType.FMUL, var2x[1].A(var1x), var2x[2].A(var1x))
                  )
            );
            break;
         case "FMADD":
            this.pC(
               var1,
               var2,
               3,
               true,
               (var1x, var2x) -> this.A
                  .createOperation(OperationType.FADD, var2x[3].A(var1x), this.A.createOperation(OperationType.FMUL, var2x[1].A(var1x), var2x[2].A(var1x)))
            );
            break;
         case "FMSUB":
            this.pC(
               var1,
               var2,
               3,
               true,
               (var1x, var2x) -> this.A
                  .createOperation(OperationType.FSUB, var2x[3].A(var1x), this.A.createOperation(OperationType.FMUL, var2x[1].A(var1x), var2x[2].A(var1x)))
            );
            break;
         case "FNMADD":
            this.pC(
               var1,
               var2,
               3,
               true,
               (var1x, var2x) -> this.A
                  .createOperation(
                     OperationType.FSUB,
                     this.pC.wS(var2x[1].wS()),
                     this.A
                        .createOperation(
                           OperationType.FADD, var2x[3].A(var1x), this.A.createOperation(OperationType.FMUL, var2x[1].A(var1x), var2x[2].A(var1x))
                        )
                  )
            );
            break;
         case "FNMSUB":
            this.pC(
               var1,
               var2,
               3,
               true,
               (var1x, var2x) -> this.A
                  .createOperation(OperationType.FSUB, this.A.createOperation(OperationType.FMUL, var2x[1].A(var1x), var2x[2].A(var1x)), var2x[3].A(var1x))
            );
            break;
         default:
            mN var8 = MX.pC(var1);
            if (var8 != null) {
               if (var8.ys()) {
                  this.pC.UT.pC(var1, var8, var2, var3);
               } else {
                  this.pC.UT.A(var1, var8, var2, var3);
               }
            } else {
               var2.add(this.pC.pC(var1, var3, true));
            }
      }
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, OperationType var5) {
      if (var1.getCountOfOperands() == 3) {
         var2.add(this.pC.pC(var1, var3, true));
      } else {
         OA var6 = this.pC.pC(var1.A(), 1, 2);
         if (var6.wS() == 16) {
            var2.add(this.pC.pC(var1, var3, true));
         } else {
            this.kS(var1, var2, var5);
         }
      }
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, OperationType var5) {
      if (var1.getCountOfOperands() == 3) {
         var2.add(this.pC.pC(var1, var3, true));
      } else {
         this.pC(var1, var2, var5.getOperandCount(), (var2x, var3x) -> this.pC(var5, var3x[1].A(var2x), var3x[0].wS()));
      }
   }

   private OperationType pC(OperationType var1) {
      switch (var1) {
         case FP2INT:
            return OperationType.INT2FP;
         case FP2UINT:
            return OperationType.UINT2FP;
         case INT2FP:
            return OperationType.FP2INT;
         case UINT2FP:
            return OperationType.FP2UINT;
         default:
            return null;
      }
   }

   private IEGeneric pC(OperationType var1, IEGeneric var2, int var3) {
      OperationType var4 = this.pC(var1);
      IEGeneric var5 = EUtil.cmpFloat(
         var1 == OperationType.FP2INT ? OperationType.GE_S : OperationType.GE_U,
         this.A.createConversionOperation(var4, this.A.createConversionOperation(var1, var2, var3), var3),
         this.A.createConversionOperation(OperationType.FP2FP, var2, var3)
      );
      return this.A
         .createCond(var5, this.A.createConversionOperation(var1, var2, var3), EUtil.add(this.A.createConversionOperation(var1, var2, var3), ajr.pC(1L, var3)));
   }

   private void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, OperationType var5) {
      if (var1.getCountOfOperands() == 3) {
         var2.add(this.pC.pC(var1, var3, true));
      } else {
         this.wS(var1, var2, var3, var5);
      }
   }

   private void wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, OperationType var5) {
      OA var6 = this.pC.pC(var1.A(), 0, 2);
      if (var6.wS() == 16) {
         var2.add(this.pC.pC(var1, var3, true));
      } else {
         this.kS(var1, var2, var5);
      }
   }

   private void UT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, OperationType var5) {
      OA var6 = this.pC.pC(var1.A(), 0, 2);
      OA var7 = this.pC.pC(var1.A(), 1, 2);
      if (var6.wS() != 16 && var7.wS() != 16) {
         this.kS(var1, var2, var5);
      } else {
         var2.add(this.pC.pC(var1, var3, true));
      }
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      IEGeneric var5 = this.pC.RW.pC(var1.pC(3), null);
      IEVar var6 = this.pC.E(var5.getBitsize());
      this.pC.pC(var1, var2, var6, var5);
      IEGeneric var7 = this.pC.pC(var1, (IInstructionOperand)var1.pC(0));
      IEGeneric var8 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1));
      if (var8.getBitsize() != 16 && var7.getBitsize() != 16) {
         int var9 = (int)var1.pC(2).getOperandValue();
         this.pC
            .pC(var1, var2, this.pC.RW.pC, this.A.createCond(var6, this.A.createOperation(OperationType.FLT, var7, var8), this.A.createImm(var9 >>> 3 & 1, 1)));
         this.pC
            .pC(var1, var2, this.pC.RW.A, this.A.createCond(var6, this.A.createOperation(OperationType.FEQ, var7, var8), this.A.createImm(var9 >>> 2 & 1, 1)));
         this.pC
            .pC(var1, var2, this.pC.RW.kS, this.A.createCond(var6, this.A.createOperation(OperationType.FGE, var7, var8), this.A.createImm(var9 >>> 1 & 1, 1)));
         this.pC.pC(var1, var2, this.pC.RW.wS, this.A.createCond(var6, this.A.createImm(0L, 1), this.A.createImm(var9 & 1, 1)));
      } else {
         var2.add(this.pC.pC(var1, var3, true));
      }
   }

   private void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.pC(0));
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1));
      IEGeneric var7 = this.pC.pC(var1, (IInstructionOperand)var1.pC(2));
      IEGeneric var8 = this.pC.RW.pC(var1.pC(3), null);
      this.pC.pC(var1, var2, var5, this.A.createCond(var8, var6, var7));
   }

   private void wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)var1.pC(0));
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1));
      if (var6.getBitsize() != 16 && var5.getBitsize() != 16) {
         this.pC.pC(var1, var2, this.pC.RW.pC, this.A.createOperation(OperationType.FLT, var5, var6));
         this.pC.pC(var1, var2, this.pC.RW.A, this.A.createOperation(OperationType.FEQ, var5, var6));
         this.pC.pC(var1, var2, this.pC.RW.kS, this.A.createOperation(OperationType.FGE, var5, var6));
         this.pC.pC(var1, var2, this.pC.RW.wS, this.A.createImm(0L, 1));
      } else {
         var2.add(this.pC.pC(var1, var3, true));
      }
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, boolean var3, boolean var4) {
      pY var5 = this.pC.pC(var1, var1.pC(0));
      OA var6 = this.pC.pC(var1.A(), 1, 2);
      IEVar var7 = this.pC.E(var6.A(0).getBitsize());
      this.pC.pC(var1, var2, var7, var6.A(0));

      for (int var8 = 1; var8 < var6.kS(); var8++) {
         this.pC.pC(var1, var2, var7, var3 ? EUtil.min(this.A, var6.A(var8), var7, var4) : EUtil.max(this.A, var6.A(var8), var7, var4));
      }

      this.pC.pC(var1, var2, var5, var7);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OperationType var3) {
      this.pC(var1, var2, var3.getOperandCount(), (var2x, var3x) -> this.pC(var2x, var3x[1], var3x[2], var3));
   }

   private IEGeneric pC(int var1, OA var2, OA var3, OperationType var4) {
      return this.A.createCond(this.A.createOperation(var4, var2.A(var1), var3.A(var1)), this.pC.UT(var2.wS()), this.pC.wS(var2.wS()));
   }

   private IEGeneric pC(OA var1, int var2) {
      return this.pC(var1.pC(), var2, var1.wS());
   }

   private IEGeneric A(OA var1, int var2) {
      return this.A(var1.pC(), var2, var1.wS());
   }

   private IEGeneric pC(IEGeneric var1, int var2, int var3) {
      return var1.slice(2 * var2 * var3, (2 * var2 + 1) * var3);
   }

   private IEGeneric A(IEGeneric var1, int var2, int var3) {
      return var1.slice((2 * var2 + 1) * var3, (2 * var2 + 2) * var3);
   }

   private IEGeneric pC(int var1, BiFunction var2, OA var3, OA var4) {
      int var5 = var4.wS() * var4.kS();
      IECompose var6 = this.A.createCompose(var3.pC().part(var5), var4.pC().part(var5));
      return (IEGeneric)var2.apply(this.pC(var6, var1, var3.wS()), this.A(var6, var1, var3.wS()));
   }

   private IEGeneric pC(int var1, OA var2, boolean var3) {
      int var4 = var2.wS() * 2;
      return EUtil.add(EUtil.extend(this.pC(var2, var1), var4, var3), EUtil.extend(this.A(var2, var1), var4, var3));
   }

   private IEGeneric pC(int var1, OA var2, OA var3, boolean var4) {
      return EUtil.add(var2.A(var1), this.pC(var1, var3, var4));
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OperationType var3, String var4) {
      this.pC(var1, var2, var3, var4.charAt(0) == 'S', var4.charAt(var4.length() - 1) == '2' ? 2 : 1);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OperationType var3, boolean var4, int var5) {
      int var6 = var5 == 2 ? 64 : 0;
      this.pC(
         var1,
         var2,
         2,
         (var4x, var5x) -> this.A.createOperation(var3, var5x[1].A(var4x), EUtil.extend(var5x[2].pC(var4x, var6, var5x[2].wS()), var5x[0].wS(), var4))
      );
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OperationType var3, String var4) {
      this.A(var1, var2, var3, var4.charAt(0) == 'S', var4.charAt(var4.length() - 1) == '2' ? 2 : 1);
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OperationType var3, boolean var4, int var5) {
      int var6 = var5 == 2 ? 64 : 0;
      this.pC(
         var1,
         var2,
         2,
         (var4x, var5x) -> this.A
            .createOperation(
               var3,
               EUtil.extend(var5x[1].pC(var4x, var6, var5x[1].wS()), var5x[0].wS(), var4),
               EUtil.extend(var5x[2].pC(var4x, var6, var5x[2].wS()), var5x[0].wS(), var4)
            )
      );
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OperationType var3, boolean var4) {
      this.pC(
         var1,
         var2,
         2,
         (var3x, var4x) -> this.pC(
            var3,
            EUtil.extend(var4x[1].pC(var3x, 0, var4x[1].wS()), var4x[0].wS(), var4),
            this.pC(EUtil.extend(var4x[2].A(var3x).part(8), var4x[0].wS(), true), var4x[1].wS()),
            var4
         )
      );
   }

   private IEGeneric pC(IEGeneric var1, int var2) {
      IEImm var3 = this.A.createImm(var2 + 1, var1.getBitsize());
      IEImm var4 = this.A.createImm(-(var2 + 1), var1.getBitsize());
      return this.A.createCond(EUtil.gtS(var1, var3), var3, this.A.createCond(EUtil.ltS(var1, var4), var4, var1));
   }

   private void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OperationType var3, boolean var4, int var5) {
      int var6 = var5 == 2 ? 64 : 0;
      if (var1.getCountOfOperands() == 3) {
         this.pC(
            var1,
            var2,
            2,
            (var4x, var5x) -> this.A.createOperation(var3, EUtil.extend(var5x[1].pC(var4x, var6, var5x[1].wS()), var5x[0].wS(), var4), var5x[2].A(var4x))
         );
      } else {
         this.pC(var1, var2, 1, (var2x, var3x) -> EUtil.extend(var3x[1].pC(var2x, var6, var3x[1].wS()), var3x[0].wS(), var4));
      }
   }

   private void wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OperationType var3, boolean var4, int var5) {
      int var6 = var5 == 2 ? 64 : 0;
      if (var1.getCountOfOperands() == 3) {
         this.pC(
            var1,
            var2,
            2,
            (var3x, var4x) -> (IEGeneric)(var3x == 0 && var6 > 0
               ? this.A.createCompose(this.pC.wS(var6), this.A.createOperation(var3, var4x[1].A(var3x), var4x[2].A(var3x)).part(var4x[1].wS() / 2))
               : (var3x >= var4x[1].kS() ? null : this.A.createOperation(var3, var4x[1].A(var3x), var4x[2].A(var3x)).part(var4x[1].wS() / 2)))
         );
      } else {
         var2.add(this.pC.pC(var1, -1L, true));
      }
   }

   private IEGeneric pC(OA var1, int var2, int var3) {
      int var4 = var3 / var1.wS();
      return var1.A(var2 / var4 * var4 + (var4 - var2 % var4 - 1));
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, Sv.K var3) {
      int var4 = var3 == null ? 1 : var3.pC().getOperandCount();
      if (var4 == 1) {
         if (var3 == null) {
            this.pC(var1, var2, var4, (var0, var1x) -> var1x[1].A(var0));
         } else {
            var2.add(this.pC.pC(var1, -1L, true));
         }
      } else {
         IEGeneric var5 = var3.kS(this.pC.A());
         this.pC(
            var1,
            var2,
            var4,
            (var3x, var4x) -> var5 == null
               ? this.A.createOperation(var3.pC(), var3.pC(var4x[1].A(var3x)), var3.A(var4x[2].A(var3x)))
               : EUtil.add(var3.pC(var4x[1].A(var3x)), var3.A(var4x[2].A(var3x)), var5.zeroExtend(var4x[1].wS()))
         );
      }
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OperationType var3) {
      OA var4 = this.pC.pC(var1.A(), 0, 2);
      if (var4.wS() == 16) {
         var2.add(this.pC.pC(var1, -1L, true));
      } else {
         this.kS(var1, var2, var3);
      }
   }

   private void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OperationType var3) {
      this.A(var1, var2, var3, false);
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OperationType var3, boolean var4) {
      if (var3 == null) {
         this.pC(var1, var2, 1, (var1x, var2x) -> EUtil.safeExtend(var2x[1].A(var1x), var2x[0].wS(), var4));
      } else if (var3.getOperandCount() == 1) {
         if (var3.isConversion()) {
            this.pC(var1, var2, var3.getOperandCount(), (var2x, var3x) -> this.A.createConversionOperation(var3, var3x[1].A(var2x), var3x[0].wS()));
         } else {
            this.pC(var1, var2, var3.getOperandCount(), (var3x, var4x) -> this.A.createOperation(var3, EUtil.extend(var4x[1].A(var3x), var4x[0].wS(), var4)));
         }
      } else {
         this.pC(
            var1,
            var2,
            var3.getOperandCount(),
            (var3x, var4x) -> this.A
               .createOperation(var3, EUtil.extend(var4x[1].A(var3x), var4x[0].wS(), var4), EUtil.extend(var4x[2].A(var3x), var4x[0].wS(), var4))
         );
      }
   }

   private IEGeneric pC(OperationType var1, IEGeneric var2, IEGeneric var3, boolean var4) {
      if (var1 != OperationType.SHL && var1 != OperationType.SHR && var1 != OperationType.SAR) {
         return this.A.createOperation(var1, var2, var3);
      } else if (var3 instanceof IEImm
         && var3.asImm().getValue().compareTo(BigInteger.ZERO) >= 0
         && var3.asImm().getValue().compareTo(BigInteger.valueOf(var2.getBitsize())) < 0) {
         return this.A.createOperation(var1, var2, var3);
      } else {
         IEOperation var5 = EUtil.gtS(EUtil.safeExtend(var3, var2.getBitsize(), false), this.A.createImm(var2.getBitsize(), var2.getBitsize()));
         IEOperation var6 = EUtil.ltS(EUtil.safeExtend(var3, var2.getBitsize(), false), EUtil.zero(var2.getBitsize()));
         return var1 == OperationType.SHL
            ? this.A
               .createCond(
                  var5,
                  EUtil.zero(var2.getBitsize()),
                  this.A
                     .createCond(
                        var6,
                        this.pC(var4 ? OperationType.SAR : OperationType.SHR, var2, EUtil.sub(EUtil.zero(var3.getBitsize()), var3), var4),
                        this.A.createOperation(var1, var2, var3)
                     )
               )
            : this.A
               .createCond(
                  var5,
                  (IEGeneric)(!var4
                     ? EUtil.zero(var2.getBitsize())
                     : this.A.createCond(EUtil.eq(var2.msb(), EUtil.zero(1)), EUtil.zero(var2.getBitsize()), this.A.createImm(-1L, var2.getBitsize()))),
                  this.A.createOperation(var1, var2, var3)
               );
      }
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, int var3, boolean var4, BiFunction var5) {
      OA var6 = this.pC.pC(var1.A(), 0, 1 + var3);
      if (var6.wS() == 16) {
         var2.add(this.pC.pC(var1, -1L, true));
      } else {
         this.pC(var1, var2, var3, var5);
      }
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, int var3, BiFunction var4) {
      OA var5 = this.pC.pC(var1.A(), 0, 1 + var3);
      OA[] var6 = new OA[1 + var3];
      var6[0] = var5;

      for (int var7 = 1; var7 < 1 + var3; var7++) {
         var6[var7] = this.pC.pC(var1.A(), var7, 1 + var3).pC(var5);
      }

      this.pC(var1, var2, var5, var2x -> (IEGeneric)var4.apply(var2x, var6));
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, BiFunction var3) {
      byte var4 = 3;
      OA var5 = this.pC.pC(var1.A(), 0, 1 + var4);
      OA[] var6 = new OA[1 + var4];
      var6[0] = var5;

      for (int var7 = 1; var7 < 1 + var4; var7++) {
         var6[var7] = this.pC.pC(var1.A(), var7, 1 + var4).pC(var5);
      }

      this.pC.pC(var1, var2, var5.pC(), (IEGeneric)var3.apply(var6[1], var6[2]));
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, OA var3, Function var4) {
      boolean var5 = true;
      if (var3.kS() == 1) {
         if (var3.E()) {
            this.pC.pC(var1, var2, new pY(var3.pC()), EUtil.safeExtend((IEGeneric)var4.apply(0), var3.wS(), false));
         } else {
            this.pC.pC(var1, var2, var3.A(0), (IEGeneric)var4.apply(0));
         }
      } else if (var5) {
         ArrayList var8 = new ArrayList();

         for (int var10 = 0; var10 < var3.kS(); var10++) {
            var8.add((IEGeneric)var4.apply(var10));
         }

         ArrayList var11 = new ArrayList();
         var11.add(null);
         var8.removeAll(var11);
         if (var8.size() == 1) {
            this.pC.pC(var1, var2, var3.pC(), (IEGeneric)var8.get(0));
         } else {
            this.pC.pC(var1, var2, var3.pC(), this.A.createCompose(var8));
         }
      } else {
         int var6 = var3.wS() * var3.kS();

         for (int var7 = 0; var7 < var3.kS(); var7++) {
            this.pC.pC(var1, var2, var3.A(var7), (IEGeneric)var4.apply(var7));
         }

         if (var3.E() && var6 < var3.pC().getBitsize()) {
            IEGeneric var9 = var3.pC().slice(var6, var3.pC().getBitsize());
            this.pC.pC(var1, var2, var9, this.pC.wS(var9.getBitsize()));
         }
      }
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, BiFunction var3) {
      this.pC(var1, var2, null, var3);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, Boolean var3, BiFunction var4) {
      byte var5 = 1;
      OA var6 = this.pC.pC(var1.A(), 0, 1 + var5);
      OA var7 = this.pC.pC(var1.A(), 1, 1 + var5).pC(var6);
      OA[] var8 = new OA[1 + var5];
      var8[0] = var6;
      int var9 = var6.wS();
      IEGeneric var10 = pC(var7.A(0), var9, var3);

      for (int var11 = 1; var11 < var7.kS(); var11++) {
         var10 = (IEGeneric)var4.apply(pC(var7.A(var11), var9, var3), var10);
      }

      if (var6.kS() == 1) {
         if (var6.E()) {
            this.pC.pC(var1, var2, new pY(var6.pC()), EUtil.safeExtend(var10, var6.wS(), false));
         } else {
            this.pC.pC(var1, var2, var6.A(0), var10);
         }
      } else {
         var2.add(this.pC.pC(var1, -1L, true));
      }
   }

   private static IEGeneric pC(IEGeneric var0, int var1, Boolean var2) {
      return var2 == null ? var0 : EUtil.safeExtend(var0, var1, var2);
   }
}
