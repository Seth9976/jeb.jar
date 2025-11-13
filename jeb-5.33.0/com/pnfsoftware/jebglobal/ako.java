package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Ser
public class ako extends akm implements IESwitch {
   @SerId(1)
   IEGeneric pC;
   @SerId(2)
   List A = new ArrayList();
   @SerId(3)
   int kS;

   public ako(IERoutineContext var1, IEGeneric var2, int var3) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var2;
         this.kS = var3;
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + this.kS;
      return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true);
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
         ako var5 = (ako)var1;
         if (this.A == null) {
            if (var5.A != null) {
               return false;
            }
         } else {
            if (var5.A == null) {
               return false;
            }

            if (this.A.size() != var5.A.size()) {
               return false;
            }

            for (int var6 = 0; var6 < this.A.size(); var6++) {
               Couple var7 = (Couple)this.A.get(var6);
               Couple var8 = (Couple)var5.A.get(var6);
               if (!pC((IEGeneric)var7.getFirst(), (IEGeneric)var8.getFirst(), var2)) {
                  return false;
               }

               if (!((Integer)var7.getSecond()).equals(var8.getSecond())) {
                  return false;
               }
            }
         }

         if (this.kS != var5.kS) {
            return false;
         } else {
            if (this.pC == null) {
               if (var5.pC != null) {
                  return false;
               }
            } else if (!this.pC.equalsEx(var5.pC, var2)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public IEGeneric getControlExpression() {
      return this.pC;
   }

   @Override
   public void setControlExpression(IEGeneric var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var1;
      }
   }

   @Override
   public boolean hasDefaultAddress() {
      return this.kS >= 0;
   }

   @Override
   public int getDefaultAddress() {
      return this.kS;
   }

   @Override
   public void setDefaultAddress(int var1) {
      this.kS = var1;
   }

   @Override
   public List getCases() {
      return this.A;
   }

   @Override
   public void addCase(IEGeneric var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (!(var1 instanceof ajr)) {
         throw new IllegalArgumentException("Only imm switch keys (EImm) are supported");
      } else {
         this.A.add(new Couple(var1, var2));
      }
   }

   @Override
   public Integer removeCase(IEGeneric var1) {
      for (int var2 = 0; var2 < this.A.size(); var2++) {
         Couple var3 = (Couple)this.A.get(var2);
         if (((IEGeneric)var3.getFirst()).equalsEx(var1, false)) {
            this.A.remove(var2);
            return (Integer)var3.getSecond();
         }
      }

      return null;
   }

   @Override
   public int getCountsToTarget(int var1, boolean var2) {
      int var3 = 0;

      for (Couple var5 : this.A) {
         if ((Integer)var5.getSecond() == var1) {
            var3++;
         }
      }

      if (var2 && var1 == this.kS) {
         var3++;
      }

      return var3;
   }

   @Override
   public boolean isPossibleTarget(int var1) {
      for (Couple var3 : this.A) {
         if ((Integer)var3.getSecond() == var1) {
            return true;
         }
      }

      return var1 == this.kS;
   }

   @Override
   public String getMnemonic() {
      return "switch";
   }

   public IEGeneric[] pC() {
      throw new RuntimeException();
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      FlowInformation var3 = new FlowInformation();
      HashSet var4 = new HashSet();
      if (this.kS >= 0) {
         var4.add(this.kS);
      }

      for (Couple var6 : this.A) {
         var4.add((Integer)var6.getSecond());
      }

      int var8 = (int)(var1 + this.getSize());
      if (var4.contains(var8)) {
         var3.addTarget(new CodePointer(var8));
         var4.remove(var8);
      }

      for (int var7 : var4) {
         var3.addTarget(new CodePointer(var7));
      }

      return var3;
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
      this.pC.getUsed(var1);

      for (Couple var3 : this.A) {
         ((IEGeneric)var3.getFirst()).getUsed(var1);
      }
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2, boolean var3) throws IllegalIntermediateExpressionException {
      int var4 = 0;
      if (this.pC == var1) {
         A(this.pC, var2);
         this.pC = var2.duplicate();
         var4++;
      } else {
         var4 += this.pC.replaceVar(var1, var2);
      }

      for (Couple var6 : this.A) {
         IEGeneric var7 = (IEGeneric)var6.getFirst();
         if (var7 == var1) {
            A(var7, var2);
            var6.setFirst(var2.duplicate());
            var4++;
         } else {
            var4 += var7.replaceVar(var1, var2);
         }
      }

      return var4;
   }

   @Override
   public int replaceDefinedVar(IEVar var1, IEGeneric var2) {
      return 0;
   }

   @Override
   public int getBitsize() {
      throw new RuntimeException("No bitsize");
   }

   @Override
   public boolean accessesMemory() {
      if (this.pC.accessesMemory()) {
         return true;
      } else {
         for (Couple var2 : this.A) {
            if (((IEGeneric)var2.getFirst()).accessesMemory()) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public void collectSubExpressions(Collection var1, Boolean var2) {
      if (var2 == null || var2) {
         var1.add(this.pC);
         var1.addAll(Couple.getFirstElements(this.A));
      }
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
      var1.add(new Couple(this, this.pC));

      for (Couple var3 : this.A) {
         var1.add(new Couple(this, (IEGeneric)var3.getFirst()));
      }
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      pC(var1, var2);
      if (var1 == this.pC) {
         A(this.pC, var2);
         this.pC = var2;
         return true;
      } else {
         for (Couple var4 : this.A) {
            if (var1 == var4.getFirst()) {
               A((IEGeneric)var4.getFirst(), var2);
               var4.setFirst(var2);
               return true;
            }
         }

         return false;
      }
   }

   public ako kS() {
      ArrayList var1 = new ArrayList();

      for (Couple var3 : this.A) {
         var1.add(new Couple(((IEGeneric)var3.getFirst()).duplicate(), (Integer)var3.getSecond()));
      }

      ako var4 = new ako(this.wS, this.pC.duplicate(), this.kS);
      var4.A = var1;
      this.pC(var4);
      return var4;
   }

   @Override
   public void pC(akz var1) {
      var1.appendKeyword("switch ");
      var1.pC(this.pC);
      var1.append(" (");
      var1.pC(this.kS);
      var1.append(") : ");
      Map var2 = this.wS();
      int var3 = 0;

      for (Entry var5 : var2.entrySet()) {
         if (var3 >= 1) {
            var1.append(", ");
         }

         var1.brace();
         if (((List)var5.getValue()).size() == 1) {
            var1.pC((IEGeneric)((List)var5.getValue()).get(0));
         } else {
            var1.pC((Collection)var5.getValue());
         }

         var1.append(" -> ");
         var1.pC((Integer)var5.getKey());
         var1.braceClose();
         var3++;
      }
   }

   private Map wS() {
      LinkedHashMap var1 = new LinkedHashMap();

      for (Couple var3 : this.A) {
         Integer var4 = (Integer)var3.getSecond();
         Object var5 = (List)var1.get(var4);
         if (var5 == null) {
            var5 = new ArrayList();
            var1.put(var4, var5);
         }

         var5.add((IEGeneric)var3.getFirst());
      }

      return var1;
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      this.pC.updateTypes(var1);
   }

   @Override
   public IEImm evaluate(EState var1) {
      IEImm var2 = this.pC.evaluate(var1);
      if (var2 == null) {
         return null;
      } else {
         int var3 = this.getDefaultAddress();

         for (Couple var5 : this.A) {
            if (((IEGeneric)var5.getFirst()).equalsEx(var2, false)) {
               var3 = (Integer)var5.getSecond();
               break;
            }
         }

         var1.setVirtualPC(var3);
         Long var6 = this.wS.convertIntermediateOffset(var1.getVirtualPC());
         if (var6 == null) {
            var1.removeValue(this.wS.getProgramCounterId());
         } else {
            var1.setValue(this.wS.getProgramCounterId(), var6);
         }

         return ajr.pC(var3, 32);
      }
   }

   @Override
   public ICStatement generateC(IERoutineContext var1, ICMethod var2) {
      Assert.a(var1 == this.wS, "Illegal IR context");
      ICElementFactory var3 = var2.getElementFactory();
      ICExpression var4 = (ICExpression)this.pC.generateC(var1, var2);
      ICSwitchStm var5 = var3.createSwitchStm(var4);
      Map var6 = this.wS();

      for (Entry var8 : var6.entrySet()) {
         ArrayList var9 = new ArrayList(((List)var8.getValue()).size());

         for (IEGeneric var11 : (List)var8.getValue()) {
            BigInteger var12 = ((IEImm)var11).getValue();
            var9.add(var12);
         }

         int var15 = (Integer)var8.getKey();
         ICLabel var16 = var2.getLabelFactory().create(var15);
         ICBlock var17 = var3.createBlock(var3.createGoto(var16));
         var5.addCase(var9, var17);
      }

      ICBlock var13;
      if (this.kS < 0) {
         var13 = var3.createBlock(var3.createThrow(var2.getConstantFactory().createInt32(0)));
      } else {
         ICLabel var14 = var2.getLabelFactory().create(this.kS);
         var13 = var3.createBlock(var3.createGoto(var14));
      }

      var5.setDefaultBlock(var13);
      return (ICStatement)this.pC(var5);
   }
}
