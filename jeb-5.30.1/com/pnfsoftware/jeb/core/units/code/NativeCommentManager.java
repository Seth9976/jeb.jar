package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class NativeCommentManager extends CodeCommentManager {
   @SerId(1)
   private INativeCodeUnit pbcu;

   public NativeCommentManager(INativeCodeUnit var1) {
      super(var1);
      this.pbcu = var1;
   }

   public long addressToMemory(String var1) {
      return this.pbcu.getCanonicalMemoryAddress(var1);
   }

   public String memoryToAddress(long var1) {
      return this.pbcu.getSymbolicStringAddress(var1, 2);
   }

   public long coordToMemory(ICodeCoordinates var1) {
      String var2 = this.pbcu.getAddressFromCodeCoordinates(var1);
      return var2 == null ? -1L : this.pbcu.getCanonicalMemoryAddress(var2);
   }

   public ICodeCoordinates memoryToCoord(long var1) {
      String var3 = this.pbcu.getSymbolicStringAddress(var1, 2);
      return var3 == null ? null : this.pbcu.getCodeCoordinatesFromAddress(var3);
   }
}
