package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.core.units.code.InstructionFlags;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtensionsManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroupElt;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.MemoryAccessInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
public class ajk extends akm implements IECall {
   private static final StructuredLogger pC = aco.pC(ajk.class);
   @SerId(1)
   private IWildcardPrototype A;
   @SerId(2)
   private IEGeneric kS;
   @SerId(3)
   private IEGeneric oT;
   @SerId(4)
   private List fI;
   @SerId(5)
   private List WR;
   @SerId(6)
   private int NS;
   @SerId(7)
   private List vP;
   @SerId(8)
   private INativeMethodItem xC;
   @SerId(9)
   private List ED;
   @SerId(10)
   private List Sc;
   @SerId(value = 11, deprecated = true)
   private boolean ah;
   @SerId(value = 12, deprecated = true)
   private boolean eP;
   @SerId(13)
   private MemoryAccessInfo UO;
   @SerId(14)
   private int Ab;
   @SerId(15)
   private int rl;
   @SerId(16)
   private Boolean z;

   @SerCustomInitPostGraph
   private void UT() {
      if (this.ah) {
         this.Ab |= 1;
         this.ah = false;
      }

      if (this.eP) {
         this.Ab |= 2;
         this.eP = false;
      }
   }

   private IWildcardPrototype pC(IERoutineContext var1, IEGeneric var2, List var3, IWildcardPrototype var4, List var5, boolean var6) {
      if (var2 == null) {
         throw new IllegalArgumentException("A callsite must be provided");
      } else {
         this.kS = var2;
         if (var4 == null) {
            if (!(var2 instanceof IEVar)) {
               throw new IllegalArgumentException("If no prototype is provided, the callsite must be an EVar");
            }

            this.xC = var1.getGlobalContext().retrieveRoutineFromSymbol((IEVar)var2);
            if (this.xC == null) {
               throw new IllegalArgumentException("A static Call target was expected");
            }

            var4 = var1.getGlobalContext().getCandidatePrototype(this.xC);
            if (var4 == null) {
               throw new NullPointerException("The construction of an IR call requires a prototype");
            }
         }

         this.ED = var3;
         this.A = var4;
         this.Sc = var5;
         this.setFailsafePrototype(var6);
         return var4;
      }
   }

   public ajk(IERoutineContext var1, IEGeneric var2, List var3, IWildcardPrototype var4, List var5, boolean var6) {
      super(var1);
      var4 = this.pC(var1, var2, var3, var4, var5, var6);
      IEConverter var7 = var1.getConverter();
      this.WR = var7.convertParameterExpressions(var1, var4, this.xC, var5);
      this.oT = var7.convertReturnLocation(var1, var4);
      this.vP = new ArrayList();
      this.fI = var7.convertReturnExpressions(var1, var4, this.xC, var5, this.vP);
      Integer var8 = var7.determineStackPointerDeltaAfterIRCall(var4, var5);
      this.NS = var8 == null ? 0 : var8;
   }

   public ajk(IERoutineContext var1, IEGeneric var2, List var3, IWildcardPrototype var4, IEGeneric var5, List var6, List var7, int var8, List var9) {
      super(var1);
      if (var6 == null) {
         var6 = new ArrayList();
      }

      if (var7 == null) {
         var7 = new ArrayList();
      }

      if (var9 == null) {
         var9 = new ArrayList();
      }

      ArrayList var10 = null;
      if (var4 != null) {
         IWildcardTypeManager var11 = var1.getWildcardTypeManager();
         int var12 = var4.getParameterTypes().size();
         int var13 = var7.size();
         if (var13 < var12) {
            throw new RuntimeException(Strings.ff("Prototype expects at least %d args, got only %d", var12, var13));
         }

         if (!var4.isVariableArgument()) {
            if (var13 != var12) {
               throw new RuntimeException(Strings.ff("Prototype expects exactly %d args, got %d", var12, var13));
            }
         } else {
            int var14 = var13 - var12;
            var10 = new ArrayList(var14);

            for (int var15 = var12; var15 < var13; var15++) {
               IEGeneric var16 = (IEGeneric)var7.get(var15);
               IWildcardType var17 = var16.getSafeType(var11);
               var10.add(var17);
            }
         }
      }

      this.pC(var1, var2, var3, var4, var10, false);
      this.oT = var5;
      this.fI = (List)var6;
      this.WR = (List)var7;
      this.NS = var8;
      this.vP = (List)var9;
   }

