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

public class buj extends btk implements IDStaticField {
   private IDIndex RF;
   private String xK;
   private String Dw;

   buj(IDIndex var1, IJavaType var2, String var3, String var4) {
      super(var2);
      if ((var1 != null || "class".equals(var4)) && var2 != null && var3 != null) {
         this.RF = var1;
         this.xK = var3;
         this.Dw = var4;
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

      buj var3 = new buj(this.RF, this.q, this.xK, this.Dw);
      super.RF(var3);
      return var3;
   }

   @Override
   public IDStaticField duplicate() {
      return (IDStaticField)this.copy(null);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         buj var3 = (buj)var1;
         if (this.xK == null) {
            if (var3.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var3.xK)) {
            return false;
         }

         if (this.Dw == null) {
            if (var3.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var3.Dw)) {
            return false;
         }

         if (this.RF == null) {
            if (var3.RF != null) {
               return false;
            }
         } else if (!this.RF.equalsEx(var3.RF, var2)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public IDIndex getIndex() {
      return this.RF;
   }

   @Override
   public String getClassSignature() {
      return this.xK;
   }

   @Override
   public String getFieldname() {
      return this.Dw;
   }

   @Override
   public boolean isTypeClass() {
      return this.RF == null && "class".equals(this.Dw);
   }

   @Override
   public void collectVarIds(Set var1) {
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
   }

   @Override
   public boolean q() {
      return this.q.isDetermined();
   }

   @Override
   public boolean RF() {
      return false;
   }

   @Override
   public boolean xK() {
      return true;
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
   public void q(IJavaType var1, IJavaType var2) {
      super.q(var1, var2);
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
      return this.RF == null ? null : var1.getDex().getField(this.RF.getValue());
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      bye var2 = (bye)var1;
      if (this.RF == null) {
         return var2.getClassReference(this.xK);
      } else {
         String var3 = this.getType().getName();
         return var2.q(this.xK, this.Dw, var3);
      }
   }

   @Override
   public IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2) {
      String var3 = null;
      if (this.RF != null) {
         IDexField var4 = var1.getDex().getField(this.RF.getValue());
         var3 = var4.getSignature(false);
         if (!var4.isInternal()) {
            ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)var2.getGlobalContext().getDecompiler()).q(var3, Boolean.valueOf(true));
         }
      }

      IJavaType var6 = var1.getTypeFactory().parseType(this.xK);
      bny var5 = (bny)var2.getGlobalContext().createStaticField(var6, var3);
      var5.setOrigin(this.getOrigin());
      return this.q(var5);
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.append(this.xK).append("->");
      if (this.RF == null) {
         var1.append("class");
      } else {
         var1.append(this.Dw);
         var1.appendFormattedTypeIf(this.q);
      }
   }
}
