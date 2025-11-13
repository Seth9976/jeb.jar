package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.core.units.IInteractiveUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class CommentManager extends AbstractCommentManager {
   public CommentManager(IInteractiveUnit var1) {
      super(var1);
   }

   public String addressToCoord(String var1) {
      return var1;
   }

   public String coordToAddress(String var1) {
      return var1;
   }
}
