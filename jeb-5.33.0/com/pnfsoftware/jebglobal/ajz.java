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
public class ajz extends ala implements IEMem {
   private static final StructuredLogger pC = aco.pC(ajz.class);
   @SerId(1)
   private IEGeneric A;
   @SerId(2)
   private IEGeneric kS;
   @SerId(3)
   private int wS;

   ajz(IEGeneric var1, IEGeneric var2, int var3) {
      if (var2 == null) {
         throw new NullPointerException("Null address in memory access is not allowed");
      } else if (var3 <= 0) {
         throw new IllegalArgumentException("Illegal bitsize, must be positive");
      } else {
         this.A = var1;
         this.kS = var2;
         this.wS = var3;
      }
   }

   ajz(IEGeneric var1, int var2) {
      this(null, var1, var2);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + this.wS;
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         ajz var3 = (ajz)var1;
         if (this.wS != var3.wS) {
            return false;
         } else {
            if (this.kS == null) {
               if (var3.kS != null) {
                  return false;
               }
            } else if (!this.kS.equalsEx(var3.kS, var2)) {
               return false;
            }

            if (this.A == null) {
               if (var3.A != null) {
                  return false;
               }
            } else if (!this.A.equalsEx(var3.A, var2)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public IEGeneric getSegment() {
      return this.A;
   }

   @Override
   public void setSegment(IEGeneric var1) {
      this.A = var1;
   }

   @Override
   public IEGeneric getReference() {
      return this.kS;
   }

   @Override
   public void setReference(IEGeneric var1) {
      this.kS = var1;
   }

   @Override
   public int getBitsize() {
      return this.wS;
   }

   @Override
   public int getPriority() {
      return 80;
   }

   private void pC(EDefUseInfo var1, boolean var2) {
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
      } else if (this.kS instanceof IEImm) {
         var1.addPotential(var3.getGlobalContext().getGlobalVariables(), var2);
      } else if (this.kS.isVar() && this.kS.asVar().isStackReference()) {
         int var10 = this.kS.asVar().getAddress().intValue();
         var1.add(var3.getStackVariables(var10, var10 + this.getBitsize() / 8), var2);
      } else {
         if (this.kS.isOperation(OperationType.ADD)) {
            IEGeneric var8 = this.kS.asOperation().getOperand1();
            IEGeneric var6 = this.kS.asOperation().getOperand2();
            if (var8.isVar() && var8.asVar().isStackReference() && var6.isImm()) {
               int var7 = (int)(var8.asVar().getAddress() + var6.asImm().getValueAsLong());
               var1.add(var3.getStackVariables(var7, var7 + this.getBitsize() / 8), var2);
               return;
            }
         }

         EVisitResults var9 = new EVisitResults();
         if (this.kS.visitDepthPost(new aka(this), null, var9)) {
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
      if (this.A != null) {
         this.A.getUsed(var1);
      }

      this.kS.getUsed(var1);
      if (var1.shouldCollectPotentials()) {
         this.pC(var1, true);
      }
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      if (this.A != null) {
         this.A.getUsed(var1);
      }

      this.kS.getUsed(var1);
      if (var1.shouldCollectPotentials()) {
         this.pC(var1, false);
      }
   }

   @Override
   public void getExplicitlyUsed(EDefUseInfo var1) {
      if (this.A != null) {
         this.A.getExplicitlyUsed(var1);
      }

      this.kS.getExplicitlyUsed(var1);
   }

   @Override
   public boolean accessesMemory() {
      return true;
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;
      if (this.A != null) {
         if (this.A == var1) {
            A(this.A, var2);
            this.A = var2.duplicate();
            var3++;
         } else {
            var3 += this.A.replaceVar(var1, var2);
         }
      }

      if (this.kS == var1) {
         A(this.kS, var2);
         this.kS = var2.duplicate();
         var3++;
      } else {
         var3 += this.kS.replaceVar(var1, var2);
      }

      return var3;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      if (this.A != null) {
         var1.add(this.A);
      }

      var1.add(this.kS);
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      pC(var1, var2);
      if (this.A == var1) {
         A(this.A, var2);
         this.A = var2;
         return true;
      } else if (this.kS == var1) {
         A(this.kS, var2);
         this.kS = var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      IWildcardTypeManager var2 = var1.getWildcardTypeManager();
      if (this.A != null) {
         this.A.updateTypes(var1);
      }

      this.kS.updateTypes(var1);
      alj var3 = new alj(this.kS, null);
      if (var3.pC()) {
         List var4 = var3.wS();
         if (var4.size() == 1) {
            IEGeneric var5 = (IEGeneric)var4.get(0);
            if (var5.getType() == null || var5.getType().isUndefined()) {
               var5.setType(var2.createPointer(0), var1);
            }
         }
      }

      IWildcardType var9 = this.getType();
      IWildcardType var12 = this.kS.getType();
      if (var9 != null && !var9.isUndefined()) {
         if (var9.isDefined()) {
            if (var12 == null || var12.isUndefined()) {
               INativeType var18 = var9.getNativeType();
               IReferenceType var22 = var18.getReference();
               this.kS.setType(var2.create(var22), var1);
            } else if (var12.isPartiallyDefined() && !(this.kS instanceof IEVar)) {
               INativeType var17 = var9.getNativeType();
               IReferenceType var21 = var17.getReference();
               this.kS.setType(var2.create(var21), var1);
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
            var1.recordConflict("Dereference IRE in EMem is not a reference type!", this.kS, var12, null);
         }
      } else if (var12 == null || var12.isUndefined() || var12.getNativeType() == null && var12.getGroup() != IWildcardType.Group.POINTER) {
         IWildcardType var6 = var2.createPointer(this.getBitsize());
         this.kS.setType(var6, var1);
      }

      try {
         if (!EUtil.hasTypeInfo(this)) {
            alo var20 = new alo(var1.getContext(), this);
            IWildcardType var24 = var20.E();
            if (var24 != null) {
               Object[] var10000 = new Object[]{var24};
               this.setType(var24, var1);
            }
         }
      } catch (Exception var8) {
      }

      var12 = this.kS.getType();
      if (var12 != null && var12.isWildcardPointer() && var12.getPointedBitsize() == 0) {
         this.kS.setType(var12.updatePointedBitsize(this.getBitsize()), var1);
      }

      var12 = this.kS.getType();
      var9 = this.getType();
      if (var12 != null && var9 != null && !var12.isResolved() && var12.getPointedGroup() == null && var9.getGroup() != null) {
         this.kS.setType(var12.updatePointedGroup(var9.getGroup()), var1);
      }

      var12 = this.kS.getType();
      var9 = this.getType();
      if (var12 != null && !var12.isResolved() && var12.getPointedGroup() != null && (var9 == null || var9.getGroup() == null)) {
         if (var9 == null) {
            var9 = var2.createWithEffectiveBitsize(this.getBitsize());
         }

         this.setType(var9.updateGroup(var12.getPointedGroup()), var1);
      }
   }

   public ajz pC() {
      return (ajz)this.pC(new ajz(this.A == null ? null : this.A.duplicate(), this.kS.duplicate(), this.wS));
   }

   @Override
   public IEImm evaluate(EState var1) {
      long var2 = 0L;
      if (this.A != null && var1.hasSegmentBases()) {
         IEImm var4 = this.A.evaluate(var1);
         if (var4 == null) {
            return null;
         }

         int var5 = (int)var4.getValueAsUnsignedLong();
         var2 = var1.getSegmentBase(var5);
      }

      IEImm var8 = this.kS.evaluate(var1);
      if (var8 == null) {
         return null;
      } else {
         var2 += var8.getValueAsAddress();
         byte[] var9 = new byte[this.wS / 8];
         int var6 = var1.readMemory2(var2, var9);
         if (var6 != 0) {
            if (var6 == -2) {
               return null;
            } else {
               throw new EvaluationException(Strings.ff("Cannot read IR-state memory at 0x%X", var2));
            }
         } else {
            if (!var1.isBigEndian()) {
               EndianUtil.swap(var9);
            }

            return ajr.pC(var9, this.wS);
         }
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      alo var3 = new alo(var1, this);
      IEVar var4 = var3.pC(var2);
      if (var4 != null) {
         return var4.generateC(var1, var2);
      } else {
         boolean var5 = this.kS.getType() == null || !this.kS.getType().isPointer() || this.kS.getType().getPointedBitsize() != this.getBitsize();
         ICElementFactory var6 = var2.getElementFactory();
         Object var7;
         if (this.kS instanceof akc) {
            var7 = (ICExpression)((akc)this.kS).pC(var1, var2, false);
         } else {
            var7 = (ICExpression)this.kS.generateC(var1, var2);
         }

         if (var5) {
            IWildcardTypeManager var8 = var1.getGlobalContext().getWildcardTypeManager();
            ICTypeFactory var9 = var2.getTypeFactory();
            ICType var10 = null;
            if (this.kS.getType() != null && this.kS.getType().isPointer() && this.kS.getType().isDefined()) {
               IReferenceType var11 = (IReferenceType)TypeUtil.getNonAlias(this.kS.getType().getNativeType());
               if (var11.getPointedType().getSize() * 8 == this.getBitsize()) {
                  var10 = var9.create(this.kS.getType());
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
   public void pC(akz var1) {
      if (var1.pC()) {
         var1.append("m");
         var1.append(this.wS + "");
      }

      if (this.A != null && var1.A()) {
         var1.append("<");
         var1.pC(this.A);
         var1.append(">");
      }

      var1.bracket();
      var1.pC(this.kS);
      var1.bracketClose();
      var1.A(this);
   }
}
