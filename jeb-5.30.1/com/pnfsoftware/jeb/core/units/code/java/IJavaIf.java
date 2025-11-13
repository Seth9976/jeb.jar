package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaIf extends IJavaCompound {
   int size();

   int sizeWithoutDefault();

   IJavaPredicate getBranchPredicate(int var1);

   List getBranchPredicates();

   IJavaBlock getBranchBody(int var1);

   IJavaBlock getDefaultBlock();

   boolean hasDefaultBlock();

   void setBranchPredicate(int var1, IJavaPredicate var2);

   void setBranchBody(int var1, IJavaBlock var2);

   IJavaBlock setDefaultBlock(IJavaBlock var1);

   void addBranch(IJavaPredicate var1, IJavaBlock var2);

   void insertBranch(int var1, IJavaPredicate var2, IJavaBlock var3);

   void removeBranch(int var1);

   IJavaIf duplicate();
}
