package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.core.units.code.ICodeInstruction;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface INativeInstructionItem extends ICodeInstruction, INativeContinuousItem {
   InstructionHints getHints(boolean var1);
}
