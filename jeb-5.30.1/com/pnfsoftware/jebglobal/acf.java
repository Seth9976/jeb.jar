package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class acf extends IL {
   public acf() {
      super(new aay.eo().q(ProcessorType.ARM, ProcessorType.ARM64).xK(11).q());
   }

   @Override
   public List Dw() {
      ArrayList var1 = new ArrayList();
      var1.add(new FS());
      return var1;
   }
}
