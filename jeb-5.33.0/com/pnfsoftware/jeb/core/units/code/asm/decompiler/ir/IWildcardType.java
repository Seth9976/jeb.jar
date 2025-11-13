package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLayoutInfo;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IWildcardType extends INativeItem, Comparable {
   INativeType getNativeType();

   boolean isVoid();

   int getBitsize();

   int getMaximumBitsize();

   int getEffectiveBitsize();

   int getSlotCount();

   IWildcardType.Group getGroup();

   boolean isFloat();

   boolean isSigned();

   boolean isUnsigned();

   default boolean isInteger() {
      return this.isSigned() || this.isUnsigned();
   }

   boolean isPointer();

   boolean isWildcardPointer();

   int getPointedBitsize();

   int getPointedSize();

   IWildcardType.Group getPointedGroup();

   IWildcardType updateEffectiveBitsize(int var1);

   IWildcardType updateMaxBitsize(int var1);

   IWildcardType updateGroup(IWildcardType.Group var1);

   IWildcardType updatePointedBitsize(int var1);

   IWildcardType updatePointedGroup(IWildcardType.Group var1);

   boolean isLessSpecializedThan(IWildcardType var1);

   boolean isDefined();

   default boolean isResolved() {
      return this.isDefined();
   }

   boolean isPartiallyDefined();

   boolean isUndefined();

   boolean isUpdatable();

   IWildcardType resolveU();

   IWildcardType resolveA();

   String toString(boolean var1);

   @Override
   String toString();

   IWildcardType updateProperties(IWildcardType var1);

   TypeLayoutInfo getLayoutInfo();

   boolean isEquivalent(IWildcardType var1);

   @Ser
   public static enum Group {
      INTEGER,
      UINT,
      POINTER,
      FLOAT;
   }
}
