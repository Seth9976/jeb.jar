package com.pnfsoftware.jeb.core.units.code.debug;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterData;
import java.util.List;

public interface IDebuggerThread {
   long getId();

   String getName();

   DebuggerThreadStatus getStatus();

   boolean resume();

   boolean suspend();

   boolean stepInto();

   boolean stepOver();

   boolean stepOut();

   int getFrameCount();

   IDebuggerThreadStackFrame getFrame(int var1);

   List getFrames();

   String getLocation();

   IRegisterData getRegisters();

   boolean setRegisters(IRegisterData var1);
}
