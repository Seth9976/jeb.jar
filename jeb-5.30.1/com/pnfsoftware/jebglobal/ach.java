package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class ach extends IL {
   public ach() {
      super(new aay.eo().q(ProcessorType.AVR, ProcessorType.AVR32).q());
   }

   @Override
   public List Dw() {
      ArrayList var1 = new ArrayList();
      var1.add(new bcs());
      return var1;
   }
}
