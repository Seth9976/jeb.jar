package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.InstructionFlags;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
public class akh extends akm implements IEReturn {
   @SerId(1)
   List pC;

   public akh(IERoutineContext var1) {
      super(var1);
   }

   public akh(IERoutineContext var1, IEGeneric var2) {
      super(var1);
      if (var2 instanceof IEStatement) {
         throw new IllegalArgumentException("Currently not supporting statement as a return value");
      } else {
         if (var2 != null) {
            this.pC = new ArrayList();
            this.pC.add(var2);
         }
      }
   }

   public akh(IERoutineContext var1, Collection var2) {
      super(var1);
      if (var2 == null) {
         throw new IllegalArgumentException("Provide a collection of value");
      } else {
         if (!var2.isEmpty()) {
            this.pC = new ArrayList(var2.size());

            for (IEGeneric var4 : var2) {
               if (var4 == null) {
                  throw new IllegalArgumentException();
               }

               this.pC.add(var4);
            }
         }
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
         akh var5 = (akh)var1;
         if (this.pC == null) {
            if (var5.pC != null) {
               return false;
            }
         } else if (!pC(this.pC, var5.pC, var2)) {
            return false;
         }

         return true;
      }
   }

   public IEGeneric[] pC() {
      return this.pC != null ? (IEGeneric[])this.pC.toArray(new IEGeneric[this.pC.size()]) : new IEGeneric[0];
   }

   @Override
   public String getMnemonic() {
      return "return";
   }

   @Override
   public IEGeneric getValue() {
      return this.pC != null && !this.pC.isEmpty() ? (IEGeneric)this.pC.get(0) : null;
   }

   @Override
   public void setValue(IEGeneric var1) {
      if (var1 == null) {
         this.pC = null;
      } else if (this.pC == null) {
         this.pC = new ArrayList(1);
         this.pC.add(var1);
      } else if (this.pC.isEmpty()) {
         this.pC.add(var1);
      } else {
         this.pC.set(0, var1);
      }
   }

   @Override
   public List getValues() {
      return this.pC == null ? Collections.emptyList() : this.pC;
   }

   @Override
   public void setValues(Collection var1) {
      if (var1 == null) {
         this.pC = null;
      } else {
         this.pC = new ArrayList(var1.size());
         this.pC.addAll(var1);
      }
   }

   @Override
   public int getBitsize() {
      return this.pC != null && !this.pC.isEmpty() ? ((IEGeneric)this.pC.get(0)).getBitsize() : 0;
   }

   @Override
   public boolean accessesMemory() {
      for (IEGeneric var2 : this.getValues()) {
         if (var2.accessesMemory()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2, boolean var3) throws IllegalIntermediateExpressionException {
      int var4 = 0;
      if (this.pC != null) {
         for (int var5 = 0; var5 < this.pC.size(); var5++) {
            IEGeneric var6 = (IEGeneric)this.pC.get(var5);
            if (var6 == var1) {
               A(var6, var2);
               this.pC.set(var5, var2.duplicate());
               var4++;
            } else {
               var4 += var6.replaceVar(var1, var2);
            }
         }
      }

      return var4;
   }

   @Override
   public int replaceDefinedVar(IEVar var1, IEGeneric var2) {
      return 0;
   }

   @Override
   public void collectSubExpressions(Collection var1, Boolean var2) {
      if (var2 == null || var2) {
         CollectionUtil.addNonNulls(var1, this.pC);
      }
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
      if (this.pC != null) {
         for (IEGeneric var3 : this.pC) {
            if (var3 != null) {
               var1.add(new Couple(this, var3));
            }
         }
      }
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) {
      if (this.pC != null && var2 != null) {
         for (int var3 = 0; var3 < this.pC.size(); var3++) {
            IEGeneric var4 = (IEGeneric)this.pC.get(var3);
            if (var1 == var4) {
               this.pC.set(var3, var2);
               return true;
            }
         }
      }

      return false;
   }

   public akh kS() {
      akh var1 = new akh(this.wS);
      if (this.pC != null) {
         var1.pC = new ArrayList(this.pC.size());

         for (IEGeneric var3 : this.pC) {
            var1.pC.add(var3.duplicate());
         }
      }

      this.pC(var1);
      return var1;
   }

   @Override
   public void getDefUse(EDefUseInfo var1) {
      for (IEGeneric var3 : this.getValues()) {
         var3.getUsed(var1);
      }
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      return new FlowInformation();
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
   public Collection getInstructionFlags() {
      return Arrays.asList(InstructionFlags.ROUTINE_TERMINATOR);
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      for (IEGeneric var3 : this.getValues()) {
         var3.updateTypes(var1);
      }
   }

   @Override
   public void pC(akz var1) {
      var1.appendKeyword("return");
      if (this.pC != null && !this.pC.isEmpty()) {
         var1.append(" ");
         int var2 = 0;

         for (IEGeneric var4 : this.pC) {
            if (var2 >= 1) {
               var1.append(", ");
            }

            var1.pC(var4);
            var2++;
         }
      }
   }

   @Override
   public IEImm evaluate(EState var1) {
      List var3 = this.getValues();
      IEImm var2;
      if (var3.isEmpty()) {
         var2 = null;
      } else {
         if (var3.size() != 1) {
            throw new EvaluationException("unsupported");
         }

         var2 = ((IEGeneric)var3.get(0)).evaluate(var1);
      }

      if (var1.getRoutineContext() == null) {
         var1.setVirtualPC(-1);
      } else {
         var1.setVirtualPC(0);
         var1.setRoutineContext(null);
      }

      return var2;
   }

   @Override
   public ICStatement generateC(IERoutineContext var1, ICMethod var2) {
      Assert.a(var1 == this.wS, "Illegal IR context");
      ICElementFactory var3 = var2.getElementFactory();
      ICTypeFactory var4 = var2.getTypeFactory();
      if (this.pC != null && !this.pC.isEmpty()) {
         ArrayList var5 = new ArrayList(this.pC.size());
         List var6 = null;
         if (var1.getPrototype() != null) {
            var6 = var1.getPrototype().getReturnTypes();
         }

         for (int var7 = 0; var7 < this.pC.size(); var7++) {
            IEGeneric var8 = (IEGeneric)this.pC.get(var7);
            Object var9 = (ICExpression)var8.generateC(var1, var2);
            IWildcardType var10 = (IWildcardType)Lists.get(var6, var7);
            if (var10 != null && !var10.isVoid() && EUtil.hasTypeInfo(var8) && EUtil.requiresExplicitCast(var10, var8.getType())) {
               ICType var11 = var4.create(var10);
               var9 = var3.createCast(var11, (ICExpression)var9);
            }

            var5.add(var9);
         }

         Object var12;
         if (var5.size() == 1) {
            var12 = (ICExpression)var5.get(0);
         } else {
            var12 = var3.createTuple(var5);
         }

         return (ICStatement)this.pC(var3.createReturn((ICExpression)var12));
      } else {
         return (ICStatement)this.pC(var3.createReturn(null));
      }
   }
}
