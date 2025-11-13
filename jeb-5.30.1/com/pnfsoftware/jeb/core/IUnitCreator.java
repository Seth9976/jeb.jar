package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.util.events.IEventSource;

public interface IUnitCreator extends IEventSource {
   IInput getInput();

   String getName();

   IUnitCreator getParent();
}
