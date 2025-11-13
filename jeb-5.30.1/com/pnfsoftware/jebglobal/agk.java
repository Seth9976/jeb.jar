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
public class agk extends aff implements ICIfStm {
   @SerId(1)
   List RF = new ArrayList();
   @SerId(2)
   List xK = new ArrayList();
   @SerId(3)
   ICBlock Dw = null;

   agk(ICPredicate var1, ICStatement var2) {
      if (var1 != null && var2 != null) {
         this.addBranch(var1, new afk(var2));
      } else {
         throw new IllegalArgumentException();
      }
   }

   agk(ICPredicate var1, ICBlock var2) {
      if (var1 != null && var2 != null) {
         this.addBranch(var1, var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public agk RF() {
      Assert.a(this.RF.size() > 0 && this.xK.size() > 0);
      agk var1 = new agk(((ICPredicate)this.RF.get(0)).duplicate(), ((ICBlock)this.xK.get(0)).duplicate());
      super.q(var1);
      var1.Dw = this.Dw != null ? this.Dw.duplicate() : null;

      for (int var2 = 1; var2 < this.RF.size(); var2++) {
         ICPredicate var3 = ((ICPredicate)this.RF.get(var2)).duplicate();
         ICBlock var4 = ((ICBlock)this.xK.get(var2)).duplicate();
         var1.addBranch(var3, var4);
      }

      return var1;
   }

   @Override
   public void reset() {
      this.RF.clear();
      this.xK.clear();
      this.Dw = null;
   }

   @Override
   public void addBranch(ICPredicate var1, ICBlock var2) {
      if (var1 != null && var2 != null) {
         this.RF.add(var1);
         this.xK.add(var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void insertBranch(int var1, ICPredicate var2, ICBlock var3) {
      if (var2 != null && var3 != null) {
         this.RF.add(var1, var2);
         this.xK.add(var1, var3);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public ICPredicate getBranchPredicate(int var1) {
      return (ICPredicate)this.RF.get(var1);
   }

   @Override
   public List getBranchPredicates() {
      return this.RF;
   }

   @Override
   public void setBranchPredicate(int var1, ICPredicate var2) {
      this.RF.set(var1, var2);
   }

   @Override
   public List getConditionalBlocks() {
      return Collections.unmodifiableList(this.xK);
   }

   @Override
   public ICBlock getBranchBody(int var1) {
      return (ICBlock)this.xK.get(var1);
   }

   @Override
   public void setBranchBody(int var1, ICBlock var2) {
      this.xK.set(var1, var2);
   }

   @Override
   public void removeBranch(int var1) {
      this.RF.remove(var1);
      this.xK.remove(var1);
   }

   @Override
   public ICBlock getDefaultBlock() {
      return this.Dw;
   }

   @Override
   public void setDefaultBlock(ICBlock var1) {
      this.Dw = var1;
   }

   @Override
   public boolean hasDefaultBlock() {
      return this.Dw != null;
   }

   @Override
   public int sizeWithoutDefault() {
      return this.RF.size();
   }

   @Override
   public int size() {
      return this.sizeWithoutDefault() + (this.hasDefaultBlock() ? 1 : 0);
   }

   @Override
   public boolean insertAt(long var1, ICStatement var3) {
      for (ICBlock var5 : this.xK) {
         if (var5.insertAt(var1, var3)) {
            return true;
         }
      }

      return this.Dw != null ? this.Dw.insertAt(var1, var3) : false;
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();

      for (ICBlock var3 : this.xK) {
         var1.add(var3);
      }

      if (this.Dw != null) {
         var1.add(this.Dw);
      }

      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new ahr(this, (ICPredicate)this.RF.get(0)));
      var1.addAll(((ICBlock)this.xK.get(0)).generateFlatList());
      int var2 = this.RF.size();

      for (int var3 = 1; var3 < var2; var3++) {
         var1.add(new ahs((ICPredicate)this.RF.get(var3)));
         var1.addAll(((ICBlock)this.xK.get(var3)).generateFlatList());
      }

      if (this.Dw != null) {
         var1.add(new aht());
         var1.addAll(this.Dw.generateFlatList());
      }

      var1.add(new ahu());
      return var1;
   }

   @Override
   public List getSubElements() {
      List var1 = ahf.q(this.RF);
      ahf.q(var1, this.xK);
      return ahf.q(var1, this.Dw);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      for (int var3 = 0; var3 < this.RF.size(); var3++) {
         if (this.RF.get(var3) == var1) {
            if (!(var2 instanceof ICPredicate)) {
               return false;
            }

            this.RF.set(var3, (ICPredicate)var2);
            return true;
         }
      }

      for (int var4 = 0; var4 < this.xK.size(); var4++) {
         if (this.xK.get(var4) == var1) {
            if (!(var2 instanceof ICBlock)) {
               return false;
            }

            this.xK.set(var4, (ICBlock)var2);
            return true;
         }
      }

      if (this.Dw != var1) {
         return false;
      } else if (var2 != null && !(var2 instanceof ICBlock)) {
         return false;
      } else {
         this.Dw = (ICBlock)var2;
         return true;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);

      for (int var2 = 0; var2 < this.RF.size(); var2++) {
         if (var2 >= 1) {
            var1.eol();
            var1.appendKeyword(CKeyword.ELSE);
            var1.append(" ");
         }

         var1.appendKeyword(CKeyword.IF);
         var1.paren();
         ((ICPredicate)this.RF.get(var2)).generate(var1);
         var1.parenClose();
         var1.space();
         ((ICBlock)this.xK.get(var2)).generate(var1);
      }

      if (this.Dw != null) {
         var1.eol();
         var1.appendKeyword(CKeyword.ELSE);
         var1.append(" ");
         this.Dw.generate(var1);
      }

      this.RF(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.If;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         agk var2 = (agk)var1;
         if (this.xK == null) {
            if (var2.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var2.xK)) {
            return false;
         }

         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
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
