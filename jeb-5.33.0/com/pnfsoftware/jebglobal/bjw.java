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
public class bjw extends bio implements IJavaNewArray {
   @SerId(1)
   private IJavaType wS;
   @SerId(2)
   private List UT;
   @SerId(3)
   private List E;

   private bjw(IJavaType var1, List var2, List var3) {
      this.wS = var1;
      this.UT = var2;
      this.E = var3;
   }

   public bjw(IJavaType var1, IJavaExpression var2) {
      if (var1 != null && var2 != null) {
         this.wS = var1;
         this.UT = new ArrayList();
         this.UT.add(var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public bjw(IJavaType var1, boolean var2, List var3) {
      if (var3 == null) {
         throw new IllegalArgumentException();
      } else if (var1 == null && var2) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
         if (var2) {
            if (var3.isEmpty()) {
               throw new IllegalArgumentException();
            }

            this.UT = var3;
         } else {
            this.E = var3;
         }
      }
   }

   @Override
   public IJavaType getType() {
      return this.wS;
   }

   @Override
   public List getSizes() {
      return this.UT;
   }

   @Override
   public boolean hasInitialValues() {
      return this.E != null;
   }

   @Override
   public List getInitialValues() {
      return this.E;
   }

   @Override
   public List getSubElements() {
      List var1 = bhr.pC(this.UT);
      return bhr.pC(var1, this.E);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.UT != null) {
         for (int var3 = 0; var3 < this.UT.size(); var3++) {
            if (this.UT.get(var3) == var1) {
               if (!(var2 instanceof IJavaExpression)) {
                  return false;
               }

               this.UT.set(var3, (IJavaExpression)var2);
               return true;
            }
         }
      }

      if (this.E != null) {
         for (int var4 = 0; var4 < this.E.size(); var4++) {
            if (this.E.get(var4) == var1) {
               if (!(var2 instanceof IJavaExpression)) {
                  return false;
               }

               this.E.set(var4, (IJavaExpression)var2);
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      IJavaElement var2 = var1.astParent();
      this.pC(var1);
      if (this.E == null) {
         var1.appendKeyword(JavaKeyword.NEW);
         var1.append(" ");
         bhn.pC(var1, this.wS.getArrayElementType());

         for (IJavaExpression var4 : this.UT) {
            var1.bracket();
            var4.generate(var1);
            var1.bracketClose();
         }

         for (int var6 = this.UT.size(); var6 < this.wS.getDimensions(); var6++) {
            var1.bracket();
            var1.bracketClose();
         }
      } else {
         if (this.wS != null) {
            boolean var7 = var2 instanceof biq && ((biq)var2).getLeft() instanceof bja && ((biq)var2).getRight() == this;
            if (!var7) {
               var1.appendKeyword(JavaKeyword.NEW);
               var1.append(" ");
               bhn.pC(var1, this.wS);
            }
         }

         var1.brace();
         int var8 = 0;

         for (IJavaExpression var5 : this.E) {
            if (var8 > 0) {
               var1.append(", ");
            }

            var5.generate(var1);
            var8++;
         }

         var1.braceClose();
      }

      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.NewArray;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.E == null ? 0 : this.E.hashCode());
      var1 = 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
      return 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
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
         bjw var2 = (bjw)var1;
         if (this.E == null) {
            if (var2.E != null) {
               return false;
            }
         } else if (!this.E.equals(var2.E)) {
            return false;
         }

         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("new ");
      var1.append(this.wS);
      if (this.UT != null) {
         var1.append(" / " + S.L("dimensions") + ": ");

         for (IJavaExpression var3 : this.UT) {
            var1.append("[" + var3 + "]");
         }
      }

      if (this.E != null) {
         var1.append(" / " + S.L("initials") + ": ");
         int var5 = 0;

         for (IJavaExpression var4 : this.E) {
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
      return "new " + this.wS;
   }

   public bjw A() {
      bjw var1 = new bjw(this.wS, pC(this.UT), pC(this.E));
      this.pC(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return true;
   }
}
