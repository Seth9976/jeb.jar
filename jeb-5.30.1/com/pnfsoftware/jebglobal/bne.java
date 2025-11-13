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
public class bne extends bmi implements IJavaIf {
   @SerId(1)
   private List oW;
   @SerId(2)
   private List gO;
   @SerId(3)
   private IJavaBlock nf;

   @SerCustomInitPostGraph
   private void Uv() {
      for (int var1 = 0; var1 < this.oW.size(); var1++) {
         IJavaExpression var2 = (IJavaExpression)this.oW.get(var1);
         if (var2 instanceof bnw var3) {
            this.oW.set(var1, new bnv(new bnu(var3.Dw, var3.Uv, var3.oW)));
         }
      }
   }

   private bne(List var1, List var2, IJavaBlock var3) {
      this.oW = var1;
      this.gO = var2;
      this.nf = var3;
   }

   public bne(IJavaPredicate var1, IJavaBlock var2) {
      if (var1 != null && var2 != null) {
         this.oW = new ArrayList();
         this.gO = new ArrayList();
         this.nf = null;
         this.addBranch(var1, var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void addBranch(IJavaPredicate var1, IJavaBlock var2) {
      if (var1 != null && var2 != null) {
         this.oW.add(var1);
         this.gO.add(var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void insertBranch(int var1, IJavaPredicate var2, IJavaBlock var3) {
      if (var2 != null && var3 != null) {
         this.oW.add(var1, var2);
         this.gO.add(var1, var3);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaPredicate getBranchPredicate(int var1) {
      return (bnv)this.oW.get(var1);
   }

   @Override
   public List getBranchPredicates() {
      return this.oW;
   }

   @Override
   public void setBranchPredicate(int var1, IJavaPredicate var2) {
      this.oW.set(var1, var2);
   }

   @Override
   public IJavaBlock getBranchBody(int var1) {
      return (IJavaBlock)this.gO.get(var1);
   }

   @Override
   public void setBranchBody(int var1, IJavaBlock var2) {
      this.gO.set(var1, (bmo)var2);
   }

   @Override
   public void removeBranch(int var1) {
      this.oW.remove(var1);
      this.gO.remove(var1);
   }

   @Override
   public boolean hasDefaultBlock() {
      return this.nf != null;
   }

   @Override
   public IJavaBlock getDefaultBlock() {
      return this.nf;
   }

   @Override
   public IJavaBlock setDefaultBlock(IJavaBlock var1) {
      IJavaBlock var2 = this.nf;
      this.nf = var1;
      return var2;
   }

   @Override
   public int size() {
      return this.oW.size() + (this.nf != null ? 1 : 0);
   }

   @Override
   public int sizeWithoutDefault() {
      return this.oW.size();
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      for (IJavaBlock var4 : this.gO) {
         if (var4.insertAt(var1, var2)) {
            return true;
         }
      }

      return this.nf != null ? this.nf.insertAt(var1, var2) : false;
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();

      for (IJavaBlock var3 : this.gO) {
         var1.add(var3);
      }

      if (this.nf != null) {
         var1.add(this.nf);
      }

      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new brx(this, (bnv)this.oW.get(0)));
      var1.addAll(((IJavaBlock)this.gO.get(0)).generateFlatList());
      int var2 = this.oW.size();

      for (int var3 = 1; var3 < var2; var3++) {
         var1.add(new bry((bnv)this.oW.get(var3)));
         var1.addAll(((IJavaBlock)this.gO.get(var3)).generateFlatList());
      }

      if (this.nf != null) {
         var1.add(new brz());
         var1.addAll(this.nf.generateFlatList());
      }

      var1.add(new bsa());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      List var2 = blo.q(this.oW);
      if (!var1) {
         blo.q(var2, this.gO);
         blo.q(var2, this.nf);
      }

      return var2;
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      for (int var3 = 0; var3 < this.oW.size(); var3++) {
         if (this.oW.get(var3) == var1) {
            if (!(var2 instanceof bnv)) {
               return false;
            }

            this.oW.set(var3, (bnv)var2);
            return true;
         }
      }

      for (int var4 = 0; var4 < this.gO.size(); var4++) {
         if (this.gO.get(var4) == var1) {
            if (!(var2 instanceof bmo)) {
               return false;
            }

            this.gO.set(var4, (bmo)var2);
            return true;
         }
      }

      if (this.nf != var1) {
         return false;
      } else if (var2 != null && !(var2 instanceof bmo)) {
         return false;
      } else {
         this.nf = (bmo)var2;
         return true;
      }
   }

   @Override
   public void reset() {
      this.oW.clear();
      this.gO.clear();
      this.nf = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);

      for (int var2 = 0; var2 < this.oW.size(); var2++) {
         if (var2 >= 1) {
            var1.eol();
         }

         bnv var3 = (bnv)this.oW.get(var2);
         IJavaBlock var4 = (IJavaBlock)this.gO.get(var2);
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

      if (this.nf != null) {
         var1.eol();
         var1.appendKeyword(JavaKeyword.ELSE);
         var1.space();
         this.nf.generate(var1);
      }

      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.If;
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
         bne var2 = (bne)var1;
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
      StringBuilder var1 = new StringBuilder();

      for (int var2 = 0; var2 < this.oW.size(); var2++) {
         if (var2 > 0) {
            var1.append("\nelse ");
         }

         var1.append("if(").append(this.oW.get(var2)).append(") ").append(((IJavaBlock)this.gO.get(var2)).toShortString());
      }

      if (this.nf != null) {
         var1.append("\nelse ").append(this.nf.toShortString());
      }

      return var1.toString();
   }

   public bne Dw() {
      bne var1 = new bne(this.xK(this.oW), this.xK(this.gO), this.nf == null ? null : this.nf.duplicate());
      this.q(var1);
      return var1;
   }
}
