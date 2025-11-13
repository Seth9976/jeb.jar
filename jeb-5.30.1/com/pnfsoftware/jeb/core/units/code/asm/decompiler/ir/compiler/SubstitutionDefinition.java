package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.util.format.Strings;

public class SubstitutionDefinition {
   INode pattern;
   INode replacement;

   public SubstitutionDefinition(INode var1, INode var2) {
      this.pattern = var1;
      this.replacement = var2;
   }

   public INode getPattern() {
      return this.pattern;
   }

   public INode getReplacement() {
      return this.replacement;
   }

   @Override
   public String toString() {
      return Strings.ff("%s  ==>  %s", this.pattern, this.replacement);
   }
}
