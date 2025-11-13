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
public class bjc extends bil implements IJavaForEach {
   @SerId(1)
   private IJavaDefinition wS;
   @SerId(2)
   private IJavaExpression UT;
   @SerId(3)
   private IJavaBlock E;

   public bjc(IJavaDefinition var1, IJavaExpression var2, IJavaBlock var3) {
      if (var1 != null && var2 != null && var3 != null) {
         this.wS = var1;
         this.UT = var2;
         this.E = var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaDefinition getVariable() {
      return this.wS;
   }

   @Override
   public void setVariable(IJavaDefinition var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
      }
   }

   @Override
   public IJavaExpression getIterable() {
      return this.UT;
   }

   @Override
   public void setIterable(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.UT = var1;
      }
   }

   @Override
   public IJavaBlock getBody() {
      return this.E;
   }

   @Override
   public void setBody(IJavaBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.E = var1;
      }
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      throw new RuntimeException();
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.E);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new bnt(this, this.wS, this.UT));
      var1.addAll(this.E.generateFlatList());
      var1.add(new bnu());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      return var1 ? bhr.pC(this.wS, this.UT) : bhr.pC(this.wS, this.UT, this.E);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.wS == var1) {
         if (!(var2 instanceof IJavaDefinition)) {
            return false;
         } else {
            this.wS = (IJavaDefinition)var2;
            return true;
         }
      } else if (this.UT == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.UT = (IJavaExpression)var2;
            return true;
         }
      } else if (this.E == var1) {
         if (!(var2 instanceof IJavaBlock)) {
            return false;
         } else {
            this.E = (IJavaBlock)var2;
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
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      var1.appendKeyword(JavaKeyword.FOR);
      var1.paren();
      this.wS.generate(var1);
      var1.append(": ");
      this.UT.generate(var1);
      var1.parenClose();
      var1.space();
      this.E.generate(var1);
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.ForEach;
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
         bjc var2 = (bjc)var1;
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
      return "for(" + this.wS + ": " + this.UT + ") " + this.E.toShortString();
   }

   public bjc A() {
      bjc var1 = new bjc(this.wS.duplicate(), this.UT.duplicate(), this.E.duplicate());
      this.pC(var1);
      return var1;
   }
}
