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

public class bpu extends bph implements IDInstanceField {
   private IDExpression A;
   private IDIndex kS;
   private String wS;

   bpu(IDExpression var1, IDIndex var2, IJavaType var3, String var4) {
      super(var3);
      if ((var2 != null || "length".equals(var4)) && var1 != null && var3 != null) {
         this.A = var1;
         this.kS = var2;
         this.wS = var4;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
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
         bpu var3 = (bpu)var1;
         if (this.wS == null) {
            if (var3.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var3.wS)) {
            return false;
         }

         if (this.kS == null) {
            if (var3.kS != null) {
               return false;
            }
         } else if (!this.kS.equalsEx(var3.kS, var2)) {
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
   public IDExpression copy(DCopyOptions var1) {
      if (var1 != null) {
         IDExpression var2 = var1.onDup(this);
         if (var2 != null) {
            return var2;
         }
      }

      bpu var3 = new bpu(pC(this.A, var1), this.kS, this.pC, this.wS);
      super.A(var3);
      return var3;
   }

   @Override
   public IDInstanceField duplicate() {
      return (IDInstanceField)this.copy(null);
   }

   @Override
   public IDExpression getInstance() {
      return this.A;
   }

   @Override
   public IDIndex getIndex() {
      return this.kS;
   }

   @Override
   public String getFieldname() {
      return this.wS;
   }

   @Override
   public boolean isArrayLength() {
      return this.kS == null && "length".equals(this.wS);
   }

   @Override
   public void collectVarIds(Set var1) {
      this.A.collectVarIds(var1);
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      this.A.updateTypes(var1);
   }

   @Override
   public boolean pC() {
      return ((bph)this.A).pC();
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      return this.A instanceof bps && ((bps)this.A).isNullRef() ? true : this.A.canThrow(var1);
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      return var2 && this.canThrow(var1) ? true : this.A.hasSideEffects(var1, false);
   }

   @Override
   public int countVariable(IDVar var1) {
      return ((bph)this.A).countVariable(var1);
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

      return var3;
   }

   @Override
   public void pC(IJavaType var1, IJavaType var2) {
      super.pC(var1, var2);
      ((bph)this.A).pC(var1, var2);
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.add(this.A);
   }

   @Override
   public boolean replaceSubExpression(IDExpression var1, IDExpression var2) {
      if (var2 == null) {
         return false;
      } else if (this.A == var1) {
         this.A = var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public IDexField getNativeField(IDGlobalContext var1) {
      return this.kS == null ? null : var1.getDex().getField(this.kS.getValue());
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      IDImm var2 = this.A.evaluate(var1);
      if (this.kS == null) {
         int var5 = var1.getArrayObjectLength(var2);
         IDGlobalContext var4 = var1.getGlobalContext();
         return var4.createConstant(var5, var4.getTypeFactory().getInt());
      } else {
         String var3 = var1.getDex().getField(this.kS.getValue()).getSignature(false);
         return var1.getInstanceField(var3, var2);
      }
   }

   public IJavaExpression pC(IDMethodContext var1, IJavaMethod var2) {
      String var3 = null;
      if (this.kS != null) {
         IDexField var4 = var1.getDex().getField(this.kS.getValue());
         var3 = this.kS == null ? null : var4.getSignature(false);
         if (!var4.isInternal()) {
            ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var2.getGlobalContext().getDecompiler()).pC(var3, Boolean.valueOf(false));
         }
      }

      IJavaExpression var6 = (IJavaExpression)this.A.generateAST(var1, var2);
      bji var5 = (bji)var2.getGlobalContext().createInstanceField(var6, var3);
      var5.pC(var2.getGlobalContext().getDecompiler());
      var5.setOrigin(this.getOrigin());
      return (IJavaExpression)this.pC(var5);
   }

   @Override
   public void format(DFormattingContext var1) {
      this.A.format(var1);
      var1.append('.');
      if (this.wS == null) {
         this.kS.format(var1);
      } else {
         var1.append(this.wS);
      }

      var1.appendFormattedTypeIf(this.pC);
   }
}
