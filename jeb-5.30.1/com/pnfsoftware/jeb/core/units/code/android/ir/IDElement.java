package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;

public interface IDElement extends IInstructionOperand {
   boolean DEFAULT_COMPARE_WITH_FULL_EQUALITY = false;

   IDElement copy(DCopyOptions var1);

   IDElement duplicate();

   boolean equalsEx(Object var1, boolean var2);

   void format(DFormattingContext var1);

   String toString(IDMethodContext var1);

   @Override
   String toString();
}
