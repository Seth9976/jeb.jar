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

public class bpy extends bph implements IDNewArrayInfo {
   private IDExpression A;
   private List kS;

   bpy(IJavaType var1, IDExpression var2, List var3) {
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
         bpy var3 = (bpy)var1;
         if (this.kS == null) {
            if (var3.kS != null) {
               return false;
            }
         } else if (!pC(this.kS, var3.kS, var2)) {
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

      bpy var3 = new bpy(this.pC, pC(this.A, var1), pC(this.kS, var1));
      super.A(var3);
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
         this.A = var1;
      }
   }

   @Override
   public IDExpression getSize() {
      return this.A;
   }

   @Override
   public int getCountOfInitialValues() {
      return this.kS.size();
   }

   @Override
   public List getInitialValues() {
      return Collections.unmodifiableList(this.kS);
   }

   @Override
   public List getArguments() {
      return this.kS;
   }

   @Override
   public IDExpression getInitialValue(int var1) {
      return (IDExpression)this.kS.get(var1);
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

         this.kS = var1;
      }
   }

   @Override
   public void setInitialValue(int var1, IDExpression var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Null expression in array initialization list at index: " + var1);
      } else {
         this.kS.set(var1, var2);
      }
   }

   @Override
   public void collectVarIds(Set var1) {
      this.A.collectVarIds(var1);

      for (IDExpression var3 : this.kS) {
         var3.collectVarIds(var1);
      }
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      this.A.updateTypes(var1);

      for (IDExpression var3 : this.kS) {
         var3.updateTypes(var1);
      }
   }

   @Override
   public boolean pC() {
      return true;
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      try {
         if (!(this.A instanceof IDImm)) {
            return true;
         }

         if (((IDImm)this.A).toLong(true) < 0L) {
            return true;
         }
      } catch (DexDecEvaluationException var4) {
      }

      if (this.A.canThrow(var1)) {
         return true;
      } else {
         for (IDExpression var3 : this.kS) {
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
      } else if (this.A.hasSideEffects(var1, false)) {
         return true;
      } else {
         for (IDExpression var4 : this.kS) {
            if (var4.hasSideEffects(var1, false)) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public int countVariable(IDVar var1) {
      int var2 = ((bph)this.A).countVariable(var1);

      for (IDExpression var4 : this.kS) {
         var2 += ((bph)var4).countVariable(var1);
      }

      return var2;
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

      for (int var4 = 0; var4 < this.kS.size(); var4++) {
         IDExpression var5 = (IDExpression)this.kS.get(var4);
         if (var5 == var1) {
            this.kS.set(var4, var2.duplicate());
            var3++;
         } else {
            var3 += var5.replaceVariable(var1, var2);
         }
      }

      return var3;
   }

   @Override
   public void pC(IJavaType var1, IJavaType var2) {
      super.pC(var1, var2);
      ((bph)this.A).pC(var1, var2);

      for (IDExpression var4 : this.kS) {
         ((bph)var4).pC(var1, var2);
      }
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      int var2 = this.kS.size();
      if (var2 > 100 && var1 instanceof ArrayList) {
         ((ArrayList)var1).ensureCapacity(var1.size() + 1 + var2);
      }

      var1.add(this.A);
      var1.addAll(this.kS);
   }

   @Override
   public boolean areSubExpsAllImms() {
      if (!(this.A instanceof IDImm)) {
         return false;
      } else {
         for (IDExpression var2 : this.kS) {
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
         if (this.A == var1) {
            this.A = pC(var2, var3);
            var3++;
         }

         for (int var4 = 0; var4 < this.kS.size(); var4++) {
            if (this.kS.get(var4) == var1) {
               this.kS.set(var4, pC(var2, var3));
               var3++;
            }
         }

         return var3 > 0;
      }
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      int var2 = (int)this.A.evaluate(var1).toLong();
      ArrayList var3 = new ArrayList();

      for (IDExpression var5 : this.kS) {
         IDImm var6 = var5.evaluate(var1);
         var3.add(var6);
      }

      return var1.createArray(this.pC, var2, var3);
   }

   @Override
   public IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2) {
      cdi.pC(this.pC);
      if (this.kS.isEmpty()) {
         IJavaExpression var6 = (IJavaExpression)this.A.generateAST(var1, var2);
         return this.pC(var2.getGlobalContext().createNewArray(this.pC, var6));
      } else {
         ArrayList var3 = new ArrayList();

         for (IDExpression var5 : this.kS) {
            var3.add((IJavaExpression)var5.generateAST(var1, var2));
         }

         return this.pC(new bjw(this.pC, false, var3));
      }
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.append("new");
      var1.space();
      this.pC.formatArray(var1, this.A);
      if (!this.kS.isEmpty()) {
         var1.brace();
         int var2 = 0;

         for (IDExpression var4 : this.kS) {
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
