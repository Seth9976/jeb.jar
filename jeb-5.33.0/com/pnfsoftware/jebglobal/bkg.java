package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCatchBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class bkg extends bil implements IJavaTry {
   @SerId(1)
   private IJavaBlock UT;
   @SerId(2)
   private List E;
   @SerId(3)
   private IJavaBlock sY;
   @SerId(4)
   public List wS;

   public bkg(IJavaBlock var1) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.UT = var1;
         this.E = new ArrayList();
      }
   }

   @Override
   public boolean isTryWithResource() {
      return this.wS != null && !this.wS.isEmpty();
   }

   @Override
   public List getResourceAcqs() {
      return this.wS == null ? Collections.emptyList() : Collections.unmodifiableList(this.wS);
   }

   @Override
   public void addResourceAcq(IJavaExpression var1) {
      int var2 = this.wS == null ? 0 : this.wS.size();
      this.addResourceAcq(var2, var1);
   }

   @Override
   public void addResourceAcq(int var1, IJavaExpression var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("The resource-acquisition expression cannot be null");
      } else if ((this.wS != null || var1 == 0) && (this.wS == null || var1 >= 0 && var1 <= this.wS.size())) {
         if (this.wS == null) {
            this.wS = new ArrayList();
         }

         this.wS.add(var1, var2);
      } else {
         throw new IllegalArgumentException("Illegal index");
      }
   }

   @Override
   public void removeResourceAcq(int var1) {
      if (this.wS != null && var1 >= 0 && var1 < this.wS.size()) {
         this.wS.remove(var1);
         if (this.wS.isEmpty()) {
            this.wS = null;
         }
      } else {
         throw new IllegalArgumentException("Illegal index");
      }
   }

   @Override
   public void setTryBody(IJavaBlock var1) {
      this.UT = var1;
   }

   @Override
   public IJavaBlock getTryBody() {
      return this.UT;
   }

   @Override
   public void addCatchBlock(int var1, IJavaType var2, List var3, IJavaIdentifier var4, IJavaDefinition var5, IJavaBlock var6) {
      this.E.add(var1, new biv(var2, var3, var4, var5, var6));
   }

   @Override
   public void addCatchBlock(IJavaType var1, List var2, IJavaIdentifier var3, IJavaDefinition var4, IJavaBlock var5) {
      this.E.add(new biv(var1, var2, var3, var4, var5));
   }

   @Override
   public IJavaCatchBlock getCatchBlock(int var1) {
      return (IJavaCatchBlock)this.E.get(var1);
   }

   @Override
   public IJavaCatchBlock removeCatchBlock(int var1) {
      return (IJavaCatchBlock)this.E.remove(var1);
   }

   @Override
   public int getCatchCount() {
      return this.E.size();
   }

   @Override
   public IJavaType getCatchType(int var1) {
      return ((IJavaCatchBlock)this.E.get(var1)).getType();
   }

   @Override
   public int getCatchByType(String var1) {
      int var2 = 0;

      for (IJavaCatchBlock var4 : this.E) {
         if (var4.canCatch(var1)) {
            return var2;
         }

         var2++;
      }

      return -1;
   }

   @Override
   public IJavaBlock getCatchBody(int var1) {
      return ((IJavaCatchBlock)this.E.get(var1)).getBlock();
   }

   @Override
   public IJavaIdentifier getCatchIdentifier(int var1) {
      return ((IJavaCatchBlock)this.E.get(var1)).getIdentifier();
   }

   @Override
   public void setFinallyBlock(IJavaBlock var1) {
      this.sY = var1;
   }

   @Override
   public IJavaBlock getFinallyBlock() {
      return this.sY;
   }

   @Override
   public boolean hasFinallyBlock() {
      return this.sY != null;
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      if (this.UT.insertAt(var1, var2)) {
         return true;
      } else {
         for (IJavaCatchBlock var4 : this.E) {
            if (var4.getBlock().insertAt(var1, var2)) {
               return true;
            }
         }

         return this.sY != null ? this.sY.insertAt(var1, var2) : false;
      }
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.UT);

      for (IJavaCatchBlock var3 : this.E) {
         var1.add(var3.getBlock());
      }

      if (this.sY != null) {
         var1.add(this.sY);
      }

      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new boi(this, this.wS));
      var1.addAll(this.UT.generateFlatList());
      var1.add(new bol());

      for (IJavaCatchBlock var3 : this.E) {
         var1.add(new boj(var3.getType(), var3.getIdentifier(), var3.getDefinition(), var3.getAdditionalCaughtTypes()));
         var1.addAll(var3.getBlock().generateFlatList());
      }

      if (this.sY != null) {
         var1.add(new bom());
         var1.addAll(this.sY.generateFlatList());
      }

      var1.add(new bok());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      ArrayList var2 = new ArrayList();
      bhr.pC(var2, this.wS);
      if (var1) {
         for (IJavaCatchBlock var4 : this.E) {
            bhr.pC(var2, var4.getIdentifier());
         }
      } else {
         bhr.pC(var2, this.UT);

         for (IJavaCatchBlock var6 : this.E) {
            bhr.pC(var2, var6.getIdentifier());
            bhr.pC(var2, var6.getBlock());
         }

         bhr.pC(var2, this.sY);
      }

      return var2;
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.UT == var1) {
         if (!(var2 instanceof IJavaBlock)) {
            return false;
         } else {
            this.UT = (IJavaBlock)var2;
            return true;
         }
      } else {
         for (IJavaCatchBlock var4 : this.E) {
            if (var4.getIdentifier() == var1) {
               if (var2 != null && !(var2 instanceof IJavaIdentifier)) {
                  return false;
               }

               var4.setIdentifier((IJavaIdentifier)var2);
               return true;
            }

            if (var4.getBlock() == var1) {
               if (!(var2 instanceof IJavaBlock)) {
                  return false;
               }

               var4.setBlock((IJavaBlock)var2);
               return true;
            }
         }

         if (this.sY == var1) {
            if (var2 != null && !(var2 instanceof IJavaBlock)) {
               return false;
            } else {
               this.sY = (IJavaBlock)var2;
               return true;
            }
         } else {
            if (this.wS != null) {
               if (!(var2 instanceof IJavaExpression)) {
                  return false;
               }

               for (int var5 = 0; var5 < this.wS.size(); var5++) {
                  IJavaExpression var6 = (IJavaExpression)this.wS.get(var5);
                  if (var6 == var1) {
                     this.wS.set(var5, (IJavaExpression)var2);
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }

   @Override
   public void reset() {
      this.UT = null;
      this.E.clear();
      this.sY = null;
      this.wS = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      var1.appendKeyword(JavaKeyword.TRY);
      if (this.isTryWithResource()) {
         var1.paren();
         int var2 = 0;

         for (IJavaExpression var4 : this.wS) {
            if (var2 > 0) {
               var1.append(";");
               var1.space();
            }

            var4.generate(var1);
            var2++;
         }

         var1.parenClose();
      }

      var1.space();
      this.UT.generate(var1);

      for (IJavaCatchBlock var10 : this.E) {
         var1.eol();
         InstructionCoordinates var11 = null;
         if (var10.getDefinition() != null && var10.getDefinition().hasPhysicalOffset()) {
            var11 = new InstructionCoordinates(
               var10.getDefinition().getPhysicalMethodIndex() >= 0 ? var10.getDefinition().getPhysicalMethodIndex() : var1.getCurrentMethodIndex(),
               var10.getDefinition().getPhysicalOffset()
            );
            var1.setEolCoordinates(var11);
            var1.recordCurrentCoordinates(var11);
            var1.loadCommentInline(var11);
         }

         var1.appendKeyword(JavaKeyword.CATCH);
         var1.paren();
         int var5 = 0;

         for (IJavaType var7 : var10.getCaughtTypes()) {
            if (var5 >= 1) {
               var1.append("| ");
            }

            bhn.pC(var1, var7);
            var1.space();
            var5++;
         }

         if (var10.getIdentifier() != null) {
            var10.getIdentifier().generate(var1, true);
         } else {
            var1.append("unused_ex");
         }

         var1.parenClose();
         var1.space();
         if (var11 != null) {
            var1.unrecordCurrentCoordinates();
         }

         var10.getBlock().generate(var1);
      }

      if (this.sY != null) {
         var1.eol();
         InstructionCoordinates var9 = null;
         if (this.sY.hasPhysicalOffset()) {
            var9 = new InstructionCoordinates(
               this.sY.getPhysicalMethodIndex() >= 0 ? this.sY.getPhysicalMethodIndex() : var1.getCurrentMethodIndex(), this.sY.getPhysicalOffset()
            );
            var1.setEolCoordinates(var9);
            var1.recordCurrentCoordinates(var9);
            var1.loadCommentInline(var9);
         }

         var1.appendKeyword(JavaKeyword.FINALLY);
         var1.space();
         if (var9 != null) {
            var1.unrecordCurrentCoordinates();
         }

         this.sY.generate(var1);
      }

      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Try;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.E == null ? 0 : this.E.hashCode());
      var1 = 31 * var1 + (this.sY == null ? 0 : this.sY.hashCode());
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
         bkg var2 = (bkg)var1;
         if (this.E == null) {
            if (var2.E != null) {
               return false;
            }
         } else if (!this.E.equals(var2.E)) {
            return false;
         }

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
      return "try { ... }";
   }

   public bkg A() {
      throw new RuntimeException("Duplication of try-statements is not supported");
   }
}
