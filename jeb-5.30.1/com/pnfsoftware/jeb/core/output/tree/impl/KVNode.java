package com.pnfsoftware.jeb.core.output.tree.impl;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class KVNode extends Node {
   @SerId(1)
   private String[] addLabels;

   public KVNode(String var1) {
      this(var1, null);
   }

   public KVNode(String var1, Object var2) {
      super(var1);
      this.addLabels = new String[]{var2 == null ? "" : var2.toString()};
   }

   @Override
   public String[] getAdditionalLabels() {
      return this.addLabels;
   }

   public void setAdditionalLabels(String[] var1) {
      this.addLabels = var1;
   }
}
