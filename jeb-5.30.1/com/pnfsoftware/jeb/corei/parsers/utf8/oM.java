package com.pnfsoftware.jeb.corei.parsers.utf8;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.tree.impl.Node;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class oM extends Node {
   public oM(String var1, ItemClassIdentifiers var2, long var3, int var5) {
      super(var1, var2, var3, var5);
   }

   @Override
   public String[] getAdditionalLabels() {
      return new String[]{"foo\n12", "bar"};
   }
}
