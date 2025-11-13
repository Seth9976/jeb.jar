package com.pnfsoftware.jeb.core.units.codeobject;

import java.util.Arrays;
import java.util.List;

public abstract class ELFRelocationContext {
   private final List applicableRelocations;

   public ELFRelocationContext(ELFRelocationApplicator... var1) {
      this.applicableRelocations = Arrays.asList(var1);
   }

   public List getApplicableRelocations() {
      return this.applicableRelocations;
   }

   public abstract boolean canApply(IELFUnit var1, long var2);

   protected boolean isMappedAtPreferredAddress(IELFUnit var1, long var2) {
      return var1.getLoaderInformation().getImageBase() == var2;
   }
}
