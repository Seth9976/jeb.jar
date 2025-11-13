package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aav extends YA {
   public aav() {
      super(new VM.Av().pC(ProcessorType.X86, ProcessorType.X86_64).pC("winpe").pC());
   }
}
