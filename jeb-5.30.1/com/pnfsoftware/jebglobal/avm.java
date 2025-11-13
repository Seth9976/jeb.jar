package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class avm extends ank {
   private int xK;
   private int Dw;
   private int Uv;
   private CFG oW;
   private IDFA gO;
   private IEVar nf;

   public avm(IERoutineContext var1) {
      super(var1);
   }

   @Override
   public int Dw() {
      INativeContext var1 = this.RF.getNativeContext();
      if (var1.getRoutineByName("JNI_OnLoad") == null) {
         return 0;
      } else {
         ITypeManager var2 = var1.getTypeManager();
         INativeType var3 = var2.getType("JNIEnv");
         if (var3 == null) {
            return 0;
         } else {
            var3 = var2.getType("JNINativeInterface");
            if (var3 == null) {
               return 0;
            } else {
               IStructureType var4 = (IStructureType)var3;
               IStructureTypeField var5 = var4.getFieldByName("GetVersion");
               if (var5 == null) {
                  return 0;
               } else {
                  this.xK = var5.getEndOffset() - var5.getOffset();
                  this.Dw = var5.getOffset();
                  this.Uv = var4.getField(var4.getFieldsCount() - 1).getOffset();
                  var5 = var4.getFieldByName("GetObjectClass");
                  if (var5 == null) {
                     return 0;
                  } else {
                     if (var5.getOffset() > this.Dw) {
                        this.Dw = var5.getOffset();
                     }

                     this.oW = this.RF.getCfg();
                     this.gO = this.oW.doDataFlowAnalysis();

                     for (BasicBlock var7 : this.oW) {
                        for (AddressableInstruction var9 : var7.addressableInstructions()) {
                           IEStatement var10 = (IEStatement)var9.getInstruction();
                           if (var10 instanceof IECall) {
                              IEGeneric var11 = ((IECall)var10).getCallSite();
                              IDFA var12 = this.oW.doDataFlowAnalysis();
                              IEGeneric var13 = this.RF.getConverter().normalizeBranchingExpression(var12, var7, var11, null);
                              if (var13 != null && this.q(var9.getOffset(), var10, var13)) {
                                 Object[] var10000 = new Object[]{this.nf};
                                 IWildcardType var14 = this.RF.getWildcardTypeManager().create("JNIEnv*");
                                 if (this.nf.setType(var14)) {
                                    return 1;
                                 }
                              }
                           }
                        }
                     }

                     return 0;
                  }
               }
            }
         }
      }
   }

   boolean q(long var1, IEStatement var3, IEGeneric var4) {
      if (var4 instanceof IEVar var10) {
         int var11 = var10.getId();
         Long var12 = this.gO.checkSingleDef(var1, var11);
         if (var12 == null) {
            return false;
         } else {
            IEStatement var13 = (IEStatement)this.oW.getInstruction(var12);
            if (!(var13 instanceof IEAssign)) {
               return false;
            } else {
               IEGeneric var14 = ((IEAssign)var13).getRightOperand();
               return this.q(var12, var13, var14);
            }
         }
      } else if (var4 instanceof IEMem var5) {
         IEGeneric var6 = var5.getReference();
         if (var6 instanceof IEOperation && ((IEOperation)var6).getOperationType() == OperationType.ADD) {
            IEGeneric var7 = ((IEOperation)var6).getOperand2();
            if (!(var7 instanceof IEImm)) {
               return false;
            } else {
               int var8 = (int)((IEImm)var7).getValueAsLong();
               if (var8 >= this.Dw && var8 <= this.Uv && var8 % this.xK == 0) {
                  IEGeneric var9 = ((IEOperation)var6).getOperand1();
                  return this.RF(var1, var3, var9);
               } else {
                  return false;
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   boolean RF(long var1, IEStatement var3, IEGeneric var4) {
      if (var4 instanceof IEVar var11) {
         int var12 = var11.getId();
         Long var13 = this.gO.checkSingleDef(var1, var12);
         if (var13 == null) {
            return false;
         } else {
            IEStatement var14 = (IEStatement)this.oW.getInstruction(var13);
            if (!(var14 instanceof IEAssign)) {
               return false;
            } else {
               IEGeneric var15 = ((IEAssign)var14).getRightOperand();
               return this.RF(var13, var14, var15);
            }
         }
      } else if (!(var4 instanceof IEMem var5)) {
         return false;
      } else if (!(var5.getReference() instanceof IEVar var7x)) {
         return false;
      } else {
         while (true) {
            Long var8 = this.gO.checkSingleDef(var1, var7x.getId());
            if (var8 == null) {
               return false;
            }

            if (var8 == -1L) {
               this.nf = var7x;
               return true;
            }

            IEStatement var9 = (IEStatement)this.oW.getInstruction(var8);
            if (!(var9 instanceof IEAssign)) {
               return false;
            }

            if (!(((IEAssign)var9).getRightOperand() instanceof IEVar var7x)) {
               return false;
            }

            var1 = var8;
         }
      }
   }
}
