package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate_LEGACY;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bjz extends bjx implements IJavaPredicate_LEGACY {
   @SerId(1)
   cdg UT;

   public bjz(IJavaExpression var1, IJavaOperator var2, IJavaExpression var3) {
      super(var1, var2, var3);
      if (!var2.isLogical()) {
         throw new IllegalArgumentException();
      } else {
         this.UT = (cdg)var2.getFactory();
      }
   }

   bjz(bjx var1) {
      this(var1.A, var1.kS, var1.wS);
   }

   @Override
   public boolean isLitteralTrue() {
      return this.A == null && this.kS == this.UT.xC && this.wS instanceof bix && ((bix)this.wS).isTrue();
   }

   @Override
   public boolean isLitteralFalse() {
      return this.A == null && this.kS == this.UT.xC && this.wS instanceof bix && ((bix)this.wS).isFalse();
   }

   @Override
   public boolean simplify() {
      boolean var1 = false;
      if (this.A instanceof bjz) {
         var1 = ((bjz)this.A).simplify();
      }

      if (this.wS instanceof bjz) {
         var1 = ((bjz)this.wS).simplify();
      }

      if (this.A != null && this.wS instanceof bix && (this.kS == this.UT.UO || this.kS == this.UT.Ab)) {
         if (((bix)this.wS).isTrue()) {
            this.kS = this.kS == this.UT.UO ? this.UT.xC : this.UT.ED;
            this.wS = this.A;
            this.A = null;
            return true;
         } else if (((bix)this.wS).isFalse()) {
            this.kS = this.kS == this.UT.UO ? this.UT.ED : this.UT.xC;
            this.wS = this.A;
            this.A = null;
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
      if (this.kS == this.UT.xC) {
         this.kS = this.UT.ED;
      } else if (this.kS == this.UT.ED) {
         this.kS = this.UT.xC;
      } else if (this.kS == this.UT.UO) {
         this.kS = this.UT.Ab;
      } else if (this.kS == this.UT.Ab) {
         this.kS = this.UT.UO;
      } else if (this.kS == this.UT.rl) {
         this.kS = this.UT.z;
      } else if (this.kS == this.UT.z) {
         this.kS = this.UT.rl;
      } else if (this.kS == this.UT.Ek) {
         this.kS = this.UT.hK;
      } else if (this.kS == this.UT.hK) {
         this.kS = this.UT.Ek;
      } else if (this.kS == this.UT.Sc) {
         ((bjz)this.A).reverse();
         this.kS = this.UT.ah;
         ((bjz)this.wS).reverse();
      } else if (this.kS == this.UT.ah) {
         ((bjz)this.A).reverse();
         this.kS = this.UT.Sc;
         ((bjz)this.wS).reverse();
      } else {
         if (this.kS != this.UT.eP) {
            throw new IllegalStateException("illegal operator for predicate");
         }

         this.wS = new bjz(this);
         this.A = null;
         this.kS = this.UT.ED;
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
      return 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
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
         bjz var2 = (bjz)var1;
         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return super.toString();
   }

   public bjz kS() {
      bjx var1 = super.A();
      bjz var2 = new bjz(var1);
      var2.UT = this.UT;
      this.pC(var2);
      return var2;
   }
}
