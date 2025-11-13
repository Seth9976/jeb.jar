package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ZS {
   uq q;
   IERoutineContext RF;

   public ZS(uq var1) {
      this.q = var1;
   }

   public void q(fA var1, List var2, long var3) {
      String var5 = var1.getMnemonic();
      if (var5.contains(".")) {
         var5 = var5.substring(0, var5.indexOf("."));
      }

      switch (var5) {
         case "DUP":
         case "MOV":
         case "UMOV":
         case "MOVI":
            this.xK(var1, var2, null);
            break;
         case "SMOV":
            this.q(var1, var2, null, true);
            break;
         case "MVN":
         case "MVNI":
            this.xK(var1, var2, OperationType.NOT);
            break;
         case "ADD":
            this.xK(var1, var2, OperationType.ADD);
            break;
         case "AND":
            this.xK(var1, var2, OperationType.AND);
            break;
         case "BIC":
            this.q(var1, var2, Lb.nI.Dw);
            break;
         case "EOR":
            this.xK(var1, var2, OperationType.XOR);
            break;
         case "ORR":
            this.xK(var1, var2, OperationType.OR);
            break;
         case "ORN":
            this.q(var1, var2, Lb.nI.gO);
            break;
         case "SUB":
            this.xK(var1, var2, OperationType.SUB);
            break;
         case "SHL":
            this.xK(var1, var2, OperationType.SHL);
            break;
         case "USHR":
            this.xK(var1, var2, OperationType.SHR);
            break;
         case "SSHR":
            this.xK(var1, var2, OperationType.SAR);
            break;
         case "SADDW":
         case "SADDW2":
         case "UADDW":
         case "UADDW2":
            this.q(var1, var2, OperationType.ADD, var5);
            break;
         case "SSUBW":
         case "SSUBW2":
         case "USUBW":
         case "USUBW2":
            this.q(var1, var2, OperationType.SUB, var5);
            break;
         case "SADDL":
         case "SADDL2":
         case "UADDL":
         case "UADDL2":
            this.RF(var1, var2, OperationType.ADD, var5);
            break;
         case "SSUBL":
         case "SSUBL2":
         case "USUBL":
         case "USUBL2":
            this.RF(var1, var2, OperationType.SUB, var5);
            break;
         case "ADDP":
            if (var1.q(0) instanceof RI) {
               this.RF(var1, var2, 2, true, (var1x, var2x) -> this.q(var1x, (var0, var1xx) -> EUtil.add(var0, var1xx), var2x[1], var2x[2]));
            } else {
               this.q(var1, var2, (var0, var1x) -> EUtil.add(var0, var1x));
            }
            break;
         case "ADDV":
            this.q(var1, var2, (var0, var1x) -> EUtil.add(var0, var1x));
            break;
         case "SADDLP":
            this.RF(var1, var2, 1, true, (var1x, var2x) -> this.q(var1x, var2x[1], true));
            break;
         case "UADDLP":
            this.RF(var1, var2, 1, true, (var1x, var2x) -> this.q(var1x, var2x[1], false));
            break;
         case "SADDLV":
            this.q(var1, var2, true, (var0, var1x) -> EUtil.add(var0, var1x));
            break;
         case "UADDLV":
            this.q(var1, var2, false, (var0, var1x) -> EUtil.add(var0, var1x));
            break;
         case "SADALP":
            this.RF(var1, var2, 1, true, (var1x, var2x) -> this.q(var1x, var2x[0], var2x[1], true));
            break;
         case "UADALP":
            this.RF(var1, var2, 1, true, (var1x, var2x) -> this.q(var1x, var2x[0], var2x[1], false));
            break;
         case "CNT":
            this.RF(var1, var2, 1, false, (var1x, var2x) -> this.q.Ub.q(var1x, var2x[1]));
            break;
         case "BIF":
            this.RF(var1, var2, 2, false, (var1x, var2x) -> this.q.Ub.q(var1x, var2x[0], var2x[1], var2x[2]));
            break;
         case "BIT":
            this.RF(var1, var2, 2, false, (var1x, var2x) -> this.q.Ub.RF(var1x, var2x[0], var2x[1], var2x[2]));
            break;
         case "BSL":
            this.RF(var1, var2, 2, false, (var1x, var2x) -> this.q.Ub.xK(var1x, var2x[0], var2x[1], var2x[2]));
            break;
         case "EXT":
            this.RF(var1, var2, 3, false, (var2x, var3x) -> this.q.Ub.q(var2x, var3x[0], var3x[1], var3x[2], (int)var1.q(3).getOperandValue()));
            break;
         case "FCVT":
            this.Uv(var1, var2, var3, OperationType.FP2FP);
            break;
         case "FCVTZS":
            this.q(var1, var2, var3, OperationType.FP2INT);
            break;
         case "FCVTZU":
            this.q(var1, var2, var3, OperationType.FP2UINT);
            break;
         case "FCVTPS":
            this.RF(var1, var2, var3, OperationType.FP2INT);
            break;
         case "FCVTPU":
            this.RF(var1, var2, var3, OperationType.FP2UINT);
            break;
         case "SCVTF":
            this.xK(var1, var2, var3, OperationType.INT2FP);
            break;
         case "UCVTF":
            this.xK(var1, var2, var3, OperationType.UINT2FP);
            break;
         case "CMEQ":
            this.q(var1, var2, OperationType.LOG_EQ);
            break;
         case "CMGE":
            this.q(var1, var2, OperationType.GE_S);
            break;
         case "CMGT":
            this.q(var1, var2, OperationType.GT_S);
            break;
         case "CMHI":
            this.q(var1, var2, OperationType.GT_U);
            break;
         case "CMHS":
            this.q(var1, var2, OperationType.GE_U);
            break;
         case "CMLE":
            this.q(var1, var2, OperationType.LE_S);
            break;
         case "CMLT":
            this.q(var1, var2, OperationType.LT_S);
            break;
         case "CMTST":
            this.q(var1, var2, OperationType.LOG_AND);
            break;
         case "FCCMP":
         case "FCCMPE":
            this.RF(var1, var2, var3);
            break;
         case "FCSEL":
            this.xK(var1, var2, var3);
            break;
         case "FCMP":
         case "FCMPE":
            this.Dw(var1, var2, var3);
            break;
         case "SMIN":
            this.RF(var1, var2, 2, true, (var1x, var2x) -> EUtil.min(this.RF, var2x[1].RF(var1x), var2x[2].RF(var1x), true));
            break;
         case "SMAX":
            this.RF(var1, var2, 2, true, (var1x, var2x) -> EUtil.max(this.RF, var2x[1].RF(var1x), var2x[2].RF(var1x), true));
            break;
         case "UMIN":
            this.RF(var1, var2, 2, true, (var1x, var2x) -> EUtil.min(this.RF, var2x[1].RF(var1x), var2x[2].RF(var1x), false));
            break;
         case "UMAX":
            this.RF(var1, var2, 2, true, (var1x, var2x) -> EUtil.max(this.RF, var2x[1].RF(var1x), var2x[2].RF(var1x), false));
            break;
         case "SMINV":
            this.q(var1, var2, true, true);
            break;
         case "SMAXV":
            this.q(var1, var2, false, true);
            break;
         case "UMINV":
            this.q(var1, var2, true, false);
            break;
         case "UMAXV":
            this.q(var1, var2, false, false);
            break;
         case "SMINP":
            this.RF(var1, var2, 2, true, (var1x, var2x) -> this.q(var1x, (var1xx, var2xx) -> EUtil.min(this.RF, var1xx, var2xx, true), var2x[1], var2x[2]));
            break;
         case "SMAXP":
            this.RF(var1, var2, 2, true, (var1x, var2x) -> this.q(var1x, (var1xx, var2xx) -> EUtil.max(this.RF, var1xx, var2xx, true), var2x[1], var2x[2]));
            break;
         case "UMINP":
            this.RF(var1, var2, 2, true, (var1x, var2x) -> this.q(var1x, (var1xx, var2xx) -> EUtil.min(this.RF, var1xx, var2xx, false), var2x[1], var2x[2]));
            break;
         case "UMAXP":
            this.RF(var1, var2, 2, true, (var1x, var2x) -> this.q(var1x, (var1xx, var2xx) -> EUtil.max(this.RF, var1xx, var2xx, false), var2x[1], var2x[2]));
            break;
         case "ABS":
            this.RF(var1, var2, 1, true, (var1x, var2x) -> EUtil.abs(this.RF, var2x[1].RF(var1x)));
            break;
         case "SHLL":
            this.xK(var1, var2, OperationType.SHL, false, 1);
            break;
         case "SHLL2":
            this.xK(var1, var2, OperationType.SHL, false, 2);
            break;
         case "SSHLL":
            this.xK(var1, var2, OperationType.SHL, true, 1);
            break;
         case "SSHLL2":
            this.xK(var1, var2, OperationType.SHL, true, 2);
            break;
         case "USHLL":
            this.xK(var1, var2, OperationType.SHL, false, 1);
            break;
         case "USHLL2":
            this.xK(var1, var2, OperationType.SHL, false, 2);
            break;
         case "SXTL":
            this.xK(var1, var2, OperationType.SHL, true, 1);
            break;
         case "SXTL2":
            this.xK(var1, var2, OperationType.SHL, true, 2);
            break;
         case "UXTL":
            this.xK(var1, var2, OperationType.SHL, false, 1);
            break;
         case "UXTL2":
            this.xK(var1, var2, OperationType.SHL, false, 2);
            break;
         case "SHRN":
            this.Dw(var1, var2, OperationType.SHR, false, 1);
            break;
         case "SHRN2":
            this.Dw(var1, var2, OperationType.SHR, false, 2);
            break;
         case "UZP1":
            this.RF(
               var1,
               var2,
               2,
               true,
               (var1x, var2x) -> this.q(var2x[var1x < var2x[0].RF() / 2 ? 1 : 2], var1x < var2x[0].RF() / 2 ? var1x : var1x - var2x[0].RF() / 2)
            );
            break;
         case "UZP2":
            this.RF(
               var1,
               var2,
               2,
               true,
               (var1x, var2x) -> this.RF(var2x[var1x < var2x[0].RF() / 2 ? 1 : 2], var1x < var2x[0].RF() / 2 ? var1x : var1x - var2x[0].RF() / 2)
            );
            break;
         case "XTN":
            this.RF(var1, var2, 1, true, (var0, var1x) -> var1x[1].RF(var0).part(var1x[1].xK() / 2));
            break;
         case "XTN2":
            this.RF(
               var1,
               var2,
               1,
               true,
               (var1x, var2x) -> (IEGeneric)(var1x < var2x[1].RF() ? this.q.Uv(var2x[0].xK()) : var2x[1].RF(var1x - var2x[1].RF()).part(var2x[1].xK() / 2))
            );
            break;
         case "FMOV":
            this.xK(var1, var2, null);
            break;
         case "FADD":
            this.RF(var1, var2, OperationType.FADD);
            break;
         case "FSUB":
            this.RF(var1, var2, OperationType.FSUB);
            break;
         case "FMUL":
            this.RF(var1, var2, OperationType.FMUL);
            break;
         case "FDIV":
            this.RF(var1, var2, OperationType.FDIV);
            break;
         case "FABS":
            this.q(var1, var2, 1, true, (var1x, var2x) -> EUtil.fabs(this.RF, var2x[1].RF(var1x)));
            break;
         case "FMIN":
            this.q(var1, var2, 2, true, (var1x, var2x) -> EUtil.fmin(this.RF, var2x[1].RF(var1x), var2x[2].RF(var1x)));
            break;
         case "FMAX":
            this.q(var1, var2, 2, true, (var1x, var2x) -> EUtil.fmax(this.RF, var2x[1].RF(var1x), var2x[2].RF(var1x)));
            break;
         case "FNEG":
            this.q(var1, var2, 1, true, (var1x, var2x) -> this.RF.createOperation(OperationType.FSUB, this.q.Uv(var2x[1].xK()), var2x[1].RF(var1x)));
            break;
         case "FNMUL":
            this.q(
               var1,
               var2,
               2,
               true,
               (var1x, var2x) -> this.RF
                  .createOperation(
                     OperationType.FSUB, this.q.Uv(var2x[1].xK()), this.RF.createOperation(OperationType.FMUL, var2x[1].RF(var1x), var2x[2].RF(var1x))
                  )
            );
            break;
         case "FMADD":
            this.q(
               var1,
               var2,
               3,
               true,
               (var1x, var2x) -> this.RF
                  .createOperation(OperationType.FADD, var2x[3].RF(var1x), this.RF.createOperation(OperationType.FMUL, var2x[1].RF(var1x), var2x[2].RF(var1x)))
            );
            break;
         case "FMSUB":
            this.q(
               var1,
               var2,
               3,
               true,
               (var1x, var2x) -> this.RF
                  .createOperation(OperationType.FSUB, var2x[3].RF(var1x), this.RF.createOperation(OperationType.FMUL, var2x[1].RF(var1x), var2x[2].RF(var1x)))
            );
            break;
         case "FNMADD":
            this.q(
               var1,
               var2,
               3,
               true,
               (var1x, var2x) -> this.RF
                  .createOperation(
                     OperationType.FSUB,
                     this.q.Uv(var2x[1].xK()),
                     this.RF
                        .createOperation(
                           OperationType.FADD, var2x[3].RF(var1x), this.RF.createOperation(OperationType.FMUL, var2x[1].RF(var1x), var2x[2].RF(var1x))
                        )
                  )
            );
            break;
         case "FNMSUB":
            this.q(
               var1,
               var2,
               3,
               true,
               (var1x, var2x) -> this.RF
                  .createOperation(OperationType.FSUB, this.RF.createOperation(OperationType.FMUL, var2x[1].RF(var1x), var2x[2].RF(var1x)), var2x[3].RF(var1x))
            );
            break;
         default:
            fV var8 = PG.q(var1);
            if (var8 != null) {
               if (var8.nf()) {
                  this.q.o.q(var1, var8, var2, var3);
               } else {
                  this.q.o.RF(var1, var8, var2, var3);
               }
            } else {
               var2.add(this.q.q(var1, var3, true));
            }
      }
   }

   private void q(fA var1, List var2, long var3, OperationType var5) {
      if (var1.getCountOfOperands() == 3) {
         var2.add(this.q.q(var1, var3, true));
      } else {
         RX var6 = this.q.q(var1.RF(), 1, 2);
         if (var6.xK() == 16) {
            var2.add(this.q.q(var1, var3, true));
         } else {
            this.xK(var1, var2, var5);
         }
      }
   }

   private void RF(fA var1, List var2, long var3, OperationType var5) {
      if (var1.getCountOfOperands() == 3) {
         var2.add(this.q.q(var1, var3, true));
      } else {
         this.RF(var1, var2, var5.getOperandCount(), false, (var2x, var3x) -> this.q(var5, var3x[1].RF(var2x), var3x[0].xK()));
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private OperationType q(OperationType var1) {
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

   private IEGeneric q(OperationType var1, IEGeneric var2, int var3) {
      OperationType var4 = this.q(var1);
      IEGeneric var5 = EUtil.cmpFloat(
         var1 == OperationType.FP2INT ? OperationType.GE_S : OperationType.GE_U,
         this.RF.createConversionOperation(var4, this.RF.createConversionOperation(var1, var2, var3), var3),
         this.RF.createConversionOperation(OperationType.FP2FP, var2, var3)
      );
      return this.RF
         .createCond(var5, this.RF.createConversionOperation(var1, var2, var3), EUtil.add(this.RF.createConversionOperation(var1, var2, var3), alu.q(1L, var3)));
   }

   private void xK(fA var1, List var2, long var3, OperationType var5) {
      if (var1.getCountOfOperands() == 3) {
         var2.add(this.q.q(var1, var3, true));
      } else {
         this.Dw(var1, var2, var3, var5);
      }
   }

   private void Dw(fA var1, List var2, long var3, OperationType var5) {
      RX var6 = this.q.q(var1.RF(), 0, 2);
      if (var6.xK() == 16) {
         var2.add(this.q.q(var1, var3, true));
      } else {
         this.xK(var1, var2, var5);
      }
   }

   private void Uv(fA var1, List var2, long var3, OperationType var5) {
      RX var6 = this.q.q(var1.RF(), 0, 2);
      RX var7 = this.q.q(var1.RF(), 1, 2);
      if (var6.xK() != 16 && var7.xK() != 16) {
         this.xK(var1, var2, var5);
      } else {
         var2.add(this.q.q(var1, var3, true));
      }
   }

   private void RF(fA var1, List var2, long var3) {
      IEGeneric var5 = this.q.Of.q(var1.q(3), null);
      IEVar var6 = this.q.gO(var5.getBitsize());
      this.q.q(var1, var2, var6, var5);
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)var1.q(0));
      IEGeneric var8 = this.q.q(var1, (IInstructionOperand)var1.q(1));
      if (var8.getBitsize() != 16 && var7.getBitsize() != 16) {
         int var9 = (int)var1.q(2).getOperandValue();
         this.q
            .q(var1, var2, this.q.Of.q, this.RF.createCond(var6, this.RF.createOperation(OperationType.FLT, var7, var8), this.RF.createImm(var9 >>> 3 & 1, 1)));
         this.q
            .q(var1, var2, this.q.Of.RF, this.RF.createCond(var6, this.RF.createOperation(OperationType.FEQ, var7, var8), this.RF.createImm(var9 >>> 2 & 1, 1)));
         this.q
            .q(var1, var2, this.q.Of.xK, this.RF.createCond(var6, this.RF.createOperation(OperationType.FGE, var7, var8), this.RF.createImm(var9 >>> 1 & 1, 1)));
         this.q.q(var1, var2, this.q.Of.Dw, this.RF.createCond(var6, this.RF.createImm(0L, 1), this.RF.createImm(var9 & 1, 1)));
      } else {
         var2.add(this.q.q(var1, var3, true));
      }
   }

   private void xK(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.q(0));
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.q(1));
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)var1.q(2));
      IEGeneric var8 = this.q.Of.q(var1.q(3), null);
      this.q.q(var1, var2, var5, this.RF.createCond(var8, var6, var7));
   }

   private void Dw(fA var1, List var2, long var3) {
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)var1.q(0));
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.q(1));
      if (var6.getBitsize() != 16 && var5.getBitsize() != 16) {
         this.q.q(var1, var2, this.q.Of.q, this.RF.createOperation(OperationType.FLT, var5, var6));
         this.q.q(var1, var2, this.q.Of.RF, this.RF.createOperation(OperationType.FEQ, var5, var6));
         this.q.q(var1, var2, this.q.Of.xK, this.RF.createOperation(OperationType.FGE, var5, var6));
         this.q.q(var1, var2, this.q.Of.Dw, this.RF.createImm(0L, 1));
      } else {
         var2.add(this.q.q(var1, var3, true));
      }
   }

   private void q(fA var1, List var2, boolean var3, boolean var4) {
      CZ var5 = this.q.q(var1, var1.q(0));
      RX var6 = this.q.q(var1.RF(), 1, 2);
      IEVar var7 = this.q.gO(var6.RF(0).getBitsize());
      this.q.q(var1, var2, var7, var6.RF(0));

      for (int var8 = 1; var8 < var6.RF(); var8++) {
         this.q.q(var1, var2, var7, var3 ? EUtil.min(this.RF, var6.RF(var8), var7, var4) : EUtil.max(this.RF, var6.RF(var8), var7, var4));
      }

      this.q.q(var1, var2, var5, var7);
   }

   private void q(fA var1, List var2, OperationType var3) {
      this.RF(var1, var2, var3.getOperandCount(), true, (var2x, var3x) -> this.q(var2x, var3x[1], var3x[2], var3));
   }

   private IEGeneric q(int var1, RX var2, RX var3, OperationType var4) {
      return this.RF.createCond(this.RF.createOperation(var4, var2.RF(var1), var3.RF(var1)), this.q.oW(var2.xK()), this.q.Uv(var2.xK()));
   }

   private IEGeneric q(RX var1, int var2) {
      return this.q(var1.q(), var2, var1.xK());
   }

   private IEGeneric RF(RX var1, int var2) {
      return this.RF(var1.q(), var2, var1.xK());
   }

   private IEGeneric q(IEGeneric var1, int var2, int var3) {
      return var1.slice(2 * var2 * var3, (2 * var2 + 1) * var3);
   }

   private IEGeneric RF(IEGeneric var1, int var2, int var3) {
      return var1.slice((2 * var2 + 1) * var3, (2 * var2 + 2) * var3);
   }

   private IEGeneric q(int var1, BiFunction var2, RX var3, RX var4) {
      int var5 = var4.xK() * var4.RF();
      IECompose var6 = this.RF.createCompose(var3.q().part(var5), var4.q().part(var5));
      return (IEGeneric)var2.apply(this.q(var6, var1, var3.xK()), this.RF(var6, var1, var3.xK()));
   }

   private IEGeneric q(int var1, RX var2, boolean var3) {
      int var4 = var2.xK() * 2;
      return EUtil.add(EUtil.extend(this.q(var2, var1), var4, var3), EUtil.extend(this.RF(var2, var1), var4, var3));
   }

   private IEGeneric q(int var1, RX var2, RX var3, boolean var4) {
      return EUtil.add(var2.RF(var1), this.q(var1, var3, var4));
   }

   private void q(fA var1, List var2, OperationType var3, String var4) {
      this.q(var1, var2, var3, var4.charAt(0) == 'S', var4.charAt(var4.length() - 1) == '2' ? 2 : 1);
   }

   private void q(fA var1, List var2, OperationType var3, boolean var4, int var5) {
      int var6 = var5 == 2 ? 64 : 0;
      this.RF(
         var1,
         var2,
         2,
         true,
         (var4x, var5x) -> this.RF.createOperation(var3, var5x[1].RF(var4x), EUtil.extend(var5x[2].q(var4x, var6, var5x[2].xK()), var5x[0].xK(), var4))
      );
   }

   private void RF(fA var1, List var2, OperationType var3, String var4) {
      this.RF(var1, var2, var3, var4.charAt(0) == 'S', var4.charAt(var4.length() - 1) == '2' ? 2 : 1);
   }

   private void RF(fA var1, List var2, OperationType var3, boolean var4, int var5) {
      int var6 = var5 == 2 ? 64 : 0;
      this.RF(
         var1,
         var2,
         2,
         true,
         (var4x, var5x) -> this.RF
            .createOperation(
               var3,
               EUtil.extend(var5x[1].q(var4x, var6, var5x[1].xK()), var5x[0].xK(), var4),
               EUtil.extend(var5x[2].q(var4x, var6, var5x[2].xK()), var5x[0].xK(), var4)
            )
      );
   }

   private void xK(fA var1, List var2, OperationType var3, boolean var4, int var5) {
      int var6 = var5 == 2 ? 64 : 0;
      if (var1.getCountOfOperands() == 3) {
         this.RF(
            var1,
            var2,
            2,
            true,
            (var4x, var5x) -> this.RF.createOperation(var3, EUtil.extend(var5x[1].q(var4x, var6, var5x[1].xK()), var5x[0].xK(), var4), var5x[2].RF(var4x))
         );
      } else {
         this.RF(var1, var2, 1, true, (var2x, var3x) -> EUtil.extend(var3x[1].q(var2x, var6, var3x[1].xK()), var3x[0].xK(), var4));
      }
   }

   private void Dw(fA var1, List var2, OperationType var3, boolean var4, int var5) {
      int var6 = var5 == 2 ? 64 : 0;
      if (var1.getCountOfOperands() == 3) {
         this.RF(
            var1,
            var2,
            2,
            true,
            (var3x, var4x) -> (IEGeneric)(var3x == 0 && var6 > 0
               ? this.RF.createCompose(this.q.Uv(var6), this.RF.createOperation(var3, var4x[1].RF(var3x), var4x[2].RF(var3x)).part(var4x[1].xK() / 2))
               : (var3x >= var4x[1].RF() ? null : this.RF.createOperation(var3, var4x[1].RF(var3x), var4x[2].RF(var3x)).part(var4x[1].xK() / 2)))
         );
      } else {
         var2.add(this.q.q(var1, -1L, true));
      }
   }

   private void q(fA var1, List var2, Lb.nI var3) {
      int var4 = var3 == null ? 1 : var3.q().getOperandCount();
      if (var4 == 1) {
         this.RF(var1, var2, var4, false, (var1x, var2x) -> var3 == null ? var2x[1].RF(var1x) : var3.q(var2x[1].RF(var1x)));
      } else {
         this.RF(var1, var2, var4, false, (var2x, var3x) -> this.RF.createOperation(var3.q(), var3.q(var3x[1].RF(var2x)), var3.RF(var3x[2].RF(var2x))));
      }
   }

   private void RF(fA var1, List var2, OperationType var3) {
      RX var4 = this.q.q(var1.RF(), 0, 2);
      if (var4.xK() == 16) {
         var2.add(this.q.q(var1, -1L, true));
      } else {
         this.xK(var1, var2, var3);
      }
   }

   private void xK(fA var1, List var2, OperationType var3) {
      this.q(var1, var2, var3, false);
   }

   private void q(fA var1, List var2, OperationType var3, boolean var4) {
      if (var3 == null) {
         this.RF(var1, var2, 1, false, (var1x, var2x) -> EUtil.safeExtend(var2x[1].RF(var1x), var2x[0].xK(), var4));
      } else if (var3.getOperandCount() == 1) {
         if (var3.isConversion()) {
            this.RF(var1, var2, var3.getOperandCount(), false, (var2x, var3x) -> this.RF.createConversionOperation(var3, var3x[1].RF(var2x), var3x[0].xK()));
         } else {
            this.RF(
               var1,
               var2,
               var3.getOperandCount(),
               false,
               (var3x, var4x) -> this.RF.createOperation(var3, EUtil.extend(var4x[1].RF(var3x), var4x[0].xK(), var4))
            );
         }
      } else {
         this.RF(
            var1,
            var2,
            var3.getOperandCount(),
            false,
            (var3x, var4x) -> this.RF
               .createOperation(var3, EUtil.extend(var4x[1].RF(var3x), var4x[0].xK(), var4), EUtil.extend(var4x[2].RF(var3x), var4x[0].xK(), var4))
         );
      }
   }

   private void q(fA var1, List var2, int var3, boolean var4, BiFunction var5) {
      RX var6 = this.q.q(var1.RF(), 0, 1 + var3);
      if (var6.xK() == 16) {
         var2.add(this.q.q(var1, -1L, true));
      } else {
         this.RF(var1, var2, var3, var4, var5);
      }
   }

   private void RF(fA var1, List var2, int var3, boolean var4, BiFunction var5) {
      RX var6 = this.q.q(var1.RF(), 0, 1 + var3);
      RX[] var7 = new RX[1 + var3];
      var7[0] = var6;

      for (int var8 = 1; var8 < 1 + var3; var8++) {
         var7[var8] = this.q.q(var1.RF(), var8, 1 + var3).q(var6);
      }

      this.q(var1, var2, var6, var2x -> (IEGeneric)var5.apply(var2x, var7), var4);
   }

   private void q(fA var1, List var2, RX var3, Function var4, boolean var5) {
      if (var3.RF() == 1) {
         if (var3.Dw()) {
            this.q.q(var1, var2, new CZ(var3.q()), EUtil.safeExtend((IEGeneric)var4.apply(0), var3.xK(), false));
         } else {
            this.q.q(var1, var2, var3.RF(0), (IEGeneric)var4.apply(0));
         }
      } else if (var5) {
         ArrayList var8 = new ArrayList();

         for (int var10 = 0; var10 < var3.RF(); var10++) {
            var8.add((IEGeneric)var4.apply(var10));
         }

         ArrayList var11 = new ArrayList();
         var11.add(null);
         var8.removeAll(var11);
         if (var8.size() == 1) {
            this.q.q(var1, var2, var3.q(), (IEGeneric)var8.get(0));
         } else {
            this.q.q(var1, var2, var3.q(), this.RF.createCompose(var8));
         }
      } else {
         int var6 = var3.xK() * var3.RF();

         for (int var7 = 0; var7 < var3.RF(); var7++) {
            this.q.q(var1, var2, var3.RF(var7), (IEGeneric)var4.apply(var7));
         }

         if (var3.Dw() && var6 < var3.q().getBitsize()) {
            IEGeneric var9 = var3.q().slice(var6, var3.q().getBitsize());
            this.q.q(var1, var2, var9, this.q.Uv(var9.getBitsize()));
         }
      }
   }

   private void q(fA var1, List var2, BiFunction var3) {
      this.q(var1, var2, null, var3);
   }

   private void q(fA var1, List var2, Boolean var3, BiFunction var4) {
      byte var5 = 1;
      RX var6 = this.q.q(var1.RF(), 0, 1 + var5);
      RX var7 = this.q.q(var1.RF(), 1, 1 + var5).q(var6);
      RX[] var8 = new RX[1 + var5];
      var8[0] = var6;
      int var9 = var6.xK();
      IEGeneric var10 = q(var7.RF(0), var9, var3);

      for (int var11 = 1; var11 < var7.RF(); var11++) {
         var10 = (IEGeneric)var4.apply(q(var7.RF(var11), var9, var3), var10);
      }

      if (var6.RF() == 1) {
         if (var6.Dw()) {
            this.q.q(var1, var2, new CZ(var6.q()), EUtil.safeExtend(var10, var6.xK(), false));
         } else {
            this.q.q(var1, var2, var6.RF(0), var10);
         }
      } else {
         var2.add(this.q.q(var1, -1L, true));
      }
   }

   private static IEGeneric q(IEGeneric var0, int var1, Boolean var2) {
      return var2 == null ? var0 : EUtil.safeExtend(var0, var1, var2);
   }
}
