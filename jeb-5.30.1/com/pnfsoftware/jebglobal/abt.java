package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

@Ser
public class abt extends BranchTarget {
   @SerId(1)
   private Set q = new TreeSet();

   public abt() {
   }

   public abt(CodePointer var1, Collection var2) {
      super(var1);
      this.q.addAll(var2);
   }

   public Set q() {
      return new TreeSet(this.q);
   }
}
