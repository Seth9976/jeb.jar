package com.pnfsoftware.jeb.core.output.tree;

import com.pnfsoftware.jeb.core.units.code.ICodeItem;
import java.util.List;

public interface ICodeNode extends IActionableNode {
   boolean hasChildren();

   int getCountOfChildren();

   @Override
   List getChildren();

   ICodeNode getParent();

   ICodeItem getObject();

   ICodeNode findNodeByObject(ICodeItem var1);
}
