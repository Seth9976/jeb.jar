package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDIndex;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.ArrayList;
import java.util.Arrays;

public class bpz extends bpp implements IDNewInfo {
   IJavaType E;
   IJavaType sY;

   bpz(IJavaType var1, IJavaType var2, IDIndex var3, IDExpression[] var4, String var5) {
      super(var3, var4, var1, var5, DInvokeType.NEW);
      this.E = var1;
      this.sY = var2;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.sY == null ? 0 : this.sY.hashCode());
      return 31 * var1 + (this.E == null ? 0 : this.E.hashCode());
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
         bpz var3 = (bpz)var1;
         if (this.sY == null) {
            if (var3.sY != null) {
               return false;
            }
         } else if (!this.sY.equals(var3.sY)) {
            return false;
         }

         if (this.E == null) {
            if (var3.E != null) {
               return false;
            }
         } else if (!this.E.equals(var3.E)) {
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

      bpz var3 = new bpz(this.E, this.sY, this.wS, pC(this.UT, var1), this.kS);
      super.pC(var3, var1);
      return var3;
   }

   @Override
   public IDNewInfo duplicate() {
      return (IDNewInfo)this.copy(null);
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      btp var2 = (btp)var1;
      IDImm var3 = var2.pC(this.kS, Arrays.asList(this.UT), this.A, this.E.getName());
      IDEmuFrame var4 = var2.getCurrentFrame();
      if (var4 != null && var4.getMethodSignature().contains("<clinit>")) {
         JvmMethodSig var5 = JvmMethodSig.parse(var4.getMethodSignature());
         btj var6 = var2.UT(var5.csig);
         if (var6 != null) {
            var6.pC(this, var3);
         }
      }

      return var3;
   }

   public IJavaExpression pC(IDMethodContext var1, IJavaMethod var2) {
      IDexMethod var3 = var1.getDex().getMethod(this.wS.getValue());
      String var4 = var3.getSignature(false);
      ArrayList var5 = new ArrayList();

      for (IDExpression var9 : this.UT) {
         var5.add((IJavaExpression)var9.generateAST(var1, var2));
      }

      bjv var10 = (bjv)var2.getGlobalContext().createNew(this.E, var4, var5);
      var10.pC(var1.getGlobalContext().getDecompiler());
      var10.setOrigin(this.getOrigin());
      return (IJavaExpression)this.pC(var10);
   }

   @Override
   public void format(DFormattingContext var1) {
      if (this.E != this.sY) {
         var1.append("alloc");
         var1.space();
         this.E.format(var1);
         var1.space();
         var1.append("invoke");
         var1.space();
         this.sY.format(var1);
      } else {
         var1.append("new");
         var1.space();
         this.E.format(var1);
      }

      var1.paren();
      int var2 = 0;

      for (IDExpression var6 : this.UT) {
         if (var2 > 0) {
            var1.append(", ");
         }

         var6.format(var1);
         var2++;
      }

      var1.parenClose();
   }
}
