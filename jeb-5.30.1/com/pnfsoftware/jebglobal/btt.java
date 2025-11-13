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

public class btt extends btk implements IDArrayElt {
   private IDExpression RF;
   private IDExpression xK;

   btt(IDExpression var1, IDExpression var2, IJavaType var3) {
      super(var3);
      if (var1 != null && var2 != null && var3 != null) {
         this.RF = var1;
         this.xK = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      return 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
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
         btt var3 = (btt)var1;
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

      btt var3 = new btt(q(this.RF, var1), q(this.xK, var1), this.q);
      super.RF(var3);
      return var3;
   }

   @Override
   public IDArrayElt duplicate() {
      return (IDArrayElt)this.copy(null);
   }

   @Override
   public IDExpression getArray() {
      return this.RF;
   }

   @Override
   public IDExpression getIndex() {
      return this.xK;
   }

   @Override
   public void collectVarIds(Set var1) {
      this.RF.collectVarIds(var1);
      this.xK.collectVarIds(var1);
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      this.RF.updateTypes(var1);
      this.xK.updateTypes(var1);
      if (this.RF.getType().isDetermined()) {
         IJavaType var2 = this.RF.getType();
         if (var2 != null && var2.isArray()) {
            this.setType(var2.getArrayTypeDimensionBelow(), var1);
         }
      }

      if (!this.xK.getType().isDetermined()) {
         IJavaTypeFactory var3 = this.q.getFactory();
         this.xK.setType(var3.getSmallIntWildcard(), var1);
      }
   }

   @Override
   public boolean q() {
      return ((btk)this.RF).q() && ((btk)this.xK).q() && this.q.isDetermined();
   }

   @Override
   public boolean RF() {
      return ((btk)this.RF).RF() || ((btk)this.xK).RF();
   }

   @Override
   public boolean xK() {
      return true;
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      Boolean var2 = this.getCustomCanThrow();
      return var2 != null ? var2 : true;
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      return var2 && this.canThrow(var1) ? true : this.RF.hasSideEffects(var1, false) || this.xK.hasSideEffects(var1, false);
   }

   @Override
   public int countVariable(IDVar var1) {
      int var2 = 0;
      var2 += ((btk)this.RF).countVariable(var1);
      return var2 + ((btk)this.xK).countVariable(var1);
   }

   @Override
   public int replaceVariable(IDVar var1, IDExpression var2) {
      int var3 = 0;
      if (this.RF == var1) {
         this.RF = var2.duplicate();
         var3++;
      } else {
         var3 += this.RF.replaceVariable(var1, var2);
      }

      if (this.xK == var1) {
         this.xK = var2.duplicate();
         var3++;
      } else {
         var3 += this.xK.replaceVariable(var1, var2);
      }

      return var3;
   }

   @Override
   public void q(IJavaType var1, IJavaType var2) {
      super.q(var1, var2);
      ((btk)this.RF).q(var1, var2);
      ((btk)this.xK).q(var1, var2);
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.add(this.xK);
      var1.add(this.RF);
   }

   @Override
   public boolean replaceSubExpression(IDExpression var1, IDExpression var2) {
      if (var2 == null) {
         return false;
      } else {
         int var3 = 0;
         if (this.RF == var1) {
            this.RF = q(var2, var3);
            var3++;
         }

         if (this.xK == var1) {
            this.xK = q(var2, var3);
            var3++;
         }

         return var3 > 0;
      }
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      IDImm var2 = this.xK.evaluate(var1);
      IDImm var3 = this.RF.evaluate(var1);
      return var1.getArrayElement(var3, (int)var2.toLong());
   }

   public IJavaExpression q(IDMethodContext var1, IJavaMethod var2) {
      IJavaExpression var3 = (IJavaExpression)this.RF.generateAST(var1, var2);
      IJavaExpression var4 = (IJavaExpression)this.xK.generateAST(var1, var2);
      return (IJavaExpression)this.q(var2.getGlobalContext().createArrayElt(var3, var4));
   }

   @Override
   public void format(DFormattingContext var1) {
      if (var1.isDisplayTypes()) {
         var1.paren();
      }

      this.RF.format(var1);
      var1.bracket();
      this.xK.format(var1);
      var1.bracketClose();
      if (var1.isDisplayTypes()) {
         var1.parenClose();
      }

      var1.appendFormattedTypeIf(this.q);
   }
}
