package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bnu extends bmk implements IJavaOperation {
   @SerId(1)
   protected IJavaExpression Dw;
   @SerId(2)
   protected IJavaOperator Uv;
   @SerId(3)
   protected IJavaExpression oW;

   public bnu(IJavaExpression var1, IJavaOperator var2, IJavaExpression var3) {
      if (var2 != null && var3 != null) {
         if (var2.isUnary()) {
            if (var1 != null) {
               throw new IllegalArgumentException();
            }
         } else if (var2.isBinary() && var1 == null) {
            throw new IllegalArgumentException();
         }

         this.Dw = var1;
         this.Uv = var2;
         this.oW = var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int getCountOfOperands() {
      return this.Dw == null ? 1 : 2;
   }

   @Override
   public IJavaExpression getLeft() {
      return this.Dw;
   }

   @Override
   public void setLeft(IJavaExpression var1) {
      this.Dw = var1;
   }

   @Override
   public IJavaExpression getRight() {
      return this.oW;
   }

   @Override
   public void setRight(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW = var1;
      }
   }

   @Override
   public IJavaOperator getOperator() {
      return this.Uv;
   }

   @Override
   public void setOperator(IJavaOperator var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Uv = var1;
      }
   }

   @Override
   public List getSubElements() {
      return blo.q(this.Dw, this.oW);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.Dw == var1) {
         if (var2 != null && !(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.Dw = (IJavaExpression)var2;
            return true;
         }
      } else if (this.oW == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.oW = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      IJavaElement var2 = this.q(var1);
      if (this.Uv.isCast()) {
         boolean var3 = var2 instanceof bmn && ((bmn)var2).getRight() == this || var2 instanceof bnx || var2 instanceof boc;
         boolean var4 = !var3;
         if (var4) {
            var1.paren();
         }

         var1.paren();
         blk.q(var1, this.Uv.getCastType());
         var1.parenClose();
         boolean var5 = this.oW instanceof bnu && !(this.oW instanceof bnc) && !(this.oW instanceof bmu);
         if (var5) {
            var1.paren();
         }

         this.oW.generate(var1);
         if (var5) {
            var1.parenClose();
         }

         if (var4) {
            var1.parenClose();
         }
      } else {
         boolean var11 = this.Uv.getOperatorType() == JavaOperatorType.CONCAT && var2 instanceof bmq && ((bmq)var2).getArgument(0) == this;
         if (var11) {
            var1.paren();
         }

         IJavaOperator var12 = this.Dw != null && this.Dw instanceof bnu ? ((bnu)this.Dw).getOperator() : null;
         IJavaOperator var13 = this.oW instanceof bnu ? ((bnu)this.oW).getOperator() : null;
         if (this.Dw != null) {
            boolean var6;
            if (this.Dw instanceof IJavaAssignment) {
               var6 = true;
            } else if (var12 != null && !var12.isCast()) {
               try {
                  int var7 = this.Uv.getPrecedenceDelta(var12);
                  var6 = var7 < 0 || var7 == 0 && this.Uv.getAssociativity() != JavaOperatorType.Associativity.LEFT;
               } catch (Exception var10) {
                  var6 = true;
               }
            } else {
               var6 = false;
            }

            if (var6) {
               var1.paren();
            }

            this.Dw.generate(var1);
            if (var6) {
               var1.parenClose();
            }
         }

         String var14 = this.Uv.toString();
         if (var14.length() > 0) {
            if (!this.Uv.is(JavaOperatorType.INSTANCEOF) && !this.Uv.isUnary()) {
               var1.append(" " + var14 + " ");
            } else {
               var1.append(var14);
            }
         }

         boolean var15;
         if (var13 != null && !var13.isCast()) {
            try {
               int var8 = this.Uv.getPrecedenceDelta(var13);
               var15 = var8 < 0 || var8 == 0 && this.Uv.getAssociativity() != JavaOperatorType.Associativity.RIGHT;
            } catch (Exception var9) {
               var15 = true;
            }
         } else {
            var15 = false;
         }

         if (var15) {
            var1.paren();
         }

         this.oW.generate(var1);
         if (var15) {
            var1.parenClose();
         }

         if (var11) {
            var1.parenClose();
         }
      }

      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Operation;
   }

   @Override
   public boolean canReverse() {
      return this.Uv.getReverse() != null;
   }

   @Override
   public boolean reverse(IJavaOperatorFactory var1) {
      IJavaOperator var2 = this.Uv.getReverse();
      if (var2 == null) {
         return false;
      } else {
         this.Uv = var2;
         return true;
      }
   }

   @Override
   public boolean canMirror() {
      return this.Dw != null && this.oW != null && this.Uv.getMirror() != null;
   }

   @Override
   public boolean mirror(IJavaOperatorFactory var1) {
      if (this.Dw != null && this.oW != null) {
         IJavaOperator var2 = this.Uv.getMirror();
         if (var2 == null) {
            return false;
         } else {
            this.Uv = var2;
            IJavaExpression var3 = this.Dw;
            this.Dw = this.oW;
            this.oW = var3;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + this.oW.hashCode();
      return 31 * var1 + this.Uv.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         bnu var2 = (bnu)var1;
         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

         return this.Uv.equals(var2.Uv) && this.oW.equals(var2.oW);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      String var1;
      if (this.Dw == null) {
         String var2 = this.Uv.toString();
         if (var2.length() != 0) {
            var2 = var2 + ", ";
         }

         var1 = "(" + var2 + this.oW + ")";
      } else {
         var1 = "(" + this.Dw;
         if (!(this.Dw instanceof IJavaIdentifier) && !(this.Dw instanceof IJavaConstant)) {
            var1 = var1 + ",";
         }

         Object var4 = var1 + " ";
         var4 = var4 + this.Uv;
         if (!(this.oW instanceof IJavaIdentifier) && !(this.oW instanceof IJavaConstant)) {
            var4 = var4 + ",";
         }

         var1 = var4 + " " + this.oW + ")";
      }

      return var1;
   }

   public bnu xK() {
      bnu var1 = new bnu(this.Dw == null ? null : this.Dw.duplicate(), this.Uv, this.oW.duplicate());
      this.q(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return this.Uv.canCauseException();
   }
}
