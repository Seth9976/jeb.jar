package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.CodeCommentManager;
import com.pnfsoftware.jeb.corei.parsers.dex.vi;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class DexCommentManager extends CodeCommentManager {
   @SerId(1)
   private vi dex;

   public DexCommentManager(IDexUnit var1) {
      super(var1);
      this.dex = (vi)var1;
   }

   @Override
   public ICodeCoordinates addressToCoord(String var1) {
      return this.dex.A().pC(var1);
   }

   @Override
   public String coordToAddress(ICodeCoordinates var1) {
      return this.dex.A().pC(var1, false, true);
   }
}
