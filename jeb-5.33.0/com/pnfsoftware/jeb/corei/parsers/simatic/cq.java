package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.simatic.S7;

public class cq extends AbstractEExpressionOptimizer {
   public cq() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (!(var1 instanceof IEOperation)) {
         return null;
      } else {
         IEOperation var2 = var1.asOperation();
         if (!var2.isCustomOperation()) {
            return null;
         } else {
            FunctionOptype var3 = var2.getCustomOperationType();
            String var4 = var3.getName();
            switch (var4) {
               case "ToNP":
                  return this.pC(var2);
               case "ExtractBase":
                  IEGeneric var10 = var2.getOperand1();
                  if (var10 instanceof IEImm) {
                     int var12 = (int)(var10.asImm().getValueAsLong() >>> 24 & 255L);
                     if (var12 == S7.AreaType.V.getId()) {
                        IEVar var8 = this.ectx.getVariableByName("rDI");
                        return AbstractEExpressionOptimizer.EOR.create(var8);
                     }
                  }
                  break;
               case "ExtractOff":
                  IEGeneric var9 = var2.getOperand1();
                  if (var9 instanceof IEImm) {
                     int var11 = (int)((var9.asImm().getValueAsLong() & 16777215L) >> 3);
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.imm(var11, var2.getBitsize()));
                  }
                  break;
               case "ExtractBit":
                  IEGeneric var6 = var2.getOperand1();
                  if (var6 instanceof IEImm) {
                     int var7 = (int)(var6.asImm().getValueAsLong() & 7L);
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.imm(var7, var2.getBitsize()));
                  }
                  break;
               case "GetDBAddress":
                  return this.pC(var2, S7.BlockType.DB);
               case "GetFBAddress":
                  return this.pC(var2, S7.BlockType.FB);
               case "GetFCAddress":
                  return this.pC(var2, S7.BlockType.FC);
            }

            return null;
         }
      }
   }

   private AbstractEExpressionOptimizer.EOR pC(IEOperation var1, S7.BlockType var2) {
      IEGeneric var3 = var1.getOperand1();
      if (var3 instanceof IEImm) {
         int var4 = (int)var3.asImm().getValueAsLong();
         if (this.ectx.getNativeContext() instanceof INativeCodeUnit) {
            INativeCodeUnit var5 = (INativeCodeUnit)this.ectx.getNativeContext();
            gb var6 = (gb)var5.getCodeObjectContainer();
            long var7;
            if (var2.isLogicBlock()) {
               var7 = var6.getAddressOfCode(var2, var4);
               if (var7 == -1L) {
                  String var9 = gJ.pC().pC(var2, var4);
                  if (var9 != null) {
                     IEVar var10 = this.ectx.getGlobalContext().createVirtualRegister(var9, 32);
                     var10.setFlags(16);
                     return AbstractEExpressionOptimizer.EOR.create(var10, true);
                  }
               }
            } else {
               if (!var2.isDataBlock()) {
                  return null;
               }

               var7 = var6.getAddressOfData(S7.BlockType.DB, var4);
            }

            if (var7 != -1L) {
               return AbstractEExpressionOptimizer.EOR.create(EUtil.imm(var7, var1.getBitsize()));
            }
         }
      }

      return null;
   }

   private AbstractEExpressionOptimizer.EOR pC(IEOperation var1) {
      IEGeneric var4 = var1.getOperand1();
      IEGeneric var2;
      IEGeneric var3;
      if (!(var4 instanceof IEVar) && !(var4 instanceof IEMem)) {
         if (!var4.isOperation(OperationType.ADD)) {
            return null;
         }

         IEOperation var5 = var4.asOperation();
         var2 = var5.getOperand1();
         var3 = var5.getOperand2();
         if (!this.pC(var2)) {
            if (!this.pC(var3)) {
               return null;
            }

            var2 = var3;
            var3 = var5.getOperand1();
         }
      } else {
         var2 = var4;
         if (!this.pC(var4)) {
            return null;
         }

         var3 = null;
      }

      Object var12 = null;
      if (var3 instanceof IEImm) {
         IEImm var6 = var3.asImm();
         long var7 = var6.asImm().getValueAsLong();
         if ((var7 & 7L) == 0L) {
            int var9 = (int)(var7 / 8L);
            var12 = EUtil.imm(var9, var2.getBitsize());
         }

         if (var12 == null) {
            return null;
         }
      } else if (var3 instanceof IEOperation && var3.isOperation(OperationType.MUL)) {
         IEOperation var13 = var3.asOperation();
         if (var13.getOperand2() instanceof IEImm) {
            IEImm var15 = var13.getOperand2().asImm();
            long var8 = var15.asImm().getValueAsLong();
            if ((var8 & 7L) == 0L) {
               int var10 = (int)(var8 / 8L);
               var12 = EUtil.mul(var13.getOperand1(), EUtil.imm(var10, var2.getBitsize()));
            }
         }

         if (var12 == null) {
            return null;
         }
      }

      IWildcardType var14 = var2.getType();
      String var16 = var14.getNativeType().getSignature();
      String var17 = var16.substring(5);
      if (!var17.startsWith("MC7P")) {
         var17 = var17.replace('`', '[').replace('\'', ']');
      }

      IReferenceType var18;
      if (var17.equals("UNKNOWN")) {
         var18 = this.ectx.getNativeContext().getTypeManager().getVoidReference();
      } else {
         INativeType var19 = this.ectx.getNativeContext().getTypeManager().getType(var17);
         if (var19 == null) {
            return null;
         }

         var18 = var19.getReference();
      }

      IWildcardType var20 = this.ectx.getWildcardTypeManager().create(var18);
      if (var3 == null) {
         return null;
      } else {
         IEOperation var11 = this.ectx.createOperation(var1.getCustomOperationType(), var2);
         var11.setType(var20);
         if (var12 != null) {
            var11 = this.ectx.createOperation(OperationType.ADD, var11, (IEGeneric)var12);
         }

         return AbstractEExpressionOptimizer.EOR.create(var11);
      }
   }

   private boolean pC(IEGeneric var1) {
      IWildcardType var2 = var1.getType();
      return var2 != null && var2.isResolved() && var2.getNativeType().getSignature().startsWith("MC7P_");
   }
}
