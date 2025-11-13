package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICIfStm extends ICConditionalStatement {
   List getBranchPredicates();

   ICPredicate getBranchPredicate(int var1);

   void setBranchPredicate(int var1, ICPredicate var2);

   ICBlock getBranchBody(int var1);

   void setBranchBody(int var1, ICBlock var2);

   void addBranch(ICPredicate var1, ICBlock var2);

   void insertBranch(int var1, ICPredicate var2, ICBlock var3);

   void removeBranch(int var1);

   @Override
   void setDefaultBlock(ICBlock var1);

   default ICBlock removeDefaultBlock() {
      ICBlock var1 = this.getDefaultBlock();
      this.setDefaultBlock(null);
      return var1;
   }

   ICIfStm duplicate();
}
