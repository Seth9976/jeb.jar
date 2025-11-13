package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaForEach;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bmz extends bmi implements IJavaForEach {
   @SerId(1)
   private IJavaDefinition oW;
   @SerId(2)
   private IJavaExpression gO;
   @SerId(3)
   private IJavaBlock nf;

   public bmz(IJavaDefinition var1, IJavaExpression var2, IJavaBlock var3) {
      if (var1 != null && var2 != null && var3 != null) {
         this.oW = var1;
         this.gO = var2;
         this.nf = var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaDefinition getVariable() {
      return this.oW;
   }

   @Override
   public void setVariable(IJavaDefinition var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW = var1;
      }
   }

   @Override
   public IJavaExpression getIterable() {
      return this.gO;
   }

   @Override
   public void setIterable(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.gO = var1;
      }
   }

   @Override
   public IJavaBlock getBody() {
      return this.nf;
   }

   @Override
   public void setBody(IJavaBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.nf = var1;
      }
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      throw new RuntimeException();
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.nf);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new bru(this, this.oW, this.gO));
      var1.addAll(this.nf.generateFlatList());
      var1.add(new brv());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      return var1 ? blo.q(this.oW, this.gO) : blo.q(this.oW, this.gO, this.nf);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.oW == var1) {
         if (!(var2 instanceof IJavaDefinition)) {
            return false;
         } else {
            this.oW = (IJavaDefinition)var2;
            return true;
         }
      } else if (this.gO == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.gO = (IJavaExpression)var2;
            return true;
         }
      } else if (this.nf == var1) {
         if (!(var2 instanceof IJavaBlock)) {
            return false;
         } else {
            this.nf = (IJavaBlock)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void reset() {
      this.oW = null;
      this.gO = null;
      this.nf = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      var1.appendKeyword(JavaKeyword.FOR);
      var1.paren();
      this.oW.generate(var1);
      var1.append(": ");
      this.gO.generate(var1);
      var1.parenClose();
      var1.space();
      this.nf.generate(var1);
      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.ForEach;
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
         bmz var2 = (bmz)var1;
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
      return "for(" + this.oW + ": " + this.gO + ") " + this.nf.toShortString();
   }

   public bmz Dw() {
      bmz var1 = new bmz(this.oW.duplicate(), this.gO.duplicate(), this.nf.duplicate());
      this.q(var1);
      return var1;
   }
}
