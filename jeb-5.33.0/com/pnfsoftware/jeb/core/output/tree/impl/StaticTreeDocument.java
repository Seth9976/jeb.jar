package com.pnfsoftware.jeb.core.output.tree.impl;

import com.pnfsoftware.jeb.core.output.tree.INode;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Ser
public class StaticTreeDocument extends AbstractTreeDocument {
   @SerId(1)
   private List roots;
   @SerId(2)
   private List columnLabels;
   @SerId(3)
   private int initialExpansionLevel;

   public StaticTreeDocument(INode var1) {
      this(Arrays.asList(var1));
   }

   public StaticTreeDocument(List var1) {
      this(var1, null, 0);
   }

   public StaticTreeDocument(List var1, List var2, int var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Tree root should not be null");
      } else {
         for (INode var5 : var1) {
            if (var5 == null) {
               throw new IllegalArgumentException("Tree root should not be null");
            }
         }

         this.roots = new ArrayList(var1);
         this.columnLabels = var2 == null ? new ArrayList() : new ArrayList(var2);
         this.initialExpansionLevel = var3;
      }
   }

   @Override
   public List getRoots() {
      return this.roots;
   }

   @Override
   public List getColumnLabels() {
      return this.columnLabels;
   }

   @Override
   public int getInitialExpansionLevel() {
      return this.initialExpansionLevel;
   }
}
