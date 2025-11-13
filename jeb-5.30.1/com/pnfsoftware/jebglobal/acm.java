package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class acm extends IL {
   public acm() {
      super(new aay.eo().q("wincoff", "winpe").q(ProcessorType.X86, ProcessorType.X86_64).q());
   }
}
