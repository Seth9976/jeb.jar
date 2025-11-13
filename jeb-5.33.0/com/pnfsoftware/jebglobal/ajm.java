package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;

@Ser
public class ajm extends ala implements IECond {
   @SerId(1)
   private IEGeneric pC;
   @SerId(2)
   private IEGeneric A;
   @SerId(3)
   private IEGeneric kS;

   public ajm(IEGeneric var1, IEGeneric var2, IEGeneric var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null predicate in conditional is not allowed");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Null true-expression in conditional is not allowed");
      } else if (var3 == null) {
         throw new IllegalArgumentException("Null false-expression in conditional is not allowed");
      } else if (var2.getBitsize() != var3.getBitsize()) {
         throw new IllegalArgumentException(Strings.ff("Alternatives do not have the same bitsize: %d, %d", var2.getBitsize(), var3.getBitsize()));
      } else {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
         ajm var3 = (ajm)var1;
         if (this.A == null) {
            if (var3.A != null) {
               return false;
            }
         } else if (!this.A.equalsEx(var3.A, var2)) {
            return false;
         }

         if (this.kS == null) {
            if (var3.kS != null) {
               return false;
            }
         } else if (!this.kS.equalsEx(var3.kS, var2)) {
            return false;
         }

         if (this.pC == null) {
            if (var3.pC != null) {
               return false;
            }
         } else if (!this.pC.equalsEx(var3.pC, var2)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public IEGeneric getCondition() {
      return this.pC;
   }

   @Override
   public void setCondition(IEGeneric var1) {
      this.pC = var1;
   }

   @Override
   public IEGeneric getExpressionTrue() {
      return this.A;
   }

   @Override
   public void setExpressionTrue(IEGeneric var1) {
      A(this.A, var1);
      this.A = var1;
   }

   @Override
   public IEGeneric getExpressionFalse() {
      return this.kS;
   }

   @Override
   public void setExpressionFalse(IEGeneric var1) {
      A(this.kS, var1);
      this.kS = var1;
   }

   @Override
   public int getBitsize() {
      return this.A.getBitsize();
   }

   @Override
   public int getPriority() {
      return 20;
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      this.pC.getUsed(var1);
      this.A.getDefinedOrUsedAsDestination(var1);
      this.kS.getDefinedOrUsedAsDestination(var1);
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      this.pC.getUsed(var1);
      this.A.getUsed(var1);
      this.kS.getUsed(var1);
   }

   @Override
   public boolean accessesMemory() {
      return this.pC.accessesMemory() || this.A.accessesMemory() || this.kS.accessesMemory();
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;
      if (this.pC == var1) {
         this.pC = var2.duplicate();
         var3++;
      } else {
         var3 += this.pC.replaceVar(var1, var2);
      }

      if (this.A == var1) {
         A(this.A, var2);
         this.A = var2.duplicate();
         var3++;
      } else {
         var3 += this.A.replaceVar(var1, var2);
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
      var1.add(this.pC);
      var1.add(this.A);
      var1.add(this.kS);
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      pC(var1, var2);
      if (this.pC == var1) {
         this.pC = var2;
         return true;
      } else if (this.A == var1) {
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
      this.pC.updateTypes(var1);
      this.A.updateTypes(var1);
      this.kS.updateTypes(var1);
      if (EUtil.hasTypeInfo(this)) {
         this.A.setType(this.getType(), var1);
         this.kS.setType(this.getType(), var1);
      } else if (EUtil.hasTypeInfo(this.A)) {
         this.setType(this.A.getType(), var1);
         this.kS.setType(this.A.getType(), var1);
      } else if (EUtil.hasTypeInfo(this.kS)) {
         this.setType(this.kS.getType(), var1);
         this.A.setType(this.kS.getType(), var1);
      }
   }

   public ajm pC() {
      return (ajm)this.pC(new ajm(this.pC.duplicate(), this.A.duplicate(), this.kS.duplicate()));
   }

   @Override
   public IEImm evaluate(EState var1) {
      IEImm var2 = this.pC.evaluate(var1);
      if (var2 == null) {
         return null;
      } else {
         return var2._signum() != 0 ? this.A.evaluate(var1) : this.kS.evaluate(var1);
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      ICElementFactory var3 = var2.getElementFactory();
      ICPredicate var4 = var3.createPredicate((ICExpression)this.pC.generateC(var1, var2));
      ICExpression var5 = (ICExpression)this.A.generateC(var1, var2);
      ICExpression var6 = (ICExpression)this.kS.generateC(var1, var2);
      return var3.createOperation(COperatorType.COND, var4, var5, var6);
   }

   @Override
   public void pC(akz var1) {
      var1.paren();
      var1.pC(this.pC);
      var1.append(" ? ");
      var1.pC(this.A);
      var1.append(" : ");
      var1.pC(this.kS);
      var1.parenClose();
      var1.A(this);
   }
}
