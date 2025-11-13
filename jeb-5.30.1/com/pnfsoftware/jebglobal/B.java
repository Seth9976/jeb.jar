package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import java.math.BigInteger;
import java.util.List;

public class B {
   uq q;
   IERoutineContext RF;

   public B(uq var1) {
      this.q = var1;
   }

   public void q(fA var1, List var2, long var3) {
      String var5 = var1.getMnemonic();
      if (var5.contains(".")) {
         var5 = var5.substring(0, var5.indexOf("."));
      }

      switch (var5) {
         case "LDNP":
         case "LDP":
         case "LDR":
         case "LDUR":
         case "VLDR":
            this.q.o.q(var1, PG.q(var1), var2, var3);
            break;
         case "STNP":
         case "STP":
         case "STR":
         case "STUR":
         case "VSTR":
            this.q.o.RF(var1, PG.q(var1), var2, var3);
            break;
         case "VMOV":
            this.RF(var1, var2, var3);
            break;
         case "VADD":
            this.q(var1, var2, var3, OperationType.FADD);
            break;
         case "VSUB":
            this.q(var1, var2, var3, OperationType.FADD);
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

   private void RF(fA var1, List var2, long var3) {
      CW var5 = var1.RF()[0];
      if ("F16".equals(var1.Dw().oW())) {
         var2.add(this.q.q(var1, var3, true));
      } else {
         CZ var6 = this.q.q(var1, var1.RF()[0]);
         if (var1.RF().length == 3) {
            IEGeneric var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[2], var3);
            if (RegisterUtil.getRegisterGroup(var5.getOperandValue()) == 0) {
               this.q.q(var1, var2, var6, var7.slice(0, 32));
               CZ var8 = this.q.q(var1, var1.RF()[1]);
               this.q.q(var1, var2, var8, var7.slice(32, 64));
            } else {
               IEGeneric var10 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
               this.q.q(var1, var2, var6, EUtil.compose(this.RF, var10, var7));
            }
         } else if (var1.RF().length == 4) {
            var2.add(this.q.q(var1, var3, true));
         } else {
            int var9 = var5.getOperandBitsize();
            if (var5 instanceof BY) {
               var9 = ((BY)var5).RF().getOperandBitsize();
            }

            IEGeneric var11 = this.q(var1, var1.RF()[1], var9, var3);
            if (var11 != null) {
               this.q.q(var1, var2, var6, var11);
            } else {
               var2.add(this.q.q(var1, var3, true));
            }
         }
      }
   }

   private void q(fA var1, List var2, long var3, OperationType var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      IEGeneric var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[2], var3);
      if (var1.Dw().oW() == null) {
         this.q.q(var1, var2, var6, this.RF.createOperation(var5, var7, var8));
      } else {
         String var9 = var1.Dw().oW();
         switch (var9) {
            case "F32":
            case "F64":
               this.q.q(var1, var2, var6, this.RF.createOperation(var5, var7, var8));
               break;
            default:
               var2.add(this.q.q(var1, var3, true));
         }
      }
   }

   private IEGeneric q(fA var1, CW var2, int var3, long var4) {
      if (var1.Dw().oW() != null) {
         switch (var2.getOperandType()) {
            case 0:
               int var15 = k.q(var1.Dw());
               IEGeneric var17 = this.q.q(var1, var2, var3, var4);
               if (var15 != var17.getBitsize()) {
                  var17 = var17.part(var15);
               }

               return var17;
            case 1:
            case 4097:
               long var6 = var2.getOperandValue(var4);
               BigInteger var8 = BigInteger.valueOf(var6);
               if (var3 == 0) {
                  var3 = var2.getOperandBitsize();
               }

               String var14 = var1.Dw().oW();
               switch (var14) {
                  case "F64":
                     if (var3 == 64) {
                        return this.q.q(var1, (IInstructionOperand)var2, var4);
                     }

                     throw new UnsupportedConversionException(Strings.f("Can not process operand imm %s with size %d", var1.Dw().oW(), var3));
                  case "F32":
                     if (var3 == 32) {
                        return this.q.q(var1, (IInstructionOperand)var2, var4);
                     }

                     if (var3 != 64 && var3 != 128) {
                        throw new UnsupportedConversionException(Strings.f("Can not process operand imm %s with size %d", var1.Dw().oW(), var3));
                     }

                     BigInteger var21 = BigInteger.ZERO;
                     var8 = var8.shiftLeft(16);

                     for (byte var24 = 0; var24 < var3; var24 += 32) {
                        var21 = var21.shiftLeft(32).add(var8);
                     }

                     return this.RF.createImm(var21, var3 == 0 ? var2.getOperandBitsize() : var3);
                  case "I8":
                     if (var3 != 64 && var3 != 128) {
                        throw new UnsupportedConversionException(Strings.f("Can not process operand imm %s with size %d", var1.Dw().oW(), var3));
                     }

                     BigInteger var20 = BigInteger.ZERO;

                     for (byte var23 = 0; var23 < var3; var23 += 8) {
                        var20 = var20.shiftLeft(8).add(var8);
                     }

                     return this.RF.createImm(var20, var3 == 0 ? var2.getOperandBitsize() : var3);
                  case "I16":
                     if (var3 != 64 && var3 != 128) {
                        throw new UnsupportedConversionException(Strings.f("Can not process operand imm %s with size %d", var1.Dw().oW(), var3));
                     }

                     BigInteger var19 = BigInteger.ZERO;

                     for (byte var22 = 0; var22 < var3; var22 += 8) {
                        if (var22 % 16 == 8) {
                           var19 = var19.shiftLeft(8).add(var8);
                        } else {
                           var19 = var19.shiftLeft(8);
                        }
                     }

                     return this.RF.createImm(var19, var3 == 0 ? var2.getOperandBitsize() : var3);
                  case "I32":
                     if (var3 != 64 && var3 != 128) {
                        throw new UnsupportedConversionException(Strings.f("Can not process operand imm %s with size %d", var1.Dw().oW(), var3));
                     }

                     BigInteger var18 = BigInteger.ZERO;

                     for (byte var12 = 0; var12 < var3; var12 += 8) {
                        if (var12 % 32 == 24) {
                           var18 = var18.shiftLeft(8).add(var8);
                        } else {
                           var18 = var18.shiftLeft(8);
                        }
                     }

                     return this.RF.createImm(var18, var3 == 0 ? var2.getOperandBitsize() : var3);
                  case "I64":
                     if (var3 == 64) {
                        return this.q.q(var1, (IInstructionOperand)var2, var4);
                     }

                     if (var3 == 128) {
                        BigInteger var11 = var8.shiftLeft(64).add(var8);
                        return this.RF.createImm(var11, var3 == 0 ? var2.getOperandBitsize() : var3);
                     }

                     throw new UnsupportedConversionException(Strings.f("Can not process operand imm %s with size %d", var1.Dw().oW(), var3));
                  default:
                     if (!Strings.isBlank(var1.Dw().oW())) {
                        throw new UnsupportedConversionException(Strings.f("Can not process operand imm %s with size %d", var1.Dw().oW(), var3));
                     }

                     return this.q.q(var1, var2, var3, var4);
               }
            case 7:
               if (var2 instanceof BY) {
                  IEGeneric var9 = this.q.q(var1, var2, var3, var4);
                  boolean var10 = k.Dw(var1.Dw());
                  if (var3 != 0) {
                     return var10 ? var9.signExtend(var3) : var9.zeroExtend(var3);
                  }

                  return var9;
               }
         }
      }

      return this.q.q(var1, var2, var3, var4);
   }
}
