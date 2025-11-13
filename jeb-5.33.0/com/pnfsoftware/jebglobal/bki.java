package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bki extends bil implements IJavaWhile {
   @SerId(1)
   private IJavaExpression wS;
   @SerId(2)
   private IJavaBlock UT;

   @SerCustomInitPostGraph
   private void kS() {
      if (this.wS instanceof bjz var1) {
         this.wS = new bjy(new bjx(var1.A, var1.kS, var1.wS));
      }
   }

   public bki(IJavaPredicate var1, IJavaBlock var2) {
      if (var1 != null && var2 != null) {
         this.wS = var1;
         this.UT = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaPredicate getPredicate() {
      return (bjy)this.wS;
   }

   @Override
   public IJavaBlock getBody() {
      return this.UT;
   }

   @Override
   public void setPredicate(IJavaPredicate var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
      }
   }

   @Override
   public void setBody(IJavaBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.UT = var1;
      }
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      return this.UT.insertAt(var1, var2);
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.UT);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new bon(this, (bjy)this.wS));
      var1.addAll(this.UT.generateFlatList());
      var1.add(new boo());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      return var1 ? bhr.pC(this.wS) : bhr.pC(this.wS, this.UT);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.wS == var1) {
         if (!(var2 instanceof bjy)) {
            return false;
         } else {
            this.wS = (bjy)var2;
            return true;
         }
      } else if (this.UT == var1) {
         if (!(var2 instanceof bir)) {
            return false;
         } else {
            this.UT = (bir)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void reset() {
      this.wS = null;
      this.UT = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      var1.appendKeyword(JavaKeyword.WHILE);
      var1.paren();
      this.wS.generate(var1);
      var1.parenClose();
      var1.space();
      this.UT.generate(var1);
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.While;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
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
         bki var2 = (bki)var1;
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
      return "while(" + this.wS + ") " + this.UT.toShortString();
   }

   public bki A() {
      bki var1 = new bki(((bjy)this.wS).A(), this.UT.duplicate());
      this.pC(var1);
      return var1;
   }
}
