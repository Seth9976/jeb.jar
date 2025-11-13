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
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.Collection;
import java.util.Set;

public class bua extends btk implements IDInstanceField {
   private IDExpression RF;
   private IDIndex xK;
   private String Dw;

   bua(IDExpression var1, IDIndex var2, IJavaType var3, String var4) {
      super(var3);
      if ((var2 != null || "length".equals(var4)) && var1 != null && var3 != null) {
         this.RF = var1;
         this.xK = var2;
         this.Dw = var4;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
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
         bua var3 = (bua)var1;
         if (this.Dw == null) {
            if (var3.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var3.Dw)) {
            return false;
         }

         if (this.xK == null) {
            if (var3.xK != null) {
               return false;
            }
         } else if (!this.xK.equalsEx(var3.xK, var2)) {
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
   public IDExpression copy(DCopyOptions var1) {
      if (var1 != null) {
         IDExpression var2 = var1.onDup(this);
         if (var2 != null) {
            return var2;
         }
      }

      bua var3 = new bua(q(this.RF, var1), this.xK, this.q, this.Dw);
      super.RF(var3);
      return var3;
   }

   @Override
   public IDInstanceField duplicate() {
      return (IDInstanceField)this.copy(null);
   }

   @Override
   public IDExpression getInstance() {
      return this.RF;
   }

   @Override
   public IDIndex getIndex() {
      return this.xK;
   }

   @Override
   public String getFieldname() {
      return this.Dw;
   }

   @Override
   public boolean isArrayLength() {
      return this.xK == null && "length".equals(this.Dw);
   }

   @Override
   public void collectVarIds(Set var1) {
      this.RF.collectVarIds(var1);
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      this.RF.updateTypes(var1);
   }

   @Override
   public boolean q() {
      return ((btk)this.RF).q();
   }

   @Override
   public boolean RF() {
      return ((btk)this.RF).RF();
   }

   @Override
   public boolean xK() {
      return true;
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      return this.RF instanceof bty && ((bty)this.RF).isNullRef() ? true : this.RF.canThrow(var1);
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      return var2 && this.canThrow(var1) ? true : this.RF.hasSideEffects(var1, false);
   }

   @Override
   public int countVariable(IDVar var1) {
      return ((btk)this.RF).countVariable(var1);
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

      return var3;
   }

   @Override
   public void q(IJavaType var1, IJavaType var2) {
      super.q(var1, var2);
      ((btk)this.RF).q(var1, var2);
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.add(this.RF);
   }

   @Override
   public boolean replaceSubExpression(IDExpression var1, IDExpression var2) {
      if (var2 == null) {
         return false;
      } else if (this.RF == var1) {
         this.RF = var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public IDexField getNativeField(IDGlobalContext var1) {
      return this.xK == null ? null : var1.getDex().getField(this.xK.getValue());
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      IDImm var2 = this.RF.evaluate(var1);
      if (this.xK == null) {
         int var5 = var1.getArrayObjectLength(var2);
         IDGlobalContext var4 = var1.getGlobalContext();
         return var4.createConstant(var5, var4.getTypeFactory().getInt());
      } else {
         String var3 = var1.getDex().getField(this.xK.getValue()).getSignature(false);
         return var1.getInstanceField(var3, var2);
      }
   }

   public IJavaExpression q(IDMethodContext var1, IJavaMethod var2) {
      String var3 = null;
      if (this.xK != null) {
         IDexField var4 = var1.getDex().getField(this.xK.getValue());
         var3 = this.xK == null ? null : var4.getSignature(false);
         if (!var4.isInternal()) {
            ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)var2.getGlobalContext().getDecompiler()).q(var3, Boolean.valueOf(false));
         }
      }

      IJavaExpression var6 = (IJavaExpression)this.RF.generateAST(var1, var2);
      bnf var5 = (bnf)var2.getGlobalContext().createInstanceField(var6, var3);
      var5.q(var2.getGlobalContext().getDecompiler());
      var5.setOrigin(this.getOrigin());
      return (IJavaExpression)this.q(var5);
   }

   @Override
   public void format(DFormattingContext var1) {
      this.RF.format(var1);
      var1.append('.');
      if (this.Dw == null) {
         this.xK.format(var1);
      } else {
         var1.append(this.Dw);
      }

      var1.appendFormattedTypeIf(this.q);
   }
}
