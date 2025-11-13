package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

@Ser
public class aab extends BranchTarget {
   @SerId(1)
   private Set pC = new TreeSet();

   public aab() {
   }

   public aab(CodePointer var1, Collection var2) {
      super(var1);
      this.pC.addAll(var2);
   }

   public Set pC() {
      return new TreeSet(this.pC);
   }
}
