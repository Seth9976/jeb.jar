package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collection;
import java.util.Set;

public class bum extends btk implements IDVar {
   private static final ILogger RF = GlobalLog.getLogger(bum.class);
   private int xK;
   private String Dw;

   bum(IDMethodContext var1, int var2, IJavaType var3) {
      super(var3);
      this.xK = var2;
      q(var2, var3);
   }

   public int q(IDVar var1) {
      return Integer.compare(this.xK, var1.getId());
   }

   private static void q(int var0, IJavaType var1) {
      if (var1.isSingleSlot()) {
         if (!DUtil.isSingleSlotVarId(var0)) {
            throw new IllegalArgumentException(Strings.ff("Illegal id 0x%X for single-slot type %s", var0, var1));
         }
      } else if (var1.isDoubleSlot()) {
         if (!DUtil.isDoubleSlotVarId(var0)) {
            throw new IllegalArgumentException(Strings.ff("Illegal id 0x%X for double-slot type %s", var0, var1));
         }
      } else {
         throw new IllegalArgumentException(Strings.ff("Illegal type %s for variable", var1));
      }
   }

   @Override
   public int getId() {
      return this.xK;
   }

   @Override
   public boolean usesSingleRegister() {
      return this.q.isSingleSlot();
   }

   @Override
   public boolean usesPairOfRegisters() {
      return this.q.isDoubleSlot();
   }

   @Override
   public void setPreferredName(String var1) {
      this.Dw = var1;
   }

   @Override
   public String getPreferredName() {
      return this.Dw;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.xK;
      return 31 * var1 + this.q.hashCode();
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      return var1 == this;
   }

   @Override
   public IDExpression copy(DCopyOptions var1) {
      if (var1 != null) {
         IDExpression var2 = var1.onDup(this);
         if (var2 != null) {
            return var2;
         }
      }

      return this;
   }

   public bum oW() {
      return (bum)this.copy(null);
   }

   @Override
   public void collectVarIds(Set var1) {
      var1.add(this.xK);
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
      return var1 == this ? 1 : 0;
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
      IDImm var2 = var1.getVariable(this.getId());
      if (!var2.getType().compareSimple(this.q)) {
         IJavaType var3 = var2.getType().compareAndGetMostPrecise(this.q);
         if (var3 != null && var3 != var2.getType()) {
            var2 = var2.duplicateWithDifferentType(this.q);
         }
      }

      return var2;
   }

   public IJavaExpression q(IDMethodContext var1, IJavaMethod var2) {
      ciq.q(this.q);
      bno var3 = (bno)var2;
      int var4 = (int)var3.za.getOffset();
      int var5 = var3.za.getPhysicalOffset();
      int var6 = var1.retrievePhysicalRegisterId(this.xK);
      if (var6 < 0) {
         var6 = this.xK;
      }

      int var7 = var1.getMethod().getIndex();
      if (var3.lm > 0 && var3.gO.getDefinition(this.xK) == null) {
         IJavaDefinition var13 = var3.gO.q(var4, this.q, this.xK, var6, null, this.Dw, var7, var5);
         var13.setPhysicalOffset(var5);
         return var13;
      } else {
         IJavaDefinition var8 = var3.gO.q(var4, this.q, this.xK, var6, null, this.Dw, var7, var5);
         IJavaIdentifier var9 = var8.getIdentifier();
         IJavaType var10 = var8.getType();
         var10.getSlotCount();
         this.q.getSlotCount();
         if (var3.lm > 0) {
            return var9;
         } else if (this.q == var10) {
            return var9;
         } else if (!this.q.isInt() || !var10.isByte() && !var10.isChar() && !var10.isShort()) {
            if (var10.isClassOrInterface() && this.q.isClassOrInterface()) {
               try {
                  if (var1.getTypeInfoProvider().isChildOf(var10.getSignature(), this.q.getSignature())) {
                     return var9;
                  }
               } catch (Exception var12) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var12, var1.getMethodSignature());
               }
            }

            return (IJavaExpression)this.q(var2.getGlobalContext().createCastOperation(this.q, var9));
         } else {
            return var9;
         }
      }
   }

   @Override
   public void format(DFormattingContext var1) {
      String var2 = this.Dw != null && var1.isUsePreferredNames() ? "$" + this.Dw : DUtil.formatVarId(this.xK);
      var1.appendFormat("%s", var2);
      var1.appendFormattedTypeIf(this.q);
   }
}