   private ajk(ajk var1) {
      super(var1.wS);
      this.A = var1.A;
      this.kS = var1.kS.duplicate();
      this.oT = var1.oT == null ? null : var1.oT.duplicate();
      this.fI = pC(var1.fI);
      this.vP = pC(var1.vP);
      this.WR = pC(var1.WR);
      this.NS = var1.NS;
      this.xC = var1.xC;
      this.ED = pC(var1.ED);
      this.Sc = var1.Sc == null ? null : new ArrayList(var1.Sc);
      this.UO = this.UO == null ? null : this.UO.clone();
      this.Ab = var1.Ab;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.WR == null ? 0 : this.WR.hashCode());
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + (this.fI == null ? 0 : this.fI.hashCode());
      var1 = 31 * var1 + (this.vP == null ? 0 : this.vP.hashCode());
      var1 = 31 * var1 + (this.oT == null ? 0 : this.oT.hashCode());
      var1 = 31 * var1 + this.NS;
      var1 = 31 * var1 + (this.xC == null ? 0 : this.xC.hashCode());
      var1 = 31 * var1 + (this.ED == null ? 0 : this.ED.hashCode());
      var1 = 31 * var1 + (this.Sc == null ? 0 : this.Sc.hashCode());
      return 31 * var1 + this.Ab;
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2, boolean var3, boolean var4) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2, var3, var4)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         ajk var5 = (ajk)var1;
         if (this.kS == null) {
            if (var5.kS != null) {
               return false;
            }
         } else if (!this.kS.equalsEx(var5.kS, var2)) {
            return false;
         }

         if (this.WR == null) {
            if (var5.WR != null) {
               return false;
            }
         } else if (!pC(this.WR, var5.WR, var2)) {
            return false;
         }

         if (this.A == null) {
            if (var5.A != null) {
               return false;
            }
         } else if (this.A != var5.A) {
            return false;
         }

         if (this.fI == null) {
            if (var5.fI != null) {
               return false;
            }
         } else if (!pC(this.fI, var5.fI, var2)) {
            return false;
         }

         if (this.vP == null) {
            if (var5.vP != null) {
               return false;
            }
         } else if (!pC(this.vP, var5.vP, var2)) {
            return false;
         }

         if (this.oT == null) {
            if (var5.oT != null) {
               return false;
            }
         } else if (!this.oT.equalsEx(var5.oT, var2)) {
            return false;
         }

         if (this.NS != var5.NS) {
            return false;
         } else {
            if (this.xC == null) {
               if (var5.xC != null) {
                  return false;
               }
            } else if (this.xC != var5.xC) {
               return false;
            }

            if (var2) {
               if (this.Sc == null) {
                  if (var5.Sc != null) {
                     return false;
                  }
               } else if (!this.Sc.equals(var5.Sc)) {
                  return false;
               }
            }

            return this.Ab == var5.Ab;
         }
      }
   }

   @Override
   public IWildcardPrototype getPrototype() {
      return this.A;
   }

   @Override
   public void setFailsafePrototype(boolean var1) {
      this.Ab = var1 ? this.Ab | 2 : this.Ab & -3;
   }

   @Override
   public boolean isFailsafePrototype() {
      return (this.Ab & 2) != 0;
   }

   @Override
   public void setPrototype(IWildcardPrototype var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   @Override
   public IEGeneric getCallSite() {
      return this.kS;
   }

   @Override
   public void setCallsite(IEGeneric var1) {
      this.kS = var1;
      if (var1 instanceof IEVar) {
         INativeMethodItem var2 = this.getContext().getGlobalContext().retrieveRoutineFromSymbol((IEVar)var1);
         if (var2 != null) {
            this.xC = var2;
         }
      }
   }

   @Override
   public boolean isStaticCallsite() {
      return this.xC != null;
   }

   @Override
   public INativeMethodItem getStaticCallsite() {
      return this.xC;
   }

   @Override
   public Boolean getNonReturning() {
      if (this.z != null) {
         return this.z;
      } else {
         return this.xC == null ? null : this.xC.getNonReturning();
      }
   }

   @Override
   public void setNonReturning(Boolean var1) {
      this.z = var1;
   }

   @Override
   public void setTentativeCall(boolean var1) {
      this.Ab = var1 ? this.Ab | 4 : this.Ab & -5;
   }

   @Override
   public boolean isTentativeCall() {
      return (this.Ab & 4) != 0;
   }

   @Override
   public List getDynamicTargetCandidates() {
      return this.ED;
   }

   @Override
   public IEGeneric getReturnLocation() {
      return this.oT;
   }

   @Override
   public void setReturnLocation(IEGeneric var1) {
      this.oT = var1;
      if (var1 == null) {
         this.setTentativeCall(false);
      }
   }

   boolean pC() {
      if (this.oT == null) {
         return true;
      } else {
         CFG var1 = this.wS.getCfg();
         if (var1 == null) {
            return false;
         } else {
            Couple var2 = var1.getInstructionLocation(this);
            if (var2 == null) {
               return false;
            } else {
               Boolean var3 = EUtil.checkCallReturnAddress(this.wS, var1, (BasicBlock)var2.getFirst(), (Integer)var2.getSecond());
               return var3 != null && var3;
            }
         }
      }
   }

   @Override
   public int getCountOfReturns() {
      return this.fI.size();
   }

   @Override
   public List getReturnExpressions() {
      return Collections.unmodifiableList(this.fI);
   }

   @Override
   public IEGeneric getReturnExpression(int var1) {
      return var1 >= 0 && var1 < this.fI.size() ? (IEGeneric)this.fI.get(var1) : null;
   }

   @Override
   public void setReturnExpression(int var1, IEGeneric var2) {
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.fI.set(var1, var2);
      }
   }

   @Override
   public void setReturnExpressionUnused(boolean var1) {
      this.Ab = var1 ? this.Ab | 1 : this.Ab & -2;
   }

   @Override
   public boolean isReturnExpressionUnused() {
      return (this.Ab & 1) != 0;
   }

   @Override
   public int getCountOfArguments() {
      return this.WR.size();
   }

   @Override
   public List getArguments() {
      return Collections.unmodifiableList(this.WR);
   }

   @Override
   public IEGeneric getArgument(int var1) {
      return var1 >= 0 && var1 < this.WR.size() ? (IEGeneric)this.WR.get(var1) : null;
   }

   @Override
   public List getSpoiledExpressions() {
      return Collections.unmodifiableList(this.vP);
   }

   @Override
   public void addSpoiledVariables(List var1) {
      for (IEVar var3 : var1) {
         if (!this.vP.contains(var3)) {
            this.vP.add(var3);
         }
      }
   }

   public boolean pC(IEVar var1) {
      return this.vP.remove(var1);
   }

   @Override
   public int getStackPointerDeltaAfterExecution() {
      return this.NS;
   }

   @Override
   public void setStackPointerDeltaAfterExecution(int var1) {
      this.NS = var1;
   }

   public IEGeneric[] kS() {
      return new IEGeneric[]{this.kS};
   }

   @Override
   public String getMnemonic() {
      return "call";
   }

   @Override
   public int getBitsize() {
      throw new RuntimeException("No bitsize");
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      if (this.oT == null && !Boolean.TRUE.equals(this.getNonReturning())) {
         return FlowInformation.NONE;
      } else {
         return this.isTentativeCall() ? FlowInformation.EMPTY_NO_FT : FlowInformation.EMPTY;
      }
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public Collection getInstructionFlags() {
      return (Collection)(Boolean.TRUE.equals(this.getNonReturning()) ? Arrays.asList(InstructionFlags.ROUTINE_TERMINATOR) : Collections.emptySet());
   }

   @Override
   public boolean accessesMemory() {
      return true;
   }

   @Override
   public boolean writesMemory() {
      return true;
   }

   @Override
   public boolean setHintArgumentPointsToExternalMemory(int var1, boolean var2) {
      if (var1 >= 0 && var1 < 31 && var1 < this.WR.size()) {
         int var3;
         if (var2) {
            var3 = this.rl | 1 << var1;
         } else {
            var3 = this.rl & ~(1 << var1);
         }

         if (var3 == this.rl) {
            return false;
         } else {
            this.rl = var3;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean getHintArgumentPointsToExternalMemory(int var1) {
      return var1 >= 0 && var1 < 31 && var1 < this.WR.size() ? (this.rl & 1 << var1) != 0 : false;
   }

   @Override
   public void collectSubExpressions(Collection var1, Boolean var2) {
      if (var2 == null) {
         var1.add(this.kS);
         CollectionUtil.addNonNulls(var1, this.oT);
         CollectionUtil.addNonNulls(var1, this.fI);
         CollectionUtil.addNonNulls(var1, this.WR);
         CollectionUtil.addNonNulls(var1, this.vP);
      } else if (var2) {
         var1.add(this.kS);
         CollectionUtil.addNonNulls(var1, this.oT);
         CollectionUtil.addNonNulls(var1, this.WR);
      } else {
         CollectionUtil.addNonNulls(var1, this.fI);
         CollectionUtil.addNonNulls(var1, this.vP);
      }
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
      var1.add(new Couple(this, this.kS));
      if (this.oT != null) {
         var1.add(new Couple(this, this.oT));
      }

      this.fI.forEach(var2 -> A(this, var2, var1));
      this.WR.forEach(var2 -> pC(this, var2, var1));
      this.vP.forEach(var2 -> A(this, var2, var1));
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      pC(var1, var2);
      if (this.kS == var1) {
         A(this.kS, var2);
         this.kS = var2;
         return true;
      } else if (this.oT == var1) {
         A(this.oT, var2);
         this.oT = var2;
         return true;
      } else {
         for (int var3 = 0; var3 < this.fI.size(); var3++) {
            IEGeneric var4 = (IEGeneric)this.fI.get(var3);
            if (var4 == var1) {
               A(var4, var2);
               this.fI.set(var3, var2);
               return true;
            }
         }

         for (int var5 = 0; var5 < this.WR.size(); var5++) {
            IEGeneric var7 = (IEGeneric)this.WR.get(var5);
            if (var7 == var1) {
               A(var7, var2);
               this.WR.set(var5, var2);
               return true;
            }
         }

         for (int var6 = 0; var6 < this.vP.size(); var6++) {
            IEGeneric var8 = (IEGeneric)this.vP.get(var6);
            if (var8 == var1) {
               A(var8, var2);
               this.vP.set(var6, var2);
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2, boolean var3) throws IllegalIntermediateExpressionException {
      int var4 = 0;
      if (this.kS == var1) {
         A(this.kS, var2);
         this.kS = var2.duplicate();
         var4++;
      } else {
         var4 += this.kS.replaceVar(var1, var2);
      }

      if (this.oT == var1) {
         A(this.oT, var2);
         this.oT = var2.duplicate();
         var4++;
      } else if (this.oT != null) {
         var4 += this.oT.replaceVar(var1, var2);
      }

      for (int var5 = 0; var5 < this.fI.size(); var5++) {
         IEGeneric var6 = (IEGeneric)this.fI.get(var5);
         if (!var3 || var6 instanceof IEMem || var6 instanceof IEGroupElt) {
            if (var6 == var1) {
               A(var6, var2);
               this.fI.set(var5, var2.duplicate());
               var4++;
            } else {
               var4 += var6.replaceVar(var1, var2);
            }
         }
      }

      for (int var8 = 0; var8 < this.WR.size(); var8++) {
         IEGeneric var10 = (IEGeneric)this.WR.get(var8);
         if (var10 == var1) {
            A(var10, var2);
            this.WR.set(var8, var2.duplicate());
            var4++;
         } else {
            var4 += var10.replaceVar(var1, var2);
         }
      }

      for (int var9 = 0; var9 < this.vP.size(); var9++) {
         IEGeneric var11 = (IEGeneric)this.vP.get(var9);
         if (!var3 || var11 instanceof ajz || var11 instanceof IEGroupElt) {
            if (var11 == var1) {
               A(var11, var2);
               this.vP.set(var9, var2.duplicate());
               var4++;
            } else {
               var4 += var11.replaceVar(var1, var2);
            }
         }
      }

      return var4;
   }

   @Override
   public int replaceDefinedVar(IEVar var1, IEGeneric var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < this.fI.size(); var4++) {
         IEGeneric var5 = (IEGeneric)this.fI.get(var4);
         if (var5 == var1) {
            A(var5, var2);
            this.fI.set(var4, var2.duplicate());
            var3++;
         }
      }

      for (int var6 = 0; var6 < this.vP.size(); var6++) {
         IEGeneric var7 = (IEGeneric)this.vP.get(var6);
         if (var7 == var1) {
            A(var7, var2);
            this.vP.set(var6, var2.duplicate());
            var3++;
         }
      }

      return var3;
   }

   @Override
   public void getDefUse(EDefUseInfo var1) {
      this.kS.getUsed(var1);
      if (this.oT != null) {
         this.oT.getUsed(var1);
      }

      for (IEGeneric var3 : this.WR) {
         var3.getUsed(var1);
      }

      for (IEGeneric var9 : this.fI) {
         var9.getDefinedOrUsedAsDestination(var1);
      }

      IEVar var8 = this.getContext().getStackPointer();
      var1.addDefined(var8);
      var1.addUsed(var8);
      boolean var10 = var1.shouldCollectSpoiled();
      boolean var4 = var1.shouldCollectPotentials();
      if (var10) {
         for (IEGeneric var6 : this.vP) {
            if (var6 instanceof IEVar) {
               var1.addSpoiled((IEVar)var6);
            }
         }
      }

      if (var10 || var4) {
         MemoryAccessInfo var11 = this.UO;
         if (var11 == null) {
            if (var1.getInstructionAddress() != null) {
               var11 = this.pC(var1.getInstructionAddress());
            }

            if (var11 == null) {
               var11 = MemoryAccessInfo.ACCESSES_ALL;
            }
         }

         if (var4 && var11.isAccessStack()) {
            Collection var12 = this.wS.getStackVariables();
            var1.addPotentialUsed(var11.filterStackReads(var12));
            var1.addPotentialDefined(var11.filterStackWrites(var12));
         }

         if (var10 && var11.hasSpoiledStack()) {
            Collection var13 = this.wS.getStackVariables();
            var1.addSpoiled(var11.filterStackSpoiled(var13));
         }

         if (var4 && var11.isAccessGlobals()) {
            Collection var14 = this.wS.getGlobalContext().getGlobalVariables();
            var1.addPotentialUsed(var11.filterGlobalsReads(var14));
            var1.addPotentialDefined(var11.filterGlobalsWrites(var14));
         }
      }
   }

   @Override
   public void setMemoryAccessInfo(MemoryAccessInfo var1) {
      this.UO = var1;
   }

   @Override
   public MemoryAccessInfo getMemoryAccessInfo() {
      return this.UO;
   }

   private MemoryAccessInfo pC(long var1) {
      if (!this.wS.isAllowUnsafeAnalysis()) {
         return MemoryAccessInfo.ACCESSES_ALL;
      } else {
         ale var3 = new ale(this);
         MemoryAccessInfo var4 = var3.pC();
         if (var4 != null) {
            return var4;
         } else {
            IdRanges var5 = null;
            boolean var6 = true;
            int[] var7 = new int[1];
            INativeDecompilerExtensionsManager var9 = this.wS.getDecompiler() == null ? null : this.wS.getDecompiler().getExtensionsManager();

            for (IEGeneric var11 : this.WR) {
               if (var11 instanceof IEOperation && ((IEOperation)var11).isCustomOperation()) {
                  IEOperation var12 = (IEOperation)var11;
                  if (var12.getCountOfOperands() == 1) {
                     FunctionOptype var13 = var12.getCustomOperationType();
                     if (var13.hasFlags(16)) {
                        var11 = var12.getOperand1();
                     }
                  }
               }

               IWildcardType var25 = var11.getType();
               if (EUtil.isLikeImmediate(var11)) {
                  if (var25 != null && var25.isPointer() && this.pC(var11)) {
                     var6 = false;
                     break;
                  }
               } else if (ale.pC(var11, var7)) {
                  if (var5 == null) {
                     var5 = new IdRanges();
                  }

                  int var27 = var7[0];
                  if (var27 < 0) {
                     var5.addAll(this.wS.getStackVariables(var27, 0));
                  } else {
                     var5.addAll(this.wS.getStackVariables(var27, var27 + 1048576));
                  }
               } else if (!ale.pC(var11) && (!(var11 instanceof IEMem) || !var11.hasFlags(256))) {
                  IEVar var8;
                  if ((var8 = alj.pC(var11)) != null && var8.isStackReference()) {
                     if (var5 == null) {
                        var5 = new IdRanges();
                     }

                     var5.addAll(this.wS.getStackVariables(-1048576, 0));
                  } else if (var25 == null || !var25.isDefined() && var25.getGroup() == null) {
                     if (var11 instanceof IEVar) {
                        var6 = false;
                        break;
                     }
                  } else if (var25.isPointer() && (var9 == null || !Boolean.TRUE.equals(var9.isOpaquePointerType(var25).getResult()))) {
                     var6 = false;
                     break;
                  }
               }
            }

            if (var6) {
               if (var5 == null) {
                  return MemoryAccessInfo.ACCESSES_GLOBALS;
               } else {
                  MemoryAccessInfo var23 = new MemoryAccessInfo();
                  var23.setStackAccessRanges(var5, var5);
                  return var23;
               }
            } else {
               MemoryAccessInfo var22 = new MemoryAccessInfo();
               Integer var24 = this.getSPDelta();
               if (var24 != null && var24 < 0) {
                  IdRanges var26 = new IdRanges();
                  var22.setStackAccessRanges(var26, var26);
                  IdRanges var28 = new IdRanges();
                  var22.setStackSpoiledRanges(var28);
                  Collection var14 = this.wS.getRoutineVariablesInRange(33554432, Integer.MAX_VALUE);
                  if (!var14.isEmpty()) {
                     long var15 = var24.intValue();
                     Integer var17 = this.wS.getConverter().determineStackBytesUsedByCall(this.A, this.Sc);
                     var15 += var17 == null ? 0L : var17.intValue();

                     for (IEVar var19 : var14) {
                        long var20 = var19.getAddress();
                        if (var20 < 0L && var20 >= var15) {
                           var26.add(var19);
                        } else if (var20 < var15 && var20 >= var24.intValue()) {
                           var28.add(var19);
                        }
                     }
                  }
               }

               return var22;
            }
         }
      }
   }

   private boolean pC(IEGeneric var1) {
      try {
         long var2 = var1.evaluateAddress(null);
         if (var2 == 0L) {
            return false;
         }

         if (this.wS.getNativeContext() instanceof INativeCodeUnit) {
            INativeCodeUnit var4 = (INativeCodeUnit)this.wS.getNativeContext();
            long var5 = var4.getVirtualImageBase();
            long var7 = var5 + var4.getImageSize();
            if (var2 >= var5 && var2 < var7) {
               return false;
            }
         }
      } catch (Exception var9) {
      }

      return true;
   }

   @Override
   public void preUpdateTypes(ETypeInfo var1) {
      if (this.xC == null && this.kS != null && this.kS.getType() != null) {
         IWildcardType var2 = this.kS.getType();
         if (var2.getNativeType() instanceof IReferenceType && ((IReferenceType)var2.getNativeType()).getPointedType() instanceof IPrototypeItem var4) {
            this.A = var1.getWildcardTypeManager().createPrototype(var4);
         }
      }

      int var6 = 0;

      for (IEGeneric var9 : this.WR) {
         IWildcardType var5 = this.A.getParameterType(var6);
         if (var5 != null) {
            var9.setType(var5, var1);
         }

         var6++;
      }

      if (this.fI.size() == 1) {
         IWildcardType var8 = this.A.getReturnType();
         if (var8 != null) {
            ((IEGeneric)this.fI.get(0)).setType(var8, var1);
         }
      }

      if (this.oT != null) {
         this.oT.setType(var1.getWildcardTypeManager().create("void*"));
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      if (this.xC == null && this.kS != null && !EUtil.hasTypeInfo(this.kS)) {
         this.kS.updateTypes(var1);
      }

      for (IEGeneric var3 : this.WR) {
         var3.updateTypes(var1);
      }

      for (IEGeneric var6 : this.fI) {
         var6.updateTypes(var1);
      }

      for (IEGeneric var7 : this.vP) {
         var7.updateTypes(var1);
      }

      if (this.oT != null) {
         this.oT.updateTypes(var1);
      }
   }

   @Override
   public void postUpdateTypes(ETypeInfo var1) {
      for (int var2 = 0; var2 < this.WR.size(); var2++) {
         IEGeneric var3 = (IEGeneric)this.WR.get(var2);
         if (var3 instanceof IEImm) {
            IWildcardType var4 = this.A.getParameterType(var2);
            if (var4 != null) {
               IWildcardType var5 = var3.getType();
               if (var5 != null && !var5.equals(var4)) {
                  IEImm var6 = (IEImm)var3;
                  IEImm var7 = var6.duplicateWithType(var4);
                  this.WR.set(var2, var7);
               }
            }
         }
      }

      if (this.fI.size() == 1) {
         IEGeneric var8 = (IEGeneric)this.fI.get(0);
         if (var8 instanceof IEImm) {
            IWildcardType var10 = this.A.getReturnType(0);
            if (var10 != null) {
               IWildcardType var12 = var8.getType();
               if (var12 != null && !var12.equals(var10)) {
                  IEImm var14 = (IEImm)var8;
                  IEImm var16 = var14.duplicateWithType(var10);
                  this.fI.set(0, var16);
               }
            }
         }
      }

      if (this.oT instanceof IEImm) {
         IWildcardType var9 = var1.getWildcardTypeManager().create("void*");
         IWildcardType var11 = this.oT.getType();
         if (var11 != null && !var11.equals(var9)) {
            IEImm var13 = (IEImm)this.oT;
            IEImm var15 = var13.duplicateWithType(var9);
            this.oT = var15;
         }
      }
   }

   @Override
   public IEImm evaluate(EState var1) {
      if (!var1.isExecuteSubRoutines()) {
         if (var1.isSoftFailMode()) {
            return null;
         } else {
            throw new EvaluationException("ECall eval(): evaluation is not allowed");
         }
      } else if (Boolean.TRUE.equals(this.getNonReturning())) {
         if (var1.isSoftFailMode()) {
            return null;
         } else {
            throw new EvaluationException("ECall eval(): non-returning routines are not supported");
         }
      } else {
         List var2 = this.getReturnExpressions();
         if (var2.size() >= 2) {
            if (var1.isSoftFailMode()) {
               return null;
            } else {
               throw new EvaluationException("ECall eval(): multi-return routines are not supported");
            }
         } else {
            IEImm var3 = this.getCallSite().evaluate(var1);
            INativeMethodItem var4 = this.wS.getNativeContext().getRoutine(var3.getValueAsAddress());
            if (var4 == null) {
               if (var1.isSoftFailMode()) {
                  return null;
               } else {
                  throw new EvaluationException("Cannot determine target of ECall");
               }
            } else {
               EEmulator var5 = new EEmulator(var1);
               var5.setTargetRoutine(var4);
               var5.setResetUnknownRegisters(true);

               for (IEGeneric var7 : this.getArguments()) {
                  IEImm var8 = var7.evaluate(var1);
                  var5.addArgument(var8);
               }

               Long var15;
               if (this.getReturnLocation() != null) {
                  IEImm var16 = this.getReturnLocation().evaluate(var1);
                  var15 = var16.getValueAsAddress();
               } else {
                  int var17 = var1.getVirtualPC() + this.getSize();
                  var15 = this.wS.convertIntermediateOffset(var17);
               }

               if (var15 != null) {
                  var5.setReturnAddress(this.wS.getConverter().sanitizeNativeAddress(var15));
               }

               IEImm var18 = null;
               var5.setup();

               try {
                  try {
                     var5.run();
                  } catch (Exception var13) {
                     if (!var1.isSoftFailMode()) {
                        throw var13;
                     }

                     return null;
                  }

                  if (var2.size() == 1) {
                     var18 = var5.getReturnValue().evaluate(var1);
                  }
               } finally {
                  var5.teardown();
               }

               if (var2.size() == 1) {
                  IEGeneric var19 = (IEGeneric)var2.get(0);
                  var18 = this.wS.createAssign(var19, var18).evaluate(var1, true);
               }

               int var20 = var1.getVirtualPC() + this.getSize();
               var1.setVirtualPC(var20);
               Long var21 = this.wS.convertIntermediateOffset(var20);
               if (var21 == null) {
                  var1.removeValue(this.wS.getProgramCounterId());
               } else {
                  var1.setValue(this.wS.getProgramCounterId(), var21);
               }

               return var18;
            }
         }
      }
   }

   public ajk wS() {
      ajk var1 = new ajk(this);
      return (ajk)super.pC(var1);
   }

   @Override
   public ICStatement generateC(IERoutineContext var1, ICMethod var2) {
      Assert.a(var1 == this.wS, "Illegal IR context");
      ICElementFactory var3 = var2.getElementFactory();
      ICTypeFactory var4 = var2.getTypeFactory();
      ICOperatorFactory var5 = var2.getOperatorFactory();
      ArrayList var6 = new ArrayList();
      int var7 = 0;

      for (IEGeneric var9 : this.WR) {
         Object var10 = (ICExpression)var9.generateC(var1, var2);
         IWildcardType var11 = this.A.getParameterType(var7);
         if (var11 != null && EUtil.hasTypeInfo(var9) && EUtil.requiresExplicitCast(var11, var9.getType())) {
            ICType var12 = var4.create(var11);
            var10 = var3.createCast(var12, (ICExpression)var10);
         }

         var6.add(var10);
         var7++;
      }

      Object var18 = null;
      ICOperator var19 = null;
      if (!this.isReturnExpressionUnused() && !this.fI.isEmpty()) {
         if (this.fI.size() == 1) {
            IEGeneric var20 = (IEGeneric)this.fI.get(0);
            if (var20 instanceof IEVar) {
               var18 = (ICLeftExpression)((aku)var20).generateC(var1, var2, 1);
               IWildcardType var23 = this.A.getReturnType();
               if (var23 != null && EUtil.hasTypeInfo(var20) && EUtil.requiresExplicitCast(var20.getType(), var23)) {
                  ICType var26 = var4.create(var20.getType());
                  var19 = var5.createCastOperator(var26);
               }
            } else {
               var18 = (ICLeftExpression)var20.generateC(var1, var2);
            }
         } else {
            ArrayList var21 = new ArrayList();

            for (IEGeneric var27 : this.fI) {
               if (var27 instanceof IEVar) {
                  var21.add((ICExpression)((aku)var27).generateC(var1, var2, this.fI.size() == 1 ? 1 : 0));
               } else {
                  var21.add((ICExpression)var27.generateC(var1, var2));
               }
            }

            var18 = var3.createTuple(var21);
         }
      }

      adi var25 = (adi)var2.getGlobalContext().getMethodFactory();
      ICCall var22;
      if (this.xC != null) {
         IWildcardPrototype var28 = var1.getGlobalContext().getCandidatePrototype(this.xC);
         aew var13 = var25.pC(this.xC, var28, false);
         var22 = var3.createCall(var13, var6);
      } else {
         ArrayList var29 = new ArrayList();
         if (this.ED != null) {
            for (IEVar var14 : this.ED) {
               INativeMethodItem var15 = var1.getGlobalContext().retrieveRoutineFromSymbol(var14);
               if (var15 != null) {
                  IWildcardPrototype var16 = var1.getGlobalContext().getCandidatePrototype(var15);
                  aew var17 = var25.pC(var15, var16, false);
                  var29.add(var17);
               }
            }
         }

         ICExpression var31 = (ICExpression)this.kS.generateC(var1, var2);
         var22 = var3.createCall(var31, var6, var29);
      }

      if (Booleans.isTrue(this.getNonReturning())) {
         ((adt)var22).A(true);
      } else if (!this.pC()) {
         ((adt)var22).pC(true);
      }

      if (var18 == null) {
         return (ICStatement)this.pC(var22);
      } else {
         return var19 == null
            ? (ICStatement)this.pC(var3.createAssignment((ICLeftExpression)var18, var22))
            : (ICStatement)this.pC(var3.createAssignment((ICLeftExpression)var18, var3.createOperation(var19, var22)));
      }
   }

   @Override
   public void pC(akz var1) {
      var1.appendKeyword("call");
      var1.append(" ");
      var1.pC(this.kS);
      if (this.ED != null && !this.ED.isEmpty()) {
         var1.brace();
         int var2 = 0;

         for (IEVar var4 : this.ED) {
            if (var2 >= 1) {
               var1.append("|");
            }

            var1.pC(var4);
            var2++;
         }

         var1.braceClose();
      }

      var1.paren();
      int var5 = 0;

      for (IEGeneric var9 : this.WR) {
         if (var5++ >= 1) {
            var1.append(", ");
         }

         var1.pC(var9);
      }

      var1.parenClose();
      var1.append("->");
      var1.paren();
      var5 = 0;

      for (IEGeneric var10 : this.fI) {
         if (var5++ >= 1) {
            var1.append(", ");
         }

         var1.pC(var10);
      }

      var1.parenClose();
      var1.brace();
      if (this.oT != null) {
         var1.pC(this.oT);
      }

      var1.braceClose();
   }
}
