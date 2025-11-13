package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
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
public class bjh extends bil implements IJavaIf {
   @SerId(1)
   private List wS;
   @SerId(2)
   private List UT;
   @SerId(3)
   private IJavaBlock E;

   @SerCustomInitPostGraph
   private void kS() {
      for (int var1 = 0; var1 < this.wS.size(); var1++) {
         IJavaExpression var2 = (IJavaExpression)this.wS.get(var1);
         if (var2 instanceof bjz var3) {
            this.wS.set(var1, new bjy(new bjx(var3.A, var3.kS, var3.wS)));
         }
      }
   }

   private bjh(List var1, List var2, IJavaBlock var3) {
      this.wS = var1;
      this.UT = var2;
      this.E = var3;
   }

   public bjh(IJavaPredicate var1, IJavaBlock var2) {
      if (var1 != null && var2 != null) {
         this.wS = new ArrayList();
         this.UT = new ArrayList();
         this.E = null;
         this.addBranch(var1, var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void addBranch(IJavaPredicate var1, IJavaBlock var2) {
      if (var1 != null && var2 != null) {
         this.wS.add(var1);
         this.UT.add(var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void insertBranch(int var1, IJavaPredicate var2, IJavaBlock var3) {
      if (var2 != null && var3 != null) {
         this.wS.add(var1, var2);
         this.UT.add(var1, var3);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaPredicate getBranchPredicate(int var1) {
      return (bjy)this.wS.get(var1);
   }

   @Override
   public List getBranchPredicates() {
      return this.wS;
   }

   @Override
   public void setBranchPredicate(int var1, IJavaPredicate var2) {
      this.wS.set(var1, var2);
   }

   @Override
   public IJavaBlock getBranchBody(int var1) {
      return (IJavaBlock)this.UT.get(var1);
   }

   @Override
   public void setBranchBody(int var1, IJavaBlock var2) {
      this.UT.set(var1, (bir)var2);
   }

   @Override
   public void removeBranch(int var1) {
      this.wS.remove(var1);
      this.UT.remove(var1);
   }

   @Override
   public boolean hasDefaultBlock() {
      return this.E != null;
   }

   @Override
   public IJavaBlock getDefaultBlock() {
      return this.E;
   }

   @Override
   public IJavaBlock setDefaultBlock(IJavaBlock var1) {
      IJavaBlock var2 = this.E;
      this.E = var1;
      return var2;
   }

   @Override
   public int size() {
      return this.wS.size() + (this.E != null ? 1 : 0);
   }

   @Override
   public int sizeWithoutDefault() {
      return this.wS.size();
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      for (IJavaBlock var4 : this.UT) {
         if (var4.insertAt(var1, var2)) {
            return true;
         }
      }

      return this.E != null ? this.E.insertAt(var1, var2) : false;
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();

      for (IJavaBlock var3 : this.UT) {
         var1.add(var3);
      }

      if (this.E != null) {
         var1.add(this.E);
      }

      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new bnw(this, (bjy)this.wS.get(0)));
      var1.addAll(((IJavaBlock)this.UT.get(0)).generateFlatList());
      int var2 = this.wS.size();

      for (int var3 = 1; var3 < var2; var3++) {
         var1.add(new bnx((bjy)this.wS.get(var3)));
         var1.addAll(((IJavaBlock)this.UT.get(var3)).generateFlatList());
      }

      if (this.E != null) {
         var1.add(new bny());
         var1.addAll(this.E.generateFlatList());
      }

      var1.add(new bnz());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      List var2 = bhr.pC(this.wS);
      if (!var1) {
         bhr.pC(var2, this.UT);
         bhr.pC(var2, this.E);
      }

      return var2;
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      for (int var3 = 0; var3 < this.wS.size(); var3++) {
         if (this.wS.get(var3) == var1) {
            if (!(var2 instanceof bjy)) {
               return false;
            }

            this.wS.set(var3, (bjy)var2);
            return true;
         }
      }

      for (int var4 = 0; var4 < this.UT.size(); var4++) {
         if (this.UT.get(var4) == var1) {
            if (!(var2 instanceof bir)) {
               return false;
            }

            this.UT.set(var4, (bir)var2);
            return true;
         }
      }

      if (this.E != var1) {
         return false;
      } else if (var2 != null && !(var2 instanceof bir)) {
         return false;
      } else {
         this.E = (bir)var2;
         return true;
      }
   }

   @Override
   public void reset() {
      this.wS.clear();
      this.UT.clear();
      this.E = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);

      for (int var2 = 0; var2 < this.wS.size(); var2++) {
         if (var2 >= 1) {
            var1.eol();
         }

         bjy var3 = (bjy)this.wS.get(var2);
         IJavaBlock var4 = (IJavaBlock)this.UT.get(var2);
         InstructionCoordinates var5 = new InstructionCoordinates(
            var3.getPhysicalMethodIndex() >= 0 ? var3.getPhysicalMethodIndex() : var1.getCurrentMethodIndex(), var3.getPhysicalOffset()
         );
         var1.setEolCoordinates(var5);
         var1.recordCurrentCoordinates(var5);
         var1.loadCommentInline(var5);
         if (var2 >= 1) {
            var1.appendKeyword(JavaKeyword.ELSE);
            var1.space();
         }

         var1.appendKeyword(JavaKeyword.IF);
         var1.paren();
         var3.generate(var1);
         var1.parenClose();
         var1.space();
         var1.unrecordCurrentCoordinates();
         var4.generate(var1);
      }

      if (this.E != null) {
         var1.eol();
         var1.appendKeyword(JavaKeyword.ELSE);
         var1.space();
         this.E.generate(var1);
      }

      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.If;
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
         bjh var2 = (bjh)var1;
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
      StringBuilder var1 = new StringBuilder();

      for (int var2 = 0; var2 < this.wS.size(); var2++) {
         if (var2 > 0) {
            var1.append("\nelse ");
         }

         var1.append("if(").append(this.wS.get(var2)).append(") ").append(((IJavaBlock)this.UT.get(var2)).toShortString());
      }

      if (this.E != null) {
         var1.append("\nelse ").append(this.E.toShortString());
      }

      return var1.toString();
   }

   public bjh A() {
      bjh var1 = new bjh(this.kS(this.wS), this.kS(this.UT), this.E == null ? null : this.E.duplicate());
      this.pC(var1);
      return var1;
   }
}
