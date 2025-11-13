package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.util.base.Couple;

public class zp extends AbstractEOptimizer {
   int pC;

   public zp() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   public int perform() {
      int var1 = 0;
      this.pC = this.ectx.getStackPointerId();

      for (BasicBlock var3 : this.cfg) {
         int var4 = 0;

         for (IEStatement var6 : var3) {
            if (var6.isCall()) {
               IECall var7 = var6.asCall();

               label90:
               for (IEGeneric var9 : var7.getArguments()) {
                  IEGeneric var10 = var9;
                  if (var9 instanceof IEOperation && ((IEOperation)var9).isCustomOperation("ToMC7P")) {
                     var10 = var9;
                     var9 = var9.asOperation().getOperand1();
                  }

                  Integer var11 = this.A(var9);
                  if (var11 != null) {
                     byte var12 = 0;
                     int var13 = 0;
                     int var14 = 0;
                     int var15 = -1;
                     int var16 = -1;

                     for (int var17 = var4 - 1; var17 >= 0; var17--) {
                        IEStatement var18 = (IEStatement)var3.get(var17);
                        if (var18 instanceof IEAssign) {
                           IEAssign var19 = var18.asAssign();
                           Couple var20 = this.pC(var19.getDstOperand());
                           if (var20 != null) {
                              int var21 = (Integer)var20.getFirst();
                              int var22 = (Integer)var20.getSecond();
                              if (var22 == var11 && var21 == 16) {
                                 if (!(var19.getSrcOperand() instanceof IEImm)) {
                                    continue label90;
                                 }

                                 var13 = (int)var19.getSrcOperand().asImm().getValueAsLong();
                                 var15 = var17;
                                 var12 |= 1;
                              } else if (var22 == var11 + 2 && var21 == 32) {
                                 if (!(var19.getSrcOperand() instanceof IEImm)) {
                                    continue label90;
                                 }

                                 var14 = (int)var19.getSrcOperand().asImm().getValueAsLong();
                                 var16 = var17;
                                 var12 |= 2;
                              }

                              if (var12 == 3) {
                                 break;
                              }
                           }
                        }
                     }

                     if (var12 == 3) {
                        Object var23 = this.pC(var13, var14);
                        if (var23 != null) {
                           if (var9 != var10) {
                              FunctionOptype var24 = this.ectx.getGlobalContext().createFunctionType("ToMC7PPTR", 16, 1, 32);
                              var23 = this.ectx.createOperation(var24, (IEGeneric)var23);
                           }

                           if (var7.replaceSubExpression(var10, (IEGeneric)var23)) {
                              var3.set(var15, this.ectx.createNop((IEStatement)var3.get(var15)));
                              var3.set(var16, this.ectx.createNop((IEStatement)var3.get(var16)));
                              var1++;
                           }
                        }
                     }
                  }
               }
            }

            var4++;
         }
      }

      return this.postPerform(var1);
   }

   Couple pC(IEGeneric var1) {
      if (!(var1 instanceof IEMem)) {
         return null;
      } else {
         IEMem var2 = var1.asMem();
         Integer var3 = this.A(var2.getReference());
         return var3 == null ? null : new Couple(var2.getBitsize(), var3);
      }
   }

   Integer A(IEGeneric var1) {
      if (var1.isVar(this.pC)) {
         return 0;
      } else {
         if (var1.isOperation(OperationType.ADD)) {
            IEOperation var2 = var1.asOperation();
            if (var2.getOperand1().isVar(this.pC) && var2.getOperand2().isImm()) {
               return (int)var1.asOperation().getOperand2().asImm().getValueAsLong();
            }
         }

         return null;
      }
   }

   IEGeneric pC(int var1, int var2) {
      int var4 = var2 >> 3 & 65535;
      int var5 = var2 & 7;
      S7.AreaType var6 = S7.AreaType.fromId(var2 >>> 24);
      Object var3;
      if (var6 == S7.AreaType.V) {
         var3 = this.ectx.getStackPointer();
      } else if (var6 == S7.AreaType.I) {
         var3 = this.ectx.getVariableById(256);
      } else if (var6 == S7.AreaType.Q) {
         var3 = this.ectx.getVariableById(320);
      } else if (var6 == S7.AreaType.M) {
         var3 = this.ectx.getVariableById(384);
      } else {
         if (var6 != S7.AreaType.DB && var6 != S7.AreaType.DI) {
            return null;
         }

         if (var1 == 0) {
            return null;
         }

         FunctionOptype var7 = this.ectx.getGlobalContext().createFunctionType("GetDBAddress", 0, 1, 32);
         var3 = this.ectx.createOperation(var7, EUtil.imm(var1, 32));
      }

      Object var9;
      if (var4 == 0) {
         var9 = var3;
      } else {
         var9 = EUtil.add((IEGeneric)var3, EUtil.imm(var4, ((IEGeneric)var3).getBitsize()));
      }

      if (var5 != 0) {
         FunctionOptype var8 = this.ectx.getGlobalContext().createFunctionType("BitAddr", 0, 2, 32);
         var9 = this.ectx.createOperation(var8, (IEGeneric)var9, EUtil.imm(var5, 32));
      }

      return (IEGeneric)var9;
   }
}
