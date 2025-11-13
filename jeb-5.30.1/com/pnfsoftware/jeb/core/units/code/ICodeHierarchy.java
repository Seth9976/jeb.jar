package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.core.output.tree.ICodeNode;

public interface ICodeHierarchy {
   ICodeNode getRoot();

   default ICodeNode findNode(String var1) {
      return this.findNode(var1, false);
   }

   ICodeNode findNode(String var1, boolean var2);
}
