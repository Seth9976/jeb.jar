package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDIndex;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.Collection;
import java.util.Set;

public class bqd extends bph implements IDStaticField {
   private IDIndex A;
   private String kS;
   private String wS;

   bqd(IDIndex var1, IJavaType var2, String var3, String var4) {
      super(var2);
      if ((var1 != null || "class".equals(var4)) && var2 != null && var3 != null) {
         this.A = var1;
         this.kS = var3;
         this.wS = var4;
      } else {
         throw new IllegalArgumentException();
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

      bqd var3 = new bqd(this.A, this.pC, this.kS, this.wS);
      super.A(var3);
      return var3;
   }

   @Override
   public IDStaticField duplicate() {
      return (IDStaticField)this.copy(null);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         bqd var3 = (bqd)var1;
         if (this.kS == null) {
            if (var3.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var3.kS)) {
            return false;
         }

         if (this.wS == null) {
            if (var3.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var3.wS)) {
            return false;
         }

         if (this.A == null) {
            if (var3.A != null) {
               return false;
            }
         } else if (!this.A.equalsEx(var3.A, var2)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public IDIndex getIndex() {
      return this.A;
   }

   @Override
   public String getClassSignature() {
      return this.kS;
   }

   @Override
   public String getFieldname() {
      return this.wS;
   }

   @Override
   public boolean isTypeClass() {
      return this.A == null && "class".equals(this.wS);
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
   public IDexField getNativeField(IDGlobalContext var1) {
      return this.A == null ? null : var1.getDex().getField(this.A.getValue());
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      btp var2 = (btp)var1;
      if (this.A == null) {
         return var2.getClassReference(this.kS);
      } else {
         String var3 = this.getType().getName();
         return var2.pC(this.kS, this.wS, var3);
      }
   }

   @Override
   public IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2) {
      String var3 = null;
      if (this.A != null) {
         IDexField var4 = var1.getDex().getField(this.A.getValue());
         var3 = var4.getSignature(false);
         if (!var4.isInternal()) {
            ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var2.getGlobalContext().getDecompiler()).pC(var3, Boolean.valueOf(true));
         }
      }

      IJavaType var6 = var1.getTypeFactory().parseType(this.kS);
      bkb var5 = (bkb)var2.getGlobalContext().createStaticField(var6, var3);
      var5.setOrigin(this.getOrigin());
      return this.pC(var5);
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.append(this.kS).append("->");
      if (this.A == null) {
         var1.append("class");
      } else {
         var1.append(this.wS);
         var1.appendFormattedTypeIf(this.pC);
      }
   }
}
