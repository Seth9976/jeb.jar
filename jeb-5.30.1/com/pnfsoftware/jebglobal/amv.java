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
public class amv extends amp implements IEUntranslatedInstruction {
   @SerId(1)
   private long RF;
   @SerId(2)
   private String xK;
   @SerId(3)
   private List lm;
   @SerId(4)
   private List zz = new ArrayList();
   @SerId(5)
   private Set JY = new HashSet();
   @SerId(6)
   private Set HF = new HashSet();
   @SerId(7)
   private Set LK = new HashSet();
   @SerId(8)
   MemoryAccessInfo q;
   @SerId(9)
   private IFlowInformation io;
   @SerId(10)
   private IFlowInformation qa;
   @SerId(11)
   private IFlowInformation Hk;
   @SerId(12)
   private Object Me;
   @SerId(13)
   private Map PV;

   public amv(IERoutineContext var1, long var2, String var4, IEGeneric... var5) {
      this(var1, var2, var4, new ArrayList(Arrays.asList(var5)));
   }

   public amv(IERoutineContext var1, long var2, String var4, List var5) {
      super(var1);
      this.RF = var2;
      this.xK = var4;
      this.lm = var5;
   }

   @Override
   public void setTag(Object var1) {
      this.Me = var1;
   }

   @Override
   public Object getTag() {
      return this.Me;
   }

   @Override
   public void setTag(String var1, Object var2) {
      if (var1 == null) {
         this.setTag(var2);
      } else if (var2 == null) {
         if (this.PV == null) {
            return;
         }

         this.PV.remove(var1);
         if (this.PV.isEmpty()) {
            this.PV = null;
         }
      } else {
         if (this.PV == null) {
            this.PV = new HashMap();
         }

         this.PV.put(var1, var2);
      }
   }

   @Override
   public Object getTag(String var1) {
      if (var1 == null) {
         return this.getTag();
      } else {
         return this.PV == null ? null : this.PV.get(var1);
      }
   }

   @Override
   public Map getTags() {
      HashMap var1 = new HashMap();
      if (this.Me != null) {
         var1.put(null, this.Me);
      }

      if (this.PV != null) {
         var1.putAll(this.PV);
      }

      return var1;
   }

   @Override
   public void setNativeAddress(long var1) {
      this.RF = var1;
   }

   @Override
   public long getNativeAddress() {
      return this.RF;
   }

   @Override
   public void setNativeMnemonic(String var1) {
      this.xK = var1;
   }

   @Override
   public String getNativeMnemonic() {
      return this.xK;
   }

   public String q() {
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
      this.q = var1;
   }

   @Override
   public MemoryAccessInfo getMemoryAccessInfo() {
      return this.q;
   }

   @Override
   public void addSideEffectDefinedVariable(IEVar... var1) {
      for (IEVar var5 : var1) {
         this.JY.add(var5);
      }
   }

   @Override
   public Set getSideEffectDefinedVariables() {
      return this.JY;
   }

   @Override
   public void addSideEffectUsedVariable(IEVar... var1) {
      for (IEVar var5 : var1) {
         this.HF.add(var5);
      }
   }

   @Override
   public Set getSideEffectUsedVariables() {
      return this.HF;
   }

   @Override
   public void addSideEffectSpoiledVariable(IEVar... var1) {
      for (IEVar var5 : var1) {
         this.LK.add(var5);
      }
   }

   @Override
   public Set getSideEffectSpoiledVariables() {
      return this.LK;
   }

   public amv xK() {
      amv var1 = new amv(this.Dw, this.RF, this.xK, q(this.lm));
      var1.zz = q(this.zz);
      var1.Me = this.Me;
      var1.PV = this.PV == null ? null : new HashMap(this.PV);
      var1.q = this.q == null ? null : this.q.clone();
      var1.HF = new HashSet(this.HF);
      var1.JY = new HashSet(this.JY);
      var1.LK = new HashSet(this.LK);
      var1.io = this.io;
      var1.qa = this.qa;
      var1.Hk = this.Hk;
      this.q(var1);
      return var1;
   }

   @Override
   public String getMnemonic() {
      return "untranslated";
   }

   public IEGeneric[] Dw() {
      return (IEGeneric[])this.lm.toArray(new IEGeneric[this.lm.size()]);
   }

   @Override
   public void setParameterExpressions(Collection var1) {
      if (this.lm == null) {
         this.lm = new ArrayList();
      } else {
         this.lm.clear();
      }

      this.lm.addAll(var1);
   }

   @Override
   public void setParameterExpressions(IEGeneric... var1) {
      this.setParameterExpressions(Arrays.asList(var1));
   }

   @Override
   public List getParameterExpressions() {
      return this.lm;
   }

