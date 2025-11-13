package com.pnfsoftware.jeb.core.units.code.asm.memory;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryChanges {
   List adds = new ArrayList();
   List mods = new ArrayList();

   public MemoryChanges(List var1, List var2) {
      if (var1 != null && var2 != null) {
         this.adds = var1;
         this.mods = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public List getAdditions() {
      return Collections.unmodifiableList(this.adds);
   }

   public List getModifications() {
      return Collections.unmodifiableList(this.mods);
   }

   @Override
   public String toString() {
      return Strings.ff("Diff: %d pages added, %d pages modified", this.adds.size(), this.mods.size());
   }
}
