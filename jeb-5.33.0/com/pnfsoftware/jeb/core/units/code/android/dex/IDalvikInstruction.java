package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.ILocatedInstruction;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IDalvikInstruction extends ILocatedInstruction {
   int TYPE_REG = 0;
   int TYPE_IMM = 1;
   int TYPE_IDX = 2;
   int TYPE_BRA = 3;
   int TYPE_RGR = 4;
   int TYPE_IDX2 = 5;
   int INDEX_TO_STRING = 16;
   int INDEX_TO_TYPE = 17;
   int INDEX_TO_FIELD = 18;
   int INDEX_TO_METHOD = 19;
   int INDEX_TO_PROTOTYPE = 20;
   int INDEX_TO_CALL_SITE = 21;
   int INDEX_TO_METHOD_HANDLE = 22;
   int INDEX_IS_INLINE_OFFSET = 32;
   int INDEX_IS_VTABLE_OFFSET = 33;
   int INDEX_IS_FIELD_OFFSET = 34;

   @Override
   byte[] getCode();

   int getOpcode();

   IDalvikInstructionParameter[] getParameters();

   IDalvikInstructionParameter getParameter(int var1);

   int getParameterCount();

   int getParameterType(int var1);

   long getParameterValue(int var1);

   int getParameterFirstIndexType();

   int getParameterSecondIndexType();

   boolean isPseudoInstruction();

   boolean isSwitch();

   boolean isArray();

   IDalvikInstructionSwitchData getSwitchData();

   IDalvikInstructionArrayData getArrayData();

   boolean isOptimized();
}
