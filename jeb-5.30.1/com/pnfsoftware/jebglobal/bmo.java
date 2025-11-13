package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

@Ser
public class bmo extends bmi implements IJavaBlock {
   @SerId(1)
   private List oW = new ArrayList();

   public bmo() {
   }

   public bmo(IJavaStatement var1) {
      this();
      this.add(var1);
   }

   @Override
   public int size() {
      return this.oW.size();
   }

   @Override
   public boolean isEmpty() {
      return this.oW.isEmpty();
   }

   @Override
   public void add(IJavaStatement var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW.add(var1);
      }
   }

   @Override
   public IJavaStatement get(int var1) {
      return (IJavaStatement)this.oW.get(var1);
   }

   @Override
   public IJavaStatement getLast() {
      return (IJavaStatement)this.oW.get(this.oW.size() - 1);
   }

   @Override
   public IJavaStatement removeLast() {
      return this.remove(this.oW.size() - 1);
   }

   @Override
   public IJavaStatement remove(int var1) {
      return (IJavaStatement)this.oW.remove(var1);
   }

   @Override
   public void removeMultiple(int var1, int var2) {
      while (var2-- > 0) {
         this.oW.remove(var1);
      }
   }

   @Override
   public void removeRange(int var1, int var2) {
      this.removeMultiple(var1, var2 - var1);
   }

   @Override
   public boolean remove(IJavaStatement var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         return this.oW.remove(var1);
      }
   }

   @Override
   public void set(int var1, IJavaStatement var2) {
      this.remove(var1);
      this.insert(var1, (bml)var2);
   }

   @Override
   public void insert(int var1, IJavaStatement var2) {
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW.add(var1, var2);
      }
   }

   @Override
   public void insertAll(int var1, IJavaBlock var2) {
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         for (IJavaStatement var4 : var2) {
            this.insert(var1, var4);
            var1++;
         }
      }
   }

   @Override
   public void addAll(IJavaBlock var1) {
      this.insertAll(this.size(), var1);
   }

   @Override
   public void insertMultiple(int var1, IJavaBlock var2, int var3, int var4, boolean var5) {
      if (var2 == this) {
         throw new IllegalArgumentException("src and dst blocks cannot be the same");
      } else {
         for (int var6 = var4 - 1; var6 >= var3; var6--) {
            IJavaStatement var7 = var5 ? var2.remove(var6) : var2.get(var6);
            this.insert(var1, var7);
         }
      }
   }

   @Override
   public void addMultiple(IJavaBlock var1, int var2, int var3, boolean var4) {
      this.insertMultiple(this.size(), var1, var2, var3, var4);
   }

   @Override
   public void clear() {
      this.oW.clear();
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      return q(this.oW, var1, var2);
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new brp(this));

      for (IJavaStatement var3 : this.oW) {
         if (var3 instanceof IJavaCompound) {
            var1.addAll(((IJavaCompound)var3).generateFlatList());
         } else {
            var1.add(var3);
         }
      }

      var1.add(new brq());
      return var1;
   }

   @Override
   public Iterator iterator() {
      return this.oW.iterator();
   }

   @Override
   public List getSubElements(boolean var1) {
      return blo.q(this.oW);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      for (int var3 = 0; var3 < this.oW.size(); var3++) {
         if (this.oW.get(var3) == var1) {
            if (!(var2 instanceof IJavaStatement)) {
               return false;
            }

            this.oW.set(var3, (IJavaStatement)var2);
            return true;
         }
      }

      return false;
   }

   @Override
   public void reset() {
      this.oW.clear();
   }

   @Override
   public void generateBody(JavaOutputSink var1) {
      this.generateBody(var1, false);
   }

   @Override
   public void generateBody(JavaOutputSink var1, boolean var2) {
      try {
         this.q(var1, var2);
      } catch (ConcurrentModificationException var4) {
         throw var4;
      }
   }

   private void q(JavaOutputSink var1, boolean var2) {
      if (var2) {
         Assert.a(this.oW.size() == 1);
      }

      int var3 = 0;

      for (IJavaStatement var5 : this.oW) {
         InstructionCoordinates var6 = new InstructionCoordinates(
            var5.getPhysicalMethodIndex() >= 0 ? var5.getPhysicalMethodIndex() : var1.getCurrentMethodIndex(), var5.getPhysicalOffset()
         );
         var1.setEolCoordinates(var6);
         boolean var7 = var5 instanceof bnp && var1.getIndentationLevel() >= 0;
         if (var7) {
            var1.decrementIndentationLevel();
         }

         if (!var2) {
            var1.renderPreComment(var6);
         }

         var1.loadCommentInline(var6);
         if (var5 instanceof bml && ((bml)var5).Uv != null) {
            var1.onEolAddComment(((bml)var5).Uv);
         }

         var1.setEolCoordinates(var6);
         var1.recordLineCoordinates(var6);
         if (var2 && var5 instanceof IJavaReturn) {
            IJavaExpression var8 = ((IJavaReturn)var5).getExpression();
            if (var8 != null) {
               var8.generate(var1);
            } else {
               var1.brace();
               var1.braceClose();
            }
         } else {
            var5.generate(var1);
         }

         if (var7) {
            var1.incrementIndentationLevel();
         }

         if (!var2) {
            if (!(var5 instanceof IJavaLabel) && (!(var5 instanceof IJavaCompound) || var5 instanceof IJavaDoWhile)) {
               var1.append(";");
            }

            var1.eol();
            if (var5 instanceof IJavaCompound && var1.getInsertBlankLinesAfterCompounds() && var3 + 1 < this.oW.size()) {
               var1.eol();
            }
         }

         var1.unrecordLineCoordinates();
         var3++;
      }
   }

   @Override
   public void generateHeader(JavaOutputSink var1) {
      this.q(var1);
      var1.brace();
      var1.eol();
      var1.incrementIndentationLevel();
   }

   @Override
   public void generateFooter(JavaOutputSink var1) {
      var1.decrementIndentationLevel();
      var1.braceClose();
      this.RF(var1);
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.generateHeader(var1);
      this.generateBody(var1, false);
      this.generateFooter(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Block;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
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
         bmo var2 = (bmo)var1;
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
   public String toShortString() {
      int var1 = this.oW.size();
      String var2 = var1 <= 1 ? S.L("statement") : S.L("statements");
      return Strings.ff("{ /* %d %s */ }", var1, var2);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (IJavaStatement var4 : this.oW) {
         if (var2 > 0) {
            var1.append(Strings.LINESEP);
         }

         var1.append(var4);
         var2++;
      }

      return var1.toString();
   }

   public bmo Dw() {
      bmo var1 = new bmo();
      var1.oW = this.RF(this.oW);
      this.q(var1);
      return var1;
   }
}
