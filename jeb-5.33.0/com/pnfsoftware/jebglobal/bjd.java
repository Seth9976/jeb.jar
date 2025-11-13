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
public class bjd extends bil implements IJavaFor {
   @SerId(1)
   private IJavaStatement wS;
   @SerId(2)
   private IJavaExpression UT;
   @SerId(3)
   private IJavaStatement E;
   @SerId(4)
   private IJavaBlock sY;

   @SerCustomInitPostGraph
   private void wS() {
      if (this.UT instanceof bjz var1) {
         this.UT = new bjy(new bjx(var1.A, var1.kS, var1.wS));
      }
   }

   public bjd(IJavaStatement var1, IJavaPredicate var2, IJavaStatement var3, IJavaBlock var4) {
      if (var4 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
         this.UT = var2;
         this.E = var3;
         this.sY = var4;
      }
   }

   @Override
   public IJavaStatement getInitializer() {
      return this.A();
   }

   public IJavaStatement A() {
      return this.wS;
   }

   @Override
   public IJavaPredicate getPredicate() {
      return (bjy)this.UT;
   }

   @Override
   public IJavaStatement getPostStatement() {
      return this.E;
   }

   @Override
   public IJavaBlock getBody() {
      return this.sY;
   }

   @Override
   public void setInitializer(IJavaStatement var1) {
      this.wS = var1;
   }

   @Override
   public void setPredicate(IJavaPredicate var1) {
      this.UT = var1;
   }

   @Override
   public void setPostStatement(IJavaStatement var1) {
      this.E = var1;
   }

   @Override
   public void setBody(IJavaBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.sY = var1;
      }
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      throw new RuntimeException();
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.sY);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new bns(this, this.wS, (bjy)this.UT, this.E));
      var1.addAll(this.sY.generateFlatList());
      var1.add(new bnv());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      return var1 ? bhr.pC(this.wS, this.UT, this.E) : bhr.pC(this.wS, this.UT, this.E, this.sY);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.wS == var1) {
         if (!(var2 instanceof bio)) {
            return false;
         } else {
            this.wS = (bio)var2;
            return true;
         }
      } else if (this.UT == var1) {
         if (!(var2 instanceof bjy)) {
            return false;
         } else {
            this.UT = (bjy)var2;
            return true;
         }
      } else if (this.E == var1) {
         if (!(var2 instanceof bio)) {
            return false;
         } else {
            this.E = (bio)var2;
            return true;
         }
      } else if (this.sY == var1) {
         if (!(var2 instanceof bir)) {
            return false;
         } else {
            this.sY = (bir)var2;
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
      this.E = null;
      this.sY = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      var1.appendKeyword(JavaKeyword.FOR);
      var1.paren();
      if (this.wS != null) {
         this.wS.generate(var1);
      }

      var1.append("; ");
      if (this.UT != null) {
         this.UT.generate(var1);
      }

      var1.append("; ");
      if (this.E != null) {
         this.E.generate(var1);
      }

      var1.parenClose();
      var1.space();
      this.sY.generate(var1);
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.For;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.sY == null ? 0 : this.sY.hashCode());
      var1 = 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
      var1 = 31 * var1 + (this.E == null ? 0 : this.E.hashCode());
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
         bjd var2 = (bjd)var1;
         if (this.sY == null) {
            if (var2.sY != null) {
               return false;
            }
         } else if (!this.sY.equals(var2.sY)) {
            return false;
         }

         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         if (this.E == null) {
            if (var2.E != null) {
               return false;
            }
         } else if (!this.E.equals(var2.E)) {
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
      return "for(" + this.wS + "; " + this.UT + "; " + this.E + ") " + this.sY.toShortString();
   }

   public bjd kS() {
      bjd var1 = new bjd(
         this.wS == null ? null : this.wS.duplicate(),
         this.UT == null ? null : ((bjy)this.UT).A(),
         this.E == null ? null : this.E.duplicate(),
         this.sY.duplicate()
      );
      this.pC(var1);
      return var1;
   }
}
