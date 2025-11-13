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
public class bjb extends bil implements IJavaDoWhile {
   @SerId(1)
   private IJavaBlock wS;
   @SerId(2)
   private IJavaExpression UT;

   @SerCustomInitPostGraph
   private void kS() {
      if (this.UT instanceof bjz var1) {
         this.UT = new bjy(new bjx(var1.A, var1.kS, var1.wS));
      }
   }

   public bjb(IJavaBlock var1, IJavaPredicate var2) {
      if (var1 != null && var2 != null) {
         this.wS = var1;
         this.UT = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaBlock getBody() {
      return this.wS;
   }

   @Override
   public IJavaPredicate getPredicate() {
      return (bjy)this.UT;
   }

   @Override
   public void setBody(IJavaBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
      }
   }

   @Override
   public void setPredicate(IJavaPredicate var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.UT = var1;
      }
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      return this.wS.insertAt(var1, var2);
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.wS);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new bnq(this));
      var1.addAll(this.wS.generateFlatList());
      var1.add(new bnr((bjy)this.UT));
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      return var1 ? bhr.pC(this.UT) : bhr.pC(this.wS, this.UT);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.wS == var1) {
         if (!(var2 instanceof IJavaBlock)) {
            return false;
         } else {
            this.wS = (IJavaBlock)var2;
            return true;
         }
      } else if (this.UT == var1) {
         if (!(var2 instanceof IJavaPredicate)) {
            return false;
         } else {
            this.UT = (IJavaPredicate)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void reset() {
      this.UT = null;
      this.wS = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      var1.appendKeyword(JavaKeyword.DO);
      var1.append(" ");
      this.wS.generate(var1);
      var1.eol();
      InstructionCoordinates var2 = new InstructionCoordinates(
         this.UT.getPhysicalMethodIndex() >= 0 ? this.UT.getPhysicalMethodIndex() : var1.getCurrentMethodIndex(), this.UT.getPhysicalOffset()
      );
      var1.setEolCoordinates(var2);
      var1.recordCurrentCoordinates(var2);
      var1.loadCommentInline(var2);
      var1.appendKeyword(JavaKeyword.WHILE);
      var1.paren();
      this.UT.generate(var1);
      var1.parenClose();
      var1.unrecordCurrentCoordinates();
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.DoWhile;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      return 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
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
         bjb var2 = (bjb)var1;
         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "do " + this.wS.toShortString() + " while(" + this.UT + ")";
   }

   public bjb A() {
      bjb var1 = new bjb(this.wS.duplicate(), ((bjy)this.UT).A());
      this.pC(var1);
      return var1;
   }
}
