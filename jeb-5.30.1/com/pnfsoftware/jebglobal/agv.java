package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class agv extends afg implements ICPredicate {
   @SerId(1)
   ICExpression q;

   agv(ICExpression var1) {
      this.q = var1;
   }

   @Override
   public ICExpression getExpression() {
      return this.q;
   }

   @Override
   public void setExpression(ICExpression var1) {
      this.q = var1;
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.q);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.q == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.q = (ICExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      this.q.generate(var1);
      this.RF(var1);
   }

   @Override
   public boolean isLitteralTrue() {
      return this.q instanceof ICConstant && ((ICConstant)this.q).isTrueLike();
   }

   @Override
   public boolean isLitteralFalse() {
      return this.q instanceof ICConstant && !((ICConstant)this.q).isTrueLike();
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Predicate;
   }

   @Override
   public void reverse(ICOperatorFactory var1) {
      if (!this.RF(var1)) {
         this.q = new agq(var1.get(COperatorType.LOG_NOT), this.q, null, null);
      }
   }

   private boolean RF(ICOperatorFactory var1) {
      return this.q instanceof ICOperation ? ((ICOperation)this.q).reverse(var1) : false;
   }

   public agv q(ICOperatorFactory var1) {
      if (this.q instanceof agq) {
         agq var2 = (agq)this.q.duplicate();
         agq var3 = new agq(var2.getOperator(), var2.getFirstOperand(), var2.getSecondOperand(), var2.getThirdOperand());
         if (var3.reverse(var1)) {
            return new agv(var3);
         }
      }

      return new agv(new agq(var1.get(COperatorType.LOG_NOT), this.q.duplicate(), null, null));
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         agv var2 = (agv)var1;
         if (this.q == null) {
            if (var2.q != null) {
               return false;
            }
         } else if (!this.q.equals(var2.q)) {
            return false;
         }

         return true;
      }
   }

   public agv RF() {
      agv var1 = new agv(this.q.duplicate());
      super.q(var1);
      return var1;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      return ((afe)this.q).evaluate(var1, var2) != 0L ? 1L : 0L;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s", super.toString(), this.q);
   }
}
