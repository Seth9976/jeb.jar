package com.pnfsoftware.jeb.core.units.code.debug;

import com.pnfsoftware.jeb.core.units.IUnitIdentifier;

public interface IDebuggerUnitIdentifier extends IUnitIdentifier {
   IDebuggerTargetEnumerator getTargetEnumerator();
}
