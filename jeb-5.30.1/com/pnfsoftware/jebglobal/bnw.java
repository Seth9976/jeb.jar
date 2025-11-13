package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate_LEGACY;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bnw extends bnu implements IJavaPredicate_LEGACY {
   @SerId(1)
   cio gO;

   public bnw(IJavaExpression var1, IJavaOperator var2, IJavaExpression var3) {
      super(var1, var2, var3);
      if (!var2.isLogical()) {
         throw new IllegalArgumentException();
      } else {
         this.gO = (cio)var2.getFactory();
      }
   }

   bnw(bnu var1) {
      this(var1.Dw, var1.Uv, var1.oW);
   }

   @Override
   public boolean isLitteralTrue() {
      return this.Dw == null && this.Uv == this.gO.io && this.oW instanceof bmu && ((bmu)this.oW).isTrue();
   }

   @Override
   public boolean isLitteralFalse() {
      return this.Dw == null && this.Uv == this.gO.io && this.oW instanceof bmu && ((bmu)this.oW).isFalse();
   }

   @Override
   public boolean simplify() {
      boolean var1 = false;
      if (this.Dw instanceof bnw) {
         var1 = ((bnw)this.Dw).simplify();
      }

      if (this.oW instanceof bnw) {
         var1 = ((bnw)this.oW).simplify();
      }

      if (this.Dw != null && this.oW instanceof bmu && (this.Uv == this.gO.oQ || this.Uv == this.gO.xW)) {
         if (((bmu)this.oW).isTrue()) {
            this.Uv = this.Uv == this.gO.oQ ? this.gO.io : this.gO.qa;
            this.oW = this.Dw;
            this.Dw = null;
            return true;
         } else if (((bmu)this.oW).isFalse()) {
            this.Uv = this.Uv == this.gO.oQ ? this.gO.qa : this.gO.io;
            this.oW = this.Dw;
            this.Dw = null;
            return true;
         } else {
            return var1;
         }
      } else {
         return var1;
      }
   }

   @Override
   public void reverse() {
      if (this.Uv == this.gO.io) {
         this.Uv = this.gO.qa;
      } else if (this.Uv == this.gO.qa) {
         this.Uv = this.gO.io;
      } else if (this.Uv == this.gO.oQ) {
         this.Uv = this.gO.xW;
      } else if (this.Uv == this.gO.xW) {
         this.Uv = this.gO.oQ;
      } else if (this.Uv == this.gO.KT) {
         this.Uv = this.gO.Gf;
      } else if (this.Uv == this.gO.Gf) {
         this.Uv = this.gO.KT;
      } else if (this.Uv == this.gO.Ef) {
         this.Uv = this.gO.cC;
      } else if (this.Uv == this.gO.cC) {
         this.Uv = this.gO.Ef;
      } else if (this.Uv == this.gO.Hk) {
         ((bnw)this.Dw).reverse();
         this.Uv = this.gO.Me;
         ((bnw)this.oW).reverse();
      } else if (this.Uv == this.gO.Me) {
         ((bnw)this.Dw).reverse();
         this.Uv = this.gO.Hk;
         ((bnw)this.oW).reverse();
      } else {
         if (this.Uv != this.gO.PV) {
            throw new IllegalStateException("illegal operator for predicate");
         }

         this.oW = new bnw(this);
         this.Dw = null;
         this.Uv = this.gO.qa;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      super.generate(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Predicate;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bnw var2 = (bnw)var1;
         if (this.gO == null) {
            if (var2.gO != null) {
               return false;
            }
         } else if (!this.gO.equals(var2.gO)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return super.toString();
   }

   public bnw Dw() {
      bnu var1 = super.xK();
      bnw var2 = new bnw(var1);
      var2.gO = this.gO;
      this.q(var2);
      return var2;
   }
}