   @Override
   public IEGeneric getParameterExpression(int var1) {
      return (IEGeneric)this.lm.get(var1);
   }

   @Override
   public void setReturnExpression(IEGeneric var1) {
      this.zz.clear();
      if (var1 != null) {
         this.zz.add(var1);
      }
   }

   @Override
   public void setReturnExpressions(IEGeneric... var1) {
      this.setReturnExpressions(Arrays.asList(var1));
   }

   @Override
   public void setReturnExpressions(List var1) {
      this.zz = new ArrayList(var1);
   }

   @Override
   public IEGeneric getReturnExpression() {
      return this.zz.isEmpty() ? null : (IEGeneric)this.zz.get(0);
   }

   @Override
   public List getReturnExpressions() {
      return this.zz;
   }

   @Override
   public void setBreakingFlow(IFlowInformation var1) {
      this.io = var1;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      return (IFlowInformation)(this.io != null ? this.io : FlowInformation.NONE);
   }

   @Override
   public void setRoutineCall(IFlowInformation var1) {
      this.qa = var1;
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      return (IFlowInformation)(this.qa != null ? this.qa : FlowInformation.NONE);
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public void getDefUse(EDefUseInfo var1) {
      for (IEGeneric var3 : this.lm) {
         var3.getUsed(var1);
      }

      for (IEGeneric var6 : this.zz) {
         var6.getDefinedOrUsedAsDestination(var1);
      }

      var1.addUsed(this.HF);
      var1.addDefined(this.JY);
      if (var1.shouldCollectSpoiled()) {
         var1.addSpoiled(this.LK);
      }

      if (var1.shouldCollectPotentials()) {
         MemoryAccessInfo var5 = this.q;
         var1.getInstructionAddress();
         if (var5 == null) {
            var5 = MemoryAccessInfo.ACCESSES_NONE;
         }

         if (var5.isAccessStack()) {
            Collection var7 = this.Dw.getStackVariables();
            var1.addPotentialUsed(var5.filterStackReads(var7));
            var1.addPotentialDefined(var5.filterStackWrites(var7));
         }

         if (var5.isAccessGlobals()) {
            Collection var8 = this.Dw.getGlobalContext().getGlobalVariables();
            var1.addPotentialUsed(var5.filterGlobalsReads(var8));
            var1.addPotentialDefined(var5.filterGlobalsWrites(var8));
         }
      }
   }

   @Override
   public void getExplicitlyUsed(EDefUseInfo var1) {
      for (IEGeneric var3 : this.lm) {
         var3.getUsed(var1);
      }

      for (IEGeneric var5 : this.zz) {
         var5.getDefinedOrUsedAsDestination(var1);
      }
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2, boolean var3) throws IllegalIntermediateExpressionException {
      int var4 = 0;

      for (int var5 = 0; var5 < this.lm.size(); var5++) {
         IEGeneric var6 = (IEGeneric)this.lm.get(var5);
         if (var6 == var1) {
            RF(var6, var2);
            this.lm.set(var5, var2.duplicate());
            var4++;
         } else {
            var4 += var6.replaceVar(var1, var2);
         }
      }

      for (int var7 = 0; var7 < this.zz.size(); var7++) {
         IEGeneric var9 = (IEGeneric)this.zz.get(var7);
         if (!var3 || var9 instanceof amc || var9 instanceof IEGroupElt) {
            if (var9 == var1) {
               RF(var9, var2);
               this.zz.set(var7, var2.duplicate());
               var4++;
            } else {
               var4 += var9.replaceVar(var1, var2);
            }
         }
      }

      if (var2 instanceof IEVar var8) {
         if (this.HF.remove(var1)) {
            this.HF.add(var8);
            var4++;
         }

         if (!var3) {
            if (this.JY.remove(var1)) {
               this.JY.add(var8);
               var4++;
            }

            if (this.LK.remove(var1)) {
               this.LK.add(var8);
               var4++;
            }
         }
      }

      return var4;
   }

   @Override
   public int replaceDefinedVar(IEVar var1, IEGeneric var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < this.zz.size(); var4++) {
         IEGeneric var5 = (IEGeneric)this.zz.get(var4);
         if (var5 == var1) {
            RF(var5, var2);
            this.zz.set(var4, var2);
            var3++;
         }
      }

      int var8 = 0;
      if (var2 instanceof IEVar) {
         ArrayList var9 = new ArrayList(this.JY);

         for (int var6 = 0; var6 < var9.size(); var6++) {
            IEVar var7 = (IEVar)var9.get(var6);
            if (var7 == var1) {
               RF(var7, var2);
               var9.set(var6, (IEVar)var2);
               var8++;
            }
         }

         if (var8 > 0) {
            this.JY.clear();
            this.JY.addAll(var9);
         }
      }

      return var3 + var8;
   }

