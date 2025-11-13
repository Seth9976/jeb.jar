package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;

public interface IDalvikInstructionParameter extends IInstructionOperand {
   int getType();

   long getValue();
}
