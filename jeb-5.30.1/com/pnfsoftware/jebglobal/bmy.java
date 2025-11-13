package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bmy extends bmi implements IJavaDoWhile {
   @SerId(1)
   private IJavaBlock oW;
   @SerId(2)
   private IJavaExpression gO;

   @SerCustomInitPostGraph
   private void Uv() {
      if (this.gO instanceof bnw var1) {
         this.gO = new bnv(new bnu(var1.Dw, var1.Uv, var1.oW));
      }
   }

   public bmy(IJavaBlock var1, IJavaPredicate var2) {
      if (var1 != null && var2 != null) {
         this.oW = var1;
         this.gO = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaBlock getBody() {
      return this.oW;
   }

   @Override
   public IJavaPredicate getPredicate() {
      return (bnv)this.gO;
   }

   @Override
   public void setBody(IJavaBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW = var1;
      }
   }

   @Override
   public void setPredicate(IJavaPredicate var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.gO = var1;
      }
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      return this.oW.insertAt(var1, var2);
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.oW);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new brr(this));
      var1.addAll(this.oW.generateFlatList());
      var1.add(new brs((bnv)this.gO));
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      return var1 ? blo.q(this.gO) : blo.q(this.oW, this.gO);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.oW == var1) {
         if (!(var2 instanceof IJavaBlock)) {
            return false;
         } else {
            this.oW = (IJavaBlock)var2;
            return true;
         }
      } else if (this.gO == var1) {
         if (!(var2 instanceof IJavaPredicate)) {
            return false;
         } else {
            this.gO = (IJavaPredicate)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void reset() {
      this.gO = null;
      this.oW = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      var1.appendKeyword(JavaKeyword.DO);
      var1.append(" ");
      this.oW.generate(var1);
      var1.eol();
      InstructionCoordinates var2 = new InstructionCoordinates(
         this.gO.getPhysicalMethodIndex() >= 0 ? this.gO.getPhysicalMethodIndex() : var1.getCurrentMethodIndex(), this.gO.getPhysicalOffset()
      );
      var1.setEolCoordinates(var2);
      var1.recordCurrentCoordinates(var2);
      var1.loadCommentInline(var2);
      var1.appendKeyword(JavaKeyword.WHILE);
      var1.paren();
      this.gO.generate(var1);
      var1.parenClose();
      var1.unrecordCurrentCoordinates();
      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.DoWhile;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
      return 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
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
         bmy var2 = (bmy)var1;
         if (this.oW == null) {
            if (var2.oW != null) {
               return false;
            }
         } else if (!this.oW.equals(var2.oW)) {
            return false;
         }

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
      return "do " + this.oW.toShortString() + " while(" + this.gO + ")";
   }

   public bmy Dw() {
      bmy var1 = new bmy(this.oW.duplicate(), ((bnv)this.gO).xK());
      this.q(var1);
      return var1;
   }
}
