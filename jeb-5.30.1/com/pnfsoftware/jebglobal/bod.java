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
public class bod extends bmi implements IJavaTry {
   @SerId(1)
   private IJavaBlock gO;
   @SerId(2)
   private List nf;
   @SerId(3)
   private IJavaBlock gP;
   @SerId(4)
   public List oW;

   public bod(IJavaBlock var1) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.gO = var1;
         this.nf = new ArrayList();
      }
   }

   @Override
   public boolean isTryWithResource() {
      return this.oW != null && !this.oW.isEmpty();
   }

   @Override
   public List getResourceAcqs() {
      return this.oW == null ? Collections.emptyList() : Collections.unmodifiableList(this.oW);
   }

   @Override
   public void addResourceAcq(IJavaExpression var1) {
      int var2 = this.oW == null ? 0 : this.oW.size();
      this.addResourceAcq(var2, var1);
   }

   @Override
   public void addResourceAcq(int var1, IJavaExpression var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("The resource-acquisition expression cannot be null");
      } else if ((this.oW != null || var1 == 0) && (this.oW == null || var1 >= 0 && var1 <= this.oW.size())) {
         if (this.oW == null) {
            this.oW = new ArrayList();
         }

         this.oW.add(var1, var2);
      } else {
         throw new IllegalArgumentException("Illegal index");
      }
   }

   @Override
   public void removeResourceAcq(int var1) {
      if (this.oW != null && var1 >= 0 && var1 < this.oW.size()) {
         this.oW.remove(var1);
         if (this.oW.isEmpty()) {
            this.oW = null;
         }
      } else {
         throw new IllegalArgumentException("Illegal index");
      }
   }

   @Override
   public void setTryBody(IJavaBlock var1) {
      this.gO = var1;
   }

   @Override
   public IJavaBlock getTryBody() {
      return this.gO;
   }

   public IJavaStatement q(int var1) {
      return this.gO.get(var1);
   }

   @Override
   public void addCatchBlock(int var1, IJavaType var2, List var3, IJavaIdentifier var4, IJavaDefinition var5, IJavaBlock var6) {
      this.nf.add(var1, new bms(var2, var3, var4, var5, var6));
   }

   @Override
   public void addCatchBlock(IJavaType var1, List var2, IJavaIdentifier var3, IJavaDefinition var4, IJavaBlock var5) {
      this.nf.add(new bms(var1, var2, var3, var4, var5));
   }

   @Override
   public IJavaCatchBlock getCatchBlock(int var1) {
      return (IJavaCatchBlock)this.nf.get(var1);
   }

   @Override
   public IJavaCatchBlock removeCatchBlock(int var1) {
      return (IJavaCatchBlock)this.nf.remove(var1);
   }

   @Override
   public int getCatchCount() {
      return this.nf.size();
   }

   @Override
   public IJavaType getCatchType(int var1) {
      return ((IJavaCatchBlock)this.nf.get(var1)).getType();
   }

   @Override
   public int getCatchByType(String var1) {
      int var2 = 0;

      for (IJavaCatchBlock var4 : this.nf) {
         if (var4.canCatch(var1)) {
            return var2;
         }

         var2++;
      }

      return -1;
   }

   @Override
   public IJavaBlock getCatchBody(int var1) {
      return ((IJavaCatchBlock)this.nf.get(var1)).getBlock();
   }

   @Override
   public IJavaIdentifier getCatchIdentifier(int var1) {
      return ((IJavaCatchBlock)this.nf.get(var1)).getIdentifier();
   }

   @Override
   public void setFinallyBlock(IJavaBlock var1) {
      this.gP = var1;
   }

   @Override
   public IJavaBlock getFinallyBlock() {
      return this.gP;
   }

   @Override
   public boolean hasFinallyBlock() {
      return this.gP != null;
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      if (this.gO.insertAt(var1, var2)) {
         return true;
      } else {
         for (IJavaCatchBlock var4 : this.nf) {
            if (var4.getBlock().insertAt(var1, var2)) {
               return true;
            }
         }

         return this.gP != null ? this.gP.insertAt(var1, var2) : false;
      }
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.gO);

      for (IJavaCatchBlock var3 : this.nf) {
         var1.add(var3.getBlock());
      }

      if (this.gP != null) {
         var1.add(this.gP);
      }

      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new bsj(this, this.oW));
      var1.addAll(this.gO.generateFlatList());
      var1.add(new bsm());

      for (IJavaCatchBlock var3 : this.nf) {
         var1.add(new bsk(var3.getType(), var3.getIdentifier(), var3.getDefinition(), var3.getAdditionalCaughtTypes()));
         var1.addAll(var3.getBlock().generateFlatList());
      }

      if (this.gP != null) {
         var1.add(new bsn());
         var1.addAll(this.gP.generateFlatList());
      }

      var1.add(new bsl());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      ArrayList var2 = new ArrayList();
      blo.q(var2, this.oW);
      if (var1) {
         for (IJavaCatchBlock var4 : this.nf) {
            blo.q(var2, var4.getIdentifier());
         }
      } else {
         blo.q(var2, this.gO);

         for (IJavaCatchBlock var6 : this.nf) {
            blo.q(var2, var6.getIdentifier());
            blo.q(var2, var6.getBlock());
         }

         blo.q(var2, this.gP);
      }

      return var2;
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.gO == var1) {
         if (!(var2 instanceof IJavaBlock)) {
            return false;
         } else {
            this.gO = (IJavaBlock)var2;
            return true;
         }
      } else {
         for (IJavaCatchBlock var4 : this.nf) {
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

         if (this.gP == var1) {
            if (var2 != null && !(var2 instanceof IJavaBlock)) {
               return false;
            } else {
               this.gP = (IJavaBlock)var2;
               return true;
            }
         } else {
            if (this.oW != null) {
               if (!(var2 instanceof IJavaExpression)) {
                  return false;
               }

               for (int var5 = 0; var5 < this.oW.size(); var5++) {
                  IJavaExpression var6 = (IJavaExpression)this.oW.get(var5);
                  if (var6 == var1) {
                     this.oW.set(var5, (IJavaExpression)var2);
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
      this.gO = null;
      this.nf.clear();
      this.gP = null;
      this.oW = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      var1.appendKeyword(JavaKeyword.TRY);
      if (this.isTryWithResource()) {
         var1.paren();
         int var2 = 0;

         for (IJavaExpression var4 : this.oW) {
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
      this.gO.generate(var1);

      for (IJavaCatchBlock var10 : this.nf) {
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

            blk.q(var1, var7);
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

      if (this.gP != null) {
         var1.eol();
         InstructionCoordinates var9 = null;
         if (this.gP.hasPhysicalOffset()) {
            var9 = new InstructionCoordinates(
               this.gP.getPhysicalMethodIndex() >= 0 ? this.gP.getPhysicalMethodIndex() : var1.getCurrentMethodIndex(), this.gP.getPhysicalOffset()
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

         this.gP.generate(var1);
      }

      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Try;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
      var1 = 31 * var1 + (this.gP == null ? 0 : this.gP.hashCode());
      var1 = 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
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
         bod var2 = (bod)var1;
         if (this.nf == null) {
            if (var2.nf != null) {
               return false;
            }
         } else if (!this.nf.equals(var2.nf)) {
            return false;
         }

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
      return "try { ... }";
   }

   public bod Dw() {
      throw new RuntimeException("Duplication of try-statements is not supported");
   }
}
