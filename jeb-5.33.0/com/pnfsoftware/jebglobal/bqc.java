package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDIndex;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeReference;
import java.util.Collection;
import java.util.Set;

public class bqc extends bph implements IDReferenceType {
   private IDIndex A;
   private IJavaType kS;

   bqc(IJavaType var1, IDIndex var2, IJavaType var3) {
      super(var1);
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public IDIndex getTypeIndex() {
      return this.A;
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
         bqc var3 = (bqc)var1;
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
         } else if (!this.kS.equals(var3.kS)) {
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

      bqc var3 = new bqc(this.pC, this.A, this.kS);
      super.A(var3);
      return var3;
   }

   @Override
   public IDReferenceType duplicate() {
      return (IDReferenceType)this.copy(null);
   }

   @Override
   public void collectVarIds(Set var1) {
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
   }

   @Override
   public boolean pC() {
      return false;
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      return false;
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      return false;
   }

   @Override
   public int countVariable(IDVar var1) {
      return 0;
   }

   @Override
   public int replaceVariable(IDVar var1, IDExpression var2) {
      return 0;
   }

   @Override
   public void pC(IJavaType var1, IJavaType var2) {
      super.pC(var1, var2);
   }

   @Override
   public void collectSubExpressions(Collection var1) {
   }

   @Override
   public boolean replaceSubExpression(IDExpression var1, IDExpression var2) {
      return false;
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      String var2 = this.kS.getName();
      return var1.getClassReference(var2);
   }

   @Override
   public IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2) {
      IJavaTypeReference var3 = var2.getGlobalContext().createTypeReference(this.kS);
      return this.pC(var3);
   }

   @Override
   public void format(DFormattingContext var1) {
      if (this.kS == null) {
         var1.angle();
         this.A.format(var1);
         var1.angleClose();
      } else {
         var1.appendFormattedType(this.kS);
      }
   }
}
