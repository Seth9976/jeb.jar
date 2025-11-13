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
public class bof extends bmi implements IJavaWhile {
   @SerId(1)
   private IJavaExpression oW;
   @SerId(2)
   private IJavaBlock gO;

   @SerCustomInitPostGraph
   private void Uv() {
      if (this.oW instanceof bnw var1) {
         this.oW = new bnv(new bnu(var1.Dw, var1.Uv, var1.oW));
      }
   }

   public bof(IJavaPredicate var1, IJavaBlock var2) {
      if (var1 != null && var2 != null) {
         this.oW = var1;
         this.gO = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaPredicate getPredicate() {
      return (bnv)this.oW;
   }

   @Override
   public IJavaBlock getBody() {
      return this.gO;
   }

   @Override
   public void setPredicate(IJavaPredicate var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW = var1;
      }
   }

   @Override
   public void setBody(IJavaBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.gO = var1;
      }
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      return this.gO.insertAt(var1, var2);
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.gO);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new bso(this, (bnv)this.oW));
      var1.addAll(this.gO.generateFlatList());
      var1.add(new bsp());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      return var1 ? blo.q(this.oW) : blo.q(this.oW, this.gO);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.oW == var1) {
         if (!(var2 instanceof bnv)) {
            return false;
         } else {
            this.oW = (bnv)var2;
            return true;
         }
      } else if (this.gO == var1) {
         if (!(var2 instanceof bmo)) {
            return false;
         } else {
            this.gO = (bmo)var2;
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
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      var1.appendKeyword(JavaKeyword.WHILE);
      var1.paren();
      this.oW.generate(var1);
      var1.parenClose();
      var1.space();
      this.gO.generate(var1);
      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.While;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
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
         bof var2 = (bof)var1;
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
      return "while(" + this.oW + ") " + this.gO.toShortString();
   }

   public bof Dw() {
      bof var1 = new bof(((bnv)this.oW).xK(), this.gO.duplicate());
      this.q(var1);
      return var1;
   }
}
