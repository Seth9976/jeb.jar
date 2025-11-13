package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;

public class avl extends ank {
   public avl(IERoutineContext var1) {
      super(var1);
   }

   @Override
   protected int Dw() {
      INativeContext var1 = this.RF.getNativeContext();
      if (var1.getRoutineByName("JNI_OnLoad") == null) {
         return 0;
      } else {
         ITypeManager var2 = var1.getTypeManager();
         INativeType var3 = var2.getType("JavaVM");
         if (var3 == null) {
            return 0;
         } else {
            INativeType var4 = var2.getType("JNIEnv");
            if (var4 == null) {
               return 0;
            } else {
               for (IEStatement var6 : this.RF.getCfg().instructions()) {
                  if (var6 instanceof IECall var7 && var7.getCountOfArguments() == 3) {
                     IEGeneric var8 = var7.getArgument(2);
                     if (this.q(var8) && this.RF(var7.getArgument(1)) && this.xK(var7.getArgument(0))) {
                        IEGeneric var9 = var7.getCallSite();
                        if (this.Dw(var9)) {
                           IWildcardTypeManager var10 = this.RF.getWildcardTypeManager();
                           IEGeneric var11 = var7.getArgument(1);
                           IReferenceType var12 = var4.getReference();
                           if (var11.setType(var10.create(var12.getReference()))) {
                              IEVar var13 = this.RF.getStackVariable(var11.asVar().getAddress().intValue());
                              if (var13 != null) {
                                 var13.setType(var10.create(var12));
                              }

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

   private boolean q(IEGeneric var1) {
      if (var1 instanceof IEImm var2 && var2.canReadAsLong()) {
         long var3 = var2.getValueAsLong();
         if (var3 >= 65537L && var3 <= 65542L) {
            return true;
         }
      }

      return false;
   }

   private boolean RF(IEGeneric var1) {
      return var1 instanceof IEVar var2 && var2.isStackReference() && (var2.getType() == null || !var2.getType().isResolved());
   }

   private boolean xK(IEGeneric var1) {
      IWildcardType var2 = var1.getType();
      if (var2 != null && var2.isDefined()) {
         INativeType var3 = var2.getNativeType();
         var3 = TypeUtil.getNonAlias(var3);
         if (var3.getSignature().equals("JavaVM*")) {
            return true;
         }
      }

      return false;
   }

   private boolean Dw(IEGeneric var1) {
      if (var1 instanceof IEMem) {
         IEGeneric var2 = ((IEMem)var1).getReference();
         if (var2.isOperation(OperationType.ADD)) {
            IEGeneric var3 = var2.asOperation().getOperand2();
            if (var3 instanceof IEImm && ((IEImm)var3).canReadAsLong()) {
               long var4 = ((IEImm)var3).getValueAsLong();
               INativeType var6 = this.RF.getNativeContext().getTypeManager().getType("JNIInvokeInterface");
               if (var6 == null) {
                  return false;
               }

               IStructureTypeField var7 = ((IStructureType)var6).getFieldByName("GetEnv");
               if (var7 == null) {
                  return false;
               }

               return var4 == var7.getOffset();
            }
         }
      }

      return false;
   }
}
