package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public class akb extends akm implements IENop {
   public akb(IERoutineContext var1) {
      super(var1);
   }

   public akb(IERoutineContext var1, IEStatement var2) {
      super(var1);
      ((akm)var2).pC(this);
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2, boolean var3, boolean var4) {
      return super.equalsEx(var1, var2, var3, var4);
   }

   @Override
   public int getBitsize() {
      throw new RuntimeException("No bitsize");
   }

   public IEGeneric[] pC() {
      return new IEGeneric[0];
   }

   @Override
   public String getMnemonic() {
      return "nop";
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public void getDefUse(EDefUseInfo var1) {
   }

   @Override
   public boolean accessesMemory() {
      return false;
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2, boolean var3) {
      return 0;
   }

   @Override
   public int replaceDefinedVar(IEVar var1, IEGeneric var2) {
      return 0;
   }

   @Override
   public void collectSubExpressions(Collection var1, Boolean var2) {
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) {
      return false;
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
   }

   public akb kS() {
      akb var1 = new akb(this.wS);
      this.pC(var1);
      return var1;
   }

   @Override
   public IEImm evaluate(EState var1) {
      int var2 = var1.getVirtualPC() + this.getSize();
      var1.setVirtualPC(var2);
      Long var3 = this.wS.convertIntermediateOffset(var2);
      if (var3 == null) {
         var1.removeValue(this.wS.getProgramCounterId());
      } else {
         var1.setValue(this.wS.getProgramCounterId(), var3);
      }

      return ajr.pC(var2, 32);
   }

   @Override
   public void pC(akz var1) {
      var1.appendKeyword("nop");
   }

   @Override
   public ICStatement generateC(IERoutineContext var1, ICMethod var2) {
      Assert.a(var1 == this.wS, "Illegal IR context");
      return null;
   }
}
