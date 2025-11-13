package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CKeyword;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class aer extends adm implements ICIfStm {
   @SerId(1)
   List A = new ArrayList();
   @SerId(2)
   List kS = new ArrayList();
   @SerId(3)
   ICBlock wS = null;

   aer(ICPredicate var1, ICStatement var2) {
      if (var1 != null && var2 != null) {
         this.addBranch(var1, new adr(var2));
      } else {
         throw new IllegalArgumentException();
      }
   }

   aer(ICPredicate var1, ICBlock var2) {
      if (var1 != null && var2 != null) {
         this.addBranch(var1, var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public aer A() {
      Assert.a(this.A.size() > 0 && this.kS.size() > 0);
      aer var1 = new aer(((ICPredicate)this.A.get(0)).duplicate(), ((ICBlock)this.kS.get(0)).duplicate());
      super.pC(var1);
      var1.wS = this.wS != null ? this.wS.duplicate() : null;

      for (int var2 = 1; var2 < this.A.size(); var2++) {
         ICPredicate var3 = ((ICPredicate)this.A.get(var2)).duplicate();
         ICBlock var4 = ((ICBlock)this.kS.get(var2)).duplicate();
         var1.addBranch(var3, var4);
      }

      return var1;
   }

   @Override
   public void reset() {
      this.A.clear();
      this.kS.clear();
      this.wS = null;
   }

   @Override
   public void addBranch(ICPredicate var1, ICBlock var2) {
      if (var1 != null && var2 != null) {
         this.A.add(var1);
         this.kS.add(var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void insertBranch(int var1, ICPredicate var2, ICBlock var3) {
      if (var2 != null && var3 != null) {
         this.A.add(var1, var2);
         this.kS.add(var1, var3);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public ICPredicate getBranchPredicate(int var1) {
      return (ICPredicate)this.A.get(var1);
   }

   @Override
   public List getBranchPredicates() {
      return this.A;
   }

   @Override
   public void setBranchPredicate(int var1, ICPredicate var2) {
      this.A.set(var1, var2);
   }

   @Override
   public List getConditionalBlocks() {
      return Collections.unmodifiableList(this.kS);
   }

   @Override
   public ICBlock getBranchBody(int var1) {
      return (ICBlock)this.kS.get(var1);
   }

   @Override
   public void setBranchBody(int var1, ICBlock var2) {
      this.kS.set(var1, var2);
   }

   @Override
   public void removeBranch(int var1) {
      this.A.remove(var1);
      this.kS.remove(var1);
   }

   @Override
   public ICBlock getDefaultBlock() {
      return this.wS;
   }

   @Override
   public void setDefaultBlock(ICBlock var1) {
      this.wS = var1;
   }

   @Override
   public boolean hasDefaultBlock() {
      return this.wS != null;
   }

   @Override
   public int sizeWithoutDefault() {
      return this.A.size();
   }

   @Override
   public int size() {
      return this.sizeWithoutDefault() + (this.hasDefaultBlock() ? 1 : 0);
   }

   @Override
   public boolean insertAt(long var1, ICStatement var3) {
      for (ICBlock var5 : this.kS) {
         if (var5.insertAt(var1, var3)) {
            return true;
         }
      }

      return this.wS != null ? this.wS.insertAt(var1, var3) : false;
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();

      for (ICBlock var3 : this.kS) {
         var1.add(var3);
      }

      if (this.wS != null) {
         var1.add(this.wS);
      }

      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new afy(this, (ICPredicate)this.A.get(0)));
      var1.addAll(((ICBlock)this.kS.get(0)).generateFlatList());
      int var2 = this.A.size();

      for (int var3 = 1; var3 < var2; var3++) {
         var1.add(new afz((ICPredicate)this.A.get(var3)));
         var1.addAll(((ICBlock)this.kS.get(var3)).generateFlatList());
      }

      if (this.wS != null) {
         var1.add(new aga());
         var1.addAll(this.wS.generateFlatList());
      }

      var1.add(new agb());
      return var1;
   }

   @Override
   public List getSubElements() {
      List var1 = afm.pC(this.A);
      afm.pC(var1, this.kS);
      return afm.pC(var1, this.wS);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      for (int var3 = 0; var3 < this.A.size(); var3++) {
         if (this.A.get(var3) == var1) {
            if (!(var2 instanceof ICPredicate)) {
               return false;
            }

            this.A.set(var3, (ICPredicate)var2);
            return true;
         }
      }

      for (int var4 = 0; var4 < this.kS.size(); var4++) {
         if (this.kS.get(var4) == var1) {
            if (!(var2 instanceof ICBlock)) {
               return false;
            }

            this.kS.set(var4, (ICBlock)var2);
            return true;
         }
      }

      if (this.wS != var1) {
         return false;
      } else if (var2 != null && !(var2 instanceof ICBlock)) {
         return false;
      } else {
         this.wS = (ICBlock)var2;
         return true;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);

      for (int var2 = 0; var2 < this.A.size(); var2++) {
         if (var2 >= 1) {
            var1.eol();
            var1.appendKeyword(CKeyword.ELSE);
            var1.append(" ");
         }

         var1.appendKeyword(CKeyword.IF);
         var1.paren();
         ((ICPredicate)this.A.get(var2)).generate(var1);
         var1.parenClose();
         var1.space();
         ((ICBlock)this.kS.get(var2)).generate(var1);
      }

      if (this.wS != null) {
         var1.eol();
         var1.appendKeyword(CKeyword.ELSE);
         var1.append(" ");
         this.wS.generate(var1);
      }

      this.A(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.If;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         aer var2 = (aer)var1;
         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("if(%s) { .... ", this.getBranchPredicate(0));
   }
}
