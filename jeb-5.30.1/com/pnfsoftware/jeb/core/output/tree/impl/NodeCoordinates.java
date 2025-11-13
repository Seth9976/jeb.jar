package com.pnfsoftware.jeb.core.output.tree.impl;

import com.pnfsoftware.jeb.core.output.tree.INodeCoordinates;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class NodeCoordinates implements INodeCoordinates {
   @SerId(1)
   private List path;

   public NodeCoordinates(List var1) {
      this.path = var1;
   }

   @Override
   public List getPath() {
      return this.path;
   }

   @Override
   public String toString() {
      return AbstractTreeDocument.formatIntegerList(this.path);
   }
}
