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
public class alp extends ane implements IECond {
   @SerId(1)
   private IEGeneric q;
   @SerId(2)
   private IEGeneric RF;
   @SerId(3)
   private IEGeneric xK;

   public alp(IEGeneric var1, IEGeneric var2, IEGeneric var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null predicate in conditional is not allowed");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Null true-expression in conditional is not allowed");
      } else if (var3 == null) {
         throw new IllegalArgumentException("Null false-expression in conditional is not allowed");
      } else if (var2.getBitsize() != var3.getBitsize()) {
         throw new IllegalArgumentException(Strings.ff("Alternatives do not have the same bitsize: %d, %d", var2.getBitsize(), var3.getBitsize()));
      } else {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      return 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
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
         alp var3 = (alp)var1;
         if (this.RF == null) {
            if (var3.RF != null) {
               return false;
            }
         } else if (!this.RF.equalsEx(var3.RF, var2)) {
            return false;
         }

         if (this.xK == null) {
            if (var3.xK != null) {
               return false;
            }
         } else if (!this.xK.equalsEx(var3.xK, var2)) {
            return false;
         }

         if (this.q == null) {
            if (var3.q != null) {
               return false;
            }
         } else if (!this.q.equalsEx(var3.q, var2)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public IEGeneric getCondition() {
      return this.q;
   }

   @Override
   public void setCondition(IEGeneric var1) {
      this.q = var1;
   }

   @Override
   public IEGeneric getExpressionTrue() {
      return this.RF;
   }

   @Override
   public void setExpressionTrue(IEGeneric var1) {
      RF(this.RF, var1);
      this.RF = var1;
   }

   @Override
   public IEGeneric getExpressionFalse() {
      return this.xK;
   }

   @Override
   public void setExpressionFalse(IEGeneric var1) {
      RF(this.xK, var1);
      this.xK = var1;
   }

   @Override
   public int getBitsize() {
      return this.RF.getBitsize();
   }

   @Override
   public int getPriority() {
      return 20;
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      this.q.getUsed(var1);
      this.RF.getDefinedOrUsedAsDestination(var1);
      this.xK.getDefinedOrUsedAsDestination(var1);
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      this.q.getUsed(var1);
      this.RF.getUsed(var1);
      this.xK.getUsed(var1);
   }

   @Override
   public boolean accessesMemory() {
      return this.q.accessesMemory() || this.RF.accessesMemory() || this.xK.accessesMemory();
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;
      if (this.q == var1) {
         this.q = var2.duplicate();
         var3++;
      } else {
         var3 += this.q.replaceVar(var1, var2);
      }

      if (this.RF == var1) {
         RF(this.RF, var2);
         this.RF = var2.duplicate();
         var3++;
      } else {
         var3 += this.RF.replaceVar(var1, var2);
      }

      if (this.xK == var1) {
         RF(this.xK, var2);
         this.xK = var2.duplicate();
         var3++;
      } else {
         var3 += this.xK.replaceVar(var1, var2);
      }

      return var3;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.add(this.q);
      var1.add(this.RF);
      var1.add(this.xK);
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      q(var1, var2);
      if (this.q == var1) {
         this.q = var2;
         return true;
      } else if (this.RF == var1) {
         RF(this.RF, var2);
         this.RF = var2;
         return true;
      } else if (this.xK == var1) {
         RF(this.xK, var2);
         this.xK = var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      this.q.updateTypes(var1);
      this.RF.updateTypes(var1);
      this.xK.updateTypes(var1);
      if (EUtil.hasTypeInfo(this)) {
         this.RF.setType(this.getType(), var1);
         this.xK.setType(this.getType(), var1);
      } else if (EUtil.hasTypeInfo(this.RF)) {
         this.setType(this.RF.getType(), var1);
         this.xK.setType(this.RF.getType(), var1);
      } else if (EUtil.hasTypeInfo(this.xK)) {
         this.setType(this.xK.getType(), var1);
         this.RF.setType(this.xK.getType(), var1);
      }
   }

   public alp q() {
      return (alp)this.q(new alp(this.q.duplicate(), this.RF.duplicate(), this.xK.duplicate()));
   }

   @Override
   public IEImm evaluate(EState var1) {
      IEImm var2 = this.q.evaluate(var1);
      if (var2 == null) {
         return null;
      } else {
         return var2._signum() != 0 ? this.RF.evaluate(var1) : this.xK.evaluate(var1);
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      ICElementFactory var3 = var2.getElementFactory();
      ICPredicate var4 = var3.createPredicate((ICExpression)this.q.generateC(var1, var2));
      ICExpression var5 = (ICExpression)this.RF.generateC(var1, var2);
      ICExpression var6 = (ICExpression)this.xK.generateC(var1, var2);
      return var3.createOperation(COperatorType.COND, var4, var5, var6);
   }

   @Override
   public void q(and var1) {
      var1.paren();
      var1.q(this.q);
      var1.append(" ? ");
      var1.q(this.RF);
      var1.append(" : ");
      var1.q(this.xK);
      var1.parenClose();
      var1.RF(this);
   }
}
