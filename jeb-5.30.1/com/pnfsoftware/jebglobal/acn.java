package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class acn extends IL {
   public acn() {
      super(new aay.eo().q(ProcessorType.X86, ProcessorType.X86_64).q("winpe").q());
   }
}
