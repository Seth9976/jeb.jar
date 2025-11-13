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
public class amk extends amp implements IEReturn {
   @SerId(1)
   List q;

   public amk(IERoutineContext var1) {
      super(var1);
   }

   public amk(IERoutineContext var1, IEGeneric var2) {
      super(var1);
      if (var2 instanceof IEStatement) {
         throw new IllegalArgumentException("Currently not supporting statement as a return value");
      } else {
         if (var2 != null) {
            this.q = new ArrayList();
            this.q.add(var2);
         }
      }
   }

   public amk(IERoutineContext var1, Collection var2) {
      super(var1);
      if (var2 == null) {
         throw new IllegalArgumentException("Provide a collection of value");
      } else {
         if (!var2.isEmpty()) {
            this.q = new ArrayList(var2.size());

            for (IEGeneric var4 : var2) {
               if (var4 == null) {
                  throw new IllegalArgumentException();
               }

               this.q.add(var4);
            }
         }
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
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
         amk var5 = (amk)var1;
         if (this.q == null) {
            if (var5.q != null) {
               return false;
            }
         } else if (!q(this.q, var5.q, var2)) {
            return false;
         }

         return true;
      }
   }

   public IEGeneric[] q() {
      return this.q != null ? (IEGeneric[])this.q.toArray(new IEGeneric[this.q.size()]) : new IEGeneric[0];
   }

   @Override
   public String getMnemonic() {
      return "return";
   }

   @Override
   public IEGeneric getValue() {
      return this.q != null && !this.q.isEmpty() ? (IEGeneric)this.q.get(0) : null;
   }

   @Override
   public void setValue(IEGeneric var1) {
      if (var1 == null) {
         this.q = null;
      } else if (this.q == null) {
         this.q = new ArrayList(1);
         this.q.add(var1);
      } else if (this.q.isEmpty()) {
         this.q.add(var1);
      } else {
         this.q.set(0, var1);
      }
   }

   @Override
   public List getValues() {
      return this.q == null ? Collections.emptyList() : this.q;
   }

   @Override
   public void setValues(Collection var1) {
      if (var1 == null) {
         this.q = null;
      } else {
         this.q = new ArrayList(var1.size());
         this.q.addAll(var1);
      }
   }

   @Override
   public int getBitsize() {
      return this.q != null && !this.q.isEmpty() ? ((IEGeneric)this.q.get(0)).getBitsize() : 0;
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
      if (this.q != null) {
         for (int var5 = 0; var5 < this.q.size(); var5++) {
            IEGeneric var6 = (IEGeneric)this.q.get(var5);
            if (var6 == var1) {
               RF(var6, var2);
               this.q.set(var5, var2.duplicate());
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
         CollectionUtil.addNonNulls(var1, this.q);
      }
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
      if (this.q != null) {
         for (IEGeneric var3 : this.q) {
            if (var3 != null) {
               var1.add(new Couple(this, var3));
            }
         }
      }
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) {
      if (this.q != null && var2 != null) {
         for (int var3 = 0; var3 < this.q.size(); var3++) {
            IEGeneric var4 = (IEGeneric)this.q.get(var3);
            if (var1 == var4) {
               this.q.set(var3, var2);
               return true;
            }
         }
      }

      return false;
   }

   public amk xK() {
      amk var1 = new amk(this.Dw);
      if (this.q != null) {
         var1.q = new ArrayList(this.q.size());

         for (IEGeneric var3 : this.q) {
            var1.q.add(var3.duplicate());
         }
      }

      this.q(var1);
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
   public void q(and var1) {
      var1.appendKeyword("return");
      if (this.q != null && !this.q.isEmpty()) {
         var1.append(" ");
         int var2 = 0;

         for (IEGeneric var4 : this.q) {
            if (var2 >= 1) {
               var1.append(", ");
            }

            var1.q(var4);
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
      Assert.a(var1 == this.Dw, "Illegal IR context");
      ICElementFactory var3 = var2.getElementFactory();
      ICTypeFactory var4 = var2.getTypeFactory();
      if (this.q != null && !this.q.isEmpty()) {
         ArrayList var5 = new ArrayList(this.q.size());
         List var6 = null;
         if (var1.getPrototype() != null) {
            var6 = var1.getPrototype().getReturnTypes();
         }

         for (int var7 = 0; var7 < this.q.size(); var7++) {
            IEGeneric var8 = (IEGeneric)this.q.get(var7);
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

         return (ICStatement)this.q(var3.createReturn((ICExpression)var12));
      } else {
         return (ICStatement)this.q(var3.createReturn(null));
      }
   }
}
