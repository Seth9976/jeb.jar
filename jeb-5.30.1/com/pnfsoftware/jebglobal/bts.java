package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDAllocObjectInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class bts extends btk implements IDAllocObjectInfo {
   public static final String RF = "Ljeb/synthetic/Objects;->alloc(Ljava/lang/Class;)Ljava/lang/Object;";
   IJavaType xK;
   public static final boolean Dw = false;

   bts(IJavaType var1) {
      super(var1);
      this.xK = var1;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
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
         bts var3 = (bts)var1;
         if (this.xK == null) {
            if (var3.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var3.xK)) {
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

      bts var3 = new bts(this.xK);
      super.RF(var3);
      return var3;
   }

   @Override
   public IDAllocObjectInfo duplicate() {
      return (IDAllocObjectInfo)this.copy(null);
   }

   @Override
   public IJavaType getObjectType() {
      return this.xK;
   }

   @Override
   public List getArguments() {
      return Collections.emptyList();
   }

   @Override
   public void collectVarIds(Set var1) {
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
   }

   @Override
   public boolean q() {
      return true;
   }

   @Override
   public boolean RF() {
      return true;
   }

   @Override
   public boolean xK() {
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
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      bye var2 = (bye)var1;
      return var2.q(this.xK.getSignature());
   }

   @Override
   public IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2) {
      ArrayList var3 = new ArrayList();
      IJavaTypeReference var4 = var2.getGlobalContext().createTypeReference(this.xK);
      var3.add(var4);
      bmq var5 = (bmq)var2.getGlobalContext().createCall("Ljeb/synthetic/Objects;->alloc(Ljava/lang/Class;)Ljava/lang/Object;", 3, var3);
      var5.q(var1.getGlobalContext().getDecompiler());
      var5.setOrigin(this.getOrigin());
      return this.q(var5);
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.append("alloc");
      var1.space();
      this.q.format(var1);
   }
}
