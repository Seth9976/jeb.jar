package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aau extends YA {
   public aau() {
      super(new VM.Av().pC("wincoff", "winpe").pC(ProcessorType.X86, ProcessorType.X86_64).pC());
   }
}
