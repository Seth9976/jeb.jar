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
public class aln extends amp implements IECall {
   private static final StructuredLogger lm = aeg.q(aln.class);
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   @SerId(1)
   private IWildcardPrototype zz;
   @SerId(2)
   private IEGeneric JY;
   @SerId(3)
   private IEGeneric HF;
   @SerId(4)
   private List LK;
   @SerId(5)
   private List io;
   @SerId(6)
   private int qa;
   @SerId(7)
   private List Hk;
   @SerId(8)
   private INativeMethodItem Me;
   @SerId(9)
   private List PV;
   @SerId(10)
   private List oQ;
   @SerId(value = 11, deprecated = true)
   private boolean xW;
   @SerId(value = 12, deprecated = true)
   private boolean KT;
   @SerId(13)
   private MemoryAccessInfo Gf;
   @SerId(14)
   private int Ef;
   @SerId(15)
   private int cC;
   @SerId(16)
   private Boolean sH;

   @SerCustomInitPostGraph
   private void Uv() {
      if (this.xW) {
         this.Ef |= 1;
         this.xW = false;
      }

      if (this.KT) {
         this.Ef |= 2;
         this.KT = false;
      }
   }

   private IWildcardPrototype q(IERoutineContext var1, IEGeneric var2, List var3, IWildcardPrototype var4, List var5, boolean var6) {
      if (var2 == null) {
         throw new IllegalArgumentException("A callsite must be provided");
      } else {
         this.JY = var2;
         if (var4 == null) {
            if (!(var2 instanceof IEVar)) {
               throw new IllegalArgumentException("If no prototype is provided, the callsite must be an EVar");
            }

            this.Me = var1.getGlobalContext().retrieveRoutineFromSymbol((IEVar)var2);
            if (this.Me == null) {
               throw new IllegalArgumentException("A static Call target was expected");
            }

            var4 = var1.getGlobalContext().getCandidatePrototype(this.Me);
            if (var4 == null) {
               throw new NullPointerException("The construction of an IR call requires a prototype");
            }
         }

         this.PV = var3;
         this.zz = var4;
         this.oQ = var5;
         this.setFailsafePrototype(var6);
         return var4;
      }
   }

   public aln(IERoutineContext var1, IEGeneric var2, List var3, IWildcardPrototype var4, List var5, boolean var6) {
      super(var1);
      var4 = this.q(var1, var2, var3, var4, var5, var6);
      IEConverter var7 = var1.getConverter();
      this.io = var7.convertParameterExpressions(var1, var4, this.Me, var5);
      this.HF = var7.convertReturnLocation(var1, var4);
      this.Hk = new ArrayList();
      this.LK = var7.convertReturnExpressions(var1, var4, this.Me, var5, this.Hk);
      this.qa = var7.determineStackPointerDeltaAfterIRCall(var4, var5);
   }

   public aln(IERoutineContext var1, IEGeneric var2, List var3, IWildcardPrototype var4, IEGeneric var5, List var6, List var7, int var8, List var9) {
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

      this.q(var1, var2, var3, var4, var10, false);
      this.HF = var5;
      this.LK = (List)var6;
      this.io = (List)var7;
      this.qa = var8;
      this.Hk = (List)var9;
   }

