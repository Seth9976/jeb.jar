package com.pnfsoftware.jeb.core.actions;

import com.pnfsoftware.jeb.core.output.tree.ICodeNode;

public class ActionTypeHierarchyData extends ActionData {
   private ICodeNode baseNode;
   private ICodeNode upNode;

   public void setBaseNode(ICodeNode var1) {
      this.baseNode = var1;
   }

   public ICodeNode getBaseNode() {
      return this.baseNode;
   }

   public void setBaseNodeForAscendingHierarchy(ICodeNode var1) {
      this.upNode = var1;
   }

   public ICodeNode getBaseNodeForAscendingHierarchy() {
      return this.upNode;
   }
}
