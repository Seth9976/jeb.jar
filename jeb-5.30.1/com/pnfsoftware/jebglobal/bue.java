package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class bue extends btk implements IDNewArrayInfo {
   private IDExpression RF;
   private List xK;

   bue(IJavaType var1, IDExpression var2, List var3) {
      super(var1);
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         if (var3 == null) {
            var3 = new ArrayList();
         }

         this.setSize(var2);
         this.setInitialValues((List)var3);
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
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
         bue var3 = (bue)var1;
         if (this.xK == null) {
            if (var3.xK != null) {
               return false;
            }
         } else if (!q(this.xK, var3.xK, var2)) {
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

      bue var3 = new bue(this.q, q(this.RF, var1), q(this.xK, var1));
      super.RF(var3);
      return var3;
   }

   @Override
   public IDNewArrayInfo duplicate() {
      return (IDNewArrayInfo)this.copy(null);
   }

   @Override
   public void setSize(IDExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
      }
   }

   @Override
   public IDExpression getSize() {
      return this.RF;
   }

   @Override
   public int getCountOfInitialValues() {
      return this.xK.size();
   }

   @Override
   public List getInitialValues() {
      return Collections.unmodifiableList(this.xK);
   }

   @Override
   public List getArguments() {
      return this.xK;
   }

   @Override
   public IDExpression getInitialValue(int var1) {
      return (IDExpression)this.xK.get(var1);
   }

   @Override
   public void setInitialValues(List var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         int var2 = 0;

         for (IDExpression var4 : var1) {
            if (var4 == null) {
               throw new IllegalArgumentException("Null expression in array initialization list at index: " + var2);
            }

            var2++;
         }

         this.xK = var1;
      }
   }

   @Override
   public void setInitialValue(int var1, IDExpression var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Null expression in array initialization list at index: " + var1);
      } else {
         this.xK.set(var1, var2);
      }
   }

   @Override
   public void collectVarIds(Set var1) {
      this.RF.collectVarIds(var1);

      for (IDExpression var3 : this.xK) {
         var3.collectVarIds(var1);
      }
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      this.RF.updateTypes(var1);

      for (IDExpression var3 : this.xK) {
         var3.updateTypes(var1);
      }
   }

   @Override
   public boolean q() {
      if (!this.q.isDetermined()) {
         return false;
      } else if (!((btk)this.RF).q()) {
         return false;
      } else {
         for (IDExpression var2 : this.xK) {
            if (!((btk)var2).q()) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public boolean RF() {
      return true;
   }

   @Override
   public boolean xK() {
      return true;
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      try {
         if (!(this.RF instanceof IDImm)) {
            return true;
         }

         if (((IDImm)this.RF).toLong(true) < 0L) {
            return true;
         }
      } catch (DexDecEvaluationException var4) {
      }

      if (this.RF.canThrow(var1)) {
         return true;
      } else {
         for (IDExpression var3 : this.xK) {
            if (var3.canThrow(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      if (var2 && this.canThrow(var1)) {
         return true;
      } else if (this.RF.hasSideEffects(var1, false)) {
         return true;
      } else {
         for (IDExpression var4 : this.xK) {
            if (var4.hasSideEffects(var1, false)) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public int countVariable(IDVar var1) {
      int var2 = ((btk)this.RF).countVariable(var1);

      for (IDExpression var4 : this.xK) {
         var2 += ((btk)var4).countVariable(var1);
      }

      return var2;
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

      for (int var4 = 0; var4 < this.xK.size(); var4++) {
         IDExpression var5 = (IDExpression)this.xK.get(var4);
         if (var5 == var1) {
            this.xK.set(var4, var2.duplicate());
            var3++;
         } else {
            var3 += var5.replaceVariable(var1, var2);
         }
      }

      return var3;
   }

   @Override
   public void q(IJavaType var1, IJavaType var2) {
      super.q(var1, var2);
      ((btk)this.RF).q(var1, var2);

      for (IDExpression var4 : this.xK) {
         ((btk)var4).q(var1, var2);
      }
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      int var2 = this.xK.size();
      if (var2 > 100 && var1 instanceof ArrayList) {
         ((ArrayList)var1).ensureCapacity(var1.size() + 1 + var2);
      }

      var1.add(this.RF);
      var1.addAll(this.xK);
   }

   @Override
   public boolean areSubExpsAllImms() {
      if (!(this.RF instanceof IDImm)) {
         return false;
      } else {
         for (IDExpression var2 : this.xK) {
            if (!(var2 instanceof IDImm)) {
               return false;
            }
         }

         return true;
      }
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

         for (int var4 = 0; var4 < this.xK.size(); var4++) {
            if (this.xK.get(var4) == var1) {
               this.xK.set(var4, q(var2, var3));
               var3++;
            }
         }

         return var3 > 0;
      }
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      int var2 = (int)this.RF.evaluate(var1).toLong();
      ArrayList var3 = new ArrayList();

      for (IDExpression var5 : this.xK) {
         IDImm var6 = var5.evaluate(var1);
         var3.add(var6);
      }

      return var1.createArray(this.q, var2, var3);
   }

   @Override
   public IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2) {
      ciq.q(this.q);
      if (this.xK.isEmpty()) {
         IJavaExpression var6 = (IJavaExpression)this.RF.generateAST(var1, var2);
         return this.q(var2.getGlobalContext().createNewArray(this.q, var6));
      } else {
         ArrayList var3 = new ArrayList();

         for (IDExpression var5 : this.xK) {
            var3.add((IJavaExpression)var5.generateAST(var1, var2));
         }

         return this.q(new bnt(this.q, false, var3));
      }
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.append("new");
      var1.space();
      this.q.formatArray(var1, this.RF);
      if (!this.xK.isEmpty()) {
         var1.brace();
         int var2 = 0;

         for (IDExpression var4 : this.xK) {
            if (var2 > 0) {
               var1.append(", ");
            }

            var4.format(var1);
            var2++;
         }

         var1.braceClose();
      }
   }
}
