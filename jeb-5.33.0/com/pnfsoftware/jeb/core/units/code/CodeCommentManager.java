package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.impl.AbstractCommentManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class CodeCommentManager extends AbstractCommentManager {
   public static final int COMMENT_FLAG_ASSEMBLY_ONLY = 1;
   public static final int COMMENT_FLAG_SOURCE_ONLY = 2;
   public static final int COMMENT_FLAG_DECOMPILER_META = 256;
   @SerId(1)
   private ICodeUnit code;

   public CodeCommentManager(ICodeUnit var1) {
      super(var1);
      this.code = var1;
   }

   public ICodeCoordinates addressToCoord(String var1) {
      return this.code.getCodeCoordinatesFromAddress(var1);
   }

   public String coordToAddress(ICodeCoordinates var1) {
      return this.code.getAddressFromCodeCoordinates(var1);
   }
}
