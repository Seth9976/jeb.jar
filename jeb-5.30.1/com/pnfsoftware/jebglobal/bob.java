package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSynchronizedBlock;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bob extends bmi implements IJavaSynchronizedBlock {
   @SerId(value = 1, deprecated = true)
   private bnc oW;
   @SerId(2)
   private IJavaBlock gO;
   @SerId(3)
   private IJavaExpression nf;

   @SerCustomInitPostGraph
   private void Uv() {
      if (this.nf == null) {
         this.nf = this.oW;
         this.oW = null;
      }
   }

   public bob(IJavaExpression var1, IJavaBlock var2) {
      if (var1 != null && var2 != null) {
         this.nf = var1;
         this.gO = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaExpression getLock() {
      return this.nf;
   }

   @Override
   public IJavaBlock getBody() {
      return this.gO;
   }

   @Override
   public void setLock(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.nf = var1;
      }
   }

   @Override
   public void setBody(IJavaBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.gO = (bmo)var1;
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
      var1.add(new bsh(this, this.nf));
      var1.addAll(this.gO.generateFlatList());
      var1.add(new bsi());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      return var1 ? blo.q(this.nf) : blo.q(this.nf, this.gO);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.gO == var1) {
         if (!(var2 instanceof bmo)) {
            return false;
         } else {
            this.gO = (bmo)var2;
            return true;
         }
      } else if (this.nf == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.nf = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void reset() {
      this.gO = null;
      this.nf = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      var1.appendKeyword(JavaKeyword.SYNCHRONIZED);
      var1.paren();
      this.nf.generate(var1);
      var1.parenClose();
      var1.space();
      this.gO.generate(var1);
      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.SynchronizedBlock;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
      var1 = 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
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
         bob var2 = (bob)var1;
         if (this.gO == null) {
            if (var2.gO != null) {
               return false;
            }
         } else if (!this.gO.equals(var2.gO)) {
            return false;
         }

         if (this.nf == null) {
            if (var2.nf != null) {
               return false;
            }
         } else if (!this.nf.equals(var2.nf)) {
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
      return "synchronized(" + this.nf + ") " + this.gO.toShortString();
   }

   public bob Dw() {
      bob var1 = new bob(this.nf.duplicate(), this.gO.duplicate());
      this.q(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return true;
   }
}
