package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.List;

@Ser
public class amc extends ane implements IEMem {
   private static final StructuredLogger q = aeg.q(amc.class);
   @SerId(1)
   private IEGeneric RF;
   @SerId(2)
   private IEGeneric xK;
   @SerId(3)
   private int Dw;

   amc(IEGeneric var1, IEGeneric var2, int var3) {
      if (var2 == null) {
         throw new NullPointerException("Null address in memory access is not allowed");
      } else if (var3 <= 0) {
         throw new IllegalArgumentException("Illegal bitsize, must be positive");
      } else {
         this.RF = var1;
         this.xK = var2;
         this.Dw = var3;
      }
   }

   amc(IEGeneric var1, int var2) {
      this(null, var1, var2);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + this.Dw;
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         amc var3 = (amc)var1;
         if (this.Dw != var3.Dw) {
            return false;
         } else {
            if (this.xK == null) {
               if (var3.xK != null) {
                  return false;
               }
            } else if (!this.xK.equalsEx(var3.xK, var2)) {
               return false;
            }

            if (this.RF == null) {
               if (var3.RF != null) {
                  return false;
               }
            } else if (!this.RF.equalsEx(var3.RF, var2)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public IEGeneric getSegment() {
      return this.RF;
   }

   @Override
   public void setSegment(IEGeneric var1) {
      this.RF = var1;
   }

   @Override
   public IEGeneric getReference() {
      return this.xK;
   }

   @Override
   public void setReference(IEGeneric var1) {
      this.xK = var1;
   }

   @Override
   public int getBitsize() {
      return this.Dw;
   }

   @Override
   public int getPriority() {
      return 80;
   }

   private void q(EDefUseInfo var1, boolean var2) {
      IERoutineContext var3 = var1.getContext();
      INativeDecompilerContext var4 = var3.getConverter().getDecompiler();
      if (var4 != null) {
         ChainedOperationResult var5 = var4.getExtensionsManager().collectCandidateMemoryDerefs(this, var1, var2);
         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            return;
         }
      }

      if (this.hasFlags(256)) {
         var1.addPotential(var3.getGlobalContext().getGlobalVariables(), var2);
      } else if (this.xK instanceof IEImm) {
         var1.addPotential(var3.getGlobalContext().getGlobalVariables(), var2);
      } else if (this.xK.isVar() && this.xK.asVar().isStackReference()) {
         int var10 = this.xK.asVar().getAddress().intValue();
         var1.add(var3.getStackVariables(var10, var10 + this.getBitsize() / 8), var2);
      } else {
         if (this.xK.isOperation(OperationType.ADD)) {
            IEGeneric var8 = this.xK.asOperation().getOperand1();
            IEGeneric var6 = this.xK.asOperation().getOperand2();
            if (var8.isVar() && var8.asVar().isStackReference() && var6.isImm()) {
               int var7 = (int)(var8.asVar().getAddress() + var6.asImm().getValueAsLong());
               var1.add(var3.getStackVariables(var7, var7 + this.getBitsize() / 8), var2);
               return;
            }
         }

         EVisitResults var9 = new EVisitResults();
         if (this.xK.visitDepthPost(new amd(this), null, var9)) {
            if (var9.getValue() == 1) {
               var1.addPotential(var3.getGlobalContext().getGlobalVariables(), var2);
               return;
            }

            if (var9.getValue() == 2) {
               var1.addPotential(var3.getStackVariables(), var2);
               return;
            }
         }

         var1.addPotential(var3.getMemoryVariables(), var2);
      }
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      if (this.RF != null) {
         this.RF.getUsed(var1);
      }

      this.xK.getUsed(var1);
      if (var1.shouldCollectPotentials()) {
         this.q(var1, true);
      }
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      if (this.RF != null) {
         this.RF.getUsed(var1);
      }

      this.xK.getUsed(var1);
      if (var1.shouldCollectPotentials()) {
         this.q(var1, false);
      }
   }

   @Override
   public void getExplicitlyUsed(EDefUseInfo var1) {
      if (this.RF != null) {
         this.RF.getExplicitlyUsed(var1);
      }

      this.xK.getExplicitlyUsed(var1);
   }

   @Override
   public boolean accessesMemory() {
      return true;
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;
      if (this.RF != null) {
         if (this.RF == var1) {
            RF(this.RF, var2);
            this.RF = var2.duplicate();
            var3++;
         } else {
            var3 += this.RF.replaceVar(var1, var2);
         }
      }

      if (this.xK == var1) {
         RF(this.xK, var2);
         this.xK = var2.duplicate();
         var3++;
      } else {
         var3 += this.xK.replaceVar(var1, var2);
      }

      return var3;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      if (this.RF != null) {
         var1.add(this.RF);
      }

      var1.add(this.xK);
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      q(var1, var2);
      if (this.RF == var1) {
         RF(this.RF, var2);
         this.RF = var2;
         return true;
      } else if (this.xK == var1) {
         RF(this.xK, var2);
         this.xK = var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      IWildcardTypeManager var2 = var1.getWildcardTypeManager();
      if (this.RF != null) {
         this.RF.updateTypes(var1);
      }

      this.xK.updateTypes(var1);
      ano var3 = new ano(this.xK, null);
      if (var3.q()) {
         List var4 = var3.Dw();
         if (var4.size() == 1) {
            IEGeneric var5 = (IEGeneric)var4.get(0);
            if (var5.getType() == null || var5.getType().isUndefined()) {
               var5.setType(var2.createPointer(0), var1);
            }
         }
      }

      IWildcardType var9 = this.getType();
      IWildcardType var12 = this.xK.getType();
      if (var9 != null && !var9.isUndefined()) {
         if (var9.isDefined()) {
            if (var12 == null || var12.isUndefined()) {
               INativeType var18 = var9.getNativeType();
               IReferenceType var22 = var18.getReference();
               this.xK.setType(var2.create(var22), var1);
            } else if (var12.isPartiallyDefined() && !(this.xK instanceof IEVar)) {
               INativeType var17 = var9.getNativeType();
               IReferenceType var21 = var17.getReference();
               this.xK.setType(var2.create(var21), var1);
            }
         } else if (var12 != null && var12.isDefined() && TypeUtil.isPointerToPointer(var12.getNativeType())) {
            IReferenceType var19 = (IReferenceType)TypeUtil.getNonAlias(var12.getNativeType(), IReferenceType.class);
            INativeType var23 = var19.getPointedType();
            this.setType(var2.create(var23), var1);
         }
      } else if (var12 != null && var12.isDefined()) {
         INativeType var16 = TypeUtil.getNonAlias(var12.getNativeType());
         if (var16 instanceof IReferenceType) {
            INativeType var7 = ((IReferenceType)var16).getPointedType();
            this.setType(var2.create(var7), var1);
         } else {
            var1.recordConflict("Dereference IRE in EMem is not a reference type!", this.xK, var12, null);
         }
      } else if (var12 == null || var12.isUndefined() || var12.getNativeType() == null && var12.getGroup() != IWildcardType.Group.POINTER) {
         IWildcardType var6 = var2.createPointer(this.getBitsize());
         this.xK.setType(var6, var1);
      }

      try {
         if (!EUtil.hasTypeInfo(this)) {
            ant var20 = new ant(var1.getContext(), this);
            IWildcardType var24 = var20.nf();
            if (var24 != null) {
               Object[] var10000 = new Object[]{var24};
               this.setType(var24, var1);
            }
         }
      } catch (Exception var8) {
      }

      var12 = this.xK.getType();
      if (var12 != null && var12.isWildcardPointer() && var12.getPointedBitsize() == 0) {
         this.xK.setType(var12.updatePointedBitsize(this.getBitsize()), var1);
      }

      var12 = this.xK.getType();
      var9 = this.getType();
      if (var12 != null && var9 != null && !var12.isResolved() && var12.getPointedGroup() == null && var9.getGroup() != null) {
         this.xK.setType(var12.updatePointedGroup(var9.getGroup()), var1);
      }

      var12 = this.xK.getType();
      var9 = this.getType();
      if (var12 != null && !var12.isResolved() && var12.getPointedGroup() != null && (var9 == null || var9.getGroup() == null)) {
         if (var9 == null) {
            var9 = var2.createWithEffectiveBitsize(this.getBitsize());
         }

         this.setType(var9.updateGroup(var12.getPointedGroup()), var1);
      }
   }

   public amc q() {
      return (amc)this.q(new amc(this.RF == null ? null : this.RF.duplicate(), this.xK.duplicate(), this.Dw));
   }

   @Override
   public IEImm evaluate(EState var1) {
      long var2 = 0L;
      if (this.RF != null && var1.hasSegmentBases()) {
         IEImm var4 = this.RF.evaluate(var1);
         if (var4 == null) {
            return null;
         }

         int var5 = (int)var4.getValueAsUnsignedLong();
         var2 = var1.getSegmentBase(var5);
      }

      IEImm var10 = this.xK.evaluate(var1);
      if (var10 == null) {
         return null;
      } else {
         var2 += var10.getValueAsAddress();
         long var11 = var1.generateAddressFromPointer(var2);
         byte[] var7 = new byte[this.Dw / 8];
         int var8 = var1.readMemory2(var11, var7);
         if (var8 != 0) {
            if (var8 == -2) {
               return null;
            } else {
               throw new EvaluationException(Strings.ff("Cannot read IR-state memory at 0x%X", var11));
            }
         } else {
            if (!var1.isBigEndian()) {
               EndianUtil.swap(var7);
            }

            return alu.q(var7, this.Dw);
         }
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      ant var3 = new ant(var1, this);
      IEVar var4 = var3.q(var2);
      if (var4 != null) {
         return var4.generateC(var1, var2);
      } else {
         boolean var5 = this.xK.getType() == null || !this.xK.getType().isPointer() || this.xK.getType().getPointedBitsize() != this.getBitsize();
         ICElementFactory var6 = var2.getElementFactory();
         Object var7;
         if (this.xK instanceof amf) {
            var7 = (ICExpression)((amf)this.xK).q(var1, var2, false);
         } else {
            var7 = (ICExpression)this.xK.generateC(var1, var2);
         }

         if (var5) {
            IWildcardTypeManager var8 = var1.getGlobalContext().getWildcardTypeManager();
            ICTypeFactory var9 = var2.getTypeFactory();
            ICType var10 = null;
            if (this.xK.getType() != null && this.xK.getType().isPointer() && this.xK.getType().isDefined()) {
               IReferenceType var11 = (IReferenceType)TypeUtil.getNonAlias(this.xK.getType().getNativeType());
               if (var11.getPointedType().getSize() * 8 == this.getBitsize()) {
                  var10 = var9.create(this.xK.getType());
               }
            }

            if (var10 == null) {
               IWildcardType var12 = this.safelyType(var8).resolveA();
               IWildcardType var13;
               if (var12.isResolved()) {
                  var13 = var8.create(var12.getNativeType().getReference());
               } else {
                  var13 = var8.createPointer(this.getBitsize());
               }

               var10 = var9.create(var13);
            }

            var7 = var6.createCast(var10, (ICExpression)var7);
         }

         return var6.createOperation(COperatorType.PTR, (ICExpression)var7);
      }
   }

   @Override
   public void q(and var1) {
      if (var1.q()) {
         var1.append("m");
         var1.append(this.Dw + "");
      }

      if (this.RF != null && var1.RF()) {
         var1.append("<");
         var1.q(this.RF);
         var1.append(">");
      }

      var1.bracket();
      var1.q(this.xK);
      var1.bracketClose();
      var1.RF(this);
   }
}
