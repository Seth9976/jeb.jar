package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIE;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDwCompileUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class cds extends cdm implements IDwCompileUnit {
   public cds(long var1) {
      super(var1, -1, null);
   }

   @Override
   public void pC(IDIE var1) {
      if (this.getChildren().size() == 1) {
         throw new IllegalStateException("Can not add child to Compile Unit");
      } else {
         super.pC(var1);
      }
   }

   @Override
   public void pC(cdn var1) {
      throw new IllegalStateException("Can not add attribute to Compile Unit");
   }

   @Override
   public String getTagName() {
      return "COMPILE_UNIT";
   }
}
