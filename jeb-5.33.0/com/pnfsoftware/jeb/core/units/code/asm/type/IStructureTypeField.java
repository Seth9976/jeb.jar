package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IStructureTypeField {
   int FLAG_HINT_RVA = 1;
   int FLAG_ANONYMOUS = 2097152;

   boolean isSynthetic();

   boolean isAnonymous();

   String getName();

   String getName(boolean var1);

   INativeType getType();

   int getOffset();

   int getSize();

   int getEndOffset();

   boolean isBitfield();

   int getBitstart();

   int getBitsize();

   int getBitend();

   int getAlignment();

   int getFlags();
}
