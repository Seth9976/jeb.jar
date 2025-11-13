package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.input.IInput;

public interface IBinaryUnit extends IUnit {
   String getMimeType();

   @Override
   IInput getInput();
}
