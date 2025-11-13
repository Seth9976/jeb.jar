package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.code.asm.INativeDebuggerUnit;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerUnit;

public interface IDalvikDebuggerUnit extends IDebuggerUnit {
   int getTargetApiLevel();

   Version getJdwpProtocolVersion();

   void setThreadFrameSlotIndexMode(IDalvikDebuggerUnit.ThreadFrameSlotIndexMode var1);

   IDalvikDebuggerUnit.ThreadFrameSlotIndexMode getThreadFrameSlotIndexMode();

   INativeDebuggerUnit getNativeDebugger();

   public static enum ThreadFrameSlotIndexMode {
      AUTO,
      PAR,
      VAR;
   }
}