   private aln(aln var1) {
      super(var1.Dw);
      this.zz = var1.zz;
      this.JY = var1.JY.duplicate();
      this.HF = var1.HF == null ? null : var1.HF.duplicate();
      this.LK = q(var1.LK);
      this.Hk = q(var1.Hk);
      this.io = q(var1.io);
      this.qa = var1.qa;
      this.Me = var1.Me;
      this.PV = q(var1.PV);
      this.oQ = var1.oQ == null ? null : new ArrayList(var1.oQ);
      this.Gf = this.Gf == null ? null : this.Gf.clone();
      this.Ef = var1.Ef;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.JY == null ? 0 : this.JY.hashCode());
      var1 = 31 * var1 + (this.io == null ? 0 : this.io.hashCode());
      var1 = 31 * var1 + (this.zz == null ? 0 : this.zz.hashCode());
      var1 = 31 * var1 + (this.LK == null ? 0 : this.LK.hashCode());
      var1 = 31 * var1 + (this.Hk == null ? 0 : this.Hk.hashCode());
      var1 = 31 * var1 + (this.HF == null ? 0 : this.HF.hashCode());
      var1 = 31 * var1 + this.qa;
      var1 = 31 * var1 + (this.Me == null ? 0 : this.Me.hashCode());
      var1 = 31 * var1 + (this.PV == null ? 0 : this.PV.hashCode());
      var1 = 31 * var1 + (this.oQ == null ? 0 : this.oQ.hashCode());
      return 31 * var1 + this.Ef;
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
         aln var5 = (aln)var1;
         if (this.JY == null) {
            if (var5.JY != null) {
               return false;
            }
         } else if (!this.JY.equalsEx(var5.JY, var2)) {
            return false;
         }

         if (this.io == null) {
            if (var5.io != null) {
               return false;
            }
         } else if (!q(this.io, var5.io, var2)) {
            return false;
         }

         if (this.zz == null) {
            if (var5.zz != null) {
               return false;
            }
         } else if (this.zz != var5.zz) {
            return false;
         }

         if (this.LK == null) {
            if (var5.LK != null) {
               return false;
            }
         } else if (!q(this.LK, var5.LK, var2)) {
            return false;
         }

         if (this.Hk == null) {
            if (var5.Hk != null) {
               return false;
            }
         } else if (!q(this.Hk, var5.Hk, var2)) {
            return false;
         }

         if (this.HF == null) {
            if (var5.HF != null) {
               return false;
            }
         } else if (!this.HF.equalsEx(var5.HF, var2)) {
            return false;
         }

