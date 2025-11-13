package com.pnfsoftware.jeb.core.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ActionOverridesData extends ActionData {
   private List children;
   private List parents;

   public List getAddresses() {
      ArrayList var1 = new ArrayList();
      if (this.children != null) {
         var1.addAll((Collection)this.children.stream().map(var0 -> var0.getSignature()).collect(Collectors.toList()));
      }

      if (this.parents != null) {
         var1.addAll((Collection)this.parents.stream().map(var0 -> var0.getSignature()).collect(Collectors.toList()));
      }

      return var1;
   }

   public List getChildren() {
      return this.children == null ? Collections.emptyList() : Collections.unmodifiableList(this.children);
   }

   public List getParents() {
      return this.parents == null ? Collections.emptyList() : Collections.unmodifiableList(this.parents);
   }

   public void setItems(List var1, List var2) {
      this.children = var1;
      this.parents = var2;
   }
}
