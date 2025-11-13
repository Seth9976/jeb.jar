package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaFor;
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
public class bna extends bmi implements IJavaFor {
   @SerId(1)
   private IJavaStatement oW;
   @SerId(2)
   private IJavaExpression gO;
   @SerId(3)
   private IJavaStatement nf;
   @SerId(4)
   private IJavaBlock gP;

   @SerCustomInitPostGraph
   private void oW() {
      if (this.gO instanceof bnw var1) {
         this.gO = new bnv(new bnu(var1.Dw, var1.Uv, var1.oW));
      }
   }

   public bna(IJavaStatement var1, IJavaPredicate var2, IJavaStatement var3, IJavaBlock var4) {
      if (var4 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW = var1;
         this.gO = var2;
         this.nf = var3;
         this.gP = var4;
      }
   }

   @Override
   public IJavaStatement getInitializer() {
      return this.Dw();
   }

   public IJavaStatement Dw() {
      return this.oW;
   }

   @Override
   public IJavaPredicate getPredicate() {
      return (bnv)this.gO;
   }

   @Override
   public IJavaStatement getPostStatement() {
      return this.nf;
   }

   @Override
   public IJavaBlock getBody() {
      return this.gP;
   }

   @Override
   public void setInitializer(IJavaStatement var1) {
      this.oW = var1;
   }

   @Override
   public void setPredicate(IJavaPredicate var1) {
      this.gO = var1;
   }

   @Override
   public void setPostStatement(IJavaStatement var1) {
      this.nf = var1;
   }

   @Override
   public void setBody(IJavaBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.gP = var1;
      }
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      throw new RuntimeException();
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.gP);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new brt(this, this.oW, (bnv)this.gO, this.nf));
      var1.addAll(this.gP.generateFlatList());
      var1.add(new brw());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      return var1 ? blo.q(this.oW, this.gO, this.nf) : blo.q(this.oW, this.gO, this.nf, this.gP);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.oW == var1) {
         if (!(var2 instanceof bml)) {
            return false;
         } else {
            this.oW = (bml)var2;
            return true;
         }
      } else if (this.gO == var1) {
         if (!(var2 instanceof bnv)) {
            return false;
         } else {
            this.gO = (bnv)var2;
            return true;
         }
      } else if (this.nf == var1) {
         if (!(var2 instanceof bml)) {
            return false;
         } else {
            this.nf = (bml)var2;
            return true;
         }
      } else if (this.gP == var1) {
         if (!(var2 instanceof bmo)) {
            return false;
         } else {
            this.gP = (bmo)var2;
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
      this.gP = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      var1.appendKeyword(JavaKeyword.FOR);
      var1.paren();
      if (this.oW != null) {
         this.oW.generate(var1);
      }

      var1.append("; ");
      if (this.gO != null) {
         this.gO.generate(var1);
      }

      var1.append("; ");
      if (this.nf != null) {
         this.nf.generate(var1);
      }

      var1.parenClose();
      var1.space();
      this.gP.generate(var1);
      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.For;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.gP == null ? 0 : this.gP.hashCode());
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
         bna var2 = (bna)var1;
         if (this.gP == null) {
            if (var2.gP != null) {
               return false;
            }
         } else if (!this.gP.equals(var2.gP)) {
            return false;
         }

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
      return "for(" + this.oW + "; " + this.gO + "; " + this.nf + ") " + this.gP.toShortString();
   }

   public bna Uv() {
      bna var1 = new bna(
         this.oW == null ? null : this.oW.duplicate(),
         this.gO == null ? null : ((bnv)this.gO).xK(),
         this.nf == null ? null : this.nf.duplicate(),
         this.gP.duplicate()
      );
      this.q(var1);
      return var1;
   }
}
