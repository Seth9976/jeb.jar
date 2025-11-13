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
public class bjx extends bin implements IJavaOperation {
   @SerId(1)
   protected IJavaExpression A;
   @SerId(2)
   protected IJavaOperator kS;
   @SerId(3)
   protected IJavaExpression wS;

   public bjx(IJavaExpression var1, IJavaOperator var2, IJavaExpression var3) {
      if (var2 != null && var3 != null) {
         if (var2.isUnary()) {
            if (var1 != null) {
               throw new IllegalArgumentException();
            }
         } else if (var2.isBinary() && var1 == null) {
            throw new IllegalArgumentException();
         }

         this.A = var1;
         this.kS = var2;
         this.wS = var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int getCountOfOperands() {
      return this.A == null ? 1 : 2;
   }

   @Override
   public IJavaExpression getLeft() {
      return this.A;
   }

   @Override
   public void setLeft(IJavaExpression var1) {
      this.A = var1;
   }

   @Override
   public IJavaExpression getRight() {
      return this.wS;
   }

   @Override
   public void setRight(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
      }
   }

   @Override
   public IJavaOperator getOperator() {
      return this.kS;
   }

   @Override
   public void setOperator(IJavaOperator var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.kS = var1;
      }
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.A, this.wS);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.A == var1) {
         if (var2 != null && !(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.A = (IJavaExpression)var2;
            return true;
         }
      } else if (this.wS == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.wS = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      IJavaElement var2 = this.pC(var1);
      if (this.kS.isCast()) {
         boolean var3 = var2 instanceof biq && ((biq)var2).getRight() == this || var2 instanceof bka || var2 instanceof bkf;
         boolean var4 = !var3;
         if (var4) {
            var1.paren();
         }

         var1.paren();
         bhn.pC(var1, this.kS.getCastType());
         var1.parenClose();
         boolean var5 = this.wS instanceof bjx && !(this.wS instanceof bjf) && !(this.wS instanceof bix);
         if (var5) {
            var1.paren();
         }

         this.wS.generate(var1);
         if (var5) {
            var1.parenClose();
         }

         if (var4) {
            var1.parenClose();
         }
      } else {
         boolean var11 = this.kS.getOperatorType() == JavaOperatorType.CONCAT && var2 instanceof bit && ((bit)var2).getArgument(0) == this;
         if (var11) {
            var1.paren();
         }

         IJavaOperator var12 = this.A != null && this.A instanceof bjx ? ((bjx)this.A).getOperator() : null;
         IJavaOperator var13 = this.wS instanceof bjx ? ((bjx)this.wS).getOperator() : null;
         if (this.A != null) {
            boolean var6;
            if (this.A instanceof IJavaAssignment) {
               var6 = true;
            } else if (var12 != null && !var12.isCast()) {
               try {
                  int var7 = this.kS.getPrecedenceDelta(var12);
                  var6 = var7 < 0 || var7 == 0 && this.kS.getAssociativity() != JavaOperatorType.Associativity.LEFT;
               } catch (Exception var10) {
                  var6 = true;
               }
            } else {
               var6 = false;
            }

            if (var6) {
               var1.paren();
            }

            this.A.generate(var1);
            if (var6) {
               var1.parenClose();
            }
         }

         String var14 = this.kS.toString();
         if (var14.length() > 0) {
            if (!this.kS.is(JavaOperatorType.INSTANCEOF) && !this.kS.isUnary()) {
               var1.append(" " + var14 + " ");
            } else {
               var1.append(var14);
            }
         }

         boolean var15;
         if (var13 != null && !var13.isCast()) {
            try {
               int var8 = this.kS.getPrecedenceDelta(var13);
               var15 = var8 < 0 || var8 == 0 && this.kS.getAssociativity() != JavaOperatorType.Associativity.RIGHT;
            } catch (Exception var9) {
               var15 = true;
            }
         } else {
            var15 = false;
         }

         if (var15) {
            var1.paren();
         }

         this.wS.generate(var1);
         if (var15) {
            var1.parenClose();
         }

         if (var11) {
            var1.parenClose();
         }
      }

      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Operation;
   }

   @Override
   public boolean canReverse() {
      return this.kS.getReverse() != null;
   }

   @Override
   public boolean reverse(IJavaOperatorFactory var1) {
      IJavaOperator var2 = this.kS.getReverse();
      if (var2 == null) {
         return false;
      } else {
         this.kS = var2;
         return true;
      }
   }

   @Override
   public boolean canMirror() {
      return this.A != null && this.wS != null && this.kS.getMirror() != null;
   }

   @Override
   public boolean mirror(IJavaOperatorFactory var1) {
      if (this.A != null && this.wS != null) {
         IJavaOperator var2 = this.kS.getMirror();
         if (var2 == null) {
            return false;
         } else {
            this.kS = var2;
            IJavaExpression var3 = this.A;
            this.A = this.wS;
            this.wS = var3;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + this.wS.hashCode();
      return 31 * var1 + this.kS.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         bjx var2 = (bjx)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return this.kS.equals(var2.kS) && this.wS.equals(var2.wS);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      String var1;
      if (this.A == null) {
         String var2 = this.kS.toString();
         if (var2.length() != 0) {
            var2 = var2 + ", ";
         }

         var1 = "(" + var2 + this.wS + ")";
      } else {
         var1 = "(" + this.A;
         if (!(this.A instanceof IJavaIdentifier) && !(this.A instanceof IJavaConstant)) {
            var1 = var1 + ",";
         }

         Object var4 = var1 + " ";
         var4 = var4 + this.kS;
         if (!(this.wS instanceof IJavaIdentifier) && !(this.wS instanceof IJavaConstant)) {
            var4 = var4 + ",";
         }

         var1 = var4 + " " + this.wS + ")";
      }

      return var1;
   }

   public bjx A() {
      bjx var1 = new bjx(this.A == null ? null : this.A.duplicate(), this.kS, this.wS.duplicate());
      this.pC(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return this.kS.canCauseException();
   }
}
