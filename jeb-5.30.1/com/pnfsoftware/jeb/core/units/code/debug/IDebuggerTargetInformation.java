package com.pnfsoftware.jeb.core.units.code.debug;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.io.Endianness;

public interface IDebuggerTargetInformation {
   ProcessorType getProcessorType();

   Endianness getEndianness();

   String getStringDescription();
}