   @Override
   public ICStatement generateC(IERoutineContext var1, ICMethod var2) {
      Assert.a(var1 == this.Dw, "Illegal IR context");
      ICStatement var3 = var1.getConverter().generateASTForUntranslatedIR(this, var1, var2);
      if (var3 != null) {
         return (ICStatement)this.q(var3);
      } else {
         ICElementFactory var4 = var2.getElementFactory();
         ICCustomStatement var5 = var4.createNativeStatement(this.RF);

         try {
            var5.setCommandName(this.q());
            ArrayList var6 = new ArrayList();

            for (IEGeneric var8 : this.lm) {
               var6.add(var8.generateC(var1, var2));
            }

            var5.setInputElements(var6);
            ArrayList var11 = new ArrayList();

            for (IEGeneric var9 : this.getReturnExpressions()) {
               if (var9 instanceof amy) {
                  var11.add(((amy)var9).generateC(var1, var2, 1));
               } else {
                  var11.add(var9.generateC(var1, var2));
               }
            }

            var5.setOutputElements(var11);
         } catch (Exception var10) {
            JebCoreService.notifySilentExceptionToClient(var10);
            var5 = var4.createNativeStatement(this.RF);
         }

         return (ICStatement)this.q(var5);
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
         for (IEGeneric var2 : this.lm) {
            if (var2.accessesMemory()) {
               return true;
            }
         }

         for (IEGeneric var4 : this.zz) {
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
         var1.addAll(this.lm);
         var1.addAll(this.zz);
         var1.addAll(this.JY);
         var1.addAll(this.HF);
         var1.addAll(this.LK);
      } else if (var2) {
         var1.addAll(this.lm);
         var1.addAll(this.HF);
      } else {
         var1.addAll(this.zz);
         var1.addAll(this.JY);
         var1.addAll(this.LK);
      }
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
      this.lm.forEach(var2 -> var1.add(new Couple(this, var2)));
      this.zz.forEach(var2 -> RF(this, var2, var1));
      this.HF.forEach(var2 -> var1.add(new Couple(this, var2)));
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      q(var1, var2);

      for (int var3 = 0; var3 < this.lm.size(); var3++) {
         IEGeneric var4 = (IEGeneric)this.lm.get(var3);
         if (var4 == var1) {
            RF(var4, var2);
            this.lm.set(var3, var2);
            return true;
         }
      }

      for (int var5 = 0; var5 < this.zz.size(); var5++) {
         IEGeneric var7 = (IEGeneric)this.zz.get(var5);
         if (var7 == var1) {
            RF(var7, var2);
            this.zz.set(var5, var2);
            return true;
         }
      }

      if (var1 instanceof IEVar && var2 instanceof IEVar) {
         IEVar var6 = (IEVar)var1;
         IEVar var8 = (IEVar)var2;
         if (this.HF.remove(var6)) {
            this.HF.add(var8);
            return true;
         }

         if (this.JY.remove(var6)) {
            this.JY.add(var8);
            return true;
         }

         if (this.LK.remove(var6)) {
            this.LK.add(var8);
            return true;
         }
      }

      return false;
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      for (IEGeneric var3 : this.lm) {
         var3.updateTypes(var1);
      }

      for (IEGeneric var8 : this.zz) {
         var8.updateTypes(var1);
      }

      for (IEGeneric var9 : this.HF) {
         var9.updateTypes(var1);
      }

      for (IEGeneric var10 : this.JY) {
         var10.updateTypes(var1);
      }

      for (IEGeneric var11 : this.LK) {
         var11.updateTypes(var1);
      }
   }

   @Override
   public IEImm evaluate(EState var1) {
      IEImm var2 = this.Dw.getConverter().evaluateUntranslatedIR(this, this.Dw, var1);
      if (var2 == null) {
         if (var1.isSoftFailMode()) {
            return null;
         } else {
            throw new EvaluationException(Strings.ff("eval() fails: untranslated instruction @ %Xh", this.RF));
         }
      } else {
         return var2;
      }
   }

   @Override
   public void q(and var1) {
      var1.appendKeyword("untranslated");
      var1.paren();
      var1.append(this.xK);
      var1.append("@");
      var1.append(Long.toHexString(this.RF).toUpperCase());
      var1.append("h");
      var1.parenClose();
      var1.paren();
      int var2 = 0;

      for (IEGeneric var4 : this.lm) {
         if (var2++ >= 1) {
            var1.append(", ");
         }

         var1.q(var4);
      }

      var1.parenClose();
      var1.append("->");
      var1.paren();
      var2 = 0;

      for (IEGeneric var7 : this.zz) {
         if (var2++ >= 1) {
            var1.append(", ");
         }

         var1.q(var7);
      }

      var1.parenClose();
   }
}
