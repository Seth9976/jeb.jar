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

public class buf extends btu implements IDNewInfo {
   IJavaType gO;
   IJavaType nf;

   buf(IJavaType var1, IJavaType var2, IDIndex var3, IDExpression[] var4, String var5) {
      super(var3, var4, var1, var5, DInvokeType.NEW);
      this.gO = var1;
      this.nf = var2;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
      return 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
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
         buf var3 = (buf)var1;
         if (this.nf == null) {
            if (var3.nf != null) {
               return false;
            }
         } else if (!this.nf.equals(var3.nf)) {
            return false;
         }

         if (this.gO == null) {
            if (var3.gO != null) {
               return false;
            }
         } else if (!this.gO.equals(var3.gO)) {
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

      buf var3 = new buf(this.gO, this.nf, this.Uv, q(this.oW, var1), this.Dw);
      super.q(var3, var1);
      return var3;
   }

   @Override
   public IDNewInfo duplicate() {
      return (IDNewInfo)this.copy(null);
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      bye var2 = (bye)var1;
      IDImm var3 = var2.q(this.Dw, Arrays.asList(this.oW), this.xK, this.gO.getName());
      IDEmuFrame var4 = var2.getCurrentFrame();
      if (var4 != null && var4.getMethodSignature().contains("<clinit>")) {
         JvmMethodSig var5 = JvmMethodSig.parse(var4.getMethodSignature());
         bxy var6 = var2.Uv(var5.csig);
         if (var6 != null) {
            var6.q(this, var3);
         }
      }

      return var3;
   }

   public IJavaExpression q(IDMethodContext var1, IJavaMethod var2) {
      IDexMethod var3 = var1.getDex().getMethod(this.Uv.getValue());
      String var4 = var3.getSignature(false);
      ArrayList var5 = new ArrayList();

      for (IDExpression var9 : this.oW) {
         var5.add((IJavaExpression)var9.generateAST(var1, var2));
      }

      bns var10 = (bns)var2.getGlobalContext().createNew(this.gO, var4, var5);
      var10.q(var1.getGlobalContext().getDecompiler());
      var10.setOrigin(this.getOrigin());
      return (IJavaExpression)this.q(var10);
   }

   @Override
   public void format(DFormattingContext var1) {
      if (this.gO != this.nf) {
         var1.append("alloc");
         var1.space();
         this.gO.format(var1);
         var1.space();
         var1.append("invoke");
         var1.space();
         this.nf.format(var1);
      } else {
         var1.append("new");
         var1.space();
         this.gO.format(var1);
      }

      var1.paren();
      int var2 = 0;

      for (IDExpression var6 : this.oW) {
         if (var2 > 0) {
            var1.append(", ");
         }

         var6.format(var1);
         var2++;
      }

      var1.parenClose();
   }
}
