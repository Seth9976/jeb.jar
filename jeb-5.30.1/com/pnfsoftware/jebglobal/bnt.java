package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNewArray;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bnt extends bml implements IJavaNewArray {
   @SerId(1)
   private IJavaType oW;
   @SerId(2)
   private List gO;
   @SerId(3)
   private List nf;

   private bnt(IJavaType var1, List var2, List var3) {
      this.oW = var1;
      this.gO = var2;
      this.nf = var3;
   }

   public bnt(IJavaType var1, IJavaExpression var2) {
      if (var1 != null && var2 != null) {
         this.oW = var1;
         this.gO = new ArrayList();
         this.gO.add(var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public bnt(IJavaType var1, boolean var2, List var3) {
      if (var3 == null) {
         throw new IllegalArgumentException();
      } else if (var1 == null && var2) {
         throw new IllegalArgumentException();
      } else {
         this.oW = var1;
         if (var2) {
            if (var3.isEmpty()) {
               throw new IllegalArgumentException();
            }

            this.gO = var3;
         } else {
            this.nf = var3;
         }
      }
   }

   @Override
   public IJavaType getType() {
      return this.oW;
   }

   @Override
   public List getSizes() {
      return this.gO;
   }

   @Override
   public boolean hasInitialValues() {
      return this.nf != null;
   }

   @Override
   public List getInitialValues() {
      return this.nf;
   }

   @Override
   public List getSubElements() {
      List var1 = blo.q(this.gO);
      return blo.q(var1, this.nf);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.gO != null) {
         for (int var3 = 0; var3 < this.gO.size(); var3++) {
            if (this.gO.get(var3) == var1) {
               if (!(var2 instanceof IJavaExpression)) {
                  return false;
               }

               this.gO.set(var3, (IJavaExpression)var2);
               return true;
            }
         }
      }

      if (this.nf != null) {
         for (int var4 = 0; var4 < this.nf.size(); var4++) {
            if (this.nf.get(var4) == var1) {
               if (!(var2 instanceof IJavaExpression)) {
                  return false;
               }

               this.nf.set(var4, (IJavaExpression)var2);
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      IJavaElement var2 = var1.astParent();
      this.q(var1);
      if (this.nf == null) {
         var1.appendKeyword(JavaKeyword.NEW);
         var1.append(" ");
         blk.q(var1, this.oW.getArrayElementType());

         for (IJavaExpression var4 : this.gO) {
            var1.bracket();
            var4.generate(var1);
            var1.bracketClose();
         }

         for (int var6 = this.gO.size(); var6 < this.oW.getDimensions(); var6++) {
            var1.bracket();
            var1.bracketClose();
         }
      } else {
         if (this.oW != null) {
            boolean var7 = var2 instanceof bmn && ((bmn)var2).getLeft() instanceof bmx && ((bmn)var2).getRight() == this;
            if (!var7) {
               var1.appendKeyword(JavaKeyword.NEW);
               var1.append(" ");
               blk.q(var1, this.oW);
            }
         }

         var1.brace();
         int var8 = 0;

         for (IJavaExpression var5 : this.nf) {
            if (var8 > 0) {
               var1.append(", ");
            }

            var5.generate(var1);
            var8++;
         }

         var1.braceClose();
      }

      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.NewArray;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
      var1 = 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
      return 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bnt var2 = (bnt)var1;
         if (this.nf == null) {
            if (var2.nf != null) {
               return false;
            }
         } else if (!this.nf.equals(var2.nf)) {
            return false;
         }

         if (this.gO == null) {
            if (var2.gO != null) {
               return false;
            }
         } else if (!this.gO.equals(var2.gO)) {
            return false;
         }

         if (this.oW == null) {
            if (var2.oW != null) {
               return false;
            }
         } else if (!this.oW.equals(var2.oW)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("new ");
      var1.append(this.oW);
      if (this.gO != null) {
         var1.append(" / " + S.L("dimensions") + ": ");

         for (IJavaExpression var3 : this.gO) {
            var1.append("[" + var3 + "]");
         }
      }

      if (this.nf != null) {
         var1.append(" / " + S.L("initials") + ": ");
         int var5 = 0;

         for (IJavaExpression var4 : this.nf) {
            if (var5 >= 1) {
               var1.append(", ");
            }

            var1.append(var4);
            var5++;
         }
      }

      return var1.toString();
   }

   @Override
   public String toShortString() {
      return "new " + this.oW;
   }

   public bnt Dw() {
      bnt var1 = new bnt(this.oW, q(this.gO), q(this.nf));
      this.q(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return true;
   }
}