         if (this.qa != var5.qa) {
            return false;
         } else {
            if (this.Me == null) {
               if (var5.Me != null) {
                  return false;
               }
            } else if (this.Me != var5.Me) {
               return false;
            }

            if (var2) {
               if (this.oQ == null) {
                  if (var5.oQ != null) {
                     return false;
                  }
               } else if (!this.oQ.equals(var5.oQ)) {
                  return false;
               }
            }

            return this.Ef == var5.Ef;
         }
      }
   }

   @Override
   public IWildcardPrototype getPrototype() {
      return this.zz;
   }

   @Override
   public void setFailsafePrototype(boolean var1) {
      this.Ef = var1 ? this.Ef | 2 : this.Ef & -3;
   }

   @Override
   public boolean isFailsafePrototype() {
      return (this.Ef & 2) != 0;
   }

   @Override
   public void setPrototype(IWildcardPrototype var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.zz = var1;
      }
   }

   @Override
   public IEGeneric getCallSite() {
      return this.JY;
   }

   @Override
   public void setCallsite(IEGeneric var1) {
      this.JY = var1;
      if (var1 instanceof IEVar) {
         INativeMethodItem var2 = this.getContext().getGlobalContext().retrieveRoutineFromSymbol((IEVar)var1);
         if (var2 != null) {
            this.Me = var2;
         }
      }
   }

   @Override
   public boolean isStaticCallsite() {
      return this.Me != null;
   }

   @Override
   public INativeMethodItem getStaticCallsite() {
      return this.Me;
   }

   @Override
   public Boolean getNonReturning() {
      if (this.sH != null) {
         return this.sH;
      } else {
         return this.Me == null ? null : this.Me.getNonReturning();
      }
   }

   @Override
   public void setNonReturning(Boolean var1) {
      this.sH = var1;
   }

   @Override
   public void setTentativeCall(boolean var1) {
      this.Ef = var1 ? this.Ef | 4 : this.Ef & -5;
   }

   @Override
   public boolean isTentativeCall() {
      return (this.Ef & 4) != 0;
   }

   @Override
   public List getDynamicTargetCandidates() {
      return this.PV;
   }

   @Override
   public IEGeneric getReturnLocation() {
      return this.HF;
   }

   @Override
   public void setReturnLocation(IEGeneric var1) {
      this.HF = var1;
      if (var1 == null) {
         this.setTentativeCall(false);
      }
   }

   boolean q() {
      if (this.HF == null) {
         return true;
      } else {
         CFG var1 = this.Dw.getCfg();
         if (var1 == null) {
            return false;
         } else {
            Couple var2 = var1.getInstructionLocation(this);
            if (var2 == null) {
               return false;
            } else {
               Boolean var3 = EUtil.checkCallReturnAddress(this.Dw, var1, (BasicBlock)var2.getFirst(), (Integer)var2.getSecond());
               return var3 != null && var3;
            }
         }
      }
   }

   @Override
   public int getCountOfReturns() {
      return this.LK.size();
   }

   @Override
   public List getReturnExpressions() {
      return Collections.unmodifiableList(this.LK);
   }

   @Override
   public IEGeneric getReturnExpression(int var1) {
      return var1 >= 0 && var1 < this.LK.size() ? (IEGeneric)this.LK.get(var1) : null;
   }

   @Override
   public void setReturnExpression(int var1, IEGeneric var2) {
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.LK.set(var1, var2);
      }
   }

   @Override
   public void setReturnExpressionUnused(boolean var1) {
      this.Ef = var1 ? this.Ef | 1 : this.Ef & -2;
   }

   @Override
   public boolean isReturnExpressionUnused() {
      return (this.Ef & 1) != 0;
   }

   @Override
   public int getCountOfArguments() {
      return this.io.size();
   }

   @Override
   public List getArguments() {
      return Collections.unmodifiableList(this.io);
   }

   @Override
   public IEGeneric getArgument(int var1) {
      return var1 >= 0 && var1 < this.io.size() ? (IEGeneric)this.io.get(var1) : null;
   }

   @Override
   public List getSpoiledExpressions() {
      return Collections.unmodifiableList(this.Hk);
   }

   @Override
   public void addSpoiledVariables(List var1) {
      for (IEVar var3 : var1) {
         if (!this.Hk.contains(var3)) {
            this.Hk.add(var3);
         }
      }
   }

   public boolean q(IEVar var1) {
      return this.Hk.remove(var1);
   }

   @Override
   public int getStackPointerDeltaAfterExecution() {
      return this.qa;
   }

   @Override
   public void setStackPointerDeltaAfterExecution(int var1) {
      this.qa = var1;
   }

   public IEGeneric[] xK() {
      return new IEGeneric[]{this.JY};
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
      if (this.HF == null && !Boolean.TRUE.equals(this.getNonReturning())) {
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
      if (var1 >= 0 && var1 < 31 && var1 < this.io.size()) {
         int var3;
         if (var2) {
            var3 = this.cC | 1 << var1;
         } else {
            var3 = this.cC & ~(1 << var1);
         }

         if (var3 == this.cC) {
            return false;
         } else {
            this.cC = var3;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean getHintArgumentPointsToExternalMemory(int var1) {
      return var1 >= 0 && var1 < 31 && var1 < this.io.size() ? (this.cC & 1 << var1) != 0 : false;
   }

   @Override
   public void collectSubExpressions(Collection var1, Boolean var2) {
      if (var2 == null) {
         var1.add(this.JY);
         CollectionUtil.addNonNulls(var1, this.HF);
         CollectionUtil.addNonNulls(var1, this.LK);
         CollectionUtil.addNonNulls(var1, this.io);
         CollectionUtil.addNonNulls(var1, this.Hk);
      } else if (var2) {
         var1.add(this.JY);
         CollectionUtil.addNonNulls(var1, this.HF);
         CollectionUtil.addNonNulls(var1, this.io);
      } else {
         CollectionUtil.addNonNulls(var1, this.LK);
         CollectionUtil.addNonNulls(var1, this.Hk);
      }
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
      var1.add(new Couple(this, this.JY));
      if (this.HF != null) {
         var1.add(new Couple(this, this.HF));
      }

      this.LK.forEach(var2 -> RF(this, var2, var1));
      this.io.forEach(var2 -> q(this, var2, var1));
      this.Hk.forEach(var2 -> RF(this, var2, var1));
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      q(var1, var2);
      if (this.JY == var1) {
         RF(this.JY, var2);
         this.JY = var2;
         return true;
      } else if (this.HF == var1) {
         RF(this.HF, var2);
         this.HF = var2;
         return true;
      } else {
         for (int var3 = 0; var3 < this.LK.size(); var3++) {
            IEGeneric var4 = (IEGeneric)this.LK.get(var3);
            if (var4 == var1) {
               RF(var4, var2);
               this.LK.set(var3, var2);
               return true;
            }
         }

         for (int var5 = 0; var5 < this.io.size(); var5++) {
            IEGeneric var7 = (IEGeneric)this.io.get(var5);
            if (var7 == var1) {
               RF(var7, var2);
               this.io.set(var5, var2);
               return true;
            }
         }

         for (int var6 = 0; var6 < this.Hk.size(); var6++) {
            IEGeneric var8 = (IEGeneric)this.Hk.get(var6);
            if (var8 == var1) {
               RF(var8, var2);
               this.Hk.set(var6, var2);
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2, boolean var3) throws IllegalIntermediateExpressionException {
      int var4 = 0;
      if (this.JY == var1) {
         RF(this.JY, var2);
         this.JY = var2.duplicate();
         var4++;
      } else {
         var4 += this.JY.replaceVar(var1, var2);
      }

      if (this.HF == var1) {
         RF(this.HF, var2);
         this.HF = var2.duplicate();
         var4++;
      } else if (this.HF != null) {
         var4 += this.HF.replaceVar(var1, var2);
      }

      for (int var5 = 0; var5 < this.LK.size(); var5++) {
         IEGeneric var6 = (IEGeneric)this.LK.get(var5);
         if (!var3 || var6 instanceof IEMem || var6 instanceof IEGroupElt) {
            if (var6 == var1) {
               RF(var6, var2);
               this.LK.set(var5, var2.duplicate());
               var4++;
            } else {
               var4 += var6.replaceVar(var1, var2);
            }
         }
      }

      for (int var8 = 0; var8 < this.io.size(); var8++) {
         IEGeneric var10 = (IEGeneric)this.io.get(var8);
         if (var10 == var1) {
            RF(var10, var2);
            this.io.set(var8, var2.duplicate());
            var4++;
         } else {
            var4 += var10.replaceVar(var1, var2);
         }
      }

      for (int var9 = 0; var9 < this.Hk.size(); var9++) {
         IEGeneric var11 = (IEGeneric)this.Hk.get(var9);
         if (!var3 || var11 instanceof amc || var11 instanceof IEGroupElt) {
            if (var11 == var1) {
               RF(var11, var2);
               this.Hk.set(var9, var2.duplicate());
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

      for (int var4 = 0; var4 < this.LK.size(); var4++) {
         IEGeneric var5 = (IEGeneric)this.LK.get(var4);
         if (var5 == var1) {
            RF(var5, var2);
            this.LK.set(var4, var2);
            var3++;
         }
      }

      for (int var6 = 0; var6 < this.Hk.size(); var6++) {
         IEGeneric var7 = (IEGeneric)this.Hk.get(var6);
         if (var7 == var1) {
            RF(var7, var2);
            this.Hk.set(var6, var2);
            var3++;
         }
      }

      return var3;
   }

   @Override
   public void getDefUse(EDefUseInfo var1) {
      this.JY.getUsed(var1);
      if (this.HF != null) {
         this.HF.getUsed(var1);
      }

      for (IEGeneric var3 : this.io) {
         var3.getUsed(var1);
      }

      for (IEGeneric var9 : this.LK) {
         var9.getDefinedOrUsedAsDestination(var1);
      }

      IEVar var8 = this.getContext().getStackPointer();
      var1.addDefined(var8);
      var1.addUsed(var8);
      boolean var10 = var1.shouldCollectSpoiled();
      boolean var4 = var1.shouldCollectPotentials();
      if (var10) {
         for (IEGeneric var6 : this.Hk) {
            if (var6 instanceof IEVar) {
               var1.addSpoiled((IEVar)var6);
            }
         }
      }

      if (var10 || var4) {
         MemoryAccessInfo var11 = this.Gf;
         if (var11 == null) {
            if (var1.getInstructionAddress() != null) {
               var11 = this.xK(var1.getInstructionAddress());
            }

            if (var11 == null) {
               var11 = MemoryAccessInfo.ACCESSES_ALL;
            }
         }

         if (var4 && var11.isAccessStack()) {
            Collection var12 = this.Dw.getStackVariables();
            var1.addPotentialUsed(var11.filterStackReads(var12));
            var1.addPotentialDefined(var11.filterStackWrites(var12));
         }

         if (var10 && var11.hasSpoiledStack()) {
            Collection var13 = this.Dw.getStackVariables();
            var1.addSpoiled(var11.filterStackSpoiled(var13));
         }

         if (var4 && var11.isAccessGlobals()) {
            Collection var14 = this.Dw.getGlobalContext().getGlobalVariables();
            var1.addPotentialUsed(var11.filterGlobalsReads(var14));
            var1.addPotentialDefined(var11.filterGlobalsWrites(var14));
         }
      }
   }

   @Override
   public void setMemoryAccessInfo(MemoryAccessInfo var1) {
      this.Gf = var1;
   }

   @Override
   public MemoryAccessInfo getMemoryAccessInfo() {
      return this.Gf;
   }

   private MemoryAccessInfo xK(long var1) {
      if (!this.Dw.isAllowUnsafeAnalysis()) {
         return MemoryAccessInfo.ACCESSES_ALL;
      } else {
         anj var3 = new anj(this);
         MemoryAccessInfo var4 = var3.q();
         if (var4 != null) {
            return var4;
         } else {
            IdRanges var5 = null;
            boolean var6 = true;
            int[] var7 = new int[1];
            INativeDecompilerExtensionsManager var9 = this.Dw.getDecompiler() == null ? null : this.Dw.getDecompiler().getExtensionsManager();

            for (IEGeneric var11 : this.io) {
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
                  if (var25 != null && var25.isPointer() && this.RF(var11)) {
                     var6 = false;
                     break;
                  }
               } else if (anj.q(var11, var7)) {
                  if (var5 == null) {
                     var5 = new IdRanges();
                  }

                  int var27 = var7[0];
                  if (var27 < 0) {
                     var5.addAll(this.Dw.getStackVariables(var27, 0));
                  } else {
                     var5.addAll(this.Dw.getStackVariables(var27, var27 + 1048576));
                  }
               } else if (!anj.q(var11) && (!(var11 instanceof IEMem) || !var11.hasFlags(256))) {
                  IEVar var8;
                  if ((var8 = ano.q(var11)) != null && var8.isStackReference()) {
                     if (var5 == null) {
                        var5 = new IdRanges();
                     }

                     var5.addAll(this.Dw.getStackVariables(-1048576, 0));
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
                  Collection var14 = this.Dw.getRoutineVariablesInRange(33554432, Integer.MAX_VALUE);
                  if (!var14.isEmpty()) {
                     long var15 = var24.intValue();
                     Integer var17 = this.Dw.getConverter().determineStackBytesUsedByCall(this.zz, this.oQ);
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

   private boolean RF(IEGeneric var1) {
      try {
         long var2 = var1.evaluateAddress(null);
         if (var2 == 0L) {
            return false;
         }

         if (this.Dw.getNativeContext() instanceof INativeCodeUnit) {
            INativeCodeUnit var4 = (INativeCodeUnit)this.Dw.getNativeContext();
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
      if (this.Me == null && this.JY != null && this.JY.getType() != null) {
         IWildcardType var2 = this.JY.getType();
         if (var2.getNativeType() instanceof IReferenceType && ((IReferenceType)var2.getNativeType()).getPointedType() instanceof IPrototypeItem var4) {
            this.zz = var1.getWildcardTypeManager().createPrototype(var4);
         }
      }

      int var6 = 0;

      for (IEGeneric var9 : this.io) {
         IWildcardType var5 = this.zz.getParameterType(var6);
         if (var5 != null) {
            var9.setType(var5, var1);
         }

         var6++;
      }

      if (this.LK.size() == 1) {
         IWildcardType var8 = this.zz.getReturnType();
         if (var8 != null) {
            ((IEGeneric)this.LK.get(0)).setType(var8, var1);
         }
      }

      if (this.HF != null) {
         this.HF.setType(var1.getWildcardTypeManager().create("void*"));
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      if (this.Me == null && this.JY != null && !EUtil.hasTypeInfo(this.JY)) {
         this.JY.updateTypes(var1);
      }

      for (IEGeneric var3 : this.io) {
         var3.updateTypes(var1);
      }

      for (IEGeneric var6 : this.LK) {
         var6.updateTypes(var1);
      }

      for (IEGeneric var7 : this.Hk) {
         var7.updateTypes(var1);
      }

      if (this.HF != null) {
         this.HF.updateTypes(var1);
      }
   }

   @Override
   public void postUpdateTypes(ETypeInfo var1) {
      for (int var2 = 0; var2 < this.io.size(); var2++) {
         IEGeneric var3 = (IEGeneric)this.io.get(var2);
         if (var3 instanceof IEImm) {
            IWildcardType var4 = this.zz.getParameterType(var2);
            if (var4 != null) {
               IWildcardType var5 = var3.getType();
               if (var5 != null && !var5.equals(var4)) {
                  IEImm var6 = (IEImm)var3;
                  IEImm var7 = var6.duplicateWithType(var4);
                  this.io.set(var2, var7);
               }
            }
         }
      }

      if (this.LK.size() == 1) {
         IEGeneric var8 = (IEGeneric)this.LK.get(0);
         if (var8 instanceof IEImm) {
            IWildcardType var10 = this.zz.getReturnType(0);
            if (var10 != null) {
               IWildcardType var12 = var8.getType();
               if (var12 != null && !var12.equals(var10)) {
                  IEImm var14 = (IEImm)var8;
                  IEImm var16 = var14.duplicateWithType(var10);
                  this.LK.set(0, var16);
               }
            }
         }
      }

      if (this.HF instanceof IEImm) {
         IWildcardType var9 = var1.getWildcardTypeManager().create("void*");
         IWildcardType var11 = this.HF.getType();
         if (var11 != null && !var11.equals(var9)) {
            IEImm var13 = (IEImm)this.HF;
            IEImm var15 = var13.duplicateWithType(var9);
            this.HF = var15;
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
            INativeMethodItem var4 = this.Dw.getNativeContext().getRoutine(var3.getValueAsAddress());
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
                  var15 = this.Dw.convertIntermediateOffset(var17);
               }

               if (var15 != null) {
                  var5.setReturnAddress(this.Dw.getConverter().sanitizeNativeAddress(var15));
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
                  var18 = this.Dw.createAssign(var19, var18).evaluate(var1, true);
               }

               int var20 = var1.getVirtualPC() + this.getSize();
               var1.setVirtualPC(var20);
               Long var21 = this.Dw.convertIntermediateOffset(var20);
               if (var21 == null) {
                  var1.removeValue(this.Dw.getProgramCounterId());
               } else {
                  var1.setValue(this.Dw.getProgramCounterId(), var21);
               }

               return var18;
            }
         }
      }
   }

   public aln Dw() {
      aln var1 = new aln(this);
      return (aln)super.q(var1);
   }

   @Override
   public ICStatement generateC(IERoutineContext var1, ICMethod var2) {
      Assert.a(var1 == this.Dw, "Illegal IR context");
      ICElementFactory var3 = var2.getElementFactory();
      ICTypeFactory var4 = var2.getTypeFactory();
      ICOperatorFactory var5 = var2.getOperatorFactory();
      ArrayList var6 = new ArrayList();
      int var7 = 0;

      for (IEGeneric var9 : this.io) {
         Object var10 = (ICExpression)var9.generateC(var1, var2);
         IWildcardType var11 = this.zz.getParameterType(var7);
         if (var11 != null && EUtil.hasTypeInfo(var9) && EUtil.requiresExplicitCast(var11, var9.getType())) {
            ICType var12 = var4.create(var11);
            var10 = var3.createCast(var12, (ICExpression)var10);
         }

         var6.add(var10);
         var7++;
      }

      Object var18 = null;
      ICOperator var19 = null;
      if (!this.isReturnExpressionUnused() && !this.LK.isEmpty()) {
         if (this.LK.size() == 1) {
            IEGeneric var20 = (IEGeneric)this.LK.get(0);
            if (var20 instanceof IEVar) {
               var18 = (ICLeftExpression)((amy)var20).generateC(var1, var2, 1);
               IWildcardType var23 = this.zz.getReturnType();
               if (var23 != null && EUtil.hasTypeInfo(var20) && EUtil.requiresExplicitCast(var20.getType(), var23)) {
                  ICType var26 = var4.create(var20.getType());
                  var19 = var5.createCastOperator(var26);
               }
            } else {
               var18 = (ICLeftExpression)var20.generateC(var1, var2);
            }
         } else {
            ArrayList var21 = new ArrayList();

            for (IEGeneric var27 : this.LK) {
               if (var27 instanceof IEVar) {
                  var21.add((ICExpression)((amy)var27).generateC(var1, var2, this.LK.size() == 1 ? 1 : 0));
               } else {
                  var21.add((ICExpression)var27.generateC(var1, var2));
               }
            }

            var18 = var3.createTuple(var21);
         }
      }

      afb var25 = (afb)var2.getGlobalContext().getMethodFactory();
      ICCall var22;
      if (this.Me != null) {
         IWildcardPrototype var28 = var1.getGlobalContext().getCandidatePrototype(this.Me);
         agp var13 = var25.q(this.Me, var28, false);
         var22 = var3.createCall(var13, var6);
      } else {
         ArrayList var29 = new ArrayList();
         if (this.PV != null) {
            for (IEVar var14 : this.PV) {
               INativeMethodItem var15 = var1.getGlobalContext().retrieveRoutineFromSymbol(var14);
               if (var15 != null) {
                  IWildcardPrototype var16 = var1.getGlobalContext().getCandidatePrototype(var15);
                  agp var17 = var25.q(var15, var16, false);
                  var29.add(var17);
               }
            }
         }

         ICExpression var31 = (ICExpression)this.JY.generateC(var1, var2);
         var22 = var3.createCall(var31, var6, var29);
      }

      if (Booleans.isTrue(this.getNonReturning())) {
         ((afm)var22).xK(true);
      } else if (!this.q()) {
         ((afm)var22).RF(true);
      }

      if (var18 == null) {
         return (ICStatement)this.q(var22);
      } else {
         return var19 == null
            ? (ICStatement)this.q(var3.createAssignment((ICLeftExpression)var18, var22))
            : (ICStatement)this.q(var3.createAssignment((ICLeftExpression)var18, var3.createOperation(var19, var22)));
      }
   }

   @Override
   public void q(and var1) {
      var1.appendKeyword("call");
      var1.append(" ");
      var1.q(this.JY);
      if (this.PV != null && !this.PV.isEmpty()) {
         var1.brace();
         int var2 = 0;

         for (IEVar var4 : this.PV) {
            if (var2 >= 1) {
               var1.append("|");
            }

            var1.q(var4);
            var2++;
         }

         var1.braceClose();
      }

      var1.paren();
      int var5 = 0;

      for (IEGeneric var9 : this.io) {
         if (var5++ >= 1) {
            var1.append(", ");
         }

         var1.q(var9);
      }

      var1.parenClose();
      var1.append("->");
      var1.paren();
      var5 = 0;

      for (IEGeneric var10 : this.LK) {
         if (var5++ >= 1) {
            var1.append(", ");
         }

         var1.q(var10);
      }

      var1.parenClose();
      var1.brace();
      if (this.HF != null) {
         var1.q(this.HF);
      }

      var1.braceClose();
   }
}
