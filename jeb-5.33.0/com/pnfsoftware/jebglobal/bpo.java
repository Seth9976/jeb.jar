package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import java.util.Collection;
import java.util.Set;

public class bpo extends bph implements IDArrayElt {
   private IDExpression A;
   private IDExpression kS;

   bpo(IDExpression var1, IDExpression var2, IJavaType var3) {
      super(var3);
      if (var1 != null && var2 != null && var3 != null) {
         this.A = var1;
         this.kS = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      return 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
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
         bpo var3 = (bpo)var1;
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

         return true;
      }
   }

   @Override
   public IDExpression copy(DCopyOptions var1) {
      if (var1 != null) {
         IDExpression var2 = var1.onDup(this);
         if (var2 != null) {
            return var2;
         }
      }

      bpo var3 = new bpo(pC(this.A, var1), pC(this.kS, var1), this.pC);
      super.A(var3);
      return var3;
   }

   @Override
   public IDArrayElt duplicate() {
      return (IDArrayElt)this.copy(null);
   }

   @Override
   public IDExpression getArray() {
      return this.A;
   }

   @Override
   public IDExpression getIndex() {
      return this.kS;
   }

   @Override
   public void collectVarIds(Set var1) {
      this.A.collectVarIds(var1);
      this.kS.collectVarIds(var1);
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      this.A.updateTypes(var1);
      this.kS.updateTypes(var1);
      if (this.A.getType().isDetermined()) {
         IJavaType var2 = this.A.getType();
         if (var2 != null && var2.isArray()) {
            this.setType(var2.getArrayTypeDimensionBelow(), var1);
         }
      }

      if (!this.kS.getType().isDetermined()) {
         IJavaTypeFactory var3 = this.pC.getFactory();
         this.kS.setType(var3.getSmallIntWildcard(), var1);
      }
   }

   @Override
   public boolean pC() {
      return ((bph)this.A).pC() || ((bph)this.kS).pC();
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      Boolean var2 = this.getCustomCanThrow();
      return var2 != null ? var2 : true;
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      return var2 && this.canThrow(var1) ? true : this.A.hasSideEffects(var1, false) || this.kS.hasSideEffects(var1, false);
   }

   @Override
   public int countVariable(IDVar var1) {
      int var2 = 0;
      var2 += ((bph)this.A).countVariable(var1);
      return var2 + ((bph)this.kS).countVariable(var1);
   }

   @Override
   public int replaceVariable(IDVar var1, IDExpression var2) {
      int var3 = 0;
      if (this.A == var1) {
         this.A = var2.duplicate();
         var3++;
      } else {
         var3 += this.A.replaceVariable(var1, var2);
      }

      if (this.kS == var1) {
         this.kS = var2.duplicate();
         var3++;
      } else {
         var3 += this.kS.replaceVariable(var1, var2);
      }

      return var3;
   }

   @Override
   public void pC(IJavaType var1, IJavaType var2) {
      super.pC(var1, var2);
      ((bph)this.A).pC(var1, var2);
      ((bph)this.kS).pC(var1, var2);
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.add(this.kS);
      var1.add(this.A);
   }

   @Override
   public boolean replaceSubExpression(IDExpression var1, IDExpression var2) {
      if (var2 == null) {
         return false;
      } else {
         int var3 = 0;
         if (this.A == var1) {
            this.A = pC(var2, var3);
            var3++;
         }

         if (this.kS == var1) {
            this.kS = pC(var2, var3);
            var3++;
         }

         return var3 > 0;
      }
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      IDImm var2 = this.kS.evaluate(var1);
      IDImm var3 = this.A.evaluate(var1);
      return var1.getArrayElement(var3, (int)var2.toLong());
   }

   public IJavaExpression pC(IDMethodContext var1, IJavaMethod var2) {
      IJavaExpression var3 = (IJavaExpression)this.A.generateAST(var1, var2);
      IJavaExpression var4 = (IJavaExpression)this.kS.generateAST(var1, var2);
      return (IJavaExpression)this.pC(var2.getGlobalContext().createArrayElt(var3, var4));
   }

   @Override
   public void format(DFormattingContext var1) {
      if (var1.isDisplayTypes()) {
         var1.paren();
      }

      this.A.format(var1);
      var1.bracket();
      this.kS.format(var1);
      var1.bracketClose();
      if (var1.isDisplayTypes()) {
         var1.parenClose();
      }

      var1.appendFormattedTypeIf(this.pC);
   }
}
