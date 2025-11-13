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
public class afc extends adn implements ICPredicate {
   @SerId(1)
   ICExpression pC;

   afc(ICExpression var1) {
      this.pC = var1;
   }

   @Override
   public ICExpression getExpression() {
      return this.pC;
   }

   @Override
   public void setExpression(ICExpression var1) {
      this.pC = var1;
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.pC);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.pC == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.pC = (ICExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      this.pC.generate(var1);
      this.A(var1);
   }

   @Override
   public boolean isLitteralTrue() {
      return this.pC instanceof ICConstant && ((ICConstant)this.pC).isTrueLike();
   }

   @Override
   public boolean isLitteralFalse() {
      return this.pC instanceof ICConstant && !((ICConstant)this.pC).isTrueLike();
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Predicate;
   }

   @Override
   public void reverse(ICOperatorFactory var1) {
      if (!this.A(var1)) {
         this.pC = new aex(var1.get(COperatorType.LOG_NOT), this.pC, null, null);
      }
   }

   private boolean A(ICOperatorFactory var1) {
      return this.pC instanceof ICOperation ? ((ICOperation)this.pC).reverse(var1) : false;
   }

   public afc pC(ICOperatorFactory var1) {
      if (this.pC instanceof aex) {
         aex var2 = (aex)this.pC.duplicate();
         aex var3 = new aex(var2.getOperator(), var2.getFirstOperand(), var2.getSecondOperand(), var2.getThirdOperand());
         if (var3.reverse(var1)) {
            return new afc(var3);
         }
      }

      return new afc(new aex(var1.get(COperatorType.LOG_NOT), this.pC.duplicate(), null, null));
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
         afc var2 = (afc)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         return true;
      }
   }

   public afc A() {
      afc var1 = new afc(this.pC.duplicate());
      super.pC(var1);
      return var1;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      return ((adl)this.pC).evaluate(var1, var2) != 0L ? 1L : 0L;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s", super.toString(), this.pC);
   }
}
