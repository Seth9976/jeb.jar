package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCustomStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroupElt;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.MemoryAccessInfo;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class aks extends akm implements IEUntranslatedInstruction {
   @SerId(1)
   private long A;
   @SerId(2)
   private String kS;
   @SerId(3)
   private List oT;
   @SerId(4)
   private List fI = new ArrayList();
   @SerId(5)
   private Set WR = new HashSet();
   @SerId(6)
   private Set NS = new HashSet();
   @SerId(7)
   private Set vP = new HashSet();
   @SerId(8)
   MemoryAccessInfo pC;
   @SerId(9)
   private IFlowInformation xC;
   @SerId(10)
   private IFlowInformation ED;
   @SerId(11)
   private IFlowInformation Sc;
   @SerId(12)
   private Object ah;
   @SerId(13)
   private Map eP;
   @SerId(14)
   private Collection UO;

   public aks(IERoutineContext var1, long var2, String var4, IEGeneric... var5) {
      this(var1, var2, var4, new ArrayList(Arrays.asList(var5)));
   }

   public aks(IERoutineContext var1, long var2, String var4, List var5) {
      super(var1);
      this.A = var2;
      this.kS = var4;
      this.oT = var5;
   }

   @Override
   public void setTag(Object var1) {
      this.ah = var1;
   }

   @Override
   public Object getTag() {
      return this.ah;
   }

   @Override
   public void setTag(String var1, Object var2) {
      if (var1 == null) {
         this.setTag(var2);
      } else if (var2 == null) {
         if (this.eP == null) {
            return;
         }

         this.eP.remove(var1);
         if (this.eP.isEmpty()) {
            this.eP = null;
         }
      } else {
         if (this.eP == null) {
            this.eP = new HashMap();
         }

         this.eP.put(var1, var2);
      }
   }

   @Override
   public Object getTag(String var1) {
      if (var1 == null) {
         return this.getTag();
      } else {
         return this.eP == null ? null : this.eP.get(var1);
      }
   }

   @Override
   public Map getTags() {
      HashMap var1 = new HashMap();
      if (this.ah != null) {
         var1.put(null, this.ah);
      }

      if (this.eP != null) {
         var1.putAll(this.eP);
      }

      return var1;
   }

   @Override
   public void setNativeAddress(long var1) {
      this.A = var1;
   }

   @Override
   public long getNativeAddress() {
      return this.A;
   }

   @Override
   public void setNativeMnemonic(String var1) {
      this.kS = var1;
   }

   @Override
   public String getNativeMnemonic() {
      return this.kS;
   }

   public String pC() {
      String var1 = (String)this.getTag("CUSTOM_NAME");
      return var1 != null ? var1 : this.getNativeMnemonic();
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2, boolean var3, boolean var4) {
      return var1 == this;
   }

   @Override
   public void setMemoryAccessInfo(MemoryAccessInfo var1) {
      this.pC = var1;
   }

   @Override
   public MemoryAccessInfo getMemoryAccessInfo() {
      return this.pC;
   }

   @Override
   public void addSideEffectDefinedVariable(IEVar... var1) {
      for (IEVar var5 : var1) {
         this.WR.add(var5);
      }
   }

   @Override
   public Set getSideEffectDefinedVariables() {
      return this.WR;
   }

   @Override
   public void addSideEffectUsedVariable(IEVar... var1) {
      for (IEVar var5 : var1) {
         this.NS.add(var5);
      }
   }

   @Override
   public Set getSideEffectUsedVariables() {
      return this.NS;
   }

   @Override
   public void addSideEffectSpoiledVariable(IEVar... var1) {
      for (IEVar var5 : var1) {
         this.vP.add(var5);
      }
   }

   @Override
   public Set getSideEffectSpoiledVariables() {
      return this.vP;
   }

   public aks kS() {
      aks var1 = new aks(this.wS, this.A, this.kS, pC(this.oT));
      var1.fI = pC(this.fI);
      var1.ah = this.ah;
      var1.eP = this.eP == null ? null : new HashMap(this.eP);
      var1.pC = this.pC == null ? null : this.pC.clone();
      var1.NS = new HashSet(this.NS);
      var1.WR = new HashSet(this.WR);
      var1.vP = new HashSet(this.vP);
      var1.xC = this.xC;
      var1.ED = this.ED;
      var1.Sc = this.Sc;
      this.pC(var1);
      return var1;
   }

   @Override
   public String getMnemonic() {
      return "untranslated";
   }

   public IEGeneric[] wS() {
      return (IEGeneric[])this.oT.toArray(new IEGeneric[this.oT.size()]);
   }

   @Override
   public void setParameterExpressions(Collection var1) {
      if (this.oT == null) {
         this.oT = new ArrayList();
      } else {
         this.oT.clear();
      }

      this.oT.addAll(var1);
   }

   @Override
   public void setParameterExpressions(IEGeneric... var1) {
      this.setParameterExpressions(Arrays.asList(var1));
   }

   @Override
   public List getParameterExpressions() {
      return this.oT;
   }

   @Override
   public IEGeneric getParameterExpression(int var1) {
      return (IEGeneric)this.oT.get(var1);
   }

   @Override
   public void setReturnExpression(IEGeneric var1) {
      this.fI.clear();
      if (var1 != null) {
         this.fI.add(var1);
      }
   }

   @Override
   public void setReturnExpressions(IEGeneric... var1) {
      this.setReturnExpressions(Arrays.asList(var1));
   }

   @Override
   public void setReturnExpressions(List var1) {
      this.fI = new ArrayList(var1);
   }

   @Override
   public IEGeneric getReturnExpression() {
      return this.fI.isEmpty() ? null : (IEGeneric)this.fI.get(0);
   }

   @Override
   public List getReturnExpressions() {
      return this.fI;
   }

   @Override
   public Collection getInstructionFlags() {
      return this.UO != null ? this.UO : super.getInstructionFlags();
   }

   @Override
   public void setInstructionFlags(Collection var1) {
      this.UO = var1;
   }

   @Override
   public void setBreakingFlow(IFlowInformation var1) {
      this.xC = var1;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      return (IFlowInformation)(this.xC != null ? this.xC : FlowInformation.NONE);
   }

   @Override
   public void setRoutineCall(IFlowInformation var1) {
      this.ED = var1;
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      return (IFlowInformation)(this.ED != null ? this.ED : FlowInformation.NONE);
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public void getDefUse(EDefUseInfo var1) {
      for (IEGeneric var3 : this.oT) {
         var3.getUsed(var1);
      }

      for (IEGeneric var6 : this.fI) {
         var6.getDefinedOrUsedAsDestination(var1);
      }

      var1.addUsed(this.NS);
      var1.addDefined(this.WR);
      if (var1.shouldCollectSpoiled()) {
         var1.addSpoiled(this.vP);
      }

      if (var1.shouldCollectPotentials()) {
         MemoryAccessInfo var5 = this.pC;
         var1.getInstructionAddress();
         if (var5 == null) {
            var5 = MemoryAccessInfo.ACCESSES_NONE;
         }

         if (var5.isAccessStack()) {
            Collection var7 = this.wS.getStackVariables();
            var1.addPotentialUsed(var5.filterStackReads(var7));
            var1.addPotentialDefined(var5.filterStackWrites(var7));
         }

         if (var5.isAccessGlobals()) {
            Collection var8 = this.wS.getGlobalContext().getGlobalVariables();
            var1.addPotentialUsed(var5.filterGlobalsReads(var8));
            var1.addPotentialDefined(var5.filterGlobalsWrites(var8));
         }
      }
   }

   @Override
   public void getExplicitlyUsed(EDefUseInfo var1) {
      for (IEGeneric var3 : this.oT) {
         var3.getUsed(var1);
      }

      for (IEGeneric var5 : this.fI) {
         var5.getDefinedOrUsedAsDestination(var1);
      }
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2, boolean var3) throws IllegalIntermediateExpressionException {
      int var4 = 0;

      for (int var5 = 0; var5 < this.oT.size(); var5++) {
         IEGeneric var6 = (IEGeneric)this.oT.get(var5);
         if (var6 == var1) {
            A(var6, var2);
            this.oT.set(var5, var2.duplicate());
            var4++;
         } else {
            var4 += var6.replaceVar(var1, var2);
         }
      }

      for (int var7 = 0; var7 < this.fI.size(); var7++) {
         IEGeneric var9 = (IEGeneric)this.fI.get(var7);
         if (!var3 || var9 instanceof ajz || var9 instanceof IEGroupElt) {
            if (var9 == var1) {
               A(var9, var2);
               this.fI.set(var7, var2.duplicate());
               var4++;
            } else {
               var4 += var9.replaceVar(var1, var2);
            }
         }
      }

      if (var2 instanceof IEVar var8) {
         if (this.NS.remove(var1)) {
            this.NS.add(var8);
            var4++;
         }

         if (!var3) {
            if (this.WR.remove(var1)) {
               this.WR.add(var8);
               var4++;
            }

            if (this.vP.remove(var1)) {
               this.vP.add(var8);
               var4++;
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

      int var8 = 0;
      if (var2 instanceof IEVar) {
         ArrayList var9 = new ArrayList(this.WR);

         for (int var6 = 0; var6 < var9.size(); var6++) {
            IEVar var7 = (IEVar)var9.get(var6);
            if (var7 == var1) {
               A(var7, var2);
               var9.set(var6, (IEVar)var2);
               var8++;
            }
         }

         if (var8 > 0) {
            this.WR.clear();
            this.WR.addAll(var9);
         }
      }

      return var3 + var8;
   }

   @Override
   public ICStatement generateC(IERoutineContext var1, ICMethod var2) {
      Assert.a(var1 == this.wS, "Illegal IR context");
      ICStatement var3 = var1.getConverter().generateASTForUntranslatedIR(this, var1, var2);
      if (var3 != null) {
         return (ICStatement)this.pC(var3);
      } else {
         ICElementFactory var4 = var2.getElementFactory();
         ICCustomStatement var5 = var4.createNativeStatement(this.A);

         try {
            var5.setCommandName(this.pC());
            ArrayList var6 = new ArrayList();

            for (IEGeneric var8 : this.oT) {
               var6.add(var8.generateC(var1, var2));
            }

            var5.setInputElements(var6);
            ArrayList var11 = new ArrayList();

            for (IEGeneric var9 : this.getReturnExpressions()) {
               if (var9 instanceof aku) {
                  var11.add(((aku)var9).generateC(var1, var2, 1));
               } else {
                  var11.add(var9.generateC(var1, var2));
               }
            }

            var5.setOutputElements(var11);
         } catch (Exception var10) {
            JebCoreService.notifySilentExceptionToClient(var10);
            var5 = var4.createNativeStatement(this.A);
         }

         return (ICStatement)this.pC(var5);
      }
   }

   @Override
   public int getBitsize() {
      throw new RuntimeException("No bitsize");
   }

   @Override
   public boolean accessesMemory() {
      if (this.getMemoryAccessInfo() != null && this.getMemoryAccessInfo().isAccessMemory()) {
         return true;
      } else {
         for (IEGeneric var2 : this.oT) {
            if (var2.accessesMemory()) {
               return true;
            }
         }

         for (IEGeneric var4 : this.fI) {
            if (var4.accessesMemory()) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public boolean writesMemory() {
      return this.getMemoryAccessInfo() != null && this.getMemoryAccessInfo().isWriteMemory();
   }

   @Override
   public void collectSubExpressions(Collection var1, Boolean var2) {
      if (var2 == null) {
         var1.addAll(this.oT);
         var1.addAll(this.fI);
         var1.addAll(this.WR);
         var1.addAll(this.NS);
         var1.addAll(this.vP);
      } else if (var2) {
         var1.addAll(this.oT);
         var1.addAll(this.NS);
      } else {
         var1.addAll(this.fI);
         var1.addAll(this.WR);
         var1.addAll(this.vP);
      }
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
      this.oT.forEach(var2 -> var1.add(new Couple(this, var2)));
      this.fI.forEach(var2 -> A(this, var2, var1));
      this.NS.forEach(var2 -> var1.add(new Couple(this, var2)));
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      pC(var1, var2);

      for (int var3 = 0; var3 < this.oT.size(); var3++) {
         IEGeneric var4 = (IEGeneric)this.oT.get(var3);
         if (var4 == var1) {
            A(var4, var2);
            this.oT.set(var3, var2);
            return true;
         }
      }

      for (int var5 = 0; var5 < this.fI.size(); var5++) {
         IEGeneric var7 = (IEGeneric)this.fI.get(var5);
         if (var7 == var1) {
            A(var7, var2);
            this.fI.set(var5, var2);
            return true;
         }
      }

      if (var1 instanceof IEVar && var2 instanceof IEVar) {
         IEVar var6 = (IEVar)var1;
         IEVar var8 = (IEVar)var2;
         if (this.NS.remove(var6)) {
            this.NS.add(var8);
            return true;
         }

         if (this.WR.remove(var6)) {
            this.WR.add(var8);
            return true;
         }

         if (this.vP.remove(var6)) {
            this.vP.add(var8);
            return true;
         }
      }

      return false;
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      for (IEGeneric var3 : this.oT) {
         var3.updateTypes(var1);
      }

      for (IEGeneric var8 : this.fI) {
         var8.updateTypes(var1);
      }

      for (IEGeneric var9 : this.NS) {
         var9.updateTypes(var1);
      }

      for (IEGeneric var10 : this.WR) {
         var10.updateTypes(var1);
      }

      for (IEGeneric var11 : this.vP) {
         var11.updateTypes(var1);
      }
   }

   @Override
   public IEImm evaluate(EState var1) {
      IEImm var2 = this.wS.getConverter().evaluateUntranslatedIR(this, this.wS, var1);
      if (var2 == null) {
         if (var1.isSoftFailMode()) {
            return null;
         } else {
            throw new EvaluationException(Strings.ff("eval() fails: untranslated instruction @ %Xh", this.A));
         }
      } else {
         return var2;
      }
   }

   @Override
   public void pC(akz var1) {
      var1.appendKeyword("untranslated");
      var1.paren();
      var1.append(this.kS);
      var1.append("@");
      var1.append(Long.toHexString(this.A).toUpperCase());
      var1.append("h");
      var1.parenClose();
      var1.paren();
      int var2 = 0;

      for (IEGeneric var4 : this.oT) {
         if (var2++ >= 1) {
            var1.append(", ");
         }

         var1.pC(var4);
      }

      var1.parenClose();
      var1.append("->");
      var1.paren();
      var2 = 0;

      for (IEGeneric var7 : this.fI) {
         if (var2++ >= 1) {
            var1.append(", ");
         }

         var1.pC(var7);
      }

      var1.parenClose();
   }
}
