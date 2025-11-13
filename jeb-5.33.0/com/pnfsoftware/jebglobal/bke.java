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
public class bke extends bil implements IJavaSynchronizedBlock {
   @SerId(value = 1, deprecated = true)
   private bjf wS;
   @SerId(2)
   private IJavaBlock UT;
   @SerId(3)
   private IJavaExpression E;

   @SerCustomInitPostGraph
   private void kS() {
      if (this.E == null) {
         this.E = this.wS;
         this.wS = null;
      }
   }

   public bke(IJavaExpression var1, IJavaBlock var2) {
      if (var1 != null && var2 != null) {
         this.E = var1;
         this.UT = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaExpression getLock() {
      return this.E;
   }

   @Override
   public IJavaBlock getBody() {
      return this.UT;
   }

   @Override
   public void setLock(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.E = var1;
      }
   }

   @Override
   public void setBody(IJavaBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.UT = (bir)var1;
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
      var1.add(new bog(this, this.E));
      var1.addAll(this.UT.generateFlatList());
      var1.add(new boh());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      return var1 ? bhr.pC(this.E) : bhr.pC(this.E, this.UT);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.UT == var1) {
         if (!(var2 instanceof bir)) {
            return false;
         } else {
            this.UT = (bir)var2;
            return true;
         }
      } else if (this.E == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.E = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void reset() {
      this.UT = null;
      this.E = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      var1.appendKeyword(JavaKeyword.SYNCHRONIZED);
      var1.paren();
      this.E.generate(var1);
      var1.parenClose();
      var1.space();
      this.UT.generate(var1);
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.SynchronizedBlock;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
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
         bke var2 = (bke)var1;
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
      return "synchronized(" + this.E + ") " + this.UT.toShortString();
   }

   public bke A() {
      bke var1 = new bke(this.E.duplicate(), this.UT.duplicate());
      this.pC(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return true;
   }
}
